package com.ruanko.bms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Tools {
	/**
	 * �����ڶ���ת��Ϊ�ַ�������ʽΪyyyy-MM-dd
	 * @param date ���ڶ���
	 * @return ת����������ַ���
	 */
	public static String formatDate(Date date) {
		// �������ڸ�ʽ������
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(date);
	}
	
	/**
	 * ���ɿ���
	 * @param cardType ������
	 * @return ����
	 */
	public static String generateCardNo(int cardType) {
		// ��������������ڸ�ʽ������
		Random random = new Random();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// ����18λ����
		String cardNo = sdf.format(new Date());
		for (int i = 0; i < 5; i++) {
			cardNo += random.nextInt(10);
		}
		cardNo = cardType + cardNo;
		
		return cardNo;
	}

}
