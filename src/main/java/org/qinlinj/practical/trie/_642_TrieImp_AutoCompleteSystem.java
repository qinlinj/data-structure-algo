package org.qinlinj.practical.trie;

import java.util.*;

public class _642_TrieImp_AutoCompleteSystem {
    private TrieNode root;
    private String currSentence = "";

    public _642_TrieImp_AutoCompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
    }

    public void insert(String s, int times) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        curr.times += times;
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            insert(currSentence, 1);
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

    private class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int times = 0;
    }
}
