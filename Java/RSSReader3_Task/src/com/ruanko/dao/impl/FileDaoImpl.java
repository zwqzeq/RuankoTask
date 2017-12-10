package com.ruanko.dao.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.ruanko.dao.NewsDao;
import com.ruanko.model.News;

/** 
 * FileDaoImpl类实现NewsDao接口
 */
public class FileDaoImpl implements NewsDao{

	/**
	 * 实现save（）方法
	 * @return flag (布尔类型变量)保存成功就返回true否则为false
	 */
	public boolean save(List <News> newsList) {                //保存文件
		boolean flag = true;                                   //标记文件是否保存成功
		File file = new File(NewsDao.FILEPATH);               //打开文件
		FileWriter fw = null;                                 //声明输出流FileWriter
		PrintWriter pw = null;                                //声明输出流PrintWriter
		try {
			fw = new FileWriter(file,true);                   //true表示从文件末尾开始写
			pw = new PrintWriter(fw);
			for(int i = 0; i < newsList.size(); i++) {
				News news = (News)newsList.get(i);
				String newstring = news.newsToString(news);         //调用newsToString()方法
				pw.println(newstring);                            //保存新闻内容到文件中	
			}
			pw.flush();                                      //刷新缓冲区
		} catch (Exception e) {
			e.printStackTrace();                         
			flag=false;
		} finally {                                          //关闭流的顺序和打开流的顺序相反
			try {
				pw.close();                                  //关闭流
				fw.close();                                  //关闭流
			} catch (IOException e) {
				e.printStackTrace();                         
			}	
		}
		return flag;                                         //返回布尔类型的值
	}
}
