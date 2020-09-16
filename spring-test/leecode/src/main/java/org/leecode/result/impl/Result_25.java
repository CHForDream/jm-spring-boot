package org.leecode.result.impl;

import org.leecode.Utils.ListNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_25 implements IResult {
	/**
	 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
	 * k 是一个正整数，它的值小于或等于链表的长度。
	 * 
	 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
	 * 
	 * 示例：
	 * 
	 * 给你这个链表：1->2->3->4->5
	 * 
	 * 当 k = 2 时，应当返回: 2->1->4->3->5
	 * 
	 * 当 k = 3 时，应当返回: 3->2->1->4->5
	 * 
	 * 
	 * 说明：
	 * 
	 * 你的算法只能使用常数的额外空间。
	 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
	 */
	public static void main(String[] args) {
		Result_25 e = new Result_25();
		e.process();
//		e.gerneral();
	}

	private ListNode l1 = null;

	public Result_25() {
		l1 = new ListNode(2);
		{
			ListNode node1 = new ListNode(3);
			ListNode node2 = new ListNode(4);
			ListNode node3 = new ListNode(5);
			ListNode node4 = new ListNode(10);
			l1.next = node1;
			node1.next = node2;
			node2.next = node3;
			node3.next = node4;
		}
	}

	@Override
	public String process() {
		long cur = System.currentTimeMillis();

		ListNode re = reverseKGroup(l1, 3);

		System.out.println(getList(re) + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(re);
	}

	@Override
	public String gerneral() {
		long cur = System.currentTimeMillis();

		ListNode re = reverseKGroup(l1, 3);

		System.out.println(getList(re) + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(re);
	}

	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode dummy = new ListNode();
		dummy.next = head;
		ListNode pre = dummy;
		ListNode end = dummy;

		while (head != null) {
			for (int i = 0; i < k && end != null; i++)
				end = end.next;
			if (end == null)
				break;
			ListNode start = pre.next;
			ListNode next = end.next;
			end.next = null;
			pre.next = reverse(start);
			start.next = next;

			pre = start;
			end = start;
		}

		return dummy.next;
	}

	private ListNode reverse(ListNode head) {
		ListNode end = null;
		ListNode prev = end;
		while (head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}

	private String getList(ListNode list) {
		String res = "";
		while (list != null) {
			res += list.val;
			res += "->";
			list = list.next;
		}
		return res;
	}
}
