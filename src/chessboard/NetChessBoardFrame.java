package chessboard;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import chessplay.*;


    // TODO: Auto-generated Javadoc
/**
     * The Class NetChessBoardFrame.
     *������������
     * @date 2020��7��4��
     * @author zengcongying
     * @version v1.0
     */
    
public class NetChessBoardFrame extends JFrame {
	
	/** The user name. */
	private static String userName;//���������û���
	
	/** The tclient. */
	Thread tclient;//�ͻ����߳�
	
	/** The tserver. */
	Thread tserver;//���������߳�

	/** The is clicked. */
	public static boolean isClicked = false;
	
	/** The is client. */
	public static boolean isClient = false;
	
	/** The is server. */
	public static boolean isServer = false;
	
	/** The is turn. */
	public static boolean isTurn = true;
	
	/** The start. */
	public static boolean start = false;//�����Ϸ�Ƿ�ʼ
	
	/** The all chess. */
	public static int allChess[][] = new int[16][16];//����
	
	/** The last chess. */
	public static int lastChess[][] = { { -1, -1 }, { -1, -1 } };
	
	/** The cb. */
	ChessBoard cb;
	
	/** The control. */
	BoardControl control;
	
	/** The gclient. */
	GameClient gclient;
	
	/** The gserver. */
	GameServer gserver;

	/** The Server address. */
	public static String ServerAddress;// ��������IP��ַ
	
	/** The Client address. */
	public static String ClientAddress;// �ͻ���IP��ַ
	
	/** The Server port. */
	public static String ServerPort;// ����˶˿ں�
	
	/** The Client port. */
	public static String ClientPort;// �ͻ��˶˿ں�
	
	/** The thread. */
	public static Thread thread;// ����һ���߳�

	/** The t image. */
	// �������Ͻǵı���
	ImageIcon tImage = new ImageIcon("����\\������.png");
	
	/** The tt image. */
	Image ttImage = tImage.getImage();

	/** The ey. */
	public static int ex = 16, ey = 16;

	/** The width. */
	// ȡ����Ļ����Ϣ
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	/** The height. */
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	
	    /**
    	 * Instantiates a new net chess board frame.
    	 */
	    
	public NetChessBoardFrame() {
		this.setTitle("������");// ���ñ���
		this.setSize(750, 635);// ���ô����С
		this.setLocation((width - 750) / 2, (height - 650) / 2);// ���������ʾ
		this.setResizable(false); // �����С���ɱ�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ����رշ�ʽ
		this.setCursor(Cursor.HAND_CURSOR);// ��״�������
		// this.setVisible(true);// ����ɼ�
		this.getLayeredPane().setLayout(null);
		this.setIconImage(ttImage);

		MyMouseListener mml = new MyMouseListener();
		cb = new ChessBoard();
		control = new BoardControl();
		control.setPreferredSize(new Dimension(135, 635));
		this.getContentPane().add(control, BorderLayout.EAST);
		control.setVisible(true);
		cb.addMouseListener(mml);
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		//new NetChessBoardFrame().init();
	}

	
	    /**
    	 * The Class ChessBoard.
    	 *���˶�ս���������
    	 * @date 2020��7��4��
    	 * @author zengcongying
    	 * @version v1.0
    	 */
	    
	private class ChessBoard extends JPanel {
		
		/** The width. */
		// ȡ����Ļ����Ϣ
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		
		/** The height. */
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		/** The bg image. */
		// ����ͼƬ
		Image bgImage = null;
		
		/** The w image. */
		// ����ͼƬ
		Image wImage = null;
		
		/** The b image. */
		Image bImage = null;
		
		/** The t image. */
		ImageIcon tImage = new ImageIcon("����\\������.png");
		
		/** The tt image. */
		Image ttImage = tImage.getImage();

		
		    /**
    		 * Instantiates a new chess board.
    		 */
		    
