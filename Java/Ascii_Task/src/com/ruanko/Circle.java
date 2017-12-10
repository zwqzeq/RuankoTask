package com.ruanko;                              //��
//����Բ��̳�AsciiArt��
public class Circle extends AsciiArt{

	private int radius;                           //Բ�İ뾶

	/*
	 * ���췽��
	 */
	Circle(int radius){
		super("Բ","֣����");
		this.radius = radius;
	}

	//��ȡ�뾶
	public int getRadius() {
		return radius;
	}

	//���ð뾶
	public void setRadius(int radius) {
		this.radius = radius;
	}

	//��д����draw����
	public void draw() {
		// TODO �Զ����ɵķ������	
		int diameter=2*radius;                 //ֱ������2���İ뾶
		for(int y=0;y<=diameter;y++){          //�����0��ֱ������
			for(int x=0;x<=diameter;x++){      //�����0��ֱ������
				if((x-radius)*(x-radius)+(y-radius)*(y-radius)<=radius*radius){//���x,y��Բ�ھ����*����������ո�
					System.out.print("**");
				}else{
					System.out.print("  ");
				}
			}
			System.out.println();              //ÿһ�������Ϻ���
		}

	}

}
