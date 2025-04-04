package org.qinlinj.practical.multlinemerging;

import java.io.BufferedReader;
import java.util.*;

public class _Step_4_Top_100_Words {
    public String[] top_100(String fileName) throws Exception {
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(100, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.cnt - o2.cnt;
            }
        });
        String prevWord = null;
        int prevCnt = 0;

        BufferedReader br = FileIOUtils.getReader(fileName);
        String currWord = null;

        while ((currWord = br.readLine()) != null) {
            if (!currWord.equals(prevWord)) {
                if (minHeap.size() < 100) {
                    minHeap.add(new Pair(prevWord, prevCnt));
                } else if (prevCnt > minHeap.peek().cnt) {
                    minHeap.remove();
                    minHeap.add(new Pair(prevWord, prevCnt));
                }

                prevWord = currWord;
                prevCnt = 0;
            }
            prevCnt++;
        }
    }

    class Pair {
        String word;
        int cnt;

        Pair(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }

}
