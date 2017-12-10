package com.ruanko;                              

import java.util.Scanner;                       

/**
 * ���������
 * @author zwqabc
 */
public class Start {
	
	//����
	private  AsciiArt art;//����һ��������AsciiArt�Ķ���

	/*
	 * �޲ι��췽��
	 */
	public Start(){
		
	}

	//��ͼ����
	public void draw() {
		System.out.println("----------------------------");
		System.out.println(" ��Ʒ:" + art.getTitle());              
		System.out.println(" ����:" + art.getAuthor());
		art.draw(); 
	}

	//��ӡ�˵�
	public static void printmanu(){
		System.out.println("========��Ʒ�б�========");
		System.out.println("1. Բ��");
		System.out.println("2. ����");
		System.out.println("3. С����");
		System.out.println("4. ��");                   //�˵�
		System.out.println("5. ��");
		System.out.println("6. ��");
		System.out.println("7. ��");
		System.out.println("0. �˳�");
		System.out.println("======================");
		System.out.println("����������ѡ��:");
	}

	//�������
	public static void main(String[] args) {
        Start st = new Start();               //����Start�����
		int selection=0;                           //selection�������������û�����
		Scanner sc=null;
		do{
			printmanu();                          //���ô�ӡ�˵��ķ���
			sc=new Scanner(System.in);    
			selection = sc.nextInt();             //�����û�����
			switch(selection){
			case 1:
				System.out.print("������Բ�İ뾶��"); 
			    int	radius= sc.nextInt();       
			    st.art=new Circle(radius);      //����һ��Բ��Ķ��󣬲���������������(����ַ)����art����������art����ָ��Բ��art.draw()�ͱ�ʾ����Բ�еĻ�ͼ����        
			    st.draw();     //����Start���еĻ�ͼ��������Ϊ��һ���Ѿ�ʹartָ����Բ�����Դ�ʱStart�е�art.getTitle()ָԲ�ı��⣬art.draw()ָԲ�Ļ�ͼ����
				break;
			case 2:
				System.out.print("��������εĳ���");                
				int length=sc.nextInt();                        
				System.out.print("��������εĿ�");
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
				System.out.println("�˳�ϵͳ��");
				break;
			default :
				System.out.print("����������������룡:");
				selection=sc.nextInt();
				break;
			}
		}while(selection != 0);
		
		//�ر�������
		if(sc!=null){
			sc.close();
		}
	}
}
