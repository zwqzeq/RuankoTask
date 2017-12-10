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

import com.ruanko.bms.model.SavingCard;

public class SavingCardIO implements IBaseIO<SavingCard> {
	
	private final static String SAVINGCARD_DIR = "./file/SavingCard.txt";//文件路径
	private final static int SAVINGCARD_ATTR_NUM = 7;//SavingCard成员属性数
	
	/**
	 * 更新卡信息
	 * @param savingCard
	 */
	@SuppressWarnings("unused")
	public void update(SavingCard savingCard) {
		// 初始化对象
		Reader reader = null;
		BufferedReader br = null;
		Writer writer = null;
		BufferedWriter bw = null;
		List<String> resultList = null;
		
		if (savingCard == null) {
			return;
		}
		
		try {
			// 创建I/O操作相关对象
		    reader = new FileReader(SAVINGCARD_DIR);
		    br = new BufferedReader(reader);
		    
		    String line = "";
		    // 创建保存文件记录的List
		    resultList = new ArrayList<String>();
		    
		    // 按行读取文件
		    while ((line = br.readLine()) != null) {
				String[] results = line.split("##");
				
				// 把被更新的储蓄卡之外的卡信息存入保存文件记录的List中
				if (results.length == SAVINGCARD_ATTR_NUM && !savingCard.getNo().equals(results[0])) {
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
	 * 根据卡号查找储蓄卡
	 * @param no 卡号
	 * @return 储蓄卡对象
	 */
	public SavingCard findById(String no) {
		// 初始化对象
		SavingCard savingCard = null;
		Reader reader = null;
		BufferedReader br = null;
		
		if (no == null) {
			return savingCard;
		}
		
		try {
			// 判断文件是否存在
			File file = new File(SAVINGCARD_DIR);
			if (!file.exists()) {
				file.createNewFile();
			}
			
			// 创建I/O操作相关对象
		    reader = new FileReader(SAVINGCARD_DIR);
		    br = new BufferedReader(reader);
		    String line = "";
		    
		    // 按行读取文件
		    while ((line = br.readLine()) != null) {
		    	// 将一行字符串按规则打散
				String[] results = line.split("##");
				// 判断卡号是否匹配
				if (results.length == SAVINGCARD_ATTR_NUM && no.equals(results[0])) {
					// 将打散的字符串数组转换为SavingCard对象
					savingCard = parseSavingCard(results);
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
		
		return savingCard;
	}
	
	/**
	 * 将打散的字符串数组转换为SavingCard对象
	 * @param results 打散的字符数组
	 * @return SavingCard对象
	 */
	private SavingCard parseSavingCard (String[] results) {
		
		// 创建SavingCard对象
		SavingCard savingCard = new SavingCard();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// 根据字符数组填充SavingCard对象
		savingCard.setNo(results[0]);
		savingCard.setPassword(results[1]);
		savingCard.setIdNum(results[2]);
		
		try {
			savingCard.setCreateDate(sdf.parse(results[3]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		savingCard.setBonusPoints(Float.parseFloat(results[4]));
		savingCard.setMoney(Float.parseFloat(results[5]));
		savingCard.setInterestRate(Float.parseFloat(results[6]));
		
		return savingCard;
	}

	/**
	 * 保存卡信息
	 * @param obj
	 */
	@Override
	public void save(SavingCard obj) {
		
		// 初始化对象
		Writer writer = null;
		BufferedWriter bw = null;
		
		// 判断savingCard对象是否为空
		if (obj == null) {
			return;
		}
		try {
			// 创建I/O操作相关对象
		    writer = new FileWriter(SAVINGCARD_DIR, true);
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
