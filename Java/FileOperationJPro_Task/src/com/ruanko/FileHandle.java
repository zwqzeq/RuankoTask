package com.ruanko;                                  //��

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;                         
import java.io.PrintWriter;


/**
 * BufferedReader�����ַ��������ж�ȡ�ı�����������ַ����Ӷ�ʵ���ַ���������еĸ�Ч��ȡ�� 
 * ����ָ���������Ĵ�С�����߿�ʹ��Ĭ�ϵĴ�С�����������£�Ĭ��ֵ���㹻���ˡ�
 * ͨ����Reader ������ÿ����ȡ���󶼻ᵼ�¶Եײ��ַ����ֽ���������Ӧ�Ķ�ȡ������ˣ�
 * ������ BufferedReader ��װ������ read() �������ܿ����ܸߵ� Reader���� FileReader
 * �� InputStreamReader�������磬 
 * BufferedReader in = new BufferedReader(new FileReader("foo.in"));
 * ������ָ���ļ������롣���û�л��壬��ÿ�ε��� read() �� readLine() ���ᵼ�´��ļ��ж�ȡ
 * �ֽڣ�������ת��Ϊ�ַ��󷵻أ������Ǽ����Ч�ġ� 
 */


/**
 * ����һ���ļ������������ļ���д�ļ�
 * ע�⣺���ļ�����������д�ļ��������
 * ע�⣺Ҫ����һ���ļ�������д�������ȴ��ļ���File file=new File(filename);
 */
public class FileHandle {

	/**
	 * ��ȡ�ļ�
	 * @param filename �ļ���(���·�������·��)
	 * @return
	 */
	public boolean readFile(String filename) {
		try {                                            //��Ǳ���쳣��������try����
			//���ļ�
			File file = new File(filename);                //����File��Ķ���file
			BufferedReader br = new BufferedReader(new FileReader(file));//�����������(�ַ�������)
			//����һ�еȼ������������
			//FileReader fr = new FileReader(file);
			//BufferedReader br = new BufferedReader(fr);   

			String line = null;                            //line������ʼ����line��������ļ��ж�ȡ�����ݣ�

			//��ȡ�ļ�
			while((line = br.readLine()) != null) {     //�ж��Ƿ��ȡ���ļ�����
				System.out.println(line);               //�����ȡ�����ļ����ݣ�����
			}
			br.close();                                 //�ر��ļ�
			return true;	    	                    //����ֵΪtrue��ʾ��ȡ�ļ��ɹ�
		} catch(FileNotFoundException e) {                //�����ļ�δ�ҵ��쳣
			e.printStackTrace();                        //����쳣��Ϣ
		} catch(IOException e) {                          //������������쳣
			e.printStackTrace();                        //����쳣��Ϣ
		}
		return false;                                   //����ֵΪfalse��ʾ��ȡ�ļ�ʧ��
	}


	/**
	 * д�ļ�
	 * @param content
	 * @param filename
	 * @return
	 */
	public boolean writeFile(String content,String filename) {
		try {                                         //��Ǳ���쳣��������try����
			//���ļ�
			File file=new File(filename);             //����File��Ķ���file
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));//����д������BufferedWriter�������ַ��������//FileWriter(file,true)���ݸ����� File ������һ�� FileWriter ��������ڶ�������Ϊ true�����ֽ�д���ļ�ĩβ����������д���ļ���ʼ����	
			PrintWriter out = null;                  //���������PrintWriter
			
			//bw.write(content);                       //д�ļ�(������)	��д�ļ�����һ����		
			//bw.newLine();                            //ʵ�ֻ���

			                                         //д�ļ�����������
			out = new PrintWriter(bw);               //����PrintWriter����out����һ������Ϊ�ַ���������ڶ�������Ϊboolean ���������Ϊ true���� println��printf �� format ������ˢ�����������
			out.println(content);                    //д�ļ�(����)��ӡ String��Ȼ����ֹ���С��˷�������Ϊ�����ȵ��� print(String) Ȼ����� println() һ����
			
			out.flush();                             //ˢ�»���
			bw.close();                              //�ر��ļ�	
			out.close();
			return true;                             //��ʾ�ļ���ȡ�ɹ�
		} catch(IOException e) {                     //������������쳣
			e.printStackTrace();                     //����쳣��Ϣ
		}
		return false;                                //��ʾ�ļ���ȡʧ��
	}

}
