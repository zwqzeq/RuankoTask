package com.ruanko;                                        

//Circle3�̳�Shape3��
public class Circle3 extends Shape3 {
	
	private double radius;                                 //Բ�İ뾶
	private final static double PI=3.1415926;              //Բ����
	
	//�޲ι��췽��
	public Circle3(){
		this(0.0);
	}
	
	//���ع��췽�����вΣ�
	public Circle3(double radius){
		super("Circle3");                                  //���ø��๹�췽��
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

	//��д�����е�getArea�������޲Σ�
	public double getArea(){
		return PI*radius*radius;                              //����Բ�����
	}

	//����Circle3�ࣨ���ࣩ�е�getArea�������вΣ�
	public double getArea(double radius){
		double Area;
		Area =radius*radius*PI;
		return Area;                                         //����Բ�����
	}

}
