package com.ruanko;                                         //包
/**
 * 定义一个程序入口类
 */
public class App{

	char []ch1={'a','b','c'};                              //定义并初始化字符数组ch1
	char []ch2={'d','e','f'};                              //定义并初始化字符数组ch2

	//第一种方法：连接字符串和逆序输出字符串
	public void strrev(){
		//根据字符数组创建两个String对象
		System.out.println("~~~~~~~~~~~第一种方法~~~~~~~~~~");
		String str1=new String(ch1);
		String str2=new String(ch2);
		String inputStr =str1.concat(str2);                //调用拼接两个字符串的concat()方法
		System.out.println("顺序输出字符串："+inputStr);       //输出拼接后的字符串

		//逆序输出字符串
		System.out.print("逆序输出字符串：");                  //输出提示信息
		int length=inputStr.length();                      //获得字符串的长度
		for(int i=0;i<length;i++){                         //遍历字符串中的字符，逆序输出每个字符
			System.out.print(inputStr.charAt(length-i-1));
		}
		System.out.println();                              //for循环结束后，输出换行


	}

	//第二种方法：连接字符串和逆序输出字符串
	public void mystrrev(){
		System.out.println("~~~~~~~~~~~第二种方法~~~~~~~~~~");
		MyString myStr1=new MyString(ch1);                   //创建一个MyString类的对象
		MyString myStr2=new MyString(ch2);                   //创建另一个MyString类的对象
		MyString inputMyStr =myStr1.concat(myStr2);          //连接两个字符串，然后将新的字符串 赋给inputMyStr           
		System.out.println("顺序输出字符串："+inputMyStr);       
		MyString outputMyStr =inputMyStr.reverse();
		System.out.println("逆序输出字符串："+outputMyStr);  
	}

	/**
	 *程序入口方法 
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		App app =new App();                                  //创建一个APP类的对象
		app.strrev();                                        //调用APP中的S赋给trrev方法
		app.mystrrev();                                      //调用APP中的myStrrev方法
	}
}
