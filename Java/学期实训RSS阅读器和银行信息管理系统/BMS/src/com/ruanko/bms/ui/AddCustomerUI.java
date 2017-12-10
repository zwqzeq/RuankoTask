package com.ruanko.bms.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.ruanko.bms.model.Customer;

@SuppressWarnings("serial")
public class AddCustomerUI extends JDialog {
	//�û�������Ϣ�ؼ�
	//1.����
	private JLabel nameLabel = null;//������ǩ
	private JTextField nameField = null;//�����ı���
	//2.�Ա�
	private JLabel sexLabel = null;//�Ա��ǩ
	private JRadioButton maleBtn = null;//���Ե�ѡ��ť
	private JRadioButton femaleBtn = null;//Ů�Ե�ѡ��ť
	private ButtonGroup sexBtnGroup = null;//�Ա�ť��
	//3.����
	private JLabel birthdayLable = null;//�������ڱ�ǩ
	private JTextField birthdayField = null;//���������ı���
	private Date birthdayDate = null;//�������ڶ���
	//4.���֤��
	private JLabel idLabel = null;//���֤�ű�ǩ
	private JTextField idField = null;//���֤���ı���
	//5.��ַ
	private JLabel addrLabel = null;//��ַ��ǩ
	private JTextField addrField = null;//��ַ�ı���
	//6.�ֻ���
	private JLabel telLabel = null;//�ֻ��ű�ǩ
	private JTextField telField = null;//�ֻ����ı���
	//7.����
	private JLabel emailLabel = null;//�����ǩ
	private JTextField emailField = null;//�����ı���
	//��ť�ؼ�
	private JButton okBtn = null;//ȷ����ť
	private JButton cancelBtn = null;//ȡ����ť
	//�����ؼ�
	private JLabel addCustomerLabel = null;//��������
	private JLabel errLable = null;//������Ϣ��ǩ
	//���峣��
	private final static int WIN_WIDTH = 400;//������
	private final static int WIN_HEIGHT = 500;//����߶�
	
	private Dimension labelSize = null;//��ǩ��С
	private Dimension textFieldSize = null;//�ı����С
	
	public AddCustomerUI(Frame owner) {
		super(owner);
		//���ÿؼ�Ĭ�ϴ�С
		labelSize = new Dimension(100, 30);
		textFieldSize = new Dimension(120,30);
		
		//���ô�����Ϣ
		setModal(true);
		setTitle("����");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIN_WIDTH, WIN_HEIGHT);
		
		//�������
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension scrSize = tk.getScreenSize();
		setLocation((scrSize.width - WIN_WIDTH)/2, (scrSize.height - WIN_HEIGHT)/2);
		
		//�����������
		setContentPane(getContentPanel());
		
