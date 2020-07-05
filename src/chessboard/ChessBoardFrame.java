package chessboard;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;


    // TODO: Auto-generated Javadoc
/**
     * The Class ChessBoardFrame.
     *�˻���ս���������
     * @date 2020��7��4��
     * @author zengcongying
     * @version v1.0
     */
    
public class ChessBoardFrame extends JPanel {
	
	/** The is redo. */
	public static boolean isRedo = false;// ����Ƿ��ܻ���
	
	/** The is empty. */
	public static boolean isEmpty=true;//���������һ���Ƿ��ߵ�����λ��
	
	/** The is black. */
	public static boolean isBlack = true;// �����жϵ�ǰ�Ǻ��廹�ǰ���
	
	/** The all chess. */
	public static int allChess[][] = new int[15][15];
	
	/** The last chess. */
	public static int lastChess[][] = { { -1, -1 }, { -1, -1 } };
	
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
	ImageIcon tImage = new ImageIcon("�˻�\\������.png");
	
	/** The tt image. */
	Image ttImage = tImage.getImage();
	
	/** The x. */
	// ��������
	int x = 0;
	
	/** The y. */
	int y = 0;
	// �������ӵ�����
	// �����е��������ݣ�0��ʾ���ӣ�1��ʾ���ӣ�2��ʾ����

	
	    /**
	 * Instantiates a new chess board frame.
	 */
	    
	public ChessBoardFrame() {
		super();
		try {
			bgImage = ImageIO.read(new File("�˻�\\���̱���.png"));
			wImage = ImageIO.read(new File("�˻�\\������.png"));
			bImage = ImageIO.read(new File("�˻�\\������.png"));
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

	
	    /**
    	 * Sets the chess.
    	 *
    	 * @param x the x
    	 * @param y the y
    	 * @param isBlack the is black
    	 */
	    
	public void setChess(int x, int y, boolean isBlack) {// ����
		if (allChess[x][y] != 0) {
			JOptionPane.showMessageDialog(null, "�˴��������ӣ��������£�");
			isEmpty=false;
		} else if (isBlack) {
			allChess[x][y] = 1;
		} else {
			allChess[x][y] = 2;
		}
		record(x, y);
	}

	
	    /**
    	 * Record.
    	 *��¼ǰ�������������
    	 * @param chessX the chess X
    	 * @param chessY the chess Y
    	 */
	    
	public void record(int chessX, int chessY) {
		lastChess[1][0] = lastChess[0][0];
		lastChess[1][1] = lastChess[0][1];
		lastChess[0][0] = chessX;
		lastChess[0][1] = chessY;
		isRedo = true;
	}

	
	    /**
    	 * Redo.
    	 * ����
    	 */
	    
	static public void redo() { 
		if (isRedo) {
			if (lastChess[1][1] == -1) {
				allChess[lastChess[0][0]][lastChess[0][1]] = 0;
			} else {
				allChess[lastChess[0][0]][lastChess[0][1]] = 0;
				allChess[lastChess[1][0]][lastChess[1][1]] = 0;
			}
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					lastChess[i][j] = -1;
				}
			}
			isRedo = false;
		} else
			JOptionPane.showMessageDialog(null, "������˼����ǰ�������ܻ��壡");
	}
	
	    /**
    	 * Inits the.
    	 * ������Ϸ�����ò���
    	 */
	    
	public void init() {
		isRedo = false;// ����Ƿ��ܻ���
		isBlack=true;
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) 
				allChess[i][j]=0;
		}
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++)
				lastChess[i][j] = -1;
		}
	}
	
}
