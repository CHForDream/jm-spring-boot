package org.leecode.result.impl;

import org.leecode.Utils.ListNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_2 implements IResult {
	/**
	 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
	 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
	 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
	 * 
	 * 示例：
	 * 
	 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * 输出：7 -> 0 -> 8
	 * 原因：342 + 465 = 807
	 */
	@Override
	public String process() {
		ListNode l1 = new ListNode(2);
		{
			ListNode node1 = new ListNode(4);
			ListNode node2 = new ListNode(3);
			l1.next = node1;
			node1.next = node2;
		}
		ListNode l2 = new ListNode(5);
		{
			ListNode node1 = new ListNode(6);
			ListNode node2 = new ListNode(4);
			l2.next = node1;
			node1.next = node2;
		}
		ListNode no = addTwoNumbers(l1, l2);
		return JSON.toJSONString(no);
	}

	@Override
	public String gerneral() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode re = null;
		ListNode re1 = null;
		int flag = 0;
		while (true) {
			int n = 0;
			if (l1 == null && l2 == null) {
				break;
			}
			if (l1 != null) {
				n += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				n += l2.val;
				l2 = l2.next;
			}
			int x = n + flag;
			flag = x / 10;
			x = x % 10;
			ListNode tmp = new ListNode(x);
			if (re == null) {
				re = tmp;
				re1 = tmp;
			} else {
				re1.next = tmp;
				re1 = tmp;
			}
		}
		if (flag > 0) {
			ListNode tmp = new ListNode(flag);
			re1.next = tmp;
		}
		return re;
	}
}
