package com.ruanko;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * �¼�Դ�������¼��Ķ�����¼�Դ
 * �����������¼�����Ȥ�Ķ���м�����
 * �¼�Դ��addXXXListener��ĳЩ������Ϊ������
 * ������ʵ������һ�����ʵ������ʵ����XXX�����ӿ�.
 * �¼��������¼�Դ���¼����󷢸���ע������м�������
 * �������������¼������ڷ�װ����Ϣ���������Ѹ������Ӧ����¼���
 */

/**
 * ��ʱ�����¼�Դ��ע��ͬһ����������
 * ���磺������ť��������ϣ����Ƕ�����Ϊ��������ʹ����ͬ�İ�ť�¼��󴰿ڵı�����ʾ��ͬ��־��
 * getResource()�����ж��¼������ĸ����󼤷��ġ�
 */





/**
 * HelloFrame�̳�JFrame��
 * @author zwqabc
 */
@SuppressWarnings("serial")
public class HelloFrame extends JFrame{

	//����
	private JLabel nameLabel=null,resultLabel=null;           //���������ı�ǩ,��ʾ����ı�ǩ
	private	JTextField nameTextField=null,resulTextField=null;//�����������ı���,��ʾ������ı���
	private JButton okButton=null;//ȷ����ť
	/**
	 * ���췽��
	 */
	public HelloFrame(){
		this.setTitle("HelloJFrame");                        //���ñ���
		this.setSize(300,200);                               //���ô��ڳߴ�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���ڹر�ʱ�ķ�ʽΪ�˳�����
		nameLabel=new JLabel("��������:");
		this.getContentPane().add(nameLabel);                //�����������������ǩ����ӵ������У������У�
		nameTextField=new JTextField(15);                    //���������������ı���Ϊ15��
		this.getContentPane().add(nameTextField);            //����������������ı�����ӵ������У������У�
		this.getContentPane().setLayout(new FlowLayout());   //���ô������Ĳ���Ϊ��ʽ����
		resultLabel=new JLabel("��ʾ���:");
		this.getContentPane().add(resultLabel);
		resulTextField=new JTextField(15);
		this.getContentPane().add(resulTextField);
		okButton=new JButton("ȷ��"); 
		this.getContentPane().add(okButton);                 //��ȷ����ť��ӵ�������
		
		/*ButtonActionListener buttonActionListener=new ButtonActionListener();
		okButton.addActionListener(buttonActionListener);//Ϊȷ����ť��Ӽ�����*/
		//���߸�Ϊ��
		okButton.addActionListener(new ButtonActionListener());//Ϊȷ����ť��Ӽ�������Ҳ����˵������Ϊnew ButtonActionListener()��
		//�¼�ԴΪokButton���¼�Դ��addXXXListener��ĳЩ���󣨼�new ButtonActionListener()����Ϊ������
		//����������new ButtonActionListener()��ʵ������һ���ࣨ��ButtonActionListener�ࣩ��ʵ������ʵ����XXX���˴�ΪActionListener�������ӿ�.
	}


	/**
	 * �ڲ��ࣺʵ��ActionListener�ӿڣ���дactionPerformed����
	 * ��ť�¼��������࣬����ȷ������ť�¼�
	 */
	class ButtonActionListener implements ActionListener{

		/**
		 * ��дactionPerformed()����������ť�¼�
		 */
		public void actionPerformed(ActionEvent e) {
			String name=nameTextField.getText();            //��á����������������ı����е����ݣ�
			String information=name+",��ӭ���봰�ڳ���";        //������Ϣ
			resulTextField.setText(information);            //�������ʾ������ʾ������ı�����

		}

	}	

}
