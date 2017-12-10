package com.ruanko.view;

import java.awt.BorderLayout;
import java.awt.Color;                                
import java.awt.Cursor;                              //���
import java.awt.Dimension;                           
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;                            //���߰�
import java.awt.event.ActionEvent;                
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;                          
import javax.swing.JRadioButton;                    
import javax.swing.JScrollPane;                       
import javax.swing.JTable;                            //���
import javax.swing.JTextField;                       
import javax.swing.table.DefaultTableModel;           //���ģ��
import com.ruanko.business.FileOperation;             
import com.ruanko.model.Contact;
import com.ruanko.xsgl.view.StatusBar;                      


/**
 * @param ���������Ĳ���
 * @return ��������ֵ�������޷���ֵ�ķ������췽����@return����ʡ��
 * @throws ������ʲô������׳�ʲô���͵��쳣
 * @author ��������
 * @version �����汾 
 * @since ��������������е�JDK�汾
 * @see �ο�ת��Ҳ�����������
 * @link ת���Ա�ĳ����ӡ�labelΪ�������֡�package.class#member�����Զ�ת��Ϊָ��package.class��member�ļ���URL
 */



/**
 * List�洢�ַ�������
 * List��һ���ӿڣ�ʵ��List�ӿڵ��������е�Ԫ������˳��ģ����ҿ����ظ�
 * List�����е�Ԫ�ض���Ӧһ�������͵���ż������������е�λ�ã����Ը�����Ŵ�ȡ�����е�Ԫ��
 * ע�⣺�ӿڲ��ܹ�����󣬵����������ӿڱ��������ҽӿڱ�������ָ��һ��ʵ���˸ýӿڵ��ࣨArrayList�ࣩ�Ķ���
 * ���磺List contacts = new ArrayList();
 * ����List����contacts�������øñ���ָ��ArrayList��Ķ���
 * ArrayList��ʵ����List�ӿڣ�ӵ��List�ӿ�����
 */


/**
 * ע�⣺������������һ����壬���Խ������ӵ������������󣬻�Ҫ���������������ӵ������
 * ����һ�����������ı�񷽷���
 * ��һ�����������ģ�Ͳ�����ӱ�ͷ��ʹ��DefaultTableModel��
 *	    DefaultTableModel tableModel=new DefaultTableModel();                         //�������ģ��
 *	    tableModel.addColumn("����");                                //����һ�еı���
 *	    tableModel.addColumn("�ֻ���");                               //���ڶ��еı���
 *	    tableModel.addColumn("����"); 
 * �ڶ������������ʹ��JTable��
 *      JTable table = new JTable(tableModel);
 * ���������������ӵ�����������
 *      JScrollPane tableScrollPane=new JScrollPane(table);                     //�������ӵ���������	
 * ���Ĳ����������ӵ�������
 *      this.setContentPane(tableScrollPane);
 */


/**
 * �ڲ������ʹ���ⲿ���˽�л�����private���Σ��ĳ�Ա����
 */


/**
 * JTable�ࣺ��javax.swing���£�������ʾ�ͱ༭�����ά��Ԫ�����
 */


/**
 * JScrollPane�ࣺ��javax.swing���У���һ���������񣬵�
 * �����е����ݳ����˴����С�������ˮƽ��ֱ�Ĺ����������Խ�
 * ���������ӵ�JScrollPane��
 */


/**
 * JMainFrame��̳�Javax.swing.JFrame���ӵ�д��ڵ�
 * ���ԣ�����ֱ�ӵ���Javax.swing.JFrame
 * ��ķ������ô��ڵı��⣬��С��
 * 
 * ���JMainFrame��û�м̳�JFrame�࣬��ôJMainFrame���
 * û�д��ڵ�����
 */


/**
 * this�ؼ��֣�this�ɿ�����һ������������ֵ�ǵ�ǰ����(�˴�
 * ��JMainFrame�����frame)�����á�
 * ����ķ���������ʹ�õ�this�ؼ��ִ���ʹ�ø÷����Ķ��������
 */


/**
 * ��壨JPanel��������ӵ�JFrame�У�����������Ҳ��һ�����������԰�
 * ��ǩJLabel���ı���JTextField�Ͱ�ťJButton���뵽JPanel��
 */


