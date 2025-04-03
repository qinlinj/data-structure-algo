package org.qinlinj.practical.trie;

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
}
