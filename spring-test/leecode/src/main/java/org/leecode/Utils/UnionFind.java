package org.leecode.Utils;

/**
 * 并查集
 */
public class UnionFind {
	private int[] acs;

	public UnionFind(int n) {
		acs = new int[n];
		for (int i = 0; i < acs.length; i++) {
			acs[i] = i;
		}
	}

	public void union(int i, int j) {
		int x = find(i);
		int y = find(j);
		if (x != y) {
			acs[i] = y;
		}
	}

	public int find(int x) {
		return x == acs[x] ? x : (acs[x] = find(acs[x]));
	}

	public int getUnionCount() {
		int count = 0;
		for (int i = 0; i < acs.length; i++) {
			if (acs[i] == i) {
				count++;
			}
		}
		return count;
	}
}
