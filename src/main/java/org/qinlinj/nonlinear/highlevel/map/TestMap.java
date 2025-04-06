package org.qinlinj.nonlinear.highlevel.map;

import java.util.*;
import org.qinlinj.practical.others.test.TestFileReader;

/**
 * Test class for comparing the performance of different Map implementations.
 */
public class TestMap {
    // Read words from a test data file
    static List<String> words = TestFileReader.readFile("data\\test-data.txt");

    /**
     * Tests the performance of a map implementation by counting word occurrences.
     *
     * @param map The map implementation to test
     * @return The time taken in seconds to perform the word counting operation
     */
    private static double testMap(Map<String, Integer> map) {
        // Record start time in nanoseconds
        long startTime = System.nanoTime();

        // For each word in the list
        for (String word : words) {
            if (map.containsKey(word)) {
                // If word exists in map, increment its count
                Integer count = map.get(word);
                map.set(word, count + 1);
            } else {
                // If word doesn't exist, add it with count 1
                map.add(word, 1);
            }
        }

        // Calculate and return elapsed time in seconds
        return (System.nanoTime() - startTime) / 1000000000.0;
    }

    /**
     * Main method to run performance tests on different Map implementations.
     */
    public static void main(String[] args) {
        // Test LinkedListMap implementation
        Map<String, Integer> llMap = new LinkedListMap<>();
        double time1 = testMap(llMap);
        System.out.println("LinkedListMap: " + time1);

        // Test BSTMap (Binary Search Tree Map) implementation
        Map<String, Integer> bstMap = new BSTMap<>();
        double time2 = testMap(bstMap);
        System.out.println("BSTMap: " + time2);

        // Test HashMap implementation
        Map<String, Integer> hashMap = new HashMap<>();
        double time3 = testMap(hashMap);
        System.out.println("HashMap: " + time3);
    }
}

