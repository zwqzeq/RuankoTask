package com.ruanko.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import com.ruanko.model.Channel;
import com.ruanko.model.News;
import com.ruanko.service.RSSService;
import com.ruanko.service.UpdateThread;
import com.ruanko.xsgl.view.StatusBar;


/**
 * 创建窗口，事件处理，
 * RSS窗口类
 */
@SuppressWarnings("serial")
public class JMainFrame extends JFrame {

	//窗口属性
	private final static int WIDTH = 800;          //窗口宽度
	private final static int HEIGHT = 600;         //窗口高度
	private final static String TITLE = "RSS阅读器";//窗口标题

	//窗口组件
	@SuppressWarnings({ "unused", "rawtypes" })
	private JComboBox jcbChannel;                  //下拉列表框
	@SuppressWarnings("unused")
	private JButton jbRead;                        //读取新闻按钮
	private JTextArea jtaContent;                  //新闻内容的文本域
	private RSSService rssService;                 //业务逻辑对象
	private DefaultTableModel dtmTableModel;       //表格数据模型
	private JTable jtTable;                        //表格
	private JScrollPane jspContent;                //滚动窗格
	private JButton jbButton;                      //导出按钮
	private JButton jupdateButton;                 //更新按钮


	private List <News> newsList;                    //新闻内容列表
	private List<Channel> channelList;
	public ReadActionListener readActionLi;        //声明一个ReadActionListener类型的成员变量
	public  boolean bool[]={false,false,false,false}; //为四个频道创建一个bool类型的值初始状态都为false，如果菜单中的某个新闻频道（新闻频道序号从0到3，共四个新闻频道）被点击，那个序号对应的bool值就为true




	/**
	 * 由于关于新闻的四个菜单项都需要添加读取RSS文件的监听器，所以采用一般内部类
	 * 来处理读取RSS文件菜单项事件
	 * 类ReadActionListener属于一般内部类
	 * ReadActionListener类实现ActionListener接口，作为读取RSS文件的监听器类
	 */
	class ReadActionListener implements ActionListener {
		@Override//重写actionPerformed方法
		public void actionPerformed(ActionEvent e) {
			channelList = rssService.getchannelList();//获得新闻频道信息
			String commend = e.getActionCommand();                  //获得用户选择的菜单项名称
			for(int i = 0; i < channelList.size(); i++) {           //遍历菜单项列表
				if(channelList.get(i).getName().equals(commend)) {  //用户选择的菜单项名称与列表中的名称比较
					channelList.get(i).getFilePath();			    //根据用户选择的菜单项的名称，获得相应的文件路径 
					newsList = rssService.getNewsList(channelList.get(i).getFilePath());
					showTable(newsList);
			        bool[i]=true; //如果菜单中的某个新闻频道（新闻频道序号从0到3，共四个新闻频道）被点击，那个序号对应的bool值就为true
				}   
			}
			if("导出".equals(commend)) {
				if(rssService.save(newsList)) {
					JOptionPane.showMessageDialog(null, "新闻信息保存成功");
				} 
				else {
					JOptionPane.showMessageDialog(null, "新闻信息保存失败");
				} 
			}
			if("退出".equals(commend)) {
				System.exit(0);                            //退出程序
			}
			if("文档".equals(commend)) {
				int option = JOptionPane.showConfirmDialog(null, "Are you sure ？","温馨提示",JOptionPane.YES_NO_CANCEL_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					String option2 = JOptionPane.showInputDialog("Sorry,I don't Know,but you can go to ruanko to see about it！","Thank you");

					if(option2.equals("Thank you")) {
						JOptionPane.showMessageDialog(getJSPContent(),"You are welcome !");
					} else if (option2.equals("OK")) {
						JOptionPane.showMessageDialog(getJSPContent(),"Over!");
					} else {
						System.exit(0);//退出
					}
				}
			}
		}		
	}

