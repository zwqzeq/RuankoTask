package com.ruanko.impl;

import java.util.List;

import javax.swing.JScrollPane;

import org.junit.Test;

import com.ruanko.dao.impl.FileDaoImpl;
import com.ruanko.model.News;
import com.ruanko.view.JMainFrame;

import junit.framework.Assert;

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
