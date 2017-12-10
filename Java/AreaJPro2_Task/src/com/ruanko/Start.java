 package com.ruanko;                                       //包

import java.util.Scanner;                                 //导入Scanner类
/*
 * 程序入口类
 * App2类继承Shape类
 */
public class Start{
	
	/**
	 * 函数功能：打印菜单
	 * 
	 */
	public static void printmanu(){
		System.out.println("~~~~~~~~~图形~~~~~~~~~");
		System.out.println("1. Shape");                  //1为图形形状
		System.out.println("2. Circle");                 //2为圆形
		System.out.println("3. Rectangle");              //3为正方形
		System.out.print("请选择图形编号（如1或2或3）：");      //选择菜单，提醒用户选择

	}
    //程序入口方法
	public static void main(String[] args) {
		printmanu();                                     //打印菜单
		Scanner sc = new Scanner(System.in);             
		int selection=sc.nextInt();                      
		switch (selection) {
		case 1:
			Shape shape = new Shape();                   //声明shape类型的对象“shape”，创建该对象时调用无参构造方法
			System.out.println(shape.getShape()+"面积="+shape.getArea());//若用户选择1，则输出默认图形的面积
			break;                                       //跳出switch，执行switch语句下面的语句
		case 2:
			System.out.print("请输入半径（radius）：");      //若选择2，提醒用户输入半径
			double radius=sc.nextDouble();             //radius变量存放用户输入的半径
			Circle cir=new Circle(radius);              //声明Circle类型对象cir,创建该对象时调用有参构造方法
			System.out.println(cir.getShape()+"面积="+cir.getArea(radius));//因为Circle类继承shape类，所以子类对象cir能调用父类的方法getShape（）
			break;
		case 3:
			System.out.print("请输入长方形的长：");          
			double length=sc.nextDouble();              
			System.out.print("请输入长方形的宽：");          
			double width=sc.nextDouble();                              
			Rectangle rec = new Rectangle(length,width);
			System.out.println(rec.getShape()+"面积="+rec.getArea(length,width));
			break;
		default:                                        
			System.out.println("输入错误请重新输入！");
			break;
		}
		
		//关闭输入流
		if(sc!=null){
			sc.close();
		}
	}

}
