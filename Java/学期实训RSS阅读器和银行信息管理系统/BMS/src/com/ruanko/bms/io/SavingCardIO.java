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
	
	private final static String SAVINGCARD_DIR = "./file/SavingCard.txt";//�ļ�·��
	private final static int SAVINGCARD_ATTR_NUM = 7;//SavingCard��Ա������
	
	/**
	 * ���¿���Ϣ
	 * @param savingCard
	 */
	@SuppressWarnings("unused")
	public void update(SavingCard savingCard) {
		// ��ʼ������
		Reader reader = null;
		BufferedReader br = null;
		Writer writer = null;
		BufferedWriter bw = null;
		List<String> resultList = null;
		
		if (savingCard == null) {
			return;
		}
		
		try {
			// ����I/O������ض���
		    reader = new FileReader(SAVINGCARD_DIR);
		    br = new BufferedReader(reader);
		    
		    String line = "";
		    // ���������ļ���¼��List
		    resultList = new ArrayList<String>();
		    
		    // ���ж�ȡ�ļ�
		    while ((line = br.readLine()) != null) {
				String[] results = line.split("##");
				
				// �ѱ����µĴ��֮��Ŀ���Ϣ���뱣���ļ���¼��List��
				if (results.length == SAVINGCARD_ATTR_NUM && !savingCard.getNo().equals(results[0])) {
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
	 * ���ݿ��Ų��Ҵ��
	 * @param no ����
	 * @return �������
	 */
	public SavingCard findById(String no) {
		// ��ʼ������
		SavingCard savingCard = null;
		Reader reader = null;
		BufferedReader br = null;
		
		if (no == null) {
			return savingCard;
		}
		
		try {
			// �ж��ļ��Ƿ����
			File file = new File(SAVINGCARD_DIR);
			if (!file.exists()) {
				file.createNewFile();
			}
			
			// ����I/O������ض���
		    reader = new FileReader(SAVINGCARD_DIR);
		    br = new BufferedReader(reader);
		    String line = "";
		    
		    // ���ж�ȡ�ļ�
		    while ((line = br.readLine()) != null) {
		    	// ��һ���ַ����������ɢ
				String[] results = line.split("##");
				// �жϿ����Ƿ�ƥ��
				if (results.length == SAVINGCARD_ATTR_NUM && no.equals(results[0])) {
					// ����ɢ���ַ�������ת��ΪSavingCard����
					savingCard = parseSavingCard(results);
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
		
		return savingCard;
	}
	
	/**
	 * ����ɢ���ַ�������ת��ΪSavingCard����
	 * @param results ��ɢ���ַ�����
	 * @return SavingCard����
	 */
	private SavingCard parseSavingCard (String[] results) {
		
		// ����SavingCard����
		SavingCard savingCard = new SavingCard();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// �����ַ��������SavingCard����
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
	 * ���濨��Ϣ
	 * @param obj
	 */
	@Override
	public void save(SavingCard obj) {
		
		// ��ʼ������
		Writer writer = null;
		BufferedWriter bw = null;
		
		// �ж�savingCard�����Ƿ�Ϊ��
		if (obj == null) {
			return;
		}
		try {
			// ����I/O������ض���
		    writer = new FileWriter(SAVINGCARD_DIR, true);
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