/**
 * JComponent����swing����Ļ�����ࣨ��Ƹ��ࣩ��
 * ��JComponent���Ǳ�ǩ��JLabel���ı�����JTextField����
 * ť��JButton�������JPanel��Ļ�����ࣨ��Ƹ��ࣩ��
 */


/**
 * JComponent��ĳ��÷�����
 * setpreferredSize()�����������ѡ��С
 * setVisible()��������ɼ��򲻿ɼ�
 * setEnabled()�����Ƿ����ô����
 * setBackground()��������ı���ɫ
 * setFont()�������������
 * setForeground()���������ǰ��ɫ
 * setBorder()��������ı߿�
 */


/**
 * �¼�Դ�������¼��Ķ�����¼�Դ
 * �����������¼�����Ȥ�Ķ���м�����
 * �¼�Դ��addXXXListener��ĳЩ������Ϊ������
 * ������ʵ������һ�����ʵ������ʵ����XXX�����ӿ�.
 * �¼��������¼�Դ���¼����󷢸���ע������м�������
 * �������������¼������ڷ�װ����Ϣ���������Ѹ������Ӧ����¼���
 */


/**
 * ��ʱ�����¼�Դ��ע��ͬһ����������
 * ���磺������ť��������ϣ����Ƕ�����Ϊ��������ʹ����ͬ�İ�ť�¼��󴰿ڵı�����ʾ��ͬ��־��
 * getResource()�����ж��¼������ĸ����󼤷��ġ�
 */


/**
 * �������ࣺ�������ڣ��¼�����
 * @author zwqabc
 *
 */
@SuppressWarnings("serial")
public class JMainFrame extends JFrame {

	//�������
	private JTextField numberTextField = null;                //����ı���
	private JTextField nameTextField = null;                  //�����ı���
	private JTextField phonenumberTextField = null;           //�ֻ����ı���
	private JTextField emailTextField = null;                 //�ʼ��ı���
	private JTextField addressTextField = null;               //��ַ�ı���
	private JTextField pathTextField = null;                  //��ϵ��·���ı���
	private JTextField queryTextField = null;                 //��ϵ�������ı���
	private JButton addButton = null;                         //������ϵ�˰�ť
	private JButton viewButton = null;                        //�鿴��ϵ�˰�ť
	private JButton queryButton = null;                       //��ѯ��ϵ�˰�ť
	private JButton exportButton = null;                      //������ϵ�˰�ť
	private JRadioButton maleButton = null,femaleButton = null; //�Ա�(��,Ů)��ť 	
	private JComboBox<String> relationshipBox = null;                 //�����б�
	//����һ�����������ı����Ҫ����������JTable��DefaultTableModel��JScrollPane
	private DefaultTableModel tableModel = null;              //���ģ��
	private JTable table = null;                              //���
	private JScrollPane tableScrollPane = null;               //����һ����������
	
	private List<?> result = null;                               //�洢��ϵ����Ϣ�б�

	
	
	

	/**
	 * �޲ι��췽������ʼ�����ڵĻ������ԣ�����⣬��С�ȣ�
	 * ���ܣ���������ʱ��ʼ�������ʵ�����������������ͬ������
	 * һ�������˹��췽�����ڶ������ϵͳ���Զ����ù��췽����ʼ������
	 */
	public JMainFrame() {
		this.setTitle("ͨѶ¼");                              //���ñ���
		this.setSize(600,500);                              //���ô���ߴ�
		//JLabel label = new JLabel("��ӭ����ͨѶ¼ϵͳ");         //������ӭ��ǩ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رմ���
		//this.getContentPane().add(label);                   //ͨ�������ڵ�getContentpane()������������ڣ�JFrame������Ĭ�ϵ����ݴ���ʹ��Ĭ�ϵ����ݴ����add()��������ǩ(label)��ӵ����ݴ�����
		ImageIcon  imageIcon = new ImageIcon("Image\\Contact-32.png");//����ͼ�����(��ΪͼƬ�ڸù����ļ����£����������ַ������ļ�������ͼ��������ȡͼ��)�����ͼ���ڴ˹����ļ�������Ҫ������ķ�����ʹ�þ���·��
		Image image = imageIcon.getImage();                  //��ȡͼ��
		this.setIconImage(image);                            //���ô������Ͻ�ͼ��(����һ����ͼ��)

		//setIconImage(new ImageIcon("Image\\Contact-32.png").getImage()); //������������ͼ��Ĵ��������һ�д���

		System.out.println("ͼ��Ŀ��Ϊ��"+imageIcon.getIconWidth()); //��ȡͼ��Ŀ�ȣ����ڿ���̨�ϴ�ӡ����
		System.out.println("ͼ��ĸ߶�Ϊ��"+imageIcon.getIconHeight());//���ͼ��ĸ߶ȣ����ڿ���̨�ϴ�ӡ����

		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();//��ȡ��Ļ�ߴ�
		int x = (size.width-600)/2;                           //ʹ�����ڿ���ϴ�����Ļ������
		int y = (size.height-500)/2;                          //ʹ�����ڸ߶��ϴ�����Ļ������
		this.setLocation(x, y);                               //���ô�����ʾ��λ��
		this.setContentPane(getContenPane());                 //���������ӵ�����
        add(new StatusBar(),BorderLayout.SOUTH);

	}

