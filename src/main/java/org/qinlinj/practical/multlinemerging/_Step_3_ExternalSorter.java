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
    public static void main(String[] args) throws Exception {
        String dirName = "data/top100/sorted_data/";
        new _Step_3_ExternalSorter().mergeSort(dirName);
    }

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
            BufferedReader br = FileIOUtils.getReader(file.getPath());
            BufferedIterator buf = new BufferedIterator(br);
            if (buf.hasNext()) {
                minHeap.add(buf);
            } else {
                buf.close();
            }
        }

        BufferedWriter bw = FileIOUtils.getWriter("data/top100/sorted_words.txt");
        while (!minHeap.isEmpty()) {
            BufferedIterator firstBuf = minHeap.poll();
            bw.write(firstBuf.next());
            bw.newLine();
            if (firstBuf.hasNext()) {
                minHeap.add(firstBuf);
            } else {
                firstBuf.close();
            }
        }

        FileIOUtils.closeWriter(bw);
    }
}
