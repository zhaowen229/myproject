package com.chz.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxDepthOfTree {
	/**
	 * 树的最大深度
	 * 
	 * recursive
	 */
	public static int maxDepth(TreeNode root) {
		if (root == null)
			return 0;

		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);
		return Math.max(leftDepth, rightDepth) + 1;

	}

	/**
	 * 树的最大深度
	 * 
	 * 迭代 层级搜索
	 */
	public static int maxDepthWithIterator(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int result = 0;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();// 当前层有几个node
			while (size > 0) {// 把下一层的所有节点存到队列中
				TreeNode node = (TreeNode) queue.poll();
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
				size--;
			}
			result++;
		}
		return result;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);

		root.left = new TreeNode(9);
		root.right = new TreeNode(20);

		root.left.left = new TreeNode(1);

		root.left.left.left = new TreeNode(11);

		root.right.left = new TreeNode(15);

		root.right.right = new TreeNode(7);

		System.out.println("递归=" + maxDepth(root));
		System.out.println("迭代=" + maxDepthWithIterator(root));

	}

}
