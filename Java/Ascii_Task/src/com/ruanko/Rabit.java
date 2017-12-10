package com.ruanko;                                   //包

//Rabbit继承AsciiArt类
public class Rabit extends AsciiArt{

	/**
	 * 无参构造方法
	 */
	Rabit(){
		super("兔子","郑文庆");                           //调用父类构造方法
	}
	//重写画图方法
	public void draw() {
		System.out.println(" /)/)");
		System.out.println("(- -)====)o");
		System.out.println("    ||  ||");

	}

}