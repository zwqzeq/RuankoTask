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
 * �������п�����
 */
@SuppressWarnings("serial")
public class AddCardUI extends JDialog {
	// �û�����ؼ�
	//1.idNum
	private JLabel idNumLabel = null;//���֤�ű�ǩ
	private JTextField idNumField = null;//���֤���ı���
	//2.pwd
	private JLabel pwdLabel = null;//���������ǩ
	private JLabel pwdConfirmLabel = null;//ȷ�������ǩ
	private JPasswordField pwdField = null;//���������ı���
	private JPasswordField pwdConfirmField = null;//ȷ�������ı���
	//3.type
	private JLabel cardTypeLabel = null;//�����ͱ�ǩ
	private JRadioButton savingCardBtn = null;//�����ѡ��ť
	private JRadioButton creditCardBtn = null;//���ÿ���ѡ��ť
	private ButtonGroup cardBtnGroup = null;//�����Ͱ�ť��
    //4.money
	private JLabel moneyLabel = null;//ȫ���ǩ
	private JTextField moneyField = null;//����ı���
	//5.creditLimit
	private JLabel creditLimitLabel = null;//���ö�ȱ�ǩ
	private JTextField creditLimitField = null;//���ö���ı���
	//6.statementDay
	private JLabel stmtDayLabel = null;//�˵��ձ�ǩ
	private JTextField stmtDayField = null;//�˵����ı���
	//7.deadline
	private JLabel deadlineLabel = null;//��󻹿��ձ�ǩ
	private JTextField deadlineField = null;//��󻹿����ı���
	
	// �����ؼ�
	private JButton okBtn = null;//ȷ�ϰ�ť
	private JButton cancelBtn = null;//ȡ����ť
	private JLabel addCardLabel = null;//�������ǩ
	private JLabel errLabel = null;//������Ϣ��ǩ
	
	// ���峣��
	private static int WIN_WIDTH = 400;//������
	private static int WIN_HEIGHT = 500;//����߶�
	
	private Dimension labelSize = null;//��ǩ��С
	private Dimension textFieldSize = null;//�ı����С
	
