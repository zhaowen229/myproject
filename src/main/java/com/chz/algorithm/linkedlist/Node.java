package com.chz.algorithm.linkedlist;

public class Node {
	Integer val;

	Node next;

	Node dummyhead = null;

	int size;

	public Node(int x) {
		val = x;
	}

	public Node add(Node node) {

		if (dummyhead == null) {
			dummyhead = node;
			size++;
		} else {
			dummyhead.next = node;
			size++;
		}

		return dummyhead;
	}

}
