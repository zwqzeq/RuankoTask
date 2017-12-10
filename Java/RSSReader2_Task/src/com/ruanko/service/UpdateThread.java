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
 * 1.�ж�RSS�ļ��Ƿ��и���
 * �������ļ���Last-Modified
 * �����ļ���LastModified
 * 
 * 2.���ңӣ��ļ����ص�����
 * HTTPЭ�飺HttpURLConnection
 * �жϳ�ʱ��setConnectionTimeOut(int timeOut)
 * ��������ByteBuffer + FileOutputStream + FileChannel
 * 
 * 3.�������е����ݱ��浽�ļ���
 * �ļ�����FileOutStream
 * �ʼ�ͨ����FileChannel
 * 
 */


/**
 *  �߳���
 *  ��ʱ���������ϸ��µ�RSS�ļ�
 *  ���裺1-��������RSS������
 *      2-�������ݻ�����
 *      3-�̶߳�ʱ����
 *      4-������
 */
public class UpdateThread extends Thread{

	private final static int TIMEOUT = 5*1000;           //5��
	private final static int DELAY_TIME = 300 * 1000;   //�������ŵ�ʱ����Ϊ5����һ��
	private final static int BUFFER_SIZE = 65536;    //64kb

	private List <Channel> channelList;//Ƶ����Ϣ�б�

	/**
	 * �޲ι��췽��
	 */
	public UpdateThread() {
		RSSService  rssService= new RSSService();
		channelList = rssService.getchannelList();
	}



	/**
	 * ��дthread���run()����
	 */
	public void run () { 
		
		//��ѭ����ÿ��5���Ӹ���һ��RSS�ļ�
		while (true) {
			System.out.println("���ڸ���.............."+new Date());
			
			//����RSS�ļ�
			for(int i = 0; i < channelList.size(); i++) {
				Channel channel = channelList.get(i);
				System.out.println("���£�"+channel.getName());
				try {
					update(channel.getUrl(),channel.getFilePath());
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
			System.out.println("�������.............."+new Date());

			//���߳�����5����
			try {
				sleep(DELAY_TIME);//pause 5 minutes
			} catch (InterruptedException e) {//�����쳣
				e.printStackTrace();//����쳣��Ϣ
			}

		}


	}


	/**
	 *  HttpURLConnection��connect()������ʵ����ֻ�ǽ�����һ�����������tcp���ӣ���û��ʵ�ʷ���http����
	 *  ������post����get��http����ʵ����ֱ��HttpURLConnection��getInputStream()��������������ʽ���ͳ�ȥ
	 *  ����outputStream��д�������ֱ���Ҫ��inputStream�Ķ�����֮ǰ�� 
	 * 
	 * 
	 * 
	 * 
	 * HttpURLConnection������ֱ�ӹ��죬��Ҫͨ��URL.openConnection()�����
	 * ������µ�RSS�ļ�������¸�RSS�ļ�
	 * �������ܣ��������ַ����RSS�ļ������浽filePath·����
	 * @param urlPath RSS�ļ�������·��
	 * @param filePath RSS �ļ����ر���·��
	 * @throws IOException 
	 */
	public static void update(String urlPath,String filePath) throws IOException {
		HttpURLConnection httpConn;
		try {
			URL url = new URL(urlPath);//����URL����
			httpConn = (HttpURLConnection)url.openConnection();
			httpConn.setConnectTimeout(TIMEOUT);//�����ó�ʱ(5000����)�ٴ�����
		    httpConn.connect();                 //��������
			int responseCode = httpConn.getResponseCode();
			if(responseCode != HttpURLConnection.HTTP_OK) {//�ж�HTTP�����Ƿ�ɹ�
				return;
			}
		} catch (MalformedURLException e) {//URL��ʽ�����δָ����ȷ��Э�����������ַ����޷�����
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();//����쳣��Ϣ
			return;
		} catch (IOException e) {//������������쳣
			e.printStackTrace();//����쳣��Ϣ
			return;
		}

		File file = new File(filePath);//���ļ�
		if(hasNewRSS(httpConn, file)) {//1��HTTP�������ϼ���Ƿ����µ�RSS�ļ�
			System.out.println("���ڸ���");
			try {
				//2����RSS�ļ������ұ��浽������
				ByteBuffer buffer = download(httpConn);
				if(buffer != null) {
					saveAs(buffer, file);//3��RSS�ļ����ݱ��浽�ļ���
				}
			} catch (Exception e) {
				e.printStackTrace();//����쳣��Ϣ
			}
		}	else {
			System.out.println("RSS�ļ��Ѿ��������");
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
	 * �����ļ���������
	 * @param httpconn
	 * @return
	 * ByteBuffer�ֽڻ���������ͨ�� allocation �����������˷���Ϊ�û����������ݷ���ռ�
	 */
	private static ByteBuffer download(HttpURLConnection httpConn) {
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);//����һ��64KB������
		try {
			InputStream in = httpConn.getInputStream();//in ��һ��������
		    BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));	
			String line = "";
			while((line=br.readLine()) != null) {
				System.out.println(line);
			}	
			//buffer.put(byte b);                    //д������
			buffer.flip();                           //д��
			in.close();                              //�ر������� 
			httpConn.disconnect();                   //�ر�����
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  //�����ļ�

		return buffer;

	}

	/**
	 * �����������ļ����浽�ļ���
	 * @param buffer
	 * @param file
	 */
	private synchronized static void saveAs(ByteBuffer buffer,File file) {

//		FileChannel channel = fileOutputStream.getChannel();//���ļ�ͨ��
//		channel.write(buffer);//д�ļ�
//		channel.close();//�ر�ͨ��





	}

	/**
	 * �ж�RSS�ļ��Ƿ��и���
	 * @param httpConn
	 * @param file
	 * @return 
	 */
	private static boolean hasNewRSS(HttpURLConnection httpConn,File file) {
		long current = System.currentTimeMillis();//��÷���������޸�ʱ��
		long httpLastModified = httpConn.getHeaderFieldDate("Last-Modified", current);//��÷���������޸�ʱ��

		long fileLastModified = file.lastModified();//�����ļ�����޸�ʱ��

		if(httpLastModified > fileLastModified){
			return true;
		}
		return false;

	}





//	/**
//	 * ������ڷ���
//	 * @param args
//	 * @throws IOException 
//	 */
//	public static void main(String [] args) throws IOException {
//		RSSService service = new RSSService();        //����һ��ҵ���߼������
//		List<Channel>channelList = service.getchannelList();//���Ƶ����Ϣ�б�
//	//	Channel channel = channelList.get(2);   //��õ�һ��Ƶ����Ϣ
//		//	    String url = channel.getUrl(); //URL(Uniform Resource Locator)Ϊͳһ��Դ��λ��,����ָ�����ַ���﷨URL��������:��Դ���͡������Դ��������������Դ�ļ���//��ȡ��һ��Ƶ����URL
//		//	    String filePath = channel.getFilePath();//��õ�һ��Ƶ����·��
//		//	    String name =  channel.getName();
//		//	    System.out.println("RSS:"+url+"-->"+filePath);//�����һ��Ƶ����url�ͱ���·��
//
//		//update(channel.getUrl(),channel.getFilePath());
//	
//		Thread th = new UpdateThread();
//		th.start();
//		
//		
//	}

}
