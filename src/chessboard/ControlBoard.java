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
     *�˻���ս���Ҳ����
     * @date 2020��7��4��
     * @author zengcongying
     * @version v1.0
     */
    
public class ControlBoard extends JPanel {
	
	/** The level name. */
	public static int levelName;// ���������û���

	/** The lbluser. */
	private JLabel lbluser;
	
	/** The user name. */
	private JLabel userName;// �û�����ǩ
	
	/** The vs. */
	private JLabel vs;// vs��ǩ
	
	/** The ai. */
	private JLabel ai;// �˻��Ѷȱ�ǩ
	
	/** The ai name. */
	public static JLabel aiName;
	
	/** The is exit. */
	public static boolean isExit = false;
	
	/** The btn bck. */
	private JButton btnPau, btnBck;// ��ͣ�����尴ť
	
	/** The btn los. */
	public JButton btnStr, btnExt, btnLos;// ���䡢��ʼ���˳���ť

	/** The music open image. */
	ImageIcon musicOpenImage = new ImageIcon("�˻�\\������.png");
	
	/** The music close image. */
	ImageIcon musicCloseImage = new ImageIcon("�˻�\\������.png");
	
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

		lbluser = new JLabel("�û���");
		lbluser.setBounds(0, 340, 70, 20);
		lbluser.setFont(new Font("����", Font.BOLD, 20));
		lbluser.setBackground(Color.gray);
		add(lbluser);

		userName = new JLabel(name);
		userName.setBounds(0, 370, 200, 20);
		userName.setFont(new Font("����", Font.BOLD, 20));
		userName.setBackground(Color.gray);
		userName.setForeground(Color.pink);
		add(userName);

		vs = new JLabel("VS");
		vs.setBounds(50, 390, 200, 30);
		vs.setFont(new Font("����", Font.BOLD, 30));
		vs.setBackground(Color.gray);
		vs.setForeground(Color.red);
		add(vs);

		ai = new JLabel("�˻���");
		ai.setBounds(0, 430, 200, 20);
		ai.setFont(new Font("����", Font.BOLD, 20));
		ai.setBackground(Color.gray);
		add(ai);

		if (levelName == 1)
			aiName = new JLabel("EAI");
		else if (levelName == 2)
			aiName = new JLabel("CAI");
		else if (levelName == 3)
			aiName = new JLabel("DAI");
		aiName.setBounds(0, 460, 200, 20);
		aiName.setFont(new Font("����", Font.BOLD, 20));
		aiName.setBackground(Color.gray);
		aiName.setForeground(Color.pink);
		add(aiName);

		btnStr = new JButton("��ʼ");
		btnStr.setBounds(0, 20, 130, 40);
		btnStr.setFont(new Font("����", Font.BOLD, 30));
		btnStr.setBackground(Color.gray);
		add(btnStr);

		btnExt = new JButton("�˳�");
		btnExt.setBounds(0, 80, 130, 40);
		btnExt.setFont(new Font("����", Font.BOLD, 30));
		btnExt.setBackground(Color.gray);
		add(btnExt);

		btnLos = new JButton("����");
		btnLos.setBounds(0, 140, 130, 40);
		btnLos.setFont(new Font("����", Font.BOLD, 30));
		btnLos.setBackground(Color.gray);
		add(btnLos);

		btnPau = new JButton("��ͣ");
		btnPau.setBounds(0, 200, 130, 40);
		btnPau.setFont(new Font("����", Font.BOLD, 30));
		btnPau.setBackground(Color.gray);
		add(btnPau);

		btnBck = new JButton("����");
		btnBck.setBounds(0, 260, 130, 40);
		btnBck.setFont(new Font("����", Font.BOLD, 30));
		btnBck.setBackground(Color.gray);
		add(btnBck);

		ButtonAct cbut = new ButtonAct();
		btnBck.addMouseListener(cbut);
		btnPau.addMouseListener(cbut);
	}
	
	
	    /**
    	 * The Class ButtonAct.
    	 *����������ϵİ�ť
    	 * @date 2020��7��4��
    	 * @author zengcongying
    	 * @version v1.0
    	 */
	    
	private class ButtonAct extends MouseAdapter {
		
		
		    /* (�� Javadoc)
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
				JOptionPane.showMessageDialog(null, "��Ϸ����ͣ�����ȷ�Ͻ�����ͣ��");
			}
		}
	}

	
	    /**
    	 * Inits the.
    	 * ��Ϸ���ã����ò���
    	 */
	    
	public void init() {
		isExit = false;
	}
}
