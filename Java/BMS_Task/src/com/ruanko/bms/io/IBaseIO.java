package com.ruanko.bms.io;

/**
 * 基本I/O操作类的泛型接口
 */
public interface IBaseIO<TYPE> {
	/**
	 * 保存模型方法
	 * @param obj
	 */
	public void save(TYPE obj);
	
}
