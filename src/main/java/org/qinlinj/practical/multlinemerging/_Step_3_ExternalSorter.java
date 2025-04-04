package org.qinlinj.practical.multlinemerging;

import java.io.BufferedReader;
import java.io.File;
import java.util.*;

public class _Step_3_ExternalSorter {
    public void mergeSort(String dirName) throws Exception {

        File dir = new File(dirName);
        File[] children = dir.listFiles();

        PriorityQueue<BufferedIterator> minHeap = new PriorityQueue<>(children.length, new Comparator<BufferedIterator>() {
            @Override
            public int compare(BufferedIterator o1, BufferedIterator o2) {
                return o1.next().compareTo(o2.next());
            }
        });
        
        for (File file : children) {
            BufferedReader br = FileIOUtils.getReader(file.getName());
            BufferedIterator buf = new BufferedIterator(br);
            if (buf.hasNext()) {
                minHeap.add(buf);
            } else {
                buf.close();
            }
        }
    }
}
