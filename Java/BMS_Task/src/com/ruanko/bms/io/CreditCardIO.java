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
import java.util.List;

import com.ruanko.bms.model.CreditCard;

/**
 * 信用卡I/O操作类
 */
public class CreditCardIO implements IBaseIO<CreditCard> {
	
	private final static String CREDITCARD_DIR = "./file/CreditCard.txt";//文件路径
	private final static int CREDITCARD_ATTR_NUM = 10;//CreditCard成员属性数
	
	
	/**
	 * 更新卡信息
	 * @param creditCard
	 */
	@SuppressWarnings("unused")
	public void update(CreditCard creditCard) {
		// 初始化对象
		Reader reader = null;
		BufferedReader br = null;
		Writer writer = null;
		BufferedWriter bw = null;
		List<String> resultList = null;
		
		if (creditCard == null) {
			return;
		}
		
		try {
			// 创建I/O操作相关对象
		    reader = new FileReader(CREDITCARD_DIR);
		    br = new BufferedReader(reader);
		    
		    String line = "";
		    // 创建保存文件记录的List
		    resultList = new ArrayList<String>();
		    
		    // 按行读取文件
		    while ((line = br.readLine()) != null) {
				String[] results = line.split("##");
				
				// 把被更新的信用卡之外的卡信息存入保存文件记录的List中
				if (results.length == CREDITCARD_ATTR_NUM && !creditCard.getNo().equals(results[0])) {
					resultList.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 资源释放
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
	}
	
	/**
	 * 根据卡号查找信用卡
	 * @param no 卡号
	 * @return 信用卡对象
	 */
	public CreditCard findById(String no) {
		// 初始化对象
		CreditCard creditCard = null;
		Reader reader = null;
		BufferedReader br = null;
		
		if (no == null) {
			return creditCard;
		}
		
		try {
			// 判断文件是否存在
			File file = new File(CREDITCARD_DIR);
			if (!file.exists()) {
				file.createNewFile();
			}
			
			// 创建I/O操作相关对象
		    reader = new FileReader(CREDITCARD_DIR);
		    br = new BufferedReader(reader);
		    String line = "";
		    
		    // 按行读取文件
		    while ((line = br.readLine()) != null) {
		    	// 将一行字符串按规则打散
				String[] results = line.split("##");
				// 判断卡号是否匹配
				if (results.length == CREDITCARD_ATTR_NUM && no.equals(results[0])) {
					// 将打散的字符串数组转换为CreditCard对象
					creditCard = parseCreditCard(results);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 资源释放
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
		
		return creditCard;
	}
	
	/**
	 * 将打散的字符串数组转换为CreditCard对象
	 * @param results 打散的字符数组
	 * @return CreditCard对象
	 */
	private CreditCard parseCreditCard (String[] results) {
		
		// 创建SavingCard对象
		CreditCard creditCard = new CreditCard();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// 根据字符数组填充CreditCard对象
		creditCard.setNo(results[0]);
		creditCard.setPassword(results[1]);
		creditCard.setIdNum(results[2]);
		
		try {
			creditCard.setCreateDate(sdf.parse(results[3]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		creditCard.setBonusPoints(Float.parseFloat(results[4]));
		creditCard.setMoney(Float.parseFloat(results[5]));
		creditCard.setCreditLimit(Float.parseFloat(results[6]));
		creditCard.setChargeRate(Float.parseFloat(results[7]));
		creditCard.setStatementDay(Integer.parseInt(results[8]));
		creditCard.setDeadline(Integer.parseInt(results[9]));
		
		return creditCard;
	}
	
	/**
	 * 保存卡信息
	 * @param savingCard
	 */
	public void save(CreditCard obj) {
		// 初始化对象
		Writer writer = null;
		BufferedWriter bw = null;
		
		// 判断savingCard对象是否为空
		if (obj == null) {
			return;
		}
		try {
			// 创建I/O操作相关对象
		    writer = new FileWriter(CREDITCARD_DIR, true);
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

}
