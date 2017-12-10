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
 *  业务逻辑类
 */
public class RSSService {
	private List <Channel> channelList;//频道信息列表
	private List <News> newsList;      //新闻信息列表
	private NewsDao rssDao;            //定义数据访问层的接口NewsDao的引用作为RSSService类的成员变量

	/**
	 * 无参构造方法
	 */
	public RSSService() {
		rssDao = new FileDaoImpl();    //创建FileDaoImpl类型的数据访问层对象

	}

	/**
	 * 获得频道信息列表
	 * @return 频道信息列表
	 */
	public List<Channel> getchannelList() {
		if(channelList == null) {                                     //判断列表是否为空
			channelList = new ArrayList<Channel>();                  //创建一个列表，列表中对象类型为Channel
			Channel channel1 = new Channel();                        //创建第一个频道
			channel1.setName("腾讯网 - 军事频道");                      //第一个频道名字
			channel1.setFilePath("NewsFiles/rss_milit.xml");        //第一个频道地址
			channel1.setUrl("http://news.qq.com/milite/rss_milit.xml");

			Channel channel2 = new Channel();
			channel2.setName("腾讯网 - 国际新闻");
			channel2.setFilePath("NewsFiles/rss_newswj.xml");
			channel2.setUrl("http://news.qq.com/newsgj/rss_newswj.xml");

			Channel channel3 = new Channel();
			channel3.setName("新浪 - 体育新闻");
			channel3.setFilePath("NewsFiles/sports.xml");
			channel3.setUrl("http://rss.sina.com.cn/news/allnews/sports.xml");

			Channel channel4 = new Channel();
			channel4.setName("新浪 - 社会新闻");
			channel4.setFilePath("NewsFiles/focus15.xml");		
			channel4.setUrl("http://rss.sina.com.cn/news/society/focus15.xml");

			//将频道对象添加到列表中
			channelList.add(channel1);                    
			channelList.add(channel2);
			channelList.add(channel3);
			channelList.add(channel4);
		}
		return channelList;
	}

	/**
	 * 方法功能：解析本地文件
	 * @param filePath
	 * @return doc
	 */
	public Document load (String filePath) {
		Document doc = null;	
		//1指定解析器
		SAXBuilder sb = new SAXBuilder(false);
		//2创建文件对象
		File fXml = new File(filePath);
		if(fXml.exists() && fXml.isFile()) {//判断文件是否存在，或是否是文件	
			try {
				//3调用build()
				doc = sb.build(fXml);//加载一个XML文件，得到Document对象
			} catch (JDOMException e) {              //捕获JDOM异常
				e.printStackTrace();                 //输出异常信息
			} catch (IOException e) {                //捕获输入输出异常
				e.printStackTrace();                 //输出异常信息
			}
		}
		return doc;

	}

	/**
	 * 方法功能：将RSS文件中的item元素转化为News对象
	 * @param item
	 * @return news
	 */
	private News itemToNews(Element item) {
		News news = new News();//创建News类的对象

		//获得节点内容
		String title = item.getChildText("title").trim();
		String link = item.getChildText("link");
		String author = item.getChildText("author");
		String guid = item.getChildText("guid");
		String category = item.getChildText("category");
		String pubDate = item.getChildText("pubDate");
		String comments = item.getChildText("comments");
		String description = item.getChildText("description").trim();

		//设置节点内容
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
	 * 解析内存中的RSS文件，生成新闻对象News列表
	 * @param doc
	 * @return newsList
	 */
	private List<News>parse(Document doc) {
		List<News>newsList = new ArrayList<News>();//创建一个News类型的列表
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
	 * 方法功能：调用load方法和parse方法获取新闻内容信息列表
	 * @param filePath
	 * @return newsList
	 */
	public List<News> getNewsList(String filePath) {
		Document doc = load(filePath);
		newsList = parse(doc);
		return newsList;
	}

	/**
	 * 方法功能：设定要保存的新闻中的那些部分到文件中
	 * @param news
	 * @return content
	 */
	public String newsToString (News news) {
		String content = null;
		content = "标题："
				+ news.getTitle()
				+"\r\n"
				+"链接："
				+news.getLink()
				+"\r\n"
				+"作者："
				+news.getAuthor()
				+"\r\n"
				+"发布时间："
				+news.getPubDate()				
				+"\r\n"
				+"----------------------------------------------------------------\r\n"
				+ news.getDescription()+"\r\n"+"\r\n";
		return content;
	}

	/**
	 * 保存文件
	 * @param newsList
	 * @return flag
	 */
	public boolean save(List<News>newsList) {
		boolean flag = false;
		if(rssDao.save(newsList)) {                //调用数据访问层的方法保存新闻内容
			flag = true;
		}
		return flag;
	}
}