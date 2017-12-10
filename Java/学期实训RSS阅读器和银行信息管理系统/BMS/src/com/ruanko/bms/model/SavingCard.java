package com.ruanko.bms.model;

import java.util.Date;

import com.ruanko.bms.io.CustomerIO;
import com.ruanko.bms.io.RecordIO;
import com.ruanko.bms.io.SavingCardIO;
import com.ruanko.bms.utils.Tools;

/**
 * 储蓄卡类
 */
public class SavingCard extends Card {
	
	private float interestRate;//利率
	
	/**
	 * 构造方法
	 */
	public SavingCard() {
		interestRate = 0;
	}
	
	/**
	 * 卡办理
	 * @return 是否办理成功
	 */
	@Override
	public boolean create() {
		// 初始化对象
		SavingCardIO savingCardIO = new SavingCardIO();
		CustomerIO customerIO = new CustomerIO();
		boolean isSuccess = false;
		// 判断是否开户
		if (customerIO.exists(idNum)) {
			// 生成卡号
			no = Tools.generateCardNo(SAVING_CARD);
			// 为业务相关属性赋值
			bonusPoints = 200.f;
			createDate = new Date();
			interestRate = 0.01f;
			
			// 保存卡信息
			savingCardIO.save(this);
			
			isSuccess = true;
		}
		return isSuccess;
	}
	
	// 重写
	public String toString() {
		
		String str = no + "##" + password + "##" + idNum + "##" + Tools.formatDate(createDate) + "##" + bonusPoints + "##" + money + "##" + interestRate;
		return str;
	}
	
	/**
	 * @return interestRate
	 */
	public float getInterestRate() {
		return interestRate;
	}

	/**
	 * @param interestRate 要设置的 interestRate
	 */
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public void deposit(float amount) {
		SavingCardIO savingCardIO = new SavingCardIO();
		
		// 处理金额
		this.money += amount;
		// 创建操作记录相关对象
		RecordIO recordIO = new RecordIO();
		
		// 静态多态的使用
		recordIO.save("储蓄卡", idNum, no, "存款", money, amount, new Date());
		
		// 更新卡信息
		savingCardIO.update(this);
	}

	@Override
	public boolean withdraw(float amount) {
		SavingCardIO savingCardIO = new SavingCardIO();
		
		boolean isSuccess = false;
		if (money > amount) {
			money -= amount;
			
			// 创建操作记录相关对象
			RecordIO recordIO = new RecordIO();
			Record record = new Record();
			//填充操作记录信息
			record.setType("储蓄卡");
			record.setIdNum(idNum);
			record.setNo(no);
			record.setChangedMoney(-amount);
			record.setBalance(money);
			record.setDate(new Date());
			record.setDesc("取款");
			//保存操作记录
			recordIO.save(record);
			
			isSuccess = true;
		}
		// 更新卡信息
		savingCardIO.update(this);
		
		return isSuccess;
	}

	@Override
	public boolean cash(float amount) {
		SavingCardIO savingCardIO = new SavingCardIO();
		
		boolean isSuccess = false;
		
		if (money > amount) {
			money -= amount;
			
			// 创建操作记录相关对象
			RecordIO recordIO = new RecordIO();
			Record record = new Record();
			//填充操作记录信息
			record.setType("储蓄卡");
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
		savingCardIO.update(this);
		
		return isSuccess;
	}

	@Override
	public boolean loadByNo(String no) {
		boolean isSuccess = false;
		// 如果卡号为空则返回
		if (no == null || no.length() == 0) {
			return isSuccess;
		}
		
		// 初始化卡的I/O操作对象
		SavingCardIO savingCardIO = new SavingCardIO();
		// 根据卡号查找卡信息
		SavingCard savingCard = savingCardIO.findById(no);
		
		if (savingCard != null) {
			// 填充卡信息
			idNum = savingCard.getIdNum();
			bonusPoints = savingCard.getBonusPoints();
			createDate = savingCard.getCreateDate();
			interestRate = savingCard.getInterestRate();
			money = savingCard.getMoney();
			this.no = savingCard.getNo();
			password = savingCard.getPassword();
			
			isSuccess = true;
		}
	    	
		return isSuccess;
	}

	@Override
	public boolean loadByNoAndPwd(String no, String pwd) {
		boolean isSuccess = false;
		// 如果卡号或密码为空则返回
		if (no == null || pwd == null || no.length() == 0 || pwd.length() == 0) {
			return isSuccess;
		}
		
		// 初始化卡的I/O操作对象
		SavingCardIO savingCardIO = new SavingCardIO();
		// 根据卡号查找卡信息
		SavingCard savingCard = (SavingCard)savingCardIO.findById(no);
		
		if (savingCard != null && savingCard.getPassword().equals(pwd)) {
			// 填充卡信息
			idNum = savingCard.getIdNum();
			bonusPoints = savingCard.getBonusPoints();
			createDate = savingCard.getCreateDate();
			interestRate = savingCard.getInterestRate();
			money = savingCard.getMoney();
			this.no = savingCard.getNo();
			password = savingCard.getPassword();
			
			isSuccess = true;
		}
		
		return isSuccess;
	}
	

}
