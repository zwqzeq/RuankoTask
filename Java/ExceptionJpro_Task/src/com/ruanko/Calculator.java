package com.ruanko;

import java.util.Scanner;

//Calculator��̳�AppException��
@SuppressWarnings("serial")
public class Calculator extends Exception {

	/**
	 * ���췽��
	 */
	public Calculator() {
		super();                                              //���ø��๹�췽��
	}


	/**
	 * �ӷ�
	 * @param x
	 * @param y
	 * @return ���������ĺ�
	 */
	public int plus(int x,int y){
		int result=0;
		result=x+y;
		return result;          
	}


	/**
	 * ����
	 * @param x ������
	 * @param y ����
	 * @return ���������Ĳ�
	 */
	public int minus(int x,int y){
		int result=0;
		result=x-y;
		return result;
	}


	/**
	 * �˷�
	 * @param x
	 * @param y
	 * @return �������ĳ˻�
	 */
	public int multiply(int x,int y){
		int result=0;
		result=x*y;
		return result;
	}


	/**
	 * ����
	 * @param x ������
	 * @param y ����
	 * @return ����������
	 * @throws AppException
	 */
	public int divide(int x,int y) throws AppException{
		int result=0;
		try {
			result =x/y;
		} catch (ArithmeticException e) {
			/**
			 * ���������쳣
			 * ������Ϊ��ʱ�׳����쳣
			 */
			e.printStackTrace();//ʹ�ø���䣬����ʾ������Ϣ��Ҳ����ʾ����λ��
			throw new AppException("Calculator.divide()����Ϊ�㣡");
		}
		return result;
	}


	/**
	 * ������ڷ���
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		System.out.print("������ʽ��");        //������ʽ��+,2,6
		                                     //���Ϊ��8                		
		Scanner input=new Scanner(System.in);
		String expr=input.next();                           //����������ַ���
		//�������ʽ
		String []temp;                                     //�ַ������͵�����
		char operation='\0';
		int a=0;
		int b=0;
		try{
			temp=expr.split(",");                        //���ݸ���������ʽ��ƥ���ִ��ַ��������˴��Ǹ��ݶ���������ַ�����
			operation=temp[0].charAt(0);  
			a=Integer.parseInt(temp[1]);
			b=Integer.parseInt(temp[2]);
		}catch(ArrayIndexOutOfBoundsException e){
			//���������±�Խ���쳣
			e.printStackTrace();
			System.out.println("�����±�Խ�磡");
			System.exit(0);                                 //�������	
		}catch(NumberFormatException e){                   //�������ָ�ʽ�쳣
			e.printStackTrace();                           //��ʾ�쳣�ľ�����Ϣ
			System.out.println("���ָ�ʽ����ȷ��");
			System.exit(0);	
		}
		Calculator cal=new Calculator();
		int result=0;	 
		switch(operation){		
		case '+':                                          //�ӷ�
			result=cal.plus(a, b);                         //����calculator��Ķ���cal�ļӷ�����
			System.out.println("���Ϊ��"+result);
			break;
		case '-':                                          //����
			result=cal.minus(a, b);                        //����calculator��Ķ���cal�ļ�������      
			System.out.println("���Ϊ��"+result);
			break;
		case '*':                                          //�˷�
			result=cal.multiply(a, b);                     //����calculator��Ķ���cal�ĳ˷�����
			System.out.println("���Ϊ��"+result);
			break;
		case '/':                                         //����
			try{
				result=cal.divide(a, b);                  //����calculator��Ķ���cal�ĳ�������
			}catch(AppException e){
				/**
				 * ����divide�������׳����쳣
				 */
				System.out.println(e.getMessage());       //����쳣��Ϣ
			}
			System.out.println("���Ϊ��"+result);          //��ʾ������
			break;
		default :
			System.out.println("������ı��ʽ����ȷ��");
			break;
		}
		//�ر�������
		if(input!=null){
			input.close();
		}
	}
}
