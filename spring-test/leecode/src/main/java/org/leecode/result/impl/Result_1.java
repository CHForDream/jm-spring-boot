package org.leecode.result.impl;

import java.util.HashMap;
import java.util.Map;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_1 implements IResult {

	/**
	 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
	 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
	 * 
	 * 给定 nums = [2, 7, 11, 15], target = 9
	 * 因为 nums[0] + nums[1] = 2 + 7 = 9
	 * 所以返回 [0, 1]
	 */

	@Override
	public String process() {
		int[] nums = { 1, 2, 3, 4 };
		int target = 3;
		return JSON.toJSONString(twoSum(nums, target));
	}

	@Override
	public String gerneral() {
		int[] nums = { 1, 2, 3, 4 };
		int target = 3;
		return JSON.toJSONString(gerneral(nums, target));
	}

	private int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (i == j)
					continue;
				if (nums[i] + nums[j] == target) {
					result[0] = i;
					result[1] = j;
					break;
				}
			}
		}
		return result;
	}

	private int[] gerneral(int[] nums, int target) {
		Map<Integer, Integer> valueMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			int nn = nums[i];
			if (valueMap.containsKey(target - nn)) {
				return new int[] { valueMap.get(target - nn), i };
			}
			valueMap.put(nn, i);
		}
		return null;
	}
}
