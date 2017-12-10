package com.ruanko;
//AppException类继承Exception类
@SuppressWarnings("serial")
public class AppException extends Exception{
	//属性
	private int exceptionCode;
	private String message;

	/**
	 * 构造方法
	 * @param message
	 */
	public AppException(String message) {
		// TODO 自动生成的构造函数存根
		this.message=message;
	}

	/**
	 * 构造方法
	 * @param exceptionCode
	 * @param message
	 */
	public AppException(int exceptionCode,String message){
		this.exceptionCode=exceptionCode;
		this.message=message;
	}

//	/**
//	 * 构造方法
//	 */
//	public AppException() {
//		// TODO 自动生成的构造函数存根
//	}

	//获取属性
	public int getExceptionCode() {
		return exceptionCode;
	}

	//设置属性
	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	
	//获取属性
	public String getMessage() {
		return message;
	}

	//设置属性
	public void setMessage(String message) {
		this.message = message;
	}


}
