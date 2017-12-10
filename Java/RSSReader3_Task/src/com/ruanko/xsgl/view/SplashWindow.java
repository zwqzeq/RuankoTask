 package com.ruanko.xsgl.view;

import java.awt.BorderLayout;
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
 * 创建一个类继承Thread类
 */
class SplashThread extends Thread {

	//属性
	SplashWindow fp;                       //创建一个SplashWindow类的变量fp,注意fp不是对象，而是变量，只有new 才是创建对象

	/**
	 * 构造方法
	 * @param fp
	 */
	public SplashThread(SplashWindow fp){
		this.fp = fp;
	}

	public SplashThread(ThreadGroup g, SplashWindow splashWindow, String string) {
		// TODO 自动生成的构造函数存根
	}

	//@Override 重写run()方法
	public void run(){
		while(fp.progressBar.getValue()<100) {                    //如果进度条的值小于100
			fp.progressBar.setValue(fp.progressBar.getValue()+1);//就让进度条的值加一

			try{
				Thread.sleep(10);                                //使进度条休眠10毫秒
			} catch (InterruptedException e) {                   //捕获异常
			}
		}
		fp.dispose();/*释放由此 Window、其子组件及其拥有的所有子组件所使用的所有本机屏幕资源。即这些 Component 的资源将被破坏，它们使用的所有内存都将返回到操作系统，并将它们标记为不可显示。 
		通过随后调用 pack 或 show 重新构造本机资源，可以再次显示 Window 及其子组件。重新创建的 Window 及其子组件的状态与释放 Window 时这些对象的状态一致（不考虑这些操作之间的其他更改）。 */
    	JMainFrame frame = new JMainFrame(); //创建JMainFrame类的对象    
		frame.setVisible(true);
	
	}
}



/**
 * SplashWindow类
 * JWindow创建的窗口无边框无最大化最小化退出模型，JFrame创建的窗口有边框有最大化最小化和退出按键
 * 实现启动界面
 */
@SuppressWarnings("serial")
public class SplashWindow extends JWindow{
	JLabel back = new JLabel(new ImageIcon("images\\2.jpg"));
	JProgressBar progressBar = new JProgressBar(1,100);

	/**
	 * 无参构造方法
	 */
	public 	SplashWindow(){
		Container con = this.getContentPane();
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));//public static Cursor getPredefinedCursor(int type)返回一个具有指定预定义类型的光标对象。WAIT_CURSOR启动界面的时候光标类型为等待光标类型图形
		progressBar.setStringPainted(true);//允许进度条显示文本
		progressBar.setString("正在加载程序请稍后......");//设置进度条文本

		//（边界布局）它是窗口（Jwindow），框架（JFrame），对话框（JDialog）等的默认布局
		//下面两行等价
		//		con.add(back,"Center");
		con.add(back,BorderLayout.CENTER);

		con.add(progressBar,"South");
		setSize(800, 600);		
		
		/**
		 * 如果此窗口是可见的，则将此窗口置于前端，并可以将其设为焦点 Window。 
         *将此窗口放在堆栈顺序的顶层，并在此虚拟机中显示在所有其他窗口的上面。如果此窗口不可见，则不会发生任何操作。有些平台不允许拥有其他窗口的窗口显示在它所拥有的窗口之上。有些平台可能不允许此虚拟机将其窗口放在本机应用程序窗口或其他虚拟机窗口之上。此权限可能取决于此虚拟机中的窗口是否已被设为焦点窗口。将进行所有尝试来移动此窗口，使其位于堆栈顺序中尽可能靠前的位置；但是，开发人员不应假定此方法在所有情况下都可以将此窗口移到所有其他窗口之上。 
         *由于本机窗口系统多种多样，因此无法保证对焦点窗口和活动窗口的更改能够实现。在此窗口接收 WINDOW_GAINED_FOCUS 或 WINDOW_ACTIVATED 事件之前，开发人员不得假定此窗口是焦点窗口或活动窗口。在顶层窗口是焦点窗口的平台上，此方法可能使此窗口成为焦点窗口（如果它还不是焦点窗口）。在堆栈顺序通常不影响焦点窗口的平台上，此方法可能维持焦点窗口和活动窗口不变。 
         *如果此方法导致此窗口成为焦点窗口，而且此窗口是一个 Frame 或 Dialog，则它也将被激活。如果此窗口是焦点窗口，但它不是一个 Frame 或 Dialog，则拥有此窗口的第一个 Frame 或 Dialog 将被激活。 
         *如果此窗口被模式对话框 (modal dialog) 阻塞，则阻塞对话框将置于最前端，仍然处于被阻塞窗口的前方
		 */
		toFront();                                             //窗口显示在最前端
		
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
		int x = (size.width-getWidth())/2;                            //使窗口在宽度上处于屏幕正中央
		int y = (size.height-getHeight())/2;                           //使窗口在高度上处于屏幕正中央
		this.setLocation(x, y);                                //设置窗口位置 
		setVisible(true);                                      //设置窗口可见

		SplashThread th = new SplashThread(this);
		th.start();                                            //启动线程
        System.out.println("此线程所属线程组的名称:"+Thread.currentThread().getThreadGroup().getName());//获取此线程所属线程组的名称
		
        /**
         * 存在异常，未解决
         */
//        ThreadGroup g = new ThreadGroup("groupname");
//        SplashThread th = new SplashThread(g,this,"线程1");
//        th.start();
//        System.out.println("此线程所属线程组的名称:"+Thread.currentThread().getThreadGroup().getName());//获取此线程所属线程组的名称
//		
        
        
//		SplashThread th = new SplashThread(this);
//		th.start();                                            //启动线程
//      上面两行等价于下面两行；因为SplashThread类（子类）继承Thread类（父类），所以子类SplashThread的对象可以直接调用父类Thread的方法start（）
//		Thread th1 = new SplashThread(this);
//		th1.start();     




	}
}
