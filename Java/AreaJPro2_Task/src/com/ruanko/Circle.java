package com.ruanko;                                      

//Circle��̳�Shape��
public class Circle extends Shape {
	
	//����
	private double radius;                              //Բ�İ뾶
	private final static double PI=3.1415926;           //Բ����

	//�޲������췽��
	public Circle() {

	}

	//�в������췽��
	public Circle(double radius) {
		super("Circle");                                //���ø���(�����ֳƻ������)���вι��췽�������Ҹ���������Ը�һ���µ�ֵ
		this.radius=radius;
	}

	//��ȡ�뾶
	public double getRadius() {
		return radius;
	}

	//���ð뾶
	public void setRadius(double radius) {
		this.radius = radius;
	}

	//����Բ�ε�����ķ���
	protected double getArea(double radius){
		double area =PI*radius*radius;
		return area;

	}
}



