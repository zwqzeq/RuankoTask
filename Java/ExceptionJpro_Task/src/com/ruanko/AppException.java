package com.ruanko;
//AppException��̳�Exception��
@SuppressWarnings("serial")
public class AppException extends Exception{
	//����
	private int exceptionCode;
	private String message;

	/**
	 * ���췽��
	 * @param message
	 */
	public AppException(String message) {
		// TODO �Զ����ɵĹ��캯�����
		this.message=message;
	}

	/**
	 * ���췽��
	 * @param exceptionCode
	 * @param message
	 */
	public AppException(int exceptionCode,String message){
		this.exceptionCode=exceptionCode;
		this.message=message;
	}

//	/**
//	 * ���췽��
//	 */
//	public AppException() {
//		// TODO �Զ����ɵĹ��캯�����
//	}

	//��ȡ����
	public int getExceptionCode() {
		return exceptionCode;
	}

	//��������
	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	
	//��ȡ����
	public String getMessage() {
		return message;
	}

	//��������
	public void setMessage(String message) {
		this.message = message;
	}


}
