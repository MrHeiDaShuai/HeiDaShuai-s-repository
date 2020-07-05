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
     *人人联机窗口
     * @date 2020年7月4日
     * @author zengcongying
     * @version v1.0
     */
    
public class NetChessBoardFrame extends JFrame {
	
	/** The user name. */
	private static String userName;//用来接收用户名
	
	/** The tclient. */
	Thread tclient;//客户端线程
	
	/** The tserver. */
	Thread tserver;//服务器端线程

	/** The is clicked. */
	public static boolean isClicked = false;
	
	/** The is client. */
	public static boolean isClient = false;
	
	/** The is server. */
	public static boolean isServer = false;
	
	/** The is turn. */
	public static boolean isTurn = true;
	
	/** The start. */
	public static boolean start = false;//标记游戏是否开始
	
	/** The all chess. */
	public static int allChess[][] = new int[16][16];//棋盘
	
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
	public static String ServerAddress;// 服务器端IP地址
	
	/** The Client address. */
	public static String ClientAddress;// 客户端IP地址
	
	/** The Server port. */
	public static String ServerPort;// 服务端端口号
	
	/** The Client port. */
	public static String ClientPort;// 客户端端口号
	
	/** The thread. */
	public static Thread thread;// 声明一个线程

	/** The t image. */
	// 窗口左上角的标题
	ImageIcon tImage = new ImageIcon("人人\\五子棋.png");
	
	/** The tt image. */
	Image ttImage = tImage.getImage();

	/** The ey. */
	public static int ex = 16, ey = 16;

	/** The width. */
	// 取得屏幕的信息
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	/** The height. */
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	
	    /**
    	 * Instantiates a new net chess board frame.
    	 */
	    
