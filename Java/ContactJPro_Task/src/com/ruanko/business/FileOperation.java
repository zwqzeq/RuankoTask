package com.ruanko.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.ruanko.model.Contact;


/**
 * ִ�ж�ȡ�ļ��Ĳ������裺
 * ��1������file��Ķ��󣬲�������������FileReader BufferedReader
 *     	File file = new File(filePath);              
		FileReader fr = null;                      
		BufferedReader br = null;                     
 * ��2����������������
 *      fr = new FileReader(file);                 //��������
	    br = new BufferedReader(fr); 
 * ��3�����ж��ļ�
 *      String data = null;                       //������ʱ�洢�Ķ���                
		while((data = br.readLine()) != null) {   //���ļ������еĶ�ȡ���ݣ��������ļ�ĩβʱֵΪnull��
	    Contact contact = new Contact();      //����һ��Contact�����contact
		String[]infor = data.split("##");    
 * ��4���ر�������
 *   	br.close();                            //�ر������������ļ��ж�ȡ���ݽ���������д���ݵ��ļ��н��������
		fr.close();
 *  
 *  
 */


/**
 * ����ϵ��ʵ����Contact�Ķ���contact��ӵ��б�Ĳ��裺
 * ��1������һ���б�contacts��List contacts = new ArrayList();��
 * ��2������һ��ʵ�������contact��Contact contact = new Contact()��;
 * ��3��ͨ��ʵ�����set����Ϊʵ����Ķ���ֵ
 * ��4��ͨ���б��add()������ʵ����Ķ�����ӵ��б���
 */


/**
 *  ����ϵ���б�result�л�ȡ��ϵ��ʵ�������ġ������������ֻ��š�����email��ֵ�Ĳ��裺
 * ��1��ʹ��forѭ�������б�result
 * ��2������һ��ʵ����Ķ��󣬲���ȡ���б��е�Ԫ�ظ�ֵ��ʵ����Ķ���
 * ��3��ͨ��ʵ�����get����Ϊ��ȡʵ����������Ӧ��ֵ
 */


/**
 * �ļ������ࣺ������ϵ�ˣ���ѯ��ϵ�ˣ�������ϵ��
 * @author zwqabc
 */
public class FileOperation {

	/**
	 * ���ܣ�����ϵ�˱��浽ָ����·��
	 * @param ContactInfo��ϵ����Ϣ
	 * @param filePath������ϵ��·��
	 * @return ���ز�������ֵtrue��false
	 */
	public boolean saveContact(String contactInfo, String filePath){

		//���ļ�
		File file = new File(filePath);              //�����ļ�����file��
		FileWriter fw = null;                        //���������FileWriter
		PrintWriter out = null;                      //���������PrintWriter
		try {                                        //����������д�ļ�
			fw = new FileWriter(file,true);          //����һ��FileWriter��������Boolean�Ͳ���true���ʾ���ֽ�д���ļ�ĩβ����������д���ļ���ʼ����file������ʾҪд�����ݵ�File���� 
			out = new PrintWriter(fw);               //����PrintWriter����out����һ������Ϊ�ַ���������ڶ�������Ϊboolean ���������Ϊ true���� println��printf �� format ������ˢ�����������
			out.println(contactInfo);                //ʹ��println()��������ϵ����Ϣд���ļ���            
			out.flush();                             //ˢ�»���                   
			System.out.println("�ļ�����ɹ���");
			return true;  //�ܹ�ִ�е���һ��˵��ǰ�����䶼ִ�����ˣ�Ҳ�����ļ�����ɹ�������true //�ļ�����ɹ�
		} catch (IOException e) {                    //������������쳣
			e.printStackTrace();                     //����쳣��Ϣ
			System.out.println("�ļ�����ʧ�ܣ�");
			return false;                            //�ļ�����ʧ��
		} finally {
			out.close();                             //�ر���������رյ�˳��ʹ򿪵�˳���෴
			try {
				fw.close();                          //�ر������
			} catch (IOException e) {                 //������������쳣
				e.printStackTrace();                 //����쳣�������Ϣ
				return false;
			}
		}

	}

