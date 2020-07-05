package chessboard;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import dao.UserFile;


    // TODO: Auto-generated Javadoc
/**
     * The Class MainFrame.
     *游戏大厅窗口
     * @date 2020年7月4日
     * @author zengcongying
     * @version v1.0
     */
    
public class MainFrame extends JFrame {
	
	/** The uf. */
	public static UserFile uf=new UserFile();//实例化文件操作类
	
	/** The user name. */
	private String userName;//用来接收登录窗口传来的用户名
	
	/** The game name. */
	private JLabel gameName;// 游戏名
	
	/** The btn his. */
	private JButton btnCom, btnNet, btnNot, btnExt, btnHis;//按钮
	
	/** The gh image. */
	// 游戏大厅图片
	Image ghImage = null;// 大厅背景
	
	/** The gt image. */
	ImageIcon gtImage = new ImageIcon("大厅\\游戏名字.png");// 大厅标题
	
	/** The t image. */
	// 窗口左上角的标题
	ImageIcon tImage = new ImageIcon("大厅\\五子棋.png");
	
	/** The tt image. */
	Image ttImage = tImage.getImage();

	/** The width. */
	// 取得屏幕的信息
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	/** The height. */
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	
	    /**
    	 * Inits the mf.
    	 *游戏大厅窗口初始化
    	 * @param name the name
    	 */
	    
	public void initMf(String name) {
		userName = name;
		try {
			ghImage = ImageIO.read(new File("大厅\\背景.png"));
			// gtImage=ImageIO.read(new File("D:\\Java\\五子棋\\五子棋.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getContentPane().add(new JLabel(new ImageIcon(ghImage)));
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().setOpaque(false);

		gameName = new JLabel(gtImage);
		gameName.setBounds(135, 0, 200, 100);
		// gameName.setFont(new Font("楷体",Font.BOLD,40));
		this.getLayeredPane().add(gameName);

		btnCom = new JButton("人机对战");
		btnCom.setBounds(135, 120, 210, 60);
		btnCom.setFont(new Font("楷体", Font.BOLD, 40));
		btnCom.setContentAreaFilled(false);
		btnCom.setFocusPainted(false);
		this.getLayeredPane().add(btnCom);

		btnNet = new JButton("人人对战");
		btnNet.setBounds(135, 200, 210, 60);
		btnNet.setFont(new Font("楷体", Font.BOLD, 40));
		btnNet.setContentAreaFilled(false);
		// btnNet.setBorderPainted(false);
		btnNet.setFocusPainted(false);
		this.getLayeredPane().add(btnNet);

		btnHis = new JButton("历史记录");
		btnHis.setBounds(135, 280, 210, 60);
		btnHis.setFont(new Font("楷体", Font.BOLD, 40));
		btnHis.setContentAreaFilled(false);
		btnHis.setFocusPainted(false);
		this.getLayeredPane().add(btnHis);

		btnNot = new JButton("游戏说明");
		btnNot.setBounds(135, 360, 210, 60);
		btnNot.setFont(new Font("楷体", Font.BOLD, 40));
		btnNot.setContentAreaFilled(false);
		btnNot.setFocusPainted(false);
		this.getLayeredPane().add(btnNot);

		btnExt = new JButton("退出游戏");
		btnExt.setBounds(135, 440, 210, 60);
		btnExt.setFont(new Font("楷体", Font.BOLD, 40));
		btnExt.setContentAreaFilled(false);
		btnExt.setFocusPainted(false);
		this.getLayeredPane().add(btnExt);

		this.setTitle("五子棋");
		this.setSize(500, 600);
		this.setVisible(true);
		this.setLocation((width - 500) / 2, (height - 600) / 2);
		this.setIconImage(ttImage);

		MyMouseListener mml = new MyMouseListener();
		btnCom.addMouseListener(mml);
		btnExt.addMouseListener(mml);
		btnHis.addMouseListener(mml);
		btnNet.addMouseListener(mml);
		btnNot.addMouseListener(mml);
	}
	
	    /**
    	 * The listener interface for receiving myMouse events.
    	 * The class that is interested in processing a myMouse
    	 * event implements this interface, and the object created
    	 * with that class is registered with a component using the
    	 * component's <code>addMyMouseListener<code> method. When
    	 * the myMouse event occurs, that object's appropriate
    	 * method is invoked.
    	 *监听游戏大厅窗口鼠标事件
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
			if (e.getSource() == btnCom) {
				MainFrameClose();
				new AIFrame().initAI(userName);
			} else if (e.getSource() == btnNet) {
				MainFrameClose();
				new NetChessBoardFrame().init(userName);
			} else if (e.getSource() == btnHis) {
				uf.openUser(userName);
			} else if (e.getSource() == btnNot) {
				try {
					Desktop desk = Desktop.getDesktop();
					File file = new File("游戏说明.docx");
					desk.open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == btnExt) {
				int flag = JOptionPane.showConfirmDialog(null, "真的要退出游戏吗？");
				if (flag == 0)
					System.exit(0);
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
    	 * Main frame close.
    	 * 在不关闭程序的基础上关闭游戏大厅窗口
    	 */
	    
	public void MainFrameClose() {
		this.dispose();
	}
}
