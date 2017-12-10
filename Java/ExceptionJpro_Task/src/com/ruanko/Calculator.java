package com.ruanko;

import java.util.Scanner;

//Calculator类继承AppException类
@SuppressWarnings("serial")
public class Calculator extends Exception {

	/**
	 * 构造方法
	 */
	public Calculator() {
		super();                                              //调用父类构造方法
	}


	/**
	 * 加法
	 * @param x
	 * @param y
	 * @return 两个整数的和
	 */
	public int plus(int x,int y){
		int result=0;
		result=x+y;
		return result;          
	}


	/**
	 * 减法
	 * @param x 被减数
	 * @param y 减数
	 * @return 两个整数的差
	 */
	public int minus(int x,int y){
		int result=0;
		result=x-y;
		return result;
	}


	/**
	 * 乘法
	 * @param x
	 * @param y
	 * @return 两个数的乘积
	 */
	public int multiply(int x,int y){
		int result=0;
		result=x*y;
		return result;
	}


	/**
	 * 除法
	 * @param x 被除数
	 * @param y 除数
	 * @return 两个数的商
	 * @throws AppException
	 */
	public int divide(int x,int y) throws AppException{
		int result=0;
		try {
			result =x/y;
		} catch (ArithmeticException e) {
			/**
			 * 捕获算术异常
			 * 当除数为零时抛出此异常
			 */
			e.printStackTrace();//使用该语句，能显示错误信息，也能显示错误位置
			throw new AppException("Calculator.divide()除数为零！");
		}
		return result;
	}


	/**
	 * 程序入口方法
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		System.out.print("输入表达式：");        //输入表达式：+,2,6
		                                     //结果为：8                		
		Scanner input=new Scanner(System.in);
		String expr=input.next();                           //接收输入的字符串
		//解析表达式
		String []temp;                                     //字符串类型的数组
		char operation='\0';
		int a=0;
		int b=0;
		try{
			temp=expr.split(",");                        //根据给定正则表达式的匹配拆分此字符串。（此处是根据逗号来拆分字符串）
			operation=temp[0].charAt(0);  
			a=Integer.parseInt(temp[1]);
			b=Integer.parseInt(temp[2]);
		}catch(ArrayIndexOutOfBoundsException e){
			//捕获数组下标越界异常
			e.printStackTrace();
			System.out.println("数组下标越界！");
			System.exit(0);                                 //程序结束	
		}catch(NumberFormatException e){                   //捕获数字格式异常
			e.printStackTrace();                           //显示异常的具体信息
			System.out.println("数字格式不正确！");
			System.exit(0);	
		}
		Calculator cal=new Calculator();
		int result=0;	 
		switch(operation){		
		case '+':                                          //加法
			result=cal.plus(a, b);                         //调用calculator类的对象cal的加法方法
			System.out.println("结果为："+result);
			break;
		case '-':                                          //减法
			result=cal.minus(a, b);                        //调用calculator类的对象cal的减法方法      
			System.out.println("结果为："+result);
			break;
		case '*':                                          //乘法
			result=cal.multiply(a, b);                     //调用calculator类的对象cal的乘法方法
			System.out.println("结果为："+result);
			break;
		case '/':                                         //除法
			try{
				result=cal.divide(a, b);                  //调用calculator类的对象cal的除法方法
			}catch(AppException e){
				/**
				 * 捕获divide方法中抛出的异常
				 */
				System.out.println(e.getMessage());       //输出异常信息
			}
			System.out.println("结果为："+result);          //显示计算结果
			break;
		default :
			System.out.println("您输入的表达式不正确！");
			break;
		}
		//关闭输入流
		if(input!=null){
			input.close();
		}
	}
}
