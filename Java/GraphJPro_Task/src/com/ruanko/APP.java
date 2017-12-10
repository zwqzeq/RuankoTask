package com.ruanko;                                                  //包

/**
 * 导入接口和Scanner类
 */
import java.util.Scanner;
import com.ruanko.graph.Drawable;
import com.ruanko.graph.Eraseable;

/**
 * 应用程序入口类
 */
public class APP {

	/**
	 * 计算并输出图形的面积
	 * @param shape 图形
	 */
	protected static void display(final Shape shape){
		System.out.println(shape.getShape()+" area="+shape.getArea());
	}

	/**
	 * 
	 * @param drawable
	 */
	protected  static void draw(final Drawable drawable) {
		drawable.draw();
	}

	/**
	 * 
	 * @param eraseable
	 */
	protected static void erase(final Eraseable eraseable) {
		eraseable.erase();
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		System.out.println("请选择图形编号(如1或2或3):");
		System.out.println("1. Circle");
		System.out.println("2. Rectangle");
		System.out.println("3. 退出");
		Scanner sca=new Scanner(System.in);
		int Selection=sca.nextInt();
		switch (Selection) {
		case 1:
			System.out.print("请输入圆的半径:");
			double radius=sca.nextDouble();
			Circle cir=new Circle(radius);
			draw(cir);                                                //调用画图方法
			display(cir);                                             //调用计算面积方法
			erase(cir);                                               //调用擦除图形方法
			break;
		case 2:
			System.out.println("请输入矩形的长:");
			double length=sca.nextDouble();
			System.out.println("请输入矩形的宽:");
			double width=sca.nextDouble();
			Rectangle rec=new Rectangle(length,width);               //创建一个矩形类的对象，并调用有参的构造方法
			draw(rec);                                               //调用画图方法
			display(rec);                                            //调用计算面积方法
			erase(rec);                                              //调用擦除图形方法
			break;
		case 3:
			System.out.println("退出");
			break;
		default:
			System.out.println("输入错误，请重新输入！");
			break;
		}
		if (sca != null) {
			sca.close();
		}
	}

}
