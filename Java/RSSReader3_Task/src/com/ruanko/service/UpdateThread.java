package com.ruanko.service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;

import com.ruanko.model.Channel;

/**
 * RSS更新类
 */
public class UpdateThread extends Thread {

	// 延迟时间(5分钟)
	private final static int DELAY_TIME = 300 * 1000; //常量
	
	// 缓存大小（3*64KB）
	private final static int BUFFER_SIZE = 3*65536;  //常量
	
	// 频道信息列表
	List <Channel> channelList;
	RSSService rssService;                           //定义业务逻辑类的对象
	
	// 无参构造方法
	public UpdateThread() {
		RSSService rssService = new RSSService();         //创建业务逻辑类对象
		channelList = rssService.getchannelList();       //调用 业务逻辑类RSSService中的getchannelList方法返回值为频道列表
	}

	// 线程运行方法
	public void run() {
		while (true) {
			
			// 更新RSS
			System.out.println("正在更新............." + new Date());
			for (int i = 0; i < channelList.size(); i++) {
				Channel channel = (Channel)channelList.get(i);
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

	// 判断RSS文件是否更新
	private boolean hasNewRSS(HttpURLConnection httpConn, File file) {
		
		// 获取服务器最后修改时间
		long current = System.currentTimeMillis();//系统当前时间，毫秒数
		long httpLastModified = httpConn.getHeaderFieldDate("Last-Modified",
				current);//public long getHeaderFieldDate(String name,long Default)：name - 头字段的名称。Default - 默认值。 
		//有些连接类型（例如 http-ng）具有预解析头，所以存在这种形式的 getHeaderField。用于该类型的连接可重写此方法和缩短解析过程。
		//返回解析为日期的指定字段的值。结果为指定字段表示的距离格林威治标准时间 1970 年 1 月 1 日的毫秒数。 字段的值，解析为日期。如果该字段缺少或有错误，则返回 Default 参数的值。
		
		
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
	
	// 网上下载RSS文件保存到路径为filePath的本地文件中
	private void update(String urlPath, String filePath) {
		
		// 创建HTTP连接
		HttpURLConnection httpConn;
		try {
			URL url = new URL(urlPath);//根据网络路径urlPath创建URL对象
			
			//打开链接
			httpConn = (HttpURLConnection) url.openConnection();//通过URL对象调用URL类中的openConnection方法打开连接
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
		File file = new File(filePath);//打开文件
		if (hasNewRSS(httpConn, file)) {
			System.out.println("现在更新...........");
			
			// 先下载要更新的RSS文件到缓冲区中
			ByteBuffer buffer = download(httpConn);
			
			// 再将缓冲区中的RSS文件内容保存到本地文件中
			if (buffer != null) {
				saveAs(buffer, file);
			}
		} else {          //缓冲区内容为空
			System.out.println("当前文件内容为最新内容");
		}

	}

	// 下载文件到缓冲区
	private ByteBuffer download(HttpURLConnection httpConn) {
		// 创建3*64KB字节缓存区
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		try {

			// 获取输入流中的数据
			InputStream in = httpConn.getInputStream();
			//将RSS文件下载到缓存区
			byte[] contentInBytes=new byte[BUFFER_SIZE];//创建一个数组用来存放数据
			int len;
			while((len=in.read(contentInBytes))!=-1){
				buffer.put(contentInBytes,0,len);//将contentInBytes数组中的内容保存到缓冲区
			}
			
			//将RSS文件显示到控制台
			BufferedReader br1 = new BufferedReader(new InputStreamReader(in,"utf-8"));//创建一个读字节流，以utf-8编码方式，并转化为读缓存流	
			String line = "";
			while((line=br1.readLine()) != null) {
				System.out.println(line);//将输入流中的文件显示到控制台
			}	

			// 文件缓存完成
			buffer.flip();
			
			// 关闭流
			in.close();
			return buffer;                  //返回缓存区
		} catch (IOException e) {
			e.printStackTrace();
			return buffer;
		}
	}

	// 将缓冲区的文件保存到文件中
	private synchronized void saveAs(ByteBuffer buffer, File file) {
		try {
			FileOutputStream fout = new FileOutputStream(file, false);//false表示从文件开头写入数据，这样每次更新的数据会覆盖之前的旧数据
			FileChannel channel=fout.getChannel();//public FileChannel getChannel()返回与此文件输出流有关的唯一 FileChannel 对象。
			channel.write(buffer);
			
			// 关闭流
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
