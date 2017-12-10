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
 * ������¼��
 */
public class RecordIO implements IBaseIO<Record> {
	
	private final static String ACCOUNT_FORM_DIR = "./file/Record.txt";//�ļ�·��
	private final static int ACCOUNT_FORM_ATTR_NUM = 7;//Record��Ա������
	
	/**
	 * ���������¼
	 * @param record
	 * @param ACCOUNT_FORM_DIR 
	 */
	public void save(Record obj) {
		// ��ʼ������
		Writer writer = null;
		BufferedWriter bw = null;
		
		// �ж�record�����Ƿ�Ϊ��
		if (obj == null) {
			return;
		}
		try {
			// ����I/O������ض���
		    writer = new FileWriter(ACCOUNT_FORM_DIR, true);
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
	
	/**
	 * ���������¼��������ʵ��
	 */
	@SuppressWarnings("unused")
	public void save(String type, String idNum, String no, String desc, float balance, float changedMoney, Date date) {
		// ��ʼ������
		Writer writer = null;
		BufferedWriter bw = null;
		
		// ��record����ֵ
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
			// ����I/O������ض���
		    writer = new FileWriter(ACCOUNT_FORM_DIR, true);
		    bw = new BufferedWriter(writer);
		
			// ����Ϣд���ļ�ĩβ
			bw.write(record.toString());
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
	
	/**
	 * �������֤�Ų�ѯ������¼
	 * @param idNum ���֤��
	 * @return ������¼List
	 */
	public List<Record> findByID(String idNum) {
		// ��ʼ������
		List<Record> list = new ArrayList<Record>();
		Reader reader = null;
		BufferedReader br = null;
		
		// �ж��˺��Ƿ�Ϊ��
		if (idNum == null) {
			return list;
		}
	
		try {
			// �ж��ļ��Ƿ����
		    File file = new File(ACCOUNT_FORM_DIR);
		    if (!file.exists()) {
			    file.createNewFile();
			}
			
			// ����I/O������ض���
			reader = new FileReader(ACCOUNT_FORM_DIR);
			br = new BufferedReader(reader);
		    String line = "";
		    
		    // ���ж�ȡ�ļ�
		    while ((line = br.readLine()) != null) {
				// ��һ���ַ����������ɢ
		    	String[] results = line.split("##");
		    	// �жϿ����Ƿ�Ϊ��
		    	if (results.length == ACCOUNT_FORM_ATTR_NUM && idNum.equals(results[1])) {
					// ����ɢ���ַ�������ת��ΪRecord���󲢼��뵽List��
		    		list.add(parseRecord(results));
				}
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
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
	 * ����ɢ���ַ�������ת��ΪRecord����
	 * @param results ��ɢ���ַ�������
	 * @return Record����
	 */
	private Record parseRecord(String[] results) {
		// ��������
		Record record = new Record();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// �����ַ��������SavingCard����
		record.setType(results[0]);
		record.setIdNum(results[1]);
		record.setNo(results[2]);
		record.setDesc(results[3]);
		record.setChangedMoney(Float.parseFloat(results[4]));
		record.setBalance(Float.parseFloat(results[5]));
		
		try {
			record.setDate(sdf.parse(results[6]));
		} catch (ParseException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return record;
	}

}
