package com.ruanko.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import com.ruanko.dao.NewsDao;
import com.ruanko.dao.impl.FileDaoImpl;
import com.ruanko.model.Channel;
import com.ruanko.model.News;

/**
 *  ҵ���߼���
 */
public class RSSService {
	private List <Channel> channelList;//Ƶ����Ϣ�б�
	private List <News> newsList;      //������Ϣ�б�
	private NewsDao rssDao;            //�������ݷ��ʲ�Ľӿ�NewsDao��������ΪRSSService��ĳ�Ա����

	/**
	 * �޲ι��췽��
	 */
	public RSSService() {
		rssDao = new FileDaoImpl();    //����FileDaoImpl���͵����ݷ��ʲ����

	}

	/**
	 * ���Ƶ����Ϣ�б�
	 * @return Ƶ����Ϣ�б�
	 */
	public List<Channel> getchannelList() {
		if(channelList == null) {                                     //�ж��б��Ƿ�Ϊ��
			channelList = new ArrayList<Channel>();                  //����һ���б��б��ж�������ΪChannel
			Channel channel1 = new Channel();                        //������һ��Ƶ��
			channel1.setName("��Ѷ�� - ����Ƶ��");                      //��һ��Ƶ������
			channel1.setFilePath("NewsFiles/rss_milit.xml");        //��һ��Ƶ����ַ
			channel1.setUrl("http://news.qq.com/milite/rss_milit.xml");

			Channel channel2 = new Channel();
			channel2.setName("��Ѷ�� - ��������");
			channel2.setFilePath("NewsFiles/rss_newswj.xml");
			channel2.setUrl("http://news.qq.com/newsgj/rss_newswj.xml");

			Channel channel3 = new Channel();
			channel3.setName("���� - ��������");
			channel3.setFilePath("NewsFiles/sports.xml");
			channel3.setUrl("http://rss.sina.com.cn/news/allnews/sports.xml");

			Channel channel4 = new Channel();
			channel4.setName("���� - �������");
			channel4.setFilePath("NewsFiles/focus15.xml");		
			channel4.setUrl("http://rss.sina.com.cn/news/society/focus15.xml");

			//��Ƶ��������ӵ��б���
			channelList.add(channel1);                    
			channelList.add(channel2);
			channelList.add(channel3);
			channelList.add(channel4);
		}
		return channelList;
	}

	/**
	 * �������ܣ����������ļ�
	 * @param filePath
	 * @return doc
	 */
	public Document load (String filePath) {
		Document doc = null;	
		//1ָ��������
		SAXBuilder sb = new SAXBuilder(false);
		//2�����ļ�����
		File fXml = new File(filePath);
		if(fXml.exists() && fXml.isFile()) {//�ж��ļ��Ƿ���ڣ����Ƿ����ļ�	
			try {
				//3����build()
				doc = sb.build(fXml);//����һ��XML�ļ����õ�Document����
			} catch (JDOMException e) {              //����JDOM�쳣
				e.printStackTrace();                 //����쳣��Ϣ
			} catch (IOException e) {                //������������쳣
				e.printStackTrace();                 //����쳣��Ϣ
			}
		}
		return doc;

	}

	/**
	 * �������ܣ���RSS�ļ��е�itemԪ��ת��ΪNews����
	 * @param item
	 * @return news
	 */
	private News itemToNews(Element item) {
		News news = new News();//����News��Ķ���

		//��ýڵ�����
		String title = item.getChildText("title").trim();
		String link = item.getChildText("link");
		String author = item.getChildText("author");
		String guid = item.getChildText("guid");
		String category = item.getChildText("category");
		String pubDate = item.getChildText("pubDate");
		String comments = item.getChildText("comments");
		String description = item.getChildText("description").trim();

		//���ýڵ�����
		news.setTitle(title);
		news.setLink(link);
		news.setAuthor(author);
		news.setGuid(guid);
		news.setCategory(category);
		news.setPubDate(pubDate);
		news.setComments(comments);
		news.setDescription(description);
		return news;
	}

	/**
	 * �����ڴ��е�RSS�ļ����������Ŷ���News�б�
	 * @param doc
	 * @return newsList
	 */
	private List<News>parse(Document doc) {
		List<News>newsList = new ArrayList<News>();//����һ��News���͵��б�
		News news = null;//
		Element root = doc.getRootElement();
		Element eChannel = root.getChild("channel");
		List<Element>itemList = eChannel.getChildren("item");
		for(int i = 0; i<itemList.size(); i++) {
			Element item = itemList.get(i);
			news = itemToNews(item);
			newsList.add(news);
		}
		return newsList;
	}

	/**
	 * �������ܣ�����load������parse������ȡ����������Ϣ�б�
	 * @param filePath
	 * @return newsList
	 */
	public List<News> getNewsList(String filePath) {
		Document doc = load(filePath);
		newsList = parse(doc);
		return newsList;
	}

	/**
	 * �������ܣ��趨Ҫ����������е���Щ���ֵ��ļ���
	 * @param news
	 * @return content
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

	/**
	 * �����ļ�
	 * @param newsList
	 * @return flag
	 */
	public boolean save(List<News>newsList) {
		boolean flag = false;
		if(rssDao.save(newsList)) {                //�������ݷ��ʲ�ķ���������������
			flag = true;
		}
		return flag;
	}
}