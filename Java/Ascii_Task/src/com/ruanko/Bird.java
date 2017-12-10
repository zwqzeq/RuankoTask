package com.ruanko;                                     //包

//Bird类继承AsciiArt类
public class Bird extends AsciiArt {
	
	/**
	 * 无参构造方法
	 */
	Bird(){
		super("鸟","郑文庆");                              //调用父类有参构造方法
	}

	//重写画图方法
	public void draw() {
		// TODO 自动生成的方法存根
		System.out.println("     _,");
		System.out.println("-==<' `\\");
		System.out.println("    ) /");
		System.out.println("   / (_.");
		System.out.println("  | ,-,`\\");
		System.out.println("   \\  \\ \\");
		System.out.println("    `\\, \\ \\");
		System.out.println("     ||\\ \\`|,");
		System.out.println("    _|| `=`-'");
		System.out.println("   ~~`~`");           
	}
}
