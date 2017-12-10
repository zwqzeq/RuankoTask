/**
 * �������п���û�г��󷽷�������һ���������󷽷���ע�⣺�ڳ�������ֻ������������
 *           ����������ʵ�ֳ��󷽷�����������һ�������࣬��������ʵ�ֳ������еĳ��󷽷���
 * �����ࣺ����Ӣ�ĵ���abstract
 * �����಻�ܴ����������Ҫ�봴�����󣬱���ͨ������̳к�����������������
 * �������п����о���ĳ�Ա��������ͨ�������ͳ�Ա����
 * һ����������г��󷽷�����������������Ϊ������
 */

/**
 * ���󷽷���ֻ�з���������û�з�����
 * ���󷽷��ɼ̳иó��󷽷�������ȥʵ��������Ӧ��������ȥ���ǳ��󷽷�
 */


package com.ruanko;  

//����һ��������AsciiArt
public abstract class AsciiArt {

	protected String  Title;                               //����
	public String  Author;                                //����
	
	/**
	 * �޲ι��췽��
	 */
	public AsciiArt() {

	}
	
	/*
	 * �вι��췽��
	 */
	public AsciiArt(String Title,String  Author) {
		this.Title=Title;
		this.Author=Author;

	}

	//��ȡ����
	public String getTitle() {
		return Title;
	}

	//���ñ���
	public void setTitle(String title) {
		Title = title;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	//���󷽷�����ͼ��
	public abstract void draw();                          

}

