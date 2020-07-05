package chessplay;

import java.io.*;
import java.net.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import chessboard.NetChessBoardFrame;


    // TODO: Auto-generated Javadoc
/**
     * The Class GameServer.
     *���˶�ս����������
     * @date 2020��7��4��
     * @author zengcongying
     * @version v1.0
     */
    
public class GameServer implements Runnable {
	
	/** The ncbf. */
	static NetChessBoardFrame ncbf = new NetChessBoardFrame();
	
	/** The server. */
	private ServerSocket server;
	
	/** The client. */
	private Socket client;
	
	/** The name. */
	private String name;
	
	/** The is. */
	private static InputStream is;
	
	/** The os. */
	private static OutputStream os;
	
	/** The flag. */
	boolean flag = true;

	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @see java.lang.Runnable#run()
	    */
	    
	@Override
	public void run() {
		try {
			ServerSocket server = new ServerSocket(Integer.parseInt(ncbf.ServerPort));
			Socket sclient = server.accept();
			is = sclient.getInputStream();
			os = sclient.getOutputStream();
			while (true) {
				if (flag) {
					String clientStr = ncbf.getX() + "-" + ncbf.getY();
					os.write(clientStr.getBytes());
					if (ncbf.getX() <= 15 && ncbf.getY() <= 15) {
						ncbf.allChess[ncbf.getX()][ncbf.getY()] = 1;
					}
					if (checkFive() >= 5) {
						JOptionPane.showMessageDialog(null, "��Ӯ��");
						os.write((clientStr + "-L").getBytes());
						ncbf.clearBoard();
						break;
					}
					flag = false;
				} else {
					byte[] b = new byte[1024];
					int length = is.read(b);
					String strReceive = new String(b, 0, length);
					String serverStr[] = strReceive.split("-");
					if (serverStr.length > 2) {
						int x = Integer.parseInt(serverStr[0]);
						int y = Integer.parseInt(serverStr[1]);
						if (x <= 15 && y <= 15) {
							ncbf.allChess[x][y] = 2;
						}
						if (serverStr[2].equalsIgnoreCase("L")) {
							ncbf.allChess[Integer.parseInt(serverStr[0])][Integer.parseInt(serverStr[1])] = 2;
							JOptionPane.showMessageDialog(null, "������");
							ncbf.clearBoard();
							break;
						}
					}
					int x = Integer.parseInt(serverStr[0]);
					int y = Integer.parseInt(serverStr[1]);
					if (x <= 15 && y <= 15) {
						ncbf.allChess[x][y] = 2;
					}
					flag = true;
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "����ʧ�ܣ���");
			ncbf.clearBoard();
			e.printStackTrace();
		}

	}

	/**
	 * Close.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void close() throws IOException {
		server.close();
		client.close();
		is.close();
		os.close();
	}

	
	    /**
    	 * Check five.
    	 *��������������ӣ��ж�ʤ��
    	 * @return the int
    	 */
	    
	public int checkFive() {
		int count = 1;
		int x = ncbf.lastChess[0][0], y = ncbf.lastChess[0][1];
		int isBlack = 0;
		if (x <= 0 || x >= 15)
			return 1;
		if (y <= 0 || y >= 15)
			return 1;
		int bd[][] = ncbf.allChess;
		if (bd[x][y] == 1)
			isBlack = 1;
		if (bd[x][y] == 2)
			isBlack = 2;
		int sum[] = { 0, 0, 0, 0 };
		boolean locked = false;
		// ˮƽ������
		for (int i = 1; i < 5 && ((x - i) >= 0) && (!locked); i++) {
			if (isBlack == bd[x - i][y]) {
				count++;
			} else
				locked = true;
		}
		locked = false;
		for (int i = 1; i < 5 && ((x + i) <= 14) && (!locked); i++)
			if (isBlack == bd[x + i][y])
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
			if (isBlack == bd[x][y - i])
				count++;
			else
				locked = true;
		locked = false;
		for (int i = 1; i < 5 && ((y + i) <= 14) && (!locked); i++)// ��ֹѭ��������ͬɫ����4�����������̱߽��������ͬɫ�ڵ�
			if (isBlack == bd[x][y + i])
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
			if (isBlack == bd[x - i][y - i])
				count++;
			else
				locked = true;
		locked = false;
		for (int i = 1; i < 5 && ((x + i) <= 14) && ((y + i) <= 14) && (!locked); i++)// ��ֹѭ��������ͬɫ����4�����������̱߽��������ͬɫ�ڵ�
			if (isBlack == bd[x + i][y + i])
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
			if (isBlack == bd[x - i][y + i])
				count++;
			else
				locked = true;
		locked = false;
		for (int i = 1; i < 5 && ((x + i) <= 14) && ((y - i) >= 0) && (!locked); i++)// ��ֹѭ��������ͬɫ����4�����������̱߽��������ͬɫ�ڵ�
			if (isBlack == bd[x + i][y - i])
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
	 *
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
