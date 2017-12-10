package com.ruanko;                                                //包
//定义一个SimpleShift类
public class SimpleShift {
	//程序入口方法
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

		System.out.println("~~~~简单移位密码~~~~");                  //此程序功能说明    
		char [] cleartext ={'N','Q','T','A','J','O','F','A','F'};//定义一个数组（并初始化）密文
		char []ciphertext=new char[cleartext.length];            //定义一个数组（长度与明文数组长度相等）用来存放密文，
		System.out.print("密文：");
		for(int i=0;i<cleartext.length;i++){                     //遍历密文数组，输出密文数组元素值
			System.out.print(cleartext[i]);
		}
		System.out.println();                                    //输出密文数组所有元素值后，换行
		System.out.print("明文：");
		for(int i=0;i<cleartext.length;i++){                     //遍历明文数组
			int index = cleartext[i]-64;                         //index的值为密文中字母的序号（序号从1~26如A序号为1，z序号为26）
			index=(index-5+26)%26;                               //index的值为明文中字母的序号（序号从1~26如A序号为1，z序号为26）
			ciphertext[i]=(char)(index+64);                      //(char)(index+64)表示将此处（1~26的序号）字母对应到ASCII表中的字母序号
			System.out.print(ciphertext[i]);                     //输出明文数组的值

		}

	}

}
