package org.leecode.result.impl;

import java.util.ArrayList;
import java.util.List;

import org.leecode.Utils.ListNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class Result_143 implements IResult {

	/**
	 * 143. 重排链表
	 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
	 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
	 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
	 * 
	 * 示例 1:
	 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
	 * 示例 2:
	 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
	 */

	private ListNode node = new ListNode();

	@Override
	public String process() {
		reorderList(node);
		return JSON.toJSONString(node);
	}

	@Override
	public String gerneral() {
		return null;
	}

	public static void main(String[] args) {
		Result_143 e = new Result_143();
		System.out.println(e.process());
	}

	public void reorderList(ListNode head) {
		if (head == null) {
			return;
		}
		List<ListNode> list = new ArrayList<ListNode>();
		ListNode node = head;
		while (node != null) {
			list.add(node);
			node = node.next;
		}

		int i = 0;
		int j = list.size() - 1;
		while (i < j) {
			ListNode l1 = list.get(i);
			ListNode l2 = list.get(j);

			ListNode l1tmp = l1.next;
			l1.next = l2;

			i++;
			if (i == j) {
				break;
			}

			l2.next = l1tmp;
			j--;
		}
		list.get(i).next = null;
	}
}