	/**
	 * ����saveContact����
	 * ���ܣ�������ϵ��
	 * @param contactInfor
	 * @param filePath
	 * @return ��������true��false������true˵���ļ�����(д�ļ�����ļ�)�ɹ���
	 */
	@SuppressWarnings("null")
	public boolean saveContact(List<?> contactInfor,String filePath) {

		File file = new File(filePath);              //����·���ҵ��ļ�������File�����file
		FileWriter fw = null;                        //���������FileWriter
		PrintWriter out = null;                      //���������PrintWriter
		try {
			fw = new FileWriter(file,true);            //����һ��FileWriter��������Boolean�Ͳ���true���ʾ���ֽ�д���ļ�ĩβ����������д���ļ���ʼ����file������ʾҪд�����ݵ�File���� 
			out = new PrintWriter(fw);                 //����PrintWriter����out
			for(int i = 0;i<contactInfor.size();i++) {
				Contact contact = (Contact)contactInfor.get(i);//ȡ����ϵ����Ϣ
				String contactStr = contact.toString();
				out.println(contactStr);
			}
			out.flush();                              //ˢ�»���
			return true;                              //������ϵ����Ϣ�ɹ�
		} catch (IOException e) {                     //������������쳣
			e.printStackTrace();                      //����쳣��Ϣ
		} finally {                                   //�ر���������ر�˳�����˳���෴
			out.close();                              //�ر������out
		} try {
			fw.close();                               //�ر������fw
		} catch (IOException e) {                     //������������쳣
			e.printStackTrace();                      //������������쳣
			return false;                             //������ϵ����Ϣʱʧ��
		}
		return false;                                 //������ϵ����Ϣʱʧ��

	}


	/**
	 * ��ָ����·����ȡ��ϵ����Ϣ�����浽�б���
	 * @param filePath��ȡ��ϵ����Ϣ��·��
	 * @return ��ϵ����Ϣ�б�
	 */
	public List<Contact> getContacts(String filePath) {
		List<Contact> contacts = new ArrayList<Contact>();              //����һ��ArrayList�����contacts�洢��ϵ����Ϣ              
		File file = new File(filePath);               //��ȡ·��filePath�����ļ�
		FileReader fr = null;                         //�����ļ���������
		BufferedReader br = null;                     //�����ļ�������������
		try {
			fr = new FileReader(file);                //��������
			br = new BufferedReader(fr);              //��������
			String data = null;                       //������ʱ�洢�Ķ���                
			while((data = br.readLine()) != null) {   //���ļ������еĶ�ȡ���ݣ��������ļ�ĩβʱֵΪnull��
				String[]infor = data.split("##");     /*ʹ��split()����������ϵ����Ϣ����ţ������ȣ�
			                                                                                                            ����ϵ����Ϣ����ţ������ȣ���##�ָ� �������ָ�����ϵ����Ϣ����ţ������ȣ�������ַ�������info��*/
				Contact contact = new Contact();      //����һ��Contact�����contact
				contact.setNumber(infor[0]);          //contact�еĳ�Ա����number��ֵ   
				contact.setName(infor[1]);            //contact�еĳ�Ա����name��ֵ
				contact.setPhonenumber(infor[2]);     //contact�еĳ�Ա����phonenumber��ֵ
				contact.setEmail(infor[3]);           //contact�еĳ�Ա����email��ֵ
				contact.setAddress(infor[4]);         //contact�еĳ�Ա����address��ֵ
				contact.setGender(infor[5]);          //contact�еĳ�Ա����gender��ֵ
				contact.setRelationship(infor[6]);    //contact�еĳ�Ա����relationship��ֵ
				contacts.add(contact);                //��contact��ӵ�������
				//contacts.add(infor);                //ʹ��add()�������ָ���ַ���������ӵ���ϵ���б���
			}
			return contacts;
		} catch (FileNotFoundException e) {
			e.printStackTrace();                       //����쳣��Ϣ
			return null;                      
		}catch (IOException e) {                       //���������쳣
			e.printStackTrace();                       //����쳣��Ϣ
			return null;                          
		} finally {
			try {                                      //�ر��������رյ�˳��ʹ򿪵�˳���෴
				br.close();                            //�ر������������ļ��ж�ȡ���ݽ���������д���ݵ��ļ��н��������
				fr.close();                            //�ر�������
			} catch (IOException e) {                  //������������쳣
				e.printStackTrace();                   //����쳣��Ϣ
				return null;
			}
		}

	}

