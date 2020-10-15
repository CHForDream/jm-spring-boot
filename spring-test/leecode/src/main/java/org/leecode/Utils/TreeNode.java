package org.leecode.Utils;

public class TreeNode {

	public static int treed = 0;

	public int val;
	public TreeNode left;
	public TreeNode right;
	public TreeNode next;

	public TreeNode(int x) {
		val = x;
	}

	public void toString(StringBuilder sb) {
		treed = 0;
		sb.append(val);
		treed++;
		if (left != null || right != null) {
			sb.append('\n');
		}
		if (left != null) {
			left.toString(sb);
		}
		if (right != null) {
			for (int i = 0; i < treed; i++)
				sb.append(" ");
			right.toString(sb);
		}
	}
}
