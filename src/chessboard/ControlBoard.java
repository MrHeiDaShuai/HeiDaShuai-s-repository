package chessboard;

import javax.imageio.ImageIO;
import javax.swing.*;

import dao.Music;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


    // TODO: Auto-generated Javadoc
/**
     * The Class ControlBoard.
     *人机对战的右部面板
     * @date 2020年7月4日
     * @author zengcongying
     * @version v1.0
     */
    
public class ControlBoard extends JPanel {
	
	/** The level name. */
	public static int levelName;// 用来接收用户名

	/** The lbluser. */
	private JLabel lbluser;
	
	/** The user name. */
	private JLabel userName;// 用户名标签
	
	/** The vs. */
	private JLabel vs;// vs标签
	
	/** The ai. */
	private JLabel ai;// 人机难度标签
	
	/** The ai name. */
	public static JLabel aiName;
	
	/** The is exit. */
	public static boolean isExit = false;
	
	/** The btn bck. */
	private JButton btnPau, btnBck;// 暂停、悔棋按钮
	
	/** The btn los. */
	public JButton btnStr, btnExt, btnLos;// 认输、开始、退出按钮

	/** The music open image. */
	ImageIcon musicOpenImage = new ImageIcon("人机\\声音开.png");
	
	/** The music close image. */
	ImageIcon musicCloseImage = new ImageIcon("人机\\声音关.png");
	
	/** The lblmusic. */
	public static JLabel lblmusic;

	/**
	 * Instantiates a new control board.
	 *
	 * @param name the name
	 */
	public ControlBoard(String name) {
		super();
		this.setLayout(null);

		lblmusic = new JLabel(musicOpenImage);
		lblmusic.setBounds(30, 500, 80, 80);
		add(lblmusic);

		lbluser = new JLabel("用户：");
		lbluser.setBounds(0, 340, 70, 20);
		lbluser.setFont(new Font("楷体", Font.BOLD, 20));
		lbluser.setBackground(Color.gray);
		add(lbluser);

		userName = new JLabel(name);
		userName.setBounds(0, 370, 200, 20);
		userName.setFont(new Font("楷体", Font.BOLD, 20));
		userName.setBackground(Color.gray);
		userName.setForeground(Color.pink);
		add(userName);

		vs = new JLabel("VS");
		vs.setBounds(50, 390, 200, 30);
		vs.setFont(new Font("楷体", Font.BOLD, 30));
		vs.setBackground(Color.gray);
		vs.setForeground(Color.red);
		add(vs);

		ai = new JLabel("人机：");
		ai.setBounds(0, 430, 200, 20);
		ai.setFont(new Font("楷体", Font.BOLD, 20));
		ai.setBackground(Color.gray);
		add(ai);

		if (levelName == 1)
			aiName = new JLabel("EAI");
		else if (levelName == 2)
			aiName = new JLabel("CAI");
		else if (levelName == 3)
			aiName = new JLabel("DAI");
		aiName.setBounds(0, 460, 200, 20);
		aiName.setFont(new Font("楷体", Font.BOLD, 20));
		aiName.setBackground(Color.gray);
		aiName.setForeground(Color.pink);
		add(aiName);

		btnStr = new JButton("开始");
		btnStr.setBounds(0, 20, 130, 40);
		btnStr.setFont(new Font("楷体", Font.BOLD, 30));
		btnStr.setBackground(Color.gray);
		add(btnStr);

		btnExt = new JButton("退出");
		btnExt.setBounds(0, 80, 130, 40);
		btnExt.setFont(new Font("楷体", Font.BOLD, 30));
		btnExt.setBackground(Color.gray);
		add(btnExt);

		btnLos = new JButton("认输");
		btnLos.setBounds(0, 140, 130, 40);
		btnLos.setFont(new Font("楷体", Font.BOLD, 30));
		btnLos.setBackground(Color.gray);
		add(btnLos);

		btnPau = new JButton("暂停");
		btnPau.setBounds(0, 200, 130, 40);
		btnPau.setFont(new Font("楷体", Font.BOLD, 30));
		btnPau.setBackground(Color.gray);
		add(btnPau);

		btnBck = new JButton("悔棋");
		btnBck.setBounds(0, 260, 130, 40);
		btnBck.setFont(new Font("楷体", Font.BOLD, 30));
		btnBck.setBackground(Color.gray);
		add(btnBck);

		ButtonAct cbut = new ButtonAct();
		btnBck.addMouseListener(cbut);
		btnPau.addMouseListener(cbut);
	}
	
	
	    /**
    	 * The Class ButtonAct.
    	 *监听此面板上的按钮
    	 * @date 2020年7月4日
    	 * @author zengcongying
    	 * @version v1.0
    	 */
	    
	private class ButtonAct extends MouseAdapter {
		
		
		    /* (非 Javadoc)
		    * 
		    * 
		    * @param e
		    * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
		    */
		    
		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == btnStr) {

			} else if (e.getSource() == btnBck) {
				ChessBoardFrame.redo();
			} else if (e.getSource() == btnPau) {
				JOptionPane.showMessageDialog(null, "游戏已暂停，点击确认结束暂停！");
			}
		}
	}

	
	    /**
    	 * Inits the.
    	 * 游戏重置，重置参数
    	 */
	    
	public void init() {
		isExit = false;
	}
}
