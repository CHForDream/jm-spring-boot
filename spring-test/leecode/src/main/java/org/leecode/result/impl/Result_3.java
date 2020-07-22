package org.leecode.result.impl;

import java.util.HashMap;
import java.util.Map;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_3 implements IResult {
	/**
	 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
	 * 
	 * 示例 1:
	 * 输入: "abcabcbb"
	 * 输出: 3
	 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
	 * 
	 * 示例 2:
	 * 输入: "bbbbb"
	 * 输出: 1
	 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
	 * 
	 * 示例 3:
	 * 输入: "pwwkew"
	 * 输出: 3
	 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
	 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
	 */
	@Override
	public String process() {
		String s = "pwwkew";
		return JSON.toJSONString(lengthOfLongestSubstring(s));
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	public int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		Map<Character, Integer> charMap = new HashMap<Character, Integer>();
		int max = 0;
		int left = 0;

		for (int i = 0; i < s.length(); i++) {
			// 使用滑动窗口 left：窗口最左边的坐标 每次找到之前已经有的就在之前位置窗口右移
			if (charMap.containsKey(s.charAt(i))) {
				int ss = charMap.get(s.charAt(i)) + 1;
				if (left < ss) {
					left = ss;
				}
			}
			charMap.put(s.charAt(i), i);
			max = Math.max(max, i - left + 1);
		}

		return max;
	}
}
