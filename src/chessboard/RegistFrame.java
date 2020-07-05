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
     *ע�ᴰ��
     * @date 2020��7��4��
     * @author zengcongying
     * @version v1.0
     */
    
public class RegistFrame extends JFrame {
	
	/** The driver name. */
	//���ݿ�������Ϣ
	private static String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	private static String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=zcy01430&useSSL=true&CharacterEncoding=GBK";
	
	/** The uul. */
	private static UserUtil uul = new UserUtil(driverName, uri);
	
	/** The uf. */
	private static UserFile uf=new UserFile();//�ļ�������ʵ����
	
	/** The game reg. */
	// ��ǩ
	private JLabel lblUser, lblPassword1, lblPassword2, gameReg;
	
	/** The txt user. */
	// �˺��������
	private JTextField txtUser;
	
	/** The txt password 2. */
	private JPasswordField txtPassword1, txtPassword2;
	
	/** The btn ret. */
	// ��ť
	JButton btnOk, btnClr, btnRet;
	
	/** The rbg image. */
	// ע�ᴰ��ͼƬ
	Image rbgImage = null;//����
	
	/** The t image. */
	//�������Ͻǵı���
	ImageIcon tImage=new ImageIcon("ע��\\������.png");
	
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
		//new RegistFrame().initReg();
	}

	
	    /**
    	 * Inits the reg.
    	 * ��ʼ��ע�ᴰ��
    	 */
	    
	public void initReg() {
		this.setTitle("ע�ᴰ��");
		try {
			rbgImage = ImageIO.read(new File("ע��\\����.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getContentPane().add(new JLabel(new ImageIcon(rbgImage)));
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().setOpaque(false);

		// ������
		gameReg = new JLabel("��Ϸע��");
		gameReg.setBounds(110, 20, 180, 40);
		gameReg.setFont(new Font("����", Font.BOLD, 40));
		this.getLayeredPane().add(gameReg);

		lblUser = new JLabel("�� �� ����");
		lblUser.setBounds(50, 80, 120, 40);
		lblUser.setFont(new Font("����", Font.BOLD, 20));
		lblUser.setForeground(Color.blue);
		this.getLayeredPane().add(lblUser);

		txtUser = new JTextField(20);
		txtUser.setBounds(180, 80, 150, 40);
		txtUser.setFont(new Font("����", Font.BOLD, 20));
		txtUser.setForeground(Color.pink);
		// txtUser.setOpaque(false);
		this.getLayeredPane().add(txtUser);

		lblPassword1 = new JLabel("��    �룺");
		lblPassword1.setBounds(50, 120, 120, 40);
		lblPassword1.setFont(new Font("����", Font.BOLD, 20));
		lblPassword1.setForeground(Color.blue);
		this.getLayeredPane().add(lblPassword1);

		txtPassword1 = new JPasswordField(20);
		txtPassword1.setBounds(180, 120, 150, 40);
		txtPassword1.setFont(new Font("����", Font.BOLD, 20));
		txtPassword1.setEchoChar('*');
		txtPassword1.setForeground(Color.pink);
		this.getLayeredPane().add(txtPassword1);

		lblPassword2 = new JLabel("ȷ�����룺");
		lblPassword2.setBounds(50, 160, 120, 40);
		lblPassword2.setFont(new Font("����", Font.BOLD, 20));
		lblPassword2.setForeground(Color.blue);
		this.getLayeredPane().add(lblPassword2);

		txtPassword2 = new JPasswordField(20);
		txtPassword2.setBounds(180, 160, 150, 40);
		txtPassword2.setFont(new Font("����", Font.BOLD, 20));
		txtPassword2.setEchoChar('*');
		txtPassword2.setForeground(Color.pink);
		this.getLayeredPane().add(txtPassword2);

		btnOk = new JButton("ȷ��");
		btnOk.setBounds(60, 220, 80, 40);
		btnOk.setFont(new Font("����", Font.BOLD, 20));
		btnOk.setBackground(Color.darkGray);
		btnOk.setForeground(Color.pink);
		this.getLayeredPane().add(btnOk);

		btnClr = new JButton("���");
		btnClr.setBounds(160, 220, 80, 40);
		btnClr.setFont(new Font("����", Font.BOLD, 20));
		btnClr.setBackground(Color.blue);
		btnClr.setForeground(Color.pink);
		this.getLayeredPane().add(btnClr);

		btnRet = new JButton("����");
		btnRet.setBounds(260, 220, 80, 40);
		btnRet.setFont(new Font("����", Font.BOLD, 20));
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
    	 *����ע�ᴰ������¼�
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
			if (e.getSource() == btnOk) {//���ȷ�ϰ�ť���ж��Ƿ�ע��ɹ�
				String user = txtUser.getText();
				String password=txtPassword1.getText();
				String sql = "select * from user_info where userName= '" + user + "'";
				ResultSet rs = uul.queryUserInfo(sql);
				try {
					if(user.equals("")||password.equals("")) {
						JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ�գ�");
					}
					else if (rs.next()) {
						JOptionPane.showMessageDialog(null, "�û����Ѵ��ڣ�����������");
						txtUser.setText("");
						txtPassword1.setText("");
						txtPassword2.setText("");
					} else if (txtPassword1.getText().equals(txtPassword2.getText()) == true) {
						uul.saveUserInfo(user, password);
						uf.createUser(txtUser.getText());
						JOptionPane.showMessageDialog(null, "��ϲ��ע��ɹ���");
						RegistFrameClose();
						new LoginFrame().initLogin();
					} else if (txtPassword1.getText().equals(txtPassword2.getText()) == false) {
						JOptionPane.showMessageDialog(null, "�Բ������벻һ�£�");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == btnClr) {//��������ť������ı���
				txtUser.setText("");
				txtPassword1.setText("");
				txtPassword2.setText("");
			} else if (e.getSource() == btnRet) {//������ذ�ť���ر�ע�ᴰ�ڣ����ص�¼����
				RegistFrameClose();
				new LoginFrame().initLogin();
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
    	 * Regist frame close.
    	 * �ڲ��رճ���Ļ����Ϲر�ע�ᴰ��
    	 */
	    
	public void RegistFrameClose() {
		this.dispose();
	}
}
