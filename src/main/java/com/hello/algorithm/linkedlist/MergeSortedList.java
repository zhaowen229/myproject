package com.hello.algorithm.linkedlist;

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

	public static void printListNode(ListNode list) {
		ListNode cur = list;
		while (cur != null) {
			System.out.println(cur.val);
			cur = cur.next;
		}
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
