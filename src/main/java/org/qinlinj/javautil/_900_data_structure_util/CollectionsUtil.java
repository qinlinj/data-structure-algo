package org.qinlinj.javautil._900_data_structure_util;

import java.util.*;

public class CollectionsUtil {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Collections.nCopies(10, 0));
        Collections.reverse(list);
    }
}