	/**
	 * ͨ������������ѯ��ϵ�˷���
	 * @param filePath
	 * @param queryname
	 * @return 
	 */
	public List<Contact> getContacts(String filePath, String queryname) {
		List<Contact> contacts = new ArrayList<Contact>();               //�洢��ϵ����Ϣ�б�              
		File file = new File(filePath);                //��ȡ·�����ļ�
		FileReader fr = null;                          //����һ���ļ���������
		BufferedReader br = null;                      //����һ���ļ�������������
		try {
			fr = new FileReader(file);                 //��������
			br = new BufferedReader(fr);               //��������
			String data = null;                        //������ʱ�洢�Ķ���                
			while ((data = br.readLine()) != null) {   //���ļ������еĶ�ȡ���ݣ��������ļ�ĩβʱֵΪnull��
				String[] infor = data.split("##");      /*������ϵ����Ϣ��������Ϣ���浽contacts�� ʹ��split()����
			                                                                                                                 ����ϵ����Ϣ��##�ָ� �������ָ�����ϵ����Ϣ������ַ�������info��*/
				if(queryname.equals(infor[1])) {
					Contact contact = new Contact();   //����һ��Contact��Ķ���contact
					contact.setNumber(infor[0]);       //��Contact��Ķ���contact�еĳ�Ա����number��ֵ
					contact.setName(infor[1]);         //��Contact��Ķ���contact�еĳ�Ա����name��ֵ
					contact.setPhonenumber(infor[2]);  //��Contact��Ķ���contact�еĳ�Ա����phonenumber��ֵ
					contact.setEmail(infor[3]);        //��Contact��Ķ���contact�еĳ�Ա����email��ֵ
					contact.setAddress(infor[4]);      //��Contact��Ķ���contact�еĳ�Ա����address��ֵ
					contact.setGender(infor[5]);       //��Contact��Ķ���contact�еĳ�Ա����gender��ֵ
					contact.setRelationship(infor[6]);//��Contact��Ķ���contact�еĳ�Ա����relationship��ֵ
					contacts.add(contact);                //ʹ��add()�������ָ���ַ���������ӵ���ϵ���б���
				} 
				//				else {
				//					JOptionPane.showMessageDialog(null, "�ļ��в����ڴ���ϵ�ˣ����������룡");
				//					System.exit(0);                      //�˳�����
				//				}
			}
			return contacts;                             //����contacts����
		} catch (FileNotFoundException e) {
			e.printStackTrace();                         //����쳣��Ϣ
			return null;                                 //�ļ�����ʧ��

		} catch (IOException e) {                        //���������쳣
			e.printStackTrace();                         //����쳣��Ϣ
			return null;                                //�ļ�����ʧ��

		} finally {
			try {                                       //�ر��������رյ�˳��ʹ򿪵�˳���෴
				br.close();                             //�ر������������ļ��ж�ȡ���ݽ���������д���ݵ��ļ��н��������
				fr.close();                             //�ر�������
			} catch (IOException e) {                   //������������쳣
				e.printStackTrace();                    //����쳣��Ϣ
				return null;                            //�ļ�����ʧ��
			}

		}

	}

}







