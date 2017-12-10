package com.ruanko.bms.model;

import java.util.Date;

import com.ruanko.bms.utils.Tools;

/**
 * 操作记录类
 */
public class Record {
	
	private String type;//银行卡类型
	private String idNum;//身份证号
	private String no;//银行卡号
	private String desc;//账务操作类型
	private float balance;//余额
	private float changedMoney;//操作金额
	private Date date = null;//账务操作日期
	
	/**
	 * 构造方法
	 */
	public Record() {
		type = "";
		idNum = "";
		no = "";
		date = new Date();
		desc = "";
	}
	
	@Override
	public String toString() {
		String str = type + "##" + idNum + "##" + no + "##" + desc + "##" + changedMoney + "##" + balance + "##" + Tools.formatDate(date);
		return str;
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type 要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return idNum
	 */
	public String getIdNum() {
		return idNum;
	}

	/**
	 * @param idNum 要设置的 idNum
	 */
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	/**
	 * @return no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no 要设置的 no
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * @return desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc 要设置的 desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return balance
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * @param balance 要设置的 balance
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}

	/**
	 * @return changedMoney
	 */
	public float getChangedMoney() {
		return changedMoney;
	}

	/**
	 * @param changedMoney 要设置的 changedMoney
	 */
	public void setChangedMoney(float changedMoney) {
		this.changedMoney = changedMoney;
	}

	/**
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date 要设置的 date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
