package com.ruanko.view;

import java.awt.BorderLayout;
import java.awt.Color;                                
import java.awt.Cursor;                              //光标
import java.awt.Dimension;                           
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;                            //工具包
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
import javax.swing.JTable;                            //表格
import javax.swing.JTextField;                       
import javax.swing.table.DefaultTableModel;           //表格模型
import com.ruanko.business.FileOperation;             
import com.ruanko.model.Contact;
import com.ruanko.xsgl.view.StatusBar;                      


/**
 * @param 描述方法的参数
 * @return 描述返回值，对于无返回值的方法或构造方法，@return可以省略
 * @throws 描述在什么情况下抛出什么类型的异常
 * @author 描述作者
 * @version 描述版本 
 * @since 描述该类可以运行的JDK版本
 * @see 参考转向，也就是相关主题
 * @link 转向成员的超链接。label为链接文字。package.class#member将被自动转换为指向package.class的member文件的URL
 */



/**
 * List存储字符串数组
 * List是一个接口，实现List接口的容器类中的元素是有顺序的，而且可以重复
 * List容器中的元素都对应一个整数型的序号记载其在容器中的位置，可以根据序号存取容器中的元素
 * 注意：接口不能构造对象，但可以声明接口变量，并且接口变量必须指向一个实现了该接口的类（ArrayList类）的对象
 * 例如：List contacts = new ArrayList();
 * 声明List变量contacts，并且让该变量指向ArrayList类的对象
 * ArrayList类实现了List接口，拥有List接口特性
 */


/**
 * 注意：滚动窗格算是一个面板，所以将组件添加到滚动窗格面板后，还要将滚动窗格面板添加到主面板
 * 创建一个带滚动条的表格方法：
 * 第一步：创建表格模型并且添加表头（使用DefaultTableModel）
 *	    DefaultTableModel tableModel=new DefaultTableModel();                         //创建表格模型
 *	    tableModel.addColumn("姓名");                                //表格第一列的标题
 *	    tableModel.addColumn("手机号");                               //表格第二列的标题
 *	    tableModel.addColumn("邮箱"); 
 * 第二步：创建表格（使用JTable）
 *      JTable table = new JTable(tableModel);
 * 第三步：将表格添加到滚动窗格中
 *      JScrollPane tableScrollPane=new JScrollPane(table);                     //将表格添加到滚动窗格	
 * 第四步：将面板添加到窗口中
 *      this.setContentPane(tableScrollPane);
 */


/**
 * 内部类可以使用外部类的私有化（即private修饰）的成员变量
 */


/**
 * JTable类：在javax.swing包下，用来显示和编辑常规二维单元表（表格）
 */


/**
 * JScrollPane类：在javax.swing包中，是一个滚动窗格，当
 * 窗格中的内容超出了窗格大小，会出现水平或垂直的滚动条，可以将
 * 表格等组件添加到JScrollPane中
 */


/**
 * JMainFrame类继承Javax.swing.JFrame类后，拥有窗口的
 * 特性，可以直接调用Javax.swing.JFrame
 * 类的方法设置窗口的标题，大小等
 * 
 * 如果JMainFrame类没有继承JFrame类，那么JMainFrame类就
 * 没有窗口的特性
 */


/**
 * this关键字：this可看成是一个变量，它的值是当前对象(此处
 * 是JMainFrame类对象frame)的引用。
 * 在类的方法定义中使用的this关键字代表使用该方法的对象的引用
 */


/**
 * 面板（JPanel）可以添加到JFrame中，并且它自身也是一个容器，可以把
 * 标签JLabel、文本域JTextField和按钮JButton加入到JPanel中
 */


/**
 * JComponent类是swing组件的基类或超类（或称父类）。
 * 即JComponent类是标签类JLabel、文本域类JTextField、按
 * 钮类JButton、面板类JPanel类的基类或超类（或称父类）。
 */


/**
 * JComponent类的常用方法：
 * setpreferredSize()设置组件的首选大小
 * setVisible()设置组件可见或不可见
 * setEnabled()设置是否启用此组件
 * setBackground()设置组件的背景色
 * setFont()设置组件的字体
 * setForeground()设置组件的前景色
 * setBorder()设置组件的边框
 */


/**
 * 事件源：产生事件的对象叫事件源
 * 监听器：对事件感兴趣的对象叫监听器
 * 事件源用addXXXListener将某些对象设为监听器
 * 监听器实际上是一个类的实例，它实现了XXX监听接口.
 * 事件发生后，事件源将事件对象发给已注册的所有监听器。
 * 监听器随后根据事件对象内封装的信息，决定自已该如何响应这个事件。
 */


