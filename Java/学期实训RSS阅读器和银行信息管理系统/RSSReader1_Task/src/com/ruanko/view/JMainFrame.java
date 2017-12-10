package com.ruanko.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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



	/**
	 * 构造方法
	 */
	public JMainFrame() {
		rssService = new RSSService();            //创建业务逻辑对象
		ImageIcon imageIcon = new ImageIcon("images/true.jpg");
		Image image = imageIcon.getImage();
		this.setIconImage(image);                 //设置标题图标
		setTitle(TITLE);                          //设置窗口标题
		setSize(WIDTH, HEIGHT);                   //设置窗口大小
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //设置窗口默认关闭操作
		this.setCenter();                         //调用窗口居中的方法
		this.setContentPane(getJPMain());         //调用getJPMain()方法将主面板添加到窗口
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
		jpMain.add(getjpNorth(),BorderLayout.NORTH);//将北面面板添加到主面板
		jpMain.add(getJSPTable(),BorderLayout.CENTER);//将带滚动窗格的表格添加到主面板中间	
		jpMain.add(getJSPContent(),BorderLayout.SOUTH);//将带滚动窗格的新闻内容文本域添加到主面板南方
		return jpMain;                            //返回主面板	
	}

	/**
	 * 方法功能：添加站点下拉框
	 * @return jcbChannel
	 */
	private JComboBox getJCBChannel() {
		if(jcbChannel == null) {                                    //如果下拉列表框为空
			jcbChannel = new JComboBox();                           //创建一个下拉列表框
			List<Channel> channelList = rssService.getchannelList();//获得新闻频道信息
			//jcbChannel.addItem("腾讯 - 国际要闻");
			for(int i = 0; i < channelList.size(); i++) {           //遍历频道列表将频道添加到下拉列表框
				jcbChannel.addItem(channelList.get(i));
			}
		}
		return jcbChannel;

	}

	/**
	 * 方法功能：添加读取按钮
	 * @return jbRead
	 */
	private JButton getJBRead() {
		if(jbRead == null) { 
			jbRead = new JButton("读取");                            //创建读取按钮
			  //匿名内部类
			jbRead.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//获得新闻列表
					Channel selectedChannel = (Channel)jcbChannel.getSelectedItem();
					String filePath = selectedChannel.getFilePath();//获取本地新闻文件的路径
					newsList = rssService.getNewsList(filePath);    //获取该路径下的新闻信息
					showTable(newsList);                            //显示新闻信息到表格
				}
			});
		}
		return jbRead;
	}

	/**
	 * 方法功能：实现带滚动条的新闻内容文本区
	 * setCaretPosition(0)将光标定往到容器的第一个字符处，这个方法一般
	 * 是当读取大量的文字后能让容器从头开始显示文字，否则容器会将光标定位在最后一个个字符处，显示的当然也是文字的未尾
	 */
	private JScrollPane getJSPContent() {
		if(jtaContent == null) {
			jtaContent = new JTextArea();              //创建一个文本域
			jtaContent.setLineWrap(true);              //setLineWrap(true)方法为自动换行，既当文字比控件的宽度还长时会自动换行
			jspContent = new JScrollPane(jtaContent); //将文本域添加到滚动窗格(创建带文本区的滚动面板)
			jspContent.setPreferredSize(new Dimension(780,260));//设置滚动窗格的大小
		}
		return jspContent;
	}

	/**
	 * 内部类 
	 */
	class ReadActionListener implements ActionListener{

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

	/**
	 * 方法功能：北面面板
	 * @return
	 */
	private JPanel getjpNorth() {
		JPanel jpnorth = new JPanel();            //创建一个北面面板
		jpnorth.setLayout(new FlowLayout(FlowLayout.LEFT));//北面面板设置为左对齐
		JLabel jlChannel = new JLabel("站点");     //创建一个标签
		jpnorth.add(jlChannel);                   //将标签添加到北面面板
		jpnorth.add(getJCBChannel());             //将下拉列表框添加到北面面板
		jpnorth.add(getJBRead());                  //将读取按钮添加到北面面板
		jpnorth.add(getJbButton());               //将导出按钮添加到北面面板
		return jpnorth;                           //返回北面面板	
	}

	/**
	 * 方法功能：创建一个带滚动条的滚动窗格
	 * @return jspTable
	 */
	private JScrollPane getJSPTable() {
		JScrollPane jspTable = null;
		if(jspTable == null) {
			dtmTableModel = new DefaultTableModel();                               //创建表格数据模型
			dtmTableModel.addColumn("主题");                                        //往表格数据模型中添加个列的标题
			dtmTableModel.addColumn("接收时间");
			dtmTableModel.addColumn("发布时间");
			dtmTableModel.addColumn("作者");
			jtTable = new JTable(dtmTableModel);                                   //将表格数据模型添加到表格中
			jspTable = new JScrollPane(jtTable);                                   //将表格添加到滚动窗格	    			 
			jtTable.addMouseListener(new MouseAdapter() {                          //采用匿名内部类的形式为表格添加鼠标单击事件监听器			 		
				public void mouseClicked(MouseEvent e) {                           //重写mouseClicked方法处理鼠标点击事件	
					if(e.getClickCount() == 1) {                                   //判断是否鼠标单击
						int selectedRow = jtTable.getSelectedRow();                //获得鼠标单击处行的位置
						News selectedNews = newsList.get(selectedRow);             //获得选中的新闻信息
						jtaContent.setText(rssService.newsToString(selectedNews)); //将选中的新闻信息显示到新闻内容文本域
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
			jbButton = new JButton("导出");              //创建一个导出按钮

			//匿名内部类	
			jbButton.addActionListener(new ActionListener() {
				@Override//重写actionperformed()方法
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


}
