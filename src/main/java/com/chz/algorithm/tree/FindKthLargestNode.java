package com.chz.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树的第k大节点
 * 
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 * 
 * 中序遍历：左 根 右
 */
public class FindKthLargestNode {
	static List<Integer> list = new ArrayList<>();
	static int count = 0;

	public static int kthLargest(TreeNode root, int k) {
		list = dfs(root, k);
		return list.get(k - 1);
	}

	/**
	 * 按中序遍历 倒序 把节点存到list中，想要得到的是：第k大的节点,那list中第k个元素（下标:k-1）就是要找的数
	 * 
	 * @param root
	 * @param k
	 * @return
	 */
	public static List<Integer> dfs(TreeNode root, int k) {
		if (root != null) {
			if (root.right != null) {
				dfs(root.right, k);
			}
			list.add(root.val);
			if (root.left != null) {
				dfs(root.left, k);
			}
			count++;
			if (count == k) {// 当count== K时 list中第k个元素已经得到了，其他就不需要再存到集合中了,返回list。
				return list;
			}
		}
		return list;

	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(1);
		root.right = new TreeNode(4);
		root.left.right = new TreeNode(2);
		System.out.println(kthLargest(root, 1));

		middleOrderPrintNode(root);
	}

	/**
	 * 打印中序遍历
	 * 
	 * @param root
	 */
	public static void middleOrderPrintNode(TreeNode root) {
		if (root != null) {
			middleOrderPrintNode(root.left);
			System.out.println(root.val);
			middleOrderPrintNode(root.right);
		}
	}

}
