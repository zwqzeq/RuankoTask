package com.ruanko;                                         //��
/**
 * ����һ�����������
 */
public class App{

	char []ch1={'a','b','c'};                              //���岢��ʼ���ַ�����ch1
	char []ch2={'d','e','f'};                              //���岢��ʼ���ַ�����ch2

	//��һ�ַ����������ַ�������������ַ���
	public void strrev(){
		//�����ַ����鴴������String����
		System.out.println("~~~~~~~~~~~��һ�ַ���~~~~~~~~~~");
		String str1=new String(ch1);
		String str2=new String(ch2);
		String inputStr =str1.concat(str2);                //����ƴ�������ַ�����concat()����
		System.out.println("˳������ַ�����"+inputStr);       //���ƴ�Ӻ���ַ���

		//��������ַ���
		System.out.print("��������ַ�����");                  //�����ʾ��Ϣ
		int length=inputStr.length();                      //����ַ����ĳ���
		for(int i=0;i<length;i++){                         //�����ַ����е��ַ����������ÿ���ַ�
			System.out.print(inputStr.charAt(length-i-1));
		}
		System.out.println();                              //forѭ���������������


	}

	//�ڶ��ַ����������ַ�������������ַ���
	public void mystrrev(){
		System.out.println("~~~~~~~~~~~�ڶ��ַ���~~~~~~~~~~");
		MyString myStr1=new MyString(ch1);                   //����һ��MyString��Ķ���
		MyString myStr2=new MyString(ch2);                   //������һ��MyString��Ķ���
		MyString inputMyStr =myStr1.concat(myStr2);          //���������ַ�����Ȼ���µ��ַ��� ����inputMyStr           
		System.out.println("˳������ַ�����"+inputMyStr);       
		MyString outputMyStr =inputMyStr.reverse();
		System.out.println("��������ַ�����"+outputMyStr);  
	}

	/**
	 *������ڷ��� 
	 */
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		App app =new App();                                  //����һ��APP��Ķ���
		app.strrev();                                        //����APP�е�S����trrev����
		app.mystrrev();                                      //����APP�е�myStrrev����
	}
}
