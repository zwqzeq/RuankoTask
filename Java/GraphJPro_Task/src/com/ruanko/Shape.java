package com.ruanko;

/**
 *图形基类
 **/
public abstract class Shape {
	//属性
	private String shape;                        //形状

	/**
	 * 无参构造方法
	 */
	Shape(){
		this.shape="Default";                   //构造方法的作用：为属性赋值，有参构造方法:在new 一个新的对象的时候
		                                        //可以调用有参构造方法，给属性赋值
		                                        //如果没有有参构造方法，系统默认无参构造方法，此时不能再在new对象的时候给属性赋值
		                                        //但可以用调用set方法设置
	}
	
	/**
	 * 构造方法
	 * @param shape 形状
	 */
	Shape(String shape){
		this.shape=shape;
	}
	
	/**
	 * 获取图形形状
	 * return形状
	 */
	public String getShape(){
		return shape;
	}
	
	/**
	 * 设置图形形状
	 * @param shape
	 */
	public void setShape(String shape) {
		this.shape = shape;
	}

	/**
	 * 计算图形的面积（抽象方法，依靠子类来实现）
	 */
	public abstract double getArea();

}
