package chessplay;

import javax.swing.*;

import chessboard.*;
import dao.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


    // TODO: Auto-generated Javadoc
/**
     * The Class ChessPlay.
     *�˻���ս������
     * @date 2020��7��4��
     * @author zengcongying
     * @version v1.0
     */
    
public class ChessPlay extends JFrame {
	
	/** The user name. */
	private static String userName;//�����û���
	
	/** The init color. */
	private static boolean initColor;//��������û�ѡ���������ɫ
	
	/** The driver name. */
	//���ݿ�������Ϣ
	private static String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	private static String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=zcy01430&useSSL=true&CharacterEncoding=GBK";
	
	/** The uul. */
	private static UserUtil uul = new UserUtil(driverName, uri);
	
	/** The uf. */
	private static UserFile uf = new UserFile();//IO������

	/** The play. */
	Music play=new Music("Stan.MP3");
	
	/** The level. */
	public static int level;// �˻��Ѷ�

	/** The cbf. */
	ChessBoardFrame cbf;// �������
	
	/** The control. */
	ControlBoard control;// �Ҳఴť���
	
	/** The eaip. */
	EAIPlay eaip = new EAIPlay();
	
	/** The caip. */
	CAIPlay caip = new CAIPlay();
	
	/** The daip. */
	DAIPlay daip = new DAIPlay();
	
	/** The is select. */
	private static boolean isSelect = false;

	/** The width. */
	// ȡ����Ļ����Ϣ
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	/** The height. */
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	/** The t image. */
	ImageIcon tImage = new ImageIcon("�˻�\\������.png");
	
	/** The tt image. */
	Image ttImage = tImage.getImage();

	
	    /**
    	 * Instantiates a new chess play.
    	 *
    	 * @param name the name
    	 */
	    
	public ChessPlay(String name) {
		userName = name;
		this.setTitle("������");// ���ñ���
		this.setSize(750, 635);// ���ô����С
		this.setLocation((width - 750) / 2, (height - 650) / 2);// ���������ʾ
		this.setResizable(false); // �����С���ɱ�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ����رշ�ʽ
		this.setCursor(Cursor.HAND_CURSOR);// ��״�������
		// this.setVisible(true);// ����ɼ�
		this.getLayeredPane().setLayout(null);

		cbf = new ChessBoardFrame();
		control.levelName = level;
		control = new ControlBoard(name);
		control.setPreferredSize(new Dimension(135, 635));
		this.getContentPane().add(control, BorderLayout.EAST);
		control.setVisible(true);

		ButtonAct ba = new ButtonAct();
		control.btnStr.addActionListener(ba);
		control.btnExt.addActionListener(ba);
		control.btnLos.addActionListener(ba);
	}

	
	    /**
    	 * Play.
    	 * ��ʼ��Ϸ
    	 */
	    
	public void play() {
		ClickAct playChess = new ClickAct();
		control.lblmusic.addMouseListener(playChess);
		cbf.addMouseListener(playChess);
		cbf.setPreferredSize(new Dimension(600, 635));
		cbf.setBounds(0, 0, 600, 635);
		this.getContentPane().add(cbf);
		cbf.repaint();
		cbf.setVisible(true);
		this.setVisible(true);// ����ɼ�
		this.setIconImage(ttImage);
	}

	
	    /**
    	 * The Class ClickAct.
    	 *��������
    	 * @date 2020��7��4��
    	 * @author zengcongying
    	 * @version v1.0
    	 */
	    
	private class ClickAct extends MouseAdapter {
		
		
		    /* (�� Javadoc)
		    * 
		    * 
		    * @param e
		    * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
		    */
		    
