package com.ruanko.xsgl.view;

import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * 实现状态栏创建和显示时间
 */
@SuppressWarnings("serial")
public class StatusBar extends JPanel implements Runnable {

	//状态栏中的标签
	JLabel labShow = new JLabel();              
	JLabel labShow2 = new JLabel("www.ruanko.com");         
	Thread th = null;                                           //定义线程变量                  

	/**
	 * 无参构造方法
	 */
	public  StatusBar(){
    	setLayout(new FlowLayout(FlowLayout.LEFT));             //标签的布局为流式左对齐
		
    	/**
    	 * public static Border createBevelBorder(int type)创建一个指定类型的斜面边框，将组
    	 * 件当前背景色的较亮的色度用于高亮显示，较暗的色度用于阴影。（在凹入边框中，阴影位于顶部，高亮显示位于其下。）
    	 * type - 指定 BevelBorder.LOWERED 或 BevelBorder.RAISED 的整数 
    	 */
    	this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
    	add(labShow);                                          //添加标签到状态栏
		add(labShow2);                                          //添加标签到状态栏
		th = new Thread(this);                                 //创建线程
		th.start();                                            //启动线程
	}

	//重写run()方法
	public void run(){
		while (true) {
			Date dd = new Date();                              //创建日期类对象
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期显示的格式
			labShow.setText(ft.format(dd));                    //将日期对象按指定的格式显示到面板
			try {
				Thread.sleep(1000);                            //休眠1秒
			} catch (InterruptedException e) {                 //捕获异常
			}
		}
	}
}
