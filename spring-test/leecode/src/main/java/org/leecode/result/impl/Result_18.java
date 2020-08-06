package org.leecode.result.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_18 implements IResult {

	/**
	 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
	 * 注意：
	 * 答案中不可以包含重复的四元组。
	 * 示例：
	 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
	 * 满足要求的四元组集合为：
	 * [
	 * [-1, 0, 0, 1],
	 * [-2, -1, 1, 2],
	 * [-2, 0, 0, 2]
	 * ]
	 */
	int[] nums = { 1, 0, -1, 0, -2, 2 };
	private int target = 0;

	public static void main(String[] args) {
		Result_18 e = new Result_18();
		e.process();
		e.gerneral();
//		System.out.println(e.process());
//		System.out.println(e.gerneral());
	}

	@Override
	public String process() {
		long cur = System.currentTimeMillis();
		List<List<Integer>> re = fourSum(nums, target);
		System.out.println("process size: " + re + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(re);
	}

	@Override
	public String gerneral() {
		long cur = System.currentTimeMillis();
		List<List<Integer>> re = fourSum(nums, target);
		System.out.println("gerneral size: " + re + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(re);
	}

	private List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int i = 0; i < nums.length; i++) {
			if (i != 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			for (int j = i + 1; j < nums.length; j++) {
				if (j != i + 1 && nums[j] == nums[j - 1]) {
					continue;
				}
				int k = j + 1;
				int w = nums.length - 1;

				int two = nums[i] + nums[j];
				while (k < w) {
					int sum = two + nums[k] + nums[w];
					if (sum > target) {
						w--;
						while (k < w && nums[w] == nums[w + 1])
							w--;
					} else if (sum < target) {
						k++;
						while (k < w && nums[k] == nums[k - 1])
							k++;
					} else if (sum == target) {
						List<Integer> single = new ArrayList<Integer>();
						single.add(nums[i]);
						single.add(nums[j]);
						single.add(nums[k]);
						single.add(nums[w]);
						result.add(single);
						k++;
						while (k < w && nums[k] == nums[k - 1])
							k++;
					}
				}
			}
		}
		return result;
	}
}
