package com.ruanko.dao;

import java.util.List;
import com.ruanko.model.News;

/**
 * �ӿ�
 */
public interface NewsDao {
	public boolean save(List<News>newsList);//�����ļ��������������������ʵ���ɽӿڵ�ʵ������ʵ��
	
	//��final���εĽг������˴������˾�̬����FILEPATH
    static final String FILEPATH = "NewsFiles/rss.txt";//�ļ�·��
}
