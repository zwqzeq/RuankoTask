package com.ruanko.view;

import javax.swing.JFrame;
import com.ruanko.xsgl.view.SplashWindow;

/**
 * 程序入口类
 * @author zwqabc
 *
 */
public class Start {

	/**
	 * 程序入口方法
	 * @param args
	 */
	public static void main(String[] args){	
		JFrame.setDefaultLookAndFeelDecorated(true);//设置窗口外观，注意：只有在创建窗口实例前调用此方法，才可以使窗口采用swing风格
		new SplashWindow();       
	}
}
