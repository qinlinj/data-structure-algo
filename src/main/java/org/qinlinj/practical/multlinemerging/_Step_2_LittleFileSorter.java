package org.qinlinj.practical.multlinemerging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Step 2: Sort each small file individually
 * External Sorting Algorithm
 * <p>
 * External sorting is an efficient algorithm for sorting large volumes of data that cannot fit entirely in the
 * computer's main memory (RAM). The algorithm works by breaking the large dataset into smaller manageable chunks,
 * sorting each chunk individually in memory, and then merging these sorted chunks to produce the final sorted output.
 * <p>
 * Key advantages of External Sorting:
 * 1. Memory Efficiency: Can process datasets much larger than available RAM
 * 2. Scalability: Works well with extremely large files (gigabytes/terabytes)
 * 3. I/O Optimization: Minimizes disk read/write operations
 * 4. Practical Application: Essential for database systems, big data processing, and file systems
 * 5. Predictable Performance: Maintains O(N log N) time complexity even for large datasets
 * <p>
 * Concept and Principles:
 * - External sorting follows a divide-and-conquer approach
 * - The algorithm has two main phases: the "sorting phase" and the "merging phase"
 * - In the sorting phase, we divide data into manageable chunks and sort each chunk
 * - In the merging phase, we combine these sorted chunks using a k-way merge algorithm
 * - A min-heap data structure is often used to efficiently perform the k-way merge
 * <p>
 * This class (_Step_2_LittleFileSorter) implements the "sorting phase" of the external sorting algorithm,
 * where each small file is sorted individually in memory before the merge phase.
 */
public class _Step_2_LittleFileSorter {
    /**
     * Main method to sort each small file
     * Time Complexity: O(k * m log m) where k is the number of small files and m is the average number of lines per file
     * Space Complexity: O(m) where m is the maximum number of lines in any small file
     */
    public static void main(String[] args) throws IOException {
        String dir = "data/top100/raw_data";
        new _Step_2_LittleFileSorter().sortEachFile(dir);
    }

    /**
     * Sort each file in the directory individually
     * <p>
     * Example visualization:
     * Before sorting:
     * file0: [cat, apple, dog]
     * file1: [banana, zebra, elephant]
     * <p>
     * After sorting:
     * sorted_data/0: [apple, cat, dog]
     * sorted_data/1: [banana, elephant, zebra]
     * <p>
     * Time Complexity: O(k * m log m) where k is the number of files and m is the average number of lines per file
     * Space Complexity: O(m) for storing the words of each file in memory
     */
    public void sortEachFile(String dirName) throws IOException {
        File dir = new File(dirName);
        File[] littleFiles = dir.listFiles();

        // Process each small file
        for (int i = 0; i < littleFiles.length; i++) {
            // Read all words from the current file
            BufferedReader br = FileIOUtils.getReader(littleFiles[i].getPath());
            List<String> words = new ArrayList<>();
            String line = null;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }

            FileIOUtils.closeReader(br);

            // Sort the words in memory (using Java's built-in sort, which is typically a variant of mergesort)
            Collections.sort(words);

            // Write the sorted words to a new file
            BufferedWriter bw = FileIOUtils.getWriter("data/top100/sorted_data/" + i);
            for (String word : words) {
                bw.write(word);
                bw.newLine();
            }
            FileIOUtils.closeWriter(bw);
        }
    }
}