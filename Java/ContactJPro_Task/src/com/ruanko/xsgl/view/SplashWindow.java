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

/**
 * 启动程序界面
 * @author asus
 *
 */
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
				Thread.sleep(10);                 //使进度条休眠20毫秒
			} catch (InterruptedException e) {    //捕获异常
			}

		}
		fp.dispose();
		JMainFrame frame = new JMainFrame();                            //创建窗口对象
		frame.setVisible(true);       
	}

}

@SuppressWarnings("serial")
public class SplashWindow extends JWindow{

	JLabel back = new JLabel(new ImageIcon("image\\2.jpg"));
	JProgressBar progressBar = new JProgressBar(1,100);
	public 	SplashWindow(){
		Container con = this.getContentPane();
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		progressBar.setStringPainted(true);
		progressBar.setString("正在加载程序......");
		con.add(back,"Center");
		con.add(progressBar,"South");
		setSize(400, 300);
		toFront();
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((size.width - getHeight())/2,(size.height - getHeight())/2);
		setVisible(true);
		Thread th = new SplashThread(this);
		th.start();
	}

}
