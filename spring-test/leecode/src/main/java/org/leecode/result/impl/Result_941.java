package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_941 implements IResult {
	/**
	 * 942. 增减字符串匹配
	 * 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。
	 * 返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
	 * 如果 S[i] == "I"，那么 A[i] < A[i+1]
	 * 如果 S[i] == "D"，那么 A[i] > A[i+1]
	 * 
	 * 示例 1：
	 * 输入："IDID"
	 * 输出：[0,4,1,3,2]
	 * 示例 2：
	 * 输入："III"
	 * 输出：[0,1,2,3]
	 * 示例 3：
	 * 输入："DDI"
	 * 输出：[3,2,0,1]
	 * 
	 * 提示：
	 * 1 <= S.length <= 10000
	 * S 只包含字符 "I" 或 "D"。
	 */

	private String str = "DDID";

	@Override
	public String process() {
		// ' ' +/- w 3
		return JSON.toJSONString(diStringMatch(str));
	}

	@Override
	public String gerneral() {
		return JSON.toJSONString(diStringMatch(str));
	}

	public static void main(String[] args) {
		Result_941 r = new Result_941();
		System.out.println(r.process());
	}

	public int[] diStringMatch(String S) {
		int n = S.length();
		int lo = 0;
		int hi = n;
		int[] res = new int[n + 1];

		for (int i = 0; i < n; i++) {
			if (S.charAt(i) == 'I') {
				res[i] = lo++;
			} else if (S.charAt(i) == 'D') {
				res[i] = hi--;
			}
		}
		res[n] = lo;
		return res;
	}
}
