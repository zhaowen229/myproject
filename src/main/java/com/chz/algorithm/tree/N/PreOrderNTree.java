package com.chz.algorithm.tree.N;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PreOrderNTree {
	static List<Integer> res = new ArrayList<>();
	int childrenSize = 0;

	/**
	 * recursion
	 * 
	 * @param root
	 * @return
	 */
	public static List<Integer> preorder(Node root) {
		if (root != null) {
			res.add(root.val);
			if (root.children != null) {
				int size = root.children.size();
				for (int i = 0; i < size; i++) {
					preorder(root.children.get(i));
				}
			}
		}
		return res;
	}

	/**
	 * iteration
	 * 
	 * 这里选用的是队列，其实可以直接使用Stack
	 * 
	 * @param root
	 * @return
	 */
	public static List<Integer> preorderByIterator(Node root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root != null) {
			// Deque 双端队列
			Deque<Node> queue = new LinkedList<>();
			queue.offer(root);
			while (!queue.isEmpty()) {
				Node node = queue.pollLast();// 取最后一个元素（子节点最左边的节点）
				res.add(node.val);
				if (node.children != null) {
					// 处理的关键：反转子节点，保证最左边的节点，在栈顶
					Collections.reverse(node.children);
					for (Node obj : node.children) {
						queue.offer(obj);
					}
				}
			}

		}
		return res;
	}

	public static void main(String[] args) {
		// 构造N叉树
		Node firstOf_FirstChildOfroot = new Node(5);
		Node secondOf_FirstChildOfroot = new Node(6);

		List<Node> list2 = new ArrayList<Node>();
		list2.add(firstOf_FirstChildOfroot);
		list2.add(secondOf_FirstChildOfroot);

		Node FirstChildOfroot = new Node(3, list2);
		Node secondChildOfroot = new Node(2);
		Node thirdChildOfroot = new Node(4);

		List<Node> list1 = new ArrayList<Node>();
		list1.add(FirstChildOfroot);
		list1.add(secondChildOfroot);
		list1.add(thirdChildOfroot);

		Node root = new Node(1, list1);

		System.out.println("结果:" + preorder(root).toString());

		System.out.println("结果:" + preorderByIterator(root));

	}

}

class Node {
	public int val;
	public List<Node> children;

	public Node() {
	}

	public Node(int _val) {
		val = _val;
	}

	public Node(int _val, List<Node> _children) {
		val = _val;
		children = _children;
	}
}