	public AddCardUI(Frame owner) {
		super(owner);
		// ���ÿؼ�Ĭ�ϴ�С
		labelSize = new Dimension(100, 30);
		textFieldSize = new Dimension(120, 30);
		
		// ���ô�����Ϣ
		setModal(true);
		setTitle("�������п�");
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
	
	// ���������
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
	
	// �������������
	private JPanel getTitlePanel() {
		// ��ʼ������еĿؼ�
		addCardLabel = new JLabel("���п���Ϣ");
		// ��ʼ�����
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// �趨����С
		titlePanel.setPreferredSize(new Dimension(WIN_WIDTH, 30));
		// ���ؼ����뵽�����
		titlePanel.add(addCardLabel);
		// �������
		return titlePanel;
	}
	
	// �������֤�������
	private JPanel getIdNumPanel() {
		// ��ʼ������еĿؼ�
		idNumLabel = new JLabel("���֤��");
		idNumField = new JTextField(15);
		// �趨�ؼ��Ĵ�С
		idNumLabel.setPreferredSize(labelSize);
		idNumField.setPreferredSize(textFieldSize);
		// ��ʼ����岢���ؼ����뵽�����
		JPanel idNumPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		idNumPanel.add(idNumLabel);
		idNumPanel.add(idNumField);
		//�������
		return idNumPanel;
	}
	
	// ���������������
	private JPanel getCardTypePanel() {
		// ��ʼ������еĿؼ�
		cardTypeLabel = new JLabel("����");
		savingCardBtn = new JRadioButton("���");
		creditCardBtn = new JRadioButton("���ÿ�");
		cardBtnGroup = new ButtonGroup();
		
		savingCardBtn.setSelected(true);
		cardBtnGroup.add(savingCardBtn);
		cardBtnGroup.add(creditCardBtn);
		
		// �趨��ť����¼�������
		savingCardBtn.addActionListener(new RadioButtonListener());
		savingCardBtn.setActionCommand("SAVING_CARD");
		creditCardBtn.addActionListener(new RadioButtonListener());
		creditCardBtn.setActionCommand("CREDIT_CARD");
		
		// �趨�ؼ��Ĵ�С
		cardTypeLabel.setPreferredSize(labelSize);
		savingCardBtn.setPreferredSize(new Dimension(80, 30));
		creditCardBtn.setPreferredSize(new Dimension(80, 30));
		
		// ��ʼ����岢���ؼ����뵽�����
		JPanel cardTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		cardTypePanel.add(cardTypeLabel);
		cardTypePanel.add(savingCardBtn);
		cardTypePanel.add(creditCardBtn);
		
		// �������
		return cardTypePanel;
	}
	
	/**
	 * �����͵�ѡ��ť�����ڲ���
	 */
	class RadioButtonListener implements ActionListener {
		/**
		 * ����¼���Ӧ����
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// ��ȡActionCommand��Ϣ
			String command = e.getActionCommand();
            if ("SAVING_CARD".equals(command)) {
				// ���Ϊ�����������ÿ�������Ϣ�ı���
            	creditLimitField.setEnabled(false);
            	stmtDayField.setEnabled(false);
            	deadlineField.setEnabled(false);
			} else if ("CREDIT_CARD".equals(command)) {
				// ���Ϊ���ÿ����������ÿ�������Ϣ�ı���
				creditLimitField.setEnabled(true);
            	stmtDayField.setEnabled(true);
            	deadlineField.setEnabled(true);
			}
		}
	}
	
	// �����������
	private JPanel getPwdPanel() {
		// ��ʼ������еĿؼ�
		pwdLabel = new JLabel("��������");
		pwdField = new JPasswordField(15);
		
		// �趨�ؼ��Ĵ�С
		pwdLabel.setPreferredSize(labelSize);
		pwdField.setPreferredSize(textFieldSize);
		
		// ��ʼ����岢���ؼ����뵽�����
		JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pwdPanel.add(pwdLabel);
		pwdPanel.add(pwdField);
		
		// �������
		return pwdPanel;
	}
	
	private JPanel getPwdConfirmPanel() {
		// ��ʼ������еĿؼ�
		pwdConfirmLabel = new JLabel("ȷ������");
		pwdConfirmField = new JPasswordField(15);
		// �趨�ؼ��Ĵ�С
		pwdConfirmLabel.setPreferredSize(labelSize);
		pwdConfirmField.setPreferredSize(textFieldSize);
		// ��ʼ����岢���ؼ����뵽�����
		JPanel getPwdConfirmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		getPwdConfirmPanel.add(pwdConfirmLabel);
		getPwdConfirmPanel.add(pwdConfirmField);
		// �������
		return getPwdConfirmPanel;
	}
	
	// ����������
	private JPanel getMoneyPanel() {
		// ��ʼ������еĿؼ�
		moneyLabel = new JLabel("���");
		moneyField = new JTextField(15);
		// �趨�ؼ��Ĵ�С
		moneyLabel.setPreferredSize(labelSize);
		moneyField.setPreferredSize(textFieldSize);
		// ��ʼ����岢���ؼ����뵽�����
		JPanel moneyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));	
		moneyPanel.add(moneyLabel);
		moneyPanel.add(moneyField);
		// �������
		return moneyPanel;
	}
	
	// ���ö�����
	private JPanel getCreditLimitPanel() {
		// ��ʼ������еĿؼ�
		creditLimitLabel = new JLabel("���ö��");
		creditLimitField = new JTextField(15);
		creditLimitField.setEnabled(false);
		// �趨�ؼ��Ĵ�С
		creditLimitLabel.setPreferredSize(labelSize);
		creditLimitField.setPreferredSize(textFieldSize);		// ��ʼ����岢���ؼ����뵽�����
		// ��ʼ����岢���ؼ����뵽�����
		JPanel creditLimitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		creditLimitPanel.add(creditLimitLabel);
		creditLimitPanel.add(creditLimitField);
		// �������
		return creditLimitPanel;
	}
	
	// �˵������
	private JPanel getStmtDayPanel() {
		// ��ʼ������еĿؼ�
		stmtDayLabel = new JLabel("�˵���");
		stmtDayField = new JTextField(15);
		stmtDayField.setEnabled(false);
		// �趨�ؼ��Ĵ�С
		stmtDayLabel.setPreferredSize(labelSize);
		stmtDayField.setPreferredSize(textFieldSize);		// ��ʼ����岢���ؼ����뵽�����
		// ��ʼ����岢���ؼ����뵽�����
		JPanel stmtDayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		stmtDayPanel.add(stmtDayLabel);
		stmtDayPanel.add(stmtDayField);
		// �������
		return stmtDayPanel;
	}
	
	// ��󻹿������
	private JPanel getDeadlinePanel() {
		// ��ʼ������еĿؼ�
		deadlineLabel = new JLabel("��󻹿���");
		deadlineField = new JTextField(15);
		deadlineField.setEnabled(false);
		// �趨�ؼ��Ĵ�С
		deadlineLabel.setPreferredSize(labelSize);
		deadlineField.setPreferredSize(textFieldSize);
		// ��ʼ����岢���ؼ����뵽�����
		JPanel deadlinePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		deadlinePanel.add(deadlineLabel);
		deadlinePanel.add(deadlineField);
		// �������
		return deadlinePanel;
	}
	
	// ȷ����ť
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
					// ���У�鲻ͨ���򷵻�
					if (!dataValidate()) {
						return;
					}
					
					String cardNo = "";
					boolean isSuccess = false;
					// ��ȡ������
					int cardType = savingCardBtn.isSelected() ? 1:2;
					
					if (cardType == Card.SAVING_CARD) {//������Ϊ���
						//����SavingCard���������Ϣ
						SavingCard savingCard = new SavingCard();
						savingCard.setIdNum(idNumField.getText());
						savingCard.setMoney(Float.parseFloat(moneyField.getText()));
						savingCard.setPassword(new String(pwdField.getPassword()));
						
						// ����savingCard����Ŀ�������
						isSuccess = savingCard.create();
						cardNo = savingCard.getNo();
					} else if (cardType == Card.CREDIT_CARD) {//������Ϊ���ÿ�
						// ����CreditCard���������Ϣ
						CreditCard creditCard = new CreditCard();
						creditCard.setIdNum(idNumField.getText());
						creditCard.setCreditLimit(Integer.parseInt(creditLimitField.getText()));
						creditCard.setDeadline(Integer.parseInt(deadlineField.getText()));
						creditCard.setMoney(Float.parseFloat(moneyField.getText()));
						creditCard.setPassword(new String(pwdField.getPassword()));
						creditCard.setStatementDay(Integer.parseInt(stmtDayField.getText()));
						
						// ����creditCard����Ŀ�������
						isSuccess = creditCard.create();
						cardNo = creditCard.getNo();
					}
					
					if (isSuccess) {
						// �����ɹ���ʾ�û�������ɹ����ṩ������Ϣ
						String msg = "����" + ((cardType == 1) ? "���":"���ÿ�") + "�ɹ�!\n���Ŀ���Ϊ��" + cardNo + "\n���μǣ�";
						JOptionPane.showMessageDialog(AddCardUI.this, msg);
						// ���ٴ���
						AddCardUI.this.dispose();
					} else {
						// ��ʾ�û�������ʧ��
						String msg = "�û�δ����������ʧ�ܣ�";
						JOptionPane.showMessageDialog(AddCardUI.this, msg);
					}
				}
			});
		}
		
		return okBtn;
	}
	
	// ȡ����ť
	private JButton getCancelBtn() {
		if (cancelBtn == null) {
			// ��ʼ��ȷ����ť
			cancelBtn = new JButton("ȡ��");
			// �趨��ť��С
			cancelBtn.setPreferredSize(new Dimension(80, 30));
			// ���������ڲ��࣬��Ӱ�ť������
			cancelBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AddCardUI.this.dispose();
				}
			});
		}
		return cancelBtn;
	}
	
	// ��ť���
	private JPanel getBtnPanel() {
		// ��ʼ����岢���ؼ����뵽�����
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPanel.add(getOkBtn());
		btnPanel.add(getCancelBtn());
		
		return btnPanel;
	}
	
	private JPanel getErrPanel() {
		// ��ʼ������еĿؼ�
		errLabel = new JLabel();
		errLabel.setForeground(Color.RED);
		// ��ʼ�����
		JPanel errPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// �趨���Ĵ�С
		errPanel.setPreferredSize(new Dimension(WIN_WIDTH, 30));
		// ���ؼ����뵽�����
		errPanel.add(errLabel);
		// �������
		return errPanel;
	}
	
	private boolean dataValidate() {
		// ��ʼ������
		boolean isLegal = false;
		String errMsg = null;
		
		// ��ȡ����������Ϣ
		String idNum = idNumField.getText();
		String pwd = new String(pwdField.getPassword()).trim();
		String pwdConfirm = new String(pwdConfirmField.getPassword()).trim();
		int cardType = savingCardBtn.isSelected() ? 1:2;
		String money = moneyField.getText();
		String creditLimit = creditLimitField.getText();
		String stmtDay = stmtDayField.getText();
		String deadLine = deadlineField.getText();
		
		// ��մ�����Ϣ��ǩ����
		errLabel.setText("");
		
		// ������֤����֤��������ÿ�������Ϣ
		if (idNum == null || idNum.length() == 0) {
			errMsg = "���֤�Ų���Ϊ��";
		} else if (pwd == null || pwd.length() == 0) {
			errMsg = "���벻��Ϊ��";
		} else if (pwdConfirm == null || pwdConfirm.length() == 0) {
			errMsg = "ȷ�����벻��Ϊ��";
		} else if (!pwd.equals(pwdConfirm)) {
			errMsg = "������ȷ�����벻ƥ��";
		} else if (money == null || money.length() == 0) {
			errMsg = "����Ϊ��";
		}
		
		try {
			if (errMsg == null) {
				Float.parseFloat(money);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			errMsg = "������Ϊ����";
		}
		// ��֤���ÿ�������Ϣ
		try {
			if (errMsg == null && cardType == 2) {
				Float.parseFloat(creditLimit);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			errMsg = "���ö�ȱ���Ϊ����";
		}
		try {
			if (errMsg == null && cardType == 2) {
				int stmtDayNum = Integer.parseInt(stmtDay);
				if (stmtDayNum > 28 || stmtDayNum < 1) {
					errMsg = "�˵�����Ϊ1~28������";
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			errMsg = "�˵�����Ϊ1~28������";
		}
		
		try {
			if (errMsg == null && cardType == 2) {
				int deadlineNum = Integer.parseInt(deadLine);
				if (deadlineNum > 28 || deadlineNum < 1) {
					errMsg = "��󻹿��ձ���Ϊ1~28������";
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			errMsg = "��󻹿��ձ���Ϊ1~28������";
		}
		
		// �ó���֤���
		if (errMsg != null) {
			errLabel.setText(errMsg);
		} else {
            isLegal = true;
		}
		
		return isLegal;
	}
	
}
