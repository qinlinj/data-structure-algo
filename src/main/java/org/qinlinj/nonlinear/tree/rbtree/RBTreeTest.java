package org.qinlinj.nonlinear.tree.rbtree;

import org.qinlinj.nonlinear.tree.avltree.AVLTree;
import org.qinlinj.nonlinear.tree.binarysearchtree.BinarySearchTree;

import java.util.ArrayList;
import java.util.Random;

public class RBTreeTest {
    private static Random random = new Random();

    public static void main(String[] args) {
        int num = 20000000;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(random.nextInt());
        }

        long startTime = System.nanoTime();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer i : list) bst.add(i);
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BST：" + time + " s");

        startTime = System.nanoTime();
        AVLTree<Integer> avl = new AVLTree<>();
        for (Integer i : list) avl.add(i);
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL：" + time + " s");

        startTime = System.nanoTime();
        RBTree<Integer> rbTree = new RBTree<>();
        for (Integer i : list) rbTree.add(i);
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RB：" + time + " s");
    }
}
