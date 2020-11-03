package org.leecode.result.impl;

import java.util.ArrayList;
import java.util.List;

import org.leecode.Utils.TreeNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_146 implements IResult {

	/**
	 * 145. 二叉树的后序遍历
	 * 给定一个二叉树，返回它的 后序 遍历。
	 * 
	 * 示例:
	 * 输入: [1,null,2,3]
	 * 1
	 * \
	 * 2
	 * /
	 * 3
	 * 
	 * 输出: [3,2,1]
	 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
	 */

	private TreeNode node = new TreeNode(0);

	@Override
	public String process() {
		postorderTraversal(node);
		return JSON.toJSONString(node);
	}

	@Override
	public String gerneral() {
		return null;
	}

	public static void main(String[] args) {
		Result_146 e = new Result_146();
		System.out.println(e.process());
	}

	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		preOrder(root, result);
		return result;
	}

	private void preOrder(TreeNode root, List<Integer> result) {
		if (root == null) {
			return;
		}
		preOrder(root.left, result);
		preOrder(root.right, result);
		result.add(root.val);
	}
}
