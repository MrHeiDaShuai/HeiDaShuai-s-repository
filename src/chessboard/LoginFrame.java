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
     *��¼����
     * @date 2020��7��4��
     * @author zengcongying
     * @version v1.0
     */
public class LoginFrame extends JFrame {
	
	/** The driver name. */
	//���ݿ�������Ϣ
	private static String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	private static String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=zcy01430&useSSL=true&CharacterEncoding=GBK";
	
	/** The uul. */
	private static UserUtil uul = new UserUtil(driverName, uri);
	
	/** The flag. */
	public static int flag=0;
	
	/** The game name. */
	// ��ǩ
	private JLabel lblUser, lblPassword, gameName;
	
	/** The txt user. */
	// �˺��������
	public static JTextField txtUser;
	
	/** The txt password. */
	private JPasswordField txtPassword;
	
	/** The btn clr. */
	// ��ť
	private JButton btnIn, btnReg, btnClr;
	
	/** The lbg image. */
	// ��¼����ͼƬ
	Image lbgImage = null;
	
	/** The t image. */
	ImageIcon tImage=new ImageIcon("��¼\\������.png");
	
	/** The tt image. */
	Image ttImage=tImage.getImage();

	/** The width. */
	// ȡ����Ļ����Ϣ
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
    	 * ��¼���ڳ�ʼ��
    	 */
	    
	public void initLogin() {
		this.setTitle("��¼");
		try {
			lbgImage = ImageIO.read(new File("��¼\\����.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getContentPane().add(new JLabel(new ImageIcon(lbgImage)));
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().setOpaque(false);

		// ������
		gameName = new JLabel("��������Ϸ");
		gameName.setBounds(85, 20, 210, 40);
		gameName.setFont(new Font("����", Font.BOLD, 40));
		this.getLayeredPane().add(gameName);

		lblUser = new JLabel("�û�����");
		lblUser.setBounds(60, 100, 90, 40);
		lblUser.setFont(new Font("����", Font.BOLD, 20));
		lblUser.setForeground(Color.blue);
		this.getLayeredPane().add(lblUser);

		txtUser = new JTextField(20);
		txtUser.setBounds(160, 100, 180, 40);
		txtUser.setFont(new Font("����", Font.BOLD, 20));
		// txtUser.setBackground(Color.gray);
		txtUser.setForeground(Color.pink);
		// txtUser.setOpaque(false);
		this.getLayeredPane().add(txtUser);

		lblPassword = new JLabel("��  �룺");
		lblPassword.setBounds(60, 145, 100, 40);
		lblPassword.setFont(new Font("����", Font.BOLD, 20));
		lblPassword.setForeground(Color.blue);
		this.getLayeredPane().add(lblPassword);

		txtPassword = new JPasswordField(20);
		txtPassword.setBounds(160, 145, 180, 40);
		txtPassword.setFont(new Font("����", Font.BOLD, 20));
		txtPassword.setEchoChar('*');
		txtPassword.setForeground(Color.pink);
		this.getLayeredPane().add(txtPassword);

		btnIn = new JButton("��¼");
		btnIn.setBounds(60, 220, 80, 40);
		btnIn.setFont(new Font("����", Font.BOLD, 20));
		btnIn.setBackground(Color.darkGray);
		btnIn.setForeground(Color.pink);
		this.getLayeredPane().add(btnIn);

		btnClr = new JButton("���");
		btnClr.setBounds(160, 220, 80, 40);
		btnClr.setFont(new Font("����", Font.BOLD, 20));
		btnClr.setBackground(Color.blue);
		btnClr.setForeground(Color.pink);
		this.getLayeredPane().add(btnClr);

		btnReg = new JButton("ע��");
		btnReg.setBounds(260, 220, 80, 40);
		btnReg.setFont(new Font("����", Font.BOLD, 20));
		btnReg.setBackground(Color.gray);
		btnReg.setForeground(Color.pink);
		this.getLayeredPane().add(btnReg);

		//���ô���Ĳ��֣��رգ��ɼ�����չ��
		this.setBounds((width - 400) / 2, (height - 350) / 2, 400, 350);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setIconImage(ttImage);

		//�����������
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
    	 *����������¼��������¼�
    	 * @see MyMouseEvent
    	 */
	    
	private class MyMouseListener implements MouseListener {
		
		
		    /* (�� Javadoc)
		    * 
		    * 
		    * @param e
		    * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		    */
		    
		@Override
		public void mouseClicked(MouseEvent e) {}
		
		
		    /* (�� Javadoc)
		    * 
		    * 
		    * @param e
		    * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
		    */
		    
		@Override
		public void mousePressed(MouseEvent e) {
			// �����¼��ť���رյ�¼���ڣ�����Ϸ����
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
						JOptionPane.showMessageDialog(null,"�˻����������");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			// ��������ť������ı���
			else if (e.getSource() == btnClr) {
				txtUser.setText("");
				txtPassword.setText("");
			}
			// ���ע�ᰴť������ע�ᴰ��
			else if (e.getSource() == btnReg) {
				LoginFrameClose();
				new RegistFrame().initReg();
			}
		}
		
		
		    /* (�� Javadoc)
		    * 
		    * 
		    * @param e
		    * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
		    */
		    
		@Override
		public void mouseReleased(MouseEvent e) {}
		
		
		    /* (�� Javadoc)
		    * 
		    * 
		    * @param e
		    * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
		    */
		    
		@Override
		public void mouseEntered(MouseEvent e) {}
		
		
		    /* (�� Javadoc)
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
    	 * �ڲ��رճ���Ļ����Ϲرյ�¼����
    	 */
	    
	public void LoginFrameClose() {
		this.dispose();
	}
}
