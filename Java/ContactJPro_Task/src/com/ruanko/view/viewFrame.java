package com.ruanko.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.ruanko.model.Contact;

/**
 * ����ʽ����
 * @author asus
 *
 */
@SuppressWarnings("serial")
public class viewFrame extends JFrame {

	//����
	private JTextField numberTextField=null;                          //����ı���
	private JTextField nameTextField=null;                            //�����ı���
	private JTextField phonenumberTextField=null;                     //�ֻ����ı���
	private JTextField emailTextField=null;                           //�ʼ��ı���
	private JTextField addressTextField=null;                         //��ַ�ı���
	private JTextField relationshipTextField=null;                    //��ַ�ı���
	private JRadioButton maleButton=null,femaleButton=null;           //�Ա�(��,Ů)��ǩ 	

	/**
	 * �޲ι��췽��
	 */
	public viewFrame(){
		setTitle("��ϵ����ϸ��Ϣ");                                      //���ô��ڱ���
		setSize(300,250);                                            //���ô��ڳߴ�
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();  //��ȡ��Ļ�ߴ�
		int x=(size.width-300)/2;
		int y=(size.height-250)/2;
		setLocation(x, y);                                          //ʹ���ھ���
		//setVisible(true);                                         //���ô��ڿɼ�
		//setDefaultCloseOperation(EXIT_ON_CLOSE);                  //���ô��ڹرղ���
		this.setContentPane(getContentpane());                      //���������ӵ�����

	}

	//���������
	public JPanel getContentpane(){
		JPanel mainPanel=new JPanel();                              //������������
		mainPanel.setLayout(new GridLayout(7,1));                   //�������Ϊ���񲼾�7��1��
		mainPanel.add(getNumberPanel());                            //��numberPanel�����ӵ������
		mainPanel.add(getNamePanel());                              //��namePanel�����ӵ������
		mainPanel.add(getGenderPanel());                            //��genderPanel�����ӵ������
		mainPanel.add(getRelationshipPanel());                      //��relationshipPanel�����ӵ������
		mainPanel.add(getEmailPanel());                             //��emailPanel�����ӵ������
		mainPanel.add(getPhonenumberPanel());                  	    //��phonenumberPanel�����ӵ������
		mainPanel.add(getAddressPanel());                           //��addressPanel�����ӵ������
		return mainPanel;

	}

	//����numberPanel���
	public JPanel getNumberPanel(){
		JPanel numberPanel=new JPanel();                            //����JPanel�����numberPanel
		numberPanel.setLayout(new FlowLayout(FlowLayout.LEFT));     //numberPanel��岼��Ϊ��ʽ�����������
		numberPanel.add(new JLabel("��ţ�"));                        //������ǩ����ӵ����
		numberTextField=new JTextField(12);                         //�ı�����12��
		numberPanel.add(numberTextField);                           //�ı�����ӵ����numberPanel
		return numberPanel;                                         //����numberPanel���

	}

	//����namePanel���
	public JPanel getNamePanel(){                                  
		JPanel namePanel=new JPanel();                               //����JPanel�����namePanel
		namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));        //namePanel��岼��Ϊ��ʽ�����������
		namePanel.add(new JLabel("������"));                          //������ǩ����ӵ����
		nameTextField=new JTextField(12);                           //�ı�����12��
		namePanel.add(nameTextField);                               //�ı�����ӵ����namePanel

		return namePanel;

	}

	//����genderPanel���
	public JPanel getGenderPanel(){ 
		JPanel genderPanel=new JPanel();                            //����JPanel�����genderPanel
		genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));     //genderPanel��岼��Ϊ��ʽ�����������
		genderPanel.add(new JLabel("�Ա�"));
		maleButton=new JRadioButton("��");
		femaleButton=new JRadioButton("Ů");
		ButtonGroup btg=new ButtonGroup();
		btg.add(maleButton);
		btg.add(femaleButton);
		genderPanel.add(maleButton);
		genderPanel.add(femaleButton);
		return genderPanel;

	}

	//����relationshipPanel���
	public JPanel getRelationshipPanel(){
		JPanel relationshipPanel=new JPanel();                      //����JPanel�����relationshipPanel
		relationshipPanel.setLayout(new FlowLayout(FlowLayout.LEFT));//relationshipPanel��岼��Ϊ��ʽ�����������
		relationshipPanel.add(new JLabel("��ϵ��"));                  //������ǩ����ӵ����
		relationshipTextField=new JTextField(12);                   //�ı�����12��
		relationshipPanel.add(relationshipTextField);               //�ı�����ӵ����relationshipPanel
		return relationshipPanel;

	}

	//����emailPanel���
	public JPanel getEmailPanel(){
		JPanel emailPanel=new JPanel();                            //����JPanel�����emailPanel
		emailPanel.setLayout(new FlowLayout(FlowLayout.LEFT));     //emailPanel��岼��Ϊ��ʽ�����������
		emailPanel.add(new JLabel("email��"));                      //������ǩ����ӵ����
		emailTextField=new JTextField(12);                         //�ı�����12��
		emailPanel.add(emailTextField);                            //�ı�����ӵ����emailPanel
		return emailPanel;

	}

	//����phonenumberPanel���
	public JPanel getPhonenumberPanel(){
		JPanel phonenumberPanel=new JPanel();                     //����JPanel�����phonenumberPanel
		phonenumberPanel.setLayout(new FlowLayout(FlowLayout.LEFT));//phonenumberPanel��岼��Ϊ��ʽ�����������
		phonenumberPanel.add(new JLabel("�ֻ��ţ�"));               //������ǩ����ӵ����
		phonenumberTextField=new JTextField(12);                 //�ı�����12��
		phonenumberPanel.add(phonenumberTextField);              //�ı�����ӵ����phonenumberPanel
		return phonenumberPanel;
	}

	//����addressPanel���
	public JPanel getAddressPanel(){
		JPanel addressPanel=new JPanel();                       //����JPanel�����addressPanel
		addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT));//addressPanel��岼��Ϊ��ʽ�����������
		addressPanel.add(new JLabel("��ַ��"));                   //������ǩ����ӵ����
		addressTextField=new JTextField(20);                    //�ı�����20��
		addressPanel.add(addressTextField);                     //�ı�����ӵ����addressPanel
		return addressPanel;

	}

	//����ϵ����Ϣ��ʾ����������
	public void setContact(Contact contact){
		numberTextField.setText(contact.getNumber());           //��ϵ�˱��
		nameTextField.setText(contact.getName());               //��ϵ������
		phonenumberTextField.setText(contact.getPhonenumber()); //��ϵ���ֻ���
		emailTextField.setText(contact.getEmail());             //��ϵ��email
		addressTextField.setText(contact.getAddress());         //��ϵ�˵�ַ
		String gender=contact.getGender();                      //����ϵ���Ա���ʾ����ѡ��ť
		if(gender.equals("��")){
			maleButton.setSelected(true);
		} 
		if(gender.equals("Ů")){
			femaleButton.setSelected(true);
		} 
		relationshipTextField.setText(contact.getRelationship());   //relationshipTextField�ı����е�������Ϊ�û�ѡ���ֵ     

	}


}
