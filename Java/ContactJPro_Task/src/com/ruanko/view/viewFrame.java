package com.ruanko.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.ruanko.model.Contact;

/**
 * 弹出式窗口
 * @author asus
 *
 */
@SuppressWarnings("serial")
public class viewFrame extends JFrame {

	//属性
	private JTextField numberTextField=null;                          //编号文本域
	private JTextField nameTextField=null;                            //姓名文本域
	private JTextField phonenumberTextField=null;                     //手机号文本域
	private JTextField emailTextField=null;                           //邮件文本域
	private JTextField addressTextField=null;                         //地址文本域
	private JTextField relationshipTextField=null;                    //地址文本域
	private JRadioButton maleButton=null,femaleButton=null;           //性别(男,女)标签 	

	/**
	 * 无参构造方法
	 */
	public viewFrame(){
		setTitle("联系人详细信息");                                      //设置窗口标题
		setSize(300,250);                                            //设置窗口尺寸
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();  //获取屏幕尺寸
		int x=(size.width-300)/2;
		int y=(size.height-250)/2;
		setLocation(x, y);                                          //使窗口居中
		//setVisible(true);                                         //设置窗口可见
		//setDefaultCloseOperation(EXIT_ON_CLOSE);                  //设置窗口关闭操作
		this.setContentPane(getContentpane());                      //将主面板添加到窗口

	}

	//创建主面板
	public JPanel getContentpane(){
		JPanel mainPanel=new JPanel();                              //创建主面板对象
		mainPanel.setLayout(new GridLayout(7,1));                   //主面板设为网格布局7行1列
		mainPanel.add(getNumberPanel());                            //将numberPanel面板添加到主面板
		mainPanel.add(getNamePanel());                              //将namePanel面板添加到主面板
		mainPanel.add(getGenderPanel());                            //将genderPanel面板添加到主面板
		mainPanel.add(getRelationshipPanel());                      //将relationshipPanel面板添加到主面板
		mainPanel.add(getEmailPanel());                             //将emailPanel面板添加到主面板
		mainPanel.add(getPhonenumberPanel());                  	    //将phonenumberPanel面板添加到主面板
		mainPanel.add(getAddressPanel());                           //将addressPanel面板添加到主面板
		return mainPanel;

	}

	//创建numberPanel面板
	public JPanel getNumberPanel(){
		JPanel numberPanel=new JPanel();                            //创建JPanel类对象numberPanel
		numberPanel.setLayout(new FlowLayout(FlowLayout.LEFT));     //numberPanel面板布局为流式布局且左对齐
		numberPanel.add(new JLabel("编号："));                        //创建标签并添加到面板
		numberTextField=new JTextField(12);                         //文本框宽度12列
		numberPanel.add(numberTextField);                           //文本框添加到面板numberPanel
		return numberPanel;                                         //返回numberPanel面板

	}

	//创建namePanel面板
	public JPanel getNamePanel(){                                  
		JPanel namePanel=new JPanel();                               //创建JPanel类对象namePanel
		namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));        //namePanel面板布局为流式布局且左对齐
		namePanel.add(new JLabel("姓名："));                          //创建标签并添加到面板
		nameTextField=new JTextField(12);                           //文本框宽度12列
		namePanel.add(nameTextField);                               //文本框添加到面板namePanel

		return namePanel;

	}

	//创建genderPanel面板
	public JPanel getGenderPanel(){ 
		JPanel genderPanel=new JPanel();                            //创建JPanel类对象genderPanel
		genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));     //genderPanel面板布局为流式布局且左对齐
		genderPanel.add(new JLabel("性别："));
		maleButton=new JRadioButton("男");
		femaleButton=new JRadioButton("女");
		ButtonGroup btg=new ButtonGroup();
		btg.add(maleButton);
		btg.add(femaleButton);
		genderPanel.add(maleButton);
		genderPanel.add(femaleButton);
		return genderPanel;

	}

	//创建relationshipPanel面板
	public JPanel getRelationshipPanel(){
		JPanel relationshipPanel=new JPanel();                      //创建JPanel类对象relationshipPanel
		relationshipPanel.setLayout(new FlowLayout(FlowLayout.LEFT));//relationshipPanel面板布局为流式布局且左对齐
		relationshipPanel.add(new JLabel("关系："));                  //创建标签并添加到面板
		relationshipTextField=new JTextField(12);                   //文本框宽度12列
		relationshipPanel.add(relationshipTextField);               //文本框添加到面板relationshipPanel
		return relationshipPanel;

	}

	//创建emailPanel面板
	public JPanel getEmailPanel(){
		JPanel emailPanel=new JPanel();                            //创建JPanel类对象emailPanel
		emailPanel.setLayout(new FlowLayout(FlowLayout.LEFT));     //emailPanel面板布局为流式布局且左对齐
		emailPanel.add(new JLabel("email："));                      //创建标签并添加到面板
		emailTextField=new JTextField(12);                         //文本框宽度12列
		emailPanel.add(emailTextField);                            //文本框添加到面板emailPanel
		return emailPanel;

	}

	//创建phonenumberPanel面板
	public JPanel getPhonenumberPanel(){
		JPanel phonenumberPanel=new JPanel();                     //创建JPanel类对象phonenumberPanel
		phonenumberPanel.setLayout(new FlowLayout(FlowLayout.LEFT));//phonenumberPanel面板布局为流式布局且左对齐
		phonenumberPanel.add(new JLabel("手机号："));               //创建标签并添加到面板
		phonenumberTextField=new JTextField(12);                 //文本框宽度12列
		phonenumberPanel.add(phonenumberTextField);              //文本框添加到面板phonenumberPanel
		return phonenumberPanel;
	}

	//创建addressPanel面板
	public JPanel getAddressPanel(){
		JPanel addressPanel=new JPanel();                       //创建JPanel类对象addressPanel
		addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT));//addressPanel面板布局为流式布局且左对齐
		addressPanel.add(new JLabel("地址："));                   //创建标签并添加到面板
		addressTextField=new JTextField(20);                    //文本框宽度20列
		addressPanel.add(addressTextField);                     //文本框添加到面板addressPanel
		return addressPanel;

	}

	//将联系人信息显示到弹出窗口
	public void setContact(Contact contact){
		numberTextField.setText(contact.getNumber());           //联系人编号
		nameTextField.setText(contact.getName());               //联系人姓名
		phonenumberTextField.setText(contact.getPhonenumber()); //联系人手机号
		emailTextField.setText(contact.getEmail());             //联系人email
		addressTextField.setText(contact.getAddress());         //联系人地址
		String gender=contact.getGender();                      //将联系人性别显示到单选按钮
		if(gender.equals("男")){
			maleButton.setSelected(true);
		} 
		if(gender.equals("女")){
			femaleButton.setSelected(true);
		} 
		relationshipTextField.setText(contact.getRelationship());   //relationshipTextField文本框中的内容设为用户选择的值     

	}


}
