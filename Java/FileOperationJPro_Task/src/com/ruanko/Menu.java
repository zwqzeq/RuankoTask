package com.ruanko;

import java.util.Scanner;

/**
 * Menu��
 */
public class Menu {

	/**
	 * ��ȡ�ļ�
	 */
	protected static void readFile(){
		//��ȡ�ļ���
		@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);                   //�Ӽ��������ļ�����
		System.out.println("������Ҫ��ȡ���ļ����ƣ�");
		String filename=input.next();                           //filename������ŴӼ���������ַ���(�ļ�����)

		//��ȡ�ļ�
		FileHandle fileHandle=new FileHandle();                 //����FileHandle�����fileHandle
		if(!fileHandle.readFile(filename)){                     
			System.out.println("�ļ���ȡʧ�ܣ�");
		}

	}


	/**
	 * д�ļ�
	 */
	protected static void writeFile(){

		//����ļ���
		@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);                 //�Ӽ�������Ҫ��ȡ���ļ�����
		System.out.println("������Ҫ��ȡ���ļ����ƣ�");
		String filename=input.nextLine();                     //filename������ŴӼ�������Ҫ��ȡ���ļ�����

		//����ļ�������
		System.out.println("������д���ļ������ݣ�");	
		String message=input.nextLine();                      //message��������ļ�������

		//д�ļ�
		FileHandle fileHandler=new FileHandle();
		if(!(fileHandler).writeFile(message,filename)){
			System.out.println("�����ļ�ʧ�ܣ�");
		}
		else{
			System.out.println("д���ļ��ɹ���");
		}
	}


	/**
	 *���ܣ�����̨��ӡ�˵�	
	 */
	public static void printmenu(){
		System.out.println("-----------�ļ��༭��-----------");
		System.out.println("           1 ���ļ�                                 ");
		System.out.println("           2 д�ļ�                                 ");
		System.out.println("------------------------------");
		System.out.print("����������ѡ��:");
	}

	/**
	 * ������ڷ���
	 * @param args
	 */
	public static void main(String[] args) {
		printmenu();                               //����printmenu()������ӡ�˵�
		int selection=-1;                          //��selection��������ֵ
		Scanner input = new Scanner(System.in);
		selection=input.nextInt();                 //�������������������selection
		switch(selection){                         //���������ֵ���������
		case 1:
			readFile();                            //��ȡ�ļ�
			break;
		case 2:
			writeFile();                           //д�ļ�
			break;
		default :
			System.out.println("�����������������룡");//�û�����ķ�1��2��������������  
			break;
		}

		//�ر�������
		if(input!=null){
			input.close();
		}

	}

}
