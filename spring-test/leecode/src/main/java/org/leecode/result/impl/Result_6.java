package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Result_6 implements IResult {
	/**
	 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
	 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
	 * 
	 * L C I R
	 * E T O E S I I G
	 * E D H N
	 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
	 * 
	 * 请你实现这个将字符串进行指定行数变换的函数：
	 * string convert(string s, int numRows);
	 * 示例 1:
	 * 
	 * 输入: s = "LEETCODEISHIRING", numRows = 3
	 * 输出: "LCIRETOESIIGEDHN"
	 * 示例 2:
	 * 
	 * 输入: s = "LEETCODEISHIRING", numRows = 4
	 * 输出: "LDREOEIIECIHNTSG"
	 * 解释:
	 * 
	 * L D R
	 * E O E I I
	 * E C I H N
	 * T S G
	 */

	private static final Logger log = LoggerFactory.getLogger(Result_6.class);

	@Override
	public String process() {
		String s = "PAYPALISHIRING";
		return convert(s, 3);
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	public String convert(String s, int numRows) {
		if (s == null || numRows == 0)
			return "";
		if (numRows < 3)
			return s;
		int a1 = s.length() / (2 * numRows - 2);
		int a2 = s.length() % (2 * numRows - 2);
		int j = a1 * (numRows - 1) + a2 / numRows + a2 % numRows;
		int[][] arr = new int[numRows][j];
		int count = 1;
		for (int i = 0; i < j; i++) {
			for (int k = 0; k < numRows; k++) {
				if (count - 1 == s.length()) {
					break;
				}
				if (i % (numRows - 1) != 0) {
					if ((i + k) % (numRows - 1) == 0) {
						arr[k][i] = count;
						count++;
					}
					continue;
				}
				arr[k][i] = count;
				count++;
			}
			if (count - 1 == s.length()) {
				break;
			}
		}
		String result = "";
		for (int i = 0; i < numRows; i++) {
			String ss = "";
			for (int k = 0; k < j; k++) {
				if (arr[i][k] == 0) {
					ss += " ";
				} else {
					ss += s.charAt(arr[i][k] - 1);
					result += s.charAt(arr[i][k] - 1);
				}
			}
			log.info(ss);
		}
		return result;
	}
}
