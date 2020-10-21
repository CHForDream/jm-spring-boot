package org.leecode.result.impl;

import org.leecode.Utils.ListNode;
import org.leecode.result.IResult;
import org.springframework.stereotype.Service;

@Service
public class Result_876 implements IResult {
	/**
	 * 876. 链表的中间结点
	 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
	 * 如果有两个中间结点，则返回第二个中间结点。
	 * 
	 * 示例 1：
	 * 输入：[1,2,3,4,5]
	 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
	 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
	 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
	 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
	 * 示例 2：
	 * 输入：[1,2,3,4,5,6]
	 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
	 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
	 * 
	 * 提示：
	 * 给定链表的结点数介于 1 和 100 之间。
	 */
	private ListNode node = new ListNode();

	@Override
	public String process() {

		return String.valueOf(middleNode(node));
	}

	@Override
	public String gerneral() {
		return String.valueOf(middleNode(node));
	}

	public static void main(String[] args) {
		Result_876 r = new Result_876();
		System.out.println(r.process());
	}

	/**
	 * 快慢指针法 慢指针一次走一个 快指针一次走两个 快指针到头慢指针正好在中间
	 */
	public ListNode middleNode(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public ListNode middleNodeLoop(ListNode head) {
		int n = 0;
		ListNode cur = head;
		while (cur != null) {
			n++;
			cur = cur.next;
		}

		int k = 0;
		cur = head;
		while (k < n / 2) {
			k++;
			cur = cur.next;
		}
		return cur;
	}
}
