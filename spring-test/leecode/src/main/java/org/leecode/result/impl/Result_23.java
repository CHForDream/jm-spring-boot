package org.leecode.result.impl;

import org.leecode.Utils.ListNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_23 implements IResult {

	/**
	 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
	 * 
	 * 示例:
	 * 
	 * 输入:
	 * [
	 *   1->4->5,
	 *   1->3->4,
	 *   2->6
	 * ]
	 * 输出: 1->1->2->3->4->4->5->6
	 */
	public static void main(String[] args) {
		Result_23 e = new Result_23();
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

	public ListNode mergeKLists(ListNode[] lists) {
		ListNode node = null;
		for (int i = 0; i < lists.length; i++) {
			node = mergeTwoListsGeneral(node, lists[i]);
		}
		return node;
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
