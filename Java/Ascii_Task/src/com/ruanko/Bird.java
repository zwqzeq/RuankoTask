package com.ruanko;                                     //��

//Bird��̳�AsciiArt��
public class Bird extends AsciiArt {
	
	/**
	 * �޲ι��췽��
	 */
	Bird(){
		super("��","֣����");                              //���ø����вι��췽��
	}

	//��д��ͼ����
	public void draw() {
		// TODO �Զ����ɵķ������
		System.out.println("     _,");
		System.out.println("-==<' `\\");
		System.out.println("    ) /");
		System.out.println("   / (_.");
		System.out.println("  | ,-,`\\");
		System.out.println("   \\  \\ \\");
		System.out.println("    `\\, \\ \\");
		System.out.println("     ||\\ \\`|,");
		System.out.println("    _|| `=`-'");
		System.out.println("   ~~`~`");           
	}
}
