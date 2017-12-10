package com.ruanko;                                                      

import java.util.Scanner;                                                

//程序入口类
public class Start {
	
	//方法
	protected static void display(final Shape3 shap){
		System.out.println(shap.getShape()+"面积="+shap.getArea());
	}

	//在控制台输出菜单
	public static void printmanu(){
		System.out.println("~~~~~~~~~图形~~~~~~~~~");
		System.out.println("1. Shape");	
		System.out.println("2. Circle");
		System.out.println("3. Rectangle");
		System.out.print("请输入图形编号(如1或2或3):");
	}

	//程序的入口方法
	public static void main(String[] args) {
		printmanu();                                                      //调用输出菜单的方法
		Scanner sc=new Scanner(System.in);
		int selection=sc.nextInt();
		//根据用户输入选项，分别输出图形参数，并计算面积
		switch (selection) {
		case 1:                                                           //选择基本形状	
			Shape3 shape=new Shape3();
			display(shape);
			break;

		case 2:                                                           //选择圆形
			System.out.print("请输入半径(radius):");
			double radius=sc.nextDouble();
			Circle3 shape1= new Circle3(radius);                           //创建一个Circle3类型的对象，并调用圆类型构造方法，将圆类型对象的引用(即地址)覆盖父类的对象
			display(shape1);                                              //计算圆形的面积并输出
			
			break;
		case 3:                                                           //选择矩形
			System.out.print("请输入矩形的长(length):");
			double length=sc.nextDouble();
			System.out.print("请输入矩形的宽(width):");
			double width=sc.nextDouble();
			Shape3 shape2=new Rectangle3(length,width);
			display(shape2);                                              //计算矩形面积并输出
			break;

		default:
			System.out.println("您输入的选项错误！");
			break;
		}
		
		//关闭输入流
		if(sc!=null){
			sc.close();
		}
	}

}
