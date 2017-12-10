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
	private List <Channel> channelList;//频道信息列表  //注意：这和变量的区别：定义一个变量时，就已经分配了内存空间，可以直接去使用，而列表是作为对象处理的，定义了一个列表，系统并没有为列表分配内存空间，只有new对象时，才分配空间才真正可以用来存放数据
	private List <News> newsList;      //新闻信息列表
	private NewsDao rssDao;            //定义NewsDao接口类型对象用来存放该接口的实现类FileDaoImpl的对象的地址，从而该接口对象就可以调用该接口实现类中的方法和变量

	/**
	 * 无参构造方法
	 * 对变量初始化
	 */
	public RSSService() {
		rssDao = new FileDaoImpl();    //创建NewsDao接口的实现类FileDaoImpl的对象，并将该对象的地址赋给接口类型的对象，此时该接口对象可以访问该接口实现类中实现的方法和变量
	}

	/**
	 * 获得频道信息列表
	 * @return 频道信息列表
	 */
	public List<Channel> getchannelList() {
		if(channelList == null) {                                     //判断列表是否为空
			channelList = new ArrayList<Channel>();                  //创建一个列表对象，列表中存放的对象类型为Channel
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
	 * 方法功能：解析本地XML文件，得到Document文件对象
	 * @param filePath
	 * @return doc
	 */
	public Document load (String filePath) {
		Document doc = null;//定义Document文件对象
		
		//1指定解析器
		SAXBuilder sb = new SAXBuilder(false);
		
		//2打开文件
		File fXml = new File(filePath);
		if(fXml.exists() && fXml.isFile()) {//判断文件是否存在，或是否是文件	
			try {
				//3调用build()
				doc = sb.build(fXml);//加载一个XML文件，得到Document对象
			} catch (JDOMException e) {              //捕获JDOM异常
				e.printStackTrace();                
			} catch (IOException e) {                
				e.printStackTrace();               
			}
		}
		return doc;                       //返回Document文件对象            
	}

	/**
	 * 方法功能：将XML文件中的item元素转化为News对象
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
	 * 解析Document对象，生成News列表
	 * @param doc
	 * @return newsList
	 */
	private List <News> parse(Document doc) {
		newsList = new ArrayList<News>();//创建一个News类型的列表
		News news = null;
		Element root = doc.getRootElement();
		Element eChannel = root.getChild("channel");
		@SuppressWarnings("unchecked")
		List <Element> itemList = eChannel.getChildren("item");
		for(int i = 0; i < itemList.size(); i++) {//遍历列表
			Element item = (Element)itemList.get(i);
			news = itemToNews(item);//调用itemToNews方法
			newsList.add(news);//将新闻news对象添加到newsList新闻列表
		}
		return newsList;//返回新闻列表，列表是由news对象组成，每个news对象包含Title，Link，Author等属性
	}

	/*
	 * 方法功能：调用load方法和parse方法获取新闻内容信息列表
	 * @param filePath
	 * @return newsList
	 */
	public List<News> getNewsList(String filePath) {
		Document doc = load(filePath);//调用load方法返回Document类的对象
		newsList = parse(doc);//调用parse方法返回新闻列表对象
		return newsList;           //返回新闻列表
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
		boolean flag = false;                      //标记文件是否保存成功
		if(rssDao.save(newsList)) {//如果没有在上面的无参构造方法中将NewsDao接口的实现类FileDaoImpl的对象地址赋给NewsDao接口对象，则NewsDao接口的对象不能调用FileDaoImpl类中实现的方法，而只能调用接口中的方法，而接口中的方法是一个抽象方法没有实现任何功能，调用是没有意义的。所以此时会抛出异常
			flag = true;
		}
		return flag;
	}
}





