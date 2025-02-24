package org.qinlinj.nonlinear.tree.avltree;

public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        avl.add(9);
        avl.add(10);
        avl.add(11);

        avl.add(13);

        System.out.println("is binary search tree：" + avl.isBinarySearchTree());
        System.out.println("is balanced：" + avl.isBalanced());
    }
}
