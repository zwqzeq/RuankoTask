package com.ruanko.bms.io;

/**
 * ����I/O������ķ��ͽӿ�
 */
public interface IBaseIO<TYPE> {
	/**
	 * ����ģ�ͷ���
	 * @param obj
	 */
	public void save(TYPE obj);
	
}
