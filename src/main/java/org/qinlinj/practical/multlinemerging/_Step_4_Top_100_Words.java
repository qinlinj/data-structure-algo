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
 */
public class _Step_4_Top_100_Words {
    /**
     * Main method to find the top 100 most frequent words
     * Time Complexity: O(n) where n is the number of words in the file
     * Space Complexity: O(1) as we only keep 100 entries in the min-heap
     */
    public static void main(String[] args) throws Exception {
        String fileName = "data/top100/sorted_words.txt";
        String[] res = new _Step_4_Top_100_Words().top_100(fileName);
        System.out.println(Arrays.toString(res));
    }

    /**
     * Find the top 100 most frequent words in the sorted file
     * <p>
     * Example visualization:
     * Sorted file: [aaa, aaa, aaa, bbb, bbb, ccc, ddd, ddd, ddd, ddd]
     * <p>
     * Min-heap contents after processing:
     * ["aaa", count=3]
     * ["bbb", count=2]
     * ["ddd", count=4]
     * <p>
     * Result: ["aaa", "bbb", "ddd"] (ordered by frequency from lowest to highest)
     * <p>
     * Time Complexity: O(n) where n is the number of words in the file
     * Each word is processed exactly once, and heap operations are constant (max size 100)
     * Space Complexity: O(1) as we maintain at most 100 entries in the min-heap
     */
    public String[] top_100(String fileName) throws Exception {
        // Create a min-heap to track the top 100 words by frequency
        // Using a min-heap allows us to efficiently remove the least frequent word when needed
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(100, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.cnt - o2.cnt;  // Compare by count (frequency)
            }
        });

        String prevWord = null;
        int prevCnt = 0;

        // Process the sorted file line by line
        BufferedReader br = FileIOUtils.getReader(fileName);
        String currWord = null;

        while ((currWord = br.readLine()) != null) {
            // If we encounter a new word
            if (!currWord.equals(prevWord)) {
                // Process the previous word if it exists
                if (prevWord != null) {
                    // If the heap is not full, simply add the word
                    if (minHeap.size() < 100) {
                        minHeap.add(new Pair(prevWord, prevCnt));
                    }
                    // If the heap is full, replace the least frequent word if the current word is more frequent
                    else if (prevCnt > minHeap.peek().cnt) {
                        minHeap.remove();
                        minHeap.add(new Pair(prevWord, prevCnt));
                    }
                }

                // Reset for the new word
                prevWord = currWord;
                prevCnt = 0;
            }
            // Increment the count for the current word
            prevCnt++;
        }

        // Don't forget to process the last word
        if (prevWord != null) {
            if (minHeap.size() < 100) {
                minHeap.add(new Pair(prevWord, prevCnt));
            } else if (prevCnt > minHeap.peek().cnt) {
                minHeap.remove();
                minHeap.add(new Pair(prevWord, prevCnt));
            }
        }

        // Convert the heap to an array
        String[] res = new String[minHeap.size()];
        int index = 0;
        while (!minHeap.isEmpty()) {
            res[index++] = minHeap.poll().word;
        }

        // Note: The result array will have the words ordered by frequency from lowest to highest
        return res;
    }

    /**
     * Helper class to store a word and its frequency count
     */
    class Pair {
        String word;
        int cnt;

        Pair(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
}
