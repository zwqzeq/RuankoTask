package com.ruanko.model;

public class Channel {

	private String name;          //频道的名字
	private String filePath;      //本地频道文件的路径
	private String url;           //网络频道文件的路径
	
	//无参构造方法
	public Channel() {
		this.name = "";
		this.filePath = "";
		this.url = "";	
	}

	public String getName() {
		return name;
	}

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
	 * 方法功能：显示下拉列表框中的菜单
	 */
	public String toString() {
		return this.name;
	}
	
	
	
	
}
