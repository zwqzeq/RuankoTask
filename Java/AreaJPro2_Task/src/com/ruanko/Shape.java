package com.ruanko;  

//����һ��ͼ�λ���shape��
public class Shape {
	
	//����
	protected  String shape;                              //��״(String����)
	
	//�޲ι��췽��
	public Shape() {
		shape="Default";                                  //ͼ�λ����ʼ��
	}
	
	//�вι��췽��
	public Shape(String shape) {
		this.shape=shape;
	}
	
	
	public String getShape(){
		return shape;
	}
	
	
	public void setShape(String shape) {
		this.shape = shape;
	}

	//����ͼ������ķ���
	protected double getArea(){
		return 0.0;                                      //�����ʼ��Ϊ 0
	}
}
