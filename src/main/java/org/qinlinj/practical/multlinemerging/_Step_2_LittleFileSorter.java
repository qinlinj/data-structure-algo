package org.qinlinj.practical.multlinemerging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Step 2: Sort each small file individually
 * This is the second step of external sorting where we sort each chunk in memory
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

    public void sortEachFile(String dirName) throws IOException {
        File dir = new File(dirName);
        File[] littleFiles = dir.listFiles();

        for (int i = 0; i < littleFiles.length; i++) {
            BufferedReader br = FileIOUtils.getReader(littleFiles[i].getPath());
            List<String> words = new ArrayList<>();
            String line = null;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }

            FileIOUtils.closeReader(br);

            Collections.sort(words);

            BufferedWriter bw = FileIOUtils.getWriter("data/top100/sorted_data/" + i);
            for (String word : words) {
                bw.write(word);
                bw.newLine();
            }
            FileIOUtils.closeWriter(bw);
        }
    }
}
