package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_37 implements IResult {
	/**
	 * 编写一个程序，通过填充空格来解决数独问题。
	 * 一个数独的解法需遵循如下规则：
	 * 数字 1-9 在每一行只能出现一次。
	 * 数字 1-9 在每一列只能出现一次。
	 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
	 * 空白格用 '.' 表示。
	 * 
	 * 提示：
	 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
	 * 你可以假设给定的数独只有唯一解。
	 * 给定数独永远是 9x9 形式的。
	 */
	public static void main(String[] args) {
		Result_37 e = new Result_37();
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

	private boolean[][] rows = new boolean[9][9];
	private boolean[][] cols = new boolean[9][9];
	private boolean[][][] boxs = new boolean[3][3][9];

	@Override
	public String process() {
		long ti = System.currentTimeMillis();
		solveSudoku(nums);
		System.out.println(" process time: " + (System.currentTimeMillis() - ti));
		return JSON.toJSONString(nums);
	}

	@Override
	public String gerneral() {
		long ti = System.currentTimeMillis();
		solveSudoku(nums);
		System.out.println(JSON.toJSONString(nums) + "process time: " + (System.currentTimeMillis() - ti));
		return JSON.toJSONString(nums);
	}

	public void solveSudoku(char[][] board) {
		reslove(board, 0, 0);
	}

	private boolean reslove(char[][] board, int row, int col) {
		if (row == board.length) {
			return true;
		} else if (col == board.length) {
			return reslove(board, row + 1, 0);
		}

		if (board[row][col] != '.') {
			return reslove(board, row, col + 1);
		}

		for (int i = 0; i < 9; i++) {
			if (rows[row][i] || cols[col][i] || boxs[row / 3][col / 3][i]) {
				continue;
			}
			rows[row][i] = true;
			cols[col][i] = true;
			boxs[row / 3][col / 3][i] = true;
			board[row][col] = (char) (i + '0' + 1);
			if (reslove(board, row, col + 1)) {
				return true;
			}

			board[row][col] = '.';
			rows[row][i] = false;
			cols[col][i] = false;
			boxs[row / 3][col / 3][i] = false;
		}
		return false;
	}
}
