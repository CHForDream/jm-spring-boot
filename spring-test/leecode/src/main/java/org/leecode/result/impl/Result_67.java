package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Result_67 implements IResult {
	/**
	 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
	 * 输入为 非空 字符串且只包含数字 1 和 0。
	 * 
	 * 示例 1:
	 * 输入: a = "11", b = "1"
	 * 输出: "100"
	 * 
	 * 示例 2:
	 * 输入: a = "1010", b = "1011"
	 * 输出: "10101"
	 * 
	 * 提示：
	 * 每个字符串仅由字符 '0' 或 '1' 组成。
	 * 1 <= a.length, b.length <= 10^4
	 * 字符串如果不是 "0" ，就都不含前导零。
	 */

	private static final Logger log = LoggerFactory.getLogger(Result_67.class);
	private String a = "11";
	private String b = "111";

	public static void main(String[] args) {
		Result_67 r = new Result_67();
		System.out.println(r.process());
	}

	@Override
	public String process() {
		return addBinary(a, b);
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	public String addBinary(String a, String b) {
		if (a == null || b == null)
			return "";
		StringBuilder sum = new StringBuilder();
		int iSum = 0;

		for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
			iSum += i >= 0 ? a.charAt(i) - '0' : 0;
			iSum += j >= 0 ? b.charAt(j) - '0' : 0;
			sum.append(iSum % 2);
			iSum = iSum / 2;
		}
		if (iSum > 0)
			sum.append("1");
		return sum.reverse().toString();
	}
}
