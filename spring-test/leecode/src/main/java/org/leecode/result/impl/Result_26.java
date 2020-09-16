package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_26 implements IResult {
	/**
	 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
	 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
	 * 
	 * 示例 1:
	 * 给定数组 nums = [1,1,2],
	 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
	 * 你不需要考虑数组中超出新长度后面的元素。
	 * 
	 * 示例 2:
	 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
	 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
	 * 你不需要考虑数组中超出新长度后面的元素。
	 *  
	 * 说明:
	 * 为什么返回数值是整数，但输出的答案是数组呢?
	 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
	 * 你可以想象内部操作如下:
	 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
	 * int len = removeDuplicates(nums);
	 * 
	 * // 在函数里修改输入数组对于调用者是可见的。
	 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
	 * for (int i = 0; i < len; i++) {
	 *     print(nums[i]);
	 * }
	 * 
	 */
	public static void main(String[] args) {
		Result_26 e = new Result_26();
		e.process();
//		e.gerneral();
	}

	private int[] nums = new int[] { 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5 };

	@Override
	public String process() {
		int len = removeDuplicates(nums);
		String re = changeResult(len, nums);
		System.out.println(re);
		return JSON.toJSONString(re);
	}

	@Override
	public String gerneral() {
		int len = removeDuplicatesGeneral(nums);
		String re = changeResult(len, nums);
		return JSON.toJSONString(re);
	}

	public int removeDuplicates(int[] nums) {
		int len = 0;
		if (nums == null || nums.length == 0) {
			return len;
		}
		len = 1;
		int dup = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == dup) {
				continue;
			}
			dup = nums[i];
			nums[len] = nums[i];
			len++;
		}
		return len;
	}

	public int removeDuplicatesGeneral(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int len = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == nums[len]) {
				continue;
			}
			len++;
			nums[len] = nums[i];
		}
		return len + 1;
	}

	private String changeResult(int len, int[] nums) {
		String str = "";

		for (int i = 0; i < len; i++) {
			str = str + nums[i] + " ";
		}

		return str;
	}
}
