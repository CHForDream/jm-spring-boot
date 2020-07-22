package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_5 implements IResult {
	/**
	 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
	 * 示例 1：
	 * 输入: "babad"
	 * 输出: "bab"
	 * 注意: "aba" 也是一个有效答案。
	 * 示例 2：
	 * 输入: "cbbd"
	 * 输出: "bb"
	 */
	@Override
	public String process() {
		String s = "aanaaaaaa";
		return longestPalindrome1(s);
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	public String longestPalindrome1(String s) {
		int len = s.length();
		if (len < 2) {
			return s;
		}

		int maxLen = 1;
		int begin = 0;
		// s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
		char[] charArray = s.toCharArray();

		// 枚举所有长度大于 1 的子串 charArray[i..j]
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
					maxLen = j - i + 1;
					begin = i;
				}
			}
		}
		return s.substring(begin, begin + maxLen);
	}

	/**
	 * 验证子串 s[left..right] 是否为回文串
	 */
	private boolean validPalindromic(char[] charArray, int left, int right) {
		while (left < right) {
			if (charArray[left] != charArray[right]) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}
