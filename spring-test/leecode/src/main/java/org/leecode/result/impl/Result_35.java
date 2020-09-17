package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_35 implements IResult {
	/**
	 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
	 * 你可以假设数组中无重复元素。
	 * 
	 * 示例 1:
	 * 输入: [1,3,5,6], 5
	 * 输出: 2
	 * 
	 * 示例 2:
	 * 输入: [1,3,5,6], 2
	 * 输出: 1
	 * 
	 * 示例 3:
	 * 输入: [1,3,5,6], 7
	 * 输出: 4
	 * 
	 * 示例 4:
	 * 输入: [1,3,5,6], 0
	 * 输出: 0
	 */
	public static void main(String[] args) {
		Result_35 e = new Result_35();
		e.process();
		e.gerneral();
	}

	private int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
	private int target = 0;

	@Override
	public String process() {
		long ti = System.currentTimeMillis();
		int res = searchInsert(nums, target);
		System.out.println(res + " process time: " + (System.currentTimeMillis() - ti));
		return String.valueOf(res);
	}

	@Override
	public String gerneral() {
		long ti = System.currentTimeMillis();
		int res = searchInsert(nums, target);
		System.out.println(res + " process time: " + (System.currentTimeMillis() - ti));
		return String.valueOf(res);
	}

	public int searchInsert(int[] nums, int target) {
		int result = nums.length;

		int l = 0;
		int r = result - 1;

		while (l <= r) {
			int mid = l + ((r - l) >> 1);
			if (nums[mid] >= target) {
				r = mid - 1;
				result = mid;
			} else {
				l = mid + 1;
			}
		}
		return result;
	}
}
