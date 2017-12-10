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
 * RSS������
 */
public class JMainFrame extends JFrame {

	//��������
	private final static int WIDTH = 800;          //���ڿ��
	private final static int HEIGHT = 600;         //���ڸ߶�
	private final static String TITLE = "RSS�Ķ���";//���ڱ���

	//�������
	private JComboBox jcbChannel;                  //�����б��
	private JButton jbRead;                        //��ȡ���Ű�ť
	private JTextArea jtaContent;                  //�������ݵ��ı���
	private RSSService rssService;                 //ҵ���߼�����
	private DefaultTableModel dtmTableModel;       //�������ģ��
	private JTable jtTable;                        //���
	private JScrollPane jspContent;                //��������
	private JButton jbButton;                      //������ť

	private List<News>newsList;                    //���������б�
	public ReadActionListener readActionLi;        //����һ��ReadActionListener���͵ĳ�Ա����



	/**
	 * ���ڹ������ŵ��ĸ��˵����Ҫ��Ӷ�ȡRSS�ļ��ļ����������Բ���һ���ڲ���
	 * �������ȡRSS�ļ��˵����¼�
	 * ��ReadActionListener����һ���ڲ���
	 * ReadActionListener��ʵ��ActionListener�ӿڣ���Ϊ��ȡRSS�ļ��ļ�������
	 */
	class ReadActionListener implements ActionListener {
		@Override//��дactionPerformed����
		public void actionPerformed(ActionEvent e) {
			List<Channel> channelList = rssService.getchannelList();//�������Ƶ����Ϣ
			String commend = e.getActionCommand();                //����û�ѡ��Ĳ˵�������
			for(int i = 0; i < channelList.size(); i++) {         //�����˵����б�
				if(channelList.get(i).getName().equals(commend)) {//�û�ѡ��Ĳ˵����������б��е����ƱȽ�
					channelList.get(i).getFilePath();			//�����û�ѡ��Ĳ˵�������ƣ������Ӧ���ļ�·�� 
					newsList = rssService.getNewsList(channelList.get(i).getFilePath());
					showTable(newsList);
				}   
			}
			
			//				if(channelList.get(0).getName().equals(commend)) {
			//					newsList = rssService.getNewsList(channelList.get(0).getFilePath());
			//					showTable(newsList); //��ʾ��������
			//				}




//			if("��Ѷ�� - ����Ƶ��".equals(commend)) {
//				newsList = rssService.getNewsList("NewsFiles/rss_milit.xml"); 
//				showTable(newsList);
//			}
//			if("������ - �����ȵ�".equals(commend)) {
//				newsList = rssService.getNewsList("NewsFiles/hot.xml"); 
//				showTable(newsList);
//			}
//			if("���� - ��������".equals(commend)) {
//				newsList = rssService.getNewsList("NewsFiles/sports.xml"); 
//				showTable(newsList);
//			}
//			if("���� - �������".equals(commend)) {
//				newsList = rssService.getNewsList("NewsFiles/focus15.xml"); 
//				showTable(newsList);
//			}
			if("����".equals(commend)){
				if(rssService.save(newsList)) {
					JOptionPane.showMessageDialog(null, "������Ϣ����ɹ�");
				} 
				else {
					JOptionPane.showMessageDialog(null, "������Ϣ����ʧ��");
				} 
			}
			if("�˳�".equals(commend)){
				System.exit(0);                            //�˳�����
			}

		}		
	}


	/**
	 * ���췽��
	 */
	public JMainFrame() {
		rssService = new RSSService();            //����ҵ���߼�����
		ImageIcon imageIcon = new ImageIcon("images/Rss.png");
		Image image = imageIcon.getImage();
		this.setIconImage(image);                 //���ñ���ͼ��
		setTitle(TITLE);                          //���ô��ڱ���
		setSize(WIDTH, HEIGHT);                   //���ô��ڴ�С
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //���ô���Ĭ�Ϲرղ���
		this.setCenter();                         //���ô��ھ��еķ���
		this.setJMenuBar(getJMBMy());             //ͨ��JFrame���е�setJMenuBar()����������getJMBMy()���������˵�����ӵ�������
		this.setContentPane(getJPMain());         //����getJPMain()�������������ӵ�����
		add(new com.ruanko.xsgl.view.StatusBar(),BorderLayout.SOUTH);//��ʾʱ��
		this.readActionLi = null;                   //��readActionLi��ʼ��

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
		jpMain.add(getJTBMy(),BorderLayout.NORTH);//�����������ӵ������
		jpMain.add(getJSPClientArea(),BorderLayout.CENTER);//������������ı����ӵ�������м�	
		//jpMain.add(getJSBMy(),BorderLayout.SOUTH);//����������������������ı�����ӵ�������Ϸ�
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
			jspContent = new JScrollPane(jtaContent);//���ı�����ӵ���������(�������ı����Ĺ������)
			jspContent.setPreferredSize(new Dimension(780,260));//���ù�������Ĵ�С
		}

