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
		//׼����������
		
		boolean flag = false;

		//���ò��Է�����ִ�в��Թ��̣�
		FileDaoImpl fi=new FileDaoImpl();
		flag =fi.save(newsList);
		//�Ƚ�ִ�н��
		Assert.assertEquals(true,flag);
			
	}	
	
	
}