		public void mousePressed(MouseEvent e) {
			if(e.getSource()==control.lblmusic) {
				play.start();
			}
			if (!isSelect)
				JOptionPane.showMessageDialog(null, "������ʼ��ť��");
			else {
				int x = e.getX();
				int y = e.getY();
				if (x >= 45 && x <= 550 && y >= 50 && y <= 555) {
					// ��x,yת���������ϵ�����
					x = (((x - 45 + (550 - 45) / 28) / ((550 - 45) / 14) * (550 - 45) / 14 + 46) - 45) * 14
							/ (550 - 45);
					y = (((y - 50 + (555 - 50) / 28) / ((555 - 50) / 14) * (555 - 50) / 14 + 51) - 50) * 14
							/ (555 - 50);
					if (cbf.isBlack) {
						if (cbf.isEmpty == false) {
							cbf.isBlack = !cbf.isBlack;
							cbf.setChess(x, y, cbf.isBlack);
							cbf.isEmpty = true;
							cbf.isBlack = !cbf.isBlack;
						} else {
							cbf.setChess(x, y, cbf.isBlack);
							cbf.isBlack = !cbf.isBlack;
						}
					} else {
						if (cbf.isEmpty == false) {
							cbf.isBlack = !cbf.isBlack;
							cbf.setChess(x, y, cbf.isBlack);
							cbf.isEmpty = true;
							cbf.isBlack = !cbf.isBlack;
						} else {
							cbf.setChess(x, y, cbf.isBlack);
							cbf.isBlack = !cbf.isBlack;
						}
					}
					if (checkFive() >= 5) {
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String dateStr = sdf.format(date);
						if (level == 1) {
							if (initColor != cbf.isBlack) {
								uul.saveUserHistory(userName, dateStr, "EAI", "W");
								try {
									uf.saveUser(userName, dateStr, "EAI", "W");
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else if (initColor == cbf.isBlack) {
								uul.saveUserHistory(userName, dateStr, "EAI", "L");
								try {
									uf.saveUser(userName, dateStr, "EAI", "L");
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
						if (level == 2) {
							if (initColor != cbf.isBlack) {
								uul.saveUserHistory(userName, dateStr, "CAI", "W");
								try {
									uf.saveUser(userName, dateStr, "CAI", "W");
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							} else if (initColor == cbf.isBlack) {
								uul.saveUserHistory(userName, dateStr, "CAI", "L");
								try {
									uf.saveUser(userName, dateStr, "CAI", "L");
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
						if (level == 3) {
							if (initColor != cbf.isBlack) {
								uul.saveUserHistory(userName, dateStr, "DAI", "W");
								try {
									uf.saveUser(userName, dateStr, "DAI", "W");
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							} else if (initColor == cbf.isBlack) {
								uul.saveUserHistory(userName, dateStr, "DAI", "L");
								try {
									uf.saveUser(userName, dateStr, "DAI", "L");
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
						JOptionPane.showMessageDialog(null, !cbf.isBlack ? "����Ӯ��" : "����Ӯ��");
						init();
					} else {
						if (cbf.isEmpty != false) {
							Location loc = null;
							if (level == 1) {
								loc = eaip.searchLocation();
							} else if (level == 2) {
								loc = caip.searchLocation();
							} else if (level == 3) {
								loc = daip.searchLocation();
							}
							cbf.setChess(loc.getX(), loc.getY(), cbf.isBlack);
							cbf.isBlack = !cbf.isBlack;
							if (checkFive() >= 5) {
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String dateStr = sdf.format(date);
								if (level == 1) {
									if (initColor != cbf.isBlack) {
										uul.saveUserHistory(userName, dateStr, "EAI", "W");
										try {
											uf.saveUser(userName, dateStr, "EAI", "W");
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									} else if (initColor == cbf.isBlack) {
										uul.saveUserHistory(userName, dateStr, "EAI", "L");
										try {
											uf.saveUser(userName, dateStr, "EAI", "L");
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
								}
								if (level == 2) {
									if (initColor != cbf.isBlack) {
										uul.saveUserHistory(userName, dateStr, "CAI", "W");
										try {
											uf.saveUser(userName, dateStr, "CAI", "W");
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									} else if (initColor == cbf.isBlack) {
										uul.saveUserHistory(userName, dateStr, "CAI", "L");
										try {
											uf.saveUser(userName, dateStr, "CAI", "L");
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
								}
								if (level == 3) {
									if (initColor != cbf.isBlack) {
										uul.saveUserHistory(userName, dateStr, "DAI", "W");
										try {
											uf.saveUser(userName, dateStr, "DAI", "W");
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									} else if (initColor == cbf.isBlack) {
										uul.saveUserHistory(userName, dateStr, "DAI", "L");
										try {
											uf.saveUser(userName, dateStr, "DAI", "L");
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
								}
								JOptionPane.showMessageDialog(null, !cbf.isBlack ? "����Ӯ��" : "����Ӯ��");
								init();
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "�������������壡");
				}
			}
		}
	}

	
	    /**
    	 * The Class ButtonAct.
    	 *�����Ҳ���ť
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
			if (e.getActionCommand().equals("��ʼ")) {
				if (!isSelect)
					selectColor();
				else {
					int flag = JOptionPane.showConfirmDialog(null, "�Ծ����ڽ����� ��Ҫ���¿�ʼ��Ϸ��");
					if (flag == 0) {
						init();
						selectColor();
					}
				}
			}
			if (e.getActionCommand().equals("�˳�")) {
				if (isSelect) {
					int flag = JOptionPane.showConfirmDialog(null, "�Ծ����ڽ����� ��Ҫ�˳���Ϸ��");
					if (flag == 0) {
						init();
						isExit();
					}
				} else {
					isExit();
				}
			}
			if (e.getActionCommand().equals("����")) {
				if (isSelect) {
					int flag = JOptionPane.showConfirmDialog(null, "����ôѡ�������𣬲���Ŭ��һ������");
					if (flag == 0) {
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String dateStr = sdf.format(date);
						if (level == 1) {
							uul.saveUserHistory(userName, dateStr, "EAI", "L");
							try {
								uf.saveUser(userName, dateStr, "EAI", "L");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if (level == 2) {
							uul.saveUserHistory(userName, dateStr, "CAI", "L");
							try {
								uf.saveUser(userName, dateStr, "CAI", "L");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if (level == 3) {
							uul.saveUserHistory(userName, dateStr, "DAI", "L");
							try {
								uf.saveUser(userName, dateStr, "DAI", "L");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						init();
					} else
						return;
				}
			}
		}
	}

	
	    /**
    	 * Inits the.
    	 * ��Ϸ���ã���������
    	 */
	    
	public void init() {
		cbf.init();
		control.init();
		isSelect = false;
	}

	
	    /**
    	 * Select color.
    	 *ѡ��������ɫ
    	 * @return true, if successful
    	 */
	    
	public boolean selectColor() {
		int flag = JOptionPane.showConfirmDialog(null, "ѡ�������Ϊ������");
		if (flag == 0) {
			cbf.isBlack = true;
			eaip.aiIsBlack = false;
		} else if (flag == 1) {
			cbf.isBlack = false;
			eaip.aiIsBlack = true;
		} else if (flag == 2)
			isExit();
		isSelect = true;
		initColor = cbf.isBlack;
		return cbf.isBlack;
	}

	
	    /**
    	 * Checks if is exit.
    	 * �ڲ��رճ���Ļ����Ϲرմ˴���
    	 */
	    
	public void isExit() {
		this.dispose();
		new AIFrame().initAI(userName);
	}

	
	    /**
    	 * Check five.
    	 *�����ж��Ƿ��������ӣ��ж�ʤ��
    	 * @return the int
    	 */
	    
	private int checkFive() {
		int count = 1;
		int x = cbf.lastChess[0][0], y = cbf.lastChess[0][1];
		if (x <= 0 || x >= 15)
			return 1;
		if (y <= 0 || y >= 15)
			return 1;
		int bd[][] = cbf.allChess;
		int sum[] = { 0, 0, 0, 0 };
		boolean locked = false;
		// ˮƽ������
		for (int i = 1; i < 5 && ((x - i) >= 0) && (!locked); i++) {
			if (bd[x][y] == bd[x - i][y]) {
				count++;
			} else
				locked = true;
		}
		locked = false;
		for (int i = 1; i < 5 && ((x + i) <= 14) && (!locked); i++)
			if (bd[x][y] == bd[x + i][y])
				count++;
			else
				locked = true;
		sum[0] = count;
		if (count >= 5)
			return count;
		// ��ֱ������
		count = 1;
		locked = false;
		for (int i = 1; i < 5 && ((y - i) >= 0) && (!locked); i++)// ��ֹѭ��������ͬɫ����4�����������̱߽��������ͬɫ�ڵ�
			if (bd[x][y] == bd[x][y - i])
				count++;
			else
				locked = true;
		locked = false;
		for (int i = 1; i < 5 && ((y + i) <= 14) && (!locked); i++)// ��ֹѭ��������ͬɫ����4�����������̱߽��������ͬɫ�ڵ�
			if (bd[x][y] == bd[x][y + i])
				count++;
			else
				locked = true;

		sum[1] = count;
		if (count >= 5)
			return count;
		// ���ϵ�����б����
		count = 1;
		locked = false;
		for (int i = 1; i < 5 && ((y - i) >= 0) && ((x - i) >= 0) && (!locked); i++)// ��ֹѭ��������ͬɫ����4�����������̱߽��������ͬɫ�ڵ�
			if (bd[x][y] == bd[x - i][y - i])
				count++;
			else
				locked = true;
		locked = false;
		for (int i = 1; i < 5 && ((x + i) <= 14) && ((y + i) <= 14) && (!locked); i++)// ��ֹѭ��������ͬɫ����4�����������̱߽��������ͬɫ�ڵ�
			if (bd[x][y] == bd[x + i][y + i])
				count++;
			else
				locked = true;
		sum[2] = count;
		if (count >= 5)
			return count;
		// ���µ�����б����
		count = 1;
		locked = false;
		for (int i = 1; i < 5 && ((y + i) <= 14) && ((x - i) >= 0) && (!locked); i++)// ��ֹѭ��������ͬɫ����4�����������̱߽��������ͬɫ�ڵ�
			if (bd[x][y] == bd[x - i][y + i])
				count++;
			else
				locked = true;
		locked = false;
		for (int i = 1; i < 5 && ((x + i) <= 14) && ((y - i) >= 0) && (!locked); i++)// ��ֹѭ��������ͬɫ����4�����������̱߽��������ͬɫ�ڵ�
			if (bd[x][y] == bd[x + i][y - i])
				count++;
			else
				locked = true;
		sum[3] = count;
		if (count >= 5)
			return count;
		return MAX(sum, 4);
	}

	
	    /**
    	 * Max.
    	 *�����ж�ʤ��
    	 * @param a the a
    	 * @param n the n
    	 * @return the int
    	 */
	    
	private int MAX(int[] a, int n) {

		int max = a[0];
		for (int i = 1; i < n; i++) {
			if (a[i] > max)
				max = a[i];
		}
		return max;
	}

}
