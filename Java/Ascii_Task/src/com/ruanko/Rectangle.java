package com.ruanko;                                     //包

//Rectangle继承AsciiArt类
public class Rectangle extends AsciiArt{
	
	private int length;
	private int width;
	
	/**
	 * 无参构造方法
	 */
	public Rectangle(int length,int width) {
		super("矩形","郑文庆");                            //调用父类有参构造方法
		this.length = length;
		this.width = width;
	}

	//重写画图方法
	public void draw() {

		for(int y=0;y<=width;y++){                       //矩形的宽度从零到输入的width以内
			for(int x=0;x<=length;x++){                  //矩形的长度从零到输入的width以内
				if(y==0||y==width||x==0||x==length){       
					System.out.print("*");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
