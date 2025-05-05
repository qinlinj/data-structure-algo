package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._531_priority_queue_practice; /**
 * _531_j_TopKFrequentWords
 * <p>
 * LeetCode #692: Top K Frequent Words
 * <p>
 * This solution finds the k most frequent words in an array of strings.
 * Words with the same frequency are ordered lexicographically (alphabetically).
 * <p>
 * Approach:
 * 1. Count the frequency of each word using a HashMap
 * 2. Use a priority queue (min-heap) to track the k most frequent words
 * - The heap orders elements first by frequency (smallest at top)
 * - If frequencies are equal, it orders in reverse lexicographical order
 * 3. Maintain the heap with size k by removing the least frequent/lexicographically
 * largest word when size exceeds k
 * 4. Build the result by extracting from the heap (needs to be reversed)
 * <p>
 * Time Complexity: O(n log k) where n is the total number of words
 * Space Complexity: O(n) for the frequency map and heap
 */

import java.util.*;

public class _531_j_TopKFrequentWords {

    public static void main(String[] args) {
        _531_j_TopKFrequentWords solution = new _531_j_TopKFrequentWords();

        // Test case 1
        String[] words1 = {"i", "love", "leetcode", "i", "love", "coding"};
        int k1 = 2;
        List<String> result1 = solution.topKFrequent(words1, k1);

        System.out.println("Input: [\"i\", \"love\", \"leetcode\", \"i\", \"love\", \"coding\"], k = 2");
        System.out.println("Output: " + result1);
        // Expected output: ["i", "love"]

        // Test case 2
        String[] words2 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k2 = 4;
        List<String> result2 = solution.topKFrequent(words2, k2);

        System.out.println("\nInput: [\"the\", \"day\", \"is\", \"sunny\", \"the\", \"the\", \"the\", \"sunny\", \"is\", \"is\"], k = 4");
        System.out.println("Output: " + result2);
        // Expected output: ["the", "is", "sunny", "day"]

        // Test case with custom sort
        String[] words3 = {"i", "love", "leetcode", "i", "love", "coding"};
        int k3 = 3;
        List<String> result3 = solution.topKFrequentWithSort(words3, k3);

        System.out.println("\nInput (With Sort): [\"i\", \"love\", \"leetcode\", \"i\", \"love\", \"coding\"], k = 3");
        System.out.println("Output: " + result3);
        // Expected output: ["i", "love", "coding"] or ["i", "love", "leetcode"]
    }

    public List<String> topKFrequent(String[] words, int k) {
        // Count frequency of each word
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Create a min heap based on:
        // 1. Frequency (ascending order)
        // 2. If frequencies are equal, lexicographical order (descending order)
        // This ensures that when we poll, we get the least frequent word or
        // lexicographically larger word when frequencies are tied
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> {
                    if (a.getValue().equals(b.getValue())) {
                        // For same frequency, higher lexicographical word stays at top
                        return b.getKey().compareTo(a.getKey());
                    }
                    // Lower frequency stays at top
                    return a.getValue() - b.getValue();
                }
        );

        // Add entries to the heap, maintaining size k
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Build result (in reverse order since we're using a min heap)
        List<String> result = new LinkedList<>();
        while (!minHeap.isEmpty()) {
            // Add to front to reverse the order
            result.add(0, minHeap.poll().getKey());
        }

        return result;
    }

    // Alternative approach using a custom comparator and sorting
    public List<String> topKFrequentWithSort(String[] words, int k) {
        // Count frequency of each word
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Create a list of all words
        List<String> candidates = new ArrayList<>(frequencyMap.keySet());

        // Sort by frequency (descending) and then lexicographically (ascending)
        Collections.sort(candidates, (a, b) -> {
            int freqCompare = frequencyMap.get(b) - frequencyMap.get(a);
            if (freqCompare != 0) {
                return freqCompare;
            }
            return a.compareTo(b);
        });

        // Return top k elements
        return candidates.subList(0, k);
    }
}