package org.qinlinj.nonlinear.highlevel.skiplist;

import java.util.Random;

public class SkipList {
    private static final int MAX_LEVEL = 16;

    private int levelCount;
    private Node dummyHead;
    private Random random = new Random();

    public SkipList() {
        this.levelCount = 1;
        this.dummyHead = new Node(-1);
    }

    public boolean contains(E e) {
        return get(e) != null;
    }

    public Node get(E e) {
        Node curr = dummyHead;
        
        for (int i = levelCount - 1; i >= 0; i--) {
            while (curr.nextNodes[i] != null
                    && curr.nextNodes[i].data.compareTo(e) < 0) {
                curr = curr.nextNodes[i];
            }
        }

        if (curr.nextNodes[0] != null
                && curr.nextNodes[0].data.compareTo(e) == 0) {
            return curr.nextNodes[0];
        }

        return null;
    }


    private class Node<E extends Comparable<E>> {
        E data;

        Node[] nextNodes;

        int maxIndexLevel = 0;

        Node(E data) {
            this.data = data;
            nextNodes = new Node[MAX_LEVEL];
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxIndexLevel);
            builder.append(" }");
            return builder.toString();
        }
    }

}
