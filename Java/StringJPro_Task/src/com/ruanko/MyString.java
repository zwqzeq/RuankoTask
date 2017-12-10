package com.ruanko;                                    //包

import java.util.Arrays;                              //导入Java.util包中的Arrays类

//定义一个MyString类
public class MyString {

	//属性
	private char data[];                             //存储字符串值的字符数组
	private int length;                              //字符串中的字符个数


	/**
	 * 无参构造方法
	 * 功能：给属性赋初值
	 */
	public MyString() {
		this.data=new char[16];
		this.length=0;
	}

	public int length (){
		return length;
	}

	/**
	 * 通过字符数组创建一个Mystring类型的字符串
	 * @param value字符串的值
	 */
	public MyString(char[] value){
		int size=value.length;
		this.length=size;
		this.data=Arrays.copyOf(value,size);
	}

	/**
	 * 通过String类型的字符串创建MyString类型的字符串
	 * @param str  String类型的字符串
	 */
	public MyString (String str){
		char[]buf=str.toCharArray();                    //toCharArray()方法：将此字符串转换为一个新的字符数组
		int size=buf.length;
		this.length=size;
		this.data=Arrays.copyOf(buf, size);
	}

	/**
	 * 返回指定索引处的char值
	 * @param index char值的索引
	 * @return 此字符串指定索引处的char值。注意：第一个char值位于索引0处
	 */
	public char charAt(int index){
		//判断索引是否越界
		if((index<0)||(index>=length)){
			throw new StringIndexOutOfBoundsException(index);
		}
		return data[index];
	}


	public MyString subString (int beginIndex,int endIndex){
		//判断下标是否越界
		if(beginIndex<0){
			throw new StringIndexOutOfBoundsException(beginIndex);
		}
		if(endIndex>length){
			throw  new StringIndexOutOfBoundsException(endIndex);
		}
		if(beginIndex>endIndex){
			throw new StringIndexOutOfBoundsException(endIndex-beginIndex);
		}
		//定义字符数组，保存子字符串的值
		char buf[]=new char[endIndex=beginIndex];
		buf=Arrays.copyOfRange(data, beginIndex, endIndex);
		//根据字符数组创建MyString类型字符串
		MyString subMystr=new MyString(buf);
		return subMystr;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public MyString concat(MyString str){
		//获得连接的字符串的字符个数
		int otherLen=str.length();
		if(otherLen==0){
			return this ;
		}
		//定义字符数组，保存连接后的字符串的值
		char buf[]=new char[length+otherLen];
		for(int i=0;i<length+otherLen;i++){
			if(i<length){
				buf[i]=data[i];
			}
			else {
				buf[i]=str.charAt(i-length);
			}
		}
		//根据字符数组创建MyString类型的字符串
		MyString concatMyStr=new MyString(buf);
		return concatMyStr;
	}

	/**
	 * 将字符串的顺序逆序
	 * @return 逆序的字符串
	 */
	public MyString reverse(){
		int length=data.length;
		char []buf=new char[length];
		for(int i=0;i<length;i++){
			buf[i]=data[length-i-1];
		}
		MyString reverseMyString=new MyString(buf);
		return reverseMyString;
	}

	/**
	 * 按字典顺序比较两个字符串
	 * @param anotherMyString 要比较的MyString
	 * @return 如果参数字符串等于此字符串则返回值0
	 *         如果此字符串按字典顺序小于字符串参数，则返回值小于零
	 *         如果此字符串按字典顺序大于字符串参数，则返回值大于零
	 */
	public int compareTo(MyString anotherMyString){
		int len1=length;
		int len2=anotherMyString.length();
		int n=Math.min(len1, len2);
		char v1[]=data;
		char v2[]=anotherMyString.toCharArray();

		int i=0;
		while(i<n){
			char c1=v1[i];
			char c2=v2[i];
			if(c1!=c2){
				return c1=c2;
			}
			i++;
		}
		return len1=len2;
	}


	public int indexOf(MyString str){
		//获得两个字符串的长度和值
		int sourceLength=this.length;
		char[]source =this.data;
		int targetLength=str.length();
		char[]target=str.toCharArray();
		//如果子字符串的长度比原字符串的长度长则返回-1
		if(targetLength>sourceLength){
			return -1;
		}
		//遍历原字符串，查找指定的子字符串
		for(int i=0;i<=sourceLength-targetLength;i++){
			//遍历指定的子字符串
			for(int j=0;j<targetLength;j++){
				if(source[i+j]==target[j]){
					if(j==targetLength-1){
						return i;
					}
				}else {
					break;
				}
			}
		}
		return -1;
	}

	/**
	 * 将此字符串转换为一个新的字符数组
	 * @return
	 */
	public char[] toCharArray(){
		char result[]=new char[length];
		result=Arrays.copyOf(data, length);
		return result;
	}

	/**
	 * 功能：将此字符串与指定的对象比较
	 * 算法：1判断两个字符串是否是同一个对象，如果是则返回true
	 *     2判断指定的对象是否是MyString类型，如果是进一步比较，如果不是则返回false
	 */
	public boolean equals (Object anObject){
		if (this==anObject){           //判断两个字符串是否为同一个对象
			return true;
		}
		if(anObject instanceof MyString){                //判断该字符串是否属于MyString类型
			MyString anotherMyString=(MyString)anObject;
			int n=length;
			if(n==anotherMyString.length){               //判断两个字符串中字符个数是否相同
				char v1[]=data;
				char v2[]=anotherMyString.data;
				int i=0;
				int j=0;
				while(n!=0){
					if(v1[i++]!=v2[j++]){               //判断两个字符串中对应的字符是否相同
						return false;
					}
					return true;
				}
			}
			return false;
		}
		return false;
	}

	/**
	 * 返回此对象本身（它已经是一个字符串）
	 * @return 字符串本身
	 */
	public String toString(){
		return new String(data);

	}


}