/**
 * 有时候多个事件源都注册同一个监听器。
 * 例如：两个按钮放于面板上，它们都用行为监听器。使按不同的按钮事件后窗口的标题显示不同标志。
 * getResource()用来判断事件是由哪个对象激发的。
 */


/**
 * 主窗口类：创建窗口，事件处理
 * @author zwqabc
 *
 */
@SuppressWarnings("serial")
public class JMainFrame extends JFrame {

	//窗口组件
	private JTextField numberTextField = null;                //编号文本域
	private JTextField nameTextField = null;                  //姓名文本域
	private JTextField phonenumberTextField = null;           //手机号文本域
	private JTextField emailTextField = null;                 //邮件文本域
	private JTextField addressTextField = null;               //地址文本域
	private JTextField pathTextField = null;                  //联系人路径文本域
	private JTextField queryTextField = null;                 //联系人姓名文本域
	private JButton addButton = null;                         //新增联系人按钮
	private JButton viewButton = null;                        //查看联系人按钮
	private JButton queryButton = null;                       //查询联系人按钮
	private JButton exportButton = null;                      //导出联系人按钮
	private JRadioButton maleButton = null,femaleButton = null; //性别(男,女)按钮 	
	private JComboBox<String> relationshipBox = null;                 //下拉列表
	//创建一个带滚动条的表格需要调用三个类JTable，DefaultTableModel，JScrollPane
	private DefaultTableModel tableModel = null;              //表格模型
	private JTable table = null;                              //表格
	private JScrollPane tableScrollPane = null;               //创建一个滚动窗格
	
	private List<?> result = null;                               //存储联系人信息列表

	
	
	

	/**
	 * 无参构造方法：初始化窗口的基本属性（如标题，大小等）
	 * 功能：创建对象时初始化对象的实例变量，与类具有相同的名字
	 * 一旦定义了构造方法，在对象定义后，系统将自动调用构造方法初始化对象
	 */
	public JMainFrame() {
		this.setTitle("通讯录");                              //设置标题
		this.setSize(600,500);                              //设置窗格尺寸
		//JLabel label = new JLabel("欢迎进入通讯录系统");         //创建欢迎标签
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口
		//this.getContentPane().add(label);                   //通过主窗口的getContentpane()方法获得主窗口（JFrame创建）默认的内容窗格，使用默认的内容窗格的add()方法将标签(label)添加到内容窗格中
		ImageIcon  imageIcon = new ImageIcon("Image\\Contact-32.png");//创建图像对象(因为图片在该工程文件夹下，所以用这种方法（文件夹名加图像名）获取图像)，如果图像不在此工程文件夹下则要用另外的方法且使用绝对路径
		Image image = imageIcon.getImage();                  //获取图标
		this.setIconImage(image);                            //设置窗口左上角图标(方法一设置图标)

		//setIconImage(new ImageIcon("Image\\Contact-32.png").getImage()); //上面三行设置图标的代码可用这一行代替

		System.out.println("图像的宽度为："+imageIcon.getIconWidth()); //获取图像的宽度，并在控制台上打印出来
		System.out.println("图像的高度为："+imageIcon.getIconHeight());//获得图像的高度，并在控制台上打印出来

		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕尺寸
		int x = (size.width-600)/2;                           //使窗口在宽度上处于屏幕正中央
		int y = (size.height-500)/2;                          //使窗口在高度上处于屏幕正中央
		this.setLocation(x, y);                               //设置窗口显示的位置
		this.setContentPane(getContenPane());                 //将主面板添加到窗口
        add(new StatusBar(),BorderLayout.SOUTH);

	}

	//创建一个面板，用来放图片，然后将表格放到这个面板上，最后将该面板放到主面板的中心
	JPanel imageJPanel = new JPanel() {
		public void paint(Graphics g) {
			ImageIcon icon = new ImageIcon("image\\2.jpg");
			Image image = icon.getImage();                     //获取图像
			//用drawImage方法绘制指定图像中已缩放到适合指定矩形内部的图像。
			g.drawImage(image,0,0, icon.getIconWidth(), icon.getIconHeight(),Color.red, icon.getImageObserver());//第一个参数：要绘制的指定图像。如果 image 为 null，则此方法不执行任何操作	                                                                                          //第六个参数为 转换了更多图像时要通知的对象。
		}
	};

