package chessboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import chessplay.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;


    // TODO: Auto-generated Javadoc
/**
     * The Class AIFrame.
     *人机窗口
     * @date 2020年7月4日
     * @author zengcongying
     * @version v1.0
     */
    
public class AIFrame extends JFrame {
	
	/** The user name. */
	private String userName;//用来接收用户名
	
	/** The cp. */
	ChessPlay cp;
	
	/** The game name. */
	private JLabel gameName;// 游戏名
	
	/** The btn ext. */
	private JButton btnEai, btnCai, btnDai, btnExt;
	
	
	/** The gh image. */
	// 人机大厅图片
	Image ghImage = null;
	
	/** The gt image. */
	ImageIcon gtImage = new ImageIcon("人机\\游戏名字.png");
	
	/** The t image. */
	ImageIcon tImage = new ImageIcon("人机\\五子棋.png");
	
	/** The tt image. */
	Image ttImage = tImage.getImage();
	
	/** The width. */
	// 取得屏幕的信息
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	/** The height. */
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	    /**
    	 * Inits the AI.
    	 *初始化人机窗口
    	 * @param name the name
    	 */
	    
	public void initAI(String name) {
		userName=name;
		try {
			ghImage = ImageIO.read(new File("人机\\背景.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getContentPane().add(new JLabel(new ImageIcon(ghImage)));
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().setOpaque(false);
		
		gameName = new JLabel(gtImage);
		gameName.setBounds(135, 0, 200, 100);
		this.getLayeredPane().add(gameName);
		
		btnEai = new JButton("简单人机");
		btnEai.setBounds(135, 120, 210, 60);
		btnEai.setFont(new Font("楷体", Font.BOLD, 40));
		btnEai.setContentAreaFilled(false);
		btnEai.setFocusPainted(false);
		this.getLayeredPane().add(btnEai);

		btnCai = new JButton("一般人机");
		btnCai.setBounds(135, 200, 210, 60);
		btnCai.setFont(new Font("楷体", Font.BOLD, 40));
		btnCai.setContentAreaFilled(false);
		btnCai.setFocusPainted(false);
		this.getLayeredPane().add(btnCai);

		btnDai = new JButton("困难人机");
		btnDai.setBounds(135, 280, 210, 60);
		btnDai.setFont(new Font("楷体", Font.BOLD, 40));
		btnDai.setContentAreaFilled(false);
		btnDai.setFocusPainted(false);
		this.getLayeredPane().add(btnDai);

		btnExt = new JButton("返回大厅");
		btnExt.setBounds(135, 360, 210, 60);
		btnExt.setFont(new Font("楷体", Font.BOLD, 40));
		btnExt.setContentAreaFilled(false);
		btnExt.setFocusPainted(false);
		this.getLayeredPane().add(btnExt);
		
		this.setTitle("五子棋");
		this.setSize(500, 600);
		this.setVisible(true);
		this.setLocation((width - 500) / 2, (height - 600) / 2);
		this.setIconImage(ttImage);
		
		MyMouseListener mml=new MyMouseListener();
		
		btnEai.addMouseListener(mml);
		btnCai.addMouseListener(mml);
		btnDai.addMouseListener(mml);
		btnExt.addMouseListener(mml);
	}
		
		    /**
    		 * The listener interface for receiving myMouse events.
    		 * The class that is interested in processing a myMouse
    		 * event implements this interface, and the object created
    		 * with that class is registered with a component using the
    		 * component's <code>addMyMouseListener<code> method. When
    		 * the myMouse event occurs, that object's appropriate
    		 * method is invoked.
    		 *监听人机窗口的鼠标事件
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
			public void mouseClicked(MouseEvent e) {
			}

			
			    /* (非 Javadoc)
			    * 
			    * 
			    * @param e
			    * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
			    */
			    
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getSource() == btnEai) {
					AIFrameClose();
					cp.level=1;
					new ChessPlay(userName).play();
				} else if (e.getSource() == btnCai) {
					AIFrameClose();
					cp.level=2;
					new ChessPlay(userName).play();
				} else if (e.getSource() == btnDai) {
					AIFrameClose();
					cp.level=3;
					new ChessPlay(userName).play();
				} else if (e.getSource() == btnExt) {
					AIFrameClose();
					new MainFrame().initMf(userName);
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
    		 * AI frame close.
    		 * 在不关闭程序的基础上关闭人机窗口
    		 */
		    
		public void AIFrameClose() {
			this.dispose();
		}
}
