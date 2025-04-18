package org.qinlinj.practical.multlinemerging;

import java.io.BufferedReader;
import java.util.*;

/**
 * Step 4: Find the top 100 most frequent words from the sorted result
 * This is a follow-up application that demonstrates how we can use the sorted output
 * <p>
 * Word Frequency Analysis with Min-Heap
 * <p>
 * This class demonstrates an efficient algorithm for finding the most frequent words in a large dataset
 * by leveraging both external sorting and a min-heap data structure. It serves as an application of
 * the previously sorted data from the external sorting process.
 * <p>
 * Advantages of Min-Heap for Top-K Problems:
 * 1. Memory Efficiency: Only stores K items (100 in this case) regardless of input size
 * 2. Performance: Maintains O(N) time complexity with respect to input size
 * 3. Single-Pass: Processes data in a streaming fashion without requiring multiple scans
 * 4. Adaptability: Works well with external sorting results by leveraging the sorted order
 * 5. Simplicity: Provides a straightforward solution to the common "Top-K" problem
 * <p>
 * Concept and Principles:
 * - We use a min-heap of fixed size K (100) to maintain the K most frequent words
 * - The min-heap is ordered by frequency (lowest frequency at the root)
 * - When we encounter a word with higher frequency than the minimum in our heap,
 * we remove the minimum and add the new word
 * - This ensures we always maintain the K highest frequency words we've seen so far
 * - Since the input is sorted, we can easily count word frequencies in a single pass
 * - The algorithm maintains O(N) time complexity while using only O(K) extra space
 * <p>
 * This implementation is particularly efficient because it takes advantage of:
 * 1. The sorted nature of the input (from previous external sorting steps)
 * 2. The bounded-size min-heap to track only the top K elements
 * 3. Single-pass processing to minimize I/O operations
 * <p>
 * Step 4: Find the top 100 most frequent words from the sorted result
 * <p>
 * This class performs frequency analysis on the sorted data to identify the most
 * common words. It demonstrates a practical application of the sorted output from
 * the external sorting process, showing how sorted data enables efficient frequency counting.
 * <p>
 * The algorithm leverages the fact that identical words are grouped together in the
 * sorted file, allowing us to count frequencies in a single pass while maintaining
 * a fixed-size data structure (min-heap) to track the top 100 words.
 */
public class _Step_4_Top_100_Words {
    /**
     * Main method to find the top 100 most frequent words
     * <p>
     * Time Complexity: O(n) where n is the total number of words in the file
     * - We process each word exactly once, and heap operations are bounded (max size 100)
     * <p>
     * Space Complexity: O(1) as we only keep a constant number (100) of entries in the min-heap
     * - This is significantly more memory-efficient than storing all unique words
     */
    public static void main(String[] args) throws Exception {
        // Path to the sorted words file from Step 3
        String fileName = "data/top100/sorted_words.txt";

        // Call the method to find top 100 words and print the result
        String[] res = new _Step_4_Top_100_Words().top_100(fileName);
        System.out.println(Arrays.toString(res));
    }

