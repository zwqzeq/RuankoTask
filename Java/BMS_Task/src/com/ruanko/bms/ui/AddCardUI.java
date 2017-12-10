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
 * 办理银行卡界面
 */
@SuppressWarnings("serial")
public class AddCardUI extends JDialog {
	// 用户输入控件
	//1.idNum
	private JLabel idNumLabel = null;//身份证号标签
	private JTextField idNumField = null;//身份证号文本域
	//2.pwd
	private JLabel pwdLabel = null;//设置密码标签
	private JLabel pwdConfirmLabel = null;//确认密码标签
	private JPasswordField pwdField = null;//设置密码文本框
	private JPasswordField pwdConfirmField = null;//确认密码文本框
	//3.type
	private JLabel cardTypeLabel = null;//卡类型标签
	private JRadioButton savingCardBtn = null;//储蓄卡大选按钮
	private JRadioButton creditCardBtn = null;//信用卡单选按钮
	private ButtonGroup cardBtnGroup = null;//卡类型按钮组
    //4.money
	private JLabel moneyLabel = null;//全额标签
	private JTextField moneyField = null;//金额文本框
	//5.creditLimit
	private JLabel creditLimitLabel = null;//信用额度标签
	private JTextField creditLimitField = null;//信用额度文本框
	//6.statementDay
	private JLabel stmtDayLabel = null;//账单日标签
	private JTextField stmtDayField = null;//账单日文本框
	//7.deadline
	private JLabel deadlineLabel = null;//最后还款日标签
	private JTextField deadlineField = null;//最后还款日文本域
	
	// 其他控件
	private JButton okBtn = null;//确认按钮
	private JButton cancelBtn = null;//取消按钮
	private JLabel addCardLabel = null;//卡办理标签
	private JLabel errLabel = null;//错误信息标签
	
	// 窗体常量
	private static int WIN_WIDTH = 400;//窗体宽度
	private static int WIN_HEIGHT = 500;//窗体高度
	
	private Dimension labelSize = null;//标签大小
	private Dimension textFieldSize = null;//文本框大小
	
