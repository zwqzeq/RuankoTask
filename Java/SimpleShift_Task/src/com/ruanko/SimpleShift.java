package com.ruanko;                                                //��
//����һ��SimpleShift��
public class SimpleShift {
	//������ڷ���
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

		System.out.println("~~~~����λ����~~~~");                  //�˳�����˵��    
		char [] cleartext ={'N','Q','T','A','J','O','F','A','F'};//����һ�����飨����ʼ��������
		char []ciphertext=new char[cleartext.length];            //����һ�����飨�������������鳤����ȣ�����������ģ�
		System.out.print("���ģ�");
		for(int i=0;i<cleartext.length;i++){                     //�����������飬�����������Ԫ��ֵ
			System.out.print(cleartext[i]);
		}
		System.out.println();                                    //���������������Ԫ��ֵ�󣬻���
		System.out.print("���ģ�");
		for(int i=0;i<cleartext.length;i++){                     //������������
			int index = cleartext[i]-64;                         //index��ֵΪ��������ĸ����ţ���Ŵ�1~26��A���Ϊ1��z���Ϊ26��
			index=(index-5+26)%26;                               //index��ֵΪ��������ĸ����ţ���Ŵ�1~26��A���Ϊ1��z���Ϊ26��
			ciphertext[i]=(char)(index+64);                      //(char)(index+64)��ʾ���˴���1~26����ţ���ĸ��Ӧ��ASCII���е���ĸ���
			System.out.print(ciphertext[i]);                     //������������ֵ

		}

	}

}