		public ChessBoard() {
			super();
			try {
				bgImage = ImageIO.read(new File("����\\���̱���.png"));
				wImage = ImageIO.read(new File("����\\������.png"));
				bImage = ImageIO.read(new File("����\\������.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.repaint();
		}

		
		    /* (�� Javadoc)
		    * 
		    * 
		    * @param g
		    * @see javax.swing.JComponent#paint(java.awt.Graphics)
		    */
		    
		public void paint(Graphics g) {
			// ˫���弼���������Ļ��˸
			BufferedImage bi = new BufferedImage(750, 635, BufferedImage.TYPE_INT_ARGB);
			Graphics g2 = bi.createGraphics();

			g2.drawImage(bgImage, 0, 0, null);
			g2.setColor(Color.black);
			// ��������
			for (int i = 0; i < 15; i++) {
				g2.drawLine(45, 50 + i * (555 - 50) / 14, 550, 50 + i * (555 - 50) / 14);
				g2.drawLine(45 + i * (550 - 45) / 14, 50, 45 + i * (550 - 45) / 14, 555);
			}
			g2.fillOval(150, 155, 6, 6);
			g2.fillOval(150, 299, 6, 6);
			g2.fillOval(150, 444, 6, 6);
			g2.fillOval(294, 155, 6, 6);
			g2.fillOval(294, 299, 6, 6);
			g2.fillOval(294, 444, 6, 6);
			g2.fillOval(438, 155, 6, 6);
			g2.fillOval(438, 299, 6, 6);
			g2.fillOval(438, 444, 6, 6);
			// ������
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (allChess[i][j] == 1) {
						int tempX = i * (550 - 45) / 14 + 45;
						int tempY = j * (555 - 50) / 14 + 50;
						g2.drawImage(bImage, tempX - 15, tempY - 15, this);
					} else if (allChess[i][j] == 2) {
						int tempX = i * (550 - 45) / 14 + 45;
						int tempY = j * (555 - 50) / 14 + 50;
						g2.drawImage(wImage, tempX - 15, tempY - 15, this);
					}

				}
			}
			g.drawImage(bi, 0, 0, this);
			repaint();
		}

	}

	
	    /**
    	 * The Class BoardControl.
    	 *���˶�ս�Ҳ����
    	 * @date 2020��7��4��
    	 * @author zengcongying
    	 * @version v1.0
    	 */
	    
	private class BoardControl extends JPanel {
		
		/** The btn srd. */
		private JButton btnCrt, btnJin, btnSrd;
		
		/** The btn ext. */
		public JButton btnExt;

		
		    /**
    		 * Instantiates a new board control.
    		 */
		    
		public BoardControl() {
			super();
			this.setLayout(null);

			btnCrt = new JButton("����");
			btnCrt.setBounds(0, 300, 130, 40);
			btnCrt.setFont(new Font("����", Font.BOLD, 30));
			btnCrt.setBackground(Color.gray);
			add(btnCrt);

			btnJin = new JButton("����");
			btnJin.setBounds(0, 360, 130, 40);
			btnJin.setFont(new Font("����", Font.BOLD, 30));
			btnJin.setBackground(Color.gray);
			add(btnJin);

			btnSrd = new JButton("����");
			btnSrd.setBounds(0, 420, 130, 40);
			btnSrd.setFont(new Font("����", Font.BOLD, 30));
			btnSrd.setBackground(Color.gray);
			add(btnSrd);

			btnExt = new JButton("�˳�");
			btnExt.setBounds(0, 480, 130, 40);
			btnExt.setFont(new Font("����", Font.BOLD, 30));
			btnExt.setBackground(Color.gray);
			add(btnExt);

			ButtonAct cbut = new ButtonAct();
			btnCrt.addActionListener(cbut);
			btnJin.addActionListener(cbut);
			btnSrd.addActionListener(cbut);
			btnExt.addActionListener(cbut);
		}

