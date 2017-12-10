package com.ruanko.bms.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.ruanko.bms.model.Card;
import com.ruanko.bms.model.CreditCard;
import com.ruanko.bms.model.SavingCard;

/**
 * 财务操作界面类
 */
@SuppressWarnings("serial")
public class AccountOperateUI extends JDialog {
	// 操作类型选择按钮
	private JRadioButton depositBtn = null;//存款按钮
	private JRadioButton withdrawBtn = null;//取款按钮
	private JRadioButton cashBtn = null;//消费按钮
	private ButtonGroup typeBtnGroup = null;//操作类型选择按钮组
	
	// 卡号
	private JLabel noLabel = null;//卡号标签
	private JTextField noField = null;//卡号文本框
	
	// 密码
	private JLabel pwdLabel = null;//密码标签
	private JPasswordField pwdField = null;//密码文本框
	
	// 金额
	private JLabel moneyLabel = null;//金额标签
	private JTextField moneyField = null;//金额文本框
	
	// 其他控件
	private JButton okBtn = null;//确认按钮
	private JButton cancelBtn = null;//取消按钮
	private JLabel errLabel = null;//错误信息按钮
	
	// 窗体常量
	private static int WIN_WIDTH = 400;//窗体宽度
	private static int WIN_HEIGHT = 300;//窗体高度
	
	private Dimension labelSize = null;
	private Dimension textFieldSize = null;
	
	/**
	 * 构造方法
	 * @param owner
	 */
	public AccountOperateUI(Frame owner) {
		super(owner);
		
		// 设置控件默认大小
		labelSize = new Dimension(70, 30);
		textFieldSize = new Dimension(500, 30);
		
		// 设置窗体信息
		setModal(true);
		setTitle("财务操作");
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
		JPanel operatePanel = new JPanel();
		
		//设置布局为浮动布局
		operatePanel.setLayout(new FlowLayout());
		
		//将行面板加入到主面板中
		operatePanel.add(getOperateTypePanel());
		operatePanel.add(getNoPanel());
		operatePanel.add(getPwdPanel());
		operatePanel.add(getMoneyPanel());
		operatePanel.add(getBtnPanel());
		operatePanel.add(getErrPanel());
		
		// 返回主面板
		return operatePanel;
	}
	
	private JPanel getOperateTypePanel() {
		// 初始化面板中的控件
		depositBtn = new JRadioButton("存款");		
		withdrawBtn = new JRadioButton("取款");
		cashBtn = new JRadioButton("消费");
		typeBtnGroup = new ButtonGroup();
		
		depositBtn.setSelected(true);
		typeBtnGroup.add(depositBtn);
		typeBtnGroup.add(withdrawBtn);
		typeBtnGroup.add(cashBtn);
		
		// 注册监听器
		depositBtn.addActionListener(new RadioButtonListener());
		withdrawBtn.addActionListener(new RadioButtonListener());
		cashBtn.addActionListener(new RadioButtonListener());
		
		//为按钮添加动作指令
		depositBtn.setActionCommand("CARD_DEPOSIT");	
		withdrawBtn.setActionCommand("CARD_WITHDRAW");
		cashBtn.setActionCommand("CARD_CASH");
		
		// 设定控件大小
		depositBtn.setPreferredSize(new Dimension(80, 30));
		withdrawBtn.setPreferredSize(new Dimension(80, 30));
		cashBtn.setPreferredSize(new Dimension(80, 30));
		
		// 初始化面板并将控件加入到面板中
		JPanel cardTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		cardTypePanel.add(depositBtn);
		cardTypePanel.add(withdrawBtn);
		cardTypePanel.add(cashBtn);
		
		// 返回面板
		return cardTypePanel;
	}
	
	private JPanel getNoPanel() {
		// 初始化面板中的控件
		noLabel = new JLabel("卡号");
		noField = new JTextField(15);
		
		// 设定控件大小
		noLabel.setPreferredSize(labelSize);
		noField.setPreferredSize(textFieldSize);
		
		// 初始化面板并将控件加入到面板中
		JPanel noPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		noPanel.add(noLabel);
		noPanel.add(noField);
		
		// 返回面板
		return noPanel;
	}
	
	private JPanel getPwdPanel() {
		// 初始化面板中的控件
		pwdLabel = new JLabel("密码");
		pwdField = new JPasswordField(15);
		
		// 设定控件大小
		pwdLabel.setPreferredSize(labelSize);
		pwdField.setPreferredSize(textFieldSize);
		
		// 初始化面板并将控件加入到面板中
		JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pwdPanel.add(pwdLabel);
		pwdPanel.add(pwdField);
		pwdField.setEnabled(false);
		
		// 返回面板
		return pwdPanel;
	}
	
