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

//����һ����̳�Thread��
class SplashThread extends Thread {
	//����
	SplashWindow fp;                       //����һ��SplashWindow��ı���
	
	//���췽��
	public SplashThread(SplashWindow fp){
		this.fp = fp;
	}
	
	//@Override ��дrun()����
	public void run(){
		while(fp.progressBar.getValue()<100){     //�����������ֵС��100
			fp.progressBar.setValue(fp.progressBar.getValue()+1);//���ý�������ֵ��һ
			try{
				Thread.sleep(30);                 //ʹ����������20����
			} catch (InterruptedException e) {    //�����쳣
			}

		}
		fp.dispose();
		JMainFrame frame = new JMainFrame();                            //�������ڶ���
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
		progressBar.setString("���ڼ��س������Ժ�......");
		con.add(back,"Center");
		con.add(progressBar,"South");
		setSize(800, 600);		
		toFront();
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();//��ȡ��Ļ�ߴ�
		int x = (size.width-800)/2;                            //ʹ�����ڿ���ϴ�����Ļ������
		int y = (size.height-600)/2;                           //ʹ�����ڸ߶��ϴ�����Ļ������
		this.setLocation(x, y);                                //���ô���λ�� 
		setVisible(true);//���ô��ڿɼ�
		Thread th = new SplashThread(this);
		th.start();//�����߳�
	}


	public static void main(String[] args) {
		new SplashWindow();
	}

}
