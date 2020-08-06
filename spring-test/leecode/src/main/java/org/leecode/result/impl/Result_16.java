package org.leecode.result.impl;

import java.util.Arrays;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_16 implements IResult {

	/**
	 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
	 * 
	 * 示例：
	 * 输入：nums = [-1,2,1,-4], target = 1
	 * 输出：2
	 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
	 *  
	 * 提示：
	 * 3 <= nums.length <= 10^3
	 * -10^3 <= nums[i] <= 10^3
	 * -10^4 <= target <= 10^4
	 */
	int[] nums = { 1, 1, -1, -1, 3 };
	private int target = -1;

	@Override
	public String process() {
		long cur = System.currentTimeMillis();
		int re = threeSumClosest(nums, target);
		System.out.println("process size: " + re + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(re);
	}

	@Override
	public String gerneral() {
		long cur = System.currentTimeMillis();
		int re = threeSumClosest(nums, target);
		System.out.println("gerneral size: " + re + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(re);
	}

	public static void main(String[] args) {
		Result_16 e = new Result_16();
		e.process();
		e.gerneral();
//		System.out.println(e.process());
//		System.out.println(e.gerneral());
	}

	private int threeSumClosest(int[] nums, int target) {

		Arrays.sort(nums);
		int result = nums[0] + nums[1] + nums[2];

		for (int i = 0; i < nums.length; i++) {
			// 去除重复的初始变量
			if (i != 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int j = i + 1;
			int k = nums.length - 1;
			while (j < k) {
				int sum = nums[i] + nums[j] + nums[k];
				// 如果三数相加==目标值 直接返回就好
				if (sum == target) {
					return target;
				}

				if (Math.abs(result - target) > Math.abs(sum - target)) {
					result = sum;
				}
				// 如果和比目标值大 那么将k左移
				if (sum > target) {
					int k0 = k - 1;
					while (j < k0 && nums[k0] == nums[k]) {
						k0--;
					}
					k = k0;
				} else {
					// 和比目标小那就j右移
					int j0 = j + 1;
					while (j0 < k && nums[j0] == nums[j]) {
						j0++;
					}
					j = j0;
				}
			}
		}
		return result;
	}
}
