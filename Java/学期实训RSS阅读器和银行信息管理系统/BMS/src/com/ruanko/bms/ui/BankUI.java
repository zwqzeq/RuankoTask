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
	// 欢迎标签控件
	private JLabel welcomeLabel = null;
	// 功能按钮区控件
	private JButton addCustomerBtn = null;//用户按钮
	private JButton addCardBtn = null;//卡办理按钮
	private JButton accOperateBtn = null;//账务操作按钮
	private JButton accQueryBtn = null;//账务查询按钮
	// 窗体常量
	private final static int WIN_WIDTH = 500;
	private final static int WIN_HEIGHT = 350;
	
	public BankUI() {
		// 窗口标题
		setTitle("银行管理系统");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIN_WIDTH, WIN_HEIGHT);
		setResizable(false);
		// 窗口居中
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension scrSize = tk.getScreenSize();
		setLocation((scrSize.width - WIN_WIDTH)/2, (scrSize.height - WIN_HEIGHT)/2);
		
		// 将主面板添加到窗口上
		setContentPane(getContentPanel());
		// 添加菜单栏
		setJMenuBar(getJMenuBar());
		// 显示窗口
		setVisible(true);
	}
	
	private JPanel getWelcomePanel() {
		// 初始化欢迎标签
		welcomeLabel = new JLabel("欢迎进入银行管理系统");
		JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		welcomePanel.setPreferredSize(new Dimension(WIN_WIDTH, 100));
		welcomePanel.add(welcomeLabel);
		
		return welcomePanel;
	}
	
	private JPanel getOperationPanel() {
		// 初始化面板中的控件
		addCustomerBtn = new JButton("开户");
		addCardBtn = new JButton("银行卡办理");
		accOperateBtn = new JButton("账务操作");
		accQueryBtn = new JButton("账务查询");
		
		// 初始化面板，设定布局
		JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// 设置面板大小
		optionPanel.setPreferredSize(new Dimension(450, 250));
		
		// 添加开户监听器
		addCustomerBtn.addActionListener(new AddCustomerListener());	
		// 添加办理银行卡监听器
		addCardBtn.addActionListener(new AddCardListener());
		// 添加财务操作监听器
		accOperateBtn.addActionListener(new AccountOperateListener());
		// 添加账务查询监听器
		accQueryBtn.addActionListener(new RecordListener());
		
		// 将控件加入到面板中
		optionPanel.add(addCustomerBtn);
		optionPanel.add(addCardBtn);
		optionPanel.add(accOperateBtn);
		optionPanel.add(accQueryBtn);
		
		return optionPanel;
	}
	
	// 创建主面板
	private JPanel getContentPanel() {
		JPanel bankPanel = new JPanel();
		bankPanel.setLayout(new FlowLayout());
		// 将欢迎信息的面板添加到主面板上
		bankPanel.add(getWelcomePanel());
		// 将功能面板添加到主面板上
		bankPanel.add(getOperationPanel());
		
		return bankPanel;
	}
	
	/**
	 * 创建菜单栏
	 */
	public JMenuBar getJMenuBar() {
		// 创建菜单栏
		JMenuBar menuBar = new JMenuBar();
		
		// 创建菜单
		JMenu sysMenu = new JMenu("系统(S)");
		JMenu bizMenu = new JMenu("业务(B)");
		menuBar.add(sysMenu);
		menuBar.add(bizMenu);
		
		JMenuItem customerItem = new JMenuItem("开户");
		JMenuItem cardItem = new JMenuItem("银行卡办理");
		JMenuItem operateItem = new JMenuItem("账务操作");
		JMenuItem queryItem = new JMenuItem("账务查询");
		JMenuItem exitItem = new JMenuItem("退出系统");
		
		bizMenu.add(customerItem);
		bizMenu.addSeparator();
		bizMenu.add(cardItem);
		bizMenu.addSeparator();;
		bizMenu.add(operateItem);
		bizMenu.add(queryItem);
		sysMenu.add(exitItem);
		
		// 添加开户监听器
		customerItem.addActionListener(new AddCustomerListener());
		// 添加办理银行卡监听器
		cardItem.addActionListener(new AddCardListener());
		// 添加财务操作监听器
		operateItem.addActionListener(new AccountOperateListener());
		// 添加账务查询监听器
		queryItem.addActionListener(new RecordListener());
		// 添加退出系统监听器
		exitItem.addActionListener(new ExitListener());
		
		// 设置快捷键
		sysMenu.setMnemonic(KeyEvent.VK_S);
		bizMenu.setMnemonic(KeyEvent.VK_B);
		
		customerItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		cardItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		operateItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		queryItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		
		return menuBar;
	}
	
	// 开户的事件监听器，采用内部类实现
	class AddCustomerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new AddCustomerUI(BankUI.this);
		}
	}
	
	// 银行卡办理的事件监听器，采用内部类实现
	class AddCardListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new AddCardUI(BankUI.this);
		}
	}
	
	// 财务操作的事件监听器，采用内部类实现
	class AccountOperateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new AccountOperateUI(BankUI.this);
		}
	}
	
	// 财务查询的事件监听器，采用内部类实现
	class RecordListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new RecordUI(BankUI.this);
		}
	}
	
	// 退出系统的事件监听器，采用内部类实现
	class ExitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			BankUI.this.dispose();
		}
	}

}
