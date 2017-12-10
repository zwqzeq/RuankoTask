package com.ruanko;                              

import java.math.BigDecimal;                      
import java.util.Scanner;                        

//定义一个长方形类
class Rectangle {
	
/**
 * get() set()方法一般用于：属性为private修饰，此时这个类的子类要调用这个private修饰的属性时，就必须用get set方法访问
 * 在本类中可以不用get set方法
 */
	
	//属性
	private double width;                         // 长方形的宽度
	private double height;                        // 长方形的长度

	// 获取（宽度）属性值
	public double getWidth() {
		return width;
	}

	// 设置（宽度）属性值
	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	// 无参构造方法
	Rectangle() {
		this.height=0.0;                                //给属性赋初值
		this.width=0.0;                                 //给属性赋初值
	}

	// 有参构造方法
	Rectangle(double inHeight, double inWidth) {
		this.height = inHeight;
		this.width = inWidth;
	}

	// 求长方形面积的方法
	public double RectangleArea(double inHeight, double inWidth) {
		return inHeight * inWidth;
	}
}
//程序入口类
public class App {
	//程序入口方法
	public static void main(String[] args) {

		double heig = 0.0;                              //初始化长度变量
		double widt = 0.0;                              //初始化宽度变量
		double Area = 0.0;                              //初始化面积变量
		Rectangle Re= new Rectangle();                  //创建一个Rectangle类的对象，名字为Rec，并调用无参构造方法
		System.out.print("请输入长方形的长：");              
		Scanner input = new Scanner(System.in);        
		heig = input.nextDouble();                     
		System.out.print("请输入长方形的宽：");              
		widt = input.nextDouble();                     
		Area = Re.RectangleArea(widt, heig);            //调用对象Re中计算面积的方法
		System.out.println("长方形的面积："+Area);          //输出面积
		BigDecimal bg=new BigDecimal(Area);             //调用保留小数位数的类
		double df=bg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println("保留两位小数后长方形的面积："+df);

		//关闭输入流
		if(input!=null){
			input.close();
		}
	}
}
