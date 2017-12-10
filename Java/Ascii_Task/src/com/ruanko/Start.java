package com.ruanko;                              

import java.util.Scanner;                       

/**
 * 程序入口类
 * @author zwqabc
 */
public class Start {
	
	//属性
	private  AsciiArt art;//定义一个抽象类AsciiArt的对象

	/*
	 * 无参构造方法
	 */
	public Start(){
		
	}

	//画图方法
	public void draw() {
		System.out.println("----------------------------");
		System.out.println(" 作品:" + art.getTitle());              
		System.out.println(" 作者:" + art.getAuthor());
		art.draw(); 
	}

	//打印菜单
	public static void printmanu(){
		System.out.println("========作品列表========");
		System.out.println("1. 圆形");
		System.out.println("2. 矩形");
		System.out.println("3. 小兔子");
		System.out.println("4. 蛇");                   //菜单
		System.out.println("5. 马");
		System.out.println("6. 鸟");
		System.out.println("7. 狗");
		System.out.println("0. 退出");
		System.out.println("======================");
		System.out.println("请输入您的选择:");
	}

	//程序入口
	public static void main(String[] args) {
        Start st = new Start();               //创建Start类对象
		int selection=0;                           //selection变量用来接收用户输入
		Scanner sc=null;
		do{
			printmanu();                          //调用打印菜单的方法
			sc=new Scanner(System.in);    
			selection = sc.nextInt();             //接收用户输入
			switch(selection){
			case 1:
				System.out.print("请输入圆的半径："); 
			    int	radius= sc.nextInt();       
			    st.art=new Circle(radius);      //创建一个圆类的对象，并把这个对象的引用(即地址)赋给art变量，所以art变量指向圆。art.draw()就表示调用圆中的画图方法        
			    st.draw();     //调用Start类中的画图方法，因为上一句已经使art指向了圆，所以此时Start中的art.getTitle()指圆的标题，art.draw()指圆的画图方法
				break;
			case 2:
				System.out.print("请输入矩形的长：");                
				int length=sc.nextInt();                        
				System.out.print("请输入矩形的宽：");
				int width=sc.nextInt();                         
				st.art=new Rectangle(length,width);
				st.draw();                     
				break;
			case 3:
				st.art=new Rabit();
				st.draw();                     
				break;
			case 4:
				st.art=new Snake(); 
				st.draw();                    
				break;
			case 5:
				st.art=new Horse();
				st.draw();                      
				break;
			case 6:
				st.art=new Bird();
				st.draw();                     
				break;
			case 7:
				st.art=new Dog();
				st.draw();                      
				break;
			case 0:
				System.out.println("退出系统！");
				break;
			default :
				System.out.print("输入错误，请重新输入！:");
				selection=sc.nextInt();
				break;
			}
		}while(selection != 0);
		
		//关闭输入流
		if(sc!=null){
			sc.close();
		}
	}
}
