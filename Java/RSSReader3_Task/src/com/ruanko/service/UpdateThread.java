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
 * RSS������
 */
public class UpdateThread extends Thread {

	// �ӳ�ʱ��(5����)
	private final static int DELAY_TIME = 300 * 1000; //����
	
	// �����С��3*64KB��
	private final static int BUFFER_SIZE = 3*65536;  //����
	
	// Ƶ����Ϣ�б�
	List <Channel> channelList;
	RSSService rssService;                           //����ҵ���߼���Ķ���
	
	// �޲ι��췽��
	public UpdateThread() {
		RSSService rssService = new RSSService();         //����ҵ���߼������
		channelList = rssService.getchannelList();       //���� ҵ���߼���RSSService�е�getchannelList��������ֵΪƵ���б�
	}

	// �߳����з���
	public void run() {
		while (true) {
			
			// ����RSS
			System.out.println("���ڸ���............." + new Date());
			for (int i = 0; i < channelList.size(); i++) {
				Channel channel = (Channel)channelList.get(i);
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

	// �ж�RSS�ļ��Ƿ����
	private boolean hasNewRSS(HttpURLConnection httpConn, File file) {
		
		// ��ȡ����������޸�ʱ��
		long current = System.currentTimeMillis();//ϵͳ��ǰʱ�䣬������
		long httpLastModified = httpConn.getHeaderFieldDate("Last-Modified",
				current);//public long getHeaderFieldDate(String name,long Default)��name - ͷ�ֶε����ơ�Default - Ĭ��ֵ�� 
		//��Щ�������ͣ����� http-ng������Ԥ����ͷ�����Դ���������ʽ�� getHeaderField�����ڸ����͵����ӿ���д�˷��������̽������̡�
		//���ؽ���Ϊ���ڵ�ָ���ֶε�ֵ�����Ϊָ���ֶα�ʾ�ľ���������α�׼ʱ�� 1970 �� 1 �� 1 �յĺ������� �ֶε�ֵ������Ϊ���ڡ�������ֶ�ȱ�ٻ��д����򷵻� Default ������ֵ��
		
		
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
	
	// ��������RSS�ļ����浽·��ΪfilePath�ı����ļ���
	private void update(String urlPath, String filePath) {
		
		// ����HTTP����
		HttpURLConnection httpConn;
		try {
			URL url = new URL(urlPath);//��������·��urlPath����URL����
			
			//������
			httpConn = (HttpURLConnection) url.openConnection();//ͨ��URL�������URL���е�openConnection����������
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
		File file = new File(filePath);//���ļ�
		if (hasNewRSS(httpConn, file)) {
			System.out.println("���ڸ���...........");
			
			// ������Ҫ���µ�RSS�ļ�����������
			ByteBuffer buffer = download(httpConn);
			
			// �ٽ��������е�RSS�ļ����ݱ��浽�����ļ���
			if (buffer != null) {
				saveAs(buffer, file);
			}
		} else {          //����������Ϊ��
			System.out.println("��ǰ�ļ�����Ϊ��������");
		}

	}

	// �����ļ���������
	private ByteBuffer download(HttpURLConnection httpConn) {
		// ����3*64KB�ֽڻ�����
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		try {

			// ��ȡ�������е�����
			InputStream in = httpConn.getInputStream();
			//��RSS�ļ����ص�������
			byte[] contentInBytes=new byte[BUFFER_SIZE];//����һ�����������������
			int len;
			while((len=in.read(contentInBytes))!=-1){
				buffer.put(contentInBytes,0,len);//��contentInBytes�����е����ݱ��浽������
			}
			
			//��RSS�ļ���ʾ������̨
			BufferedReader br1 = new BufferedReader(new InputStreamReader(in,"utf-8"));//����һ�����ֽ�������utf-8���뷽ʽ����ת��Ϊ��������	
			String line = "";
			while((line=br1.readLine()) != null) {
				System.out.println(line);//���������е��ļ���ʾ������̨
			}	

			// �ļ��������
			buffer.flip();
			
			// �ر���
			in.close();
			return buffer;                  //���ػ�����
		} catch (IOException e) {
			e.printStackTrace();
			return buffer;
		}
	}

	// �����������ļ����浽�ļ���
	private synchronized void saveAs(ByteBuffer buffer, File file) {
		try {
			FileOutputStream fout = new FileOutputStream(file, false);//false��ʾ���ļ���ͷд�����ݣ�����ÿ�θ��µ����ݻḲ��֮ǰ�ľ�����
			FileChannel channel=fout.getChannel();//public FileChannel getChannel()��������ļ�������йص�Ψһ FileChannel ����
			channel.write(buffer);
			
			// �ر���
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
