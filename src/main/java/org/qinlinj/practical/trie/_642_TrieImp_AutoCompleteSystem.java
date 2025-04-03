package org.qinlinj.practical.trie;

import java.util.*;

public class _642_TrieImp_AutoCompleteSystem {
    public _642_TrieImp_AutoCompleteSystem() {
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
