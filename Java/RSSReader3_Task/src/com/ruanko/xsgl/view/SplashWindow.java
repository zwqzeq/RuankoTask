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
 * ����һ����̳�Thread��
 */
class SplashThread extends Thread {

	//����
	SplashWindow fp;                       //����һ��SplashWindow��ı���fp,ע��fp���Ƕ��󣬶��Ǳ�����ֻ��new ���Ǵ�������

	/**
	 * ���췽��
	 * @param fp
	 */
	public SplashThread(SplashWindow fp){
		this.fp = fp;
	}

	public SplashThread(ThreadGroup g, SplashWindow splashWindow, String string) {
		// TODO �Զ����ɵĹ��캯�����
	}

	//@Override ��дrun()����
	public void run(){
		while(fp.progressBar.getValue()<100) {                    //�����������ֵС��100
			fp.progressBar.setValue(fp.progressBar.getValue()+1);//���ý�������ֵ��һ

			try{
				Thread.sleep(10);                                //ʹ����������10����
			} catch (InterruptedException e) {                   //�����쳣
			}
		}
		fp.dispose();/*�ͷ��ɴ� Window�������������ӵ�е������������ʹ�õ����б�����Ļ��Դ������Щ Component ����Դ�����ƻ�������ʹ�õ������ڴ涼�����ص�����ϵͳ���������Ǳ��Ϊ������ʾ�� 
		ͨ�������� pack �� show ���¹��챾����Դ�������ٴ���ʾ Window ��������������´����� Window �����������״̬���ͷ� Window ʱ��Щ�����״̬һ�£���������Щ����֮����������ģ��� */
    	JMainFrame frame = new JMainFrame(); //����JMainFrame��Ķ���    
		frame.setVisible(true);
	
	}
}



/**
 * SplashWindow��
 * JWindow�����Ĵ����ޱ߿��������С���˳�ģ�ͣ�JFrame�����Ĵ����б߿��������С�����˳�����
 * ʵ����������
 */
@SuppressWarnings("serial")
public class SplashWindow extends JWindow{
	JLabel back = new JLabel(new ImageIcon("images\\2.jpg"));
	JProgressBar progressBar = new JProgressBar(1,100);

	/**
	 * �޲ι��췽��
	 */
	public 	SplashWindow(){
		Container con = this.getContentPane();
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));//public static Cursor getPredefinedCursor(int type)����һ������ָ��Ԥ�������͵Ĺ�����WAIT_CURSOR���������ʱ��������Ϊ�ȴ��������ͼ��
		progressBar.setStringPainted(true);//�����������ʾ�ı�
		progressBar.setString("���ڼ��س������Ժ�......");//���ý������ı�

		//���߽粼�֣����Ǵ��ڣ�Jwindow������ܣ�JFrame�����Ի���JDialog���ȵ�Ĭ�ϲ���
		//�������еȼ�
		//		con.add(back,"Center");
		con.add(back,BorderLayout.CENTER);

		con.add(progressBar,"South");
		setSize(800, 600);		
		
		/**
		 * ����˴����ǿɼ��ģ��򽫴˴�������ǰ�ˣ������Խ�����Ϊ���� Window�� 
         *���˴��ڷ��ڶ�ջ˳��Ķ��㣬���ڴ����������ʾ�������������ڵ����档����˴��ڲ��ɼ����򲻻ᷢ���κβ�������Щƽ̨������ӵ���������ڵĴ�����ʾ������ӵ�еĴ���֮�ϡ���Щƽ̨���ܲ��������������䴰�ڷ��ڱ���Ӧ�ó��򴰿ڻ��������������֮�ϡ���Ȩ�޿���ȡ���ڴ�������еĴ����Ƿ��ѱ���Ϊ���㴰�ڡ����������г������ƶ��˴��ڣ�ʹ��λ�ڶ�ջ˳���о����ܿ�ǰ��λ�ã����ǣ�������Ա��Ӧ�ٶ��˷�������������¶����Խ��˴����Ƶ�������������֮�ϡ� 
         *���ڱ�������ϵͳ���ֶ���������޷���֤�Խ��㴰�ںͻ���ڵĸ����ܹ�ʵ�֡��ڴ˴��ڽ��� WINDOW_GAINED_FOCUS �� WINDOW_ACTIVATED �¼�֮ǰ��������Ա���üٶ��˴����ǽ��㴰�ڻ����ڡ��ڶ��㴰���ǽ��㴰�ڵ�ƽ̨�ϣ��˷�������ʹ�˴��ڳ�Ϊ���㴰�ڣ�����������ǽ��㴰�ڣ����ڶ�ջ˳��ͨ����Ӱ�콹�㴰�ڵ�ƽ̨�ϣ��˷�������ά�ֽ��㴰�ںͻ���ڲ��䡣 
         *����˷������´˴��ڳ�Ϊ���㴰�ڣ����Ҵ˴�����һ�� Frame �� Dialog������Ҳ�����������˴����ǽ��㴰�ڣ���������һ�� Frame �� Dialog����ӵ�д˴��ڵĵ�һ�� Frame �� Dialog ������� 
         *����˴��ڱ�ģʽ�Ի��� (modal dialog) �������������Ի���������ǰ�ˣ���Ȼ���ڱ��������ڵ�ǰ��
		 */
		toFront();                                             //������ʾ����ǰ��
		
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();//��ȡ��Ļ�ߴ�
		int x = (size.width-getWidth())/2;                            //ʹ�����ڿ���ϴ�����Ļ������
		int y = (size.height-getHeight())/2;                           //ʹ�����ڸ߶��ϴ�����Ļ������
		this.setLocation(x, y);                                //���ô���λ�� 
		setVisible(true);                                      //���ô��ڿɼ�

		SplashThread th = new SplashThread(this);
		th.start();                                            //�����߳�
        System.out.println("���߳������߳��������:"+Thread.currentThread().getThreadGroup().getName());//��ȡ���߳������߳��������
		
        /**
         * �����쳣��δ���
         */
//        ThreadGroup g = new ThreadGroup("groupname");
//        SplashThread th = new SplashThread(g,this,"�߳�1");
//        th.start();
//        System.out.println("���߳������߳��������:"+Thread.currentThread().getThreadGroup().getName());//��ȡ���߳������߳��������
//		
        
        
//		SplashThread th = new SplashThread(this);
//		th.start();                                            //�����߳�
//      �������еȼ����������У���ΪSplashThread�ࣨ���ࣩ�̳�Thread�ࣨ���ࣩ����������SplashThread�Ķ������ֱ�ӵ��ø���Thread�ķ���start����
//		Thread th1 = new SplashThread(this);
//		th1.start();     




	}
}
