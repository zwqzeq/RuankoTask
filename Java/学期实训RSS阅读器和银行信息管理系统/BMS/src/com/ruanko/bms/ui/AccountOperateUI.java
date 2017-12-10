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
 * �������������
 */
@SuppressWarnings("serial")
public class AccountOperateUI extends JDialog {
	// ��������ѡ��ť
	private JRadioButton depositBtn = null;//��ť
	private JRadioButton withdrawBtn = null;//ȡ�ť
	private JRadioButton cashBtn = null;//���Ѱ�ť
	private ButtonGroup typeBtnGroup = null;//��������ѡ��ť��
	
	// ����
	private JLabel noLabel = null;//���ű�ǩ
	private JTextField noField = null;//�����ı���
	
	// ����
	private JLabel pwdLabel = null;//�����ǩ
	private JPasswordField pwdField = null;//�����ı���
	
	// ���
	private JLabel moneyLabel = null;//����ǩ
	private JTextField moneyField = null;//����ı���
	
	// �����ؼ�
	private JButton okBtn = null;//ȷ�ϰ�ť
	private JButton cancelBtn = null;//ȡ����ť
	private JLabel errLabel = null;//������Ϣ��ť
	
	// ���峣��
	private static int WIN_WIDTH = 400;//������
	private static int WIN_HEIGHT = 300;//����߶�
	
	private Dimension labelSize = null;
	private Dimension textFieldSize = null;
	
	/**
	 * ���췽��
	 * @param owner
	 */
	public AccountOperateUI(Frame owner) {
		super(owner);
		
		// ���ÿؼ�Ĭ�ϴ�С
		labelSize = new Dimension(70, 30);
		textFieldSize = new Dimension(500, 30);
		
		// ���ô�����Ϣ
		setModal(true);
		setTitle("�������");
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
		JPanel operatePanel = new JPanel();
		
		//���ò���Ϊ��������
		operatePanel.setLayout(new FlowLayout());
		
		//���������뵽�������
		operatePanel.add(getOperateTypePanel());
		operatePanel.add(getNoPanel());
		operatePanel.add(getPwdPanel());
		operatePanel.add(getMoneyPanel());
		operatePanel.add(getBtnPanel());
		operatePanel.add(getErrPanel());
		
		// ���������
		return operatePanel;
	}
	
	private JPanel getOperateTypePanel() {
		// ��ʼ������еĿؼ�
		depositBtn = new JRadioButton("���");		
		withdrawBtn = new JRadioButton("ȡ��");
		cashBtn = new JRadioButton("����");
		typeBtnGroup = new ButtonGroup();
		
		depositBtn.setSelected(true);
		typeBtnGroup.add(depositBtn);
		typeBtnGroup.add(withdrawBtn);
		typeBtnGroup.add(cashBtn);
		
		// ע�������
		depositBtn.addActionListener(new RadioButtonListener());
		withdrawBtn.addActionListener(new RadioButtonListener());
		cashBtn.addActionListener(new RadioButtonListener());
		
		//Ϊ��ť��Ӷ���ָ��
		depositBtn.setActionCommand("CARD_DEPOSIT");	
		withdrawBtn.setActionCommand("CARD_WITHDRAW");
		cashBtn.setActionCommand("CARD_CASH");
		
		// �趨�ؼ���С
		depositBtn.setPreferredSize(new Dimension(80, 30));
		withdrawBtn.setPreferredSize(new Dimension(80, 30));
		cashBtn.setPreferredSize(new Dimension(80, 30));
		
		// ��ʼ����岢���ؼ����뵽�����
		JPanel cardTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		cardTypePanel.add(depositBtn);
		cardTypePanel.add(withdrawBtn);
		cardTypePanel.add(cashBtn);
		
		// �������
		return cardTypePanel;
	}
	
	private JPanel getNoPanel() {
		// ��ʼ������еĿؼ�
		noLabel = new JLabel("����");
		noField = new JTextField(15);
		
		// �趨�ؼ���С
		noLabel.setPreferredSize(labelSize);
		noField.setPreferredSize(textFieldSize);
		
		// ��ʼ����岢���ؼ����뵽�����
		JPanel noPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		noPanel.add(noLabel);
		noPanel.add(noField);
		
		// �������
		return noPanel;
	}
	
