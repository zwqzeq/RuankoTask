package com.ruanko;                                      //��

/**
 * ����ӿ�
 */
import com.ruanko.graph.Drawable;
import com.ruanko.graph.Eraseable;

//Circle��̳�Shape�ಢʵ�ֽӿ�Eraseable��Drawable
public class Circle extends Shape implements Drawable,Eraseable{
	//����
	private double radius;                                  //Բ�İ뾶
	private final static double PI=3.1415926;               //Բ����

	/**
	 * �޲ι��췽��
	 */
	public Circle(){

	}

	/**
	 * ���췽��
	 * @param radius
	 */
	public Circle(double radius){
		super("Circle");
		this.radius=radius;
	}

	/**
	 * ��ȡ�뾶
	 * @return radius �뾶
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * ���ð뾶
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * ����Բ�ε����
	 * @return area ���
	 */
	public double getArea() {
		double area=PI*radius*radius;
		return area;
	}

	/**
	 *  ʵ�ֽӿ�Eraseable�еĲ�ͼ�ĳ��󷽷�
	 */
	public void erase() {
		System.out.println("Erase a Circle( radius = "+radius+").");	
	}

	/**
	 *  ʵ�ֽӿ�Drawable�еĲ�ͼ���󷽷�
	 */
	public void draw() {
		System.out.println("Draw a Circle( radius = "+radius+").");
	}

}
