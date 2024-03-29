package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_36 implements IResult {
	/**
	 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
	 * 
	 * 数字 1-9 在每一行只能出现一次。
	 * 数字 1-9 在每一列只能出现一次。
	 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
	 * 
	 * 示例 1:
	 * 输入:
	 * [
	 * ["5","3",".",".","7",".",".",".","."],
	 * ["6",".",".","1","9","5",".",".","."],
	 * [".","9","8",".",".",".",".","6","."],
	 * ["8",".",".",".","6",".",".",".","3"],
	 * ["4",".",".","8",".","3",".",".","1"],
	 * ["7",".",".",".","2",".",".",".","6"],
	 * [".","6",".",".",".",".","2","8","."],
	 * [".",".",".","4","1","9",".",".","5"],
	 * [".",".",".",".","8",".",".","7","9"]
	 * ]
	 * 输出: true
	 * 示例 2:
	 * 输入:
	 * [
	 *   ["8","3",".",".","7",".",".",".","."],
	 *   ["6",".",".","1","9","5",".",".","."],
	 *   [".","9","8",".",".",".",".","6","."],
	 *   ["8",".",".",".","6",".",".",".","3"],
	 *   ["4",".",".","8",".","3",".",".","1"],
	 *   ["7",".",".",".","2",".",".",".","6"],
	 *   [".","6",".",".",".",".","2","8","."],
	 *   [".",".",".","4","1","9",".",".","5"],
	 *   [".",".",".",".","8",".",".","7","9"]
	 * ]
	 * 输出: false
	 * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
	 * 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
	 * 说明:
	 * 一个有效的数独（部分已被填充）不一定是可解的。
	 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
	 * 给定数独序列只包含数字 1-9 和字符 '.' 。
	 * 给定数独永远是 9x9 形式的。
	 */
	public static void main(String[] args) {
		Result_36 e = new Result_36();
		e.process();
		e.gerneral();
	}

	private char[][] nums = {
			{ '8', '3', '.', '.', '7', '.', '.', '.', '.' },
			{ '6', '.', '.', '1', '9', '5', '.', '.', '.' },
			{ '.', '9', '8', '.', '.', '.', '.', '6', '.' },
			{ '8', '.', '.', '.', '6', '.', '.', '.', '3' },
			{ '4', '.', '.', '8', '.', '3', '.', '.', '1' },
			{ '7', '.', '.', '.', '2', '.', '.', '.', '6' },
			{ '.', '6', '.', '.', '.', '.', '2', '8', '.' },
			{ '.', '.', '.', '4', '1', '9', '.', '.', '5' },
			{ '.', '.', '.', '.', '8', '.', '.', '7', '9' }
	};

	@Override
	public String process() {
		long ti = System.currentTimeMillis();
		boolean res = isValidSudoku(nums);
		System.out.println(res + " process time: " + (System.currentTimeMillis() - ti));
		return String.valueOf(res);
	}

	@Override
	public String gerneral() {
		long ti = System.currentTimeMillis();
		boolean res = isValidSudoku(nums);
		System.out.println(res + " process time: " + (System.currentTimeMillis() - ti));
		return String.valueOf(res);
	}

	private boolean[][] row = new boolean[9][9];
	private boolean[][] col = new boolean[9][9];
	private boolean[][][] box = new boolean[3][3][9];

	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				int num = board[i][j] - '0' - 1;
				if (row[i][num] || col[j][num] || box[i / 3][j / 3][num]) {
					return false;
				}
				row[i][num] = true;
				col[j][num] = true;
				box[i / 3][j / 3][num] = true;
			}
		}
		return true;
	}

}