	private JPanel getPwdPanel() {
		// ��ʼ������еĿؼ�
		pwdLabel = new JLabel("����");
		pwdField = new JPasswordField(15);
		
		// �趨�ؼ���С
		pwdLabel.setPreferredSize(labelSize);
		pwdField.setPreferredSize(textFieldSize);
		
		// ��ʼ����岢���ؼ����뵽�����
		JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pwdPanel.add(pwdLabel);
		pwdPanel.add(pwdField);
		pwdField.setEnabled(false);
		
		// �������
		return pwdPanel;
	}
	
	private JPanel getMoneyPanel() {
		// ��ʼ������еĿؼ�
		moneyLabel = new JLabel("���");
		moneyField = new JTextField(15);
		
		// �趨�ؼ���С
		moneyLabel.setPreferredSize(labelSize);
		moneyField.setPreferredSize(textFieldSize);
		
		// ��ʼ����岢���ؼ����뵽�����
		JPanel moneyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		moneyPanel.add(moneyLabel);
		moneyPanel.add(moneyField);
		
		// �������
		return moneyPanel;
	}
	
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
		// �趨����С
		errPanel.setPreferredSize(new Dimension(WIN_WIDTH, 30));	
		// ���ؼ����뵽�����
		errPanel.add(errLabel);
		// �������
		return errPanel;
	}
	
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
					
					String cardNo = noField.getText().trim();
					String pwd = new String(pwdField.getPassword());
					Float amount = Float.parseFloat(moneyField.getText());
					
					Card card = null;
					
					// ��ȡ��������
					int cardType = Integer.parseInt(cardNo.substring(0, 1));
					
					// ���ݲ�ͬ���ʹ�����ͬ�Ķ��󣬸�������ָ���������
					switch (cardType) {
					case Card.SAVING_CARD:
						card = new SavingCard();
						break;
					case Card.CREDIT_CARD:
						card = new CreditCard();
						break;
					default:
						errLabel.setText("�ÿ����Ͳ�����");
						return;
					}
					
					// ������
					if (depositBtn.isSelected()) {
						if (card != null && card.loadByNo(cardNo)) {
							card.deposit(amount);
						} else {
							errLabel.setText("���뿨��Ϣʧ�ܣ����������뿨��");
							return;
						}
					}
					
					// ȡ�����
					else if (withdrawBtn.isSelected()) {
						if (!(card != null && card.loadByNoAndPwd(cardNo, pwd))) {
							errLabel.setText("���뿨��Ϣʧ�ܣ����������뿨�ź�����");
							return;
						}
						
						if (!card.withdraw(amount)) {
							errLabel.setText("���㣬������������");
							return;
						}
					}
					
					// ����
					else if (cashBtn.isSelected()) {
						if (!(card != null && card.loadByNoAndPwd(cardNo, pwd))) {
							errLabel.setText("���뿨��Ϣʧ�ܣ����������뿨�ź�����");
							return;
						}
						
						if (!card.cash(amount)) {
							errLabel.setText("���㣬������������");
							return;
						}
					}
					
					JOptionPane.showMessageDialog(AccountOperateUI.this, "�����ɹ���");
					AccountOperateUI.this.dispose();
				}
			});
		}
		return okBtn;
	}
	
	private JButton getCancelBtn() {
		if (cancelBtn == null) {
			// ��ʼ��ȡ����ť
			cancelBtn = new JButton("ȡ��");
			// �趨��ť��С
			cancelBtn.setPreferredSize(new Dimension(80, 30));
			// ���������ڲ��࣬��Ӱ�ť������
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
	 * ��������ѡ��ť������
	 */
	class RadioButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// ��ȡActionCommand��Ϣ
			String command = e.getActionCommand();
			if ("CARD_DEPOSIT".equals(command)) {
				// ���Ϊ��������������Ϣ�ı���
				pwdField.setEnabled(false);
			} else {
				// ���Ϊȡ���������ʹ��������Ϣ�ı���
				pwdField.setEnabled(true);
			}
		}
	}
	
	private boolean dataValidate() {
		// ��ʼ������
		boolean isLegal = false;
		String errMsg = null;
		// ��ȡ��������
		int operateType = depositBtn.isSelected() ? 1:2;
		String no = noField.getText();
		String pwd = new String(pwdField.getPassword()).trim();
		String money = moneyField.getText();
		// ��մ�����Ϣ��ǩ����
		errLabel.setText("");
		// ������֤
		if (no == null || no.length() == 0) {
			errMsg = "���Ų���Ϊ��";
		} else if (money == null || money.length() == 0) {
			errMsg = "����Ϊ��";
		}
		
		if (errMsg == null && operateType == 2) {
			if (pwd == null || pwd.length() == 0) {
				errMsg = "���벻��Ϊ��";
			}
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
