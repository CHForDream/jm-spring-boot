package org.leecode.result.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_1356 implements IResult {

	/**
	 * 1356. 根据数字二进制下 1 的数目排序
	 * 
	 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
	 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
	 * 请你返回排序后的数组。
	 * 
	 * 示例 1：
	 * 输入：arr = [0,1,2,3,4,5,6,7,8]
	 * 输出：[0,1,2,4,8,3,5,6,7]
	 * 解释：[0] 是唯一一个有 0 个 1 的数。
	 * [1,2,4,8] 都有 1 个 1 。
	 * [3,5,6] 有 2 个 1 。
	 * [7] 有 3 个 1 。
	 * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
	 * 示例 2：
	 * 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
	 * 输出：[1,2,4,8,16,32,64,128,256,512,1024]
	 * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
	 * 示例 3：
	 * 输入：arr = [10000,10000]
	 * 输出：[10000,10000]
	 * 示例 4：
	 * 输入：arr = [2,3,5,7,11,13,17,19]
	 * 输出：[2,3,5,17,7,11,13,19]
	 * 示例 5：
	 * 输入：arr = [10,100,1000,10000]
	 * 输出：[10,100,10000,1000]
	 * 
	 * 提示：
	 * 1 <= arr.length <= 500
	 * 0 <= arr[i] <= 10^4
	 */

	private int[] nums = { 1, 2, 5, 3, 3 };

	@Override
	public String process() {
		return JSON.toJSONString(sortByBits1(nums));
	}

	@Override
	public String gerneral() {
		return JSON.toJSONString(sortByBits(nums));
	}

	public static void main(String[] args) {
		Result_1356 r = new Result_1356();
		System.out.println(r.process());
	}

	public int[] sortByBits(int[] arr) {
		int[] bit = new int[10001];
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			bit[arr[i]] = getBit(arr[i]);
			list.add(arr[i]);
		}

		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer x, Integer y) {
				if (bit[x] != bit[y]) {
					return bit[x] - bit[y];
				} else {
					return x - y;
				}
			}
		});

		for (int i = 0; i < arr.length; ++i) {
			arr[i] = list.get(i);
		}
		return arr;
	}

	public int[] sortByBits1(int[] arr) {
		int length = arr.length;

		/*
		 * 根据 1的个数 和 当前数值，存储 每一个数字：
		 * 此处是本题解的精髓：1的个数权值最大，其次是本身的值，方便之后的 还原和排序
		 */
		for (int i = 0; i < length; i++) {
			arr[i] = Integer.bitCount(arr[i]) * 100000 + arr[i];
		}

		/*
		 * 将 存储的数字，还原成最初始的数字，并根据 1的个数 和 当前数值 排序
		 * 
		 */
		Arrays.sort(arr);
		for (int i = 0; i < length; i++) {
			arr[i] %= 100000;
		}
		return arr;
	}

	private int getBit(int x) {
		int count = 0;
		while (x != 0) {
			count += x & 1;
			x = x >> 1;
		}
		return count;
	}
}
