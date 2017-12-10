/**
 * 抽象类中可以没有抽象方法，或者一个或多个抽象方法（注意：在抽象类中只允许声明抽象方
 *           法，不允许实现抽象方法，即抽象类一定有子类，在子类中实现抽象类中的抽象方法）
 * 抽象类：抽象英文单词abstract
 * 抽象类不能创建对象，如果要想创建对象，必须通过子类继承后，由子类来创建对象
 * 抽象类中可以有具体的成员方法（普通方法）和成员变量
 * 一个类中如果有抽象方法，则这个类必须声明为抽象类
 */

/**
 * 抽象方法：只有方法的声明没有方法体
 * 抽象方法由继承该抽象方法的子类去实现它，相应于子类中去覆盖抽象方法
 */


package com.ruanko;  

//定义一个抽象类AsciiArt
public abstract class AsciiArt {

	protected String  Title;                               //标题
	public String  Author;                                //作者
	
	/**
	 * 无参构造方法
	 */
	public AsciiArt() {

	}
	
	/*
	 * 有参构造方法
	 */
	public AsciiArt(String Title,String  Author) {
		this.Title=Title;
		this.Author=Author;

	}

	//获取标题
	public String getTitle() {
		return Title;
	}

	//设置标题
	public void setTitle(String title) {
		Title = title;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	//抽象方法（画图）
	public abstract void draw();                          

}

