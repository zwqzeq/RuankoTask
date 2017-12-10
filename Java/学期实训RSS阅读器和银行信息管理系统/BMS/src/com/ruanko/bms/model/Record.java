package com.ruanko.bms.model;

import java.util.Date;

import com.ruanko.bms.utils.Tools;

/**
 * ������¼��
 */
public class Record {
	
	private String type;//���п�����
	private String idNum;//���֤��
	private String no;//���п���
	private String desc;//�����������
	private float balance;//���
	private float changedMoney;//�������
	private Date date = null;//�����������
	
	/**
	 * ���췽��
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
	 * @param type Ҫ���õ� type
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
	 * @param idNum Ҫ���õ� idNum
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
	 * @param no Ҫ���õ� no
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
	 * @param desc Ҫ���õ� desc
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
	 * @param balance Ҫ���õ� balance
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
	 * @param changedMoney Ҫ���õ� changedMoney
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
	 * @param date Ҫ���õ� date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
