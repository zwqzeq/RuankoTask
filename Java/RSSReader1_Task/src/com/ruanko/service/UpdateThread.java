package com.ruanko.service;

/**
 *  �߳���
 */
public class UpdateThread extends Thread{
	
	private final static int DELAY_TIME = 300 * 1000;//�������ŵ�ʱ����Ϊ5����һ��
       
	@Override//��дrun()����
	public void run () { 
        while (true) {
        	//UpDate RSS
        	
        	try {
        		sleep(DELAY_TIME);//pause 5 minutes
        	} catch (InterruptedException e) {//�����쳣
        		e.printStackTrace();//����쳣��Ϣ
			}
        	
        }
		
		
		
        }
	
	
	
	
	
	
	
	
	
	
	/**
	 * ������ڷ���
	 * @param args
	 */
	public static void main(String [] args) {
		
	}
	
	
	
	
	
	
	
}
