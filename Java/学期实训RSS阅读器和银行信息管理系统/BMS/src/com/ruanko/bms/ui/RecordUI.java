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
	// �û�����ؼ�
	private JLabel idNumLabel = null;//���֤�ű�ǩ
	private JTextField idNumField = null;//���֤���ı���
	
	// �����ؼ�
	private JButton okBtn = null;//ȷ����ť
	private JTable table = null;//���񱨱���
	private DefaultTableModel tableModel = null;//���񱨱���ģʽ
	private JScrollPane scrollPane = null;//���񱨱���������
	
	// ���峣��
	private static int WIN_WIDTH = 600;//������
	private static int WIN_HEIGHT = 450;//����߶�
	private static final String[] TABLE_COLS = {"���п�����", "����", "ժҪ", "����(+)/֧��(-)", "���", "����"};//���񱨱����ͷ��Ϣ
	
	
	public RecordUI(Frame owner) {
		super(owner);
		
		// ���ô�����Ϣ
		setModal(true);
		setTitle("�����ѯ");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIN_WIDTH, WIN_HEIGHT);
		setResizable(false);
		
		// �������
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension scrSize = tk.getScreenSize();
		setLocation((scrSize.width - WIN_WIDTH)/2, (scrSize.height - WIN_HEIGHT)/2);
	    
		// �����������
		setContentPane(getContentPanel());
		// ��ʾ����
		setVisible(true);
	}
	
	private JPanel getContentPanel() {
		//��ʼ�������
		JPanel recordPanel = new JPanel();
		//���ò���Ϊ��������
		recordPanel.setLayout(new BorderLayout());
		//���������뵽�������
		recordPanel.add(getSearchPanel(), BorderLayout.NORTH);
		recordPanel.add(getTablePanel(), BorderLayout.CENTER);
		//���������
		return recordPanel;
	}
	
	private JPanel getSearchPanel() {
		// ��ʼ������еĿؼ�
		idNumLabel = new JLabel("���֤��");
		idNumField = new JTextField(15);
		
		// ��ʼ�����
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		// �趨�ؼ���С
		idNumLabel.setPreferredSize(new Dimension(80, 30));
		idNumField.setPreferredSize(new Dimension(200, 30));
		
		// ���ؼ����뵽�����
		searchPanel.add(idNumLabel);
		searchPanel.add(idNumField);
		searchPanel.add(getOkBtn());
		
		// �������
		return searchPanel;
	}
	
	private JScrollPane getTablePanel() {
		
		// ������������е�����
		tableModel = new DefaultTableModel();
		
		// ���ĺ����࣬������ʾ���
		table = new JTable(tableModel);
		
		// ���������
		scrollPane = new JScrollPane();
		
		// ������
		scrollPane.setViewportView(table);
		
		// �趨�ؼ�����
		tableModel.setColumnIdentifiers(TABLE_COLS);
		scrollPane.setPreferredSize(new Dimension(580, 350));
		
		// ��������һ�еĿ��
		table.getColumnModel().getColumn(1).setMinWidth(150);
		
		// �������
		return scrollPane;
	}
	
	/**
	 * ȷ����ť
	 * @return
	 */
	private JButton getOkBtn() {
		if (okBtn == null) {
			// ��ʼ��ȷ����ť
			okBtn = new JButton("ȷ��");
			// �趨��ť��С
			okBtn.setPreferredSize(new Dimension(80, 30));
			// ���������ڲ��࣬��Ӱ�ť������
			okBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// ��ձ��
					tableModel.getDataVector().clear();
					// ˢ�±��
					tableModel.fireTableDataChanged();
					
					// ���У�鲻ͨ���򷵻�
					if (!dataValidate()) {
						return;
					}
					
					// ����IO��������
					RecordIO recordIO = new RecordIO();
					
					// �������֤�Ų�ѯ������¼
					List<Record> list = recordIO.findByID(idNumField.getText());
					
					// ���������¼�����ݣ�����ʾ�ڽ�����
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
						// ��ʾ�û�δ��ѯ����Ϣ
						JOptionPane.showMessageDialog(RecordUI.this, "δ��ѯ����Ϣ��");
					}
					
				}
			});
			
		}
		return okBtn;
	}
	
	private boolean dataValidate() {
		// ��ʼ������
		boolean isLegal = false;
		
		// ��ȡ��������
		String idNum = idNumField.getText();
		
		// ������֤
		if (idNum == null || idNum.length() == 0) {
			JOptionPane.showMessageDialog(RecordUI.this, "���֤�Ų���Ϊ�գ�");	
		} else {
			isLegal = true;
		}
		return isLegal;
	}

}
