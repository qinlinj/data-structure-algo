package org.qinlinj.practical.multlinemerging;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

/**
 * External Sorting Algorithm Implementation
 * <p>
 * External sorting is used when the data being sorted is too large to fit into memory.
 * It works by:
 * 1. Splitting the large file into smaller chunks that fit in memory
 * 2. Sorting each chunk individually
 * 3. Merging the sorted chunks using a k-way merge
 * <p>
 * Advantages of External Sorting:
 * - Can handle datasets much larger than available memory
 * - Efficient for disk-based sorting operations
 * - Scales well with large datasets
 * - Minimizes disk I/O operations
 * <p>
 * The algorithm has a time complexity of O(N log N) where N is the total number of elements,
 * but with the additional cost of disk I/O operations which is the main bottleneck.
 * <p>
 * This implementation demonstrates a complete external sorting process through 4 steps:
 * Step 0: Generate random words for testing
 * Step 1: Split large file into smaller chunks
 * Step 2: Sort each chunk individually
 * Step 3: Merge all sorted chunks using a min-heap
 * Step 4: Find the top 100 most frequent words from the sorted result
 */

/**
 * Step 0: Generate a file with random words for testing the external sort
 * This class creates a dataset of random words made from characters a-g
 */
public class _Step_0_WordsGenerator {
    private static Random r = new Random();

    /**
     * Main method to generate random words and write them to a file
     * Time Complexity: O(n*m) where n is the number of words and m is the max length of each word
     * Space Complexity: O(m) where m is the max length of a word
     */
    public static void main(String[] args) throws IOException {
        // Initialize a writer to the output file
        BufferedWriter writer = FileIOUtils.getWriter("data/top100/words.txt");

        // Define the character set we'll use to generate words
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        int m = chars.length;

        // Generate 10,000 random words
        for (int i = 0; i < 10000; i++) {
            StringBuilder line = new StringBuilder();

            // Each word has a random length between 0 and 15
            for (int j = 0; j < r.nextInt(16); j++) {
                // Randomly select a character from our character set
                line.append(chars[r.nextInt(m)]);
            }

            // Skip empty lines
            if (line.length() == 0) continue;

            // Write the word to the file
            writer.write(line.toString());
            writer.newLine();
        }

        // Close the writer to flush and release resources
        FileIOUtils.closeWriter(writer);
    }
}
