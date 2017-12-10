package com.ruanko.xsgl.view;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import com.ruanko.view.JMainFrame;

//创建一个类继承Thread类
class SplashThread extends Thread {
	//属性
	SplashWindow fp;                       //创建一个SplashWindow类的变量
	
	//构造方法
	public SplashThread(SplashWindow fp){
		this.fp = fp;
	}
	
	//@Override 重写run()方法
	public void run(){
		while(fp.progressBar.getValue()<100){     //如果进度条的值小于100
			fp.progressBar.setValue(fp.progressBar.getValue()+1);//就让进度条的值加一
			try{
				Thread.sleep(30);                 //使进度条休眠20毫秒
			} catch (InterruptedException e) {    //捕获异常
			}

		}
		fp.dispose();
		JMainFrame frame = new JMainFrame();                            //创建窗口对象
		frame.setVisible(true);       
	}

}

public class SplashWindow extends JWindow{

	JLabel back = new JLabel(new ImageIcon("images\\2.jpg"));
	JProgressBar progressBar = new JProgressBar(1,100);
	public 	SplashWindow(){
		Container con = this.getContentPane();
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		progressBar.setStringPainted(true);
		progressBar.setString("正在加载程序请稍后......");
		con.add(back,"Center");
		con.add(progressBar,"South");
		setSize(800, 600);		
		toFront();
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
		int x = (size.width-800)/2;                            //使窗口在宽度上处于屏幕正中央
		int y = (size.height-600)/2;                           //使窗口在高度上处于屏幕正中央
		this.setLocation(x, y);                                //设置窗口位置 
		setVisible(true);//设置窗口可见
		Thread th = new SplashThread(this);
		th.start();//启动线程
	}


	public static void main(String[] args) {
		new SplashWindow();
	}

}
