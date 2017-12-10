package com.ruanko.xsgl.view;

import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * ʵ��״̬����������ʾʱ��
 */
@SuppressWarnings("serial")
public class StatusBar extends JPanel implements Runnable {

	//״̬���еı�ǩ
	JLabel labShow = new JLabel();              
	JLabel labShow2 = new JLabel("www.ruanko.com");         
	Thread th = null;                                           //�����̱߳���                  

	/**
	 * �޲ι��췽��
	 */
	public  StatusBar(){
    	setLayout(new FlowLayout(FlowLayout.LEFT));             //��ǩ�Ĳ���Ϊ��ʽ�����
		
    	/**
    	 * public static Border createBevelBorder(int type)����һ��ָ�����͵�б��߿򣬽���
    	 * ����ǰ����ɫ�Ľ�����ɫ�����ڸ�����ʾ���ϰ���ɫ��������Ӱ�����ڰ���߿��У���Ӱλ�ڶ�����������ʾλ�����¡���
    	 * type - ָ�� BevelBorder.LOWERED �� BevelBorder.RAISED ������ 
    	 */
    	this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
    	add(labShow);                                          //��ӱ�ǩ��״̬��
		add(labShow2);                                          //��ӱ�ǩ��״̬��
		th = new Thread(this);                                 //�����߳�
		th.start();                                            //�����߳�
	}

	//��дrun()����
	public void run(){
		while (true) {
			Date dd = new Date();                              //�������������
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//����������ʾ�ĸ�ʽ
			labShow.setText(ft.format(dd));                    //�����ڶ���ָ���ĸ�ʽ��ʾ�����
			try {
				Thread.sleep(1000);                            //����1��
			} catch (InterruptedException e) {                 //�����쳣
			}
		}
	}
}
