package org.qinlinj.nonlinear.tree.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeTest {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(23);
        TreeNode<Integer> node1 = new TreeNode<>(34);
        TreeNode<Integer> node2 = new TreeNode<>(21);
        TreeNode<Integer> node3 = new TreeNode<>(99);
        TreeNode<Integer> node4 = new TreeNode<>(77);
        TreeNode<Integer> node5 = new TreeNode<>(90);
        TreeNode<Integer> node6 = new TreeNode<>(45);
        TreeNode<Integer> node7 = new TreeNode<>(60);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node3.left = node4;
        node3.right = node5;
        node2.left = node6;
        node2.right = node7;

        System.out.println(postOrder(root));
    }

    private static List<Integer> preOrder(TreeNode<Integer> root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<Integer> curr = stack.pop();
            res.add(curr.data);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return res;
    }

    private static List<Integer> inOrder(TreeNode<Integer> root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            TreeNode<Integer> node = stack.pop();
            res.add(node.data);

            curr = node.right;
        }
        return res;
    }

    public static List<Integer> postOrder(TreeNode<Integer> root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;

        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<Integer> curr = stack.pop();
            res.addFirst(curr.data);
            if (curr.left != null) stack.push(curr.left);
            if (curr.right != null) stack.push(curr.right);
        }
        return res;
    }

}
