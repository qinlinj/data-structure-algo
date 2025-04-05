package org.qinlinj.practical.multlinemerging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Step 1: Split the large file into smaller chunks
 * This is the first step of external sorting where we divide the large input file
 * into smaller files that can fit in memory
 */
public class _Step_1_FileSplit {
    /**
     * Main method to split the input file
     * Time Complexity: O(n) where n is the number of lines in the input file
     * Space Complexity: O(1) as we process line by line
     */
    public static void main(String[] args) throws IOException {
        String fileName = "data/top100/words.txt";
        new _Step_1_FileSplit().splitFile(fileName);
    }

    /**
     * Split the input file into smaller chunks of approximately 2KB each
     * <p>
     * Example visualization:
     * Original file (words.txt):  [abcde, fghij, klmno, pqrst, uvwxy, zabcd, ...]
     * After splitting:
     * file0: [abcde, fghij]  (size ~2KB)
     * file1: [klmno, pqrst]  (size ~2KB)
     * file2: [uvwxy, zabcd]  (size ~2KB)
     * ...
     * <p>
     * Time Complexity: O(n) where n is the number of lines in the input file
     * Space Complexity: O(1) as we process line by line
     */
    public void splitFile(String fileName) throws IOException {
        int fileNum = 0;  // Counter for the small files we'll create
        String fileSuffix = "data/top100/raw_data/";
        String littleFileName = fileSuffix + fileNum;

        long totalSize = 0;  // Track the current small file's size

        // Create the writer for the first small file
        BufferedWriter bw = FileIOUtils.getWriter(littleFileName);

        // Read the large input file line by line
        BufferedReader br = FileIOUtils.getReader(fileName);
        String line = null;
        while ((line = br.readLine()) != null) {
            // If the current small file reaches 2KB, create a new file
            if (totalSize >= 2 * 1024) {
                FileIOUtils.closeWriter(bw);

                fileNum++;
                littleFileName = fileSuffix + fileNum;
                bw = FileIOUtils.getWriter(littleFileName);
                totalSize = 0;
            }

            // Add the current line's length to the total size
            totalSize += line.length();

            // Write the line to the current small file
            bw.write(line);
            bw.newLine();
        }

        // Close all resources
        FileIOUtils.closeReader(br);
        FileIOUtils.closeWriter(bw);
    }
}

