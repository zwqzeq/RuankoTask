package com.ruanko.dao;

import java.util.List;
import com.ruanko.model.News;

/**
 * 接口
 */
public interface NewsDao {
	public boolean save(List<News>newsList);//保存文件方法声明，方法具体的实现由接口的实现类来实现

}