	//����һ����壬������ͼƬ��Ȼ�󽫱��ŵ��������ϣ���󽫸����ŵ�����������
	JPanel imageJPanel = new JPanel() {
		public void paint(Graphics g) {
			ImageIcon icon = new ImageIcon("image\\2.jpg");
			Image image = icon.getImage();                     //��ȡͼ��
			//��drawImage��������ָ��ͼ���������ŵ��ʺ�ָ�������ڲ���ͼ��
			g.drawImage(image,0,0, icon.getIconWidth(), icon.getIconHeight(),Color.red, icon.getImageObserver());//��һ��������Ҫ���Ƶ�ָ��ͼ����� image Ϊ null����˷�����ִ���κβ���	                                                                                          //����������Ϊ ת���˸���ͼ��ʱҪ֪ͨ�Ķ���
		}
	};

	//���������
	public JPanel getContenPane() {	
		JPanel mainPanel = new JPanel();                       //������������
		JPanel northPanel = new JPanel();                      //����һ���������
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); //���������Ϊ��ʽ����
		northPanel.setBackground(Color.orange);                //������������ɫ	
		//mainPanel.setOpaque(true);                            //�������Ϊ͸��, Opaque(��͸��)
		mainPanel.setLayout(new BorderLayout());               //���������Ϊ�߽粼��	
		northPanel.add(getAddPanel1());                        //��1�����ӵ��������
		northPanel.add(getAddPanel2());                        //��2�����ӵ��������
		northPanel.add(getAddPanel3());                        //��3�����ӵ��������
		northPanel.add(getAddPanel4());                        //��4�����ӵ��������
		northPanel.add(getQueryPanel());                       //����ѯ�����ӵ��������
		northPanel.setPreferredSize(new Dimension(600,200));   //���ñ������ĳߴ�Ϊ600*200����600����200��
		mainPanel.add(northPanel,BorderLayout.NORTH);          //�����������ӵ�����壬�����������ı���		
		imageJPanel.add(getTablePane(),BorderLayout.CENTER);   //����������ӵ���壬������������������м�
		mainPanel.add(imageJPanel);                            //����ͼƬ�������ӵ������
		
