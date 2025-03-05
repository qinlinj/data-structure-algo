package org.qinlinj.nonlinear.highlevel.skiplist;

import java.util.Random;

public class SkipList {
    private static final int MAX_LEVEL = 16;

    private int levelCount;
    private Node dummyHead;
    private Random random = new Random();

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
