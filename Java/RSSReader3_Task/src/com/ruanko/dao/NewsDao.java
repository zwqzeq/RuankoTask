package com.ruanko.dao;

import java.util.List;
import com.ruanko.model.News;

/**
 * 接口
 */
public interface NewsDao {
	public boolean save(List<News>newsList);//保存文件方法声明，方法具体的实现由接口的实现类来实现
	
	//用final修饰的叫常量，此处定义了静态常量FILEPATH
    static final String FILEPATH = "NewsFiles/rss.txt";//文件路径
}
