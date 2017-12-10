package com.ruanko.bms.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ruanko.bms.io.RecordIO;
import com.ruanko.bms.model.Record;
import com.ruanko.bms.utils.Tools;

@SuppressWarnings("serial")
public class RecordUI extends JDialog {
	// 用户输入控件
	private JLabel idNumLabel = null;//身份证号标签
	private JTextField idNumField = null;//身份证号文本框
	
	// 其他控件
	private JButton okBtn = null;//确定按钮
	private JTable table = null;//账务报表表格
	private DefaultTableModel tableModel = null;//账务报表表格模式
	private JScrollPane scrollPane = null;//账务报表表格滚动面板
	
	// 窗体常量
	private static int WIN_WIDTH = 600;//窗体宽度
	private static int WIN_HEIGHT = 450;//窗体高度
	private static final String[] TABLE_COLS = {"银行卡类型", "卡号", "摘要", "存入(+)/支出(-)", "余额", "日期"};//账务报表表格表头信息
	
	
	public RecordUI(Frame owner) {
		super(owner);
		
		// 设置窗体信息
		setModal(true);
		setTitle("账务查询");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIN_WIDTH, WIN_HEIGHT);
		setResizable(false);
		
		// 窗体居中
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension scrSize = tk.getScreenSize();
		setLocation((scrSize.width - WIN_WIDTH)/2, (scrSize.height - WIN_HEIGHT)/2);
	    
		// 设置面板内容
		setContentPane(getContentPanel());
		// 显示窗体
		setVisible(true);
	}
	
	private JPanel getContentPanel() {
		//初始化主面板
		JPanel recordPanel = new JPanel();
		//设置布局为浮动布局
		recordPanel.setLayout(new BorderLayout());
		//将行面板加入到主面板中
		recordPanel.add(getSearchPanel(), BorderLayout.NORTH);
		recordPanel.add(getTablePanel(), BorderLayout.CENTER);
		//返回主面板
		return recordPanel;
	}
	
	private JPanel getSearchPanel() {
		// 初始化面板中的控件
		idNumLabel = new JLabel("身份证号");
		idNumField = new JTextField(15);
		
		// 初始化面板
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		// 设定控件大小
		idNumLabel.setPreferredSize(new Dimension(80, 30));
		idNumField.setPreferredSize(new Dimension(200, 30));
		
		// 将控件加入到面板中
		searchPanel.add(idNumLabel);
		searchPanel.add(idNumField);
		searchPanel.add(getOkBtn());
		
		// 返回面板
		return searchPanel;
	}
	
	private JScrollPane getTablePanel() {
		
		// 用来操作表格中的数据
		tableModel = new DefaultTableModel();
		
		// 表格的核心类，用来显示表格
		table = new JTable(tableModel);
		
		// 滚动条面板
		scrollPane = new JScrollPane();
		
		// 添加组件
		scrollPane.setViewportView(table);
		
		// 设定控件属性
		tableModel.setColumnIdentifiers(TABLE_COLS);
		scrollPane.setPreferredSize(new Dimension(580, 350));
		
		// 调整卡号一列的宽度
		table.getColumnModel().getColumn(1).setMinWidth(150);
		
		// 返回面板
		return scrollPane;
	}
	
	/**
	 * 确定按钮
	 * @return
	 */
	private JButton getOkBtn() {
		if (okBtn == null) {
			// 初始化确定按钮
			okBtn = new JButton("确定");
			// 设定按钮大小
			okBtn.setPreferredSize(new Dimension(80, 30));
			// 利用匿名内部类，添加按钮监听器
			okBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// 清空表格
					tableModel.getDataVector().clear();
					// 刷新表格
					tableModel.fireTableDataChanged();
					
					// 如果校验不通过则返回
					if (!dataValidate()) {
						return;
					}
					
					// 创建IO操作对象
					RecordIO recordIO = new RecordIO();
					
					// 根据身份证号查询操作记录
					List<Record> list = recordIO.findByID(idNumField.getText());
					
					// 如果操作记录有内容，则显示在界面上
					if (list != null && list.size() > 0) {
						for (Record record : list) {
							Object[] row = new Object[] {
								record.getType(),
								record.getNo(),
								record.getDesc(),
								record.getChangedMoney(),
								record.getBalance(),
								Tools.formatDate(record.getDate())
							};
							tableModel.addRow(row);
						}
					} else {
						// 提示用户未查询到信息
						JOptionPane.showMessageDialog(RecordUI.this, "未查询到信息！");
					}
					
				}
			});
			
		}
		return okBtn;
	}
	
	private boolean dataValidate() {
		// 初始化对象
		boolean isLegal = false;
		
		// 获取界面输入
		String idNum = idNumField.getText();
		
		// 数据验证
		if (idNum == null || idNum.length() == 0) {
			JOptionPane.showMessageDialog(RecordUI.this, "身份证号不能为空！");	
		} else {
			isLegal = true;
		}
		return isLegal;
	}

}
