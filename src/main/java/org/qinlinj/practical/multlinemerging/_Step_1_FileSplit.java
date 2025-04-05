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
    public static void main(String[] args) throws IOException {
        String fileName = "data/top100/words.txt";
        new _Step_1_FileSplit().splitFile(fileName);
    }

    public void splitFile(String fileName) throws IOException {
        int fileNum = 0;
        String fileSuffix = "data/top100/raw_data/";
        String littleFileName = fileSuffix + fileNum;

        long totalSize = 0;

        BufferedWriter bw = FileIOUtils.getWriter(littleFileName);

        BufferedReader br = FileIOUtils.getReader(fileName);
        String line = null;
        while ((line = br.readLine()) != null) {
            if (totalSize >= 2 * 1024) {
                FileIOUtils.closeWriter(bw);

                fileNum++;
                littleFileName = fileSuffix + fileNum;
                bw = FileIOUtils.getWriter(littleFileName);
                totalSize = 0;
            }

            totalSize += line.length();

            bw.write(line);
            bw.newLine();
        }

        FileIOUtils.closeReader(br);
        FileIOUtils.closeWriter(bw);
    }
}
