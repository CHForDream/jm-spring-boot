package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_66 implements IResult {
	/**
	 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
	 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
	 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
	 * 
	 * 示例 1:
	 * 输入: [1,2,3]
	 * 输出: [1,2,4]
	 * 解释: 输入数组表示数字 123。
	 * 
	 * 示例 2:
	 * 输入: [4,3,2,1]
	 * 输出: [4,3,2,2]
	 * 解释: 输入数组表示数字 4321。
	 */

	private static final Logger log = LoggerFactory.getLogger(Result_66.class);
	private int[] digit = new int[] { 1, 2, 3 };

	public static void main(String[] args) {
		Result_66 r = new Result_66();
		System.out.println(r.process());
	}

	@Override
	public String process() {
		String s = "PAYPALISHIRING";
		return JSON.toJSONString(plusOne(digit));
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] plusOne(int[] digits) {
		if (digits == null || digits.length == 0) {
			return digits;
		}

		for (int i = digits.length - 1; i >= 0; i--) {
			digits[i]++;
			digits[i] = digits[i] % 10;
			if (digits[i] != 0) {
				return digits;
			}
		}
		digits = new int[digits.length + 1];
		digits[0] = 1;
		return digits;
	}
}
