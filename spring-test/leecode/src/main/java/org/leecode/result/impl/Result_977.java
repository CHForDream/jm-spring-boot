package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_977 implements IResult {
	/**
	 * 977. 有序数组的平方
	 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
	 * 
	 * 示例 1：
	 * 输入：[-4,-1,0,3,10]
	 * 输出：[0,1,9,16,100]
	 * 示例 2：
	 * 输入：[-7,-3,2,3,11]
	 * 输出：[4,9,9,49,121]
	 * 
	 * 提示：
	 * 1 <= A.length <= 10000
	 * -10000 <= A[i] <= 10000
	 * A 已按非递减顺序排序。
	 */
	private int[] a = new int[] { 0, 1, 2, 3 };

	@Override
	public String process() {
		// ' ' +/- w 3
		return String.valueOf(sortedSquares(a));
	}

	@Override
	public String gerneral() {
		return String.valueOf(sortedSquares(a));
	}

	/**
	 * 这个方法比较绕
	 * 使用双指针 一个指向数组头 一个指向数组结尾 排序的话因为无论如何最大的值出现在数组的开头或者是结尾 所以从最大的那个数开始 然后递减排序好的数组下标 俩指针相遇 那么就结束了
	 */
	public int[] sortedSquares(int[] A) {
		if (A == null || A.length == 0) {
			return A;
		}
		int n = A.length;
		int[] res = new int[n];
		for (int i = 0, j = n - 1, pos = n - 1; i <= j;) {
			int iPower = A[i] * A[i];
			int jPower = A[j] * A[j];
			if (A[i] * A[i] > A[j] * A[j]) {
				res[pos] = iPower;
				i++;
			} else {
				res[pos] = jPower;
				j--;
			}
			pos--;
		}
		return res;
	}
}
