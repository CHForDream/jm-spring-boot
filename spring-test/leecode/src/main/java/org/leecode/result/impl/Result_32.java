package org.leecode.result.impl;

import java.util.Stack;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_32 implements IResult {
	/**
	 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
	 * 
	 * 示例 1:
	 * 输入: "(()"
	 * 输出: 2
	 * 解释: 最长有效括号子串为 "()"
	 * 
	 * 示例 2:
	 * 输入: ")()())"
	 * 输出: 4
	 * 解释: 最长有效括号子串为 "()()"
	 */
	public static void main(String[] args) {
		Result_32 e = new Result_32();
		e.process();
		e.gerneral();
	}

	private String str = "(()";

	@Override
	public String process() {
		long ti = System.currentTimeMillis();
		int res = longestValidParentheses(str);
		System.out.println(res + " process time: " + (System.currentTimeMillis() - ti));
		return String.valueOf(res);
	}

	@Override
	public String gerneral() {
		long ti = System.currentTimeMillis();
		int res = longestValidParentheses(str);
		System.out.println(res + " process time: " + (System.currentTimeMillis() - ti));
		return String.valueOf(res);
	}

	public int longestValidParentheses(String s) {
		int maxlength = 0;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(-1);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				stack.push(i);
			} else if (c == ')') {
				stack.pop();
				if (stack.isEmpty()) {
					stack.push(i);
				} else {
					maxlength = Math.max(maxlength, i - stack.peek());
				}
			}
		}
		return maxlength;
	}
}
