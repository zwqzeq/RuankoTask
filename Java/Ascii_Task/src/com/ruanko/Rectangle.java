package com.ruanko;                                     //��

//Rectangle�̳�AsciiArt��
public class Rectangle extends AsciiArt{
	
	private int length;
	private int width;
	
	/**
	 * �޲ι��췽��
	 */
	public Rectangle(int length,int width) {
		super("����","֣����");                            //���ø����вι��췽��
		this.length = length;
		this.width = width;
	}

	//��д��ͼ����
	public void draw() {

		for(int y=0;y<=width;y++){                       //���εĿ�ȴ��㵽�����width����
			for(int x=0;x<=length;x++){                  //���εĳ��ȴ��㵽�����width����
				if(y==0||y==width||x==0||x==length){       
					System.out.print("*");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
