package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_58 implements IResult {
	/**
	 * 58. 最后一个单词的长度
	 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
	 * 如果不存在最后一个单词，请返回 0 。
	 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
	 * 
	 * 示例:
	 * 输入: "Hello World"
	 * 输出: 5
	 */

	private String str = "Hello World";

	@Override
	public String process() {
		return JSON.toJSONString(lengthOfLastWord(str));
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		Result_58 r = new Result_58();
		System.out.println(JSON.toJSONString(r.process()));

	}

	public int lengthOfLastWord(String s) {
		int end = s.length() - 1;
		while (end >= 0 && s.charAt(end) == ' ') {
			end--;
		}
		int start = end;
		while (start >= 0 && s.charAt(start) != ' ') {
			start--;
		}
		return end - start;
	}
}