	public NetChessBoardFrame() {
		this.setTitle("五子棋");// 设置标题
		this.setSize(750, 635);// 设置窗体大小
		this.setLocation((width - 750) / 2, (height - 650) / 2);// 窗体居中显示
		this.setResizable(false); // 窗体大小不可变
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 窗体关闭方式
		this.setCursor(Cursor.HAND_CURSOR);// 手状光标类型
		// this.setVisible(true);// 窗体可见
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
    	 *人人对战左部棋盘面板
    	 * @date 2020年7月4日
    	 * @author zengcongying
    	 * @version v1.0
    	 */
	    
	private class ChessBoard extends JPanel {
		
		/** The width. */
		// 取得屏幕的信息
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		
		/** The height. */
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		/** The bg image. */
		// 背景图片
		Image bgImage = null;
		
		/** The w image. */
		// 棋子图片
		Image wImage = null;
		
		/** The b image. */
		Image bImage = null;
		
		/** The t image. */
		ImageIcon tImage = new ImageIcon("人人\\五子棋.png");
		
		/** The tt image. */
		Image ttImage = tImage.getImage();

		
		    /**
    		 * Instantiates a new chess board.
    		 */
		    
		public ChessBoard() {
			super();
			try {
				bgImage = ImageIO.read(new File("人人\\棋盘背景.png"));
				wImage = ImageIO.read(new File("人人\\白棋子.png"));
				bImage = ImageIO.read(new File("人人\\黑棋子.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.repaint();
		}

		
		    /* (非 Javadoc)
		    * 
		    * 
		    * @param g
		    * @see javax.swing.JComponent#paint(java.awt.Graphics)
		    */
		    
		public void paint(Graphics g) {
			// 双缓冲技术，解决屏幕闪烁
			BufferedImage bi = new BufferedImage(750, 635, BufferedImage.TYPE_INT_ARGB);
			Graphics g2 = bi.createGraphics();

			g2.drawImage(bgImage, 0, 0, null);
			g2.setColor(Color.black);
			// 绘制棋盘
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
			// 画棋子
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
    	 *人人对战右部面板
    	 * @date 2020年7月4日
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

			btnCrt = new JButton("创建");
			btnCrt.setBounds(0, 300, 130, 40);
			btnCrt.setFont(new Font("楷体", Font.BOLD, 30));
			btnCrt.setBackground(Color.gray);
			add(btnCrt);

			btnJin = new JButton("加入");
			btnJin.setBounds(0, 360, 130, 40);
			btnJin.setFont(new Font("楷体", Font.BOLD, 30));
			btnJin.setBackground(Color.gray);
			add(btnJin);

			btnSrd = new JButton("认输");
			btnSrd.setBounds(0, 420, 130, 40);
			btnSrd.setFont(new Font("楷体", Font.BOLD, 30));
			btnSrd.setBackground(Color.gray);
			add(btnSrd);

			btnExt = new JButton("退出");
			btnExt.setBounds(0, 480, 130, 40);
			btnExt.setFont(new Font("楷体", Font.BOLD, 30));
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
		 * @date 2020年7月4日
		 * @author zengcongying
		 * @version v1.0
		 */
		private class ButtonAct implements ActionListener {
			
			
			    /* (非 Javadoc)
			    * 
			    * 
			    * @param e
			    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			    */
			    
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("创建")) {
					ServerPort = JOptionPane.showInputDialog("请设置端口号（0~65536）：");
					if (ServerPort == null)
						return;
					else if (ServerPort.equalsIgnoreCase("") || Integer.parseInt(ServerPort) < 0
							|| Integer.parseInt(ServerPort) > 65536) {
						JOptionPane.showMessageDialog(null, "请输入正确的端口号！");
						return;
					}
					gserver = new GameServer();
					tserver = new Thread(gserver);
					tserver.start();
					isServer = true;
					start = true;
				} else if (e.getActionCommand().equals("加入")) {
					ClientAddress = JOptionPane.showInputDialog("请输入对方地址：");
					if (ClientAddress == null)
						return;
					else if (ClientAddress.equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null, "请输入正确的地址！");
						return;
					}
					ClientPort = JOptionPane.showInputDialog("请输入端口号：");
					if (ClientPort == null)
						return;
					else if(ClientPort.equalsIgnoreCase("") || Integer.parseInt(ClientPort) < 0
							|| Integer.parseInt(ClientPort) > 65536) {
						JOptionPane.showMessageDialog(null, "请输入正确的端口号！");
						return;
					}
					gclient = new GameClient();
					tclient = new Thread(gclient);
					tclient.start();
					start = true;
					isClient = true;
				} else if (e.getActionCommand().equals("认输")) {
					if(start) {
					JOptionPane.showConfirmDialog(null, "就这么选择认输吗，不再努力一下啦？");
					clearBoard();}
				} else if (e.getActionCommand().equals("退出")) {
					int choose = JOptionPane.showConfirmDialog(null, "确认要退出吗？");
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
    	 *监听人人对战窗口
    	 * @see MyMouseEvent
    	 */
	    
	private class MyMouseListener extends MouseAdapter {
		
		
		    /* (非 Javadoc)
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
				// 把x,y转换成棋盘上的坐标
				x = (((x - 45 + (550 - 45) / 28) / ((550 - 45) / 14) * (550 - 45) / 14 + 46) - 45) * 14 / (550 - 45);
				y = (((y - 50 + (555 - 50) / 28) / ((555 - 50) / 14) * (555 - 50) / 14 + 51) - 50) * 14 / (555 - 50);
			}
			if (allChess[x][y] != 0) {
				JOptionPane.showMessageDialog(null, "此处已有棋子！");
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
    	 *窗口初始化
    	 * @param name the name
    	 */
	    
	public void init(String name) {
		userName=name;
		cb.setPreferredSize(new Dimension(600, 635));
		cb.setBounds(0, 0, 600, 635);
		this.getContentPane().add(cb);
		cb.repaint();
		cb.setVisible(true);
		this.setVisible(true);// 窗体可见
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.ex = x;
	}

	
	    /* (非 Javadoc)
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

	
	    /* (非 Javadoc)
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
    	 * 游戏重置
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
    	 *记录前一步的棋的坐标
    	 * @param chessX the chess X
    	 * @param chessY the chess Y
    	 */
	    
	public void record(int chessX, int chessY) {
		lastChess[0][0] = chessX;
		lastChess[0][1] = chessY;
	}

	
	    /**
    	 * Close.
    	 * 在不关闭程序的基础上关闭此窗口
    	 */
	    
	public void close() {
		this.dispose();
	}
}
