package com.ruanko;                                     //包
//Rectangle类继承Shape类
public class Rectangle extends Shape {
	private double length;                              //长方形的长
	private double width;                               //长方形的宽

	//无参构造方法
	Rectangle() {

	}
	//有参构造方法
	Rectangle(double length,double width){
		//super("Rectangle");
		super.setShape("Rectangle");
		this.length=length;
		this.width=width;
		//setLength(length);
		//setWidth(width);
	}
	
	//获取长方形长度
	public double getLength() {
		return length;
	}
//	//设置长方形长度
//	public void setLength(double length) {
//		this.length = length;
//	}
	//获取长方形宽度
	public double getWidth() {
		return width;
	}
//	//设置长方形宽度
//	public void setWidth(double width) {
//		this.width = width;
//	}

	//计算长方形的面积
	protected double getArea(double length,double width){
		double area=length*width;
		return area;
	}

}