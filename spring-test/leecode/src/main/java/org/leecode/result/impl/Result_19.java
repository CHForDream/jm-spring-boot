package org.leecode.result.impl;

import java.util.HashMap;
import java.util.Map;

import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_19 implements IResult {

	/**
	 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
	 * 
	 * 示例：
	 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
	 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
	 * 说明：
	 * 给定的 n 保证是有效的。
	 * 
	 * 进阶：
	 * 你能尝试使用一趟扫描实现吗？
	 */
	int[] nums = { 1, 0, -1, 0, -2, 2 };
	private int target = 0;

	public static void main(String[] args) {
		Result_19 e = new Result_19();
		e.process();
		e.gerneral();
//		System.out.println(e.process());
//		System.out.println(e.gerneral());
	}

	@Override
	public String process() {
		long cur = System.currentTimeMillis();

		ListNode l1 = new ListNode(2);
		{
			ListNode node1 = new ListNode(4);
			ListNode node2 = new ListNode(3);
			l1.next = node1;
			node1.next = node2;
		}

		ListNode re = removeNthFromEnd(l1, 2);

		String res = "";
		while (re != null) {
			res += re.val;
			res += "->";
			re = re.next;
		}

		System.out.println(res + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(re);
	}

	@Override
	public String gerneral() {
		long cur = System.currentTimeMillis();
		ListNode l2 = new ListNode(5);
		{
			ListNode node1 = new ListNode(6);
			ListNode node2 = new ListNode(4);
			l2.next = node1;
			node1.next = node2;
		}
		ListNode re = removeNthFromEndGernaral(l2, 2);

		String res = "";
		while (re != null) {
			res += re.val;
			res += "->";
			re = re.next;
		}

		System.out.println(res + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(re);
	}

	private ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode node = head;

		Map<Integer, ListNode> nodeMap = new HashMap<Integer, ListNode>();
		int size = 0;
		while (node != null) {
			ListNode tmp = node;
			nodeMap.put(size, tmp);
			size++;
			node = node.next;
		}

		int tartget = size - n;
		if (tartget >= 0) {
			nodeMap.get(tartget - 1).next = nodeMap.get(tartget).next;
		} else if (tartget < 0) {
			head = head.next;
		}

		return head;
	}

	private ListNode removeNthFromEndGernaral(ListNode head, int n) {
		ListNode pre = new ListNode(0);
		pre.next = head;
		ListNode start = pre;
		ListNode end = pre;
		for (int i = 0; i < n; i++) {
			start = start.next;
		}

		while (start.next != null) {
			end = end.next;
			start = start.next;
		}
		end.next = end.next.next;
		return pre.next;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

		public int getVal() {
			return val;
		}

		public void setVal(int val) {
			this.val = val;
		}

		public ListNode getNext() {
			return next;
		}

		public void setNext(ListNode next) {
			this.next = next;
		}

	}
}
