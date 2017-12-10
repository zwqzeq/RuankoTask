package com.ruanko.bms.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.ruanko.bms.io.CustomerIO;
import com.ruanko.bms.utils.Tools;

/**
 * �ͻ���
 */
public class Customer {
	// ��Ա����
	private String serialNum;//��ˮ��
	private String name;//����
	private String gender;//�Ա�
	private Date birthday = null;//��������
	private String idNum;//���֤��
	private String tel;//�ֻ���
	private String email;//����
	private String address;//��ͥסַ
	private int credit;//������
	private Date createDate = null;//��������
	
	// ���췽������ʼ������
	public Customer() {
		serialNum = "";
		name = "";
		gender = "";
		birthday = new Date();
		idNum = "";
		tel = "";
		email = "";
		address = "";
		credit = 0;
		createDate = new Date();	
	}
	
	/**
	 * �������Ĺ��췽������ʼ������
	 * @param name
	 * @param gender
	 * @param birthday
	 * @param idNum
	 * @param tel
	 * @param email
	 * @param address
	 */
	public Customer(String name, String gender, Date birthday, String idNum, String tel, String email, String address) {
		super();
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.idNum = idNum;
		this.tel = tel;
		this.email = email;
		this.address = address;
		
		serialNum = "";
		credit = 0;
		createDate = new Date();
	}

	public boolean create() {
		// �����ɹ���־λ
		boolean isSuccess = false;
		// ����CustomerIO����
		CustomerIO customerIO = new CustomerIO();
		
		// �ж��Ƿ��ظ�����
		if (!customerIO.exists(idNum)) {
			// �������20λ��ˮ��
		    this.serialNum = generateSerialNum();
			// ����ҵ��������Ը�ֵ
			credit = 100;
			createDate = new Date();
			// ����CustomerIO�ķ���������ͻ���Ϣ
			customerIO.save(this);
			isSuccess = true;
		}
		return isSuccess;
	}
	
	
	private String generateSerialNum() {
		// ����Random����
		Random random = new Random();
		// �������ڸ�ʽ������
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// ����ǰʱ���ʽ��Ϊָ����ʽ���ַ���
		String serialNum = "A" + sdf.format(new Date());
		// ����Random��������5λ�����
		for (int i = 0; i < 5; i++) {
			serialNum += random.nextInt(10);
		}
		// �������ɵ���ˮ��
		return serialNum;
	}
	
	// ��дtoString()����
	public String toString() {
		// �������ڸ�ʽ������
		@SuppressWarnings("unused")
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
		// �������ֶΰ��涨��ʽƴ��Ϊ�ַ���
		String str = serialNum + "##" + name + "##" + gender + "##" + Tools.formatDate(birthday) + "##" + idNum + "##" + tel + "##" + email + "##" + address + "##" + credit + "##" + Tools.formatDate(createDate);
		
		// ����ƴ�Ӻú���ַ���
		return str;
	}
	
	/**
	 * @return serialNum
	 */
	public String getSerialNum() {
		return serialNum;
	}

	/**
	 * @param serialNum Ҫ���õ� serialNum
	 */
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Ҫ���õ� name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender Ҫ���õ� gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday Ҫ���õ� birthday
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	 * @return tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel Ҫ���õ� tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email Ҫ���õ� email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address Ҫ���õ� address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return credit
	 */
	public int getCredit() {
		return credit;
	}

	/**
	 * @param credit Ҫ���õ� credit
	 */
	public void setCredit(int credit) {
		this.credit = credit;
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

}