	/**
	 * 构造方法
	 */
	public JMainFrame() {
		rssService = new RSSService();            //创建业务逻辑对象
		ImageIcon imageIcon = new ImageIcon("images/Rss.png");
		//	String string = imageIcon.toString();     //返回此图像的字符串表示形式  
		//	System.out.println(string);
		Image image = imageIcon.getImage();
		this.setIconImage(image);                 //设置标题图标
		setTitle(TITLE);                          //设置窗口标题
		setSize(WIDTH, HEIGHT);                   //设置窗口大小
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //设置窗口默认关闭操作
		this.setCenter();                         //调用窗口居中的方法
		this.setJMenuBar(getJMBMy());             //通过JFrame类中的setJMenuBar()方法，调用getJMBMy()方法，将菜单栏添加到窗口中
		this.setContentPane(getJPMain());         //调用getJPMain()方法将主面板添加到窗口
//		add(new StatusBar(),BorderLayout.SOUTH);//显示时间
		this.readActionLi = null;                 //对readActionLi初始化
	}

	/**
	 * 方法功能：使窗口居中
	 */
	private void setCenter() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
		int x = (screenSize.width - WIDTH)/2;     //窗口左上角横坐标
		int y = (screenSize.height - HEIGHT)/2;   //窗口左上角纵坐标
		this.setLocation(x, y);	                  //设置窗口显示的位置
	}

	/**
	 * 方法功能：创建主面板
	 * @return
	 */
	private JPanel getJPMain() {

		JPanel jpMain = new JPanel();             //创建一个主面板
		jpMain.setLayout(new BorderLayout());     //主面板设置为边界布局
		jpMain.add(getJTBMy(),BorderLayout.NORTH);         //将工具栏添加到主面板北部
		jpMain.add(getJSPClientArea(),BorderLayout.CENTER);//客户区添加到主面板中间	
		//jpMain.add(getJSBMy(),BorderLayout.SOUTH);//将带滚动窗格的新闻内容文本域添加到主面板南方	
		jpMain.add(new StatusBar(),BorderLayout.SOUTH);//这一行可以移到构造方法中
//		jpMain.add(new StatusBar(),"South");//这一行与上面一行等价		
		return jpMain;                            //返回主面板	
	}

	/**
	 * 方法功能：添加站点下拉框
	 * @return 
	 */
	//	private JComboBox getJCBChannel() {
	//		if(jcbChannel == null) {                 //如果下拉列表框为空
	//			jcbChannel = new JComboBox();        //创建一个下拉列表框
	//			List<Channel> channelList = rssService.getchannelList();//获得新闻频道信息
	//			//jcbChannel.addItem("腾讯 - 国际要闻");
	//			for(int i = 0; i < channelList.size(); i++) {
	//				jcbChannel.addItem(channelList.get(i));
	//			}
	//		}
	//		return jcbChannel;
	//
	//	}

	//	/**
	//	 * 方法功能：添加读取按钮
	//	 * @return jbRead
	//	 */
	//	private JButton getJBRead() {
	//		if(jbRead == null) { 
	//			jbRead = new JButton("读取");                 //创建读取按钮
	//			//ReadActionListener readActionListener = new ReadActionListener();//创建读取按钮监听器
	//			jbRead.addActionListener(new ActionListener() {//把事件监听器注册到读取按钮中
	//				public void actionPerformed(ActionEvent e) {
	//					//获得新闻列表
	//					Channel selectedChannel = (Channel)jcbChannel.getSelectedItem();
	//					String filePath = selectedChannel.getFilePath();
	//					newsList = rssService.getNewsList(filePath);
	//					//显示新闻内容
	//					showTable(newsList);
	//				}
	//			});
	//		}
	//		return jbRead;
	//
	//	}

	/**
	 * 方法功能：实现带滚动条的新闻内容文本区
	 * setCaretPosition(0)将光标定往到容器的第一个字符处，这个方法一般
	 * 是当读取大量的文字后能让容器从头开始显示文字，否则容器会将光标定位在最后一个个字符处，显示的当然也是文字的未尾
	 */
	private JScrollPane getJSPContent() {
		if(jtaContent == null) {
			jtaContent = new JTextArea();          //创建一个文本域
			jtaContent.setLineWrap(true);//setLineWrap(true)方法为自动换行，既当文字比控件的宽度还长时会自动换行
			jtaContent.setFont(new Font("华文行楷", 0, 22));
			jtaContent.setForeground(Color.orange);//文本域的前景色也就是在文本域中显示的字体的颜色
			jtaContent.setBackground(Color.black);//文本域的背景色也就是整个文本域的颜色
			jspContent = new JScrollPane(jtaContent);//将文本域添加到滚动窗格(创建带文本区的滚动面板)
			jspContent.setPreferredSize(new Dimension(780,260));//设置滚动窗格的大小
		}
		return jspContent;

	}

	//	/**
	//	 * 内部类 
	//	 */
	//	class ReadActionListener1 implements ActionListener {
	//
	//		@Override//重写actionPerformed方法
	//		public void actionPerformed(ActionEvent e) {
	//			// TODO 自动生成的方法存根
	//			Channel selectedChannel = (Channel)jcbChannel.getSelectedItem();//获取下拉列表框中用户的选择
	//			String filePath = selectedChannel.getFilePath();//获取用户选择的那一项的路径
	//			if(rssService.load(filePath) == null) {
	//				jtaContent.setText("读取失败");
	//			} else {
	//				jtaContent.setText("读取成功");
	//			} 
	//
	//		}
	//
	//	}

	//	/**
	//	 * 方法功能：北面面板
	//	 * @return
	//	 */
	//	private JPanel getjpNorth() {
	//		JPanel jpnorth = new JPanel();            //创建一个北面面板
	//		jpnorth.setLayout(new FlowLayout(FlowLayout.LEFT));//北面面板设置为左对齐
	//	    JLabel jlChannel = new JLabel("站点");     //创建一个标签
	//		jpnorth.add(jlChannel);                   //将标签添加到北面面板
	//		jpnorth.add(getJCBChannel());             //将下拉列表框添加到北面面板
	//		jpnorth.add(getJBRead());                 //将读取按钮添加到北面面板
	//		jpnorth.add(getJbButton());               //将导出按钮添加到北面面板
	//		return jpnorth;                           //返回北面面板	
	//	}

	/**
	 * 方法功能：创建一个带滚动条的滚动窗格
	 * @return
	 */
	public JScrollPane getJSPTable() {
		JScrollPane jspTable = null;
		if(jspTable == null) {
			dtmTableModel = new DefaultTableModel();//创建表格数据模型
			dtmTableModel.addColumn("主题");      //往表格数据模型中添加个列的标题
			dtmTableModel.addColumn("接收时间");
			dtmTableModel.addColumn("发布时间");
			dtmTableModel.addColumn("作者");
			jtTable = new JTable(dtmTableModel); //将表格数据模型添加到表格中		
			jtTable.setForeground(Color.green);  //表格的前景色也就是表格中字体的颜色
			jtTable.getTableHeader().setFont(new Font("宋体", 0, 22));
			jtTable.setFont(new Font("宋体",Font.BOLD,15));		
			jtTable.setBackground(Color.darkGray);//表格背景色
//			System.out.println("表格的行高为："+jtTable.getRowHeight());
			jtTable.setRowHeight(30);		

			/**
			 * 采用匿名内部类的形式为表格添加鼠标单击事件监听器
			 */
			jtTable.addMouseListener(new MouseAdapter() {

				/**
				 * 重写mouseClicked方法处理鼠标点击事件
				 */
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 1) {      //判断是否鼠标单击
						int selectedRow = jtTable.getSelectedRow();//获得鼠标单击处行的位置
						News selectedNews = (News)newsList.get(selectedRow);//获得选中的新闻信息
						jtaContent.setText(rssService.newsToString(selectedNews));//将选中的新闻信息显示到新闻内容文本域
					}
				}

			});
			jspTable = new JScrollPane(jtTable);//将表格添加到滚动窗格
		}
		return jspTable;//返回滚动窗格
	}

	/**
	 * 方法功能：将新闻信息显示到表格中
	 * @param newList
	 */
	public void showTable (List<News>newsList) {
		//清空表格的内容
		int rowCount = dtmTableModel.getRowCount();
		
		//表格中行数清空后退出循环
		while(rowCount > 0) {                        //表格数据模型中的行数大于零
			dtmTableModel.removeRow(0);              //removeRow()是删除第零行，行标从零开始数
			rowCount--;
		}
		//遍历新闻内容列表，将相应的新闻内容显示到表格中
		for(int i = 0; i < newsList.size(); i++) {
			News news = (News)newsList.get(i);
			//按指定的时间格式。获得当前日期
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String currentDate = dateFormat.format(date);
			//将新闻的标题。当前日期。发布时间和作者显示在表格中
			String [] data = {news.getTitle(),currentDate,news.getPubDate(),news.getAuthor()};
			dtmTableModel.addRow(data);
		}
	}

	/**
	 * 方法功能：创建导出按钮
	 * @return jbButton （返回导出按钮）
	 */
	private JButton getJbButton() {
		if(jbButton == null) {                            //判断按钮是否为空
			//jbButton = new JButton("导出");              //创建一个导出按钮
			ImageIcon imageIcon = new ImageIcon("images/export.png","这是工具栏图标");
			//System.out.println(imageIcon.getDescription());//获取图像的描述
			jbButton = new JButton(imageIcon);
			jbButton.setToolTipText("导出");

			/**
			 * 匿名内部类
			 */
			jbButton.addActionListener(new ActionListener() {
				@Override                              //重写actionperformed()方法
				public void actionPerformed(ActionEvent e) {
					if(rssService.save(newsList)) {
						JOptionPane.showMessageDialog(null, "新闻信息保存成功");
					} 
					else {
						JOptionPane.showMessageDialog(null, "新闻信息保存失败");
					}

				}
			});
		}
		return jbButton;
	}


	/**
	 * 创建更新按钮
	 */
	private JButton getUpdate(){
		if( jupdateButton == null){
			ImageIcon imageIcon = new ImageIcon("images/update.png","这是工具栏图标");
			//System.out.println(imageIcon.getDescription());//获取图像的描述
			jupdateButton = new JButton(imageIcon);
			jupdateButton.setToolTipText("更新");
			/**
			 * 匿名内部类
			 */
			jupdateButton.addActionListener(new ActionListener() {
				@Override                              //重写actionperformed()方法
				public void actionPerformed(ActionEvent e) {
					updateNews();
				}
			});
		}
		return jupdateButton;		

	}


	/**
	 * 方法功能：实现菜单栏的创建，并且添加相应的菜单及菜单项
	 * @return jmbMy
	 */
	private  JMenuBar getJMBMy() {
		JMenuItem[] jmi = new JMenuItem[4];          //创建一个数组，存放子菜单
		JMenuBar jmbMy = new JMenuBar();             //创建菜单栏
		JMenu jm1 = new JMenu("文件(S)");              //创建第一个主菜单,快捷键为S
		List<Channel> channelList = rssService.getchannelList();//获得新闻频道信息
		for(int i = 0; i < channelList.size(); i++) {
			jmi[i]= new JMenuItem(channelList.get(i).getName());//创建第一个主菜单的菜单项（子菜单） 
//			jmi[i].setIcon(new ImageIcon("D:\\Users\\zwqabc\\Workspaces\\MyEclipse 2017 CI\\RSSReader2\\images\\Rss.png"));//第一个主菜单下的第一个子菜单
			jmi[i].setIcon(new ImageIcon("E:\\软酷网作业\\Java\\学期实训RSS阅读器和银行信息管理系统\\RSSReader2\\images\\Rss.png"));//第一个主菜单下的第一个子菜单
			jm1.add(jmi[i]);                          //将子菜单添加到主菜单
			jm1.addSeparator();                       //在子菜单之间添加分隔线
			jmi[i].addActionListener(new ReadActionListener());//为菜单项注册监听器
		}		
		JMenuItem export = new JMenuItem("导出");       //创建导出（子菜单）
		JMenuItem exit = new JMenuItem("退出");         //创建退出（子菜单）
//		export.setIcon(new ImageIcon("D:\\Users\\zwqabc\\Workspaces\\MyEclipse 2017 CI\\RSSReader2\\images\\Rss.png"));//第一个主菜单下的第一个子菜单	
//		exit.setIcon(new ImageIcon("D:\\Users\\zwqabc\\Workspaces\\MyEclipse 2017 CI\\RSSReader2\\images\\Rss.png"));//第一个主菜单下的第一个子菜单
		export.setIcon(new ImageIcon("E:\\软酷网作业\\Java\\学期实训RSS阅读器和银行信息管理系统\\RSSReader2\\images\\Rss.png"));//第一个主菜单下的第一个子菜单	
		exit.setIcon(new ImageIcon("E:\\软酷网作业\\Java\\学期实训RSS阅读器和银行信息管理系统\\RSSReader2\\images\\Rss.png"));//第一个主菜单下的第一个子菜单

		
		export.addActionListener(new ReadActionListener());//为菜单项注册监听器
		exit.addActionListener(new ReadActionListener());//为菜单项注册监听器
		jm1.add(export);                                 //将导出（子菜单）添加到主菜单
		jm1.addSeparator();                              //在子菜单之间添加分隔线
		jm1.add(exit);                                   //将退出（子菜单）添加到主菜单
		jmbMy.add(jm1);                                  //将主菜单一添加到菜单栏
		JMenu jm2 = new JMenu("帮助(H)"); 	
		JMenuItem word = new JMenuItem("文档");           //创建导出（子菜单）
		jm2.add(word);
		jm1.add(jm2);
		jmbMy.add(jm2);                                  //将第二个主菜单添加到菜单栏
		word.addActionListener(new ReadActionListener());//为菜单项注册监听器

		//菜单项添加快捷键
		jmi[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,InputEvent.CTRL_MASK));//为子菜单（保存）建立快捷键Ctrl+1
		jmi[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,InputEvent.CTRL_MASK));//为子菜单（保存）建立快捷键Ctrl+2
		jmi[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,InputEvent.CTRL_MASK));//为子菜单（保存）建立快捷键Ctrl+3
		jmi[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,InputEvent.CTRL_MASK));//为子菜单（保存）建立快捷键Ctrl+4
		export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5,InputEvent.CTRL_MASK));//为子菜单（保存）建立快捷键Ctrl+5
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6,InputEvent.CTRL_MASK));//为子菜单（保存）建立快捷键Ctrl+6
		
		//菜单项设置热键（这和上面的快捷键达到的效果是相同的）
		jmi[0].setMnemonic('0');
		jmi[1].setMnemonic('1');
		jmi[2].setMnemonic('2');
		jmi[3].setMnemonic('3');
		export.setMnemonic('5');
		exit.setMnemonic('6');
		
		//为主菜单（文件）建立快捷键Alt+S
		jm1.setMnemonic(KeyEvent.VK_S);                   //为主菜单（文件）建立快捷键Alt+S
		jm2.setMnemonic(KeyEvent.VK_H);                   //为主菜单（帮助）建立快捷键Alt+H
		
		//为主菜单设置提示语句
		jm1.setToolTipText("Alt+S");
		jm2.setToolTipText("Alt+H");
        
		//为主菜单设置字体
		jm1.setFont(new Font("console",Font.BOLD, 14));
		jm2.setFont(new Font("console",Font.BOLD, 14));
		
		//为主菜单设置颜色
		jm1.setForeground(Color.blue);//字体颜色
		jm2.setForeground(Color.blue);
		return jmbMy;
		
	
	}
	


	/**
	 * 创建工具栏
	 */
	private JToolBar getJTBMy() {
		JToolBar jtbMy = new JToolBar();			       //创建工具栏
		jtbMy.add(getJbButton());                          //将导出按钮添加到工具栏
		jtbMy.add(getUpdate());                          //将更新按钮添加到工具栏
		jtbMy.setFloatable(false);                         //设置工具栏不可浮动
		//		jtbMy.setRollover(true);                          //设置转滚效果，鼠标移上是出现边框
		return jtbMy;                                      
	}

	/**
	 * 添加客户区
	 * 分隔面板：JSplitPane,为Javax.swing包中
	 * 方法功能：创建分隔面板，实现垂直分隔
	 * @return jspVertical
	 */
	private JSplitPane getJSPClientArea() {
		JSplitPane jspVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);//创建分隔面板实现垂直分隔
		jspVertical.setDividerLocation(280);		       //设置分隔条的位置
		jspVertical.setLeftComponent(getJSPTable());        //将表格添加到分隔面板的上部
		jspVertical.setRightComponent(getJSPContent());  	//将新闻内容文本区添加到分隔面板的下部
		return jspVertical;
	}



	/**
	 * 方法功能：点击更新按钮时更新新闻
	 */
	public void updateNews(){
		List<Channel> channelList = rssService.getchannelList();//获得新闻频道信息
		Thread th = new UpdateThread();      //创建线程
		th.start();                          //启动线程

		//遍历bool数组
		for(int x = 0; x < 4; x++) {
			if(bool[x]==true){              //如果某个序号下的bool值为true，那么这个序号对应下的频道被点击
				newsList = rssService.getNewsList(channelList.get(x).getFilePath());//获取该路径下的新闻
				showTable(newsList);        //将该频道更新后的新闻信息显示到表格中
			}
		}
	}
}
