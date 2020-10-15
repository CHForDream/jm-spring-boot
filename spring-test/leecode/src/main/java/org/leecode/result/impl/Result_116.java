package org.leecode.result.impl;

import org.leecode.Utils.TreeNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_116 implements IResult {
	/**
	 * 116. 填充每个节点的下一个右侧节点指针
	 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
	 * 
	 * struct Node {
	 * int val;
	 * Node *left;
	 * Node *right;
	 * Node *next;
	 * }
	 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
	 * 
	 * 初始状态下，所有 next 指针都被设置为 NULL。
	 */

	private TreeNode node = new TreeNode(0);

	public static void main(String[] args) {
		Result_116 r = new Result_116();
		System.out.println(r.process());
	}

	@Override
	public String process() {
		return JSON.toJSONString(connect(node));
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根节点的next为null
	 * 遍历根节点 然后将其左右节点连接起来
	 * 
	 * 接着就是通俗性的遍历每层的节点 各节点的左右孩子连接 右孩子和当前节点的下一个节点的做孩子连接
	 */
	public TreeNode connect(TreeNode root) {
		if (root == null) {
			return null;
		}

		// 最左侧的节点
		TreeNode leftMost = root;
		while (leftMost != null) {
			TreeNode head = leftMost;
			while (head != null && head.left != null) {
				head.left.next = head.right;
				if (head.next != null) {
					head.right.next = head.next.left;
				}
				head = head.next;
			}
			leftMost = leftMost.left;
		}
		return root;
	}
}
