package org.leecode.result.impl;

import org.leecode.Utils.TreeNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_530 implements IResult {
	/**
	 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
	 * 
	 * 示例：
	 * 输入：
	 * 1
	 * \
	 * 3
	 * /
	 * 2
	 * 输出：
	 * 1
	 * 解释：
	 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
	 * 
	 * 提示：
	 * 树中至少有 2 个节点。
	 * 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同
	 */
	@Override
	public String process() {
		TreeNode node = new TreeNode(0);
		return JSON.toJSONString(getMinimumDifference(node));
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	private int res = Integer.MAX_VALUE;
	private TreeNode pre = null;

	public int getMinimumDifference(TreeNode root) {
		convertBST(root);
		return res;
	}

	public void convertBST(TreeNode root) {
		if (root == null)
			return;
		if (root != null) {
			convertBST(root.left);

			if (pre != null) {
				res = Math.min(res, root.val - pre.val);
			}
			pre = root;
			convertBST(root.right);
		}
	}
}
