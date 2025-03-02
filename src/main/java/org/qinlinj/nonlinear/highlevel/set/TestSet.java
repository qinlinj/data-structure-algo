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

    public static void main(String[] args) {
        // Read words from a file
        List<String> words = TestFileReader.readFile("data/test-data.txt");

        // Create an ArraySet and measure its performance
        Set<String> arrSet = new ArraySet<>();
        double time1 = testSet(arrSet, words);
        System.out.println("ArraySet: " + time1);

        // Create a LinkedListSet and measure its performance
        Set<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet, words);
        System.out.println("LinkedListSet: " + time2);

        // Performance Explanation:
        // Arrays are stored in a continuous block of memory
        // When the CPU reads one element of an array,
        // it simultaneously loads neighboring elements into the CPU's high-speed cache

        // This cache loading mechanism makes subsequent reads much faster
        // As the next elements are already pre-loaded in the cache

        // In contrast, LinkedList elements are scattered across different memory locations
        // The CPU must fetch each element from the main memory separately

        // This memory access pattern makes array sequential traversal
        // significantly faster than LinkedList sequential traversal

        // Key performance factors:
        // 1. Memory locality
        // 2. Cache line efficiency
        // 3. Predictive memory loading by the CPU
    }
}
