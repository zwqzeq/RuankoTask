package com.ruanko;                                         

//Horse继承AsciiArt类
public class Horse extends AsciiArt{

	/**
	 * 无参构造方法
	 */
	Horse(){
		super("马","郑文庆");                                  //调用父类构造方法
	}

	//重写画图方法
	public void draw() {
		// TODO 自动生成的方法存根
		System.out.println("                            _(\\_/)"); 
		System.out.println("                          ,((((^`\\");
		System.out.println("                         ((((  (6 \\"); 
		System.out.println("                       ,((((( ,    \\");
		System.out.println("   ,,,_              ,(((((  /'._  ,`,");
		System.out.println("  ((((\\ ,...       ,((((   /    `-.-'");
		System.out.println("  )))  ;'    `'''''((((   (");      
		System.out.println(" (((  /            (((      \\");
		System.out.println("  )) |                      |");
		System.out.println(" ((  |        .       '     |");
		System.out.println(" ))  \\     _ '      `t   ,.')");
		System.out.println(" (   |   y;- -,-'''-.\\   \\/");  
		System.out.println(" )   / ./  ) /         `\\  \\");
		System.out.println("    |./   ( (           /  /'");
		System.out.println("    ||     \\          //' |");
		System.out.println("    ||      \\       _//'  ||");
		System.out.println("    ||       ))     |_/   ||");
		System.out.println("    \\_\\     |_/           ||");
		System.out.println("                          \\_\\");
	}
}
