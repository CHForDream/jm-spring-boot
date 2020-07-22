package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_4 implements IResult {
	/**
	 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
	 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
	 * 你可以假设 nums1 和 nums2 不会同时为空。
	 * 
	 * 示例 1:
	 * nums1 = [1, 3]
	 * nums2 = [2]
	 * 则中位数是 2.0
	 * 示例 2:
	 * nums1 = [1, 2]
	 * nums2 = [3, 4]
	 * 则中位数是 (2 + 3)/2 = 2.5
	 */
	@Override
	public String process() {
		int[] nums1 = { 1, 3 };
		int[] nums2 = { 2, 4 };
		return JSON.toJSONString(findMedianSortedArrays(nums1, nums2));
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int nSize1 = nums1.length;
		int nSize2 = nums2.length;

		boolean flag = (nSize1 + nSize2) % 2 == 1;

		int pos0 = flag ? (nSize1 + nSize2) / 2 + 1 : (nSize1 + nSize2) / 2;
		int pos1 = (nSize1 + nSize2) / 2 + 1;

		int n1 = 0;

		int n1Start = 0;
		int n2Start = 0;
		int count = 0;
		while (true) {
			int tmp = 0;
			if (n1Start == nSize1) {
				tmp = nums2[n2Start];
				n2Start++;
			} else if (n2Start == nSize2) {
				tmp = nums1[n1Start];
				n1Start++;
			} else if (nums1[n1Start] <= nums2[n2Start]) {
				tmp = nums1[n1Start];
				n1Start++;
			} else {
				tmp = nums2[n2Start];
				n2Start++;
			}
			count++;

			if (flag) {
				if (count == pos0) {
					return tmp;
				}
			} else {
				if (count == pos0) {
					n1 = tmp;
				}
				if (count == pos1) {
					return (n1 + tmp) / 2.0;
				}
			}
		}
	}
}
