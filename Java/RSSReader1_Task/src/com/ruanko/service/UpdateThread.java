package com.ruanko.service;

/**
 *  线程类
 */
public class UpdateThread extends Thread{
	
	private final static int DELAY_TIME = 300 * 1000;//更新新闻的时间间隔为5分钟一次
       
	@Override//重写run()方法
	public void run () { 
        while (true) {
        	//UpDate RSS
        	
        	try {
        		sleep(DELAY_TIME);//pause 5 minutes
        	} catch (InterruptedException e) {//捕获异常
        		e.printStackTrace();//输出异常信息
			}
        	
        }
		
		
		
        }
	
	
	
	
	
	
	
	
	
	
	/**
	 * 程序入口方法
	 * @param args
	 */
	public static void main(String [] args) {
		
	}
	
	
	
	
	
	
	
}
