package com.ruanko.bms.model;

import java.util.Date;

import com.ruanko.bms.io.CreditCardIO;
import com.ruanko.bms.io.CustomerIO;
import com.ruanko.bms.io.RecordIO;
import com.ruanko.bms.utils.Tools;

/**
  * 信用卡类型
  */
public class CreditCard extends Card {
	
	private float creditLimit;//信用额度
	private float chargeRate;//手续费率
	private int statementDay;//账单日
	private int deadline;//最后还款日
	
	/**
	 * 构造方法
	 */
	public CreditCard() {
		
		creditLimit = 0;
		chargeRate = 0;
		statementDay = 0;
		deadline = 0;
	}
	
	@Override
	public boolean create() {
		// 初始化对象
		CreditCardIO creditCardIO = new CreditCardIO();
		CustomerIO customerIO = new CustomerIO();
		boolean isSuccess = false;
		// 判断是否开户
		if (customerIO.exists(idNum)) {
			// 生成卡号
			no = Tools.generateCardNo(CREDIT_CARD);
			// 为业务相关属性赋值
			bonusPoints = 200.f;
			createDate = new Date();
			chargeRate = 0.01f;
			
			//保存卡信息
			creditCardIO.save(this);
			
			isSuccess = true;
		}
		return isSuccess;	
	}
	
	//重写
	public String toString() {
		String str = no + "##" + password + "##" + idNum + "##" + Tools.formatDate(createDate) + "##" + bonusPoints + "##" + money + "##" + creditLimit + "##" + chargeRate + "##" + statementDay + "##" + deadline;
		return str;
	}

	/**
	 * @return creditLimit
	 */
	public float getCreditLimit() {
		return creditLimit;
	}


	/**
	 * @param creditLimit 要设置的 creditLimit
	 */
	public void setCreditLimit(float creditLimit) {
		this.creditLimit = creditLimit;
	}


	/**
	 * @return chargeRate
	 */
	public float getChargeRate() {
		return chargeRate;
	}


	/**
	 * @param chargeRate 要设置的 chargeRate
	 */
	public void setChargeRate(float chargeRate) {
		this.chargeRate = chargeRate;
	}


	/**
	 * @return statementDay
	 */
	public int getStatementDay() {
		return statementDay;
	}


	/**
	 * @param statementDay 要设置的 statementDay
	 */
	public void setStatementDay(int statementDay) {
		this.statementDay = statementDay;
	}


	/**
	 * @return deadline
	 */
	public int getDeadline() {
		return deadline;
	}


	/**
	 * @param deadline 要设置的 deadline
	 */
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}


	/**
	 * @return creditCard
	 */
	public static int getCreditCard() {
		return CREDIT_CARD;
	}
    
	/**
	 * 存款操作
	 */
	@Override
	public void deposit(float amount) {
		CreditCardIO creditCardIO = new CreditCardIO();
		// 处理金额
		this.money += amount;
		
		// 创建操作记录相关对象
		RecordIO recordIO = new RecordIO();
		Record record = new Record();
		//填充操作记录信息
		record.setType("信用卡");
		record.setIdNum(idNum);
		record.setNo(no);
		record.setChangedMoney(amount);
		record.setBalance(money);
		record.setDate(new Date());
		record.setDesc("存款");
		
		//保存操作记录
		recordIO.save(record);
		
		// 更新卡信息
		creditCardIO.update(this);
	}

	/**
	 * 取款操作
	 */
	@Override
	public boolean withdraw(float amount) {
		CreditCardIO creditCardIO = new CreditCardIO();
		boolean isSuccess = false;
		//计算手续费
		float fee = amount * chargeRate;
		//计算最大可用金额
		float balance = money + creditLimit;
		
		if (balance > (amount + fee)) {
			//减少余额
			money -= amount;
			
			// 创建操作记录相关对象
			RecordIO recordIO = new RecordIO();
			Record record = new Record();
			//填充操作记录信息
			record.setType("信用卡");
			record.setIdNum(idNum);
			record.setNo(no);
			record.setChangedMoney(-amount);
			record.setBalance(money);
			record.setDate(new Date());
			record.setDesc("取款");
			//保存操作记录
			recordIO.save(record);
			
			//扣除手续费
			money -= fee;
			// 填充操作记录信息
			record.setChangedMoney(-fee);
			record.setBalance(money);
			record.setDesc("取款手续费");
			//保存操作记录
			recordIO.save(record);
			
			isSuccess = true;
		}
		// 更新卡信息
		creditCardIO.update(this);
		
		return isSuccess;
	}

	/**
	 * 消费操作
	 */
	@Override
	public boolean cash(float amount) {
		CreditCardIO creditCardIO = new CreditCardIO();
		
		boolean isSuccess = false;
		
		//计算最大可用金额
		float balance = money + creditLimit;
		
		if (balance > amount) {
			money -= amount;
			// 创建操作记录相关对象
			RecordIO recordIO = new RecordIO();
			Record record = new Record();
			//填充操作记录信息
			record.setType("信用卡");
			record.setIdNum(idNum);
			record.setNo(no);
			record.setChangedMoney(-amount);
			record.setBalance(money);
			record.setDate(new Date());
			record.setDesc("消费");
			//保存操作记录
			recordIO.save(record);
			
			isSuccess = true;
		}
		// 更新卡信息
		creditCardIO.update(this);
		
		return isSuccess;
	}

	/**
	 * 根据卡号载入卡信息
	 */
	@Override
	public boolean loadByNo(String no) {
		boolean isSuccess = false;
		// 如果卡号为空则返回
		if (no == null || no.length() == 0) {
			return isSuccess;
		}
		
		// 初始化卡的I/O操作对象
		CreditCardIO creditCardIO = new CreditCardIO();
		// 根据卡号查找卡信息
		CreditCard creditCard = creditCardIO.findById(no);
		
		if (creditCard != null) {
			// 填充卡信息
			idNum = creditCard.getIdNum();
			bonusPoints = creditCard.getBonusPoints();
			createDate = creditCard.getCreateDate();
			creditLimit = creditCard.getCreditLimit();
			chargeRate = creditCard.getChargeRate();
			statementDay = creditCard.getStatementDay();
			deadline = creditCard.getDeadline();
			money = creditCard.getMoney();
			this.no = creditCard.getNo();
			password = creditCard.getPassword();
			
			isSuccess = true;
		}
		
		return isSuccess;
	}

	/**
	 * 根据卡号和密码载入卡信息
	 */
	@Override
	public boolean loadByNoAndPwd(String no, String pwd) {
		boolean isSuccess = false;
		// 如果卡号或密码为空则返回
		if (no == null || pwd == null || no.length() == 0 || pwd.length() == 0) {
			return isSuccess;
		}
		
		// 初始化卡的I/O操作对象
		CreditCardIO creditCardIO = new CreditCardIO();
		// 根据卡号查找卡信息
		CreditCard creditCard = (CreditCard)creditCardIO.findById(no);
		
		if (creditCard != null && creditCard.getPassword().equals(pwd)) {
			// 填充卡信息
			idNum = creditCard.getIdNum();
			bonusPoints = creditCard.getBonusPoints();
			createDate = creditCard.getCreateDate();
			creditLimit = creditCard.getCreditLimit();
			chargeRate = creditCard.getChargeRate();
			statementDay = creditCard.getStatementDay();
			deadline = creditCard.getDeadline();
			money = creditCard.getMoney();
			this.no = creditCard.getNo();
			password = creditCard.getPassword();
			
			isSuccess = true;
		}
		
		return isSuccess;
	}

}
