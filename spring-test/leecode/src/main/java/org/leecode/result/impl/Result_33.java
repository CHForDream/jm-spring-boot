package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_33 implements IResult {
	/**
	 * 
	 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
	 * 
	 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
	 * 
	 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
	 * 
	 * 你可以假设数组中不存在重复的元素。
	 * 
	 * 你的算法时间复杂度必须是 O(log n) 级别。
	 * 
	 * 示例 1:
	 * 
	 * 输入: nums = [4,5,6,7,0,1,2], target = 0
	 * 输出: 4
	 * 示例 2:
	 * 
	 * 输入: nums = [4,5,6,7,0,1,2], target = 3
	 * 输出: -1
	 */
	public static void main(String[] args) {
		Result_33 e = new Result_33();
		e.process();
		e.gerneral();
	}

	private int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
	private int target = 0;

	@Override
	public String process() {
		long ti = System.currentTimeMillis();
		int res = search(nums, target);
		System.out.println(res + " process time: " + (System.currentTimeMillis() - ti));
		return String.valueOf(res);
	}

	@Override
	public String gerneral() {
		long ti = System.currentTimeMillis();
		int res = search(nums, target);
		System.out.println(res + " process time: " + (System.currentTimeMillis() - ti));
		return String.valueOf(res);
	}

	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		if (nums.length == 1) {
			if (nums[0] == target) {
				return 0;
			}
			return -1;
		}

		int l = 0;
		int r = nums.length - 1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (nums[mid] == target) {
				return mid;
			}
			if (nums[0] <= nums[mid]) {
				if (nums[0] <= target && target < nums[mid]) {
					r = mid - 1;
				} else {
					l = mid + 1;
				}
			} else {
				if (nums[mid] < target && target <= nums[nums.length - 1]) {
					l = mid + 1;
				} else {
					r = mid - 1;
				}
			}
		}
		return -1;
	}
}
