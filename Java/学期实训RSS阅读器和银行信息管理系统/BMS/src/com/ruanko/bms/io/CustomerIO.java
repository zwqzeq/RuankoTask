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
 * 顾客信息IO流
 * 保存客户信息
 * @author asus
 *
 */
public class CustomerIO implements IBaseIO<Customer>{
	
	private final static String CUSTOMER_DIR = "./file/Customer.txt";//文件路径
	private final static int CUSTOMER_ATTR_NUM = 10;//Customer成员属性数
	
	/**保存客户信息
	 * @param customer
	 */
	public void save(Customer obj) {
		
		// 判断对象是否为空
		if (obj == null) {
			return;
		}
		
		// 声明I/O操作相关对象
		Writer writer = null;
		BufferedWriter bw = null;
		
		// 实例化I/O操作相关对象
		try {
			writer = new FileWriter(CUSTOMER_DIR, true);
		    bw = new BufferedWriter(writer);
		    
		    // 将信息写至文件末尾
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
	 * 验证用户的身份证号是否存在
	 * @param idNum
	 * @return
	 */
	public boolean exists(String idNum) {
		
		boolean isExists = false;
		
		// 判断身份证号是否为空
		if (idNum == null || idNum.length() == 0) {
			return isExists;
		}
		
		// 声明I/O操作相关对象
		Reader reader = null;
		BufferedReader br = null;
		try {
			// 判断文件是否存在
			File file = new File(CUSTOMER_DIR);
			if (!file.exists()) {
				file.createNewFile();
			}
			
			// 实例化I/O操作相关对象
			reader = new FileReader(CUSTOMER_DIR);
			br = new BufferedReader(reader);
			String line = "";
			
			// 按行读取文件
			while ((line = br.readLine()) != null) {
				// 将一行字符串按规则打散
				String[] results = line.split("##");
				
				// 判断身份证号是否匹配
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
