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
	//用户输入信息控件
	//1.姓名
	private JLabel nameLabel = null;//姓名标签
	private JTextField nameField = null;//姓名文本域
	//2.性别
	private JLabel sexLabel = null;//性别标签
	private JRadioButton maleBtn = null;//男性单选按钮
	private JRadioButton femaleBtn = null;//女性单选按钮
	private ButtonGroup sexBtnGroup = null;//性别按钮组
	//3.生日
	private JLabel birthdayLable = null;//出生日期标签
	private JTextField birthdayField = null;//出生日期文本域
	private Date birthdayDate = null;//出生日期对象
	//4.身份证号
	private JLabel idLabel = null;//身份证号标签
	private JTextField idField = null;//身份证号文本域
	//5.地址
	private JLabel addrLabel = null;//地址标签
	private JTextField addrField = null;//地址文本域
	//6.手机号
	private JLabel telLabel = null;//手机号标签
	private JTextField telField = null;//手机号文本域
	//7.邮箱
	private JLabel emailLabel = null;//邮箱标签
	private JTextField emailField = null;//邮箱文本域
	//按钮控件
	private JButton okBtn = null;//确定按钮
	private JButton cancelBtn = null;//取消按钮
	//其他控件
	private JLabel addCustomerLabel = null;//开户标题
	private JLabel errLable = null;//错误信息标签
	//窗体常量
	private final static int WIN_WIDTH = 400;//窗体宽度
	private final static int WIN_HEIGHT = 500;//窗体高度
	
	private Dimension labelSize = null;//标签大小
	private Dimension textFieldSize = null;//文本框大小
	
	public AddCustomerUI(Frame owner) {
		super(owner);
		//设置控件默认大小
		labelSize = new Dimension(100, 30);
		textFieldSize = new Dimension(120,30);
		
		//设置窗口信息
		setModal(true);
		setTitle("开户");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIN_WIDTH, WIN_HEIGHT);
		
		//窗体居中
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension scrSize = tk.getScreenSize();
		setLocation((scrSize.width - WIN_WIDTH)/2, (scrSize.height - WIN_HEIGHT)/2);
		
		//设置面板内容
		setContentPane(getContentPanel());
		
		//显示窗体
		setVisible(true);
	}
	
	private JPanel getTitlePanel() {
		//初始化面板中的组件
		addCustomerLabel = new JLabel("用户信息");
		//初始化面板
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//设定面板大小
		titlePanel.setPreferredSize(new Dimension(WIN_WIDTH, 30));
		//将组件加入到面板上
		titlePanel.add(addCustomerLabel);
		
		return titlePanel;
	}
	
	private JPanel getContentPanel() {
		//初始化主面板
		JPanel contentPanel = new JPanel();
		//设置布局为浮动布局
		contentPanel.setLayout(new FlowLayout());
		//将子面板加入到主面板中
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
		//返回主面板
		return contentPanel;
	}
	
	private JPanel getNamePanel() {
		// 初始化面板中的组件
		nameLabel = new JLabel("姓名");
		nameField = new JTextField(15);
		// 设定组件大小
		nameLabel.setPreferredSize(labelSize);
		nameField.setPreferredSize(textFieldSize);
		// 初始化面板并将组件添加到面板中
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		
		return namePanel;
	}
	
	private JPanel getSexPanel() {
		// 初始化面板中的控件
		sexLabel = new JLabel("性别");
		maleBtn = new JRadioButton("男");
		femaleBtn = new JRadioButton("女");
		
		// 将单选按钮添加到按钮组
		sexBtnGroup = new ButtonGroup();
		sexBtnGroup.add(maleBtn);
		sexBtnGroup.add(femaleBtn);
		
		maleBtn.setSelected(true);
		
		// 设定控件的大小
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
		// 初始化面板中的控件
		birthdayLable = new JLabel("出生日期");
		birthdayField = new JTextField(15);
		birthdayField.setText("示例  1990-01-01");
		// 设定控件的大小
		birthdayLable.setPreferredSize(labelSize);
		birthdayField.setPreferredSize(textFieldSize);
		// 初始化面板并将控件加入到面板中
		JPanel birthdayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		birthdayPanel.add(birthdayLable);
		birthdayPanel.add(birthdayField);
		
		return birthdayPanel;
	}
	
	private JPanel getIdPanel() {
		// 初始化面板中的控件
		idLabel = new JLabel("身份证号");
		idField = new JTextField(15);
		idField.setText("必须为18位身份证号");
		// 设定控件的大小
		idLabel.setPreferredSize(labelSize);
		idField.setPreferredSize(textFieldSize);
		// 初始化面板并将控件加入到面板中
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		idPanel.add(idLabel);
		idPanel.add(idField);
		
		return idPanel;
	}
	
	private JPanel getAddrPanel() {
		// 初始化面板中的控件
		addrLabel = new JLabel("家庭住址");
		addrField = new JTextField(15);
		// 设定控件的大小
		addrLabel.setPreferredSize(labelSize);
		addrField.setPreferredSize(textFieldSize);
		// 初始化面板并将控件加入到面板中
		JPanel addrPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		addrPanel.add(addrLabel);
		addrPanel.add(addrField);
		
		return addrPanel;
	}
	
	private JPanel getTelPanel() {
		// 初始化面板中的控件
		telLabel = new JLabel("手机号");
		telField = new JTextField(15);
		// 设定控件的大小
		telLabel.setPreferredSize(labelSize);
		telField.setPreferredSize(textFieldSize);
		// 初始化面板并将控件加入到面板中
		JPanel telPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		telPanel.add(telLabel);
		telPanel.add(telField);
		
		return telPanel;
	}
	
	private JPanel getEmailPanel() {
		// 初始化面板中的控件
		emailLabel = new JLabel("邮箱");
		emailField = new JTextField(15);
		// 设定控件的大小
		emailLabel.setPreferredSize(labelSize);
		emailField.setPreferredSize(textFieldSize);
		// 初始化面板并将控件加入到面板中
		JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		emailPanel.add(emailLabel);
		emailPanel.add(emailField);
		
		return emailPanel;
	}
	
	private JButton getOkBtn() {
		if (okBtn == null) {
			// 初始化确定按钮
			okBtn = new JButton("确定");
			// 设定按钮大小
			okBtn.setPreferredSize(new Dimension(80, 30));
			// 利用匿名内部类，添加按钮监听器
			okBtn.addActionListener(new ActionListener() {
				/**
				 * 点击事件响应方法
				 */
				@Override
				public void actionPerformed(ActionEvent e) {
					// 如果校验不通过则返回
					if (!dataValidate()) {
						return;
					}
					
					// 创建customer对象，使用带参数的构造方法
				    Customer customer = new Customer(nameField.getText(), maleBtn.isSelected()?"男":"女", birthdayDate, idField.getText(), telField.getText(), emailField.getText(), addrField.getText());
					
					if (customer.create()) {
						// 开户成功
						JOptionPane.showMessageDialog(AddCustomerUI.this, "开户成功！");
						AddCustomerUI.this.dispose();
					} else {
						// 开户失败
						JOptionPane.showMessageDialog(AddCustomerUI.this, "开户失败，该身份证号用户已存在！");
					}
					
				}
			});
		}
		
		return okBtn;
	}
	
	private JButton getCancelBtn() {
		if (cancelBtn == null) {
			// 初始化取消按钮
			cancelBtn = new JButton("取消");
			//设定按钮大小
			cancelBtn.setPreferredSize(new Dimension(80, 30));
			//利用匿名内部类，添加按钮监听器
			cancelBtn.addActionListener(new ActionListener() {
				/**
				 * 点击事件响应方法
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
		
		// 初始化面板并将控件加入到面板中
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPanel.add(getOkBtn());
		btnPanel.add(getCancelBtn());
		
		return btnPanel;
	}
	
	private JPanel getErrPanel() {
		// 初始化面板中的控件
		errLable = new JLabel();
		errLable.setForeground(Color.RED);
		// 初始化面板并将控件加入到面板中
		JPanel errPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		errPanel.setPreferredSize(new Dimension(WIN_WIDTH, 30));
		errPanel.add(errLable);
		
		return errPanel;
	}
	
	/**
	 * 输入数据的校验
	 * @return 是否校验通过
	 */
	private boolean dataValidate() {
		// 初始化对象
		boolean islegal = false;
		String errMag = null;
		
		//创建日期格式转换对象
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// 获取界面输入
		String name = nameField.getText();
		String id = idField.getText();
		String birthday = birthdayField.getText();
		String addr = addrField.getText();
		String tel = telField.getText();
		String email = emailField.getText();	
		// 清空错误信息标签内容
		errLable.setText("");
		// 数据验证
		if (name == null || name.length() == 0) {
			errMag = "姓名不能为空";
		} else if (id == null || id.length() == 0) {
			errMag = "身份证不能为空";
		} else if (addr == null || addr.length() == 0) {
			errMag = "地址不能为空";
		} else if (tel == null || tel.length() == 0) {
			errMag = "联系电话不能为空";
		} else if (birthday == null || birthday.length() == 0) {
			errMag = "出生日期不能为空";
		} else if (email == null || email.length() == 0) {
			errMag = "邮箱不能为空";
		}
		
		try {
			if (errMag == null) {
				birthdayDate = sdf.parse(birthday);
			}
		} catch (ParseException e) {
			errMag = "出生日期格式不正确";
		}
		
		// 得出验证结果
		if (errMag != null) {
			errLable.setText(errMag);
		} else {
            islegal = true;
		}
		return islegal;
	}
	
}
