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



	/**
	 * ���췽��
	 */
	public JMainFrame() {
		rssService = new RSSService();            //����ҵ���߼�����
		ImageIcon imageIcon = new ImageIcon("images/true.jpg");
		Image image = imageIcon.getImage();
		this.setIconImage(image);                 //���ñ���ͼ��
		setTitle(TITLE);                          //���ô��ڱ���
		setSize(WIDTH, HEIGHT);                   //���ô��ڴ�С
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //���ô���Ĭ�Ϲرղ���
		this.setCenter();                         //���ô��ھ��еķ���
		this.setContentPane(getJPMain());         //����getJPMain()�������������ӵ�����
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
		jpMain.add(getjpNorth(),BorderLayout.NORTH);//�����������ӵ������
		jpMain.add(getJSPTable(),BorderLayout.CENTER);//������������ı����ӵ�������м�	
		jpMain.add(getJSPContent(),BorderLayout.SOUTH);//����������������������ı�����ӵ�������Ϸ�
		return jpMain;                            //���������	
	}

	/**
	 * �������ܣ����վ��������
	 * @return jcbChannel
	 */
	private JComboBox getJCBChannel() {
		if(jcbChannel == null) {                                    //��������б��Ϊ��
			jcbChannel = new JComboBox();                           //����һ�������б��
			List<Channel> channelList = rssService.getchannelList();//�������Ƶ����Ϣ
			//jcbChannel.addItem("��Ѷ - ����Ҫ��");
			for(int i = 0; i < channelList.size(); i++) {           //����Ƶ���б�Ƶ����ӵ������б��
				jcbChannel.addItem(channelList.get(i));
			}
		}
		return jcbChannel;

	}

	/**
	 * �������ܣ���Ӷ�ȡ��ť
	 * @return jbRead
	 */
	private JButton getJBRead() {
		if(jbRead == null) { 
			jbRead = new JButton("��ȡ");                            //������ȡ��ť
			  //�����ڲ���
			jbRead.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//��������б�
					Channel selectedChannel = (Channel)jcbChannel.getSelectedItem();
					String filePath = selectedChannel.getFilePath();//��ȡ���������ļ���·��
					newsList = rssService.getNewsList(filePath);    //��ȡ��·���µ�������Ϣ
					showTable(newsList);                            //��ʾ������Ϣ�����
				}
			});
		}
		return jbRead;
	}

	/**
	 * �������ܣ�ʵ�ִ������������������ı���
	 * setCaretPosition(0)����궨���������ĵ�һ���ַ������������һ��
	 * �ǵ���ȡ���������ֺ�����������ͷ��ʼ��ʾ���֣����������Ὣ��궨λ�����һ�����ַ�������ʾ�ĵ�ȻҲ�����ֵ�δβ
	 */
	private JScrollPane getJSPContent() {
		if(jtaContent == null) {
			jtaContent = new JTextArea();              //����һ���ı���
			jtaContent.setLineWrap(true);              //setLineWrap(true)����Ϊ�Զ����У��ȵ����ֱȿؼ��Ŀ�Ȼ���ʱ���Զ�����
			jspContent = new JScrollPane(jtaContent); //���ı�����ӵ���������(�������ı����Ĺ������)
			jspContent.setPreferredSize(new Dimension(780,260));//���ù�������Ĵ�С
		}
		return jspContent;
	}

	/**
	 * �ڲ��� 
	 */
	class ReadActionListener implements ActionListener{

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

	/**
	 * �������ܣ��������
	 * @return
	 */
	private JPanel getjpNorth() {
		JPanel jpnorth = new JPanel();            //����һ���������
		jpnorth.setLayout(new FlowLayout(FlowLayout.LEFT));//�����������Ϊ�����
		JLabel jlChannel = new JLabel("վ��");     //����һ����ǩ
		jpnorth.add(jlChannel);                   //����ǩ��ӵ��������
		jpnorth.add(getJCBChannel());             //�������б����ӵ��������
		jpnorth.add(getJBRead());                  //����ȡ��ť��ӵ��������
		jpnorth.add(getJbButton());               //��������ť��ӵ��������
		return jpnorth;                           //���ر������	
	}

	/**
	 * �������ܣ�����һ�����������Ĺ�������
	 * @return jspTable
	 */
	private JScrollPane getJSPTable() {
		JScrollPane jspTable = null;
		if(jspTable == null) {
			dtmTableModel = new DefaultTableModel();                               //�����������ģ��
			dtmTableModel.addColumn("����");                                        //���������ģ������Ӹ��еı���
			dtmTableModel.addColumn("����ʱ��");
			dtmTableModel.addColumn("����ʱ��");
			dtmTableModel.addColumn("����");
			jtTable = new JTable(dtmTableModel);                                   //���������ģ����ӵ������
			jspTable = new JScrollPane(jtTable);                                   //�������ӵ���������	    			 
			jtTable.addMouseListener(new MouseAdapter() {                          //���������ڲ������ʽΪ��������굥���¼�������			 		
				public void mouseClicked(MouseEvent e) {                           //��дmouseClicked��������������¼�	
					if(e.getClickCount() == 1) {                                   //�ж��Ƿ���굥��
						int selectedRow = jtTable.getSelectedRow();                //�����굥�����е�λ��
						News selectedNews = newsList.get(selectedRow);             //���ѡ�е�������Ϣ
						jtaContent.setText(rssService.newsToString(selectedNews)); //��ѡ�е�������Ϣ��ʾ�����������ı���
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
			jbButton = new JButton("����");              //����һ��������ť

			//�����ڲ���	
			jbButton.addActionListener(new ActionListener() {
				@Override//��дactionperformed()����
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


}
