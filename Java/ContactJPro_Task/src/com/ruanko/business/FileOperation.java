package com.ruanko.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.ruanko.model.Contact;


/**
 * 执行读取文件的操作步骤：
 * （1）创建file类的对象，并且声明输入流FileReader BufferedReader
 *     	File file = new File(filePath);              
		FileReader fr = null;                      
		BufferedReader br = null;                     
 * （2）打开输入流读数据
 *      fr = new FileReader(file);                 //打开输入流
	    br = new BufferedReader(fr); 
 * （3）按行读文件
 *      String data = null;                       //数据临时存储的对象                
		while((data = br.readLine()) != null) {   //从文件中逐行的读取数据（当读到文件末尾时值为null）
	    Contact contact = new Contact();      //创建一个Contact类对象contact
		String[]infor = data.split("##");    
 * （4）关闭输入流
 *   	br.close();                            //关闭输入流（从文件中读取数据叫输入流，写数据到文件中叫输出流）
		fr.close();
 *  
 *  
 */


/**
 * 将联系人实体类Contact的对象contact添加到列表的步骤：
 * （1）创建一个列表contacts（List contacts = new ArrayList();）
 * （2）创建一个实体类对象contact（Contact contact = new Contact()）;
 * （3）通过实体类的set方法为实体类的对象赋值
 * （4）通过列表的add()方法将实体类的对象添加到列表中
 */


/**
 *  从联系人列表result中获取联系人实体类对象的“姓名”，“手机号”，“email”值的步骤：
 * （1）使用for循环遍历列表result
 * （2）创建一个实体类的对象，并且取出列表中的元素赋值给实体类的对象
 * （3）通过实体类的get方法为获取实体类对象的相应的值
 */


/**
 * 文件操作类：新增联系人，查询联系人，导出联系人
 * @author zwqabc
 */
public class FileOperation {

	/**
	 * 功能：将联系人保存到指定的路径
	 * @param ContactInfo联系人信息
	 * @param filePath保存联系人路径
	 * @return 返回布尔类型值true，false
	 */
	public boolean saveContact(String contactInfo, String filePath){

		//打开文件
		File file = new File(filePath);              //创建文件对象（file）
		FileWriter fw = null;                        //声明输出流FileWriter
		PrintWriter out = null;                      //声明输出流PrintWriter
		try {                                        //打开输入流，写文件
			fw = new FileWriter(file,true);          //创建一个FileWriter对象，其中Boolean型参数true则表示将字节写入文件末尾处，而不是写入文件开始处。file参数表示要写入数据的File对象 
			out = new PrintWriter(fw);               //创建PrintWriter对象out，第一个参数为字符输出流，第二个参数为boolean 变量；如果为 true，则 println、printf 或 format 方法将刷新输出缓冲区
			out.println(contactInfo);                //使用println()方法将联系人信息写入文件中            
			out.flush();                             //刷新缓冲                   
			System.out.println("文件保存成功！");
			return true;  //能够执行到这一句说明前面的语句都执行完了，也就是文件保存成功，返回true //文件保存成功
		} catch (IOException e) {                    //捕获输入输出异常
			e.printStackTrace();                     //输出异常信息
			System.out.println("文件保存失败！");
			return false;                            //文件保存失败
		} finally {
			out.close();                             //关闭输出流，关闭的顺序和打开的顺序相反
			try {
				fw.close();                          //关闭输出流
			} catch (IOException e) {                 //捕获输入输出异常
				e.printStackTrace();                 //输出异常对象的信息
				return false;
			}
		}

	}

	/**
	 * 重载saveContact方法
	 * 功能：导出联系人
	 * @param contactInfor
	 * @param filePath
	 * @return 布尔类型true或false（返回true说明文件操作(写文件或读文件)成功）
	 */
	@SuppressWarnings("null")
	public boolean saveContact(List<?> contactInfor,String filePath) {

		File file = new File(filePath);              //根据路径找到文件并创建File类对象file
		FileWriter fw = null;                        //声明输出流FileWriter
		PrintWriter out = null;                      //声明输出流PrintWriter
		try {
			fw = new FileWriter(file,true);            //创建一个FileWriter对象，其中Boolean型参数true则表示将字节写入文件末尾处，而不是写入文件开始处。file参数表示要写入数据的File对象 
			out = new PrintWriter(fw);                 //创建PrintWriter对象out
			for(int i = 0;i<contactInfor.size();i++) {
				Contact contact = (Contact)contactInfor.get(i);//取出联系人信息
				String contactStr = contact.toString();
				out.println(contactStr);
			}
			out.flush();                              //刷新缓冲
			return true;                              //导出联系人信息成功
		} catch (IOException e) {                     //捕获输入输出异常
			e.printStackTrace();                      //输出异常信息
		} finally {                                   //关闭输出流，关闭顺序域打开顺序相反
			out.close();                              //关闭输出流out
		} try {
			fw.close();                               //关闭输出流fw
		} catch (IOException e) {                     //捕获输入输出异常
			e.printStackTrace();                      //捕获输入输出异常
			return false;                             //导出联系人信息时失败
		}
		return false;                                 //导出联系人信息时失败

	}


