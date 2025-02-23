package org.qinlinj.nonlinear.tree.binarysearchtree;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(33);
        bst.add(22);
        bst.add(66);
        bst.add(12);
        bst.add(35);
        bst.add(70);
        bst.add(34);
        bst.add(50);
        bst.add(68);
        bst.add(99);
        System.out.println(bst.inOrder());
        System.out.println(bst.contains(34));

        System.out.println(bst.minValue());
        System.out.println(bst.maxValue());

        System.out.println(bst.removeMin());
        System.out.println(bst.inOrder());

        System.out.println(bst.removeMax());
        System.out.println(bst.inOrder());

        bst.remove(66);
        System.out.println(bst.inOrder());

        // bst.set(50, 100); // Breaking the properties of BST
        System.out.println(bst.inOrder());
    }
}
