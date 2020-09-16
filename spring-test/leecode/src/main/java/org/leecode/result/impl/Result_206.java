package org.leecode.result.impl;

import org.leecode.Utils.ListNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_206 implements IResult {

	/**
	 * 反转一个单链表。
	 * 
	 * 示例:
	 * 
	 * 输入: 1->2->3->4->5->NULL
	 * 输出: 5->4->3->2->1->NULL
	 * 进阶:
	 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
	 */
	public static void main(String[] args) {
		Result_206 e = new Result_206();
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
		ListNode re = reverseList(l1);
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
		ListNode re = reverseList(l1);

		String res = "";
		while (re != null) {
			res += re.val;
			res += "->";
			re = re.next;
		}

		System.out.println(res + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(re);
	}

	public ListNode reverseList(ListNode head) {
		ListNode prev = null;
		ListNode cur = head;
		while (cur != null) {
			ListNode tmp = cur.next;
			cur.next = prev;
			prev = cur;
			cur = tmp;
		}
		return prev;
	}

	public ListNode reverseList1(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode p = reverseList1(head.next);
		head.next.next = head;
		head.next = null;
		return p;
	}
}
