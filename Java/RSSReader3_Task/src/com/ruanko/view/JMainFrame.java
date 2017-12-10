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
 * �������ڣ��¼�����
 * RSS������
 */
@SuppressWarnings("serial")
public class JMainFrame extends JFrame {

	//��������
	private final static int WIDTH = 800;          //���ڿ��
	private final static int HEIGHT = 600;         //���ڸ߶�
	private final static String TITLE = "RSS�Ķ���";//���ڱ���

	//�������
	@SuppressWarnings({ "unused", "rawtypes" })
	private JComboBox jcbChannel;                  //�����б��
	@SuppressWarnings("unused")
	private JButton jbRead;                        //��ȡ���Ű�ť
	private JTextArea jtaContent;                  //�������ݵ��ı���
	private RSSService rssService;                 //ҵ���߼�����
	private DefaultTableModel dtmTableModel;       //�������ģ��
	private JTable jtTable;                        //���
	private JScrollPane jspContent;                //��������
	private JButton jbButton;                      //������ť
	private JButton jupdateButton;                 //���°�ť


	private List <News> newsList;                    //���������б�
	private List<Channel> channelList;
	public ReadActionListener readActionLi;        //����һ��ReadActionListener���͵ĳ�Ա����
	public  boolean bool[]={false,false,false,false}; //Ϊ�ĸ�Ƶ������һ��bool���͵�ֵ��ʼ״̬��Ϊfalse������˵��е�ĳ������Ƶ��������Ƶ����Ŵ�0��3�����ĸ�����Ƶ������������Ǹ���Ŷ�Ӧ��boolֵ��Ϊtrue




	/**
	 * ���ڹ������ŵ��ĸ��˵����Ҫ��Ӷ�ȡRSS�ļ��ļ����������Բ���һ���ڲ���
	 * �������ȡRSS�ļ��˵����¼�
	 * ��ReadActionListener����һ���ڲ���
	 * ReadActionListener��ʵ��ActionListener�ӿڣ���Ϊ��ȡRSS�ļ��ļ�������
	 */
	class ReadActionListener implements ActionListener {
		@Override//��дactionPerformed����
		public void actionPerformed(ActionEvent e) {
			channelList = rssService.getchannelList();//�������Ƶ����Ϣ
			String commend = e.getActionCommand();                  //����û�ѡ��Ĳ˵�������
			for(int i = 0; i < channelList.size(); i++) {           //�����˵����б�
				if(channelList.get(i).getName().equals(commend)) {  //�û�ѡ��Ĳ˵����������б��е����ƱȽ�
					channelList.get(i).getFilePath();			    //�����û�ѡ��Ĳ˵�������ƣ������Ӧ���ļ�·�� 
					newsList = rssService.getNewsList(channelList.get(i).getFilePath());
					showTable(newsList);
			        bool[i]=true; //����˵��е�ĳ������Ƶ��������Ƶ����Ŵ�0��3�����ĸ�����Ƶ������������Ǹ���Ŷ�Ӧ��boolֵ��Ϊtrue
				}   
			}
			if("����".equals(commend)) {
				if(rssService.save(newsList)) {
					JOptionPane.showMessageDialog(null, "������Ϣ����ɹ�");
				} 
				else {
					JOptionPane.showMessageDialog(null, "������Ϣ����ʧ��");
				} 
			}
			if("�˳�".equals(commend)) {
				System.exit(0);                            //�˳�����
			}
			if("�ĵ�".equals(commend)) {
				int option = JOptionPane.showConfirmDialog(null, "Are you sure ��","��ܰ��ʾ",JOptionPane.YES_NO_CANCEL_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					String option2 = JOptionPane.showInputDialog("Sorry,I don't Know,but you can go to ruanko to see about it��","Thank you");

					if(option2.equals("Thank you")) {
						JOptionPane.showMessageDialog(getJSPContent(),"You are welcome !");
					} else if (option2.equals("OK")) {
						JOptionPane.showMessageDialog(getJSPContent(),"Over!");
					} else {
						System.exit(0);//�˳�
					}
				}
			}
		}		
	}

