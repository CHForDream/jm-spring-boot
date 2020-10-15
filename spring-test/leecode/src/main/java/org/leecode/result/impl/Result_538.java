package org.leecode.result.impl;

import org.leecode.Utils.TreeNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_538 implements IResult {
	/**
	 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
	 * 
	 * 例如：
	 * 输入: 原始二叉搜索树:
	 * 5
	 * / \
	 * 2 13
	 * 
	 * 输出: 转换为累加树:
	 * 18
	 * / \
	 * 20 13
	 */
	@Override
	public String process() {
		TreeNode node = new TreeNode(0);
		return JSON.toJSONString(convertBST(node));
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	private int sum = 0;

	public TreeNode convertBST(TreeNode root) {
		if (root != null) {
			convertBST(root.right);
			sum += root.val;
			root.val = sum;
			convertBST(root.left);
		}
		return root;
	}
}
