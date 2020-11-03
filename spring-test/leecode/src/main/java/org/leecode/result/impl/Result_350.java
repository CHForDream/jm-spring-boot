package org.leecode.result.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_350 implements IResult {
	/**
	 * 350. 两个数组的交集 II
	 * 给定两个数组，编写一个函数来计算它们的交集。
	 * 
	 * 示例 1：
	 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
	 * 输出：[2,2]
	 * 示例 2:
	 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
	 * 输出：[4,9]
	 * 
	 * 说明：
	 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
	 * 我们可以不考虑输出结果的顺序。
	 * 
	 * 进阶：
	 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
	 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
	 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
	 */
	public static void main(String[] args) {
		Result_350 e = new Result_350();
		e.process();
		e.gerneral();
	}

	private int[] nums = { 4, 9, 5 };
	private int[] nums1 = { 9, 4, 9, 8, 4 };

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
		int[] res = new int[Math.max(nums1.length, nums2.length)];
		int index = 0;
		Map<Integer, Integer> valueMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums1.length; i++) {
			valueMap.put(nums1[i], valueMap.getOrDefault(nums1[i], 0) + 1);
		}
		for (int i = 0; i < nums2.length; i++) {
			int count = valueMap.getOrDefault(nums2[i], 0);
			if (count <= 0)
				continue;

			res[index] = nums2[i];
			index++;
			count -= 1;
			if (count > 0) {
				valueMap.put(nums2[i], count);
			} else {
				valueMap.remove(nums2[i]);
			}
		}
		return Arrays.copyOf(res, index);
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
				res[index] = nums1[index1];
				index++;
				index1++;
				index2++;
			} else if (nums1[index1] < nums2[index2]) {
				index1++;
			} else {
				index2++;
			}
		}
		return Arrays.copyOf(res, index);
	}
}
