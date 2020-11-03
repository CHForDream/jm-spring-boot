package org.leecode.result.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_349 implements IResult {
	/**
	 * 349. 两个数组的交集
	 * 给定两个数组，编写一个函数来计算它们的交集。
	 * 
	 * 示例 1：
	 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
	 * 输出：[2]
	 * 示例 2：
	 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
	 * 输出：[9,4]
	 * 
	 * 说明：
	 * 输出结果中的每个元素一定是唯一的。
	 * 我们可以不考虑输出结果的顺序。
	 */
	public static void main(String[] args) {
		Result_349 e = new Result_349();
		e.process();
		e.gerneral();
	}

	private int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
	private int[] nums1 = { 4, 5, 6, 7, 0, 1, 2, 9 };

	@Override
	public String process() {
		long ti = System.currentTimeMillis();
		int[] res = intersection(nums, nums1);
		System.out.println(JSON.toJSONString(res) + " process time: " + (System.currentTimeMillis() - ti));
		return JSON.toJSONString(res);
	}

	@Override
	public String gerneral() {
		long ti = System.currentTimeMillis();
		int[] res = intersection(nums, nums1);
		System.out.println(JSON.toJSONString(res) + " process time: " + (System.currentTimeMillis() - ti));
		return JSON.toJSONString(res);
	}

	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> valueMap = new HashSet<Integer>();
		Set<Integer> res1 = new HashSet<Integer>();
		for (int i = 0; i < nums1.length; i++) {
			valueMap.add(nums1[i]);
		}
		for (int i = 0; i < nums2.length; i++) {
			if (valueMap.contains(nums2[i])) {
				res1.add(nums2[i]);
			}
		}
		int[] res = new int[res1.size()];
		int index = 0;
		for (int num : res1) {
			res[index] = num;
			index++;
		}
		return res;
	}

	public int[] intersectionDouble(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int index = 0;
		int index1 = 0;
		int index2 = 0;
		int[] res = new int[Math.max(nums1.length, nums2.length)];
		while (index1 < nums1.length && index2 < nums2.length) {
			if (nums1[index1] == nums2[index2]) {
				if (index == 0 || res[index - 1] != nums1[index1]) {
					res[index] = nums1[index1];
					index++;
				}
				index1++;
				index2++;
			} else if (nums1[index1] > nums2[index2]) {
				index2++;
			} else {
				index1++;
			}
		}
		return Arrays.copyOf(res, index);
	}
}
