package com.ruanko;                                        

//Circle3继承Shape3类
public class Circle3 extends Shape3 {
	
	private double radius;                                 //圆的半径
	private final static double PI=3.1415926;              //圆周率
	
	//无参构造方法
	public Circle3(){
		this(0.0);
	}
	
	//重载构造方法（有参）
	public Circle3(double radius){
		super("Circle3");                                  //调用父类构造方法
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

	//重写父类中的getArea方法（无参）
	public double getArea(){
		return PI*radius*radius;                              //返回圆形面积
	}

	//重载Circle3类（本类）中的getArea方法（有参）
	public double getArea(double radius){
		double Area;
		Area =radius*radius*PI;
		return Area;                                         //返回圆形面积
	}

}
