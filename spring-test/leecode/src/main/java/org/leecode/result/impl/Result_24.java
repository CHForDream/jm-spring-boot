package org.leecode.result.impl;

import org.leecode.Utils.ListNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_24 implements IResult {

	/**
	 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
	 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
	 * 
	 * 示例:
	 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
	 */
	public static void main(String[] args) {
		Result_24 e = new Result_24();
		e.process();
		e.gerneral();
	}

	@Override
	public String process() {
		long cur = System.currentTimeMillis();

		ListNode l1 = new ListNode(2);
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
		ListNode re = swapPairs(l1);
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
		ListNode l1 = new ListNode(2);
		{
			ListNode node1 = new ListNode(3);
			ListNode node2 = new ListNode(4);
			ListNode node3 = new ListNode(4);
			ListNode node4 = new ListNode(10);
			ListNode node5 = new ListNode(7);
			l1.next = node1;
			node1.next = node2;
			node2.next = node3;
			node3.next = node4;
			node4.next = node5;
		}
		ListNode re = swapPairs(l1);

		String res = "";
		while (re != null) {
			res += re.val;
			res += "->";
			re = re.next;
		}

		System.out.println(res + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(re);
	}

	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode pre = new ListNode();
		pre.next = head;
		ListNode tmp = pre;

		while (head != null && head.next != null) {
			ListNode first = head;
			ListNode second = head.next;

			tmp.next = second;
			first.next = second.next;
			second.next = first;

			tmp = first;
			head = first.next;
		}
		return pre.next;
	}

	public ListNode swapPairsGeneral(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode next = head.next;
		head.next = swapPairs(next.next);
		next.next = head;
		return next;
	}

}
