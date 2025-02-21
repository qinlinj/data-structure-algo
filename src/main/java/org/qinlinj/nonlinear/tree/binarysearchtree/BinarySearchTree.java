package org.qinlinj.nonlinear.tree.binarysearchtree;

import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> {
    TreeNode root;
    int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /************************* Create *******************************/
    // create
    public void add(E e) {
        if (root == null) {
            root = new TreeNode(e);
        }
        TreeNode curr = root;
        while (curr != null) {
            if (curr.data.compareTo(e) == 0) {
                return;
            } else if (curr.left == null && curr.data.compareTo(e) > 0) {
                curr.left = new TreeNode(e);
                size++;
                return;
            } else if (curr.right == null && curr.data.compareTo(e) < 0) {
                curr.right = new TreeNode(e);
                size++;
                return;
            }

            if (curr.data.compareTo(e) > 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    /************************* Retrieve *******************************/
    // retrieve
    public boolean contains(E target) {
        if (root == null) {
            return false;
        }
        return find(target) != null;
    }

    public TreeNode find(E target) {
        if (root == null) {
            return null;
        }
        TreeNode curr = root;
        while (curr != null) {
            if (curr.data.compareTo(target) == 0) {
                return curr;
            } else if (curr.data.compareTo(target) > 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return null;
    }

    // order

    // preorder
    public List<E> preOrder() {
        List<E> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            list.add(curr.data);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return list;
    }

    // inorder
    public List<E> inOrder() {
        List<E> list = new ArrayList<>();
        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode node = stack.pop();
            list.add(node.data);
            curr = node.right;
        }
        return list;
    }

    // postorder
    public List<E> postOrder() {
        LinkedList<E> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            list.addFirst(curr.data);
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        return list;
    }

    // level order
    public List<List<E>> levelOrder() {
        List<List<E>> list = new ArrayList<>();

        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<E> stageList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                stageList.add(node.data);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(stageList);
        }
        return list;
    }

    // min value
    public E minValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        TreeNode curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr.data;
    }

    // max value
    public E maxValue() {
        if (root == null) {
            throw new RuntimeException("tree is null");
        }
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr.data;
    }

    /************************* Delete *******************************/
    // delete
    public E removeMin() {

    }

    public E removeMax() {

    }

    public void remove(E e) {

    }

    public void remove1(E e) {

    }

    private class TreeNode {
        E data;
        TreeNode left;
        TreeNode right;

        public TreeNode(E data) {
            this.data = data;
        }
    }
}
