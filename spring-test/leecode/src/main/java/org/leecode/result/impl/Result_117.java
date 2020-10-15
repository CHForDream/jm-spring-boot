package org.leecode.result.impl;

import java.util.HashMap;
import java.util.Map;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_117 implements IResult {

	/**
	 * 给定一个二叉树
	 * struct Node {
	 * int val;
	 * Node *left;
	 * Node *right;
	 * Node *next;
	 * }
	 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
	 * 初始状态下，所有 next 指针都被设置为 NULL。
	 * 
	 * 进阶：
	 * 你只能使用常量级额外空间。
	 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
	 *  
	 * 
	 * 示例：
	 * 输入：root = [1,2,3,4,5,null,7]
	 * 输出：[1,#,2,3,#,4,5,7,#]
	 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
	 * 提示：
	 * 树中的节点数小于 6000
	 * -100 <= node.val <= 100
	 */

	@Override
	public String process() {
		int[] nums = { 1, 2, 3, 4 };
		return JSON.toJSONString(maxArea(nums));
	}

	@Override
	public String gerneral() {
		int[] nums = { 1, 2, 3, 4 };
		int target = 3;
		return JSON.toJSONString(gerneral(nums, target));
	}

	// 双指针
	private int maxArea(int[] height) {
		int result = 0;
		int left = 0;
		int right = height.length - 1;
		while (left < right) {
			int a = height[left];
			int b = height[right];
			int tmp = Math.min(a, b) * (right - left);
			result = Math.max(tmp, result);
			if (a <= b) {
				left++;
			} else {
				right--;
			}
		}
		return result;
	}

	private int[] gerneral(int[] nums, int target) {
		Map<Integer, Integer> valueMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			int nn = nums[i];
			if (valueMap.containsKey(target - nn)) {
				return new int[] { valueMap.get(target - nn), i };
			}
			valueMap.put(nn, i);
		}
		return null;
	}
}
