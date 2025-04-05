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
     * Step 2: Sort each small file individually
     * <p>
     * This class handles the sorting phase of the external sorting algorithm. It processes
     * each small chunk file (created in Step 1) and sorts its contents entirely in memory.
     * <p>
     * In external sorting, this step is critical because:
     * - It ensures each individual chunk is sorted efficiently using in-memory algorithms
     * - It prepares the data for the efficient k-way merge in the next step
     * - It balances memory usage by only sorting manageable chunks at a time
     */
    public static void main(String[] args) throws IOException {
        String dir = "data/top100/raw_data";
        new _Step_2_LittleFileSorter().sortEachFile(dir);
    }

    /**
     * Sort each file in the directory individually
     * <p>
     * This method:
     * 1. Reads each small file completely into memory
     * 2. Sorts the contents using an in-memory sorting algorithm
     * 3. Writes the sorted results to new files in the sorted_data directory
     * <p>
     * Visual Example of External Sorting (Sorting Phase):
     * -----------------------------------------------------
     * Original Large File (too big for memory):
     * [dog, zebra, apple, cat, elephant, fox, banana, hawk, ...]
     * <p>
     * After Step 1 (File Splitting):
     * file0: [dog, zebra, apple]
     * file1: [cat, elephant, fox]
     * file2: [banana, hawk, ...]
     * <p>
     * After Step 2 (This class - Little File Sorting):
     * sorted_data/0: [apple, dog, zebra]
     * sorted_data/1: [cat, elephant, fox]
     * sorted_data/2: [banana, hawk, ...]
     * <p>
     * (Later, Step 3 will merge these sorted files efficiently)
     * -----------------------------------------------------
     * <p>
     * Time Complexity: O(k * m log m) where k is the number of files and m is the average number of lines per file
     * - For each file: Reading takes O(m) time
     * - Sorting takes O(m log m) time using Java's Collections.sort
     * - Writing takes O(m) time
     * - This is done for k files, so total complexity is O(k * m log m)
     * <p>
     * Space Complexity: O(m) for storing the words of each file in memory
     * - Only one file's contents are loaded into memory at a time
     * - The ArrayList<String> uses O(m) space for m words
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