	private JPanel getMoneyPanel() {
		// 初始化面板中的控件
		moneyLabel = new JLabel("金额");
		moneyField = new JTextField(15);
		
		// 设定控件大小
		moneyLabel.setPreferredSize(labelSize);
		moneyField.setPreferredSize(textFieldSize);
		
		// 初始化面板并将控件加入到面板中
		JPanel moneyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		moneyPanel.add(moneyLabel);
		moneyPanel.add(moneyField);
		
		// 返回面板
		return moneyPanel;
	}
	
	private JPanel getBtnPanel() {
		// 初始化面板并将控件加入到面板中
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPanel.add(getOkBtn());
		btnPanel.add(getCancelBtn());
		
		return btnPanel;
	}
	
	private JPanel getErrPanel() {
		// 初始化面板中的控件
		errLabel = new JLabel();
		errLabel.setForeground(Color.RED);
		// 初始化面板
		JPanel errPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// 设定面板大小
		errPanel.setPreferredSize(new Dimension(WIN_WIDTH, 30));	
		// 将控件加入到面板中
		errPanel.add(errLabel);
		// 返回面板
		return errPanel;
	}
	
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
					// 如果校验不通过则返回
					if (!dataValidate()) {
						return;
					}
					
					String cardNo = noField.getText().trim();
					String pwd = new String(pwdField.getPassword());
					Float amount = Float.parseFloat(moneyField.getText());
					
					Card card = null;
					
					// 获取卡号类型
					int cardType = Integer.parseInt(cardNo.substring(0, 1));
					
					// 根据不同类型创建不同的对象，父类引用指向子类对象
					switch (cardType) {
					case Card.SAVING_CARD:
						card = new SavingCard();
						break;
					case Card.CREDIT_CARD:
						card = new CreditCard();
						break;
					default:
						errLabel.setText("该卡类型不存在");
						return;
					}
					
					// 存款操作
					if (depositBtn.isSelected()) {
						if (card != null && card.loadByNo(cardNo)) {
							card.deposit(amount);
						} else {
							errLabel.setText("载入卡信息失败，请重新输入卡号");
							return;
						}
					}
					
					// 取款操作
					else if (withdrawBtn.isSelected()) {
						if (!(card != null && card.loadByNoAndPwd(cardNo, pwd))) {
							errLabel.setText("载入卡信息失败，请重新输入卡号和密码");
							return;
						}
						
						if (!card.withdraw(amount)) {
							errLabel.setText("余额不足，请重新输入金额");
							return;
						}
					}
					
					// 消费
					else if (cashBtn.isSelected()) {
						if (!(card != null && card.loadByNoAndPwd(cardNo, pwd))) {
							errLabel.setText("载入卡信息失败，请重新输入卡号和密码");
							return;
						}
						
						if (!card.cash(amount)) {
							errLabel.setText("余额不足，请重新输入金额");
							return;
						}
					}
					
					JOptionPane.showMessageDialog(AccountOperateUI.this, "操作成功！");
					AccountOperateUI.this.dispose();
				}
			});
		}
		return okBtn;
	}
	
	private JButton getCancelBtn() {
		if (cancelBtn == null) {
			// 初始化取消按钮
			cancelBtn = new JButton("取消");
			// 设定按钮大小
			cancelBtn.setPreferredSize(new Dimension(80, 30));
			// 利用匿名内部类，添加按钮监听器
			cancelBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AccountOperateUI.this.dispose();
				}
			});
		}
		return cancelBtn;
	}
	
	/**
	 * 操作类型选择按钮监听器
	 */
	class RadioButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 获取ActionCommand信息
			String command = e.getActionCommand();
			if ("CARD_DEPOSIT".equals(command)) {
				// 如果为存款则禁用密码信息文本框
				pwdField.setEnabled(false);
			} else {
				// 如果为取款或消费则使用密码信息文本框
				pwdField.setEnabled(true);
			}
		}
	}
	
	private boolean dataValidate() {
		// 初始化对象
		boolean isLegal = false;
		String errMsg = null;
		// 获取界面输入
		int operateType = depositBtn.isSelected() ? 1:2;
		String no = noField.getText();
		String pwd = new String(pwdField.getPassword()).trim();
		String money = moneyField.getText();
		// 清空错误信息标签内容
		errLabel.setText("");
		// 数据验证
		if (no == null || no.length() == 0) {
			errMsg = "卡号不能为空";
		} else if (money == null || money.length() == 0) {
			errMsg = "金额不能为空";
		}
		
		if (errMsg == null && operateType == 2) {
			if (pwd == null || pwd.length() == 0) {
				errMsg = "密码不能为空";
			}
		}
		
		// 得出验证结果
		if (errMsg != null) {
			errLabel.setText(errMsg);
		} else { 
            isLegal = true;
		}
		return isLegal;
	}

}