		return mainPanel;                                      //���������

	}


	//����������ϵ�˵�һ�����
	public JPanel getAddPanel1() {
		JPanel addPanel1 = new JPanel();                         //����������ϵ�˵�һ��������
		addPanel1.setBackground(Color.BLUE);                   //��һ��������ñ�����ɫΪ��ɫ
		//����ϵ��·���ı�ǩ���ı�����ӵ������
		addPanel1.add(new JLabel("��ϵ��·����"));                 //����һ����ǩ����ӵ����
		pathTextField = new JTextField(43);                      //������ϵ�˱���λ�õ��ı���
		//String defaultPath = "D:\\Users\\zwqabc\\Workspaces\\MyEclipse 2017 CI\\ContactJPro\\contacts.txt";
		String defaultPath = "E:\\�������ҵ\\Java\\ContactJPro_Task\\contacts.txt";
		pathTextField.setText(defaultPath);                    //������ϵ��·���ı�����Ĭ����Ϣ
		addPanel1.add(pathTextField);                          //��ϵ��·���ı������е���Ϣ��ӵ����1
		return addPanel1;                                      //���ص�һ�����
	}


	//����������ϵ�˵ڶ������
	public JPanel getAddPanel2() {
		JPanel addPanel2 = new JPanel();                         //����������ϵ�˵ڶ������
		//����ϵ�˱�ţ��������ֻ�����������ӵ���� 
		addPanel2.add(new JLabel("��ţ�"));                    //���ñ�ű�ǩ��������ӵ����2��
		numberTextField = new JTextField(12);                   //���ñ���ı��򳤶�Ϊ12��	
		
		/**
		 * �����ڲ���
		 * �۽����
		 */
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				numberTextField.requestFocusInWindow();        //�۽���굽����ı�����
			}
		});
		addPanel2.add(numberTextField);                       //������ı������е���Ϣ��ӵ����2��  

		addPanel2.add(new JLabel("������"));                    //����������ǩ
		nameTextField = new JTextField(12);                     //���������ı��򳤶�Ϊ12��
		addPanel2.add(nameTextField);                         //�������ı������е���Ϣ��ӵ����2��     

		addPanel2.add(new JLabel("�ֻ��ţ�"));                   //�����ֻ��ű�ǩ��������ӵ����2��
		phonenumberTextField = new JTextField(12);              //�����ֻ����ı��򳤶�Ϊ12��
		addPanel2.add(phonenumberTextField);                  //���ֻ����ı������е���Ϣ��ӵ����2��     
		return addPanel2;
	}



	//����������ϵ�˵��������
	public JPanel getAddPanel3() {
		JPanel addPanel3 = new JPanel();                       //����������ϵ�˵��������
		//����ϵ�ˣ���ַ��������ӵ������
		addPanel3.add(new JLabel("emile��"));                 //����email��ǩ��������ӵ�3�����
		emailTextField = new JTextField(12);                   //����email�ı��򳤶�Ϊ12��
		addPanel3.add(emailTextField);                       //��email�ı������е���Ϣ��ӵ����2�� 

		addPanel3.add(new JLabel("��ַ��"));                  //���õ�ַ��ǩ��������ӵ����3��
		addressTextField = new JTextField(12);                 //���õ�ַ�ı��򳤶�Ϊ12��
		addPanel3.add(addressTextField);                     //����ַ�ı������е���Ϣ��ӵ����2�� 
		return addPanel3;
	}

	//����������ϵ�˵��ĸ����
	public JPanel getAddPanel4() {
		JPanel addPanel4 = new JPanel();                       //����������ϵ�˵��ĸ����
		//����ϵ���Ա𣬹�ϵ����������ϵ�˰�ť��ӵ����
		addPanel4.add(new JLabel("�Ա�"));                   //���õ�ַ��ǩ��������ӵ����4��                  
		maleButton = new JRadioButton("��");                    //maleButton��ť����Ϊ��
		femaleButton = new JRadioButton("Ů");                  //femaleButton����ΪŮ                  
		ButtonGroup buttonGroup = new ButtonGroup();          //����һ����ѡ��ť��
		buttonGroup.add(maleButton);                         //����ť��ӵ���ť��ʵ�ֻ��⣨��ѡ��
		buttonGroup.add(femaleButton);                       //����ť��ӵ���ť��ʵ�ֻ��⣨��ѡ��
		addPanel4.add(maleButton);                           //����ѡ��ť��ӵ����4��ע�⣺�ǽ�ÿ����ť��ӵ��������ǽ���ť����ӵ���壩
		addPanel4.add(femaleButton);                         //����ѡ��ť��ӵ����4
		addPanel4.add(new JLabel("��ϵ��"));                   //���ù�ϵ��ǩ����ӵ����4
		String []relationship = {"ͬ��","ͬѧ","����","����","����"};//����һ���ַ������������������б���ѡ��
		relationshipBox = new JComboBox<String>(relationship);          //����һ�������б�����Ͷ���relationshipBox�������������ݷŵ������б����
		addPanel4.add(relationshipBox);                       //�������б�������ӵ����4
		addPanel4.add(getAddButton());                        //��������ϵ�˰�ť��ӵ����4                       
		return addPanel4;                                     //�������4
	}

	/**
	 * ������ϵ�˰�ť��ط���
	 * @return ����ֵΪJButton����
	 */
	@SuppressWarnings("deprecation")
	private JButton getAddButton() {
		if(addButton == null) {                                          //�����ťΪ��
			addButton = new JButton("������ϵ��");                         //��Ϊ��ť����һ������
			addButton.setFont(new Font("�����п�", Font.BOLD, 16));      //�������壨��һ������Ϊ��������������壬�����п��ȣ��ڶ�������Ϊ����ķ����б�壬���壨BOLD���ȣ�����������Ϊ����Ĵ�С���Ե���������һ���㣨point��Ϊ1/72Ӣ�磩
			addButton.setForeground(Color.yellow);                 //����ǰ��ɫ����ť������ɫ��Ϊ��ɫ
			addButton.setBackground(Color.blue);                   //���ñ���ɫΪ��ɫ
			addButton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));//������(������ϵ��)��ӹ����ʽ��������ƶ���������ʱ����Ϊ���ͣ�HAND_CURSOR��ʾ��״�������
			
			/**
			 * �����ڲ���
			 */
			addButton.addActionListener(new ActionListener() {        //Ϊ��ť��Ӽ�����,�¼�ԴΪaddButton���¼�ԴaddButtonͨ��addXXXListener������ĳЩ����new ActionListener()������Ϊ��������������ʵ������һ�����ʵ������ʵ����XXX��Action�������ӿ�

				//@Override ��дactionPerformed����
				public void actionPerformed(ActionEvent e) {
					String number = numberTextField.getText();          //����ı������û�����ı��
					String name = nameTextField.getText();              //����ı������û����������
					String phonenumber = phonenumberTextField.getText();//����ı������û�������ֻ���
					String email = emailTextField.getText();            //����ı������û������email
					String address = addressTextField.getText();        //����ı������û�����ĵ�ַ
					String gender = null;                               //����һ������û�ѡ����Ա�ı�������ʼ��Ϊ��
					if(maleButton.isSelected()) {                      //���maleButton��ť��ѡ��
						gender = "��";	                                  //��gender��������Ϊ��		
					} else if(femaleButton.isSelected()) {	          //���femaleButton��ť��ѡ��				
						gender = "Ů";	                                  //��gender��������ΪŮ
					}
					String relationship = (String)relationshipBox.getSelectedItem();//��ȡ��ϵ�����б�����û���ѡ��ǿ��ת��ΪString����
					String contactInfor = number+"##"+name+"##"+phonenumber+"##"+email+"##"+address+"##"+gender+"##"+relationship;//����һ���ַ�������contactInfor����û��������Ϣ��ѡ���ѡ��
					String defaultpath = pathTextField.getText();               //��ȡ�ļ��洢·��
					FileOperation fileOperation = new FileOperation();          //����һ���ļ�������Ķ���
					if(fileOperation.saveContact(contactInfor, defaultpath)) { //����fileOperation���е�saveContact����������contactInfor�е���Ϣ�浽λ��defaultpath·�����ļ���//�������ֵΪtrue����ִ��if�е����
						JOptionPane.showConfirmDialog(null, "��ϵ����Ϣ����ɹ�"); //����ȷ�϶Ի�����ʾ�����ļ��ɹ�

					} else {
						JOptionPane.showConfirmDialog(null, "��ϵ����Ϣ����ʧ��"); //����ȷ�϶Ի�����ʾ�����ļ�ʧ��

					}

				}
			});
		}
		return addButton;                                            //����������ϵ�˰�ť
	}

	//������ѯ��ϵ�����
	private JPanel getQueryPanel() {
		JPanel queryPanel = new JPanel();                            //������ѯ��ϵ�����
		//�������ӵ����
		queryPanel.add(new JLabel("��ϵ��������"));                     //����ǩ��ӵ����
		queryTextField = new JTextField(12);                         //�����ı�����
		queryPanel.add(queryTextField);                              //���ı�����ӵ����
		queryPanel.add(getQueryButton());                            //����ѯ��ť��ӵ����
		queryPanel.add(getViewButton());                             //���鿴��ϵ�˰�ť��ӵ���ѯ��ϵ�����
		queryPanel.add(getExportButton());                           //��������ϵ�˰�ť��ӵ���ѯ��ϵ�����
		return queryPanel;                                           //���ز�ѯ��ϵ�����
	}

	/**
	 * ��ѯ��ϵ�˰�ť����
	 * @return queryButton
	 */
	@SuppressWarnings("deprecation")
	private JButton getQueryButton(){
		if(queryButton == null){                        
			queryButton = new JButton("��ѯ��ϵ��");                            //������ѯ��ϵ�˰�ť
			queryButton.setFont(new Font("�����п�", Font.BOLD, 16));        //�������壨��һ������Ϊ��������������壬�����п��ȣ��ڶ�������Ϊ����ķ����б�壬����ȣ�����������Ϊ����Ĵ�С���Ե���������һ���㣨point��Ϊ1/72Ӣ�磩
			queryButton.setForeground(Color.orange);                       //���ð�ť������ɫΪ��ɫ
			queryButton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));//������(��ѯ��ϵ��)��ӹ����ʽ��������ƶ���������ʱ����Ϊ���ͣ�HAND_CURSOR��ʾ��״�������
			
			/**
			 * �����ڲ���
			 */
			queryButton.addActionListener(new ActionListener() {           //Ϊ��ť��Ӽ�����

				//@Override ��дactionPerformed����
				public void actionPerformed(ActionEvent e) {
					System.out.println("��ť�����");
					FileOperation fileOperation = new FileOperation();       //����һ���ļ�������Ķ���
					String filePath = pathTextField.getText();               //�ļ���ȡ·��
					String queryname = queryTextField.getText();             //�û�����ϵ�������ı������������ϵ������
					if(queryname.equals("")) {                               //�����ϵ�������ı���������Ϊ��
						result = fileOperation.getContacts(filePath);        //����·����ѯ

					} else {                                                 //��ϵ�������ı��������ݲ�Ϊ��
						result = fileOperation.getContacts(filePath,queryname);//����������ѯ
					}
					if(result == null) {                                    //result����Ϊ�գ���������������������ļ��У�
						JOptionPane.showMessageDialog(null,"��ϵ����Ϣ��ѯʧ��");
					} else { //�����ѯ�������ݲ�Ϊ�գ�����ձ��׼����һ����ʾ�����ڱ����                                               //result���ݲ�Ϊ�գ�����ѯ�ɹ�
						
						//ֱ���������˾�����ѭ��
						while(tableModel.getRowCount()>0) {                //���ģ���е�����������
							tableModel.removeRow(0);                     //�Ƴ������У��б���㿪ʼ��
//						int rowCount = tableModel.getRowCount();
//						rowCount--;
						}
						for(int i=0;i<result.size();i++) {                 //ȡ����ϵ����Ϣ��������
							//String[]info=(String[])result.get(i);
							//Object[]date={info[1],info[2],info[3]};     //�����ϵ���������ֻ��ţ�����
							Contact contact=(Contact)result.get(i);
							Object[]date={contact.getName(),contact.getAddress(),contact.getEmail()};
							tableModel.addRow(date);                      //����ԭ�ͣ�public void addRow(Object[] rowData)��rowData - Ҫ��ӵ������ݣ���ѡ��
						}
					}
				}
			});

		}
		return queryButton;                                              //���ز�ѯ��ϵ�˰�ť

	}


	/**
	 * �鿴��ϵ�˰�ť����
	 * @return viewButton
	 */
	private JButton getViewButton(){                                     
		if(viewButton==null){
			viewButton=new JButton("�鿴��ϵ��");//�����鿴��ϵ�˰�ť
			viewButton.setFont(new Font("�����п�", Font.BOLD, 16));      //�������壨��һ������Ϊ��������������壬�����п��ȣ��ڶ�������Ϊ����ķ����б�壬����ȣ�����������Ϊ����Ĵ�С���Ե���������һ���㣨point��Ϊ1/72Ӣ�磩
			viewButton.setForeground(Color.magenta);
			
			/**
			 * �����ڲ���
			 */
			viewButton.addActionListener(new ActionListener() {         //Ϊ�鿴��ϵ�˰�ť��Ӽ�����

				/**
				 * ����鿴��ť�¼��ķ���
				 */
				@Override //��дactionPerformed����
				public void actionPerformed(ActionEvent e) {            //�����ڲ��࣬����ť�¼�
					//System.out.println("�鿴��ϵ�˰�ť�����");
					int selectRow = table.getSelectedRow();               //������ѡ�еı����к�

					//�жϱ��ѡ�����
					if(selectRow==-1) {                               //δѡ�б��
						JOptionPane.showMessageDialog(null,"��ѡ��Ҫ�鿴����ϵ�ˣ�");
					} else { //ѡ�б���ĳһ��
						table.setSelectionForeground(Color.blue);//ĳһ�б�ѡ�к��ǰ��ɫ
						table.setSelectionBackground(Color.cyan);//ĳһ�б�ѡ�к�ı���ɫ
						Contact contact =(Contact)result.get(selectRow);//��ñ���кŶ�Ӧ����ϵ��ʵ��
						showInfor(contact);                             //����ϵ����Ϣ������ʾ�������
					}
				}
			});
		}

		return viewButton;

	}

	/**
	 * ��ʾ��ϵ����Ϣ����ǰ���ڵ�����͵������ڣ�
	 * @param contact
	 */
	private void showInfor(Contact contact){
		//����Ӧ����ϵ����Ϣд���������ǰ���ڵ������
		numberTextField.setText(contact.getNumber());                         //��ϵ�˱��
		nameTextField.setText(contact.getName());                             //��ϵ������
		phonenumberTextField.setText(contact.getPhonenumber());               //��ϵ���ֻ���
		emailTextField.setText(contact.getEmail());                           //��ϵ��email

		String gender=contact.getGender();                                     //����ϵ���Ա���ʾ����ѡ��ť
		if(gender.equals("��")){
			maleButton.setSelected(true);
		} else {
			femaleButton.setSelected(true);
		}

		//����ϵ�˹�ϵ��ʾ����������
		String relationship = contact.getRelationship();
		if(relationship.equals("ͬ��")) {
			relationshipBox.setSelectedIndex(0);
		} else if (relationship.equals("ͬѧ")) {
			relationshipBox.setSelectedIndex(1);

		} else if (relationship.equals("����")) {
			relationshipBox.setSelectedIndex(2);

		} else {
			relationshipBox.setSelectedIndex(3);

		}
		addressTextField.setText(contact.getAddress());//��ϵ�˵�ַ
        
		//��ʾ����ʽ����
		viewFrame viewFrame = new viewFrame();                     //����viewFrame��Ķ���viewFrame
		viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//���õ����Ĵ��ڹرղ���
		viewFrame.setVisible(true);                                //���õ������ڿɼ�
		viewFrame.setContact(contact);                             //����viewFrame���е�setContact����
	}

	//������������ķ���
	private JScrollPane getTablePane(){                                 //����ֵ����ΪJScrollPane
		if(tableScrollPane==null){

			//�������ģ�Ͳ�����ӱ�ͷ
			tableModel=new DefaultTableModel();                         //�������ģ��
			tableModel.addColumn("����");                                //����һ�еı���
			tableModel.addColumn("�ֻ���");                               //���ڶ��еı���
			tableModel.addColumn("����");                                //�������еı���

			//������񣺽����ģ����ӵ������
			table=new JTable(tableModel);                               //�������

			//�������ӵ�����������
			tableScrollPane=new JScrollPane(table);                     //�������ӵ���������	
			
			//���ô���������ĳߴ�
			tableScrollPane.setPreferredSize(new Dimension(580,250));   //���ñ��ĳߴ�
		}
		return tableScrollPane;                                         //���ع�������
	}


	/**
	 * ������ϵ��
	 * @return
	 */
	private JButton getExportButton() {
		if(exportButton == null) {
			exportButton = new JButton("������ϵ��");                        //����������ϵ�˰�ť
			
			/**
			 * �����ڲ��� ����дactionPerformed����
			 */
			exportButton.addActionListener(new ActionListener() {       //�����ڲ��࣬����ť�¼�

				/**
				 * ��������ť�¼��ķ���
				 * @Override
				 */
				public void actionPerformed(ActionEvent e) {
					String filePath=pathTextField.getText();            //��ȡ�ļ��ľ���·��
					FileOperation fileOperation = new FileOperation();
					if(fileOperation.saveContact(result, filePath)) {    //�����ɹ�
						JOptionPane.showMessageDialog(null, "������ϵ����Ϣ�ɹ�");
					} else {
						JOptionPane.showMessageDialog(null, "������ϵ����Ϣʧ��");
					}
				}
			});
		}
		return exportButton;
	}


}
