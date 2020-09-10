package com.hello.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

public class PreOrderBinaryTree {
	static List<Integer> resultList = new ArrayList<>();

	/**
	 * 先序遍历二叉树 recursive
	 * 
	 * @param root
	 * @return
	 */
	public static List<Integer> preorder(TreeNode root) {
		if (root == null) {
			return null;
		}
		resultList.add(root.val);
		preorder(root.left);
		preorder(root.right);

		return resultList;
	}

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);

		node1.left = new TreeNode(2);
		node1.right = new TreeNode(3);

		node1.left.left = new TreeNode(4);
		node1.left.right = new TreeNode(5);

		node1.right.left = new TreeNode(6);
		node1.right.right = new TreeNode(7);

		preorder(node1);

		System.out.println(resultList.toString());

	}
}
