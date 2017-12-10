package com.ruanko;                            

//Snake继承AsciiArt类
public class Snake extends AsciiArt{

	/**
	 * 无参构造方法
	 */
	public Snake() {
		super("蛇","郑文庆");                        //调用父类有参构造方法
	}

	//重写画图方法
	public void draw() {                  
		System.out.println("         _|__|  O|");
		System.out.println("\\/     /~     \\_/ \\");
		System.out.println(" \\____|__________/  \\");
		System.out.println("        \\_______      \\");
		System.out.println("               `\\     \\                  \\");
		System.out.println("                  |     |                    \\");
		System.out.println("                 /      /                     \\");
		System.out.println("                /     /                        \\");
		System.out.println("              /      /                         \\ \\");
		System.out.println("             /     /                            \\  \\");
		System.out.println("           /     /             _----_            \\  \\");
		System.out.println("          /     /           _-~      ~-_         |   |");
		System.out.println("         (      (        _-~    _--_    ~-_     _/   |");
		System.out.println("          \\      ~-____-~    _-~    ~-_    ~-_-~    /");
		System.out.println("            ~-_           _-~          ~-_       _-~ ");  
		System.out.println("               ~--______-~                ~-___-~  ");             
	}
}
