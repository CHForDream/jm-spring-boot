package org.leecode.result.impl;

import java.util.HashSet;
import java.util.Set;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_10 implements IResult {

	/**
	 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
	 * '.' 匹配任意单个字符
	 * '*' 匹配零个或多个前面的那一个元素
	 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
	 * 
	 * 说明:
	 * s 可能为空，且只包含从 a-z 的小写字母。
	 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
	 * 
	 * 示例 1:
	 * 输入:
	 * s = "aa"
	 * p = "a"
	 * 输出: false
	 * 解释: "a" 无法匹配 "aa" 整个字符串。
	 * 示例 2:
	 * 输入:
	 * s = "aa"
	 * p = "a*"
	 * 输出: true
	 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
	 * 示例 3:
	 * 输入:
	 * s = "ab"
	 * p = ".*"
	 * 输出: true
	 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
	 * 示例 4:
	 * 输入:
	 * s = "aab"
	 * p = "c*a*b"
	 * 输出: true
	 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
	 * 示例 5:
	 * 输入:
	 * s = "mississippi"
	 * p = "mis*is*p*."
	 * 输出: false
	 */

	@Override
	public String process() {
		String s = "mississippi";
		String p = "mis*is*p*.";
		return JSON.toJSONString(isMatch(s, p));
	}

	@Override
	public String gerneral() {
		return null;
	}

	public boolean isMatch(String s, String p) {
		// S串当前下标位置，刚开始为0
		int index = 0;
		// 下一个状态集合
		// 这里状态用p串的下标表示，代表下一次可以从p的这些下标开始匹配
		Set<Integer> nextState = new HashSet<>();
		// 由于是刚开始匹配，这时候p下一个下标是0
		nextMatch(p, 0, nextState);
		// nextState不为空时，表示还有合法的下一个状态，匹配继续
		while (!nextState.isEmpty()) {
			// 当前状态就是上一次的nextState
			Set<Integer> nowState = nextState;
			// 创建新的nextState
			nextState = new HashSet<>();
			// 测试s[index]和集合里的状态是否有匹配
			for (int state : nowState) {
				// 如果同时到达s和p串末尾，匹配成功
				if (state >= p.length() && index >= s.length()) {
					return true;
				}
				// 仅仅p到达末尾还不行
				else if (state >= p.length()) {
					continue;
				}
				// s和p都未到达末尾
				else if (index < s.length()) {
					// 这里是匹配上的情况
					if (p.charAt(state) == '.' || s.charAt(index) == p.charAt(state)) {
						// 如果p串的下一个字符是'*'，当前状态可以匹配任意多次，所以下一个状态还是当前
						if (state + 1 < p.length() && p.charAt(state + 1) == '*') {
							nextMatch(p, state, nextState);
						}
						// 否则，下一个状态就是state+1
						else {
							nextMatch(p, state + 1, nextState);
						}
					}
				}
			}
			index++;
		}
		// 此时，nextState为空，代表没有合法的下一个状态了，匹配失败
		return false;
	}

	// p:正则表达式
	// state:下一个状态
	// nextState:下一个状态集合，无重复
	private void nextMatch(String p, int state, Set<Integer> nextState) {
		// 首先加上下一个状态到状态集中
		nextState.add(state);
		// 这里是判断下一个字符是'*'的情况，由于此时匹配次数可以是0，所以state+2也是合法的下一个状态
		if (state + 1 < p.length() && p.charAt(state + 1) == '*') {
			nextMatch(p, state + 2, nextState);
		}
	}
}
