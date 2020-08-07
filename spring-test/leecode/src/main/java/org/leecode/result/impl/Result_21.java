package org.leecode.result.impl;

import org.leecode.Utils.ListNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_21 implements IResult {

	/**
	 * 
	 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
	 * 示例：
	 * 
	 * 输入：1->2->4, 1->3->4
	 * 输出：1->1->2->3->4->4
	 */
	int[] nums = { 1, 0, -1, 0, -2, 2 };
	private int target = 0;

	public static void main(String[] args) {
		Result_21 e = new Result_21();
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
			ListNode node1 = new ListNode(3);
			ListNode node2 = new ListNode(4);
			l1.next = node1;
			node1.next = node2;
		}
		ListNode l2 = new ListNode(3);
		{
			ListNode node1 = new ListNode(4);
			ListNode node2 = new ListNode(5);
			l2.next = node1;
			node1.next = node2;
		}
		ListNode re = mergeTwoLists(l1, l2);

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
			l1.next = node1;
			node1.next = node2;
		}
		ListNode l2 = new ListNode(3);
		{
			ListNode node1 = new ListNode(4);
			ListNode node2 = new ListNode(10);
			l2.next = node1;
			node1.next = node2;
		}
		ListNode re = mergeTwoListsGeneral(l2, l1);

		String res = "";
		while (re != null) {
			res += re.val;
			res += "->";
			re = re.next;
		}

		System.out.println(res + " time: " + (System.currentTimeMillis() - cur));
		return JSON.toJSONString(re);
	}

	private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		else if (l2 == null)
			return l1;
		else if (l1.val <= l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}

	private ListNode mergeTwoListsGeneral(ListNode l1, ListNode l2) {
		ListNode node = new ListNode();

		ListNode l1Node = l1;
		ListNode l2Node = l2;
		ListNode flag = node;
		while (l1Node != null && l2Node != null) {
			if (l1Node.val <= l2Node.val) {
				flag.next = l1Node;
				l1Node = l1Node.next;
			} else {
				flag.next = l2Node;
				l2Node = l2Node.next;
			}
			flag = flag.next;
		}
		flag.next = l1Node == null ? l2Node : l1Node;
		return node.next;
	}
}
