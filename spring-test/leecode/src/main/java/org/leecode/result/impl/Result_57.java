package org.leecode.result.impl;

import java.util.ArrayList;
import java.util.List;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_57 implements IResult {
	/**
	 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
	 * 示例 1：
	 * 输入: "babad"
	 * 输出: "bab"
	 * 注意: "aba" 也是一个有效答案。
	 * 示例 2：
	 * 输入: "cbbd"
	 * 输出: "bb"
	 */

	private int[][] nums = new int[][] { { 0, 1 }, { 1, 3 }, { 6, 9 } };
	private int[] input = new int[] { 2, 7 };

	@Override
	public String process() {
		return JSON.toJSONString(insert(nums, input));
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		Result_57 r = new Result_57();
		System.out.println(JSON.toJSONString(r.process()));

	}

	public int[][] insert(int[][] intervals, int[] newInterval) {
		List<int[]> resList = new ArrayList<int[]>();
		int left = newInterval[0];
		int right = newInterval[1];
		boolean placed = false;
		for (int[] r : intervals) {
			if (r[0] > right) {
				if (!placed) {
					resList.add(new int[] { left, right });
					placed = true;
				}
				resList.add(r);
			} else if (r[1] < left) {
				resList.add(r);
			} else {
				left = Math.min(r[0], left);
				right = Math.max(r[1], right);
			}
		}
		if (!placed) {
			resList.add(new int[] { left, right });
		}
		int[][] res = new int[resList.size()][2];
		for (int i = 0; i < resList.size(); i++) {
			res[i] = resList.get(i);
		}
		return res;
	}
}
