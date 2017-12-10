package com.ruanko.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import javax.swing.table.DefaultTableModel;
import com.ruanko.model.Channel;
import com.ruanko.model.News;
import com.ruanko.service.RSSService;

/**
 * RSS窗口类
 */
public class JMainFrame extends JFrame {

	//窗口属性
	private final static int WIDTH = 800;          //窗口宽度
	private final static int HEIGHT = 600;         //窗口高度
	private final static String TITLE = "RSS阅读器";//窗口标题

	//窗口组件
	private JComboBox jcbChannel;                  //下拉列表框
	private JButton jbRead;                        //读取新闻按钮
	private JTextArea jtaContent;                  //新闻内容的文本域
	private RSSService rssService;                 //业务逻辑对象
	private DefaultTableModel dtmTableModel;       //表格数据模型
	private JTable jtTable;                        //表格
	private JScrollPane jspContent;                //滚动窗格
	private JButton jbButton;                      //导出按钮

	private List<News>newsList;                    //新闻内容列表
	public ReadActionListener readActionLi;        //声明一个ReadActionListener类型的成员变量



	/**
	 * 由于关于新闻的四个菜单项都需要添加读取RSS文件的监听器，所以采用一般内部类
	 * 来处理读取RSS文件菜单项事件
	 * 类ReadActionListener属于一般内部类
	 * ReadActionListener类实现ActionListener接口，作为读取RSS文件的监听器类
	 */
	class ReadActionListener implements ActionListener {
		@Override//重写actionPerformed方法
		public void actionPerformed(ActionEvent e) {
			List<Channel> channelList = rssService.getchannelList();//获得新闻频道信息
			String commend = e.getActionCommand();                //获得用户选择的菜单项名称
			for(int i = 0; i < channelList.size(); i++) {         //遍历菜单项列表
				if(channelList.get(i).getName().equals(commend)) {//用户选择的菜单项名称与列表中的名称比较
					channelList.get(i).getFilePath();			//根据用户选择的菜单项的名称，获得相应的文件路径 
					newsList = rssService.getNewsList(channelList.get(i).getFilePath());
					showTable(newsList);
				}   
			}
			
			//				if(channelList.get(0).getName().equals(commend)) {
			//					newsList = rssService.getNewsList(channelList.get(0).getFilePath());
			//					showTable(newsList); //显示新闻内容
			//				}




//			if("腾讯网 - 军事频道".equals(commend)) {
//				newsList = rssService.getNewsList("NewsFiles/rss_milit.xml"); 
//				showTable(newsList);
//			}
//			if("环球网 - 国际热点".equals(commend)) {
//				newsList = rssService.getNewsList("NewsFiles/hot.xml"); 
//				showTable(newsList);
//			}
//			if("新浪 - 体育新闻".equals(commend)) {
//				newsList = rssService.getNewsList("NewsFiles/sports.xml"); 
//				showTable(newsList);
//			}
//			if("新浪 - 社会新闻".equals(commend)) {
//				newsList = rssService.getNewsList("NewsFiles/focus15.xml"); 
//				showTable(newsList);
//			}
			if("导出".equals(commend)){
				if(rssService.save(newsList)) {
					JOptionPane.showMessageDialog(null, "新闻信息保存成功");
				} 
				else {
					JOptionPane.showMessageDialog(null, "新闻信息保存失败");
				} 
			}
			if("退出".equals(commend)){
				System.exit(0);                            //退出程序
			}

		}		
	}


	/**
	 * 构造方法
	 */
	public JMainFrame() {
		rssService = new RSSService();            //创建业务逻辑对象
		ImageIcon imageIcon = new ImageIcon("images/Rss.png");
		Image image = imageIcon.getImage();
		this.setIconImage(image);                 //设置标题图标
		setTitle(TITLE);                          //设置窗口标题
		setSize(WIDTH, HEIGHT);                   //设置窗口大小
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //设置窗口默认关闭操作
		this.setCenter();                         //调用窗口居中的方法
		this.setJMenuBar(getJMBMy());             //通过JFrame类中的setJMenuBar()方法，调用getJMBMy()方法，将菜单栏添加到窗口中
		this.setContentPane(getJPMain());         //调用getJPMain()方法将主面板添加到窗口
		add(new com.ruanko.xsgl.view.StatusBar(),BorderLayout.SOUTH);//显示时间
		this.readActionLi = null;                   //对readActionLi初始化

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
		jpMain.add(getJTBMy(),BorderLayout.NORTH);//将北面面板添加到主面板
		jpMain.add(getJSPClientArea(),BorderLayout.CENTER);//将带滚动窗格的表格添加到主面板中间	
		//jpMain.add(getJSBMy(),BorderLayout.SOUTH);//将带滚动窗格的新闻内容文本域添加到主面板南方
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
			jspContent = new JScrollPane(jtaContent);//将文本域添加到滚动窗格(创建带文本区的滚动面板)
			jspContent.setPreferredSize(new Dimension(780,260));//设置滚动窗格的大小
		}

