package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_28 implements IResult {
	/**
	 * 实现 strStr() 函数。
	 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
	 * 
	 * 示例 1:
	 * 输入: haystack = "hello", needle = "ll"
	 * 输出: 2
	 * 
	 * 示例 2:
	 * 输入: haystack = "aaaaa", needle = "bba"
	 * 输出: -1
	 * 
	 * 说明:
	 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
	 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
	 * 
	 */
	public static void main(String[] args) {
		Result_28 e = new Result_28();
		e.process();
//		e.gerneral();
	}

	private String haystack = "thisisworld";
	private String needle = "world";

	@Override
	public String process() {
		int len = strStr(haystack, needle);
		System.out.println("len: " + len);
		System.out.println(changeRe(haystack, len, needle.length()));
		return JSON.toJSONString(len);
	}

	@Override
	public String gerneral() {
		int len = strStr(haystack, needle);
		System.out.println(changeRe(haystack, len, needle.length()));
		return JSON.toJSONString(len);
	}

	public int strStr(String haystack, String needle) {
		if (needle == null || needle.length() == 0) {
			return 0;
		}
		int index = -1;
		int stackLengh = haystack.length();
		int needLengh = needle.length();
		for (int i = 0; i < stackLengh - needLengh + 1; i++) {
			if (haystack.charAt(i) != needle.charAt(0)) {
				continue;
			}
			if (haystack.substring(i, i + needLengh).equals(needle)) {
				return i;
			}
		}
		return index;
	}

	private String changeRe(String haystack, int index, int len) {
		if (index == -1) {
			return "";
		}
		return haystack.substring(index, index + len);
	}
}
