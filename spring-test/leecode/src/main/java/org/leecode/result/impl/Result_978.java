package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_978 implements IResult {
	/**
	 * 978. 最长湍流子数组
	 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
	 * 
	 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
	 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
	 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
	 * 
	 * 返回 A 的最大湍流子数组的长度。
	 * 
	 * 示例 1：
	 * 输入：[9,4,2,10,7,8,8,1,9]
	 * 输出：5
	 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
	 * 示例 2：
	 * 输入：[4,8,12,16]
	 * 输出：2
	 * 示例 3：
	 * 输入：[100]
	 * 输出：1
	 * 
	 * 提示：
	 * 1 <= A.length <= 40000
	 * 0 <= A[i] <= 10^9
	 */
	private int[] a = new int[] { 0, 1, 2, 3 };

	@Override
	public String process() {
		// ' ' +/- w 3
		return String.valueOf(maxTurbulenceSize(a));
	}

	@Override
	public String gerneral() {
		return String.valueOf(maxTurbulenceSize(a));
	}

	public int maxTurbulenceSize(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int res = 1;
		int n = A.length;
		int pos = 0;
		for (int i = 1; i < n; i++) {
			int c = Integer.compare(A[i - 1], A[i]);
			if (i == n - 1 || c * Integer.compare(A[i], A[i + 1]) != -1) {
				if (c != 0)
					res = Integer.max(res, i - pos + 1);
				pos = i;
			}
		}
		return res;
	}
}
