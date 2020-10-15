package org.leecode.result.impl;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_684 implements IResult {
	/**
	 * 在本问题中, 树指的是一个连通且无环的无向图。
	 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
	 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
	 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
	 * 
	 * 示例 1：
	 * 输入: [[1,2], [1,3], [2,3]]
	 * 输出: [2,3]
	 * 解释: 给定的无向图为:
	 * 1
	 * / \
	 * 2 - 3
	 * 
	 * 示例 2：
	 * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
	 * 输出: [1,4]
	 * 解释: 给定的无向图为:
	 * 5 - 1 - 2
	 * | |
	 * 4 - 3
	 * 
	 * 注意:
	 * 输入的二维数组大小在 3 到 1000。
	 * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
	 */

	private int[][] inputs = new int[][] { { 1, 2 }, { 1, 4 }, { 3, 2 }, { 4, 3 } };

	public static void main(String[] args) {
		Result_684 r = new Result_684();
		System.out.println(r.process());
	}

	@Override
	public String process() {
		return JSON.toJSONString(findRedundantConnection(inputs));
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	private int[] result = new int[] { -1, -1 };

	private int find(int[] fa, int x) {
		return x == fa[x] ? x : (fa[x] = find(fa, fa[x]));
	}

	private void merge(int[] fa, int i, int j) {
		int x = find(fa, i);
		int y = find(fa, j);
		if (x != y) {
			fa[x] = y;
		} else {
			// 如果父节点相同 说明两个节点是连通的边节点 那么就记录
			result[0] = i;
			result[1] = j;
		}
	}

	public int[] findRedundantConnection(int[][] edges) {
		int[] fa = new int[edges.length + 1];
		for (int i = 0; i < fa.length; i++) {
			fa[i] = i;
		}
		for (int[] edge : edges) {
			merge(fa, edge[0], edge[1]);
		}
		return result;
	}
}
