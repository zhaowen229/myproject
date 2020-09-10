package com.hello.algorithm.tree;

public class SumTreeNodeValue {
	static int result = 0;
	static int leftResult = 0;

	/**
	 * 所有节点的和
	 * 
	 * @param root
	 * @return
	 */
	public static int sumAllNodeValue(TreeNode root) {
		if (root == null) {
			return 0;
		}

		if (root != null) {
			result = result + root.val;
		}

		sumAllNodeValue(root.left);
		sumAllNodeValue(root.right);

		return result;

	}

	/**
	 * 所有左叶子节点的和
	 * 
	 * @param root
	 * @return
	 */
	public static int sumLeftLeafNodeValue(TreeNode root) {
		if (root == null) {
			return 0;
		}

		if (root.left != null && root.left.left == null && root.left.right == null) {
			leftResult = leftResult + root.left.val;
		}

		sumLeftLeafNodeValue(root.left);
		sumLeftLeafNodeValue(root.right);

		return leftResult;

	}

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(3);

		node1.left = new TreeNode(9);
		node1.right = new TreeNode(20);

		node1.right.left = new TreeNode(15);
		node1.right.right = new TreeNode(7);

		node1.right.left.left = new TreeNode(1);

		node1.right.right.right = new TreeNode(2);

		System.out.println("All Node Value sum == " + sumAllNodeValue(node1));
		System.out.println("Left Leaf Node Value sum == " + sumLeftLeafNodeValue(node1));

	}

}
