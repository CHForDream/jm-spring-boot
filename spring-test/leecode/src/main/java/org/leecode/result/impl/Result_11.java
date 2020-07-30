package org.leecode.result.impl;

import java.util.HashMap;
import java.util.Map;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_11 implements IResult {

	/**
	 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
	 * 
	 * 说明：你不能倾斜容器，且 n 的值至少为 2。
	 * 
	 * 示例：
	 * 输入：[1,8,6,2,5,4,8,3,7]
	 * 输出：49
	 */

	@Override
	public String process() {
		int[] nums = { 1, 2, 3, 4 };
		return JSON.toJSONString(maxArea(nums));
	}

	@Override
	public String gerneral() {
		int[] nums = { 1, 2, 3, 4 };
		int target = 3;
		return JSON.toJSONString(gerneral(nums, target));
	}

	// 双指针
	private int maxArea(int[] height) {
		int result = 0;
		int left = 0;
		int right = height.length - 1;
		while (left < right) {
			int a = height[left];
			int b = height[right];
			int tmp = Math.min(a, b) * (right - left);
			result = Math.max(tmp, result);
			if (a <= b) {
				left++;
			} else {
				right--;
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
