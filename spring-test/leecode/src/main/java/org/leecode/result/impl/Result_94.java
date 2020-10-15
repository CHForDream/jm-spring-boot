package org.leecode.result.impl;

import java.util.ArrayList;
import java.util.List;

import org.leecode.Utils.TreeNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_94 implements IResult {
	/**
	 * 给定一个二叉树，返回它的中序 遍历。
	 * 
	 * 示例:
	 * 
	 * 输入: [1,null,2,3]
	 * 1
	 * \
	 * 2
	 * /
	 * 3
	 * 
	 * 输出: [1,3,2]
	 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
	 */
	private TreeNode node = new TreeNode(0);

	@Override
	public String process() {
		// ' ' +/- w 3
		return JSON.toJSONString(inorderTraversal(node));
	}

	@Override
	public String gerneral() {
		return JSON.toJSONString(inorderTraversal(node));
	}

	private List<Integer> res = new ArrayList<Integer>();

	public List<Integer> inorderTraversal(TreeNode root) {
		dfs(root);
		return res;
	}

	private void dfs(TreeNode root) {
		if (root == null) {
			return;
		}
		dfs(root.left);
		res.add(root.val);
		dfs(root.right);
	}
}
