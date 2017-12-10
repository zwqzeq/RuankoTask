package com.ruanko;                                  //包

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;                         
import java.io.PrintWriter;


/**
 * BufferedReader：从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。 
 * 可以指定缓冲区的大小，或者可使用默认的大小。大多数情况下，默认值就足够大了。
 * 通常，Reader 所作的每个读取请求都会导致对底层字符或字节流进行相应的读取请求。因此，
 * 建议用 BufferedReader 包装所有其 read() 操作可能开销很高的 Reader（如 FileReader
 * 和 InputStreamReader）。例如， 
 * BufferedReader in = new BufferedReader(new FileReader("foo.in"));
 * 将缓冲指定文件的输入。如果没有缓冲，则每次调用 read() 或 readLine() 都会导致从文件中读取
 * 字节，并将其转换为字符后返回，而这是极其低效的。 
 */


/**
 * 创建一个文件操作类来读文件和写文件
 * 注意：读文件叫输入流，写文件叫输出流
 * 注意：要操作一个文件（读或写）必须先打开文件（File file=new File(filename);
 */
public class FileHandle {

	/**
	 * 读取文件
	 * @param filename 文件名(相对路径或绝对路径)
	 * @return
	 */
	public boolean readFile(String filename) {
		try {                                            //有潜在异常的语句放在try里面
			//打开文件
			File file = new File(filename);                //创建File类的对象file
			BufferedReader br = new BufferedReader(new FileReader(file));//构造读缓存流(字符输入流)
			//上面一行等价于下面的两行
			//FileReader fr = new FileReader(file);
			//BufferedReader br = new BufferedReader(fr);   

			String line = null;                            //line变量初始化（line用来存从文件中读取的内容）

			//读取文件
			while((line = br.readLine()) != null) {     //判断是否读取到文件内容
				System.out.println(line);               //输出读取到的文件内容，后换行
			}
			br.close();                                 //关闭文件
			return true;	    	                    //返回值为true表示读取文件成功
		} catch(FileNotFoundException e) {                //捕获文件未找到异常
			e.printStackTrace();                        //输出异常信息
		} catch(IOException e) {                          //捕获输入输出异常
			e.printStackTrace();                        //输出异常信息
		}
		return false;                                   //返回值为false表示读取文件失败
	}


	/**
	 * 写文件
	 * @param content
	 * @param filename
	 * @return
	 */
	public boolean writeFile(String content,String filename) {
		try {                                         //有潜在异常的语句放在try里面
			//打开文件
			File file=new File(filename);             //创建File类的对象file
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));//构造写缓存流BufferedWriter（属于字符输出流）//FileWriter(file,true)根据给定的 File 对象构造一个 FileWriter 对象。如果第二个参数为 true，则将字节写入文件末尾处，而不是写入文件开始处。	
			PrintWriter out = null;                  //声明输出流PrintWriter
			
			//bw.write(content);                       //写文件(不换行)	（写文件方法一：）		
			//bw.newLine();                            //实现换行

			                                         //写文件（方法二）
			out = new PrintWriter(bw);               //创建PrintWriter对象out，第一个参数为字符输出流，第二个参数为boolean 变量；如果为 true，则 println、printf 或 format 方法将刷新输出缓冲区
			out.println(content);                    //写文件(后换行)打印 String，然后终止该行。此方法的行为就像先调用 print(String) 然后调用 println() 一样。
			
			out.flush();                             //刷新缓冲
			bw.close();                              //关闭文件	
			out.close();
			return true;                             //表示文件读取成功
		} catch(IOException e) {                     //捕获输入输出异常
			e.printStackTrace();                     //输出异常信息
		}
		return false;                                //表示文件读取失败
	}

}
