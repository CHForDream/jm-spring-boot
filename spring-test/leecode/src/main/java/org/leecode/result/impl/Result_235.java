package org.leecode.result.impl;

import java.util.ArrayList;
import java.util.List;

import org.leecode.Utils.TreeNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_235 implements IResult {

	/**
	 * 235. 二叉搜索树的最近公共祖先
	 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
	 * 
	 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
	 * 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5]
	 * 
	 * 示例 1:
	 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
	 * 输出: 6
	 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
	 * 示例 2:
	 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
	 * 输出: 2
	 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
	 * 说明:
	 * 所有节点的值都是唯一的。
	 * p、q 为不同节点且均存在于给定的二叉搜索树中。
	 */

	public static void main(String[] args) {
		Result_235 e = new Result_235();
		System.out.println(e.process());
	}

	@Override
	public String process() {
		return JSON.toJSONString(lowestCommonAncestor(new TreeNode(0), new TreeNode(0), new TreeNode(0)));
	}

	@Override
	public String gerneral() {
		return JSON.toJSONString(lowestCommonAncestor(new TreeNode(0), new TreeNode(0), new TreeNode(0)));
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		List<TreeNode> arr1 = getPath(root, p);
		List<TreeNode> arr2 = getPath(root, q);

		TreeNode node = null;
		for (int i = 0; i < arr1.size() && i < arr2.size(); i++) {
			if (arr1.get(i) == arr2.get(i)) {
				node = arr1.get(i);
			}
		}
		return node;
	}

	public List<TreeNode> getPath(TreeNode root, TreeNode node) {
		List<TreeNode> arr = new ArrayList<TreeNode>();
		TreeNode tmp = root;
		while (tmp != node) {
			arr.add(tmp);
			if (node.val > tmp.val) {
				tmp = tmp.right;
			} else {
				tmp = tmp.left;
			}
		}
		arr.add(tmp);
		return arr;
	}
}
