package com.ruanko.service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;

import com.ruanko.model.Channel;
import com.ruanko.model.News;

/**
 * RSS更新类
 */
public class UpdateThread extends Thread {

	// 延迟时间(5分钟)
	private final static int DELAY_TIME = 300 * 1000;
	// 缓存大小（3*64KB）
	private final static int BUFFER_SIZE = 3*65536;
	// 频道信息列表
	List<Channel> channelList;
	RSSService rssService;                         //业务逻辑类对象
	private List<News> newsList;                    //新闻内容列表


	// 无参构造器
	public UpdateThread() {
		// 创建RSSService 对象
		RSSService rssService = new RSSService();
		channelList = rssService.getchannelList();
	}

	// 线程运行方法
	public void run() {
		while (true) {
			// 更新RSS
			System.out.println("正在更新............." + new Date());
			for (int i = 0; i < channelList.size(); i++) {
				Channel channel = channelList.get(i);
				System.out.println("更新：" + channel.getName());
				update(channel.getUrl(), channel.getFilePath());
				System.out.println(channel.getUrl()+"    "+channel.getFilePath());
				//System.out.println("！！！更新失败！！！");
			}
			System.out.println("更新完毕............." + new Date());
			// 暂停5分钟（每五分钟更新一次）
			try {
				sleep(DELAY_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	// 网上下载RSS文件保存
	private void update(String urlPath, String filePath) {
		// 创建HTTP连接
		HttpURLConnection httpConn;
		try {
			URL url = new URL(urlPath);
			//打开链接
			httpConn = (HttpURLConnection) url.openConnection();
			// 判断HTTP连接是否成功
			int responseCode = httpConn.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// 在HTTP文件上检查是否有需要更新的RSS文件
		File file = new File(filePath);
		if (hasNewRSS(httpConn, file)) {
			System.out.println("现在更新...........");
			// 下载要更新的RSS文件到缓冲区中
			ByteBuffer buffer = download(httpConn);
			// 将RSS文件内容保存到文件中
			if (buffer != null) {
				saveAs(buffer, file);
			}
		} else {
			System.out.println("当前文件内容为最新内容");
		}

	}

	// 下载文件到缓冲区
	private ByteBuffer download(HttpURLConnection httpConn) {
		// 创建3*64KB缓存区
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		try {

			// 获取数据
			InputStream in = httpConn.getInputStream();
			// 将RSS文件下载到缓存区
			byte[] contentInBytes=new byte[BUFFER_SIZE];
			int len;
			while((len=in.read(contentInBytes))!=-1){
				buffer.put(contentInBytes,0,len);
			}
			BufferedReader br1 = new BufferedReader(new InputStreamReader(in,"utf-8"));	
			String line = "";
			while((line=br1.readLine()) != null) {
				System.out.println(line);
			}	

			// 文件缓存完成
			buffer.flip();
			// 关闭流
			in.close();
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return buffer;
		}
	}

	// 将缓冲区的文件保存到文件中
	private synchronized void saveAs(ByteBuffer buffer, File file) {
		try {
			FileOutputStream fout = new FileOutputStream(file, false);
			FileChannel channel=fout.getChannel();
			channel.write(buffer);
			// 关闭流
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 判断RSS文件是否更新
	private boolean hasNewRSS(HttpURLConnection httpConn, File file) {
		// 获取服务器最后修改时间
		long current = System.currentTimeMillis();
		long httpLastModified = httpConn.getHeaderFieldDate("Last-Modified",
				current);
		// 获取本地文件最后修改时间
		long fileLastModified = file.lastModified();
		// 判断两种时间是否相等
		if (httpLastModified > fileLastModified) {
			// 如果服务器最后修改时间大于本地文件最后修改时间，则需要更新，返回 ture
			return true;
		} else {
			return false;
		}
	

	
	}
}