	//创建主面板
	public JPanel getContenPane() {	
		JPanel mainPanel = new JPanel();                       //创建主面板对象
		JPanel northPanel = new JPanel();                      //创建一个北面面板
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); //北面板设置为流式布局
		northPanel.setBackground(Color.orange);                //设置主面板的颜色	
		//mainPanel.setOpaque(true);                            //主面板设为透明, Opaque(不透明)
		mainPanel.setLayout(new BorderLayout());               //主面板设置为边界布局	
		northPanel.add(getAddPanel1());                        //将1面板添加到北面面板
		northPanel.add(getAddPanel2());                        //将2面板添加到北面面板
		northPanel.add(getAddPanel3());                        //将3面板添加到北面面板
		northPanel.add(getAddPanel4());                        //将4面板添加到北面面板
		northPanel.add(getQueryPanel());                       //将查询面板添加到北面面板
		northPanel.setPreferredSize(new Dimension(600,200));   //设置北面面板的尺寸为600*200（宽600，高200）
		mainPanel.add(northPanel,BorderLayout.NORTH);          //将北面面板添加到主面板，并放在主面板的北面		
		imageJPanel.add(getTablePane(),BorderLayout.CENTER);   //将表格面板添加到面板，并将表格面板放在面板的中间
		mainPanel.add(imageJPanel);                            //将放图片的面板添加到主面板
		
