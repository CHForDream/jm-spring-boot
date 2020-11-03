package org.leecode.result.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_1207 implements IResult {

	/**
	 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
	 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
	 * 
	 * 示例 1：
	 * 输入：arr = [1,2,2,1,1,3]
	 * 输出：true
	 * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
	 * 示例 2：
	 * 输入：arr = [1,2]
	 * 输出：false
	 * 示例 3：
	 * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
	 * 输出：true
	 * 
	 * 提示：
	 * 1 <= arr.length <= 1000
	 * -1000 <= arr[i] <= 1000
	 */

	private int[] arr = { 1, 2 };

	@Override
	public String process() {
		return String.valueOf(uniqueOccurrences(arr));
	}

	@Override
	public String gerneral() {
		return String.valueOf(uniqueOccurrences(arr));
	}

	public boolean uniqueOccurrences(int[] arr) {
		Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
		for (int i : arr) {
			numMap.put(i, numMap.getOrDefault(i, 0) + 1);
		}

		Set<Integer> times = new HashSet<Integer>();
		for (int i : numMap.values()) {
			times.add(i);
		}
		return numMap.size() == times.size();
	}
}
