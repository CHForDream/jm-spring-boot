package org.leecode.result.impl;

import java.util.ArrayList;
import java.util.List;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_763 implements IResult {
	/**
	 * 763. 划分字母区间
	 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
	 * 
	 * 示例 1：
	 * 输入：S = "ababcbacadefegdehijhklij"
	 * 输出：[9,7,8]
	 * 解释：
	 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
	 * 每个字母最多出现在一个片段中。
	 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
	 * 
	 * 提示：
	 * S的长度在[1, 500]之间。
	 * S只包含小写字母 'a' 到 'z' 。
	 */

	private String str = "ababcbacadefegdehijhklij";

	@Override
	public String process() {
		return JSON.toJSONString(partitionLabels(str));
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Integer> partitionLabels(String S) {
		List<Integer> list = new ArrayList<Integer>();

		int[] last = new int[26];
		for (int i = 0; i < S.length(); i++) {
			last[S.charAt(i) - 'a'] = i;
		}
		int start = 0;
		int end = 0;
		for (int i = 0; i < S.length(); i++) {
			end = Math.max(end, last[S.charAt(i)]);
			if (end == i) {
				list.add(end - start + 1);
				start = end + 1;
			}
		}
		return list;
	}
}
