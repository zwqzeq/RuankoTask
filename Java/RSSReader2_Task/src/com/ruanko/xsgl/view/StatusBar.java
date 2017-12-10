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
	JLabel labShow = new JLabel("",JLabel.LEFT);         //����Ϊ�յı�ǩ
	JLabel labShow2 = new JLabel("www.ruanko.com");
	Thread th = null;                         
	
	//���췽��
	public  StatusBar(){
		setLayout(new FlowLayout(FlowLayout.LEFT));      //��ǩ�Ĳ���Ϊ
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		add(labShow);
		add(labShow2);
		th = new Thread(this);
		th.start();  
	}
	
	//��дrun()����
	public void run(){
		while (true) {
			Date dd = new Date();                        //�������������
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//����������ʾ�ĸ�ʽ
			labShow.setText(ft.format(dd));              //�����ڶ���ָ���ĸ�ʽ��ʾ�����
			try {
				Thread.sleep(1000);              //����1��
			} catch (InterruptedException e) {   //�����쳣
			}
		}
	}
}
