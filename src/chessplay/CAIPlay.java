package chessplay;
import chessboard.*;


    // TODO: Auto-generated Javadoc
/**
     * The Class CAIPlay.
     * 一般人机 Common AI
     * @date 2020年7月4日
     * @author zengcongying
     * @version v1.0
     */
    
public class CAIPlay {
	
	/** The ai is black. */
	public static boolean aiIsBlack;
	
	/** The score. */
	private int[][] score = new int[15][15];// 每个位置得分
	
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
		// 每次都初始化下score评分数组
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				score[i][j] = 0;
			}
		}

		int humanChessmanNum = 0;// 五元组中的黑棋数量
		int machineChessmanNum = 0;// 五元组中的白棋数量
		int tupleScoreTmp = 0;// 五元组得分临时变量

		int goalX = -1;// 目标位置x坐标
		int goalY = -1;// 目标位置y坐标
		int maxScore = -1;// 最大分数

		// 1.扫描横向的15个行
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
				// 为该五元组的每个位置添加分数
				for (k = j; k < j + 5; k++) {
					score[i][k] += tupleScoreTmp;
				}
				// 置零
				humanChessmanNum = 0;// 五元组中的黑棋数量
				machineChessmanNum = 0;// 五元组中的白棋数量
				tupleScoreTmp = 0;// 五元组得分临时变量
			}
		}

		// 2.扫描纵向15行
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
				// 为该五元组的每个位置添加分数
				for (k = j; k < j + 5; k++) {
					score[k][i] += tupleScoreTmp;
				}
				// 置零
				humanChessmanNum = 0;// 五元组中的黑棋数量
				machineChessmanNum = 0;// 五元组中的白棋数量
				tupleScoreTmp = 0;// 五元组得分临时变量
			}
		}

		// 3.扫描右上角到左下角上侧部分
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
				// 注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
				if (m == k - 5) {
					tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
					// 为该五元组的每个位置添加分数
					for (m = k, n = j; m > k - 5; m--, n++) {
						score[m][n] += tupleScoreTmp;
					}
				}

				// 置零
				humanChessmanNum = 0;// 五元组中的黑棋数量
				machineChessmanNum = 0;// 五元组中的白棋数量
				tupleScoreTmp = 0;// 五元组得分临时变量

			}
		}

		// 4.扫描右上角到左下角下侧部分
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
				// 注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
				if (m == k + 5) {
					tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
					// 为该五元组的每个位置添加分数
					for (m = k, n = j; m < k + 5; m++, n--) {
						score[n][m] += tupleScoreTmp;
					}
				}
				// 置零
				humanChessmanNum = 0;// 五元组中的黑棋数量
				machineChessmanNum = 0;// 五元组中的白棋数量
				tupleScoreTmp = 0;// 五元组得分临时变量

			}
		}

		// 5.扫描左上角到右下角上侧部分
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
				// 注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
				if (m == k + 5) {
					tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
					// 为该五元组的每个位置添加分数
					for (m = k, n = j; m < k + 5; m++, n++) {
						score[m][n] += tupleScoreTmp;
					}
				}

				// 置零
				humanChessmanNum = 0;// 五元组中的黑棋数量
				machineChessmanNum = 0;// 五元组中的白棋数量
				tupleScoreTmp = 0;// 五元组得分临时变量

			}
		}

		// 6.扫描左上角到右下角下侧部分
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
				// 注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
				if (m == k + 5) {
					tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
					// 为该五元组的每个位置添加分数
					for (m = k, n = j; m < k + 5; m++, n++) {
						score[n][m] += tupleScoreTmp;
					}
				}

				// 置零
				humanChessmanNum = 0;// 五元组中的黑棋数量
				machineChessmanNum = 0;// 五元组中的白棋数量
				tupleScoreTmp = 0;// 五元组得分临时变量

			}
		}

		// 从空位置中找到得分最大的位置
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

		// 没找到坐标说明平局了
		return new Location(-1, -1, cbf.isBlack);
	}

	/**
	 * Tuple score.
	 *各种五元组情况评分表
	 * @param humanChessmanNum the human chessman num
	 * @param machineChessmanNum the machine chessman num
	 * @return the int
	 */
	// 各种五元组情况评分表
	public int tupleScore(int humanChessmanNum, int machineChessmanNum) {
		// 1.既有人类落子，又有机器落子，判分为0
		if (humanChessmanNum > 0 && machineChessmanNum > 0) {
			return 0;
		}
		// 2.全部为空，没有落子，判分为7
		if (humanChessmanNum == 0 && machineChessmanNum == 0) {
			return 7;
		}
		// 3.机器落1子，判分为35
		if (machineChessmanNum == 1) {
			return 35;
		}
		// 4.机器落2子，判分为800
		if (machineChessmanNum == 2) {
			return 800;
		}
		// 5.机器落3子，判分为15000
		if (machineChessmanNum == 3) {
			return 15000;
		}
		// 6.机器落4子，判分为800000
		if (machineChessmanNum == 4) {
			return 800000;
		}
		// 7.人类落1子，判分为45
		if (humanChessmanNum == 1) {
			return 45;
		}
		// 8.人类落2子，判分为4000
		if (humanChessmanNum == 2) {
			return 900;
		}
		// 9.人类落3子，判分为18000
		if (humanChessmanNum == 3) {
			return 18000;
		}
		// 10.人类落4子，判分为1000000
		if (humanChessmanNum == 4) {
			return 1000000;
		}
		return -1;// 若是其他结果肯定出错了。这行代码根本不可能执行
	}
	
}