    /**
     * Find the top 100 most frequent words in the sorted file
     * <p>
     * This method implements a streaming algorithm that:
     * 1. Reads the sorted file sequentially, counting word frequencies
     * 2. Maintains a min-heap of the 100 most frequent words seen so far
     * 3. Returns these words ordered by their frequency (from lowest to highest)
     * <p>
     * Detailed Visual Example:
     * -----------------------------------------------------
     * Input Sorted File:
     * [aaa, aaa, aaa, bbb, bbb, ccc, ddd, ddd, ddd, ddd, eee, eee, fff]
     * <p>
     * Processing step by step:
     * <p>
     * 1. Read "aaa" (count: 3)
     * Min-heap: ["aaa", count=3]
     * <p>
     * 2. Read "bbb" (count: 2)
     * Min-heap: ["bbb", count=2], ["aaa", count=3]
     * <p>
     * 3. Read "ccc" (count: 1)
     * Min-heap: ["ccc", count=1], ["bbb", count=2], ["aaa", count=3]
     * <p>
     * 4. Read "ddd" (count: 4)
     * Min-heap is full, but "ddd" has higher count than minimum ("ccc")
     * Remove ["ccc", count=1]
     * Add ["ddd", count=4]
     * Min-heap: ["bbb", count=2], ["aaa", count=3], ["ddd", count=4]
     * <p>
     * 5. Read "eee" (count: 2)
     * Min-heap is full, "eee" has same count as minimum ("bbb")
     * No change to min-heap
     * <p>
     * 6. Read "fff" (count: 1)
     * Min-heap is full, "fff" has lower count than minimum ("bbb")
     * No change to min-heap
     * <p>
     * Final min-heap: ["bbb", count=2], ["aaa", count=3], ["ddd", count=4]
     * Result array after polling: ["bbb", "aaa", "ddd"]
     * -----------------------------------------------------
     * <p>
     * Time Complexity: O(n) where n is the number of words in the file
     * - We process each word exactly once in a single pass
     * - Heap operations (add/remove) take O(log 100) = O(1) time
     * - Total runtime is dominated by the single pass through the data: O(n)
     * <p>
     * Space Complexity: O(1) as we maintain at most 100 entries in the min-heap
     * - This is constant with respect to the input size
     * - Much more efficient than storing all unique words for large vocabularies
     */
    public String[] top_100(String fileName) throws Exception {
        // Create a min-heap to track the top 100 words by frequency
        // The heap is ordered by count, so the least frequent word is always at the top
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(100, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.cnt - o2.cnt;  // Compare by count (frequency)
            }
        });

        // Variables to track the current word being processed
        String prevWord = null;  // The word we're currently counting
        int prevCnt = 0;        // Its frequency count so far

        // Open the sorted file for reading
        BufferedReader br = FileIOUtils.getReader(fileName);
        String currWord = null;

        // Process the file one word at a time
        while ((currWord = br.readLine()) != null) {
            // If we've encountered a new word, process the previous word
            if (!currWord.equals(prevWord)) {
                // Only process if we had a previous word (skips the first iteration)
                if (prevWord != null) {
                    // Case 1: Heap isn't full yet (less than 100 words)
                    if (minHeap.size() < 100) {
                        minHeap.add(new Pair(prevWord, prevCnt));
                    }
                    // Case 2: Heap is full, current word has higher count than the minimum
                    else if (prevCnt > minHeap.peek().cnt) {
                        minHeap.remove();  // Remove the least frequent word
                        minHeap.add(new Pair(prevWord, prevCnt));  // Add current word
                    }
                }

                // Reset counters for the new word
                prevWord = currWord;
                prevCnt = 0;
            }

            // Increment count for the current word
            prevCnt++;
        }

        // Process the last word (which won't trigger the if-condition in the loop)
        if (prevWord != null) {
            // Same logic as in the loop
            if (minHeap.size() < 100) {
                minHeap.add(new Pair(prevWord, prevCnt));
            } else if (prevCnt > minHeap.peek().cnt) {
                minHeap.remove();
                minHeap.add(new Pair(prevWord, prevCnt));
            }
        }

        // Close the file reader
        FileIOUtils.closeReader(br);

        // Convert the heap to an array
        // Note: Elements will come out in ascending order of frequency
        String[] res = new String[minHeap.size()];
        int index = 0;
        while (!minHeap.isEmpty()) {
            res[index++] = minHeap.poll().word;
        }

        // At this point, the array contains the top words sorted by frequency (lowest to highest)
        return res;
    }

    /**
     * Helper class to store a word and its frequency count
     * <p>
     * This simple pair class enables us to keep track of both the word and its count
     * in the min-heap. It's used internally by the algorithm and doesn't need to be
     * exposed outside this class.
     */
    class Pair {
        String word;  // The word itself
        int cnt;      // Its frequency count

        /**
         * Constructor to create a word-count pair
         * <p>
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        Pair(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
}