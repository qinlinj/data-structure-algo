package org.qinlinj.practical.trie;

import java.util.*;

public class _642_HashMapImp_AutocompleteSystem {
    private Map<String, Integer> map;

    private String currSentence = "";

    public _642_HashMapImp_AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], times[i]);
        }
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
