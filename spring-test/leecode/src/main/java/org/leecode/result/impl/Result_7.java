package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_7 implements IResult {
	/**
	 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
	 * 
	 * 示例 1:
	 * 输入: 123
	 * 输出: 321
	 * 
	 * 示例 2:
	 * 输入: -123
	 * 输出: -321
	 * 输入: 120
	 * 输出: 21
	 * 注意:
	 * 
	 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
	 */

	@Override
	public String process() {
		int x = 120;
		return String.valueOf(reverse(x));
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	public int reverse(int x) {
		int result = 0;
		int pop = 0;
		while (x != 0) {
			pop = x % 10;
			x = x / 10;

			if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && pop > 7)) {
				return 0;
			}
			if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && pop > 8)) {
				return 0;
			}
			result = result * 10 + pop;
		}
		return result;
	}
}
