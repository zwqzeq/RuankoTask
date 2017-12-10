package com.ruanko.model;

/**
 * ��ϵ����Ϣ
 * @author asus
 *
 */
public class Contact {
	private String number=null;                       //���
	private String name=null;                         //����
	private String phonenumber=null;                  //�ֻ���
	private String email=null;                        //����
	private String address=null;                      //��ַ
	private String gender=null;                       //�Ա�
	private String relationship=null;                 //��ϵ


	/**
	 * �޲ι��췽��
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

	//��ȡ���
	public String getNumber() {
		return number;
	}

	//���ñ��
	public void setNumber(String number) {
		this.number = number;
	}

	//��ȡ����
	public String getName() {
		return name;
	}

	//��������
	public void setName(String name) {
		this.name = name;
	}

	//��ȡ�ֻ���
	public String getPhonenumber() {
		return phonenumber;
	}

	//�����ֻ���
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	//��ȡ����
	public String getEmail() {
		return email;
	}

	//��������
	public void setEmail(String email) {
		this.email = email;
	}

	//��ȡ��ַ
	public String getAddress() {
		return address;
	}

	//���õ�ַ
	public void setAddress(String address) {
		this.address = address;
	}

	//��ȡ�Ա�
	public String getGender() {
		return gender;
	}

	//�����Ա�
	public void setGender(String gender) {
		this.gender = gender;
	}

	//��ȡ��ϵ
	public String getRelationship() {
		return relationship;
	}

	//���ù�ϵ
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	
	public String toString(){
		return (number+"##"+name+"##"+phonenumber+"##"+email+"##"+address+"##"+gender+"##"+relationship);
		
	}

}
