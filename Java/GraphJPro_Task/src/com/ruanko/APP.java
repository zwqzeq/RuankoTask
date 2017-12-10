package com.ruanko;                                                  //��

/**
 * ����ӿں�Scanner��
 */
import java.util.Scanner;
import com.ruanko.graph.Drawable;
import com.ruanko.graph.Eraseable;

/**
 * Ӧ�ó��������
 */
public class APP {

	/**
	 * ���㲢���ͼ�ε����
	 * @param shape ͼ��
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
		// TODO �Զ����ɵķ������
		System.out.println("��ѡ��ͼ�α��(��1��2��3):");
		System.out.println("1. Circle");
		System.out.println("2. Rectangle");
		System.out.println("3. �˳�");
		Scanner sca=new Scanner(System.in);
		int Selection=sca.nextInt();
		switch (Selection) {
		case 1:
			System.out.print("������Բ�İ뾶:");
			double radius=sca.nextDouble();
			Circle cir=new Circle(radius);
			draw(cir);                                                //���û�ͼ����
			display(cir);                                             //���ü����������
			erase(cir);                                               //���ò���ͼ�η���
			break;
		case 2:
			System.out.println("��������εĳ�:");
			double length=sca.nextDouble();
			System.out.println("��������εĿ�:");
			double width=sca.nextDouble();
			Rectangle rec=new Rectangle(length,width);               //����һ��������Ķ��󣬲������вεĹ��췽��
			draw(rec);                                               //���û�ͼ����
			display(rec);                                            //���ü����������
			erase(rec);                                              //���ò���ͼ�η���
			break;
		case 3:
			System.out.println("�˳�");
			break;
		default:
			System.out.println("����������������룡");
			break;
		}
		if (sca != null) {
			sca.close();
		}
	}

}
