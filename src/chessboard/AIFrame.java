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
     *�˻�����
     * @date 2020��7��4��
     * @author zengcongying
     * @version v1.0
     */
    
public class AIFrame extends JFrame {
	
	/** The user name. */
	private String userName;//���������û���
	
	/** The cp. */
	ChessPlay cp;
	
	/** The game name. */
	private JLabel gameName;// ��Ϸ��
	
	/** The btn ext. */
	private JButton btnEai, btnCai, btnDai, btnExt;
	
	
	/** The gh image. */
	// �˻�����ͼƬ
	Image ghImage = null;
	
	/** The gt image. */
	ImageIcon gtImage = new ImageIcon("�˻�\\��Ϸ����.png");
	
	/** The t image. */
	ImageIcon tImage = new ImageIcon("�˻�\\������.png");
	
	/** The tt image. */
	Image ttImage = tImage.getImage();
	
	/** The width. */
	// ȡ����Ļ����Ϣ
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	/** The height. */
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	    /**
    	 * Inits the AI.
    	 *��ʼ���˻�����
    	 * @param name the name
    	 */
	    
	public void initAI(String name) {
		userName=name;
		try {
			ghImage = ImageIO.read(new File("�˻�\\����.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getContentPane().add(new JLabel(new ImageIcon(ghImage)));
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().setOpaque(false);
		
		gameName = new JLabel(gtImage);
		gameName.setBounds(135, 0, 200, 100);
		this.getLayeredPane().add(gameName);
		
		btnEai = new JButton("���˻�");
		btnEai.setBounds(135, 120, 210, 60);
		btnEai.setFont(new Font("����", Font.BOLD, 40));
		btnEai.setContentAreaFilled(false);
		btnEai.setFocusPainted(false);
		this.getLayeredPane().add(btnEai);

		btnCai = new JButton("һ���˻�");
		btnCai.setBounds(135, 200, 210, 60);
		btnCai.setFont(new Font("����", Font.BOLD, 40));
		btnCai.setContentAreaFilled(false);
		btnCai.setFocusPainted(false);
		this.getLayeredPane().add(btnCai);

		btnDai = new JButton("�����˻�");
		btnDai.setBounds(135, 280, 210, 60);
		btnDai.setFont(new Font("����", Font.BOLD, 40));
		btnDai.setContentAreaFilled(false);
		btnDai.setFocusPainted(false);
		this.getLayeredPane().add(btnDai);

		btnExt = new JButton("���ش���");
		btnExt.setBounds(135, 360, 210, 60);
		btnExt.setFont(new Font("����", Font.BOLD, 40));
		btnExt.setContentAreaFilled(false);
		btnExt.setFocusPainted(false);
		this.getLayeredPane().add(btnExt);
		
		this.setTitle("������");
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
    		 *�����˻����ڵ�����¼�
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
			public void mouseClicked(MouseEvent e) {
			}

			
			    /* (�� Javadoc)
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
    		 * AI frame close.
    		 * �ڲ��رճ���Ļ����Ϲر��˻�����
    		 */
		    
		public void AIFrameClose() {
			this.dispose();
		}
}
