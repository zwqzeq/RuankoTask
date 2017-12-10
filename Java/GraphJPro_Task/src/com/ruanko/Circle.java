package com.ruanko;                                      //包

/**
 * 导入接口
 */
import com.ruanko.graph.Drawable;
import com.ruanko.graph.Eraseable;

//Circle类继承Shape类并实现接口Eraseable，Drawable
public class Circle extends Shape implements Drawable,Eraseable{
	//属性
	private double radius;                                  //圆的半径
	private final static double PI=3.1415926;               //圆周率

	/**
	 * 无参构造方法
	 */
	public Circle(){

	}

	/**
	 * 构造方法
	 * @param radius
	 */
	public Circle(double radius){
		super("Circle");
		this.radius=radius;
	}

	/**
	 * 获取半径
	 * @return radius 半径
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * 设置半径
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * 计算圆形的面积
	 * @return area 面积
	 */
	public double getArea() {
		double area=PI*radius*radius;
		return area;
	}

	/**
	 *  实现接口Eraseable中的擦图的抽象方法
	 */
	public void erase() {
		System.out.println("Erase a Circle( radius = "+radius+").");	
	}

	/**
	 *  实现接口Drawable中的擦图抽象方法
	 */
	public void draw() {
		System.out.println("Draw a Circle( radius = "+radius+").");
	}

}
