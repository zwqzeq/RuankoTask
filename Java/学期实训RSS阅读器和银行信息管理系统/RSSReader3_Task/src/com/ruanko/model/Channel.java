package com.ruanko.model;

/**
 * ����Ƶ����Ϣ��
 * @author asus
 */
public class Channel {

	//Ƶ������
	private String name;          //Ƶ��������
	private String filePath;      //����Ƶ���ļ���·��
	private String url;           //����Ƶ���ļ���·��

	//�޲ι��췽��
	public Channel() {
		this.name = "";
		this.filePath = "";
		this.url = "";	
	}

	//��ȡ����ֵ
	public String getName() {
		return name;
	}

	//��������ֵ
	public void setName(String name) {
		this.name = name;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * �������ܣ���ʾ�����б���еĲ˵�
	 */
//	public String toString() {
//		return this.name;
//	}

}
