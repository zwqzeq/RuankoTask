package com.ruanko.bms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Tools {
	/**
	 * 将日期对象转换为字符串，格式为yyyy-MM-dd
	 * @param date 日期对象
	 * @return 转换后的日期字符串
	 */
	public static String formatDate(Date date) {
		// 创建日期格式化对象
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(date);
	}
	
	/**
	 * 生成卡号
	 * @param cardType 卡类型
	 * @return 卡号
	 */
	public static String generateCardNo(int cardType) {
		// 创建随机数和日期格式化对象
		Random random = new Random();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 生成18位卡号
		String cardNo = sdf.format(new Date());
		for (int i = 0; i < 5; i++) {
			cardNo += random.nextInt(10);
		}
		cardNo = cardType + cardNo;
		
		return cardNo;
	}

}
