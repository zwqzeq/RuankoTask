 package com.ruanko;                                       //��

import java.util.Scanner;                                 //����Scanner��
/*
 * ���������
 * App2��̳�Shape��
 */
public class Start{
	
	/**
	 * �������ܣ���ӡ�˵�
	 * 
	 */
	public static void printmanu(){
		System.out.println("~~~~~~~~~ͼ��~~~~~~~~~");
		System.out.println("1. Shape");                  //1Ϊͼ����״
		System.out.println("2. Circle");                 //2ΪԲ��
		System.out.println("3. Rectangle");              //3Ϊ������
		System.out.print("��ѡ��ͼ�α�ţ���1��2��3����");      //ѡ��˵��������û�ѡ��

	}
    //������ڷ���
	public static void main(String[] args) {
		printmanu();                                     //��ӡ�˵�
		Scanner sc = new Scanner(System.in);             
		int selection=sc.nextInt();                      
		switch (selection) {
		case 1:
			Shape shape = new Shape();                   //����shape���͵Ķ���shape���������ö���ʱ�����޲ι��췽��
			System.out.println(shape.getShape()+"���="+shape.getArea());//���û�ѡ��1�������Ĭ��ͼ�ε����
			break;                                       //����switch��ִ��switch�����������
		case 2:
			System.out.print("������뾶��radius����");      //��ѡ��2�������û�����뾶
			double radius=sc.nextDouble();             //radius��������û�����İ뾶
			Circle cir=new Circle(radius);              //����Circle���Ͷ���cir,�����ö���ʱ�����вι��췽��
			System.out.println(cir.getShape()+"���="+cir.getArea(radius));//��ΪCircle��̳�shape�࣬�����������cir�ܵ��ø���ķ���getShape����
			break;
		case 3:
			System.out.print("�����볤���εĳ���");          
			double length=sc.nextDouble();              
			System.out.print("�����볤���εĿ�");          
			double width=sc.nextDouble();                              
			Rectangle rec = new Rectangle(length,width);
			System.out.println(rec.getShape()+"���="+rec.getArea(length,width));
			break;
		default:                                        
			System.out.println("����������������룡");
			break;
		}
		
		//�ر�������
		if(sc!=null){
			sc.close();
		}
	}

}
