package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._531_priority_queue_practice; /**
 * _531_g_SortCharactersByFrequency
 * <p>
 * LeetCode #451: Sort Characters By Frequency
 * <p>
 * This solution sorts characters in a string based on their frequency,
 * with the most frequent characters appearing first in the result.
 * <p>
 * Approach:
 * 1. Count the frequency of each character using a HashMap
 * 2. Use a priority queue (max heap) to sort character-frequency pairs
 * by frequency in descending order
 * 3. Build the result string by appending each character according to its frequency
 * <p>
 * Time Complexity: O(n log k) where n is the string length and k is the number of unique characters
 * Space Complexity: O(n) for storing character frequencies and the result
 */

import java.util.*;

public class _531_g_SortCharactersByFrequency {

    public static void main(String[] args) {
        _531_g_SortCharactersByFrequency solution = new _531_g_SortCharactersByFrequency();

        // Test case 1
        String s1 = "tree";
        System.out.println("Input: " + s1);
        System.out.println("Output: " + solution.frequencySort(s1));
        // Expected output: "eert" or "eetr"

        // Test case 2
        String s2 = "cccaaa";
        System.out.println("\nInput: " + s2);
        System.out.println("Output: " + solution.frequencySort(s2));
        // Expected output: "cccaaa" or "aaaccc"

        // Test case 3
        String s3 = "Aabb";
        System.out.println("\nInput: " + s3);
        System.out.println("Output: " + solution.frequencySort(s3));
        // Expected output: "bbAa" or "bbaA"
    }

    public String frequencySort(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        // Count character frequencies
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Create a max heap based on character frequency
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );

        // Add all character-frequency pairs to the heap
        maxHeap.addAll(frequencyMap.entrySet());

        // Build the result string
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            char c = entry.getKey();
            int frequency = entry.getValue();

            // Append the character 'frequency' times
            for (int i = 0; i < frequency; i++) {
                result.append(c);
            }
        }

        return result.toString();
    }

    // Alternative implementation using sorting
    public String frequencySort2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        // Count character frequencies
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Create a list of characters
        List<Character> characters = new ArrayList<>();
        for (char c : s.toCharArray()) {
            characters.add(c);
        }

        // Sort characters based on their frequency
        Collections.sort(characters, (a, b) -> {
            int freqCompare = frequencyMap.get(b) - frequencyMap.get(a);
            if (freqCompare != 0) {
                return freqCompare;
            }
            return a - b; // If frequencies are equal, sort by character
        });

        // Build the result string
        StringBuilder result = new StringBuilder();
        for (char c : characters) {
            result.append(c);
        }

        return result.toString();
    }
}