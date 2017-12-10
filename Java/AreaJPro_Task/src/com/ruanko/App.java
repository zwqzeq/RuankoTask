package com.ruanko;                              

import java.math.BigDecimal;                      
import java.util.Scanner;                        

//����һ����������
class Rectangle {
	
/**
 * get() set()����һ�����ڣ�����Ϊprivate���Σ���ʱ����������Ҫ�������private���ε�����ʱ���ͱ�����get set��������
 * �ڱ����п��Բ���get set����
 */
	
	//����
	private double width;                         // �����εĿ��
	private double height;                        // �����εĳ���

	// ��ȡ����ȣ�����ֵ
	public double getWidth() {
		return width;
	}

	// ���ã���ȣ�����ֵ
	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	// �޲ι��췽��
	Rectangle() {
		this.height=0.0;                                //�����Ը���ֵ
		this.width=0.0;                                 //�����Ը���ֵ
	}

	// �вι��췽��
	Rectangle(double inHeight, double inWidth) {
		this.height = inHeight;
		this.width = inWidth;
	}

	// �󳤷�������ķ���
	public double RectangleArea(double inHeight, double inWidth) {
		return inHeight * inWidth;
	}
}
//���������
public class App {
	//������ڷ���
	public static void main(String[] args) {

		double heig = 0.0;                              //��ʼ�����ȱ���
		double widt = 0.0;                              //��ʼ����ȱ���
		double Area = 0.0;                              //��ʼ���������
		Rectangle Re= new Rectangle();                  //����һ��Rectangle��Ķ�������ΪRec���������޲ι��췽��
		System.out.print("�����볤���εĳ���");              
		Scanner input = new Scanner(System.in);        
		heig = input.nextDouble();                     
		System.out.print("�����볤���εĿ�");              
		widt = input.nextDouble();                     
		Area = Re.RectangleArea(widt, heig);            //���ö���Re�м�������ķ���
		System.out.println("�����ε������"+Area);          //������
		BigDecimal bg=new BigDecimal(Area);             //���ñ���С��λ������
		double df=bg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println("������λС���󳤷��ε������"+df);

		//�ر�������
		if(input!=null){
			input.close();
		}
	}
}
