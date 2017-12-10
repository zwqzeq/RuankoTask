package com.ruanko;                                    //��

import java.util.Arrays;                              //����Java.util���е�Arrays��

//����һ��MyString��
public class MyString {

	//����
	private char data[];                             //�洢�ַ���ֵ���ַ�����
	private int length;                              //�ַ����е��ַ�����


	/**
	 * �޲ι��췽��
	 * ���ܣ������Ը���ֵ
	 */
	public MyString() {
		this.data=new char[16];
		this.length=0;
	}

	public int length (){
		return length;
	}

	/**
	 * ͨ���ַ����鴴��һ��Mystring���͵��ַ���
	 * @param value�ַ�����ֵ
	 */
	public MyString(char[] value){
		int size=value.length;
		this.length=size;
		this.data=Arrays.copyOf(value,size);
	}

	/**
	 * ͨ��String���͵��ַ�������MyString���͵��ַ���
	 * @param str  String���͵��ַ���
	 */
	public MyString (String str){
		char[]buf=str.toCharArray();                    //toCharArray()�����������ַ���ת��Ϊһ���µ��ַ�����
		int size=buf.length;
		this.length=size;
		this.data=Arrays.copyOf(buf, size);
	}

	/**
	 * ����ָ����������charֵ
	 * @param index charֵ������
	 * @return ���ַ���ָ����������charֵ��ע�⣺��һ��charֵλ������0��
	 */
	public char charAt(int index){
		//�ж������Ƿ�Խ��
		if((index<0)||(index>=length)){
			throw new StringIndexOutOfBoundsException(index);
		}
		return data[index];
	}


	public MyString subString (int beginIndex,int endIndex){
		//�ж��±��Ƿ�Խ��
		if(beginIndex<0){
			throw new StringIndexOutOfBoundsException(beginIndex);
		}
		if(endIndex>length){
			throw  new StringIndexOutOfBoundsException(endIndex);
		}
		if(beginIndex>endIndex){
			throw new StringIndexOutOfBoundsException(endIndex-beginIndex);
		}
		//�����ַ����飬�������ַ�����ֵ
		char buf[]=new char[endIndex=beginIndex];
		buf=Arrays.copyOfRange(data, beginIndex, endIndex);
		//�����ַ����鴴��MyString�����ַ���
		MyString subMystr=new MyString(buf);
		return subMystr;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public MyString concat(MyString str){
		//������ӵ��ַ������ַ�����
		int otherLen=str.length();
		if(otherLen==0){
			return this ;
		}
		//�����ַ����飬�������Ӻ���ַ�����ֵ
		char buf[]=new char[length+otherLen];
		for(int i=0;i<length+otherLen;i++){
			if(i<length){
				buf[i]=data[i];
			}
			else {
				buf[i]=str.charAt(i-length);
			}
		}
		//�����ַ����鴴��MyString���͵��ַ���
		MyString concatMyStr=new MyString(buf);
		return concatMyStr;
	}

	/**
	 * ���ַ�����˳������
	 * @return ������ַ���
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
	 * ���ֵ�˳��Ƚ������ַ���
	 * @param anotherMyString Ҫ�Ƚϵ�MyString
	 * @return ��������ַ������ڴ��ַ����򷵻�ֵ0
	 *         ������ַ������ֵ�˳��С���ַ����������򷵻�ֵС����
	 *         ������ַ������ֵ�˳������ַ����������򷵻�ֵ������
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
		//��������ַ����ĳ��Ⱥ�ֵ
		int sourceLength=this.length;
		char[]source =this.data;
		int targetLength=str.length();
		char[]target=str.toCharArray();
		//������ַ����ĳ��ȱ�ԭ�ַ����ĳ��ȳ��򷵻�-1
		if(targetLength>sourceLength){
			return -1;
		}
		//����ԭ�ַ���������ָ�������ַ���
		for(int i=0;i<=sourceLength-targetLength;i++){
			//����ָ�������ַ���
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
	 * �����ַ���ת��Ϊһ���µ��ַ�����
	 * @return
	 */
	public char[] toCharArray(){
		char result[]=new char[length];
		result=Arrays.copyOf(data, length);
		return result;
	}

	/**
	 * ���ܣ������ַ�����ָ���Ķ���Ƚ�
	 * �㷨��1�ж������ַ����Ƿ���ͬһ������������򷵻�true
	 *     2�ж�ָ���Ķ����Ƿ���MyString���ͣ�����ǽ�һ���Ƚϣ���������򷵻�false
	 */
	public boolean equals (Object anObject){
		if (this==anObject){           //�ж������ַ����Ƿ�Ϊͬһ������
			return true;
		}
		if(anObject instanceof MyString){                //�жϸ��ַ����Ƿ�����MyString����
			MyString anotherMyString=(MyString)anObject;
			int n=length;
			if(n==anotherMyString.length){               //�ж������ַ������ַ������Ƿ���ͬ
				char v1[]=data;
				char v2[]=anotherMyString.data;
				int i=0;
				int j=0;
				while(n!=0){
					if(v1[i++]!=v2[j++]){               //�ж������ַ����ж�Ӧ���ַ��Ƿ���ͬ
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
	 * ���ش˶��������Ѿ���һ���ַ�����
	 * @return �ַ�������
	 */
	public String toString(){
		return new String(data);

	}


}


