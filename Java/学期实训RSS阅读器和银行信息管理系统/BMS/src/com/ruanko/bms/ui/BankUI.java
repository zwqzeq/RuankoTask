package com.ruanko.bms.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class BankUI extends JFrame {
	// ��ӭ��ǩ�ؼ�
	private JLabel welcomeLabel = null;
	// ���ܰ�ť���ؼ�
	private JButton addCustomerBtn = null;//�û���ť
	private JButton addCardBtn = null;//������ť
	private JButton accOperateBtn = null;//���������ť
	private JButton accQueryBtn = null;//�����ѯ��ť
	// ���峣��
	private final static int WIN_WIDTH = 500;
	private final static int WIN_HEIGHT = 350;
	
	public BankUI() {
		// ���ڱ���
		setTitle("���й���ϵͳ");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIN_WIDTH, WIN_HEIGHT);
		setResizable(false);
		// ���ھ���
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension scrSize = tk.getScreenSize();
		setLocation((scrSize.width - WIN_WIDTH)/2, (scrSize.height - WIN_HEIGHT)/2);
		
		// ���������ӵ�������
		setContentPane(getContentPanel());
		// ��Ӳ˵���
		setJMenuBar(getJMenuBar());
		// ��ʾ����
		setVisible(true);
	}
	
	private JPanel getWelcomePanel() {
		// ��ʼ����ӭ��ǩ
		welcomeLabel = new JLabel("��ӭ�������й���ϵͳ");
		JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		welcomePanel.setPreferredSize(new Dimension(WIN_WIDTH, 100));
		welcomePanel.add(welcomeLabel);
		
		return welcomePanel;
	}
	
	private JPanel getOperationPanel() {
		// ��ʼ������еĿؼ�
		addCustomerBtn = new JButton("����");
		addCardBtn = new JButton("���п�����");
		accOperateBtn = new JButton("�������");
		accQueryBtn = new JButton("�����ѯ");
		
		// ��ʼ����壬�趨����
		JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// ��������С
		optionPanel.setPreferredSize(new Dimension(450, 250));
		
		// ��ӿ���������
		addCustomerBtn.addActionListener(new AddCustomerListener());	
		// ��Ӱ������п�������
		addCardBtn.addActionListener(new AddCardListener());
		// ��Ӳ������������
		accOperateBtn.addActionListener(new AccountOperateListener());
		// ��������ѯ������
		accQueryBtn.addActionListener(new RecordListener());
		
		// ���ؼ����뵽�����
		optionPanel.add(addCustomerBtn);
		optionPanel.add(addCardBtn);
		optionPanel.add(accOperateBtn);
		optionPanel.add(accQueryBtn);
		
		return optionPanel;
	}
	
	// ���������
	private JPanel getContentPanel() {
		JPanel bankPanel = new JPanel();
		bankPanel.setLayout(new FlowLayout());
		// ����ӭ��Ϣ�������ӵ��������
		bankPanel.add(getWelcomePanel());
		// �����������ӵ��������
		bankPanel.add(getOperationPanel());
		
		return bankPanel;
	}
	
	/**
	 * �����˵���
	 */
	public JMenuBar getJMenuBar() {
		// �����˵���
		JMenuBar menuBar = new JMenuBar();
		
		// �����˵�
		JMenu sysMenu = new JMenu("ϵͳ(S)");
		JMenu bizMenu = new JMenu("ҵ��(B)");
		menuBar.add(sysMenu);
		menuBar.add(bizMenu);
		
		JMenuItem customerItem = new JMenuItem("����");
		JMenuItem cardItem = new JMenuItem("���п�����");
		JMenuItem operateItem = new JMenuItem("�������");
		JMenuItem queryItem = new JMenuItem("�����ѯ");
		JMenuItem exitItem = new JMenuItem("�˳�ϵͳ");
		
		bizMenu.add(customerItem);
		bizMenu.addSeparator();
		bizMenu.add(cardItem);
		bizMenu.addSeparator();;
		bizMenu.add(operateItem);
		bizMenu.add(queryItem);
		sysMenu.add(exitItem);
		
		// ��ӿ���������
		customerItem.addActionListener(new AddCustomerListener());
		// ��Ӱ������п�������
		cardItem.addActionListener(new AddCardListener());
		// ��Ӳ������������
		operateItem.addActionListener(new AccountOperateListener());
		// ��������ѯ������
		queryItem.addActionListener(new RecordListener());
		// ����˳�ϵͳ������
		exitItem.addActionListener(new ExitListener());
		
		// ���ÿ�ݼ�
		sysMenu.setMnemonic(KeyEvent.VK_S);
		bizMenu.setMnemonic(KeyEvent.VK_B);
		
		customerItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		cardItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		operateItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		queryItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		
		return menuBar;
	}
	
	// �������¼��������������ڲ���ʵ��
	class AddCustomerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new AddCustomerUI(BankUI.this);
		}
	}
	
	// ���п�������¼��������������ڲ���ʵ��
	class AddCardListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new AddCardUI(BankUI.this);
		}
	}
	
	// ����������¼��������������ڲ���ʵ��
	class AccountOperateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new AccountOperateUI(BankUI.this);
		}
	}
	
	// �����ѯ���¼��������������ڲ���ʵ��
	class RecordListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new RecordUI(BankUI.this);
		}
	}
	
	// �˳�ϵͳ���¼��������������ڲ���ʵ��
	class ExitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			BankUI.this.dispose();
		}
	}

}
