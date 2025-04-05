package org.qinlinj.practical.multlinemerging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.*;

/**
 * Step 3: Merge all sorted files using a k-way merge with a min-heap
 * This is the key step of external sorting where we merge all sorted chunks
 * to produce the final sorted output
 * <p>
 * External Sorting: K-Way Merge Implementation
 * <p>
 * External sorting is a technique used when the data being sorted doesn't fit entirely in memory.
 * It follows a two-phase approach:
 * 1. Split and Sort: Divide the large file into smaller chunks, sort each chunk in memory
 * 2. Merge: Combine all sorted chunks using a k-way merge algorithm (implemented in this class)
 * <p>
 * Advantages of External Sorting with K-Way Merge:
 * 1. Memory Efficiency: Processes data larger than available RAM
 * 2. I/O Optimization: Minimizes disk reads/writes by sequential access patterns
 * 3. Scalability: Effectively handles gigabytes or terabytes of data
 * 4. Performance: Maintains near-optimal O(N log K) time complexity even for huge datasets
 * 5. Practical: Used in databases, big data frameworks, and operating systems
 * <p>
 * K-Way Merge Concept:
 * The k-way merge is the heart of external sorting, efficiently combining multiple sorted chunks
 * into a single sorted output. It uses a min-heap data structure to always select the smallest
 * element from among all chunks with minimal comparisons.
 * <p>
 * The algorithm works by:
 * - Keeping exactly one element from each sorted chunk in a min-heap
 * - Repeatedly extracting the minimum element and writing it to the output
 * - Replacing the extracted element with the next element from its chunk
 * - Continuing until all elements from all chunks are processed
 * <p>
 * This approach ensures that elements are merged in sorted order using minimal memory
 * (proportional to the number of chunks, not the total data size).
 */
public class _Step_3_ExternalSorter {
    /**
     * Main method to merge all sorted files
     * Time Complexity: O(n log k) where n is the total number of elements and k is the number of files
     * Space Complexity: O(k) for the min-heap with k elements
     */
    public static void main(String[] args) throws Exception {
        String dirName = "data/top100/sorted_data/";
        new _Step_3_ExternalSorter().mergeSort(dirName);
    }

    /**
     * Perform a k-way merge of all sorted files using a min-heap
     * <p>
     * Example visualization:
     * Sorted files:
     * file0: [apple, cat, dog]
     * file1: [banana, elephant, zebra]
     * file2: [ant, bear, tiger]
     * <p>
     * Min-heap initially contains: [apple, banana, ant]
     * Pull smallest (ant) → Min-heap: [apple, banana, bear]
     * Pull smallest (apple) → Min-heap: [banana, bear, cat]
     * ... and so on until all elements are processed
     * <p>
     * Time Complexity: O(n log k) where n is the total number of elements and k is the number of files
     * The log k factor comes from the heap operations
     * Space Complexity: O(k) for storing one element from each file in the min-heap
     */
    public void mergeSort(String dirName) throws Exception {
        // Get all sorted files
        File dir = new File(dirName);
        File[] children = dir.listFiles();

        // Create a min-heap to help with the k-way merge
        // The heap will store BufferedIterator objects, which wrap the file readers
        PriorityQueue<BufferedIterator> minHeap = new PriorityQueue<>(children.length, new Comparator<BufferedIterator>() {
            @Override
            public int compare(BufferedIterator o1, BufferedIterator o2) {
                return o1.next().compareTo(o2.next());
            }
        });

        // Initialize the heap with the first element from each file
        for (File file : children) {
            BufferedReader br = FileIOUtils.getReader(file.getPath());
            BufferedIterator buf = new BufferedIterator(br);
            if (buf.hasNext()) {
                minHeap.add(buf);
            } else {
                buf.close();
            }
        }

        // Create the output file for the merged result
        BufferedWriter bw = FileIOUtils.getWriter("data/top100/sorted_words.txt");

        // Perform the k-way merge
        while (!minHeap.isEmpty()) {
            // Get the iterator with the smallest next value
            BufferedIterator firstBuf = minHeap.poll();

            // Write the smallest value to the output file
            bw.write(firstBuf.next());
            bw.newLine();

            // If the iterator has more elements, add it back to the heap
            if (firstBuf.hasNext()) {
                minHeap.add(firstBuf);
            } else {
                // Otherwise, close it
                firstBuf.close();
            }
        }

        // Close the output file
        FileIOUtils.closeWriter(bw);
    }
}
