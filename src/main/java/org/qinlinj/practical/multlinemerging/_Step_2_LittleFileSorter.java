package org.qinlinj.practical.multlinemerging;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class _Step_2_LittleFileSorter {
    public void sortEachFile(String dirName) throws IOException {
        File dir = new File(dirName);
        File[] littleFiles = dir.listFiles();

        for (int i = 0; i < littleFiles.length; i++) {
            BufferedReader br = FileIOUtils.getReader(littleFiles[i].getName());
            List<String> words = new ArrayList<>();
            String line = null;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }

            FileIOUtils.closeReader(br);

            Collections.sort(words);


        }

    }
}
