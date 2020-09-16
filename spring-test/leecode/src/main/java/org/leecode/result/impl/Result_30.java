package org.leecode.result.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_30 implements IResult {
	/**
	 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
	 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
	 * 
	 * 示例 1：
	 * 输入：
	 * s = "barfoothefoobarman",
	 * words = ["foo","bar"]
	 * 输出：[0,9]
	 * 解释：
	 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
	 * 输出的顺序不重要, [9,0] 也是有效答案。
	 * 
	 * 示例 2：
	 * 输入：
	 * s = "wordgoodgoodgoodbestword",
	 * words = ["word","good","best","word"]
	 * 输出：[]
	 */
	public static void main(String[] args) {
		Result_30 e = new Result_30();
		e.process();
		e.gerneral();
	}

	private String s = "barfoothefoobarman";
	private String[] words = { "foo", "bar" };

	@Override
	public String process() {
		long ti = System.currentTimeMillis();
		List<Integer> result = findSubstring(s, words);
		System.out.println(result.toString() + " process time: " + (System.currentTimeMillis() - ti));
		return JSON.toJSONString(result);
	}

	@Override
	public String gerneral() {
		long ti = System.currentTimeMillis();
		List<Integer> result = findSubstring(s, words);
		System.out.println(result.toString() + " gerneral time: " + (System.currentTimeMillis() - ti));
		return JSON.toJSONString(result);
	}

	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> result = new ArrayList<Integer>();
		if (s == null || s.length() == 0 || words == null || words.length == 0) {
			return result;
		}

		int wordLen = words[0].length();
		int wordNum = words.length;
		int wordLength = wordNum * wordLen;

		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		for (String word : words) {
			wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
		}

		for (int i = 0; i < s.length() - wordLength + 1; i++) {
			String check = s.substring(i, i + wordLength);
			Map<String, Integer> tmpMap = new HashMap<String, Integer>();
			for (int j = 0; j < wordLength; j += wordLen) {
				String tmp = check.substring(j, j + wordLen);
				tmpMap.put(tmp, tmpMap.getOrDefault(tmp, 0) + 1);
			}

			if (tmpMap.equals(wordMap))
				result.add(i);
		}

		return result;
	}
}