		/**
		 * The Class ButtonAct.
		 *
		 * @date 2020��7��4��
		 * @author zengcongying
		 * @version v1.0
		 */
		private class ButtonAct implements ActionListener {
			
			
			    /* (�� Javadoc)
			    * 
			    * 
			    * @param e
			    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			    */
			    
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("����")) {
					ServerPort = JOptionPane.showInputDialog("�����ö˿ںţ�0~65536����");
					if (ServerPort == null)
						return;
					else if (ServerPort.equalsIgnoreCase("") || Integer.parseInt(ServerPort) < 0
							|| Integer.parseInt(ServerPort) > 65536) {
						JOptionPane.showMessageDialog(null, "��������ȷ�Ķ˿ںţ�");
						return;
					}
					gserver = new GameServer();
					tserver = new Thread(gserver);
					tserver.start();
					isServer = true;
					start = true;
				} else if (e.getActionCommand().equals("����")) {
					ClientAddress = JOptionPane.showInputDialog("������Է���ַ��");
					if (ClientAddress == null)
						return;
					else if (ClientAddress.equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null, "��������ȷ�ĵ�ַ��");
						return;
					}
					ClientPort = JOptionPane.showInputDialog("������˿ںţ�");
					if (ClientPort == null)
						return;
					else if(ClientPort.equalsIgnoreCase("") || Integer.parseInt(ClientPort) < 0
							|| Integer.parseInt(ClientPort) > 65536) {
						JOptionPane.showMessageDialog(null, "��������ȷ�Ķ˿ںţ�");
						return;
					}
					gclient = new GameClient();
					tclient = new Thread(gclient);
					tclient.start();
					start = true;
					isClient = true;
				} else if (e.getActionCommand().equals("����")) {
					if(start) {
					JOptionPane.showConfirmDialog(null, "����ôѡ�������𣬲���Ŭ��һ������");
					clearBoard();}
				} else if (e.getActionCommand().equals("�˳�")) {
					int choose = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�˳���");
					if (choose == 0) {
						close();
						new MainFrame().initMf(userName);
					} else
						return;
				}
			}
		}
	}

	
	    /**
    	 * The listener interface for receiving myMouse events.
    	 * The class that is interested in processing a myMouse
    	 * event implements this interface, and the object created
    	 * with that class is registered with a component using the
    	 * component's <code>addMyMouseListener<code> method. When
    	 * the myMouse event occurs, that object's appropriate
    	 * method is invoked.
    	 *�������˶�ս����
    	 * @see MyMouseEvent
    	 */
	    
	private class MyMouseListener extends MouseAdapter {
		
		
		    /* (�� Javadoc)
		    * 
		    * 
		    * @param e
		    * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
		    */
		    
		public void mouseReleased(MouseEvent e) {
			System.out.println(e.getX() + "==" + e.getY());
			if (start == false) {
				return;
			}
			isClicked = true;
			int x = e.getX();
			int y = e.getY();

			if (x >= 45 && x <= 550 && y >= 50 && y <= 555) {
				// ��x,yת���������ϵ�����
				x = (((x - 45 + (550 - 45) / 28) / ((550 - 45) / 14) * (550 - 45) / 14 + 46) - 45) * 14 / (550 - 45);
				y = (((y - 50 + (555 - 50) / 28) / ((555 - 50) / 14) * (555 - 50) / 14 + 51) - 50) * 14 / (555 - 50);
			}
			if (allChess[x][y] != 0) {
				JOptionPane.showMessageDialog(null, "�˴��������ӣ�");
				return;
			}
			setX(x);
			setY(y);
			if (x <= 15 && y <= 15)
				record(x, y);
		}
	}

	
	    /**
    	 * Inits the.
    	 *���ڳ�ʼ��
    	 * @param name the name
    	 */
	    
	public void init(String name) {
		userName=name;
		cb.setPreferredSize(new Dimension(600, 635));
		cb.setBounds(0, 0, 600, 635);
		this.getContentPane().add(cb);
		cb.repaint();
		cb.setVisible(true);
		this.setVisible(true);// ����ɼ�
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.ex = x;
	}

	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.awt.Component#getX()
	    */
	    
	public int getX() {
		return ex;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.ey = y;
	}

	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.awt.Component#getY()
	    */
	    
	public int getY() {
		return ey;
	}

	
	    /**
    	 * Clear board.
    	 * ��Ϸ����
    	 */
	    
	public void clearBoard() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				allChess[i][j] = 0;
			}
		}
		start=false;
		ex=16;
		ey=16;
		lastChess[0][0] = -1;
		lastChess[0][1] = -1;
	}

	
	    /**
    	 * Record.
    	 *��¼ǰһ�����������
    	 * @param chessX the chess X
    	 * @param chessY the chess Y
    	 */
	    
	public void record(int chessX, int chessY) {
		lastChess[0][0] = chessX;
		lastChess[0][1] = chessY;
	}

	
	    /**
    	 * Close.
    	 * �ڲ��رճ���Ļ����Ϲرմ˴���
    	 */
	    
	public void close() {
		this.dispose();
	}
}
