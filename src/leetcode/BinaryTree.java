package leetcode;

import java.util.List;

public class BinaryTree {

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	//
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		} else if (p != null && q != null) {
			if (p.val == q.val) {
				boolean leftResult = isSameTree(p.left, q.left);
				boolean rightResult = isSameTree(p.right, q.right);

				return (leftResult && rightResult);
			} else {
				return false;
			}
		}

		return false;
	}

	// 前序遍历(根-左-右)
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> list = new java.util.ArrayList<Integer>();
		if (root == null) {
			return list;
		}

		list.add(root.val);

		List<Integer> leftList = preorderTraversal(root.left);
		List<Integer> rightList = preorderTraversal(root.right);

		list.addAll(leftList);
		list.addAll(rightList);

		return list;
	}

	//中序遍历(左-根-右)
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> list = new java.util.ArrayList<Integer>();
		
		if(root == null){
			return list;
		}
		
		List<Integer> leftList = inorderTraversal(root.left);
		List<Integer> rightList = inorderTraversal(root.right);
		
		list.addAll(leftList);
		list.add(root.val);
		list.addAll(rightList);
		
		return list;
	}

	private void test() {
		TreeNode p = new TreeNode(0);
		p.left = new TreeNode(5);
		p.right = new TreeNode(15);

		TreeNode q = new TreeNode(0);
		TreeNode qL = new TreeNode(5);
		q.left = qL;
		qL.right = new TreeNode(15);

		System.out.println(inorderTraversal(q));

	}

	public static void main(String[] args) {
		BinaryTree sm = new BinaryTree();
		sm.test();

	}
}
