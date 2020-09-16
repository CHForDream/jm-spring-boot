package org.leecode.result.impl;

import java.util.ArrayList;
import java.util.List;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_22 implements IResult {

	/**
	 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
	 * 示例：
	 * 
	 * 输入：n = 3
	 * 输出：[
	 * "((()))",
	 * "(()())",
	 * "(())()",
	 * "()(())",
	 * "()()()"
	 * ]
	 */

	private int n = 20;

	public static void main(String[] args) {
		Result_22 e = new Result_22();
		e.process();
//		e.gerneral();
//		System.out.println(e.process());
//		System.out.println(e.gerneral());
	}

	@Override
	public String process() {
		long cur = System.currentTimeMillis();
		List<String> res = generateParenthesis(n);
		System.out.println(res.size() + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(res);
	}

	@Override
	public String gerneral() {
		long cur = System.currentTimeMillis();
		List<String> res = generateParenthesis(n);
		System.out.println(res + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(res);
	}

	private List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<String>();
		if (n == 0) {
			result.add("");
			return result;
		} else if (n == 1) {
			result.add("()");
			return result;
		}
		dfs(result, "", 0, 0, n);
		return result;
	}

	private void dfs(List<String> result, String res, int left, int right, int n) {
		if (left < right || left > n || right > n) {
			return;
		}
		if (left == right && left == n) {
			result.add(res);
			return;
		}

		dfs(result, res + "(", left + 1, right, n);
		dfs(result, res + ")", left, right + 1, n);
	}
}