		return mainPanel;                                      //返回主面板

	}


	//创建新增联系人第一个面板
	public JPanel getAddPanel1() {
		JPanel addPanel1 = new JPanel();                         //创建新增联系人第一个面板对象
		addPanel1.setBackground(Color.BLUE);                   //第一个面板设置背景颜色为蓝色
		//将联系人路径的标签和文本域添加到面板中
		addPanel1.add(new JLabel("联系人路径："));                 //创建一个标签并添加到面板
		pathTextField = new JTextField(43);                      //设置联系人保存位置的文本域
		//String defaultPath = "D:\\Users\\zwqabc\\Workspaces\\MyEclipse 2017 CI\\ContactJPro\\contacts.txt";
		String defaultPath = "E:\\软酷网作业\\Java\\ContactJPro_Task\\contacts.txt";
		pathTextField.setText(defaultPath);                    //设置联系人路径文本框中默认信息
		addPanel1.add(pathTextField);                          //联系人路径文本框即其中的信息添加到面板1
		return addPanel1;                                      //返回第一个面板
	}


	//创建新增联系人第二个面板
	public JPanel getAddPanel2() {
		JPanel addPanel2 = new JPanel();                         //创建新增联系人第二个面板
		//将联系人编号，姓名，手机号相关组件添加到面板 
		addPanel2.add(new JLabel("编号："));                    //设置编号标签并将其添加到面板2中
		numberTextField = new JTextField(12);                   //设置编号文本域长度为12列	
		
		/**
		 * 匿名内部类
		 * 聚焦光标
		 */
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				numberTextField.requestFocusInWindow();        //聚焦光标到编号文本框中
			}
		});
		addPanel2.add(numberTextField);                       //将编号文本框即其中的信息添加到面板2中  

		addPanel2.add(new JLabel("姓名："));                    //设置姓名标签
		nameTextField = new JTextField(12);                     //设置姓名文本域长度为12列
		addPanel2.add(nameTextField);                         //将姓名文本框即其中的信息添加到面板2中     

		addPanel2.add(new JLabel("手机号："));                   //设置手机号标签并将其添加到面板2中
		phonenumberTextField = new JTextField(12);              //设置手机号文本域长度为12列
		addPanel2.add(phonenumberTextField);                  //将手机号文本框即其中的信息添加到面板2中     
		return addPanel2;
	}



	//创建新增联系人第三个面板
	public JPanel getAddPanel3() {
		JPanel addPanel3 = new JPanel();                       //创建新增联系人第三个面板
		//将联系人，地址相关组件添加到面板中
		addPanel3.add(new JLabel("emile："));                 //设置email标签并将其添加到3面板中
		emailTextField = new JTextField(12);                   //设置email文本域长度为12列
		addPanel3.add(emailTextField);                       //将email文本框即其中的信息添加到面板2中 

		addPanel3.add(new JLabel("地址："));                  //设置地址标签并将其添加到面板3中
		addressTextField = new JTextField(12);                 //设置地址文本域长度为12列
		addPanel3.add(addressTextField);                     //将地址文本框即其中的信息添加到面板2中 
		return addPanel3;
	}

	//创建新增联系人第四个面板
	public JPanel getAddPanel4() {
		JPanel addPanel4 = new JPanel();                       //创建新增联系人第四个面板
		//将联系人性别，关系，和新增联系人按钮添加到面板
		addPanel4.add(new JLabel("性别："));                   //设置地址标签并将其添加到面板4中                  
		maleButton = new JRadioButton("男");                    //maleButton按钮设置为男
		femaleButton = new JRadioButton("女");                  //femaleButton设置为女                  
		ButtonGroup buttonGroup = new ButtonGroup();          //创建一个单选按钮组
		buttonGroup.add(maleButton);                         //将按钮添加到按钮组实现互斥（单选）
		buttonGroup.add(femaleButton);                       //将按钮添加到按钮组实现互斥（单选）
		addPanel4.add(maleButton);                           //将单选按钮添加到面板4（注意：是将每个按钮添加到面板而不是将按钮组添加到面板）
		addPanel4.add(femaleButton);                         //将单选按钮添加到面板4
		addPanel4.add(new JLabel("关系："));                   //设置关系标签并添加到面板4
		String []relationship = {"同事","同学","亲戚","朋友","家人"};//创建一个字符串类型数组存放下拉列表框的选项
		relationshipBox = new JComboBox<String>(relationship);          //创建一个下拉列表框类型对象relationshipBox，并将数组内容放到下拉列表框中
		addPanel4.add(relationshipBox);                       //将下拉列表框对象添加到面板4
		addPanel4.add(getAddButton());                        //将新增联系人按钮添加到面板4                       
		return addPanel4;                                     //返回面板4
	}

	/**
	 * 新增联系人按钮监控方法
	 * @return 返回值为JButton类型
	 */
	@SuppressWarnings("deprecation")
	private JButton getAddButton() {
		if(addButton == null) {                                          //如果按钮为空
			addButton = new JButton("新增联系人");                         //则为按钮创建一个名字
			addButton.setFont(new Font("华文行楷", Font.BOLD, 16));      //设置字体（第一个参数为字体的名称如宋体，华文行楷等，第二个参数为字体的风格如斜体，黑体（BOLD）等，第三个参数为字体的大小，以点来衡量，一个点（point）为1/72英寸）
			addButton.setForeground(Color.yellow);                 //设置前景色（按钮字体颜色）为黄色
			addButton.setBackground(Color.blue);                   //设置背景色为蓝色
			addButton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));//给文字(新增联系人)添加光标样式，当光标移动到文字上时光标变为手型）HAND_CURSOR表示手状光标类型
			
			/**
			 * 匿名内部类
			 */
			addButton.addActionListener(new ActionListener() {        //为按钮添加监听器,事件源为addButton，事件源addButton通过addXXXListener方法将某些对象（new ActionListener()）设置为监听器，监听器实际上是一个类的实例，它实现了XXX（Action）监听接口

				//@Override 重写actionPerformed方法
				public void actionPerformed(ActionEvent e) {
					String number = numberTextField.getText();          //获得文本框中用户输入的编号
					String name = nameTextField.getText();              //获得文本框中用户输入的姓名
					String phonenumber = phonenumberTextField.getText();//获得文本框中用户输入的手机号
					String email = emailTextField.getText();            //获得文本框中用户输入的email
					String address = addressTextField.getText();        //获得文本框中用户输入的地址
					String gender = null;                               //创建一个存放用户选择的性别的变量并初始化为空
					if(maleButton.isSelected()) {                      //如果maleButton按钮被选则
						gender = "男";	                                  //则将gender变量设置为男		
					} else if(femaleButton.isSelected()) {	          //如果femaleButton按钮被选则				
						gender = "女";	                                  //则将gender变量设置为女
					}
					String relationship = (String)relationshipBox.getSelectedItem();//获取关系下拉列表框中用户的选择，强制转换为String类型
					String contactInfor = number+"##"+name+"##"+phonenumber+"##"+email+"##"+address+"##"+gender+"##"+relationship;//创建一个字符串变量contactInfor存放用户输入的信息和选择的选项
					String defaultpath = pathTextField.getText();               //获取文件存储路径
					FileOperation fileOperation = new FileOperation();          //创建一个文件操作类的对象
					if(fileOperation.saveContact(contactInfor, defaultpath)) { //调用fileOperation类中的saveContact方法将变量contactInfor中的信息存到位于defaultpath路径的文件中//如果返回值为true，则执行if中的语句
						JOptionPane.showConfirmDialog(null, "联系人信息保存成功"); //弹出确认对话框提示保存文件成功

					} else {
						JOptionPane.showConfirmDialog(null, "联系人信息保存失败"); //弹出确认对话框提示保存文件失败

					}

				}
			});
		}
		return addButton;                                            //返回新增联系人按钮
	}

	//创建查询联系人面板
	private JPanel getQueryPanel() {
		JPanel queryPanel = new JPanel();                            //创建查询联系人面板
		//将组件添加到面板
		queryPanel.add(new JLabel("联系人姓名："));                     //将标签添加到面板
		queryTextField = new JTextField(12);                         //设置文本域宽度
		queryPanel.add(queryTextField);                              //将文本域添加到面板
		queryPanel.add(getQueryButton());                            //将查询按钮添加到面板
		queryPanel.add(getViewButton());                             //将查看联系人按钮添加到查询联系人面板
		queryPanel.add(getExportButton());                           //将导出联系人按钮添加到查询联系人面板
		return queryPanel;                                           //返回查询联系人面板
	}

	/**
	 * 查询联系人按钮方法
	 * @return queryButton
	 */
	@SuppressWarnings("deprecation")
	private JButton getQueryButton(){
		if(queryButton == null){                        
			queryButton = new JButton("查询联系人");                            //创建查询联系人按钮
			queryButton.setFont(new Font("华文行楷", Font.BOLD, 16));        //设置字体（第一个参数为字体的名称如宋体，华文行楷等，第二个参数为字体的风格如斜体，黑体等，第三个参数为字体的大小，以点来衡量，一个点（point）为1/72英寸）
			queryButton.setForeground(Color.orange);                       //设置按钮字体颜色为蓝色
			queryButton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));//给文字(查询联系人)添加光标样式，当光标移动到文字上时光标变为手型）HAND_CURSOR表示手状光标类型
			
			/**
			 * 匿名内部类
			 */
			queryButton.addActionListener(new ActionListener() {           //为按钮添加监听器

				//@Override 重写actionPerformed方法
				public void actionPerformed(ActionEvent e) {
					System.out.println("按钮被点击");
					FileOperation fileOperation = new FileOperation();       //创建一个文件操作类的对象
					String filePath = pathTextField.getText();               //文件读取路径
					String queryname = queryTextField.getText();             //用户在联系人姓名文本框中输入的联系人姓名
					if(queryname.equals("")) {                               //如果联系人姓名文本框中内容为空
						result = fileOperation.getContacts(filePath);        //根据路径查询

					} else {                                                 //联系人姓名文本框中内容不为空
						result = fileOperation.getContacts(filePath,queryname);//根据姓名查询
					}
					if(result == null) {                                    //result内容为空（可能是输入的姓名不在文件中）
						JOptionPane.showMessageDialog(null,"联系人信息查询失败");
					} else { //如果查询到的内容不为空，就清空表格，准备再一次显示数据在表格中                                               //result内容不为空，即查询成功
						
						//直到表格清空了就跳出循环
						while(tableModel.getRowCount()>0) {                //表格模型中的行数大于零
							tableModel.removeRow(0);                     //移除第零行（行标从零开始）
//						int rowCount = tableModel.getRowCount();
//						rowCount--;
						}
						for(int i=0;i<result.size();i++) {                 //取出联系人信息存入表格中
							//String[]info=(String[])result.get(i);
							//Object[]date={info[1],info[2],info[3]};     //获得联系人姓名，手机号，邮箱
							Contact contact=(Contact)result.get(i);
							Object[]date={contact.getName(),contact.getAddress(),contact.getEmail()};
							tableModel.addRow(date);                      //函数原型：public void addRow(Object[] rowData)，rowData - 要添加的行数据（可选）
						}
					}
				}
			});

		}
		return queryButton;                                              //返回查询联系人按钮

	}


	/**
	 * 查看联系人按钮方法
	 * @return viewButton
	 */
	private JButton getViewButton(){                                     
		if(viewButton==null){
			viewButton=new JButton("查看联系人");//创建查看联系人按钮
			viewButton.setFont(new Font("华文行楷", Font.BOLD, 16));      //设置字体（第一个参数为字体的名称如宋体，华文行楷等，第二个参数为字体的风格如斜体，黑体等，第三个参数为字体的大小，以点来衡量，一个点（point）为1/72英寸）
			viewButton.setForeground(Color.magenta);
			
			/**
			 * 匿名内部类
			 */
			viewButton.addActionListener(new ActionListener() {         //为查看联系人按钮添加监听器

				/**
				 * 处理查看按钮事件的方法
				 */
				@Override //重写actionPerformed方法
				public void actionPerformed(ActionEvent e) {            //匿名内部类，处理按钮事件
					//System.out.println("查看联系人按钮被点击");
					int selectRow = table.getSelectedRow();               //获得鼠标选中的表格的行号

					//判断表格选中情况
					if(selectRow==-1) {                               //未选中表格
						JOptionPane.showMessageDialog(null,"请选择要查看的联系人！");
					} else { //选中表格的某一行
						table.setSelectionForeground(Color.blue);//某一行被选中后的前景色
						table.setSelectionBackground(Color.cyan);//某一行被选中后的背景色
						Contact contact =(Contact)result.get(selectRow);//获得表格行号对应的联系人实体
						showInfor(contact);                             //将联系人信息反向显示到组件中
					}
				}
			});
		}

		return viewButton;

	}

	/**
	 * 显示联系人信息（当前窗口的组件和弹出窗口）
	 * @param contact
	 */
	private void showInfor(Contact contact){
		//将相应的联系人信息写入组件（当前窗口的组件）
		numberTextField.setText(contact.getNumber());                         //联系人编号
		nameTextField.setText(contact.getName());                             //联系人姓名
		phonenumberTextField.setText(contact.getPhonenumber());               //联系人手机号
		emailTextField.setText(contact.getEmail());                           //联系人email

		String gender=contact.getGender();                                     //将联系人性别显示到单选按钮
		if(gender.equals("男")){
			maleButton.setSelected(true);
		} else {
			femaleButton.setSelected(true);
		}

		//将联系人关系显示到下拉框中
		String relationship = contact.getRelationship();
		if(relationship.equals("同事")) {
			relationshipBox.setSelectedIndex(0);
		} else if (relationship.equals("同学")) {
			relationshipBox.setSelectedIndex(1);

		} else if (relationship.equals("亲戚")) {
			relationshipBox.setSelectedIndex(2);

		} else {
			relationshipBox.setSelectedIndex(3);

		}
		addressTextField.setText(contact.getAddress());//联系人地址
        
		//显示弹出式窗口
		viewFrame viewFrame = new viewFrame();                     //创建viewFrame类的对象viewFrame
		viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置弹出的窗口关闭操作
		viewFrame.setVisible(true);                                //设置弹出窗口可见
		viewFrame.setContact(contact);                             //调用viewFrame类中的setContact方法
	}

	//创建滚动窗格的方法
	private JScrollPane getTablePane(){                                 //返回值类型为JScrollPane
		if(tableScrollPane==null){

			//创建表格模型并且添加表头
			tableModel=new DefaultTableModel();                         //创建表格模型
			tableModel.addColumn("姓名");                                //表格第一列的标题
			tableModel.addColumn("手机号");                               //表格第二列的标题
			tableModel.addColumn("邮箱");                                //表格第三列的标题

			//创建表格：将表格模型添加到表格中
			table=new JTable(tableModel);                               //创建表格

			//将表格添加到滚动窗格中
			tableScrollPane=new JScrollPane(table);                     //将表格添加到滚动窗格	
			
			//设置带滚动窗格的尺寸
			tableScrollPane.setPreferredSize(new Dimension(580,250));   //设置表格的尺寸
		}
		return tableScrollPane;                                         //返回滚动窗格
	}


	/**
	 * 导出联系人
	 * @return
	 */
	private JButton getExportButton() {
		if(exportButton == null) {
			exportButton = new JButton("导出联系人");                        //创建导出联系人按钮
			
			/**
			 * 匿名内部类 ：重写actionPerformed方法
			 */
			exportButton.addActionListener(new ActionListener() {       //匿名内部类，处理按钮事件

				/**
				 * 处理导出按钮事件的方法
				 * @Override
				 */
				public void actionPerformed(ActionEvent e) {
					String filePath=pathTextField.getText();            //获取文件的绝对路径
					FileOperation fileOperation = new FileOperation();
					if(fileOperation.saveContact(result, filePath)) {    //导出成功
						JOptionPane.showMessageDialog(null, "导出联系人信息成功");
					} else {
						JOptionPane.showMessageDialog(null, "导出联系人信息失败");
					}
				}
			});
		}
		return exportButton;
	}


}
