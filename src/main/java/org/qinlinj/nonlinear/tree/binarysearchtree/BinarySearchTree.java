package org.qinlinj.nonlinear.tree.binarysearchtree;

import java.util.List;

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

    }

    /************************* Retrieve *******************************/
    // retrieve
    public boolean contains(E target) {

    }

    public TreeNode find(E target) {

    }

    // order

    // preorder
    public List<E> preOrder() {

    }

    // inorder
    public List<E> inOrder() {

    }

    // postorder
    public List<E> postOrder() {

    }

    // level order
    public List<List<E>> levelOrder() {

    }

    // min value
    public E minValue() {

    }

    // max value
    public E maxValue() {

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
