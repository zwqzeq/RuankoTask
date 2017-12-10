package com.ruanko;                                         //包

/**
 * 导入接口
 */
import com.ruanko.graph.Drawable;
import com.ruanko.graph.Eraseable;
/**
 *Rectangle类继承Shape类并实现接口Eraseable，Drawable
 */
public class Rectangle extends Shape implements Eraseable,Drawable{
	//属性
	private double length;                                  //矩形的长
	private double width;                                   //矩形的宽

	/**
	 * 无参构造方法
	 */
	public Rectangle(){

	}

	/**
	 * 构造方法
	 * @param length
	 * @param width
	 */
	public Rectangle(double length,double width) {
		super("rectangle");                          //调用图形基类的构造方法
		this.length=length;
		this.width=width;
	}
	/**
	 * 获取矩形长度
	 * @return 矩形的长
	 */
	public double getLength() {
		return length;
	}

	/**
	 * 设置矩形的长度
	 * @param length
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * 获取矩形的宽
	 * @return 矩形的宽
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * 设置矩形的宽
	 * @param width
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * 实现接口Eraseable中的擦图抽象方法
	 */
	public void erase() {
		System.out.println("Erase a rectangle(length = )"+length+",width = "+width+").");
	}

	/**
	 * 实现接口Drawable中的画图的抽象方法
	 */
	public void draw() {
		System.out.println("Draw a rectangle(length = "+length+",width = "+width+").");
	}
	/**
	 * 计算矩形面积
	 * @return area 面积
	 */
	public double getArea() {
		double area=length*width;
		return area;
	}





}
