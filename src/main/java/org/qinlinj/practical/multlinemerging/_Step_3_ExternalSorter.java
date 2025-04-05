package org.qinlinj.practical.multlinemerging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.*;

/**
 * Step 3: Merge all sorted files using a k-way merge with a min-heap
 * This is the key step of external sorting where we merge all sorted chunks
 * to produce the final sorted output
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
