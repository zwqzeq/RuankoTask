package com.ruanko;                                     //��
//Rectangle��̳�Shape��
public class Rectangle extends Shape {
	private double length;                              //�����εĳ�
	private double width;                               //�����εĿ�

	//�޲ι��췽��
	Rectangle() {

	}
	//�вι��췽��
	Rectangle(double length,double width){
		//super("Rectangle");
		super.setShape("Rectangle");
		this.length=length;
		this.width=width;
		//setLength(length);
		//setWidth(width);
	}
	
	//��ȡ�����γ���
	public double getLength() {
		return length;
	}
//	//���ó����γ���
//	public void setLength(double length) {
//		this.length = length;
//	}
	//��ȡ�����ο��
	public double getWidth() {
		return width;
	}
//	//���ó����ο��
//	public void setWidth(double width) {
//		this.width = width;
//	}

	//���㳤���ε����
	protected double getArea(double length,double width){
		double area=length*width;
		return area;
	}

}