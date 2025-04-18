package org.qinlinj.javabasics;

import java.util.*;

public class CollectionsUtil {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Collections.nCopies(10, 0));
        Collections.reverse(list);
    }
}
