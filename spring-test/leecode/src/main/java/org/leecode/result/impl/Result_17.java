package org.leecode.result.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.leecode.Utils.ReadInput;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_17 implements IResult {

	/**
	 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
	 * 
	 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
	 * 
	 * 示例:
	 * 
	 * 输入："23"
	 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
	 * 说明:
	 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
	 */
	private static String in = "";

	public static void main(String[] args) {
		Result_17 e = new Result_17();
		while (true) {
			in = ReadInput.getInputString();
			System.out.println(e.process());
		}
	}

	private Map<String, String> phone = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("2", "abc");
			put("3", "def");
			put("4", "ghi");
			put("5", "jkl");
			put("6", "mno");
			put("7", "pqrs");
			put("8", "tuv");
			put("9", "wxyz");
		}
	};

	private List<String> result = new ArrayList<String>();

	@Override
	public String process() {
		long cur = System.currentTimeMillis();
		List<String> re = letterCombinations(in);
		System.out.println("process size: " + re.size() + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(re);
	}

	@Override
	public String gerneral() {
		long cur = System.currentTimeMillis();
		List<String> re = letterCombinations(in);
		System.out.println("gerneral size: " + re.size() + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(re);
	}

	private List<String> letterCombinations(String digits) {
		letterCombinations("", digits);
		return result;
	}

	private void letterCombinations(String combines, String digits) {
		if (digits.length() == 0) {
			result.add(combines);
			return;
		}
		String digit = digits.substring(0, 1);
		String letters = phone.get(digit);
		for (int i = 0; i < letters.length(); i++) {
			String letter = letters.substring(i, i + 1);
			this.letterCombinations(combines + letter, digits.substring(1));
		}
	}
}