		return jspContent;

	}

	/**
	 * �ڲ��� 
	 */
	class ReadActionListener1 implements ActionListener{

		@Override//��дactionPerformed����
		public void actionPerformed(ActionEvent e) {
			// TODO �Զ����ɵķ������
			Channel selectedChannel = (Channel)jcbChannel.getSelectedItem();//��ȡ�����б�����û���ѡ��
			String filePath = selectedChannel.getFilePath();//��ȡ�û�ѡ�����һ���·��
			if(rssService.load(filePath) == null) {
				jtaContent.setText("��ȡʧ��");
			} else {
				jtaContent.setText("��ȡ�ɹ�");
			} 

		}

	}

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
	private JScrollPane getJSPTable() {
		JScrollPane jspTable = null;
		if(jspTable == null) {
			dtmTableModel = new DefaultTableModel();//�����������ģ��
			dtmTableModel.addColumn("����");      //���������ģ������Ӹ��еı���
			dtmTableModel.addColumn("����ʱ��");
			dtmTableModel.addColumn("����ʱ��");
			dtmTableModel.addColumn("����");
			jtTable = new JTable(dtmTableModel); //���������ģ����ӵ������
			jspTable = new JScrollPane(jtTable); //�������ӵ���������	    

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
						News selectedNews = newsList.get(selectedRow);//���ѡ�е�������Ϣ
						jtaContent.setText(rssService.newsToString(selectedNews));//��ѡ�е�������Ϣ��ʾ�����������ı���
					}
				}

			});

			jspTable = new JScrollPane(jtTable);

		}
		return jspTable;
	}

	/**
	 * �������ܣ���������Ϣ��ʾ�������
	 * @param newList
	 */
	private void showTable (List<News>newsList) {
		//��ձ�������
		int rowCount = dtmTableModel.getRowCount();
		while(rowCount > 0) {                //�������ģ���е�����������
			dtmTableModel.removeRow(0);      //removeRow()��ɾ������
			rowCount--;
		}
		//�������������б�����Ӧ������������ʾ�������
		for(int i = 0; i < newsList.size(); i++) {
			News news = newsList.get(i);
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
		if(jbButton == null) {                          //�жϰ�ť�Ƿ�Ϊ��
			//jbButton = new JButton("����");              //����һ��������ť
			ImageIcon imageIcon = new ImageIcon("images/export.png");
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
			jm1.add(jmi[i]);//���Ӳ˵���ӵ����˵�
			jm1.addSeparator();//���Ӳ˵�֮����ӷָ���
			jmi[i].addActionListener(new ReadActionListener());//Ϊ�˵���ע�������
		}
		JMenuItem export = new JMenuItem("����");//�����������Ӳ˵���
		JMenuItem exit = new JMenuItem("�˳�");//�����˳����Ӳ˵���
		export.addActionListener(new ReadActionListener());//Ϊ�˵���ע�������
		exit.addActionListener(new ReadActionListener());//Ϊ�˵���ע�������
		jm1.add(export);//���������Ӳ˵�����ӵ����˵�
		jm1.addSeparator();//���Ӳ˵�֮����ӷָ���
		jm1.add(exit);//���˳����Ӳ˵�����ӵ����˵�
		jmbMy.add(jm1);//�����˵�һ��ӵ��˵���
		JMenu jm2 = new JMenu("����(H)"); 	
		jmbMy.add(jm2);//���ڶ������˵���ӵ��˵���

		return jmbMy;

	}

	/**
	 * ����������
	 */
	private JToolBar getJTBMy() {
		JToolBar jtbMy = new JToolBar();			//����������
		jtbMy.add(getJbButton());                   //��������ť��ӵ�������

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
		jspVertical.setDividerLocation(280);		//���÷ָ�����λ��
		jspVertical.setLeftComponent(getJSPTable());//�������ӵ��ָ������ϲ�
		jspVertical.setRightComponent(getJSPContent());	//�����������ı�����ӵ��ָ������²�
		return jspVertical;

	}

	/**
	 * �������ܣ�����һ�������Ϊ״̬��
	 * @return jP
	 */
	//        private JPanel getJSBMy() {
	//        	JPanel jP = new JPanel();
	//        	jP.setLayout(new FlowLayout(FlowLayout.LEFT));//��ʽ�����
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
