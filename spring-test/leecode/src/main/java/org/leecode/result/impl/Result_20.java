package org.leecode.result.impl;

import java.util.HashMap;
import java.util.Stack;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_20 implements IResult {

	/**
	 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
	 * 
	 * 有效字符串需满足：
	 * 
	 * 左括号必须用相同类型的右括号闭合。
	 * 左括号必须以正确的顺序闭合。
	 * 注意空字符串可被认为是有效字符串。
	 * 
	 * 示例 1:
	 * 
	 * 输入: "()"
	 * 输出: true
	 * 示例 2:
	 * 
	 * 输入: "()[]{}"
	 * 输出: true
	 * 示例 3:
	 * 
	 * 输入: "(]"
	 * 输出: false
	 * 示例 4:
	 * 
	 * 输入: "([)]"
	 * 输出: false
	 * 示例 5:
	 * 
	 * 输入: "{[]}"
	 * 输出: true
	 */
	String s = "{[[]}";

	public static void main(String[] args) {
		Result_20 e = new Result_20();
		e.process();
		e.gerneral();
//		System.out.println(e.process());
//		System.out.println(e.gerneral());
	}

	@Override
	public String process() {
		long cur = System.currentTimeMillis();
		boolean res = isValid(s);
		System.out.println(res + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(res);
	}

	@Override
	public String gerneral() {
		long cur = System.currentTimeMillis();
		boolean res = isValid(s);
		System.out.println(res + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(res);
	}

	private HashMap<Character, Character> mappings = new HashMap<Character, Character>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(')', '(');
			put('}', '{');
			put(']', '[');
		}
	};

	private boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (this.mappings.containsKey(c)) {
				char topelement = stack.isEmpty() ? '#' : stack.pop();
				if (topelement != this.mappings.get(c)) {
					return false;
				}
			} else {
				stack.push(c);
			}
		}
		return stack.isEmpty();
	}
}
