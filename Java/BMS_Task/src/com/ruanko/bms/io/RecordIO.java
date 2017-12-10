package com.ruanko.bms.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruanko.bms.model.Record;

/**
 * 操作记录类
 */
public class RecordIO implements IBaseIO<Record> {
	
	private final static String ACCOUNT_FORM_DIR = "./file/Record.txt";//文件路径
	private final static int ACCOUNT_FORM_ATTR_NUM = 7;//Record成员属性数
	
	/**
	 * 保存操作记录
	 * @param record
	 * @param ACCOUNT_FORM_DIR 
	 */
	public void save(Record obj) {
		// 初始化对象
		Writer writer = null;
		BufferedWriter bw = null;
		
		// 判断record对象是否为空
		if (obj == null) {
			return;
		}
		try {
			// 创建I/O操作相关对象
		    writer = new FileWriter(ACCOUNT_FORM_DIR, true);
		    bw = new BufferedWriter(writer);
		
			// 将信息写至文件末尾
			bw.write(obj.toString());
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 资源释放
			try {
				if (bw != null) {
				    bw.close();
			    }
		    } catch (IOException e) {
			    e.printStackTrace();
		    }
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}				
	}
	
	/**
	 * 保存操作记录方法重载实现
	 */
	@SuppressWarnings("unused")
	public void save(String type, String idNum, String no, String desc, float balance, float changedMoney, Date date) {
		// 初始化对象
		Writer writer = null;
		BufferedWriter bw = null;
		
		// 对record对象赋值
		Record record = new Record();
		record.setType(type);
		record.setIdNum(idNum);
		record.setNo(no);
		record.setDesc(desc);
		record.setBalance(balance);
		record.setChangedMoney(changedMoney);
		record.setDate(date);
		
		if (record == null) {
			return;
		}
		
		try {
			// 创建I/O操作相关对象
		    writer = new FileWriter(ACCOUNT_FORM_DIR, true);
		    bw = new BufferedWriter(writer);
		
			// 将信息写至文件末尾
			bw.write(record.toString());
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 资源释放
			try {
				if (bw != null) {
				    bw.close();
			    }
		    } catch (IOException e) {
			    e.printStackTrace();
		    }
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}		
	}
	
	/**
	 * 根据身份证号查询操作记录
	 * @param idNum 身份证号
	 * @return 操作记录List
	 */
	public List<Record> findByID(String idNum) {
		// 初始化对象
		List<Record> list = new ArrayList<Record>();
		Reader reader = null;
		BufferedReader br = null;
		
		// 判断账号是否为空
		if (idNum == null) {
			return list;
		}
	
		try {
			// 判断文件是否存在
		    File file = new File(ACCOUNT_FORM_DIR);
		    if (!file.exists()) {
			    file.createNewFile();
			}
			
			// 创建I/O操作相关对象
			reader = new FileReader(ACCOUNT_FORM_DIR);
			br = new BufferedReader(reader);
		    String line = "";
		    
		    // 按行读取文件
		    while ((line = br.readLine()) != null) {
				// 将一行字符串按规则打散
		    	String[] results = line.split("##");
		    	// 判断卡号是否为空
		    	if (results.length == ACCOUNT_FORM_ATTR_NUM && idNum.equals(results[1])) {
					// 将打散的字符串数组转换为Record对象并加入到List中
		    		list.add(parseRecord(results));
				}
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			// 释放资源
			try {
				if (br != null) {
				    br.close();
			    }
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (reader != null) {
				    reader.close();
			    }
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		return list;
	}

	/**
	 * 将打散的字符串数组转换为Record对象
	 * @param results 打散的字符串数组
	 * @return Record对象
	 */
	private Record parseRecord(String[] results) {
		// 创建对象
		Record record = new Record();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// 根据字符数组填充SavingCard对象
		record.setType(results[0]);
		record.setIdNum(results[1]);
		record.setNo(results[2]);
		record.setDesc(results[3]);
		record.setChangedMoney(Float.parseFloat(results[4]));
		record.setBalance(Float.parseFloat(results[5]));
		
		try {
			record.setDate(sdf.parse(results[6]));
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return record;
	}

}
