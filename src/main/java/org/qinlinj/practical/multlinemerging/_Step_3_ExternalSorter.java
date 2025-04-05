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
 * <p>
 * Step 3: Merge all sorted files using a k-way merge with a min-heap
 * <p>
 * This class implements the merge phase of external sorting where all previously sorted
 * chunks are merged into a single, fully-sorted output file. This is the most critical step
 * in external sorting as it combines the partial results while maintaining memory efficiency.
 * <p>
 * The k-way merge algorithm used here is optimal for external sorting because:
 * - It processes data in a streaming fashion, never loading all data into memory
 * - It maintains sorted order with minimal comparisons using a priority queue (min-heap)
 * - It works with an arbitrary number of input chunks
 */
public class _Step_3_ExternalSorter {
    /**
     * Main method to execute the k-way merge process
     * <p>
     * Time Complexity: O(n log k) where:
     * - n is the total number of elements across all files
     * - k is the number of separate sorted files
     * - log k represents the cost of heap operations
     * <p>
     * Space Complexity: O(k) where k is the number of files
     * - We only keep k elements (one from each file) in memory at any time
     */
    public static void main(String[] args) throws Exception {
        // Directory containing the sorted chunks from Step 2
        String dirName = "data/top100/sorted_data/";
        new _Step_3_ExternalSorter().mergeSort(dirName);
    }

    /**
     * Perform a k-way merge of all sorted files using a min-heap
     * <p>
     * This method implements the efficient k-way merge algorithm that:
     * 1. Initializes a min-heap with the first element from each sorted chunk
     * 2. Repeatedly extracts the minimum element and writes it to the output
     * 3. Replaces the extracted element with the next element from its chunk
     * 4. Continues until all elements from all chunks are processed
     * <p>
     * Detailed Visual Example of K-Way Merge:
     * ---------------------------------------
     * Input: 3 sorted files
     * file0: [apple, cat, dog]
     * file1: [banana, elephant, zebra]
     * file2: [ant, bear, tiger]
     * <p>
     * Step-by-step visualization:
     * <p>
     * 1. Initialize min-heap with first element from each file:
     * Min-heap: [ant, apple, banana]
     * <p>
     * 2. Extract minimum (ant) and write to output:
     * Output: [ant]
     * Read next element from file2: "bear"
     * Min-heap: [apple, banana, bear]
     * <p>
     * 3. Extract minimum (apple) and write to output:
     * Output: [ant, apple]
     * Read next element from file0: "cat"
     * Min-heap: [banana, bear, cat]
     * <p>
     * 4. Extract minimum (banana) and write to output:
     * Output: [ant, apple, banana]
     * Read next element from file1: "elephant"
     * Min-heap: [bear, cat, elephant]
     * <p>
     * 5. Continue this process until all elements are processed...
     * <p>
     * Final output: [ant, apple, banana, bear, cat, dog, elephant, tiger, zebra]
     * ---------------------------------------
     * <p>
     * Time Complexity: O(n log k) where:
     * - n is the total number of elements across all files
     * - k is the number of files being merged
     * - Each heap operation (poll/add) costs O(log k)
     * - We perform these operations n times (once for each element)
     * <p>
     * Space Complexity: O(k) where k is the number of files
     * - The min-heap stores exactly one element from each file
     * - We maintain k file readers (one per input file)
     */
    public void mergeSort(String dirName) throws Exception {
        // Get all sorted files from the directory
        File dir = new File(dirName);
        File[] children = dir.listFiles();

        // Create a min-heap (priority queue) to efficiently find the minimum element
        // This heap will store BufferedIterator objects, which wrap file readers and provide access to lines
        PriorityQueue<BufferedIterator> minHeap = new PriorityQueue<>(children.length, new Comparator<BufferedIterator>() {
            @Override
            public int compare(BufferedIterator o1, BufferedIterator o2) {
                // Compare based on the next available string in each iterator
                return o1.next().compareTo(o2.next());
            }
        });

        // Initialize the heap with the first element from each sorted file
        for (File file : children) {
            // Create a reader for the current file
            BufferedReader br = FileIOUtils.getReader(file.getPath());

            // Wrap it in a BufferedIterator for easier access
            BufferedIterator buf = new BufferedIterator(br);

            // Add to the heap if the file is not empty
            if (buf.hasNext()) {
                minHeap.add(buf);
            } else {
                // Close empty files immediately
                buf.close();
            }
        }

        // Create a writer for the final merged output file
        BufferedWriter bw = FileIOUtils.getWriter("data/top100/sorted_words.txt");

        // Perform the k-way merge as long as there are elements in the heap
        while (!minHeap.isEmpty()) {
            // Extract the iterator containing the smallest element
            BufferedIterator firstBuf = minHeap.poll();

            // Write the smallest element to the output file
            // The next() method both retrieves and advances the iterator
            bw.write(firstBuf.next());
            bw.newLine();

            // If this iterator has more elements, put it back in the heap
            if (firstBuf.hasNext()) {
                minHeap.add(firstBuf);
            } else {
                // If no more elements, close the associated file
                firstBuf.close();
            }
        }

        // Close the output file to ensure all data is flushed to disk
        FileIOUtils.closeWriter(bw);

        // At this point, all elements from all chunks have been merged
        // into a single sorted file "sorted_words.txt"
    }
}