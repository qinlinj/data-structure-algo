package org.qinlinj.nonlinear.highlevel.set;

import org.qinlinj.others.test.TestFileReader;

import java.util.List;

public class TestSet {
    /**
     * Tests the performance of a Set by adding words and measuring lookup time.
     *
     * @param set   The Set implementation to test
     * @param words List of words to add to the set
     * @return Time in seconds to perform 10,000 lookups
     */
    private static double testSet(Set<String> set, List<String> words) {
        // Add all words from the list to the set
        for (String word : words)
            set.add(word);

        // Record start time for performance measurement
        long start = System.nanoTime();

        // Perform 10,000 lookups of the same word
        for (int i = 0; i < 10000; i++) {
            set.contains("father");
        }

        // Record end time
        long end = System.nanoTime();

        // Calculate and return time taken in seconds
        return (end - start) / 1000_000_000.0;
    }

    public static void main(String[] args) {
        // Read words from a file
        List<String> words = TestFileReader.readFile("data/test-data.txt");

        // Create an SetArrayImplementation and measure its performance
        Set<String> arrSet = new SetArrayImplementation<>();
        double time1 = testSet(arrSet, words);
        System.out.println("SetArrayImplementation: " + time1);

        // Create a SetLinkedListImplementation and measure its performance
        Set<String> linkedListSet = new SetLinkedListImplementation<>();
        double time2 = testSet(linkedListSet, words);
        System.out.println("SetLinkedListImplementation: " + time2);

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
