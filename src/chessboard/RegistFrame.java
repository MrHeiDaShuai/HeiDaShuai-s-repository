package chessboard;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import dao.*;


    // TODO: Auto-generated Javadoc
/**
     * The Class RegistFrame.
     *注册窗口
     * @date 2020年7月4日
     * @author zengcongying
     * @version v1.0
     */
    
public class RegistFrame extends JFrame {
	
	/** The driver name. */
	//数据库连接信息
	private static String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	private static String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=zcy01430&useSSL=true&CharacterEncoding=GBK";
	
	/** The uul. */
	private static UserUtil uul = new UserUtil(driverName, uri);
	
	/** The uf. */
	private static UserFile uf=new UserFile();//文件操作类实例化
	
	/** The game reg. */
	// 标签
	private JLabel lblUser, lblPassword1, lblPassword2, gameReg;
	
	/** The txt user. */
	// 账号与密码框
	private JTextField txtUser;
	
	/** The txt password 2. */
	private JPasswordField txtPassword1, txtPassword2;
	
	/** The btn ret. */
	// 按钮
	JButton btnOk, btnClr, btnRet;
	
	/** The rbg image. */
	// 注册窗口图片
	Image rbgImage = null;//背景
	
	/** The t image. */
	//窗口左上角的标题
	ImageIcon tImage=new ImageIcon("注册\\五子棋.png");
	
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
		//new RegistFrame().initReg();
	}

	
	    /**
    	 * Inits the reg.
    	 * 初始化注册窗口
    	 */
	    
	public void initReg() {
		this.setTitle("注册窗口");
		try {
			rbgImage = ImageIO.read(new File("注册\\背景.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getContentPane().add(new JLabel(new ImageIcon(rbgImage)));
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().setOpaque(false);

		// 添加组件
		gameReg = new JLabel("游戏注册");
		gameReg.setBounds(110, 20, 180, 40);
		gameReg.setFont(new Font("楷体", Font.BOLD, 40));
		this.getLayeredPane().add(gameReg);

		lblUser = new JLabel("用 户 名：");
		lblUser.setBounds(50, 80, 120, 40);
		lblUser.setFont(new Font("楷体", Font.BOLD, 20));
		lblUser.setForeground(Color.blue);
		this.getLayeredPane().add(lblUser);

		txtUser = new JTextField(20);
		txtUser.setBounds(180, 80, 150, 40);
		txtUser.setFont(new Font("楷体", Font.BOLD, 20));
		txtUser.setForeground(Color.pink);
		// txtUser.setOpaque(false);
		this.getLayeredPane().add(txtUser);

		lblPassword1 = new JLabel("密    码：");
		lblPassword1.setBounds(50, 120, 120, 40);
		lblPassword1.setFont(new Font("楷体", Font.BOLD, 20));
		lblPassword1.setForeground(Color.blue);
		this.getLayeredPane().add(lblPassword1);

		txtPassword1 = new JPasswordField(20);
		txtPassword1.setBounds(180, 120, 150, 40);
		txtPassword1.setFont(new Font("楷体", Font.BOLD, 20));
		txtPassword1.setEchoChar('*');
		txtPassword1.setForeground(Color.pink);
		this.getLayeredPane().add(txtPassword1);

		lblPassword2 = new JLabel("确认密码：");
		lblPassword2.setBounds(50, 160, 120, 40);
		lblPassword2.setFont(new Font("楷体", Font.BOLD, 20));
		lblPassword2.setForeground(Color.blue);
		this.getLayeredPane().add(lblPassword2);

		txtPassword2 = new JPasswordField(20);
		txtPassword2.setBounds(180, 160, 150, 40);
		txtPassword2.setFont(new Font("楷体", Font.BOLD, 20));
		txtPassword2.setEchoChar('*');
		txtPassword2.setForeground(Color.pink);
		this.getLayeredPane().add(txtPassword2);

		btnOk = new JButton("确认");
		btnOk.setBounds(60, 220, 80, 40);
		btnOk.setFont(new Font("楷体", Font.BOLD, 20));
		btnOk.setBackground(Color.darkGray);
		btnOk.setForeground(Color.pink);
		this.getLayeredPane().add(btnOk);

		btnClr = new JButton("清除");
		btnClr.setBounds(160, 220, 80, 40);
		btnClr.setFont(new Font("楷体", Font.BOLD, 20));
		btnClr.setBackground(Color.blue);
		btnClr.setForeground(Color.pink);
		this.getLayeredPane().add(btnClr);

		btnRet = new JButton("返回");
		btnRet.setBounds(260, 220, 80, 40);
		btnRet.setFont(new Font("楷体", Font.BOLD, 20));
		btnRet.setBackground(Color.gray);
		btnRet.setForeground(Color.pink);
		this.getLayeredPane().add(btnRet);

		MyMouseListener mml = new MyMouseListener();
		this.addMouseListener(mml);
		btnOk.addMouseListener(mml);
		btnClr.addMouseListener(mml);
		btnRet.addMouseListener(mml);

		this.setBounds((width - 400) / 2, (height - 350) / 2, 400, 350);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setIconImage(ttImage);
	}
	
	    /**
    	 * The listener interface for receiving myMouse events.
    	 * The class that is interested in processing a myMouse
    	 * event implements this interface, and the object created
    	 * with that class is registered with a component using the
    	 * component's <code>addMyMouseListener<code> method. When
    	 * the myMouse event occurs, that object's appropriate
    	 * method is invoked.
    	 *监听注册窗口鼠标事件
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
			if (e.getSource() == btnOk) {//点击确认按钮，判断是否注册成功
				String user = txtUser.getText();
				String password=txtPassword1.getText();
				String sql = "select * from user_info where userName= '" + user + "'";
				ResultSet rs = uul.queryUserInfo(sql);
				try {
					if(user.equals("")||password.equals("")) {
						JOptionPane.showMessageDialog(null, "用户名或密码不能为空！");
					}
					else if (rs.next()) {
						JOptionPane.showMessageDialog(null, "用户名已存在，请重新输入");
						txtUser.setText("");
						txtPassword1.setText("");
						txtPassword2.setText("");
					} else if (txtPassword1.getText().equals(txtPassword2.getText()) == true) {
						uul.saveUserInfo(user, password);
						uf.createUser(txtUser.getText());
						JOptionPane.showMessageDialog(null, "恭喜您注册成功！");
						RegistFrameClose();
						new LoginFrame().initLogin();
					} else if (txtPassword1.getText().equals(txtPassword2.getText()) == false) {
						JOptionPane.showMessageDialog(null, "对不起，密码不一致！");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == btnClr) {//点击清除按钮，清除文本框
				txtUser.setText("");
				txtPassword1.setText("");
				txtPassword2.setText("");
			} else if (e.getSource() == btnRet) {//点击返回按钮，关闭注册窗口，返回登录窗口
				RegistFrameClose();
				new LoginFrame().initLogin();
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
    	 * Regist frame close.
    	 * 在不关闭程序的基础上关闭注册窗口
    	 */
	    
	public void RegistFrameClose() {
		this.dispose();
	}
}
