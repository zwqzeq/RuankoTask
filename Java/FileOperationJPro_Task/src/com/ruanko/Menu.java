package com.ruanko;

import java.util.Scanner;

/**
 * Menu类
 */
public class Menu {

	/**
	 * 读取文件
	 */
	protected static void readFile(){
		//获取文件名
		@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);                   //从键盘输入文件名称
		System.out.println("请输入要读取的文件名称：");
		String filename=input.next();                           //filename用来存放从键盘输入的字符串(文件名称)

		//读取文件
		FileHandle fileHandle=new FileHandle();                 //创建FileHandle类对象fileHandle
		if(!fileHandle.readFile(filename)){                     
			System.out.println("文件读取失败！");
		}

	}


	/**
	 * 写文件
	 */
	protected static void writeFile(){

		//获得文件名
		@SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);                 //从键盘输入要读取的文件名称
		System.out.println("请输入要读取的文件名称：");
		String filename=input.nextLine();                     //filename用来存放从键盘输入要读取的文件名称

		//获得文件的内容
		System.out.println("请输入写入文件的内容：");	
		String message=input.nextLine();                      //message用来存放文件的内容

		//写文件
		FileHandle fileHandler=new FileHandle();
		if(!(fileHandler).writeFile(message,filename)){
			System.out.println("保存文件失败！");
		}
		else{
			System.out.println("写入文件成功！");
		}
	}


	/**
	 *功能：控制台打印菜单	
	 */
	public static void printmenu(){
		System.out.println("-----------文件编辑器-----------");
		System.out.println("           1 读文件                                 ");
		System.out.println("           2 写文件                                 ");
		System.out.println("------------------------------");
		System.out.print("请输入您的选择:");
	}

	/**
	 * 程序入口方法
	 * @param args
	 */
	public static void main(String[] args) {
		printmenu();                               //调用printmenu()方法打印菜单
		int selection=-1;                          //给selection变量赋初值
		Scanner input = new Scanner(System.in);
		selection=input.nextInt();                 //将键盘输入的整数赋给selection
		switch(selection){                         //根据输入的值分情况处理
		case 1:
			readFile();                            //读取文件
			break;
		case 2:
			writeFile();                           //写文件
			break;
		default :
			System.out.println("输入有误，请重新输入！");//用户输入的非1和2就提醒重新输入  
			break;
		}

		//关闭输入流
		if(input!=null){
			input.close();
		}

	}

}