	public AddCardUI(Frame owner) {
		super(owner);
		// 设置控件默认大小
		labelSize = new Dimension(100, 30);
		textFieldSize = new Dimension(120, 30);
		
		// 设置窗体信息
		setModal(true);
		setTitle("办理银行卡");
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
	
	// 创建主面板
	private JPanel getContentPanel() {
		JPanel addCardPanel = new JPanel();
		addCardPanel.setLayout(new FlowLayout());
		
		addCardPanel.add(getTitlePanel());
		addCardPanel.add(getIdNumPanel());
		addCardPanel.add(getPwdPanel());
		addCardPanel.add(getPwdConfirmPanel());
		addCardPanel.add(getCardTypePanel());
		addCardPanel.add(getMoneyPanel());
		addCardPanel.add(getCreditLimitPanel());
		addCardPanel.add(getStmtDayPanel());
		addCardPanel.add(getDeadlinePanel());
		addCardPanel.add(getBtnPanel());
		addCardPanel.add(getErrPanel());
		
		return addCardPanel;
	}
	
	// 创建标题行面板
	private JPanel getTitlePanel() {
		// 初始化面板中的控件
		addCardLabel = new JLabel("银行卡信息");
		// 初始化面板
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// 设定面板大小
		titlePanel.setPreferredSize(new Dimension(WIN_WIDTH, 30));
		// 将控件加入到面板中
		titlePanel.add(addCardLabel);
		// 返回面板
		return titlePanel;
	}
	
	// 创建身份证号行面板
	private JPanel getIdNumPanel() {
		// 初始化面板中的控件
		idNumLabel = new JLabel("身份证号");
		idNumField = new JTextField(15);
		// 设定控件的大小
		idNumLabel.setPreferredSize(labelSize);
		idNumField.setPreferredSize(textFieldSize);
		// 初始化面板并将控件加入到面板中
		JPanel idNumPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		idNumPanel.add(idNumLabel);
		idNumPanel.add(idNumField);
		//返回面板
		return idNumPanel;
	}
	
	// 创建卡类型行面板
	private JPanel getCardTypePanel() {
		// 初始化面板中的控件
		cardTypeLabel = new JLabel("类型");
		savingCardBtn = new JRadioButton("储蓄卡");
		creditCardBtn = new JRadioButton("信用卡");
		cardBtnGroup = new ButtonGroup();
		
		savingCardBtn.setSelected(true);
		cardBtnGroup.add(savingCardBtn);
		cardBtnGroup.add(creditCardBtn);
		
		// 设定按钮点击事件监听器
		savingCardBtn.addActionListener(new RadioButtonListener());
		savingCardBtn.setActionCommand("SAVING_CARD");
		creditCardBtn.addActionListener(new RadioButtonListener());
		creditCardBtn.setActionCommand("CREDIT_CARD");
		
		// 设定控件的大小
		cardTypeLabel.setPreferredSize(labelSize);
		savingCardBtn.setPreferredSize(new Dimension(80, 30));
		creditCardBtn.setPreferredSize(new Dimension(80, 30));
		
		// 初始化面板并将控件加入到面板中
		JPanel cardTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		cardTypePanel.add(cardTypeLabel);
		cardTypePanel.add(savingCardBtn);
		cardTypePanel.add(creditCardBtn);
		
		// 返回面板
		return cardTypePanel;
	}
	
	/**
	 * 卡类型单选按钮匿名内部类
	 */
	class RadioButtonListener implements ActionListener {
		/**
		 * 点击事件响应方法
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// 获取ActionCommand信息
			String command = e.getActionCommand();
            if ("SAVING_CARD".equals(command)) {
				// 如果为储蓄卡则禁用信用卡特有信息文本框
            	creditLimitField.setEnabled(false);
            	stmtDayField.setEnabled(false);
            	deadlineField.setEnabled(false);
			} else if ("CREDIT_CARD".equals(command)) {
				// 如果为信用卡则启用信用卡特有信息文本框
				creditLimitField.setEnabled(true);
            	stmtDayField.setEnabled(true);
            	deadlineField.setEnabled(true);
			}
		}
	}
	
	// 创建密码面板
	private JPanel getPwdPanel() {
		// 初始化面板中的控件
		pwdLabel = new JLabel("设置密码");
		pwdField = new JPasswordField(15);
		
		// 设定控件的大小
		pwdLabel.setPreferredSize(labelSize);
		pwdField.setPreferredSize(textFieldSize);
		
		// 初始化面板并将控件加入到面板中
		JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pwdPanel.add(pwdLabel);
		pwdPanel.add(pwdField);
		
		// 返回面板
		return pwdPanel;
	}
	
	private JPanel getPwdConfirmPanel() {
		// 初始化面板中的控件
		pwdConfirmLabel = new JLabel("确认密码");
		pwdConfirmField = new JPasswordField(15);
		// 设定控件的大小
		pwdConfirmLabel.setPreferredSize(labelSize);
		pwdConfirmField.setPreferredSize(textFieldSize);
		// 初始化面板并将控件加入到面板中
		JPanel getPwdConfirmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		getPwdConfirmPanel.add(pwdConfirmLabel);
		getPwdConfirmPanel.add(pwdConfirmField);
		// 返回面板
		return getPwdConfirmPanel;
	}
	
	// 创建金额面板
	private JPanel getMoneyPanel() {
		// 初始化面板中的控件
		moneyLabel = new JLabel("金额");
		moneyField = new JTextField(15);
		// 设定控件的大小
		moneyLabel.setPreferredSize(labelSize);
		moneyField.setPreferredSize(textFieldSize);
		// 初始化面板并将控件加入到面板中
		JPanel moneyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));	
		moneyPanel.add(moneyLabel);
		moneyPanel.add(moneyField);
		// 返回面板
		return moneyPanel;
	}
	
	// 信用额度面板
	private JPanel getCreditLimitPanel() {
		// 初始化面板中的控件
		creditLimitLabel = new JLabel("信用额度");
		creditLimitField = new JTextField(15);
		creditLimitField.setEnabled(false);
		// 设定控件的大小
		creditLimitLabel.setPreferredSize(labelSize);
		creditLimitField.setPreferredSize(textFieldSize);		// 初始化面板并将控件加入到面板中
		// 初始化面板并将控件加入到面板中
		JPanel creditLimitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		creditLimitPanel.add(creditLimitLabel);
		creditLimitPanel.add(creditLimitField);
		// 返回面板
		return creditLimitPanel;
	}
	
	// 账单日面板
	private JPanel getStmtDayPanel() {
		// 初始化面板中的控件
		stmtDayLabel = new JLabel("账单日");
		stmtDayField = new JTextField(15);
		stmtDayField.setEnabled(false);
		// 设定控件的大小
		stmtDayLabel.setPreferredSize(labelSize);
		stmtDayField.setPreferredSize(textFieldSize);		// 初始化面板并将控件加入到面板中
		// 初始化面板并将控件加入到面板中
		JPanel stmtDayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		stmtDayPanel.add(stmtDayLabel);
		stmtDayPanel.add(stmtDayField);
		// 返回面板
		return stmtDayPanel;
	}
	
	// 最后还款日面板
	private JPanel getDeadlinePanel() {
		// 初始化面板中的控件
		deadlineLabel = new JLabel("最后还款日");
		deadlineField = new JTextField(15);
		deadlineField.setEnabled(false);
		// 设定控件的大小
		deadlineLabel.setPreferredSize(labelSize);
		deadlineField.setPreferredSize(textFieldSize);
		// 初始化面板并将控件加入到面板中
		JPanel deadlinePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		deadlinePanel.add(deadlineLabel);
		deadlinePanel.add(deadlineField);
		// 返回面板
		return deadlinePanel;
	}
	
	// 确定按钮
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
					
					String cardNo = "";
					boolean isSuccess = false;
					// 获取卡类型
					int cardType = savingCardBtn.isSelected() ? 1:2;
					
					if (cardType == Card.SAVING_CARD) {//卡类型为储蓄卡
						//创建SavingCard对象并填充信息
						SavingCard savingCard = new SavingCard();
						savingCard.setIdNum(idNumField.getText());
						savingCard.setMoney(Float.parseFloat(moneyField.getText()));
						savingCard.setPassword(new String(pwdField.getPassword()));
						
						// 调用savingCard对象的卡办理方法
						isSuccess = savingCard.create();
						cardNo = savingCard.getNo();
					} else if (cardType == Card.CREDIT_CARD) {//卡类型为信用卡
						// 创建CreditCard对象并填充信息
						CreditCard creditCard = new CreditCard();
						creditCard.setIdNum(idNumField.getText());
						creditCard.setCreditLimit(Integer.parseInt(creditLimitField.getText()));
						creditCard.setDeadline(Integer.parseInt(deadlineField.getText()));
						creditCard.setMoney(Float.parseFloat(moneyField.getText()));
						creditCard.setPassword(new String(pwdField.getPassword()));
						creditCard.setStatementDay(Integer.parseInt(stmtDayField.getText()));
						
						// 调用creditCard对象的卡办理方法
						isSuccess = creditCard.create();
						cardNo = creditCard.getNo();
					}
					
					if (isSuccess) {
						// 开户成功提示用户卡办理成功并提供卡号信息
						String msg = "办理" + ((cardType == 1) ? "储蓄卡":"信用卡") + "成功!\n您的卡号为：" + cardNo + "\n请牢记！";
						JOptionPane.showMessageDialog(AddCardUI.this, msg);
						// 销毁窗体
						AddCardUI.this.dispose();
					} else {
						// 提示用户卡办理失败
						String msg = "用户未开户，办理卡失败！";
						JOptionPane.showMessageDialog(AddCardUI.this, msg);
					}
				}
			});
		}
		
		return okBtn;
	}
	
	// 取消按钮
	private JButton getCancelBtn() {
		if (cancelBtn == null) {
			// 初始化确定按钮
			cancelBtn = new JButton("取消");
			// 设定按钮大小
			cancelBtn.setPreferredSize(new Dimension(80, 30));
			// 利用匿名内部类，添加按钮监听器
			cancelBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AddCardUI.this.dispose();
				}
			});
		}
		return cancelBtn;
	}
	
	// 按钮面板
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
		// 设定面板的大小
		errPanel.setPreferredSize(new Dimension(WIN_WIDTH, 30));
		// 将控件加入到面板中
		errPanel.add(errLabel);
		// 返回面板
		return errPanel;
	}
	
	private boolean dataValidate() {
		// 初始化对象
		boolean isLegal = false;
		String errMsg = null;
		
		// 获取界面输入信息
		String idNum = idNumField.getText();
		String pwd = new String(pwdField.getPassword()).trim();
		String pwdConfirm = new String(pwdConfirmField.getPassword()).trim();
		int cardType = savingCardBtn.isSelected() ? 1:2;
		String money = moneyField.getText();
		String creditLimit = creditLimitField.getText();
		String stmtDay = stmtDayField.getText();
		String deadLine = deadlineField.getText();
		
		// 清空错误信息标签内容
		errLabel.setText("");
		
		// 数据验证，验证储蓄卡、信用卡共用信息
		if (idNum == null || idNum.length() == 0) {
			errMsg = "身份证号不能为空";
		} else if (pwd == null || pwd.length() == 0) {
			errMsg = "密码不能为空";
		} else if (pwdConfirm == null || pwdConfirm.length() == 0) {
			errMsg = "确认密码不能为空";
		} else if (!pwd.equals(pwdConfirm)) {
			errMsg = "密码与确认密码不匹配";
		} else if (money == null || money.length() == 0) {
			errMsg = "金额不能为空";
		}
		
		try {
			if (errMsg == null) {
				Float.parseFloat(money);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			errMsg = "金额必须为数字";
		}
		// 验证信用卡持有信息
		try {
			if (errMsg == null && cardType == 2) {
				Float.parseFloat(creditLimit);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			errMsg = "信用额度必须为数字";
		}
		try {
			if (errMsg == null && cardType == 2) {
				int stmtDayNum = Integer.parseInt(stmtDay);
				if (stmtDayNum > 28 || stmtDayNum < 1) {
					errMsg = "账单必须为1~28的数字";
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			errMsg = "账单必须为1~28的数字";
		}
		
		try {
			if (errMsg == null && cardType == 2) {
				int deadlineNum = Integer.parseInt(deadLine);
				if (deadlineNum > 28 || deadlineNum < 1) {
					errMsg = "最后还款日必须为1~28的数字";
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			errMsg = "最后还款日必须为1~28的数字";
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
