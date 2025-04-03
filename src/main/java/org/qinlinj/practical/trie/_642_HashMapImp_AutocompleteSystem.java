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

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            map.put(currSentence, map.getOrDefault(currSentence, 0) + 1);
            currSentence = "";
        } else {
            currSentence += c;
            
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
