package org.leecode.result.impl;

import java.util.ArrayList;
import java.util.List;

import org.leecode.Utils.ListNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_234 implements IResult {

	/**
	 * 234. 回文链表
	 * 请判断一个链表是否为回文链表。
	 * 示例 1:
	 * 输入: 1->2
	 * 输出: false
	 * 示例 2:
	 * 输入: 1->2->2->1
	 * 输出: true
	 * 进阶：
	 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
	 */

	private static ListNode l1 = new ListNode(-129);
	static {
		ListNode node1 = new ListNode(-129);
		l1.next = node1;
	}

	public static void main(String[] args) {
		Result_234 e = new Result_234();
		System.out.println(e.process());
	}

	@Override
	public String process() {
		return JSON.toJSONString(isPalindrome(l1));
	}

	@Override
	public String gerneral() {
		return JSON.toJSONString(isPalindrome(l1));
	}

	public boolean isPalindrome(ListNode head) {
		List<Integer> arrays = new ArrayList<Integer>();
		ListNode node = head;
		while (node != null) {
			arrays.add(node.val);
			node = node.next;
		}

		int i = 0;
		int j = arrays.size() - 1;
		while (i < j) {
			if (arrays.get(i).intValue() != arrays.get(j).intValue()) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
}
