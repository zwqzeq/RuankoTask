package com.ruanko;                                          //包
//Dog继承AsciiArt类
public class Dog extends AsciiArt{

	/**
	 * 无参构造方法
	 */
	Dog(){
		super("狗","郑文庆");                                 //调用父类构造方法
	} 

	//重写画图方法
	public void draw() {
		// TODO 自动生成的方法存根	
		System.out.println("          .-._");     
		System.out.println("         {_}^ )o"); 
		System.out.println("{\\________//~`") ;    
		System.out.println(" (         )");
		System.out.println(" /||~~~~~||\\");
		System.out.println("|_\\_     \\_ \\_");  
	}
}
