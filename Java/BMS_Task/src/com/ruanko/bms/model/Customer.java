package com.ruanko.bms.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.ruanko.bms.io.CustomerIO;
import com.ruanko.bms.utils.Tools;

/**
 * 客户类
 */
public class Customer {
	// 成员属性
	private String serialNum;//流水号
	private String name;//姓名
	private String gender;//性别
	private Date birthday = null;//出生日期
	private String idNum;//身份证号
	private String tel;//手机号
	private String email;//邮箱
	private String address;//家庭住址
	private int credit;//信誉度
	private Date createDate = null;//创建日期
	
	// 构造方法，初始化属性
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
	 * 带参数的构造方法，初始化属性
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
		// 创建成功标志位
		boolean isSuccess = false;
		// 创建CustomerIO对象
		CustomerIO customerIO = new CustomerIO();
		
		// 判断是否重复开户
		if (!customerIO.exists(idNum)) {
			// 随机生成20位流水号
		    this.serialNum = generateSerialNum();
			// 部分业务相关属性赋值
			credit = 100;
			createDate = new Date();
			// 调用CustomerIO的方法，保存客户信息
			customerIO.save(this);
			isSuccess = true;
		}
		return isSuccess;
	}
	
	
	private String generateSerialNum() {
		// 创建Random对象
		Random random = new Random();
		// 创建日期格式化对象
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 将当前时间格式化为指定格式的字符串
		String serialNum = "A" + sdf.format(new Date());
		// 利用Random对象生成5位随机数
		for (int i = 0; i < 5; i++) {
			serialNum += random.nextInt(10);
		}
		// 返回生成的流水号
		return serialNum;
	}
	
	// 重写toString()方法
	public String toString() {
		// 创建日期格式化对象
		@SuppressWarnings("unused")
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
		// 将属性字段按规定格式拼接为字符串
		String str = serialNum + "##" + name + "##" + gender + "##" + Tools.formatDate(birthday) + "##" + idNum + "##" + tel + "##" + email + "##" + address + "##" + credit + "##" + Tools.formatDate(createDate);
		
		// 返回拼接好后的字符串
		return str;
	}
	
	/**
	 * @return serialNum
	 */
	public String getSerialNum() {
		return serialNum;
	}

	/**
	 * @param serialNum 要设置的 serialNum
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
	 * @param name 要设置的 name
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
	 * @param gender 要设置的 gender
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
	 * @param birthday 要设置的 birthday
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
	 * @param idNum 要设置的 idNum
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
	 * @param tel 要设置的 tel
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
	 * @param email 要设置的 email
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
	 * @param address 要设置的 address
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
	 * @param credit 要设置的 credit
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
	 * @param createDate 要设置的 createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
