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
 * RSS������
 */
public class UpdateThread extends Thread {

	// �ӳ�ʱ��(5����)
	private final static int DELAY_TIME = 300 * 1000;
	// �����С��3*64KB��
	private final static int BUFFER_SIZE = 3*65536;
	// Ƶ����Ϣ�б�
	List<Channel> channelList;
	RSSService rssService;                         //ҵ���߼������
	private List<News> newsList;                    //���������б�


	// �޲ι�����
	public UpdateThread() {
		// ����RSSService ����
		RSSService rssService = new RSSService();
		channelList = rssService.getchannelList();
	}

	// �߳����з���
	public void run() {
		while (true) {
			// ����RSS
			System.out.println("���ڸ���............." + new Date());
			for (int i = 0; i < channelList.size(); i++) {
				Channel channel = channelList.get(i);
				System.out.println("���£�" + channel.getName());
				update(channel.getUrl(), channel.getFilePath());
				System.out.println(channel.getUrl()+"    "+channel.getFilePath());
				//System.out.println("����������ʧ�ܣ�����");
			}
			System.out.println("�������............." + new Date());
			// ��ͣ5���ӣ�ÿ����Ӹ���һ�Σ�
			try {
				sleep(DELAY_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	// ��������RSS�ļ�����
	private void update(String urlPath, String filePath) {
		// ����HTTP����
		HttpURLConnection httpConn;
		try {
			URL url = new URL(urlPath);
			//������
			httpConn = (HttpURLConnection) url.openConnection();
			// �ж�HTTP�����Ƿ�ɹ�
			int responseCode = httpConn.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// ��HTTP�ļ��ϼ���Ƿ�����Ҫ���µ�RSS�ļ�
		File file = new File(filePath);
		if (hasNewRSS(httpConn, file)) {
			System.out.println("���ڸ���...........");
			// ����Ҫ���µ�RSS�ļ�����������
			ByteBuffer buffer = download(httpConn);
			// ��RSS�ļ����ݱ��浽�ļ���
			if (buffer != null) {
				saveAs(buffer, file);
			}
		} else {
			System.out.println("��ǰ�ļ�����Ϊ��������");
		}

	}

	// �����ļ���������
	private ByteBuffer download(HttpURLConnection httpConn) {
		// ����3*64KB������
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		try {

			// ��ȡ����
			InputStream in = httpConn.getInputStream();
			// ��RSS�ļ����ص�������
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

			// �ļ��������
			buffer.flip();
			// �ر���
			in.close();
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return buffer;
		}
	}

	// �����������ļ����浽�ļ���
	private synchronized void saveAs(ByteBuffer buffer, File file) {
		try {
			FileOutputStream fout = new FileOutputStream(file, false);
			FileChannel channel=fout.getChannel();
			channel.write(buffer);
			// �ر���
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �ж�RSS�ļ��Ƿ����
	private boolean hasNewRSS(HttpURLConnection httpConn, File file) {
		// ��ȡ����������޸�ʱ��
		long current = System.currentTimeMillis();
		long httpLastModified = httpConn.getHeaderFieldDate("Last-Modified",
				current);
		// ��ȡ�����ļ�����޸�ʱ��
		long fileLastModified = file.lastModified();
		// �ж�����ʱ���Ƿ����
		if (httpLastModified > fileLastModified) {
			// �������������޸�ʱ����ڱ����ļ�����޸�ʱ�䣬����Ҫ���£����� ture
			return true;
		} else {
			return false;
		}
	

	
	}
}
