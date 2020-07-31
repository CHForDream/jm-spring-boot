package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_14 implements IResult {

	/**
	 * 编写一个函数来查找字符串数组中的最长公共前缀。
	 * 如果不存在公共前缀，返回空字符串 ""。
	 * 
	 * 示例 1:
	 * 输入: ["flower","flow","flight"]
	 * 输出: "fl"
	 * 
	 * 示例 2:
	 * 输入: ["dog","racecar","car"]
	 * 输出: ""
	 * 解释: 输入不存在公共前缀。
	 * 
	 * 说明:
	 * 所有输入只包含小写字母 a-z 。
	 */

	@Override
	public String process() {
		String[] strs = { "dog", "racecar", "car" };
		return longestCommonPrefix(strs);
	}

	@Override
	public String gerneral() {
		return null;
	}

	public static void main(String[] args) {
		Result_14 e = new Result_14();
		System.out.println(e.process());
	}

	public String longestCommonPrefix(String[] strs) {
		String result = "";
		if (strs == null || strs.length == 0 || strs[0].length() == 0) {
			return result;
		}

		for (int j = 0; j < strs[0].length(); j++) {
			char c = strs[0].charAt(j);
			for (int i = 0; i < strs.length; i++) {
				if (j >= strs[i].length() || c != strs[i].charAt(j)) {
					return result;
				}
			}
			result += c;
		}
		return result;
	}
}
