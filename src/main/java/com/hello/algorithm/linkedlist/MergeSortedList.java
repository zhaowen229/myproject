package com.hello.algorithm.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class MergeSortedList {

	// leetcode:21 合并两个有序链表
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		} else if (l2 == null) {
			return l1;
		} else if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}

	}

	// leetcode:21 合并两个有序链表
	public static ListNode mergeTwoListsIterator(ListNode l1, ListNode l2) {
		ListNode prehead = new ListNode(-1);// 记录合并链表中的数据

		ListNode prev = prehead;// prev 用来迭代
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				prev.next = l1;
				l1 = l1.next;
			} else {
				prev.next = l2;
				l2 = l2.next;
			}
			prev = prev.next;
			System.out.println(prev.val);// 如果返回prev结果是不对的,当前指向的是最后一个值。通过打印也可一看出来
		}

		// 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
		prev.next = l1 == null ? l2 : l1;
		return prehead.next;

	}

	public static void printListNode(ListNode list) {
		ListNode cur = list;
		List<Integer> result = new ArrayList<>();
		while (cur != null) {
			result.add(cur.val);
			cur = cur.next;
		}
		System.out.println(result.toString());

	}

	public static void main(String args[]) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(4);

		ListNode l2 = new ListNode(3);
		l2.next = new ListNode(4);

		ListNode resultListNode = mergeTwoLists(l1, l2);

		printListNode(resultListNode);
	}

}
