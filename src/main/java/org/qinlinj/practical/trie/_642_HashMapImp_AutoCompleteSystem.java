package org.qinlinj.practical.trie;

import java.util.*;

public class _642_HashMapImp_AutoCompleteSystem {
    private Map<String, Integer> map;

    private String currSentence = "";

    public _642_HashMapImp_AutoCompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            map.put(currSentence, map.getOrDefault(currSentence, 0) + 1);
            currSentence = "";
        } else {
            currSentence += c;

            List<SentenceInfo> list = new ArrayList<>();
            for (String sentence : map.keySet()) { // O(n*k)
                if (sentence.startsWith(currSentence)) {
                    list.add(new SentenceInfo(sentence, map.get(sentence)));
                }
            }

            Collections.sort(list, new Comparator<SentenceInfo>() {
                @Override
                public int compare(SentenceInfo o1, SentenceInfo o2) {
                    return o1.time == o2.time ?
                            o1.content.compareTo(o2.content) :
                            o2.time - o1.time;
                }
            });
            for (int i = 0; i < Math.min(3, list.size()); i++) {
                res.add(list.get(i).content);
            }
        }
        return res;
    }

    private class SentenceInfo {
        String content;
        int time;

        SentenceInfo(String content, int time) {
            this.content = content;
            this.time = time;
        }
    }
}
