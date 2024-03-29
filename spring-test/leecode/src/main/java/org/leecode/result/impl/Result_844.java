package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_844 implements IResult {
	/**
	 * 844. 比较含退格的字符串
	 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
	 * 注意：如果对空文本输入退格字符，文本继续为空。
	 * 
	 * 示例 1：
	 * 输入：S = "ab#c", T = "ad#c"
	 * 输出：true
	 * 解释：S 和 T 都会变成 “ac”。
	 * 示例 2：
	 * 输入：S = "ab##", T = "c#d#"
	 * 输出：true
	 * 解释：S 和 T 都会变成 “”。
	 * 示例 3：
	 * 输入：S = "a##c", T = "#a#c"
	 * 输出：true
	 * 解释：S 和 T 都会变成 “c”。
	 * 示例 4：
	 * 输入：S = "a#c", T = "b"
	 * 输出：false
	 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
	 * 
	 * 提示：
	 * 1 <= S.length <= 200
	 * 1 <= T.length <= 200
	 * S 和 T 只含有小写字母以及字符 '#'。
	 * 
	 * 进阶：
	 * 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
	 */

	@Override
	public String process() {
		String str = "ab#c";
		String str1 = "ad#c";
		// ' ' +/- w 3
		return String.valueOf(backspaceCompareStack(str, str1));
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		String str = "   -42";
		return String.valueOf(backspaceCompare(str, str));
	}

	public static void main(String[] args) {
		Result_844 r = new Result_844();
		System.out.println(r.process());
	}

	public boolean backspaceCompare(String S, String T) {
		int i = S.length() - 1;
		int j = T.length() - 1;

		int skipS = 0;
		int skipT = 0;

		while (i >= 0 || j >= 0) {
			while (i >= 0) {
				if (S.charAt(i) == '#') {
					skipS++;
					i--;
				} else if (skipS > 0) {
					skipS--;
					i--;
				} else {
					break;
				}
			}
			while (j >= 0) {
				if (T.charAt(j) == '#') {
					skipT++;
					j--;
				} else if (skipT > 0) {
					skipT--;
					j--;
				} else {
					break;
				}
			}

			if (i >= 0 && j >= 0) {
				if (S.charAt(i) != T.charAt(j)) {
					return false;
				}
			} else {
				if (i >= 0 || j >= 0) {
					return false;
				}
			}
			i--;
			j--;
		}
		return true;
	}

	public boolean backspaceCompareStack(String S, String T) {
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();

		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == '#') {
				if (sb1.length() > 0) {
					sb1.deleteCharAt(sb1.length() - 1);
				}
				continue;
			}
			sb1.append(S.charAt(i));
		}

		for (int i = 0; i < T.length(); i++) {
			if (T.charAt(i) == '#') {
				if (sb2.length() > 0) {
					sb2.deleteCharAt(sb2.length() - 1);
				}
				continue;
			}
			sb2.append(T.charAt(i));
		}
		return sb1.toString().equals(sb2.toString());
	}
}
