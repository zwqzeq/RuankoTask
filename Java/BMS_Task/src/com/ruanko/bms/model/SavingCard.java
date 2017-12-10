package com.ruanko.bms.model;

import java.util.Date;

import com.ruanko.bms.io.CustomerIO;
import com.ruanko.bms.io.RecordIO;
import com.ruanko.bms.io.SavingCardIO;
import com.ruanko.bms.utils.Tools;

/**
 * �����
 */
public class SavingCard extends Card {
	
	private float interestRate;//����
	
	/**
	 * ���췽��
	 */
	public SavingCard() {
		interestRate = 0;
	}
	
	/**
	 * ������
	 * @return �Ƿ����ɹ�
	 */
	@Override
	public boolean create() {
		// ��ʼ������
		SavingCardIO savingCardIO = new SavingCardIO();
		CustomerIO customerIO = new CustomerIO();
		boolean isSuccess = false;
		// �ж��Ƿ񿪻�
		if (customerIO.exists(idNum)) {
			// ���ɿ���
			no = Tools.generateCardNo(SAVING_CARD);
			// Ϊҵ��������Ը�ֵ
			bonusPoints = 200.f;
			createDate = new Date();
			interestRate = 0.01f;
			
			// ���濨��Ϣ
			savingCardIO.save(this);
			
			isSuccess = true;
		}
		return isSuccess;
	}
	
	// ��д
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
	 * @param interestRate Ҫ���õ� interestRate
	 */
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public void deposit(float amount) {
		SavingCardIO savingCardIO = new SavingCardIO();
		
		// ������
		this.money += amount;
		// ����������¼��ض���
		RecordIO recordIO = new RecordIO();
		
		// ��̬��̬��ʹ��
		recordIO.save("���", idNum, no, "���", money, amount, new Date());
		
		// ���¿���Ϣ
		savingCardIO.update(this);
	}

	@Override
	public boolean withdraw(float amount) {
		SavingCardIO savingCardIO = new SavingCardIO();
		
		boolean isSuccess = false;
		if (money > amount) {
			money -= amount;
			
			// ����������¼��ض���
			RecordIO recordIO = new RecordIO();
			Record record = new Record();
			//��������¼��Ϣ
			record.setType("���");
			record.setIdNum(idNum);
			record.setNo(no);
			record.setChangedMoney(-amount);
			record.setBalance(money);
			record.setDate(new Date());
			record.setDesc("ȡ��");
			//���������¼
			recordIO.save(record);
			
			isSuccess = true;
		}
		// ���¿���Ϣ
		savingCardIO.update(this);
		
		return isSuccess;
	}

	@Override
	public boolean cash(float amount) {
		SavingCardIO savingCardIO = new SavingCardIO();
		
		boolean isSuccess = false;
		
		if (money > amount) {
			money -= amount;
			
			// ����������¼��ض���
			RecordIO recordIO = new RecordIO();
			Record record = new Record();
			//��������¼��Ϣ
			record.setType("���");
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
		savingCardIO.update(this);
		
		return isSuccess;
	}

	@Override
	public boolean loadByNo(String no) {
		boolean isSuccess = false;
		// �������Ϊ���򷵻�
		if (no == null || no.length() == 0) {
			return isSuccess;
		}
		
		// ��ʼ������I/O��������
		SavingCardIO savingCardIO = new SavingCardIO();
		// ���ݿ��Ų��ҿ���Ϣ
		SavingCard savingCard = savingCardIO.findById(no);
		
		if (savingCard != null) {
			// ��俨��Ϣ
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
		// ������Ż�����Ϊ���򷵻�
		if (no == null || pwd == null || no.length() == 0 || pwd.length() == 0) {
			return isSuccess;
		}
		
		// ��ʼ������I/O��������
		SavingCardIO savingCardIO = new SavingCardIO();
		// ���ݿ��Ų��ҿ���Ϣ
		SavingCard savingCard = (SavingCard)savingCardIO.findById(no);
		
		if (savingCard != null && savingCard.getPassword().equals(pwd)) {
			// ��俨��Ϣ
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
