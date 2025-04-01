package org.qinlinj.practical.cache;

import java.util.*;

class Node {
}

class DoubleLinkedList {
}

public class LFUCacheDoubleLinkedListImp {
    private Map<Integer, Node> keyToNode;
    private Map<Integer, DoubleLinkedList> usedCountToKeys;

    private int capacity;
    private int minUsedCount;
}
