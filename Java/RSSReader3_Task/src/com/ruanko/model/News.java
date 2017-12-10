package com.ruanko.model;

/**
 * ������Ϣ��
 * @author asus
 *
 */
public class News {

	//��������
	private String title;         //���ű���
	private String link;          //��������
	private String author;        //��������
	private String guid;          //������ַ
	private String category;      //���Ű��
	private String pubDate;       //���ŷ�������
	private String comments;      //����Ҫ��
	private String description;   //��������

	/**
	 * �޲ι��췽��
	 */
	public News(){
		this.title = "";//��ʼ������
		this.link = "";
		this.author = "";
		this.guid = "";
		this.category = "";
		this.pubDate = "";
		this.comments = "";
		this.description = "";
	}

	//��ȡ����ֵ
	public String getTitle() {
		return title;
	}

	//��������ֵ
	public void setTitle(String title) {
		this.title = title;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getGuid() {
		return guid;
	}


	public void setGuid(String guid) {
		this.guid = guid;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getPubDate() {
		return pubDate;
	}


	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * �������ܣ��趨Ҫ�������е���Щ���ֱ��浽�ļ��У������ⲿ����������Ϊһ���ַ���
	 * @param news ����
	 * @return content ���Ϻ�����
	 */
	public String newsToString (News news) {
		String content = null;
		content = "���⣺"
				+ news.getTitle()
				+"\r\n"
				+"���ӣ�"
				+news.getLink()
				+"\r\n"
				+"���ߣ�"
				+news.getAuthor()
				+"\r\n"
				+"����ʱ�䣺"
				+news.getPubDate()
				+"\r\n"
				+"----------------------------------------------------------------\r\n"
				+ news.getDescription()+"\r\n"+"\r\n";
		return content;                  
	}

}
