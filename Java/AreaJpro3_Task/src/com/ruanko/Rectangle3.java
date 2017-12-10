package com.ruanko;

//Rectangle3��̳� Shape3��
public class Rectangle3 extends Shape3 {
	
	//����
	private double length;                            //���εĳ�
	private double width;                             //���εĿ�

	//�޲ι��췽��
	public Rectangle3(){
		this(0.0, 0.0);                                             //���εĳ�����δ��ʼ��
	}

	//���ع��췽�����вΣ�
	public Rectangle3(double length,double width){
		super("Rectangle3");                           //���ø��๹�췽��
		this.length=length;                           //���εĳ���ʼ��
		this.width=width;                             //���εĿ��ʼ��
	}

	//��ȡ����ֵ	
	public double getLength() {
		return length;
	}
	
	//���ó���ֵ
	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	//��д����Shape3���е�getArea����
	public double getArea(){
		return length*width;                          //���ؾ������
	}

	//����Rectangle3�ࣨ���ࣩ�е�getArea����
	public double getArea(double length,double width){
		double Area=length*width;
		return Area;                                 //���ݲ���������ε���� 
	}
}
