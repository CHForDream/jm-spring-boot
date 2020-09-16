package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_31 implements IResult {
	/**
	 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
	 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
	 * 必须原地修改，只允许使用额外常数空间。
	 * 
	 * 这个属于找一个全排列下 下一个排列
	 * 
	 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
	 * 1,2,3 → 1,3,2
	 * 3,2,1 → 1,2,3
	 * 1,1,5 → 1,5,1
	 */
	public static void main(String[] args) {
		Result_31 e = new Result_31();
		e.process();
		e.gerneral();
	}

	private int[] nums = { 1, 3, 5, 4, 3 };

	@Override
	public String process() {
		long ti = System.currentTimeMillis();
		nextPermutation(nums);
		System.out.println(JSON.toJSONString(nums) + " process time: " + (System.currentTimeMillis() - ti));
		return JSON.toJSONString(nums);
	}

	@Override
	public String gerneral() {
		long ti = System.currentTimeMillis();
		nextPermutation(nums);
		System.out.println(nums.toString() + " process time: " + (System.currentTimeMillis() - ti));
		return JSON.toJSONString(nums);
	}

	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length < 3) {
			return;
		}
		int i = nums.length - 2;
		while (i >= 0 && nums[i] >= nums[i + 1]) {
			i--;
		}
		int j = nums.length - 1;
		if (i >= 0) {
			while (j >= 0 && nums[j] <= nums[i]) {
				j--;
			}

			int tmp = nums[j];
			nums[j] = nums[i];
			nums[i] = tmp;
		}

		j = nums.length - 1;
		i++;
		while (i < j) {
			int temp = nums[j];
			nums[j] = nums[i];
			nums[i] = temp;

			i++;
			j--;
		}
	}
}
