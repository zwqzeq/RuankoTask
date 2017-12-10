package com.ruanko;                                      

//Circle类继承Shape类
public class Circle extends Shape {
	
	//属性
	private double radius;                              //圆的半径
	private final static double PI=3.1415926;           //圆周率

	//无参数构造方法
	public Circle() {

	}

	//有参数构造方法
	public Circle(double radius) {
		super("Circle");                                //调用父类(父类又称基类或超类)的有参构造方法，并且给父类的属性赋一个新的值
		this.radius=radius;
	}

	//获取半径
	public double getRadius() {
		return radius;
	}

	//设置半径
	public void setRadius(double radius) {
		this.radius = radius;
	}

	//计算圆形的面积的方法
	protected double getArea(double radius){
		double area =PI*radius*radius;
		return area;

	}
}



