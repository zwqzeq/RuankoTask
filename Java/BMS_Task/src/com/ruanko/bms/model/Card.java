package com.ruanko.bms.model;

import java.util.Date;

/**
 * ���Ļ���
 */
public abstract class Card {
	
	protected String no;//����
	protected String password;//����
	protected String idNum;//���֤��
	protected Date createDate = null;//�쿨����
	protected float bonusPoints;//����
	protected float money;//���
	
	public final static int SAVING_CARD = 1;//�������
	public final static int CREDIT_CARD = 2;//���ÿ�����
	
	/**
	 * ���췽��
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
	 * ������
	 * @return �Ƿ����ɹ�
	 */
	public boolean create() {
		return false;
		
	}
	/**
	 * ����
	 * @param amount
	 * @return
	 */
	public abstract void deposit(float amount);
	/**
	 * ȡ���
	 * @param amount
	 * @return
	 */
	public abstract boolean withdraw(float amount);
	/**
	 * ���ѷ���
	 * @param amount
	 * @return
	 */
	public abstract boolean cash(float amount);
	
	// ���뿨��Ϣ����
	public abstract boolean loadByNo(String no);
	public abstract boolean loadByNoAndPwd(String no, String pwd);

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
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password Ҫ���õ� password
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate Ҫ���õ� createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return bonusPoints
	 */
	public float getBonusPoints() {
		return bonusPoints;
	}

	/**
	 * @param bonusPoints Ҫ���õ� bonusPoints
	 */
	public void setBonusPoints(float bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	/**
	 * @return money
	 */
	public float getMoney() {
		return money;
	}

	/**
	 * @param money Ҫ���õ� money
	 */
	public void setMoney(float money) {
		this.money = money;
	}

}
