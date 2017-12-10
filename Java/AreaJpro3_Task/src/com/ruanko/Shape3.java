package com.ruanko;                                    

//图形基类
public class Shape3 {
	
	//属性
	private String shape;                              //形状

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	//无参构造方法
	public Shape3(){
		this.shape="Default";
	}


	//重载构造方法
	public Shape3(String shape){
		this.shape=shape;
	}

	public double getArea(){
		return 0.0;                                     //返回面积，默认0
	}

}
