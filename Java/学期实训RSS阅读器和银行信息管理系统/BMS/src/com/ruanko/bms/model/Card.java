package com.ruanko.bms.model;

import java.util.Date;

/**
 * 抽象类：卡类
 * 卡的基类
 */
public abstract class Card {
	
	//卡的属性
	protected String no;//卡号
	protected String password;//密码
	protected String idNum;//身份证号
	protected Date createDate = null;//办卡日期
	protected float bonusPoints;//积分
	protected float money;//金额
	public final static int SAVING_CARD = 1;//储蓄卡类型
	public final static int CREDIT_CARD = 2;//信用卡类型
	
	/**
	 * 构造方法
	 */
	public Card() {
		no = "";
		password = "";
		idNum = "";
		createDate = new Date();
		bonusPoints = 0;
		money = 0;
	}
	
	/**
	 * 卡办理
	 * @return 是否办理成功
	 */
	public boolean create() {
		return false;
		
	}
	
	/**
	 * 存款方法
	 * @param amount
	 * @return
	 */
	public abstract void deposit(float amount);
	
	/**
	 * 取款方法
	 * @param amount
	 * @return
	 */
	public abstract boolean withdraw(float amount);
	
	/**
	 * 消费方法
	 * @param amount
	 * @return
	 */
	public abstract boolean cash(float amount);
	
	// 载入卡信息方法
	public abstract boolean loadByNo(String no);
	public abstract boolean loadByNoAndPwd(String no, String pwd);

	/**
	 * @return no 卡号
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no 要设置的 no 卡号
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * @return password 密码
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return idNum 身份证号
	 */
	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	/**
	 * @return createDate 办卡日期
	 */
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return bonusPoints 积分
	 */
	public float getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(float bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	/**
	 * @return money 金额
	 */
	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

}
