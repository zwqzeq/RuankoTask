package com.ruanko;                                          //��
//Dog�̳�AsciiArt��
public class Dog extends AsciiArt{

	/**
	 * �޲ι��췽��
	 */
	Dog(){
		super("��","֣����");                                 //���ø��๹�췽��
	} 

	//��д��ͼ����
	public void draw() {
		// TODO �Զ����ɵķ������	
		System.out.println("          .-._");     
		System.out.println("         {_}^ )o"); 
		System.out.println("{\\________//~`") ;    
		System.out.println(" (         )");
		System.out.println(" /||~~~~~||\\");
		System.out.println("|_\\_     \\_ \\_");  
	}
}
