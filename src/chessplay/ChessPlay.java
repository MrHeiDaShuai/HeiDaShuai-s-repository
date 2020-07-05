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
     *人机对战主窗体
     * @date 2020年7月4日
     * @author zengcongying
     * @version v1.0
     */
    
public class ChessPlay extends JFrame {
	
	/** The user name. */
	private static String userName;//接收用户名
	
	/** The init color. */
	private static boolean initColor;//用来标记用户选择的棋子颜色
	
	/** The driver name. */
	//数据库连接信息
	private static String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	private static String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=zcy01430&useSSL=true&CharacterEncoding=GBK";
	
	/** The uul. */
	private static UserUtil uul = new UserUtil(driverName, uri);
	
	/** The uf. */
	private static UserFile uf = new UserFile();//IO流操作

	/** The play. */
	Music play=new Music("Stan.MP3");
	
	/** The level. */
	public static int level;// 人机难度

	/** The cbf. */
	ChessBoardFrame cbf;// 棋盘面板
	
	/** The control. */
	ControlBoard control;// 右侧按钮面板
	
	/** The eaip. */
	EAIPlay eaip = new EAIPlay();
	
	/** The caip. */
	CAIPlay caip = new CAIPlay();
	
	/** The daip. */
	DAIPlay daip = new DAIPlay();
	
	/** The is select. */
	private static boolean isSelect = false;

	/** The width. */
	// 取得屏幕的信息
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	/** The height. */
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	/** The t image. */
	ImageIcon tImage = new ImageIcon("人机\\五子棋.png");
	
	/** The tt image. */
	Image ttImage = tImage.getImage();

	
	    /**
    	 * Instantiates a new chess play.
    	 *
    	 * @param name the name
    	 */
	    
	public ChessPlay(String name) {
		userName = name;
		this.setTitle("五子棋");// 设置标题
		this.setSize(750, 635);// 设置窗体大小
		this.setLocation((width - 750) / 2, (height - 650) / 2);// 窗体居中显示
		this.setResizable(false); // 窗体大小不可变
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 窗体关闭方式
		this.setCursor(Cursor.HAND_CURSOR);// 手状光标类型
		// this.setVisible(true);// 窗体可见
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
    	 * 开始游戏
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
		this.setVisible(true);// 窗体可见
		this.setIconImage(ttImage);
	}

	
	    /**
    	 * The Class ClickAct.
    	 *监听棋盘
    	 * @date 2020年7月4日
    	 * @author zengcongying
    	 * @version v1.0
    	 */
	    
	private class ClickAct extends MouseAdapter {
		
		
		    /* (非 Javadoc)
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
				JOptionPane.showMessageDialog(null, "请点击开始按钮！");
			else {
				int x = e.getX();
				int y = e.getY();
				if (x >= 45 && x <= 550 && y >= 50 && y <= 555) {
					// 把x,y转换成棋盘上的坐标
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
						JOptionPane.showMessageDialog(null, !cbf.isBlack ? "黑棋赢了" : "白棋赢了");
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
								JOptionPane.showMessageDialog(null, !cbf.isBlack ? "黑棋赢了" : "白棋赢了");
								init();
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "请在棋盘内下棋！");
				}
			}
		}
	}

	
	    /**
    	 * The Class ButtonAct.
    	 *监听右部按钮
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
			if (e.getActionCommand().equals("开始")) {
				if (!isSelect)
					selectColor();
				else {
					int flag = JOptionPane.showConfirmDialog(null, "对局正在进行中 ，要重新开始游戏吗？");
					if (flag == 0) {
						init();
						selectColor();
					}
				}
			}
			if (e.getActionCommand().equals("退出")) {
				if (isSelect) {
					int flag = JOptionPane.showConfirmDialog(null, "对局正在进行中 ，要退出游戏吗？");
					if (flag == 0) {
						init();
						isExit();
					}
				} else {
					isExit();
				}
			}
			if (e.getActionCommand().equals("认输")) {
				if (isSelect) {
					int flag = JOptionPane.showConfirmDialog(null, "就这么选择认输吗，不再努力一下啦？");
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
    	 * 游戏重置，参数重置
    	 */
	    
	public void init() {
		cbf.init();
		control.init();
		isSelect = false;
	}

	
	    /**
    	 * Select color.
    	 *选择棋子颜色
    	 * @return true, if successful
    	 */
	    
	public boolean selectColor() {
		int flag = JOptionPane.showConfirmDialog(null, "选择黑棋作为先手吗？");
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
    	 * 在不关闭程序的基础上关闭此窗口
    	 */
	    
	public void isExit() {
		this.dispose();
		new AIFrame().initAI(userName);
	}

	
	    /**
    	 * Check five.
    	 *用来判断是否连成五子，判断胜负
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
		// 水平方向检测
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
		// 竖直方向检测
		count = 1;
		locked = false;
		for (int i = 1; i < 5 && ((y - i) >= 0) && (!locked); i++)// 终止循环条件：同色超过4个或触碰到棋盘边界或遇到非同色节点
			if (bd[x][y] == bd[x][y - i])
				count++;
			else
				locked = true;
		locked = false;
		for (int i = 1; i < 5 && ((y + i) <= 14) && (!locked); i++)// 终止循环条件：同色超过4个或触碰到棋盘边界或遇到非同色节点
			if (bd[x][y] == bd[x][y + i])
				count++;
			else
				locked = true;

		sum[1] = count;
		if (count >= 5)
			return count;
		// 左上到右下斜向检测
		count = 1;
		locked = false;
		for (int i = 1; i < 5 && ((y - i) >= 0) && ((x - i) >= 0) && (!locked); i++)// 终止循环条件：同色超过4个或触碰到棋盘边界或遇到非同色节点
			if (bd[x][y] == bd[x - i][y - i])
				count++;
			else
				locked = true;
		locked = false;
		for (int i = 1; i < 5 && ((x + i) <= 14) && ((y + i) <= 14) && (!locked); i++)// 终止循环条件：同色超过4个或触碰到棋盘边界或遇到非同色节点
			if (bd[x][y] == bd[x + i][y + i])
				count++;
			else
				locked = true;
		sum[2] = count;
		if (count >= 5)
			return count;
		// 左下到右上斜向检测
		count = 1;
		locked = false;
		for (int i = 1; i < 5 && ((y + i) <= 14) && ((x - i) >= 0) && (!locked); i++)// 终止循环条件：同色超过4个或触碰到棋盘边界或遇到非同色节点
			if (bd[x][y] == bd[x - i][y + i])
				count++;
			else
				locked = true;
		locked = false;
		for (int i = 1; i < 5 && ((x + i) <= 14) && ((y - i) >= 0) && (!locked); i++)// 终止循环条件：同色超过4个或触碰到棋盘边界或遇到非同色节点
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
    	 *辅助判断胜负
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
