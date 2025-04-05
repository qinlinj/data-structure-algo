package org.qinlinj.practical.multlinemerging;

import java.io.BufferedReader;
import java.util.*;

/**
 * Step 4: Find the top 100 most frequent words from the sorted result
 * This is a follow-up application that demonstrates how we can use the sorted output
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
