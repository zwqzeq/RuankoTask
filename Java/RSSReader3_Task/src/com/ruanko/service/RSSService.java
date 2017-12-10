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
	private List <Channel> channelList;//Ƶ����Ϣ�б�  //ע�⣺��ͱ��������𣺶���һ������ʱ�����Ѿ��������ڴ�ռ䣬����ֱ��ȥʹ�ã����б�����Ϊ������ģ�������һ���б�ϵͳ��û��Ϊ�б�����ڴ�ռ䣬ֻ��new����ʱ���ŷ���ռ���������������������
	private List <News> newsList;      //������Ϣ�б�
	private NewsDao rssDao;            //����NewsDao�ӿ����Ͷ���������Ÿýӿڵ�ʵ����FileDaoImpl�Ķ���ĵ�ַ���Ӷ��ýӿڶ���Ϳ��Ե��øýӿ�ʵ�����еķ����ͱ���

	/**
	 * �޲ι��췽��
	 * �Ա�����ʼ��
	 */
	public RSSService() {
		rssDao = new FileDaoImpl();    //����NewsDao�ӿڵ�ʵ����FileDaoImpl�Ķ��󣬲����ö���ĵ�ַ�����ӿ����͵Ķ��󣬴�ʱ�ýӿڶ�����Է��ʸýӿ�ʵ������ʵ�ֵķ����ͱ���
	}

	/**
	 * ���Ƶ����Ϣ�б�
	 * @return Ƶ����Ϣ�б�
	 */
	public List<Channel> getchannelList() {
		if(channelList == null) {                                     //�ж��б��Ƿ�Ϊ��
			channelList = new ArrayList<Channel>();                  //����һ���б�����б��д�ŵĶ�������ΪChannel
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
	 * �������ܣ���������XML�ļ����õ�Document�ļ�����
	 * @param filePath
	 * @return doc
	 */
	public Document load (String filePath) {
		Document doc = null;//����Document�ļ�����
		
		//1ָ��������
		SAXBuilder sb = new SAXBuilder(false);
		
		//2���ļ�
		File fXml = new File(filePath);
		if(fXml.exists() && fXml.isFile()) {//�ж��ļ��Ƿ���ڣ����Ƿ����ļ�	
			try {
				//3����build()
				doc = sb.build(fXml);//����һ��XML�ļ����õ�Document����
			} catch (JDOMException e) {              //����JDOM�쳣
				e.printStackTrace();                
			} catch (IOException e) {                
				e.printStackTrace();               
			}
		}
		return doc;                       //����Document�ļ�����            
	}

	/**
	 * �������ܣ���XML�ļ��е�itemԪ��ת��ΪNews����
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
	 * ����Document��������News�б�
	 * @param doc
	 * @return newsList
	 */
	private List <News> parse(Document doc) {
		newsList = new ArrayList<News>();//����һ��News���͵��б�
		News news = null;
		Element root = doc.getRootElement();
		Element eChannel = root.getChild("channel");
		@SuppressWarnings("unchecked")
		List <Element> itemList = eChannel.getChildren("item");
		for(int i = 0; i < itemList.size(); i++) {//�����б�
			Element item = (Element)itemList.get(i);
			news = itemToNews(item);//����itemToNews����
			newsList.add(news);//������news������ӵ�newsList�����б�
		}
		return newsList;//���������б��б�����news������ɣ�ÿ��news�������Title��Link��Author������
	}

	/*
	 * �������ܣ�����load������parse������ȡ����������Ϣ�б�
	 * @param filePath
	 * @return newsList
	 */
	public List<News> getNewsList(String filePath) {
		Document doc = load(filePath);//����load��������Document��Ķ���
		newsList = parse(doc);//����parse�������������б����
		return newsList;           //���������б�
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
		boolean flag = false;                      //����ļ��Ƿ񱣴�ɹ�
		if(rssDao.save(newsList)) {//���û����������޲ι��췽���н�NewsDao�ӿڵ�ʵ����FileDaoImpl�Ķ����ַ����NewsDao�ӿڶ�����NewsDao�ӿڵĶ����ܵ���FileDaoImpl����ʵ�ֵķ�������ֻ�ܵ��ýӿ��еķ��������ӿ��еķ�����һ�����󷽷�û��ʵ���κι��ܣ�������û������ġ����Դ�ʱ���׳��쳣
			flag = true;
		}
		return flag;
	}
}





