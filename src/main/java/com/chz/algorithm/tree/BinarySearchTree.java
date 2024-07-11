package com.chz.algorithm.tree;

/**
 * 二叉搜索树中的搜索 https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 */
public class BinarySearchTree {

	public TreeNode searchBSTInRecursive(TreeNode root, int val) {
		if (root == null) {
			return null;
		} else {
			if (root.val == val) {
				return root;
			} else if (root.val > val) {
				return searchBST(root.left, val);
			} else {
				return searchBST(root.right, val);
			}
		}
	}

	public static TreeNode searchBST(TreeNode root, int val) {
		if (root == null)
			return root;

		while (root != null) {
			if (val == root.val) {
				return root;
			}
			if (root.val > val) {
				root = root.left;
			} else {
				root = root.right;
			}
		}

		return null;
	}

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(62);

		node1.left = new TreeNode(2);
		node1.right = new TreeNode(93);

		node1.left.right = new TreeNode(30);

		node1.left.right.left = new TreeNode(15);

		TreeNode result = searchBST(node1, 15);

		System.out.println(result.val);

	}

}
