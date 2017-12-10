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
 * ���ÿ�I/O������
 */
public class CreditCardIO implements IBaseIO<CreditCard> {
	
	private final static String CREDITCARD_DIR = "./file/CreditCard.txt";//�ļ�·��
	private final static int CREDITCARD_ATTR_NUM = 10;//CreditCard��Ա������
	
	
	/**
	 * ���¿���Ϣ
	 * @param creditCard
	 */
	@SuppressWarnings("unused")
	public void update(CreditCard creditCard) {
		// ��ʼ������
		Reader reader = null;
		BufferedReader br = null;
		Writer writer = null;
		BufferedWriter bw = null;
		List<String> resultList = null;
		
		if (creditCard == null) {
			return;
		}
		
		try {
			// ����I/O������ض���
		    reader = new FileReader(CREDITCARD_DIR);
		    br = new BufferedReader(reader);
		    
		    String line = "";
		    // ���������ļ���¼��List
		    resultList = new ArrayList<String>();
		    
		    // ���ж�ȡ�ļ�
		    while ((line = br.readLine()) != null) {
				String[] results = line.split("##");
				
				// �ѱ����µ����ÿ�֮��Ŀ���Ϣ���뱣���ļ���¼��List��
				if (results.length == CREDITCARD_ATTR_NUM && !creditCard.getNo().equals(results[0])) {
					resultList.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// ��Դ�ͷ�
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
	 * ���ݿ��Ų������ÿ�
	 * @param no ����
	 * @return ���ÿ�����
	 */
	public CreditCard findById(String no) {
		// ��ʼ������
		CreditCard creditCard = null;
		Reader reader = null;
		BufferedReader br = null;
		
		if (no == null) {
			return creditCard;
		}
		
		try {
			// �ж��ļ��Ƿ����
			File file = new File(CREDITCARD_DIR);
			if (!file.exists()) {
				file.createNewFile();
			}
			
			// ����I/O������ض���
		    reader = new FileReader(CREDITCARD_DIR);
		    br = new BufferedReader(reader);
		    String line = "";
		    
		    // ���ж�ȡ�ļ�
		    while ((line = br.readLine()) != null) {
		    	// ��һ���ַ����������ɢ
				String[] results = line.split("##");
				// �жϿ����Ƿ�ƥ��
				if (results.length == CREDITCARD_ATTR_NUM && no.equals(results[0])) {
					// ����ɢ���ַ�������ת��ΪCreditCard����
					creditCard = parseCreditCard(results);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// ��Դ�ͷ�
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
	 * ����ɢ���ַ�������ת��ΪCreditCard����
	 * @param results ��ɢ���ַ�����
	 * @return CreditCard����
	 */
	private CreditCard parseCreditCard (String[] results) {
		
		// ����SavingCard����
		CreditCard creditCard = new CreditCard();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// �����ַ��������CreditCard����
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
	 * ���濨��Ϣ
	 * @param savingCard
	 */
	public void save(CreditCard obj) {
		// ��ʼ������
		Writer writer = null;
		BufferedWriter bw = null;
		
		// �ж�savingCard�����Ƿ�Ϊ��
		if (obj == null) {
			return;
		}
		try {
			// ����I/O������ض���
		    writer = new FileWriter(CREDITCARD_DIR, true);
		    bw = new BufferedWriter(writer);
		
			// ����Ϣд���ļ�ĩβ
			bw.write(obj.toString());
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// ��Դ�ͷ�
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
