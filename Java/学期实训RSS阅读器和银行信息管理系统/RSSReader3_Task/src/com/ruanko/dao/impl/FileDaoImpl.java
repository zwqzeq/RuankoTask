package com.ruanko.dao.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.ruanko.dao.NewsDao;
import com.ruanko.model.News;

/** 
 * FileDaoImpl��ʵ��NewsDao�ӿ�
 */
public class FileDaoImpl implements NewsDao{

	//��final���εĽг������˴������˾�̬����FILEPATH
	private static final String FILEPATH = "NewsFiles/rss.txt";//�ļ�·��

	/**
	 * ʵ��save��������
	 * @return flag (�������ͱ���)����ɹ��ͷ���true����Ϊfalse
	 */
	public boolean save(List <News> newsList) {                //�����ļ�
		boolean flag = true;
		File file = new File(FILEPATH);                       //���ļ�
		FileWriter fw = null;                                 //���������FileWriter
		PrintWriter pw = null;                                //���������PrintWriter
		try {
			fw = new FileWriter(file,true);                   //true��ʾ���ļ�ĩβ��ʼд
			pw = new PrintWriter(fw);
			for(int i = 0; i < newsList.size(); i++) {
				News news = (News)newsList.get(i);
				String new1=news.newsToString(news);         //����newsToString()����
				pw.println(new1);                            //�����������ݵ��ļ���	
			}
			pw.flush();                                      //ˢ�»�����
		} catch (Exception e) {
		} finally {                                          //�ر�����˳��ʹ�����˳���෴
			try {
				pw.close();                                  //�ر���
				fw.close();                                  //�ر���
			} catch (IOException e) {
				e.printStackTrace();                         //�׳��쳣��Ϣ
			}	
		}
		return flag;                                         //���ز������͵�ֵ
	}
}