	/**
	 * 从指定的路径读取联系人信息并保存到列表中
	 * @param filePath读取联系人信息的路径
	 * @return 联系人信息列表
	 */
	public List<Contact> getContacts(String filePath) {
		List<Contact> contacts = new ArrayList<Contact>();              //创建一个ArrayList类对象contacts存储联系人信息              
		File file = new File(filePath);               //读取路径filePath处的文件
		FileReader fr = null;                         //定义文件读流对象
		BufferedReader br = null;                     //定义文件读缓存流对象
		try {
			fr = new FileReader(file);                //打开输入流
			br = new BufferedReader(fr);              //打开输入流
			String data = null;                       //数据临时存储的对象                
			while((data = br.readLine()) != null) {   //从文件中逐行的读取数据（当读到文件末尾时值为null）
				String[]infor = data.split("##");     /*使用split()方法解析联系人信息（编号，姓名等）
			                                                                                                            将联系人信息（编号，姓名等）以##分割 ，并将分割后的联系人信息（编号，姓名等）存放在字符串数组info中*/
				Contact contact = new Contact();      //创建一个Contact类对象contact
				contact.setNumber(infor[0]);          //contact中的成员变量number赋值   
				contact.setName(infor[1]);            //contact中的成员变量name赋值
				contact.setPhonenumber(infor[2]);     //contact中的成员变量phonenumber赋值
				contact.setEmail(infor[3]);           //contact中的成员变量email赋值
				contact.setAddress(infor[4]);         //contact中的成员变量address赋值
				contact.setGender(infor[5]);          //contact中的成员变量gender赋值
				contact.setRelationship(infor[6]);    //contact中的成员变量relationship赋值
				contacts.add(contact);                //将contact添加到集合中
				//contacts.add(infor);                //使用add()方法将分割后字符串数组添加到联系人列表中
			}
			return contacts;
		} catch (FileNotFoundException e) {
			e.printStackTrace();                       //输出异常信息
			return null;                      
		}catch (IOException e) {                       //捕获输入异常
			e.printStackTrace();                       //输出异常信息
			return null;                          
		} finally {
			try {                                      //关闭输入流关闭的顺序和打开的顺序相反
				br.close();                            //关闭输入流（从文件中读取数据叫输入流，写数据到文件中叫输出流）
				fr.close();                            //关闭输入流
			} catch (IOException e) {                  //捕获输入输出异常
				e.printStackTrace();                   //输出异常信息
				return null;
			}
		}

	}

	/**
	 * 通过输入姓名查询联系人方法
	 * @param filePath
	 * @param queryname
	 * @return 
	 */
	public List<Contact> getContacts(String filePath, String queryname) {
		List<Contact> contacts = new ArrayList<Contact>();               //存储联系人信息列表              
		File file = new File(filePath);                //读取路径的文件
		FileReader fr = null;                          //定义一个文件读流对象
		BufferedReader br = null;                      //定义一个文件读缓存流对象
		try {
			fr = new FileReader(file);                 //打开输入流
			br = new BufferedReader(fr);               //打开输入流
			String data = null;                        //数据临时存储的对象                
			while ((data = br.readLine()) != null) {   //从文件中逐行的读取数据（当读到文件末尾时值为null）
				String[] infor = data.split("##");      /*解析联系人信息，并将信息保存到contacts中 使用split()方法
			                                                                                                                 将联系人信息以##分割 ，并将分割后的联系人信息存放在字符串数组info中*/
				if(queryname.equals(infor[1])) {
					Contact contact = new Contact();   //创建一个Contact类的对象contact
					contact.setNumber(infor[0]);       //给Contact类的对象contact中的成员变量number赋值
					contact.setName(infor[1]);         //给Contact类的对象contact中的成员变量name赋值
					contact.setPhonenumber(infor[2]);  //给Contact类的对象contact中的成员变量phonenumber赋值
					contact.setEmail(infor[3]);        //给Contact类的对象contact中的成员变量email赋值
					contact.setAddress(infor[4]);      //给Contact类的对象contact中的成员变量address赋值
					contact.setGender(infor[5]);       //给Contact类的对象contact中的成员变量gender赋值
					contact.setRelationship(infor[6]);//给Contact类的对象contact中的成员变量relationship赋值
					contacts.add(contact);                //使用add()方法将分割后字符串数组添加到联系人列表中
				} 
				//				else {
				//					JOptionPane.showMessageDialog(null, "文件中不存在此联系人，请重新输入！");
				//					System.exit(0);                      //退出程序
				//				}
			}
			return contacts;                             //返回contacts集合
		} catch (FileNotFoundException e) {
			e.printStackTrace();                         //输出异常信息
			return null;                                 //文件操作失败

		} catch (IOException e) {                        //捕获输入异常
			e.printStackTrace();                         //输出异常信息
			return null;                                //文件操作失败

		} finally {
			try {                                       //关闭输入流关闭的顺序和打开的顺序相反
				br.close();                             //关闭输入流（从文件中读取数据叫输入流，写数据到文件中叫输出流）
				fr.close();                             //关闭输入流
			} catch (IOException e) {                   //捕获输入输出异常
				e.printStackTrace();                    //输出异常信息
				return null;                            //文件操作失败
			}

		}

	}

}







