package chessboard;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;

import dao.UserUtil;
    // TODO: Auto-generated Javadoc

    /**
     * The Class LoginFrame.
     *登录窗口
     * @date 2020年7月4日
     * @author zengcongying
     * @version v1.0
     */
public class LoginFrame extends JFrame {
	
	/** The driver name. */
	//数据库连接信息
	private static String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	private static String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=zcy01430&useSSL=true&CharacterEncoding=GBK";
	
	/** The uul. */
	private static UserUtil uul = new UserUtil(driverName, uri);
	
	/** The flag. */
	public static int flag=0;
	
	/** The game name. */
	// 标签
	private JLabel lblUser, lblPassword, gameName;
	
	/** The txt user. */
	// 账号与密码框
	public static JTextField txtUser;
	
	/** The txt password. */
	private JPasswordField txtPassword;
	
	/** The btn clr. */
	// 按钮
	private JButton btnIn, btnReg, btnClr;
	
	/** The lbg image. */
	// 登录窗口图片
	Image lbgImage = null;
	
	/** The t image. */
	ImageIcon tImage=new ImageIcon("登录\\五子棋.png");
	
	/** The tt image. */
	Image ttImage=tImage.getImage();

	/** The width. */
	// 取得屏幕的信息
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	/** The height. */
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new LoginFrame().initLogin();
	}
	
	    /**
    	 * Inits the login.
    	 * 登录窗口初始化
    	 */
	    
	public void initLogin() {
		this.setTitle("登录");
		try {
			lbgImage = ImageIO.read(new File("登录\\背景.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getContentPane().add(new JLabel(new ImageIcon(lbgImage)));
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().setOpaque(false);

		// 添加组件
		gameName = new JLabel("五子棋游戏");
		gameName.setBounds(85, 20, 210, 40);
		gameName.setFont(new Font("楷体", Font.BOLD, 40));
		this.getLayeredPane().add(gameName);

		lblUser = new JLabel("用户名：");
		lblUser.setBounds(60, 100, 90, 40);
		lblUser.setFont(new Font("楷体", Font.BOLD, 20));
		lblUser.setForeground(Color.blue);
		this.getLayeredPane().add(lblUser);

		txtUser = new JTextField(20);
		txtUser.setBounds(160, 100, 180, 40);
		txtUser.setFont(new Font("楷体", Font.BOLD, 20));
		// txtUser.setBackground(Color.gray);
		txtUser.setForeground(Color.pink);
		// txtUser.setOpaque(false);
		this.getLayeredPane().add(txtUser);

		lblPassword = new JLabel("密  码：");
		lblPassword.setBounds(60, 145, 100, 40);
		lblPassword.setFont(new Font("楷体", Font.BOLD, 20));
		lblPassword.setForeground(Color.blue);
		this.getLayeredPane().add(lblPassword);

		txtPassword = new JPasswordField(20);
		txtPassword.setBounds(160, 145, 180, 40);
		txtPassword.setFont(new Font("楷体", Font.BOLD, 20));
		txtPassword.setEchoChar('*');
		txtPassword.setForeground(Color.pink);
		this.getLayeredPane().add(txtPassword);

		btnIn = new JButton("登录");
		btnIn.setBounds(60, 220, 80, 40);
		btnIn.setFont(new Font("楷体", Font.BOLD, 20));
		btnIn.setBackground(Color.darkGray);
		btnIn.setForeground(Color.pink);
		this.getLayeredPane().add(btnIn);

		btnClr = new JButton("清除");
		btnClr.setBounds(160, 220, 80, 40);
		btnClr.setFont(new Font("楷体", Font.BOLD, 20));
		btnClr.setBackground(Color.blue);
		btnClr.setForeground(Color.pink);
		this.getLayeredPane().add(btnClr);

		btnReg = new JButton("注册");
		btnReg.setBounds(260, 220, 80, 40);
		btnReg.setFont(new Font("楷体", Font.BOLD, 20));
		btnReg.setBackground(Color.gray);
		btnReg.setForeground(Color.pink);
		this.getLayeredPane().add(btnReg);

		//设置窗体的布局，关闭，可见，延展性
		this.setBounds((width - 400) / 2, (height - 350) / 2, 400, 350);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setIconImage(ttImage);

		//添加鼠标监听器
		MyMouseListener mml = new MyMouseListener();
		//this.addMouseListener(mml);
		btnIn.addMouseListener(mml);
		btnClr.addMouseListener(mml);
		btnReg.addMouseListener(mml);
	}
	
	    /**
    	 * The listener interface for receiving myMouse events.
    	 * The class that is interested in processing a myMouse
    	 * event implements this interface, and the object created
    	 * with that class is registered with a component using the
    	 * component's <code>addMyMouseListener<code> method. When
    	 * the myMouse event occurs, that object's appropriate
    	 * method is invoked.
    	 *用来监听登录窗口鼠标事件
    	 * @see MyMouseEvent
    	 */
	    
	private class MyMouseListener implements MouseListener {
		
		
		    /* (非 Javadoc)
		    * 
		    * 
		    * @param e
		    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		    */
		    
		@Override
		public void mouseClicked(MouseEvent e) {}
		
		
		    /* (非 Javadoc)
		    * 
		    * 
		    * @param e
		    * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
		    */
		    
		@Override
		public void mousePressed(MouseEvent e) {
			// 点击登录按钮，关闭登录窗口，打开游戏窗口
			if (e.getSource() == btnIn) {
				String user = txtUser.getText();
				String password=txtPassword.getText();
				String sqlCon = "select * from user_info where userName= '" + user + "'and passWord= '" + password + "'";
				ResultSet rsUser = uul.queryUserInfo(sqlCon);
				try {
					if(rsUser.next()) {
						LoginFrameClose();
						new MainFrame().initMf(txtUser.getText());
					}
					else {
						JOptionPane.showMessageDialog(null,"账户或密码错误！");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			// 点击清除按钮，清空文本框
			else if (e.getSource() == btnClr) {
				txtUser.setText("");
				txtPassword.setText("");
			}
			// 点击注册按钮，弹出注册窗口
			else if (e.getSource() == btnReg) {
				LoginFrameClose();
				new RegistFrame().initReg();
			}
		}
		
		
		    /* (非 Javadoc)
		    * 
		    * 
		    * @param e
		    * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
		    */
		    
		@Override
		public void mouseReleased(MouseEvent e) {}
		
		
		    /* (非 Javadoc)
		    * 
		    * 
		    * @param e
		    * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
		    */
		    
		@Override
		public void mouseEntered(MouseEvent e) {}
		
		
		    /* (非 Javadoc)
		    * 
		    * 
		    * @param e
		    * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
		    */
		    
		@Override
		public void mouseExited(MouseEvent e) {}
	}

	
	    /**
    	 * Login frame close.
    	 * 在不关闭程序的基础上关闭登录窗口
    	 */
	    
	public void LoginFrameClose() {
		this.dispose();
	}
}
