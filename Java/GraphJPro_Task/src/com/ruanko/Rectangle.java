package com.ruanko;                                         //��

/**
 * ����ӿ�
 */
import com.ruanko.graph.Drawable;
import com.ruanko.graph.Eraseable;
/**
 *Rectangle��̳�Shape�ಢʵ�ֽӿ�Eraseable��Drawable
 */
public class Rectangle extends Shape implements Eraseable,Drawable{
	//����
	private double length;                                  //���εĳ�
	private double width;                                   //���εĿ�

	/**
	 * �޲ι��췽��
	 */
	public Rectangle(){

	}

	/**
	 * ���췽��
	 * @param length
	 * @param width
	 */
	public Rectangle(double length,double width) {
		super("rectangle");                          //����ͼ�λ���Ĺ��췽��
		this.length=length;
		this.width=width;
	}
	/**
	 * ��ȡ���γ���
	 * @return ���εĳ�
	 */
	public double getLength() {
		return length;
	}

	/**
	 * ���þ��εĳ���
	 * @param length
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * ��ȡ���εĿ�
	 * @return ���εĿ�
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * ���þ��εĿ�
	 * @param width
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * ʵ�ֽӿ�Eraseable�еĲ�ͼ���󷽷�
	 */
	public void erase() {
		System.out.println("Erase a rectangle(length = )"+length+",width = "+width+").");
	}

	/**
	 * ʵ�ֽӿ�Drawable�еĻ�ͼ�ĳ��󷽷�
	 */
	public void draw() {
		System.out.println("Draw a rectangle(length = "+length+",width = "+width+").");
	}
	/**
	 * ����������
	 * @return area ���
	 */
	public double getArea() {
		double area=length*width;
		return area;
	}





}
