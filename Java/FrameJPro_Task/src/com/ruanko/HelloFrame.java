package com.ruanko;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * 事件源：产生事件的对象叫事件源
 * 监听器：对事件感兴趣的对象叫监听器
 * 事件源用addXXXListener将某些对象设为监听器
 * 监听器实际上是一个类的实例，它实现了XXX监听接口.
 * 事件发生后，事件源将事件对象发给已注册的所有监听器。
 * 监听器随后根据事件对象内封装的信息，决定自已该如何响应这个事件。
 */

/**
 * 有时候多个事件源都注册同一个监听器。
 * 例如：两个按钮放于面板上，它们都用行为监听器。使按不同的按钮事件后窗口的标题显示不同标志。
 * getResource()用来判断事件是由哪个对象激发的。
 */





/**
 * HelloFrame继承JFrame类
 * @author zwqabc
 */
@SuppressWarnings("serial")
public class HelloFrame extends JFrame{

	//属性
	private JLabel nameLabel=null,resultLabel=null;           //输入姓名的标签,显示结果的标签
	private	JTextField nameTextField=null,resulTextField=null;//输入姓名的文本框,显示结果的文本框
	private JButton okButton=null;//确定按钮
	/**
	 * 构造方法
	 */
	public HelloFrame(){
		this.setTitle("HelloJFrame");                        //设置标题
		this.setSize(300,200);                               //设置窗口尺寸
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //窗口关闭时的方式为退出程序
		nameLabel=new JLabel("输入姓名:");
		this.getContentPane().add(nameLabel);                //将组件（输入姓名标签）添加到容器中（窗口中）
		nameTextField=new JTextField(15);                    //设置输入姓名的文本框为15列
		this.getContentPane().add(nameTextField);            //将组件（输入姓名文本框）添加到容器中（窗口中）
		this.getContentPane().setLayout(new FlowLayout());   //设置窗口面板的布局为流式布局
		resultLabel=new JLabel("显示结果:");
		this.getContentPane().add(resultLabel);
		resulTextField=new JTextField(15);
		this.getContentPane().add(resulTextField);
		okButton=new JButton("确定"); 
		this.getContentPane().add(okButton);                 //将确定按钮添加到窗口中
		
		/*ButtonActionListener buttonActionListener=new ButtonActionListener();
		okButton.addActionListener(buttonActionListener);//为确定按钮添加监听器*/
		//或者改为：
		okButton.addActionListener(new ButtonActionListener());//为确定按钮添加监听器（也就是说监听器为new ButtonActionListener()）
		//事件源为okButton，事件源用addXXXListener将某些对象（即new ButtonActionListener()）设为监听器
		//监听器（即new ButtonActionListener()）实际上是一个类（即ButtonActionListener类）的实例，它实现了XXX（此处为ActionListener）监听接口.
	}


	/**
	 * 内部类：实现ActionListener接口，重写actionPerformed方法
	 * 按钮事件监听器类，处理“确定”按钮事件
	 */
	class ButtonActionListener implements ActionListener{

		/**
		 * 重写actionPerformed()方法，处理按钮事件
		 */
		public void actionPerformed(ActionEvent e) {
			String name=nameTextField.getText();            //获得“输入姓名”（即文本域中的内容）
			String information=name+",欢迎进入窗口程序";        //整合信息
			resulTextField.setText(information);            //将结果显示到“显示结果”文本框中

		}

	}	

}
