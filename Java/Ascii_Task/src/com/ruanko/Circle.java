package com.ruanko;                              //包
//定义圆类继承AsciiArt类
public class Circle extends AsciiArt{

	private int radius;                           //圆的半径

	/*
	 * 构造方法
	 */
	Circle(int radius){
		super("圆","郑文庆");
		this.radius = radius;
	}

	//获取半径
	public int getRadius() {
		return radius;
	}

	//设置半径
	public void setRadius(int radius) {
		this.radius = radius;
	}

	//重写父类draw方法
	public void draw() {
		// TODO 自动生成的方法存根	
		int diameter=2*radius;                 //直径等于2倍的半径
		for(int y=0;y<=diameter;y++){          //纵轴从0到直径长度
			for(int x=0;x<=diameter;x++){      //横轴从0到直径长度
				if((x-radius)*(x-radius)+(y-radius)*(y-radius)<=radius*radius){//如果x,y在圆内就输出*，否则输出空格
					System.out.print("**");
				}else{
					System.out.print("  ");
				}
			}
			System.out.println();              //每一行输出完毕后换行
		}

	}

}
