package com.ruanko;

/**
 *ͼ�λ���
 **/
public abstract class Shape {
	//����
	private String shape;                        //��״

	/**
	 * �޲ι��췽��
	 */
	Shape(){
		this.shape="Default";                   //���췽�������ã�Ϊ���Ը�ֵ���вι��췽��:��new һ���µĶ����ʱ��
		                                        //���Ե����вι��췽���������Ը�ֵ
		                                        //���û���вι��췽����ϵͳĬ���޲ι��췽������ʱ��������new�����ʱ������Ը�ֵ
		                                        //�������õ���set��������
	}
	
	/**
	 * ���췽��
	 * @param shape ��״
	 */
	Shape(String shape){
		this.shape=shape;
	}
	
	/**
	 * ��ȡͼ����״
	 * return��״
	 */
	public String getShape(){
		return shape;
	}
	
	/**
	 * ����ͼ����״
	 * @param shape
	 */
	public void setShape(String shape) {
		this.shape = shape;
	}

	/**
	 * ����ͼ�ε���������󷽷�������������ʵ�֣�
	 */
	public abstract double getArea();

}
