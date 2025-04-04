package org.qinlinj.practical.multlinemerging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class _Step_1_FileSplit {
    public void splitFile(String fileName) throws IOException {
        int fileNum = 0;
        String fileSuffix = "data/top100/raw_data/";
        String littleFileName = fileSuffix + fileNum;

        long totalSize = 0;

        BufferedWriter bw = FileIOUtils.getWriter(littleFileName);

        BufferedReader br = FileIOUtils.getReader(fileName);
        String line = null;
        while ((line = br.readLine()) != null) {
            if (totalSize >= 512 * 1024) {
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
