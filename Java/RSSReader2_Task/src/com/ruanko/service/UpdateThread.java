package com.ruanko.service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;
import com.ruanko.model.Channel;


/**
 * 1.判断RSS文件是否有更新
 * 服务器文件：Last-Modified
 * 本地文件：LastModified
 * 
 * 2.将ＲＳＳ文件下载到缓存
 * HTTP协议：HttpURLConnection
 * 判断超时：setConnectionTimeOut(int timeOut)
 * 缓冲区：ByteBuffer + FileOutputStream + FileChannel
 * 
 * 3.将缓存中的数据保存到文件中
 * 文件流：FileOutStream
 * 问件通道：FileChannel
 * 
 */


/**
 *  线程类
 *  定时下载网络上更新的RSS文件
 *  步骤：1-下载网络RSS到本地
 *      2-创建数据缓冲区
 *      3-线程定时更新
 *      4-检查更新
 */
public class UpdateThread extends Thread{

	private final static int TIMEOUT = 5*1000;           //5秒
	private final static int DELAY_TIME = 300 * 1000;   //更新新闻的时间间隔为5分钟一次
	private final static int BUFFER_SIZE = 65536;    //64kb

	private List <Channel> channelList;//频道信息列表

	/**
	 * 无参构造方法
	 */
	public UpdateThread() {
		RSSService  rssService= new RSSService();
		channelList = rssService.getchannelList();
	}



	/**
	 * 重写thread类的run()方法
	 */
	public void run () { 
		
		//死循环，每隔5分钟更新一次RSS文件
		while (true) {
			System.out.println("正在更新.............."+new Date());
			
			//更新RSS文件
			for(int i = 0; i < channelList.size(); i++) {
				Channel channel = channelList.get(i);
				System.out.println("更新："+channel.getName());
				try {
					update(channel.getUrl(),channel.getFilePath());
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			System.out.println("更新完毕.............."+new Date());

			//让线程休眠5分钟
			try {
				sleep(DELAY_TIME);//pause 5 minutes
			} catch (InterruptedException e) {//捕获异常
				e.printStackTrace();//输出异常信息
			}

		}


	}


	/**
	 *  HttpURLConnection的connect()函数，实际上只是建立了一个与服务器的tcp连接，并没有实际发送http请求。
	 *  无论是post还是get，http请求实际上直到HttpURLConnection的getInputStream()这个函数里面才正式发送出去
	 *  而对outputStream的写操作，又必须要在inputStream的读操作之前。 
	 * 
	 * 
	 * 
	 * 
	 * HttpURLConnection对象不能直接构造，需要通过URL.openConnection()来获得
	 * 如果有新的RSS文件，则更新该RSS文件
	 * 方法功能：从网络地址下载RSS文件，保存到filePath路径下
	 * @param urlPath RSS文件的网络路径
	 * @param filePath RSS 文件本地保存路径
	 * @throws IOException 
	 */
	public static void update(String urlPath,String filePath) throws IOException {
		HttpURLConnection httpConn;
		try {
			URL url = new URL(urlPath);//创建URL对象
			httpConn = (HttpURLConnection)url.openConnection();
			httpConn.setConnectTimeout(TIMEOUT);//先设置超时(5000毫秒)再打开连接
		    httpConn.connect();                 //建立连接
			int responseCode = httpConn.getResponseCode();
			if(responseCode != HttpURLConnection.HTTP_OK) {//判断HTTP连接是否成功
				return;
			}
		} catch (MalformedURLException e) {//URL格式错误或未指定正确的协议名，或是字符串无法解析
			// TODO 自动生成的 catch 块
			e.printStackTrace();//输出异常信息
			return;
		} catch (IOException e) {//捕获输入输出异常
			e.printStackTrace();//输出异常信息
			return;
		}

		File file = new File(filePath);//打开文件
		if(hasNewRSS(httpConn, file)) {//1在HTTP服务器上检查是否有新的RSS文件
			System.out.println("现在更新");
			try {
				//2下载RSS文件，并且保存到缓冲区
				ByteBuffer buffer = download(httpConn);
				if(buffer != null) {
					saveAs(buffer, file);//3将RSS文件内容保存到文件中
				}
			} catch (Exception e) {
				e.printStackTrace();//输出异常信息
			}
		}	else {
			System.out.println("RSS文件已经更新完毕");
		}
	}
	
//			URL url = new URL(urlPath);
//			HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
//			httpConn.connect();
//			InputStream in = httpConn.getInputStream();
//			BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));
//			// String line = br.readLine();
//	
//			String line = "";
//			while((line = br.readLine()) != null) {
//				System.out.println(line);
//	
//			}
//			in.close();
//			httpConn.disconnect();
//		}



	/**
	 * 下载文件到缓冲区
	 * @param httpconn
	 * @return
	 * ByteBuffer字节缓冲区可以通过 allocation 方法创建，此方法为该缓冲区的内容分配空间
	 */
	private static ByteBuffer download(HttpURLConnection httpConn) {
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);//创建一个64KB缓冲区
		try {
			InputStream in = httpConn.getInputStream();//in 是一个输入流
		    BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));	
			String line = "";
			while((line=br.readLine()) != null) {
				System.out.println(line);
			}	
			//buffer.put(byte b);                    //写缓冲区
			buffer.flip();                           //写完
			in.close();                              //关闭输入流 
			httpConn.disconnect();                   //关闭连接
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  //下载文件

		return buffer;

	}

	/**
	 * 将缓冲区的文件保存到文件中
	 * @param buffer
	 * @param file
	 */
	private synchronized static void saveAs(ByteBuffer buffer,File file) {

//		FileChannel channel = fileOutputStream.getChannel();//打开文件通道
//		channel.write(buffer);//写文件
//		channel.close();//关闭通道





	}

	/**
	 * 判断RSS文件是否有更新
	 * @param httpConn
	 * @param file
	 * @return 
	 */
	private static boolean hasNewRSS(HttpURLConnection httpConn,File file) {
		long current = System.currentTimeMillis();//获得服务器最后修改时间
		long httpLastModified = httpConn.getHeaderFieldDate("Last-Modified", current);//获得服务器最后修改时间

		long fileLastModified = file.lastModified();//本地文件最后修改时间

		if(httpLastModified > fileLastModified){
			return true;
		}
		return false;

	}





//	/**
//	 * 程序入口方法
//	 * @param args
//	 * @throws IOException 
//	 */
//	public static void main(String [] args) throws IOException {
//		RSSService service = new RSSService();        //创建一个业务逻辑类对象
//		List<Channel>channelList = service.getchannelList();//获得频道信息列表
//	//	Channel channel = channelList.get(2);   //获得第一个频道信息
//		//	    String url = channel.getUrl(); //URL(Uniform Resource Locator)为统一资源定位符,就是指网络地址。语法URL由三部分:资源类型、存放资源的主机域名、资源文件名//获取第一个频道的URL
//		//	    String filePath = channel.getFilePath();//获得第一个频道的路径
//		//	    String name =  channel.getName();
//		//	    System.out.println("RSS:"+url+"-->"+filePath);//输出第一个频道的url和本地路径
//
//		//update(channel.getUrl(),channel.getFilePath());
//	
//		Thread th = new UpdateThread();
//		th.start();
//		
//		
//	}

}
