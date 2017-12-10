package com.ruanko;  

//创建一个图形基类shape类
public class Shape {
	
	//属性
	protected  String shape;                              //形状(String类型)
	
	//无参构造方法
	public Shape() {
		shape="Default";                                  //图形基类初始化
	}
	
	//有参构造方法
	public Shape(String shape) {
		this.shape=shape;
	}
	
	
	public String getShape(){
		return shape;
	}
	
	
	public void setShape(String shape) {
		this.shape = shape;
	}

	//计算图形面积的方法
	protected double getArea(){
		return 0.0;                                      //面积初始化为 0
	}
}
