package com.ruanko.impl;

import java.util.List;

import org.junit.Test;

import com.ruanko.dao.impl.FileDaoImpl;
import com.ruanko.model.News;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TestFileDaoimpl {
	List <News> newsList;
	
	@Test
	public void save() {		
		//准备测试数据
		
		boolean flag = false;

		//调用测试方法（执行测试过程）
		FileDaoImpl fi=new FileDaoImpl();
		flag =fi.save(newsList);
		//比较执行结果
		Assert.assertEquals(true,flag);
			
	}	
	
	
}