		//��ʾ����
		setVisible(true);
	}
	
	private JPanel getTitlePanel() {
		//��ʼ������е����
		addCustomerLabel = new JLabel("�û���Ϣ");
		//��ʼ�����
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//�趨����С
		titlePanel.setPreferredSize(new Dimension(WIN_WIDTH, 30));
		//��������뵽�����
		titlePanel.add(addCustomerLabel);
		
		return titlePanel;
	}
	
	private JPanel getContentPanel() {
		//��ʼ�������
		JPanel contentPanel = new JPanel();
		//���ò���Ϊ��������
		contentPanel.setLayout(new FlowLayout());
		//���������뵽�������
		contentPanel.add(getTitlePanel());
		contentPanel.add(getNamePanel());
		contentPanel.add(getSexPanel());
		contentPanel.add(getBirthdayPanel());
		contentPanel.add(getIdPanel());
		contentPanel.add(getAddrPanel());
		contentPanel.add(getTelPanel());
		contentPanel.add(getEmailPanel());
		contentPanel.add(getBtnPanel());
		contentPanel.add(getErrPanel());
		//���������
		return contentPanel;
	}
	
	private JPanel getNamePanel() {
		// ��ʼ������е����
		nameLabel = new JLabel("����");
		nameField = new JTextField(15);
		// �趨�����С
		nameLabel.setPreferredSize(labelSize);
		nameField.setPreferredSize(textFieldSize);
		// ��ʼ����岢�������ӵ������
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		
		return namePanel;
	}
	
	private JPanel getSexPanel() {
		// ��ʼ������еĿؼ�
		sexLabel = new JLabel("�Ա�");
		maleBtn = new JRadioButton("��");
		femaleBtn = new JRadioButton("Ů");
		
		// ����ѡ��ť��ӵ���ť��
		sexBtnGroup = new ButtonGroup();
		sexBtnGroup.add(maleBtn);
		sexBtnGroup.add(femaleBtn);
		
		maleBtn.setSelected(true);
		
		// �趨�ؼ��Ĵ�С
		sexLabel.setPreferredSize(labelSize);
		maleBtn.setPreferredSize(new Dimension(80, 30));
		femaleBtn.setPreferredSize(new Dimension(80, 30));
		
		JPanel sexPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		sexPanel.add(sexLabel);
		sexPanel.add(maleBtn);
		sexPanel.add(femaleBtn);
		
		return sexPanel;
	}
	
	private JPanel getBirthdayPanel() {
		// ��ʼ������еĿؼ�
		birthdayLable = new JLabel("��������");
		birthdayField = new JTextField(15);
		birthdayField.setText("ʾ��  1990-01-01");
		// �趨�ؼ��Ĵ�С
		birthdayLable.setPreferredSize(labelSize);
		birthdayField.setPreferredSize(textFieldSize);
		// ��ʼ����岢���ؼ����뵽�����
		JPanel birthdayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		birthdayPanel.add(birthdayLable);
		birthdayPanel.add(birthdayField);
		
		return birthdayPanel;
	}
	
	private JPanel getIdPanel() {
		// ��ʼ������еĿؼ�
		idLabel = new JLabel("���֤��");
		idField = new JTextField(15);
		idField.setText("����Ϊ18λ���֤��");
		// �趨�ؼ��Ĵ�С
		idLabel.setPreferredSize(labelSize);
		idField.setPreferredSize(textFieldSize);
		// ��ʼ����岢���ؼ����뵽�����
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		idPanel.add(idLabel);
		idPanel.add(idField);
		
		return idPanel;
	}
	
	private JPanel getAddrPanel() {
		// ��ʼ������еĿؼ�
		addrLabel = new JLabel("��ͥסַ");
		addrField = new JTextField(15);
		// �趨�ؼ��Ĵ�С
		addrLabel.setPreferredSize(labelSize);
		addrField.setPreferredSize(textFieldSize);
		// ��ʼ����岢���ؼ����뵽�����
		JPanel addrPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		addrPanel.add(addrLabel);
		addrPanel.add(addrField);
		
		return addrPanel;
	}
	
	private JPanel getTelPanel() {
		// ��ʼ������еĿؼ�
		telLabel = new JLabel("�ֻ���");
		telField = new JTextField(15);
		// �趨�ؼ��Ĵ�С
		telLabel.setPreferredSize(labelSize);
		telField.setPreferredSize(textFieldSize);
		// ��ʼ����岢���ؼ����뵽�����
		JPanel telPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		telPanel.add(telLabel);
		telPanel.add(telField);
		
		return telPanel;
	}
	
	private JPanel getEmailPanel() {
		// ��ʼ������еĿؼ�
		emailLabel = new JLabel("����");
		emailField = new JTextField(15);
		// �趨�ؼ��Ĵ�С
		emailLabel.setPreferredSize(labelSize);
		emailField.setPreferredSize(textFieldSize);
		// ��ʼ����岢���ؼ����뵽�����
		JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		emailPanel.add(emailLabel);
		emailPanel.add(emailField);
		
		return emailPanel;
	}
	
	private JButton getOkBtn() {
		if (okBtn == null) {
			// ��ʼ��ȷ����ť
			okBtn = new JButton("ȷ��");
			// �趨��ť��С
			okBtn.setPreferredSize(new Dimension(80, 30));
			// ���������ڲ��࣬��Ӱ�ť������
			okBtn.addActionListener(new ActionListener() {
				/**
				 * ����¼���Ӧ����
				 */
				@Override
				public void actionPerformed(ActionEvent e) {
					// ���У�鲻ͨ���򷵻�
					if (!dataValidate()) {
						return;
					}
					
					// ����customer����ʹ�ô������Ĺ��췽��
				    Customer customer = new Customer(nameField.getText(), maleBtn.isSelected()?"��":"Ů", birthdayDate, idField.getText(), telField.getText(), emailField.getText(), addrField.getText());
					
					if (customer.create()) {
						// �����ɹ�
						JOptionPane.showMessageDialog(AddCustomerUI.this, "�����ɹ���");
						AddCustomerUI.this.dispose();
					} else {
						// ����ʧ��
						JOptionPane.showMessageDialog(AddCustomerUI.this, "����ʧ�ܣ������֤���û��Ѵ��ڣ�");
					}
					
				}
			});
		}
		
		return okBtn;
	}
	
	private JButton getCancelBtn() {
		if (cancelBtn == null) {
			// ��ʼ��ȡ����ť
			cancelBtn = new JButton("ȡ��");
			//�趨��ť��С
			cancelBtn.setPreferredSize(new Dimension(80, 30));
			//���������ڲ��࣬��Ӱ�ť������
			cancelBtn.addActionListener(new ActionListener() {
				/**
				 * ����¼���Ӧ����
				 */
				@Override
				public void actionPerformed(ActionEvent e) {
					AddCustomerUI.this.dispose();		
				}
			});
		}
		
		return cancelBtn;
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
		errLable = new JLabel();
		errLable.setForeground(Color.RED);
		// ��ʼ����岢���ؼ����뵽�����
		JPanel errPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		errPanel.setPreferredSize(new Dimension(WIN_WIDTH, 30));
		errPanel.add(errLable);
		
		return errPanel;
	}
	
	/**
	 * �������ݵ�У��
	 * @return �Ƿ�У��ͨ��
	 */
	private boolean dataValidate() {
		// ��ʼ������
		boolean islegal = false;
		String errMag = null;
		
		//�������ڸ�ʽת������
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// ��ȡ��������
		String name = nameField.getText();
		String id = idField.getText();
		String birthday = birthdayField.getText();
		String addr = addrField.getText();
		String tel = telField.getText();
		String email = emailField.getText();	
		// ��մ�����Ϣ��ǩ����
		errLable.setText("");
		// ������֤
		if (name == null || name.length() == 0) {
			errMag = "��������Ϊ��";
		} else if (id == null || id.length() == 0) {
			errMag = "���֤����Ϊ��";
		} else if (addr == null || addr.length() == 0) {
			errMag = "��ַ����Ϊ��";
		} else if (tel == null || tel.length() == 0) {
			errMag = "��ϵ�绰����Ϊ��";
		} else if (birthday == null || birthday.length() == 0) {
			errMag = "�������ڲ���Ϊ��";
		} else if (email == null || email.length() == 0) {
			errMag = "���䲻��Ϊ��";
		}
		
		try {
			if (errMag == null) {
				birthdayDate = sdf.parse(birthday);
			}
		} catch (ParseException e) {
			errMag = "�������ڸ�ʽ����ȷ";
		}
		
		// �ó���֤���
		if (errMag != null) {
			errLable.setText(errMag);
		} else {
            islegal = true;
		}
		return islegal;
	}
	
}
