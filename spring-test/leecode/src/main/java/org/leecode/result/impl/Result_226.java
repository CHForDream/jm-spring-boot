package org.leecode.result.impl;

import org.leecode.Utils.TreeNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_226 implements IResult {

	/**
	 * 翻转一棵二叉树。
	 * 
	 * 示例：
	 * 输入：
	 * 4
	 * / \
	 * 2 7
	 * / \ / \
	 * 1 3 6 9
	 * 输出：
	 * 4
	 * / \
	 * 7 2
	 * / \ / \
	 * 9 6 3 1
	 * 
	 */

	private TreeNode root = new TreeNode(0);

	public static void main(String[] args) {
		Result_226 e = new Result_226();
		e.process();
//		e.gerneral();
//		System.out.println(e.process());
//		System.out.println(e.gerneral());
	}

	@Override
	public String process() {
		long cur = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		TreeNode revert = invertTree(root);
		revert.toString(sb);
		System.out.println(sb + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(revert);
	}

	@Override
	public String gerneral() {
		long cur = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		TreeNode revert = invertTree(root);
		revert.toString(sb);
		System.out.println(sb + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(revert);
	}

	public TreeNode invertTree(TreeNode root) {

		if (root == null) {
			return root;
		}
		TreeNode tmpLeft = root.left;
		TreeNode tmpRight = root.right;

		if (tmpLeft != null || tmpRight != null) {
			TreeNode tmp = null;
			tmp = root.left;
			root.left = root.right;
			root.right = tmp;
		}
		invertTree(tmpLeft);
		invertTree(tmpRight);
		return root;
	}
}
