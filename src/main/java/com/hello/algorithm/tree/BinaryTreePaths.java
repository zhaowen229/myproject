package com.hello.algorithm.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 注意要保留之前的路径
 * 
 * https://leetcode-cn.com/problems/binary-tree-paths/
 *
 */
public class BinaryTreePaths {

	/**
	 * DFS
	 * 
	 * @param root
	 * @return
	 */
	public static List<String> binaryTreePaths(TreeNode root) {
		List<String> paths = new ArrayList<>();
		constructAllPaths(root, "", paths);
		return paths;
	}

	public static void constructAllPaths(TreeNode root, String content, List<String> paths) {
		if (root != null) {
			content = content + root.val;
			if (root.left == null && root.right == null) {
				paths.add(content);
			} else {
				content = content + "->";
				constructAllPaths(root.left, content, paths);
				constructAllPaths(root.right, content, paths);
			}
		}
	}

	/**
	 * 广度优先搜索
	 * 
	 * 还可以只用一个queue，来处理
	 * 
	 * 在队列里先存节点，再存路径 TODO
	 * 
	 * @param root
	 * @return
	 */
	public static List<String> binaryTreePathsBFS(TreeNode root) {
		Deque<TreeNode> queueNode = new LinkedList<>();
		Deque<String> queuePath = new LinkedList<>();
		List<String> result = new ArrayList<>();

		if (root != null) {
			queueNode.offer(root);
			queuePath.offer("" + root.val);
			while (!queueNode.isEmpty()) {
				TreeNode node = queueNode.poll();
				String path = queuePath.poll();

				// 叶子节点 增加路径到结果中
				if (node.left == null && node.right == null) {
					result.add(path);
				} else {
					if (node.left != null) {
						queueNode.offer(node.left);
						queuePath.offer(path + "->" + node.left.val);
					}
					if (node.right != null) {
						queueNode.offer(node.right);
						queuePath.offer(path + "->" + node.right.val);
					}
				}
			}

		}
		return result;
	}

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);

		node1.left = new TreeNode(2);
		node1.right = new TreeNode(3);

		node1.left.right = new TreeNode(5);

		List<String> list = binaryTreePathsBFS(node1);
		for (Object object : list) {
			System.out.println(object);
		}
	}

}
