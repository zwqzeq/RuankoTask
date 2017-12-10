package com.ruanko;

//Rectangle3类继承 Shape3类
public class Rectangle3 extends Shape3 {
	
	//属性
	private double length;                            //矩形的长
	private double width;                             //矩形的宽

	//无参构造方法
	public Rectangle3(){
		this(0.0, 0.0);                                             //矩形的长，宽未初始化
	}

	//重载构造方法（有参）
	public Rectangle3(double length,double width){
		super("Rectangle3");                           //调用父类构造方法
		this.length=length;                           //矩形的长初始化
		this.width=width;                             //矩形的宽初始化
	}

	//获取长度值	
	public double getLength() {
		return length;
	}
	
	//设置长度值
	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	//重写父类Shape3类中的getArea方法
	public double getArea(){
		return length*width;                          //返回矩形面积
	}

	//重载Rectangle3类（本类）中的getArea方法
	public double getArea(double length,double width){
		double Area=length*width;
		return Area;                                 //根据参数计算矩形的面积 
	}
}
