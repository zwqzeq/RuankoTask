package com.ruanko.model;

/**
 * 联系人信息
 * @author asus
 *
 */
public class Contact {
	private String number=null;                       //编号
	private String name=null;                         //姓名
	private String phonenumber=null;                  //手机号
	private String email=null;                        //邮箱
	private String address=null;                      //地址
	private String gender=null;                       //性别
	private String relationship=null;                 //关系


	/**
	 * 无参构造方法
	 */
	public Contact(){
		number="";
		name="";
		phonenumber="";
		email="";
		address="";
		gender="";
		relationship="";
	}

	//获取编号
	public String getNumber() {
		return number;
	}

	//设置编号
	public void setNumber(String number) {
		this.number = number;
	}

	//获取姓名
	public String getName() {
		return name;
	}

	//设置姓名
	public void setName(String name) {
		this.name = name;
	}

	//获取手机号
	public String getPhonenumber() {
		return phonenumber;
	}

	//设置手机号
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	//获取邮箱
	public String getEmail() {
		return email;
	}

	//设置邮箱
	public void setEmail(String email) {
		this.email = email;
	}

	//获取地址
	public String getAddress() {
		return address;
	}

	//设置地址
	public void setAddress(String address) {
		this.address = address;
	}

	//获取性别
	public String getGender() {
		return gender;
	}

	//设置性别
	public void setGender(String gender) {
		this.gender = gender;
	}

	//获取关系
	public String getRelationship() {
		return relationship;
	}

	//设置关系
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	
	public String toString(){
		return (number+"##"+name+"##"+phonenumber+"##"+email+"##"+address+"##"+gender+"##"+relationship);
		
	}

}
