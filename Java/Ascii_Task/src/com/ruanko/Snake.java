package com.ruanko;                            

//Snake�̳�AsciiArt��
public class Snake extends AsciiArt{

	/**
	 * �޲ι��췽��
	 */
	public Snake() {
		super("��","֣����");                        //���ø����вι��췽��
	}

	//��д��ͼ����
	public void draw() {                  
		System.out.println("         _|__|  O|");
		System.out.println("\\/     /~     \\_/ \\");
		System.out.println(" \\____|__________/  \\");
		System.out.println("        \\_______      \\");
		System.out.println("               `\\     \\                  \\");
		System.out.println("                  |     |                    \\");
		System.out.println("                 /      /                     \\");
		System.out.println("                /     /                        \\");
		System.out.println("              /      /                         \\ \\");
		System.out.println("             /     /                            \\  \\");
		System.out.println("           /     /             _----_            \\  \\");
		System.out.println("          /     /           _-~      ~-_         |   |");
		System.out.println("         (      (        _-~    _--_    ~-_     _/   |");
		System.out.println("          \\      ~-____-~    _-~    ~-_    ~-_-~    /");
		System.out.println("            ~-_           _-~          ~-_       _-~ ");  
		System.out.println("               ~--______-~                ~-___-~  ");             
	}
}