		return jspContent;

	}

	/**
	 * 内部类 
	 */
	class ReadActionListener1 implements ActionListener{

		@Override//重写actionPerformed方法
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			Channel selectedChannel = (Channel)jcbChannel.getSelectedItem();//获取下拉列表框中用户的选择
			String filePath = selectedChannel.getFilePath();//获取用户选择的那一项的路径
			if(rssService.load(filePath) == null) {
				jtaContent.setText("读取失败");
			} else {
				jtaContent.setText("读取成功");
			} 

		}

	}

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
	private JScrollPane getJSPTable() {
		JScrollPane jspTable = null;
		if(jspTable == null) {
			dtmTableModel = new DefaultTableModel();//创建表格数据模型
			dtmTableModel.addColumn("主题");      //往表格数据模型中添加个列的标题
			dtmTableModel.addColumn("接收时间");
			dtmTableModel.addColumn("发布时间");
			dtmTableModel.addColumn("作者");
			jtTable = new JTable(dtmTableModel); //将表格数据模型添加到表格中
			jspTable = new JScrollPane(jtTable); //将表格添加到滚动窗格	    

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
						News selectedNews = newsList.get(selectedRow);//获得选中的新闻信息
						jtaContent.setText(rssService.newsToString(selectedNews));//将选中的新闻信息显示到新闻内容文本域
					}
				}

			});

			jspTable = new JScrollPane(jtTable);

		}
		return jspTable;
	}

	/**
	 * 方法功能：将新闻信息显示到表格中
	 * @param newList
	 */
	private void showTable (List<News>newsList) {
		//清空表格的内容
		int rowCount = dtmTableModel.getRowCount();
		while(rowCount > 0) {                //表格数据模型中的行数大于零
			dtmTableModel.removeRow(0);      //removeRow()是删除本行
			rowCount--;
		}
		//遍历新闻内容列表，将相应的新闻内容显示到表格中
		for(int i = 0; i < newsList.size(); i++) {
			News news = newsList.get(i);
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
		if(jbButton == null) {                          //判断按钮是否为空
			//jbButton = new JButton("导出");              //创建一个导出按钮
			ImageIcon imageIcon = new ImageIcon("images/export.png");
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
			jm1.add(jmi[i]);//将子菜单添加到主菜单
			jm1.addSeparator();//在子菜单之间添加分隔线
			jmi[i].addActionListener(new ReadActionListener());//为菜单项注册监听器
		}
		JMenuItem export = new JMenuItem("导出");//创建导出（子菜单）
		JMenuItem exit = new JMenuItem("退出");//创建退出（子菜单）
		export.addActionListener(new ReadActionListener());//为菜单项注册监听器
		exit.addActionListener(new ReadActionListener());//为菜单项注册监听器
		jm1.add(export);//将导出（子菜单）添加到主菜单
		jm1.addSeparator();//在子菜单之间添加分隔线
		jm1.add(exit);//将退出（子菜单）添加到主菜单
		jmbMy.add(jm1);//将主菜单一添加到菜单栏
		JMenu jm2 = new JMenu("帮助(H)"); 	
		jmbMy.add(jm2);//将第二个主菜单添加到菜单栏

		return jmbMy;

	}

	/**
	 * 创建工具栏
	 */
	private JToolBar getJTBMy() {
		JToolBar jtbMy = new JToolBar();			//创建工具栏
		jtbMy.add(getJbButton());                   //将导出按钮添加到工具栏

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
		jspVertical.setDividerLocation(280);		//设置分隔条的位置
		jspVertical.setLeftComponent(getJSPTable());//将表格添加到分隔面板的上部
		jspVertical.setRightComponent(getJSPContent());	//将新闻内容文本区添加到分隔面板的下部
		return jspVertical;

	}

	/**
	 * 方法功能：创建一个面板作为状态栏
	 * @return jP
	 */
	//        private JPanel getJSBMy() {
	//        	JPanel jP = new JPanel();
	//        	jP.setLayout(new FlowLayout(FlowLayout.LEFT));//流式左对齐
	//        	JLabel jl = new JLabel("www.ruanko.com");
	//        	jP.add(jl);
	//        	Date date = new Date();
	//        	SimpleDateFormat simpledf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//        	String st = simpledf.format(date);
	//        	JLabel j2 = new JLabel(st);
	//        	jP.add(j2);    	
	//			return jP;
	//        	
	//        }








}
