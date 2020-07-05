package chessplay;
import chessboard.*;


    // TODO: Auto-generated Javadoc
/**
     * The Class DAIPlay.
     *�����˻� Difficult AI
     * @date 2020��7��4��
     * @author zengcongying
     * @version v1.0
     */
    
public class DAIPlay {
	
	/** The ai is black. */
	public static boolean aiIsBlack;
	
	/** The score. */
	private int[][] score = new int[15][15];// ÿ��λ�õ÷�
	
	/** The cbf. */
	ChessBoardFrame cbf;
	
	/** The ai turn. */
	public static boolean aiTurn=false;
	
	/**
	 * Ai.
	 *
	 * @param color the color
	 */
	public void AI(boolean color) {
		Location loc=searchLocation();
		cbf.setChess(loc.getX(), loc.getY(), color);
	}

	/**
	 * Search location.
	 *
	 * @return the location
	 */
	public Location searchLocation() {
		int[][] chessboard=cbf.allChess;
		
		int AiColorNum;
		int ManColorNum;
		if (aiIsBlack) {
			AiColorNum = 1;
			ManColorNum = 2;
		} else {
			AiColorNum = 2;
			ManColorNum = 1;
		}
		// ÿ�ζ���ʼ����score��������
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				score[i][j] = 0;
			}
		}

		int humanChessmanNum = 0;// ��Ԫ���еĺ�������
		int machineChessmanNum = 0;// ��Ԫ���еİ�������
		int tupleScoreTmp = 0;// ��Ԫ��÷���ʱ����

		int goalX = -1;// Ŀ��λ��x����
		int goalY = -1;// Ŀ��λ��y����
		int maxScore = -1;// ������

		// 1.ɨ������15����
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 11; j++) {
				int k = j;
				while (k < j + 5) {
					if (chessboard[i][k] == AiColorNum)
						machineChessmanNum++;
					else if (chessboard[i][k] == ManColorNum)
						humanChessmanNum++;
					k++;
				}
				tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
				// Ϊ����Ԫ���ÿ��λ����ӷ���
				for (k = j; k < j + 5; k++) {
					score[i][k] += tupleScoreTmp;
				}
				// ����
				humanChessmanNum = 0;// ��Ԫ���еĺ�������
				machineChessmanNum = 0;// ��Ԫ���еİ�������
				tupleScoreTmp = 0;// ��Ԫ��÷���ʱ����
			}
		}

		// 2.ɨ������15��
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 11; j++) {
				int k = j;
				while (k < j + 5) {
					if (chessboard[k][i] == AiColorNum)
						machineChessmanNum++;
					else if (chessboard[k][i] == ManColorNum)
						humanChessmanNum++;

					k++;
				}
				tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
				// Ϊ����Ԫ���ÿ��λ����ӷ���
				for (k = j; k < j + 5; k++) {
					score[k][i] += tupleScoreTmp;
				}
				// ����
				humanChessmanNum = 0;// ��Ԫ���еĺ�������
				machineChessmanNum = 0;// ��Ԫ���еİ�������
				tupleScoreTmp = 0;// ��Ԫ��÷���ʱ����
			}
		}

		// 3.ɨ�����Ͻǵ����½��ϲಿ��
		for (int i = 14; i >= 4; i--) {
			for (int k = i, j = 0; j < 15 && k >= 0; j++, k--) {
				int m = k;
				int n = j;
				while (m > k - 5 && k - 5 >= -1) {
					if (chessboard[m][n] == AiColorNum)
						machineChessmanNum++;
					else if (chessboard[m][n] == ManColorNum)
						humanChessmanNum++;

					m--;
					n++;
				}
				// ע��б���жϵ�ʱ�򣬿��ܹ�������Ԫ�飨�����ĸ����䣩�������������Ҫ���Ե�
				if (m == k - 5) {
					tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
					// Ϊ����Ԫ���ÿ��λ����ӷ���
					for (m = k, n = j; m > k - 5; m--, n++) {
						score[m][n] += tupleScoreTmp;
					}
				}

				// ����
				humanChessmanNum = 0;// ��Ԫ���еĺ�������
				machineChessmanNum = 0;// ��Ԫ���еİ�������
				tupleScoreTmp = 0;// ��Ԫ��÷���ʱ����

			}
		}

		// 4.ɨ�����Ͻǵ����½��²ಿ��
		for (int i = 1; i < 15; i++) {
			for (int k = i, j = 14; j >= 0 && k < 15; j--, k++) {
				int m = k;
				int n = j;
				while (m < k + 5 && k + 5 <= 15) {
					if (chessboard[n][m] == AiColorNum)
						machineChessmanNum++;
					else if (chessboard[n][m] == ManColorNum)
						humanChessmanNum++;

					m++;
					n--;
				}
				// ע��б���жϵ�ʱ�򣬿��ܹ�������Ԫ�飨�����ĸ����䣩�������������Ҫ���Ե�
				if (m == k + 5) {
					tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
					// Ϊ����Ԫ���ÿ��λ����ӷ���
					for (m = k, n = j; m < k + 5; m++, n--) {
						score[n][m] += tupleScoreTmp;
					}
				}
				// ����
				humanChessmanNum = 0;// ��Ԫ���еĺ�������
				machineChessmanNum = 0;// ��Ԫ���еİ�������
				tupleScoreTmp = 0;// ��Ԫ��÷���ʱ����

			}
		}

		// 5.ɨ�����Ͻǵ����½��ϲಿ��
		for (int i = 0; i < 11; i++) {
			for (int k = i, j = 0; j < 15 && k < 15; j++, k++) {
				int m = k;
				int n = j;
				while (m < k + 5 && k + 5 <= 15) {
					if (chessboard[m][n] == AiColorNum)
						machineChessmanNum++;
					else if (chessboard[m][n] == ManColorNum)
						humanChessmanNum++;

					m++;
					n++;
				}
				// ע��б���жϵ�ʱ�򣬿��ܹ�������Ԫ�飨�����ĸ����䣩�������������Ҫ���Ե�
				if (m == k + 5) {
					tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
					// Ϊ����Ԫ���ÿ��λ����ӷ���
					for (m = k, n = j; m < k + 5; m++, n++) {
						score[m][n] += tupleScoreTmp;
					}
				}

				// ����
				humanChessmanNum = 0;// ��Ԫ���еĺ�������
				machineChessmanNum = 0;// ��Ԫ���еİ�������
				tupleScoreTmp = 0;// ��Ԫ��÷���ʱ����

			}
		}

		// 6.ɨ�����Ͻǵ����½��²ಿ��
		for (int i = 1; i < 11; i++) {
			for (int k = i, j = 0; j < 15 && k < 15; j++, k++) {
				int m = k;
				int n = j;
				while (m < k + 5 && k + 5 <= 15) {
					if (chessboard[n][m] == AiColorNum)
						machineChessmanNum++;
					else if (chessboard[n][m] == ManColorNum)
						humanChessmanNum++;

					m++;
					n++;
				}
				// ע��б���жϵ�ʱ�򣬿��ܹ�������Ԫ�飨�����ĸ����䣩�������������Ҫ���Ե�
				if (m == k + 5) {
					tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
					// Ϊ����Ԫ���ÿ��λ����ӷ���
					for (m = k, n = j; m < k + 5; m++, n++) {
						score[n][m] += tupleScoreTmp;
					}
				}

				// ����
				humanChessmanNum = 0;// ��Ԫ���еĺ�������
				machineChessmanNum = 0;// ��Ԫ���еİ�������
				tupleScoreTmp = 0;// ��Ԫ��÷���ʱ����

			}
		}

		// �ӿ�λ�����ҵ��÷�����λ��
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (chessboard[i][j] == 0 && score[i][j] > maxScore) {
					goalX = i;
					goalY = j;
					maxScore = score[i][j];
				}
			}
		}

		if (goalX != -1 && goalY != -1) {
			return new Location(goalX, goalY,cbf.isBlack);
		}

		// û�ҵ�����˵��ƽ����
		return new Location(-1, -1, cbf.isBlack);
	}

	/**
	 * Tuple score.
	 *
	 * @param humanChessmanNum the human chessman num
	 * @param machineChessmanNum the machine chessman num
	 * @return the int
	 */
	// ������Ԫ��������ֱ�
	public int tupleScore(int humanChessmanNum, int machineChessmanNum) {
		// 1.�����������ӣ����л������ӣ��з�Ϊ0
		if (humanChessmanNum > 0 && machineChessmanNum > 0) {
			return 0;
		}
		// 2.ȫ��Ϊ�գ�û�����ӣ��з�Ϊ7
		if (humanChessmanNum == 0 && machineChessmanNum == 0) {
			return 7;
		}
		// 3.������1�ӣ��з�Ϊ35
		if (machineChessmanNum == 1) {
			return 35;
		}
		// 4.������2�ӣ��з�Ϊ800
		if (machineChessmanNum == 2) {
			return 800;
		}
		// 5.������3�ӣ��з�Ϊ15000
		if (machineChessmanNum == 3) {
			return 15000;
		}
		// 6.������4�ӣ��з�Ϊ800000
		if (machineChessmanNum == 4) {
			return 800000;
		}
		// 7.������1�ӣ��з�Ϊ15
		if (humanChessmanNum == 1) {
			return 15;
		}
		// 8.������2�ӣ��з�Ϊ400
		if (humanChessmanNum == 2) {
			return 400;
		}
		// 9.������3�ӣ��з�Ϊ1800
		if (humanChessmanNum == 3) {
			return 1800;
		}
		// 10.������4�ӣ��з�Ϊ100000
		if (humanChessmanNum == 4) {
			return 100000;
		}
		return -1;// ������������϶������ˡ����д������������ִ��
	}
	
}
