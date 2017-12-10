package com.ruanko.bms.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.ruanko.bms.model.Customer;

/**
 * �˿���ϢIO��
 * ����ͻ���Ϣ
 * @author asus
 *
 */
public class CustomerIO implements IBaseIO<Customer>{
	
	private final static String CUSTOMER_DIR = "./file/Customer.txt";//�ļ�·��
	private final static int CUSTOMER_ATTR_NUM = 10;//Customer��Ա������
	
	/**����ͻ���Ϣ
	 * @param customer
	 */
	public void save(Customer obj) {
		
		// �ж϶����Ƿ�Ϊ��
		if (obj == null) {
			return;
		}
		
		// ����I/O������ض���
		Writer writer = null;
		BufferedWriter bw = null;
		
		// ʵ����I/O������ض���
		try {
			writer = new FileWriter(CUSTOMER_DIR, true);
		    bw = new BufferedWriter(writer);
		    
		    // ����Ϣд���ļ�ĩβ
		    bw.write(obj.toString());
		    bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	
	/**
	 * ��֤�û������֤���Ƿ����
	 * @param idNum
	 * @return
	 */
	public boolean exists(String idNum) {
		
		boolean isExists = false;
		
		// �ж����֤���Ƿ�Ϊ��
		if (idNum == null || idNum.length() == 0) {
			return isExists;
		}
		
		// ����I/O������ض���
		Reader reader = null;
		BufferedReader br = null;
		try {
			// �ж��ļ��Ƿ����
			File file = new File(CUSTOMER_DIR);
			if (!file.exists()) {
				file.createNewFile();
			}
			
			// ʵ����I/O������ض���
			reader = new FileReader(CUSTOMER_DIR);
			br = new BufferedReader(reader);
			String line = "";
			
			// ���ж�ȡ�ļ�
			while ((line = br.readLine()) != null) {
				// ��һ���ַ����������ɢ
				String[] results = line.split("##");
				
				// �ж����֤���Ƿ�ƥ��
				if (results.length == CUSTOMER_ATTR_NUM && idNum.equals(results[4])) {
					isExists = true;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isExists;
	}
}
