package com.ruanko;                                    

//ͼ�λ���
public class Shape3 {
	
	//����
	private String shape;                              //��״

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	//�޲ι��췽��
	public Shape3(){
		this.shape="Default";
	}


	//���ع��췽��
	public Shape3(String shape){
		this.shape=shape;
	}

	public double getArea(){
		return 0.0;                                     //���������Ĭ��0
	}

}
