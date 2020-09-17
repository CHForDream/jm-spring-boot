package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_34 implements IResult {
	/**
	 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
	 * 你的算法时间复杂度必须是 O(log n) 级别。
	 * 如果数组中不存在目标值，返回 [-1, -1]。
	 * 
	 * 示例 1:
	 * 输入: nums = [5,7,7,8,8,10], target = 8
	 * 输出: [3,4]
	 * 
	 * 示例 2:
	 * 输入: nums = [5,7,7,8,8,10], target = 6
	 * 输出: [-1,-1]
	 */
	public static void main(String[] args) {
		Result_34 e = new Result_34();
		e.process();
		e.gerneral();
	}

	private int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
	private int target = 0;

	@Override
	public String process() {
		long ti = System.currentTimeMillis();
		int[] res = searchRange(nums, target);
		System.out.println(res + " process time: " + (System.currentTimeMillis() - ti));
		return String.valueOf(res);
	}

	@Override
	public String gerneral() {
		long ti = System.currentTimeMillis();
		int[] res = searchRange(nums, target);
		System.out.println(res + " process time: " + (System.currentTimeMillis() - ti));
		return String.valueOf(res);
	}

	public int[] searchRange(int[] nums, int target) {
		int[] result = new int[] { -1, -1 };
		if (nums == null || nums.length == 0) {
			return result;
		}
		int l = doSearch(nums, target, true);
		if (l >= nums.length || nums[l] != target) {
			return result;
		}
		result[0] = l;
		result[1] = doSearch(nums, target, false);
		return result;
	}

	private int doSearch(int[] nums, int target, boolean left) {
		int l = 0;
		int r = nums.length;
		while (l < r) {
			int mid = l + (r - l) / 2;
			if (nums[mid] > target || (left && nums[mid] == target)) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
		return l;
	}
}
