package com.ruanko.xsgl.view;

import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class StatusBar extends JPanel implements Runnable {
	JLabel labShow = new JLabel("",JLabel.LEFT);         //名字为空的标签
	JLabel labShow2 = new JLabel("www.ruanko.com");
	Thread th = null;                         
	
	//构造方法
	public  StatusBar(){
		setLayout(new FlowLayout(FlowLayout.LEFT));      //标签的布局为
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		add(labShow);
		add(labShow2);
		th = new Thread(this);
		th.start();  
	}
	
	//重写run()方法
	public void run(){
		while (true) {
			Date dd = new Date();                        //创建日期类对象
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期显示的格式
			labShow.setText(ft.format(dd));              //将日期对象按指定的格式显示到面板
			try {
				Thread.sleep(1000);              //休眠1秒
			} catch (InterruptedException e) {   //捕获异常
			}
		}
	}
}
