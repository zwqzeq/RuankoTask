package com.lp;                                                 //����Ϊcom.lp
/**
 * 100ԪǮ��100ֻ����С��һֻ0.5������һֻ1��ĸ��һֻ2Ԫ����С������ĸ�������м��ֿ��ܣ�
 * @author asus
 *
 */
public class Application {                                      //����ΪApplication

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		int count=0;                                        //����countΪ�����򼦵Ŀ����������                    
		System.out.println("�ټ���Ǯ����");
		for(int chick=0;chick<=100;chick++){               //����chickΪС����(��Ϊ�ܹ�Ҫ��һ��ֻ��������С���������100ֻ��ѭ���������Ϊ100��������Ϊ200)
			for(int cock=0;cock<=100;cock++){              //����cockΪ������
				for(int hen=0;hen<=50;hen++){              //����henΪĸ����
					if((chick+cock+hen==100)&&(0.5*chick+cock+2*hen==100)){//������ּ��ܺ�Ϊ100�������򼦵�Ǯ��Ϊ100
						System.out.println("С��"+chick+"ֻ,����"+cock+"ֻ,ĸ��"+hen+"ֻ");//����򼦵ĸ������
						count=count+1;//�򼦵Ŀ��������һ
					}
				}
			}
		}
		System.out.println("����"+count+"�ַ�������");
	}

}
