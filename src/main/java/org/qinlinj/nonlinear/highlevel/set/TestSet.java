package org.qinlinj.nonlinear.highlevel.set;

import java.util.List;

public class TestSet {
    private static double testSet(Set<String> set, List<String> words) {
        for (String word : words)
            set.add(word);

        long start = System.nanoTime();


        for (int i = 0; i < 10000; i++) {
            set.contains("father");
        }


        long end = System.nanoTime();

        return (end - start) / 1000_000_000.0;
    }
}