	/**
	 * ���췽��
	 */
	public JMainFrame() {
		rssService = new RSSService();            //����ҵ���߼�����
		ImageIcon imageIcon = new ImageIcon("images/Rss.png");
		//	String string = imageIcon.toString();     //���ش�ͼ����ַ�����ʾ��ʽ  
		//	System.out.println(string);
		Image image = imageIcon.getImage();
		this.setIconImage(image);                 //���ñ���ͼ��
		setTitle(TITLE);                          //���ô��ڱ���
		setSize(WIDTH, HEIGHT);                   //���ô��ڴ�С
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //���ô���Ĭ�Ϲرղ���
		this.setCenter();                         //���ô��ھ��еķ���
		this.setJMenuBar(getJMBMy());             //ͨ��JFrame���е�setJMenuBar()����������getJMBMy()���������˵�����ӵ�������
		this.setContentPane(getJPMain());         //����getJPMain()�������������ӵ�����
//		add(new StatusBar(),BorderLayout.SOUTH);//��ʾʱ��
		this.readActionLi = null;                 //��readActionLi��ʼ��
	}

	/**
	 * �������ܣ�ʹ���ھ���
	 */
	private void setCenter() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//��ȡ��Ļ�ߴ�
		int x = (screenSize.width - WIDTH)/2;     //�������ϽǺ�����
		int y = (screenSize.height - HEIGHT)/2;   //�������Ͻ�������
		this.setLocation(x, y);	                  //���ô�����ʾ��λ��
	}

	/**
	 * �������ܣ����������
	 * @return
	 */
	private JPanel getJPMain() {

		JPanel jpMain = new JPanel();             //����һ�������
		jpMain.setLayout(new BorderLayout());     //���������Ϊ�߽粼��
		jpMain.add(getJTBMy(),BorderLayout.NORTH);         //����������ӵ�����山��
		jpMain.add(getJSPClientArea(),BorderLayout.CENTER);//�ͻ�����ӵ�������м�	
		//jpMain.add(getJSBMy(),BorderLayout.SOUTH);//����������������������ı�����ӵ�������Ϸ�	
		jpMain.add(new StatusBar(),BorderLayout.SOUTH);//��һ�п����Ƶ����췽����
//		jpMain.add(new StatusBar(),"South");//��һ��������һ�еȼ�		
		return jpMain;                            //���������	
	}

	/**
	 * �������ܣ����վ��������
	 * @return 
	 */
	//	private JComboBox getJCBChannel() {
	//		if(jcbChannel == null) {                 //��������б��Ϊ��
	//			jcbChannel = new JComboBox();        //����һ�������б��
	//			List<Channel> channelList = rssService.getchannelList();//�������Ƶ����Ϣ
	//			//jcbChannel.addItem("��Ѷ - ����Ҫ��");
	//			for(int i = 0; i < channelList.size(); i++) {
	//				jcbChannel.addItem(channelList.get(i));
	//			}
	//		}
	//		return jcbChannel;
	//
	//	}

	//	/**
	//	 * �������ܣ���Ӷ�ȡ��ť
	//	 * @return jbRead
	//	 */
	//	private JButton getJBRead() {
	//		if(jbRead == null) { 
	//			jbRead = new JButton("��ȡ");                 //������ȡ��ť
	//			//ReadActionListener readActionListener = new ReadActionListener();//������ȡ��ť������
	//			jbRead.addActionListener(new ActionListener() {//���¼�������ע�ᵽ��ȡ��ť��
	//				public void actionPerformed(ActionEvent e) {
	//					//��������б�
	//					Channel selectedChannel = (Channel)jcbChannel.getSelectedItem();
	//					String filePath = selectedChannel.getFilePath();
	//					newsList = rssService.getNewsList(filePath);
	//					//��ʾ��������
	//					showTable(newsList);
	//				}
	//			});
	//		}
	//		return jbRead;
	//
	//	}

	/**
	 * �������ܣ�ʵ�ִ������������������ı���
	 * setCaretPosition(0)����궨���������ĵ�һ���ַ������������һ��
	 * �ǵ���ȡ���������ֺ�����������ͷ��ʼ��ʾ���֣����������Ὣ��궨λ�����һ�����ַ�������ʾ�ĵ�ȻҲ�����ֵ�δβ
	 */
	private JScrollPane getJSPContent() {
		if(jtaContent == null) {
			jtaContent = new JTextArea();          //����һ���ı���
			jtaContent.setLineWrap(true);//setLineWrap(true)����Ϊ�Զ����У��ȵ����ֱȿؼ��Ŀ�Ȼ���ʱ���Զ�����
			jtaContent.setFont(new Font("�����п�", 0, 22));
			jtaContent.setForeground(Color.orange);//�ı����ǰ��ɫҲ�������ı�������ʾ���������ɫ
			jtaContent.setBackground(Color.black);//�ı���ı���ɫҲ���������ı������ɫ
			jspContent = new JScrollPane(jtaContent);//���ı�����ӵ���������(�������ı����Ĺ������)
			jspContent.setPreferredSize(new Dimension(780,260));//���ù�������Ĵ�С
		}
		return jspContent;

	}

	//	/**
	//	 * �ڲ��� 
	//	 */
	//	class ReadActionListener1 implements ActionListener {
	//
	//		@Override//��дactionPerformed����
	//		public void actionPerformed(ActionEvent e) {
	//			// TODO �Զ����ɵķ������
	//			Channel selectedChannel = (Channel)jcbChannel.getSelectedItem();//��ȡ�����б�����û���ѡ��
	//			String filePath = selectedChannel.getFilePath();//��ȡ�û�ѡ�����һ���·��
	//			if(rssService.load(filePath) == null) {
	//				jtaContent.setText("��ȡʧ��");
	//			} else {
	//				jtaContent.setText("��ȡ�ɹ�");
	//			} 
	//
	//		}
	//
	//	}

	//	/**
	//	 * �������ܣ��������
	//	 * @return
	//	 */
	//	private JPanel getjpNorth() {
	//		JPanel jpnorth = new JPanel();            //����һ���������
	//		jpnorth.setLayout(new FlowLayout(FlowLayout.LEFT));//�����������Ϊ�����
	//	    JLabel jlChannel = new JLabel("վ��");     //����һ����ǩ
	//		jpnorth.add(jlChannel);                   //����ǩ��ӵ��������
	//		jpnorth.add(getJCBChannel());             //�������б����ӵ��������
	//		jpnorth.add(getJBRead());                 //����ȡ��ť��ӵ��������
	//		jpnorth.add(getJbButton());               //��������ť��ӵ��������
	//		return jpnorth;                           //���ر������	
	//	}

	/**
	 * �������ܣ�����һ�����������Ĺ�������
	 * @return
	 */
	public JScrollPane getJSPTable() {
		JScrollPane jspTable = null;
		if(jspTable == null) {
			dtmTableModel = new DefaultTableModel();//�����������ģ��
			dtmTableModel.addColumn("����");      //���������ģ������Ӹ��еı���
			dtmTableModel.addColumn("����ʱ��");
			dtmTableModel.addColumn("����ʱ��");
			dtmTableModel.addColumn("����");
			jtTable = new JTable(dtmTableModel); //���������ģ����ӵ������		
			jtTable.setForeground(Color.green);  //����ǰ��ɫҲ���Ǳ�����������ɫ
			jtTable.getTableHeader().setFont(new Font("����", 0, 22));
			jtTable.setFont(new Font("����",Font.BOLD,15));		
			jtTable.setBackground(Color.darkGray);//��񱳾�ɫ
//			System.out.println("�����и�Ϊ��"+jtTable.getRowHeight());
			jtTable.setRowHeight(30);		

			/**
			 * ���������ڲ������ʽΪ��������굥���¼�������
			 */
			jtTable.addMouseListener(new MouseAdapter() {

				/**
				 * ��дmouseClicked��������������¼�
				 */
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 1) {      //�ж��Ƿ���굥��
						int selectedRow = jtTable.getSelectedRow();//�����굥�����е�λ��
						News selectedNews = (News)newsList.get(selectedRow);//���ѡ�е�������Ϣ
						jtaContent.setText(rssService.newsToString(selectedNews));//��ѡ�е�������Ϣ��ʾ�����������ı���
					}
				}

			});
			jspTable = new JScrollPane(jtTable);//�������ӵ���������
		}
		return jspTable;//���ع�������
	}

	/**
	 * �������ܣ���������Ϣ��ʾ�������
	 * @param newList
	 */
	public void showTable (List<News>newsList) {
		//��ձ�������
		int rowCount = dtmTableModel.getRowCount();
		
		//�����������պ��˳�ѭ��
		while(rowCount > 0) {                        //�������ģ���е�����������
			dtmTableModel.removeRow(0);              //removeRow()��ɾ�������У��б���㿪ʼ��
			rowCount--;
		}
		//�������������б�����Ӧ������������ʾ�������
		for(int i = 0; i < newsList.size(); i++) {
			News news = (News)newsList.get(i);
			//��ָ����ʱ���ʽ����õ�ǰ����
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String currentDate = dateFormat.format(date);
			//�����ŵı��⡣��ǰ���ڡ�����ʱ���������ʾ�ڱ����
			String [] data = {news.getTitle(),currentDate,news.getPubDate(),news.getAuthor()};
			dtmTableModel.addRow(data);
		}
	}

	/**
	 * �������ܣ�����������ť
	 * @return jbButton �����ص�����ť��
	 */
	private JButton getJbButton() {
		if(jbButton == null) {                            //�жϰ�ť�Ƿ�Ϊ��
			//jbButton = new JButton("����");              //����һ��������ť
			ImageIcon imageIcon = new ImageIcon("images/export.png","���ǹ�����ͼ��");
			//System.out.println(imageIcon.getDescription());//��ȡͼ�������
			jbButton = new JButton(imageIcon);
			jbButton.setToolTipText("����");

			/**
			 * �����ڲ���
			 */
			jbButton.addActionListener(new ActionListener() {
				@Override                              //��дactionperformed()����
				public void actionPerformed(ActionEvent e) {
					if(rssService.save(newsList)) {
						JOptionPane.showMessageDialog(null, "������Ϣ����ɹ�");
					} 
					else {
						JOptionPane.showMessageDialog(null, "������Ϣ����ʧ��");
					}

				}
			});
		}
		return jbButton;
	}


	/**
	 * �������°�ť
	 */
	private JButton getUpdate(){
		if( jupdateButton == null){
			ImageIcon imageIcon = new ImageIcon("images/update.png","���ǹ�����ͼ��");
			//System.out.println(imageIcon.getDescription());//��ȡͼ�������
			jupdateButton = new JButton(imageIcon);
			jupdateButton.setToolTipText("����");
			/**
			 * �����ڲ���
			 */
			jupdateButton.addActionListener(new ActionListener() {
				@Override                              //��дactionperformed()����
				public void actionPerformed(ActionEvent e) {
					updateNews();
				}
			});
		}
		return jupdateButton;		

	}


	/**
	 * �������ܣ�ʵ�ֲ˵����Ĵ��������������Ӧ�Ĳ˵����˵���
	 * @return jmbMy
	 */
	private  JMenuBar getJMBMy() {
		JMenuItem[] jmi = new JMenuItem[4];          //����һ�����飬����Ӳ˵�
		JMenuBar jmbMy = new JMenuBar();             //�����˵���
		JMenu jm1 = new JMenu("�ļ�(S)");              //������һ�����˵�,��ݼ�ΪS
		List<Channel> channelList = rssService.getchannelList();//�������Ƶ����Ϣ
		for(int i = 0; i < channelList.size(); i++) {
			jmi[i]= new JMenuItem(channelList.get(i).getName());//������һ�����˵��Ĳ˵���Ӳ˵��� 
//			jmi[i].setIcon(new ImageIcon("D:\\Users\\zwqabc\\Workspaces\\MyEclipse 2017 CI\\RSSReader2\\images\\Rss.png"));//��һ�����˵��µĵ�һ���Ӳ˵�
			jmi[i].setIcon(new ImageIcon("E:\\�������ҵ\\Java\\ѧ��ʵѵRSS�Ķ�����������Ϣ����ϵͳ\\RSSReader2\\images\\Rss.png"));//��һ�����˵��µĵ�һ���Ӳ˵�
			jm1.add(jmi[i]);                          //���Ӳ˵���ӵ����˵�
			jm1.addSeparator();                       //���Ӳ˵�֮����ӷָ���
			jmi[i].addActionListener(new ReadActionListener());//Ϊ�˵���ע�������
		}		
		JMenuItem export = new JMenuItem("����");       //�����������Ӳ˵���
		JMenuItem exit = new JMenuItem("�˳�");         //�����˳����Ӳ˵���
//		export.setIcon(new ImageIcon("D:\\Users\\zwqabc\\Workspaces\\MyEclipse 2017 CI\\RSSReader2\\images\\Rss.png"));//��һ�����˵��µĵ�һ���Ӳ˵�	
//		exit.setIcon(new ImageIcon("D:\\Users\\zwqabc\\Workspaces\\MyEclipse 2017 CI\\RSSReader2\\images\\Rss.png"));//��һ�����˵��µĵ�һ���Ӳ˵�
		export.setIcon(new ImageIcon("E:\\�������ҵ\\Java\\ѧ��ʵѵRSS�Ķ�����������Ϣ����ϵͳ\\RSSReader2\\images\\Rss.png"));//��һ�����˵��µĵ�һ���Ӳ˵�	
		exit.setIcon(new ImageIcon("E:\\�������ҵ\\Java\\ѧ��ʵѵRSS�Ķ�����������Ϣ����ϵͳ\\RSSReader2\\images\\Rss.png"));//��һ�����˵��µĵ�һ���Ӳ˵�

		
		export.addActionListener(new ReadActionListener());//Ϊ�˵���ע�������
		exit.addActionListener(new ReadActionListener());//Ϊ�˵���ע�������
		jm1.add(export);                                 //���������Ӳ˵�����ӵ����˵�
		jm1.addSeparator();                              //���Ӳ˵�֮����ӷָ���
		jm1.add(exit);                                   //���˳����Ӳ˵�����ӵ����˵�
		jmbMy.add(jm1);                                  //�����˵�һ��ӵ��˵���
		JMenu jm2 = new JMenu("����(H)"); 	
		JMenuItem word = new JMenuItem("�ĵ�");           //�����������Ӳ˵���
		jm2.add(word);
		jm1.add(jm2);
		jmbMy.add(jm2);                                  //���ڶ������˵���ӵ��˵���
		word.addActionListener(new ReadActionListener());//Ϊ�˵���ע�������

		//�˵�����ӿ�ݼ�
		jmi[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,InputEvent.CTRL_MASK));//Ϊ�Ӳ˵������棩������ݼ�Ctrl+1
		jmi[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,InputEvent.CTRL_MASK));//Ϊ�Ӳ˵������棩������ݼ�Ctrl+2
		jmi[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,InputEvent.CTRL_MASK));//Ϊ�Ӳ˵������棩������ݼ�Ctrl+3
		jmi[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,InputEvent.CTRL_MASK));//Ϊ�Ӳ˵������棩������ݼ�Ctrl+4
		export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5,InputEvent.CTRL_MASK));//Ϊ�Ӳ˵������棩������ݼ�Ctrl+5
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6,InputEvent.CTRL_MASK));//Ϊ�Ӳ˵������棩������ݼ�Ctrl+6
		
		//�˵��������ȼ����������Ŀ�ݼ��ﵽ��Ч������ͬ�ģ�
		jmi[0].setMnemonic('0');
		jmi[1].setMnemonic('1');
		jmi[2].setMnemonic('2');
		jmi[3].setMnemonic('3');
		export.setMnemonic('5');
		exit.setMnemonic('6');
		
		//Ϊ���˵����ļ���������ݼ�Alt+S
		jm1.setMnemonic(KeyEvent.VK_S);                   //Ϊ���˵����ļ���������ݼ�Alt+S
		jm2.setMnemonic(KeyEvent.VK_H);                   //Ϊ���˵���������������ݼ�Alt+H
		
		//Ϊ���˵�������ʾ���
		jm1.setToolTipText("Alt+S");
		jm2.setToolTipText("Alt+H");
        
		//Ϊ���˵���������
		jm1.setFont(new Font("console",Font.BOLD, 14));
		jm2.setFont(new Font("console",Font.BOLD, 14));
		
		//Ϊ���˵�������ɫ
		jm1.setForeground(Color.blue);//������ɫ
		jm2.setForeground(Color.blue);
		return jmbMy;
		
	
	}
	


	/**
	 * ����������
	 */
	private JToolBar getJTBMy() {
		JToolBar jtbMy = new JToolBar();			       //����������
		jtbMy.add(getJbButton());                          //��������ť��ӵ�������
		jtbMy.add(getUpdate());                          //�����°�ť��ӵ�������
		jtbMy.setFloatable(false);                         //���ù��������ɸ���
		//		jtbMy.setRollover(true);                          //����ת��Ч������������ǳ��ֱ߿�
		return jtbMy;                                      
	}

	/**
	 * ��ӿͻ���
	 * �ָ���壺JSplitPane,ΪJavax.swing����
	 * �������ܣ������ָ���壬ʵ�ִ�ֱ�ָ�
	 * @return jspVertical
	 */
	private JSplitPane getJSPClientArea() {
		JSplitPane jspVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);//�����ָ����ʵ�ִ�ֱ�ָ�
		jspVertical.setDividerLocation(280);		       //���÷ָ�����λ��
		jspVertical.setLeftComponent(getJSPTable());        //�������ӵ��ָ������ϲ�
		jspVertical.setRightComponent(getJSPContent());  	//�����������ı�����ӵ��ָ������²�
		return jspVertical;
	}



	/**
	 * �������ܣ�������°�ťʱ��������
	 */
	public void updateNews(){
		List<Channel> channelList = rssService.getchannelList();//�������Ƶ����Ϣ
		Thread th = new UpdateThread();      //�����߳�
		th.start();                          //�����߳�

		//����bool����
		for(int x = 0; x < 4; x++) {
			if(bool[x]==true){              //���ĳ������µ�boolֵΪtrue����ô�����Ŷ�Ӧ�µ�Ƶ�������
				newsList = rssService.getNewsList(channelList.get(x).getFilePath());//��ȡ��·���µ�����
				showTable(newsList);        //����Ƶ�����º��������Ϣ��ʾ�������
			}
		}
	}
}
