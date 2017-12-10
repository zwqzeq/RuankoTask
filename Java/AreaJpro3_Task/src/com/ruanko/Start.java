package com.ruanko;                                                      

import java.util.Scanner;                                                

//���������
public class Start {
	
	//����
	protected static void display(final Shape3 shap){
		System.out.println(shap.getShape()+"���="+shap.getArea());
	}

	//�ڿ���̨����˵�
	public static void printmanu(){
		System.out.println("~~~~~~~~~ͼ��~~~~~~~~~");
		System.out.println("1. Shape");	
		System.out.println("2. Circle");
		System.out.println("3. Rectangle");
		System.out.print("������ͼ�α��(��1��2��3):");
	}

	//�������ڷ���
	public static void main(String[] args) {
		printmanu();                                                      //��������˵��ķ���
		Scanner sc=new Scanner(System.in);
		int selection=sc.nextInt();
		//�����û�����ѡ��ֱ����ͼ�β��������������
		switch (selection) {
		case 1:                                                           //ѡ�������״	
			Shape3 shape=new Shape3();
			display(shape);
			break;

		case 2:                                                           //ѡ��Բ��
			System.out.print("������뾶(radius):");
			double radius=sc.nextDouble();
			Circle3 shape1= new Circle3(radius);                           //����һ��Circle3���͵Ķ��󣬲�����Բ���͹��췽������Բ���Ͷ��������(����ַ)���Ǹ���Ķ���
			display(shape1);                                              //����Բ�ε���������
			
			break;
		case 3:                                                           //ѡ�����
			System.out.print("��������εĳ�(length):");
			double length=sc.nextDouble();
			System.out.print("��������εĿ�(width):");
			double width=sc.nextDouble();
			Shape3 shape2=new Rectangle3(length,width);
			display(shape2);                                              //���������������
			break;

		default:
			System.out.println("�������ѡ�����");
			break;
		}
		
		//�ر�������
		if(sc!=null){
			sc.close();
		}
	}

}
