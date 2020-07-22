package org.leecode.result.impl;

import java.util.ArrayList;
import java.util.List;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_9 implements IResult {
	/**
	 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
	 * 
	 * 示例 1:
	 * 输入: 121
	 * 输出: true
	 * 
	 * 示例 2:
	 * 输入: -121
	 * 输出: false
	 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
	 * 
	 * 示例 3:
	 * 输入: 10
	 * 输出: false
	 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
	 * 
	 * 进阶:
	 * 你能不将整数转为字符串来解决这个问题吗？
	 */

	@Override
	public String process() {
		int i = 0;

		// ' ' +/- w 3
		return String.valueOf(isPalindrome(i));
	}

	@Override
	public String gerneral() {
		int i = 14654968;
		return String.valueOf(isPalindrome1(i));
	}

	private boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}

		int n = 0;
		List<Integer> list = new ArrayList<Integer>();

		while (true) {
			int n1 = (x % 10);
			list.add(n1);
			n++;
			x = x / 10;
			if (x == 0) {
				break;
			}
		}
		int left = 0;
		int right = n - 1;
		while (left < right) {
			if (list.get(left) != list.get(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	// 数字反转一半 反转后的数据>=原来的数字代表反转了一半
	private boolean isPalindrome1(int x) {
		// 特殊情况：
		// 如上所述，当 x < 0 时，x 不是回文数。
		// 同样地，如果数字的最后一位是 0，为了使该数字为回文，
		// 则其第一位数字也应该是 0
		// 只有 0 满足这一属性
		if (x < 0 || x % 10 == 0) {
			return false;
		}
		int n = 0;
		while (x > n) {
			n = n * 10 + x % 10;
			x = x / 10;
		}
		// 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
		// 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
		// 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
		return x == n || x == n / 10;
	}
}
