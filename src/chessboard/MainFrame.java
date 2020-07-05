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
     *��Ϸ��������
     * @date 2020��7��4��
     * @author zengcongying
     * @version v1.0
     */
    
public class MainFrame extends JFrame {
	
	/** The uf. */
	public static UserFile uf=new UserFile();//ʵ�����ļ�������
	
	/** The user name. */
	private String userName;//�������յ�¼���ڴ������û���
	
	/** The game name. */
	private JLabel gameName;// ��Ϸ��
	
	/** The btn his. */
	private JButton btnCom, btnNet, btnNot, btnExt, btnHis;//��ť
	
	/** The gh image. */
	// ��Ϸ����ͼƬ
	Image ghImage = null;// ��������
	
	/** The gt image. */
	ImageIcon gtImage = new ImageIcon("����\\��Ϸ����.png");// ��������
	
	/** The t image. */
	// �������Ͻǵı���
	ImageIcon tImage = new ImageIcon("����\\������.png");
	
	/** The tt image. */
	Image ttImage = tImage.getImage();

	/** The width. */
	// ȡ����Ļ����Ϣ
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	/** The height. */
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	
	    /**
    	 * Inits the mf.
    	 *��Ϸ�������ڳ�ʼ��
    	 * @param name the name
    	 */
	    
	public void initMf(String name) {
		userName = name;
		try {
			ghImage = ImageIO.read(new File("����\\����.png"));
			// gtImage=ImageIO.read(new File("D:\\Java\\������\\������.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getContentPane().add(new JLabel(new ImageIcon(ghImage)));
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().setOpaque(false);

		gameName = new JLabel(gtImage);
		gameName.setBounds(135, 0, 200, 100);
		// gameName.setFont(new Font("����",Font.BOLD,40));
		this.getLayeredPane().add(gameName);

		btnCom = new JButton("�˻���ս");
		btnCom.setBounds(135, 120, 210, 60);
		btnCom.setFont(new Font("����", Font.BOLD, 40));
		btnCom.setContentAreaFilled(false);
		btnCom.setFocusPainted(false);
		this.getLayeredPane().add(btnCom);

		btnNet = new JButton("���˶�ս");
		btnNet.setBounds(135, 200, 210, 60);
		btnNet.setFont(new Font("����", Font.BOLD, 40));
		btnNet.setContentAreaFilled(false);
		// btnNet.setBorderPainted(false);
		btnNet.setFocusPainted(false);
		this.getLayeredPane().add(btnNet);

		btnHis = new JButton("��ʷ��¼");
		btnHis.setBounds(135, 280, 210, 60);
		btnHis.setFont(new Font("����", Font.BOLD, 40));
		btnHis.setContentAreaFilled(false);
		btnHis.setFocusPainted(false);
		this.getLayeredPane().add(btnHis);

		btnNot = new JButton("��Ϸ˵��");
		btnNot.setBounds(135, 360, 210, 60);
		btnNot.setFont(new Font("����", Font.BOLD, 40));
		btnNot.setContentAreaFilled(false);
		btnNot.setFocusPainted(false);
		this.getLayeredPane().add(btnNot);

		btnExt = new JButton("�˳���Ϸ");
		btnExt.setBounds(135, 440, 210, 60);
		btnExt.setFont(new Font("����", Font.BOLD, 40));
		btnExt.setContentAreaFilled(false);
		btnExt.setFocusPainted(false);
		this.getLayeredPane().add(btnExt);

		this.setTitle("������");
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
    	 *������Ϸ������������¼�
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
					File file = new File("��Ϸ˵��.docx");
					desk.open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == btnExt) {
				int flag = JOptionPane.showConfirmDialog(null, "���Ҫ�˳���Ϸ��");
				if (flag == 0)
					System.exit(0);
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
    	 * Main frame close.
    	 * �ڲ��رճ���Ļ����Ϲر���Ϸ��������
    	 */
	    
	public void MainFrameClose() {
		this.dispose();
	}
}
