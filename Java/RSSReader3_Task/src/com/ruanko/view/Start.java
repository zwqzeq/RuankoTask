package com.ruanko.view;

import javax.swing.JFrame;
import com.ruanko.xsgl.view.SplashWindow;

/**
 * ���������
 * @author zwqabc
 *
 */
public class Start {

	/**
	 * ������ڷ���
	 * @param args
	 */
	public static void main(String[] args){	
		JFrame.setDefaultLookAndFeelDecorated(true);//���ô�����ۣ�ע�⣺ֻ���ڴ�������ʵ��ǰ���ô˷������ſ���ʹ���ڲ���swing���
		new SplashWindow();       
	}
}
