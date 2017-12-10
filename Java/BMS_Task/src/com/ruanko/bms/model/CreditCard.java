package com.ruanko.bms.model;

import java.util.Date;

import com.ruanko.bms.io.CreditCardIO;
import com.ruanko.bms.io.CustomerIO;
import com.ruanko.bms.io.RecordIO;
import com.ruanko.bms.utils.Tools;

/**
  * ���ÿ�����
  */
public class CreditCard extends Card {
	
	private float creditLimit;//���ö��
	private float chargeRate;//��������
	private int statementDay;//�˵���
	private int deadline;//��󻹿���
	
	/**
	 * ���췽��
	 */
	public CreditCard() {
		
		creditLimit = 0;
		chargeRate = 0;
		statementDay = 0;
		deadline = 0;
	}
	
	@Override
	public boolean create() {
		// ��ʼ������
		CreditCardIO creditCardIO = new CreditCardIO();
		CustomerIO customerIO = new CustomerIO();
		boolean isSuccess = false;
		// �ж��Ƿ񿪻�
		if (customerIO.exists(idNum)) {
			// ���ɿ���
			no = Tools.generateCardNo(CREDIT_CARD);
			// Ϊҵ��������Ը�ֵ
			bonusPoints = 200.f;
			createDate = new Date();
			chargeRate = 0.01f;
			
			//���濨��Ϣ
			creditCardIO.save(this);
			
			isSuccess = true;
		}
		return isSuccess;	
	}
	
	//��д
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
	 * @param creditLimit Ҫ���õ� creditLimit
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
	 * @param chargeRate Ҫ���õ� chargeRate
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
	 * @param statementDay Ҫ���õ� statementDay
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
	 * @param deadline Ҫ���õ� deadline
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
	 * ������
	 */
	@Override
	public void deposit(float amount) {
		CreditCardIO creditCardIO = new CreditCardIO();
		// ������
		this.money += amount;
		
		// ����������¼��ض���
		RecordIO recordIO = new RecordIO();
		Record record = new Record();
		//��������¼��Ϣ
		record.setType("���ÿ�");
		record.setIdNum(idNum);
		record.setNo(no);
		record.setChangedMoney(amount);
		record.setBalance(money);
		record.setDate(new Date());
		record.setDesc("���");
		
		//���������¼
		recordIO.save(record);
		
		// ���¿���Ϣ
		creditCardIO.update(this);
	}

	/**
	 * ȡ�����
	 */
	@Override
	public boolean withdraw(float amount) {
		CreditCardIO creditCardIO = new CreditCardIO();
		boolean isSuccess = false;
		//����������
		float fee = amount * chargeRate;
		//���������ý��
		float balance = money + creditLimit;
		
		if (balance > (amount + fee)) {
			//�������
			money -= amount;
			
			// ����������¼��ض���
			RecordIO recordIO = new RecordIO();
			Record record = new Record();
			//��������¼��Ϣ
			record.setType("���ÿ�");
			record.setIdNum(idNum);
			record.setNo(no);
			record.setChangedMoney(-amount);
			record.setBalance(money);
			record.setDate(new Date());
			record.setDesc("ȡ��");
			//���������¼
			recordIO.save(record);
			
			//�۳�������
			money -= fee;
			// ��������¼��Ϣ
			record.setChangedMoney(-fee);
			record.setBalance(money);
			record.setDesc("ȡ��������");
			//���������¼
			recordIO.save(record);
			
			isSuccess = true;
		}
		// ���¿���Ϣ
		creditCardIO.update(this);
		
		return isSuccess;
	}

	/**
	 * ���Ѳ���
	 */
	@Override
	public boolean cash(float amount) {
		CreditCardIO creditCardIO = new CreditCardIO();
		
		boolean isSuccess = false;
		
		//���������ý��
		float balance = money + creditLimit;
		
		if (balance > amount) {
			money -= amount;
			// ����������¼��ض���
			RecordIO recordIO = new RecordIO();
			Record record = new Record();
			//��������¼��Ϣ
			record.setType("���ÿ�");
			record.setIdNum(idNum);
			record.setNo(no);
			record.setChangedMoney(-amount);
			record.setBalance(money);
			record.setDate(new Date());
			record.setDesc("����");
			//���������¼
			recordIO.save(record);
			
			isSuccess = true;
		}
		// ���¿���Ϣ
		creditCardIO.update(this);
		
		return isSuccess;
	}

	/**
	 * ���ݿ������뿨��Ϣ
	 */
	@Override
	public boolean loadByNo(String no) {
		boolean isSuccess = false;
		// �������Ϊ���򷵻�
		if (no == null || no.length() == 0) {
			return isSuccess;
		}
		
		// ��ʼ������I/O��������
		CreditCardIO creditCardIO = new CreditCardIO();
		// ���ݿ��Ų��ҿ���Ϣ
		CreditCard creditCard = creditCardIO.findById(no);
		
		if (creditCard != null) {
			// ��俨��Ϣ
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
	 * ���ݿ��ź��������뿨��Ϣ
	 */
	@Override
	public boolean loadByNoAndPwd(String no, String pwd) {
		boolean isSuccess = false;
		// ������Ż�����Ϊ���򷵻�
		if (no == null || pwd == null || no.length() == 0 || pwd.length() == 0) {
			return isSuccess;
		}
		
		// ��ʼ������I/O��������
		CreditCardIO creditCardIO = new CreditCardIO();
		// ���ݿ��Ų��ҿ���Ϣ
		CreditCard creditCard = (CreditCard)creditCardIO.findById(no);
		
		if (creditCard != null && creditCard.getPassword().equals(pwd)) {
			// ��俨��Ϣ
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
