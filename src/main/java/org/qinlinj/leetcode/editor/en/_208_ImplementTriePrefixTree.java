package org.qinlinj.leetcode.editor.en;

import java.util.*;

// [208] Implement Trie (Prefix Tree)
public class _208_ImplementTriePrefixTree {

    public static void main(String[] args) {
//        Solution solution = new _208_ImplementTriePrefixTree().new Solution();
        // put your test code here

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Trie {
        private Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String word) {
            Node curr = root;
            for (Character o : word.toCharArray()) {
                if (!curr.children.containsKey(o)) {
                    curr.children.put(o, new Node());
                }
                curr = curr.children.get(o);
            }
            curr.isWord = true;
        }

        public boolean search(String word) {
            Node curr = root;
            for (Character o : word.toCharArray()) {
                if (!curr.children.containsKey(o)) {
                    return false;
                }
                curr = curr.children.get(o);
            }
            return curr.isWord;
        }

        public boolean startsWith(String prefix) {
            Node curr = root;
            for (Character o : prefix.toCharArray()) {
                if (!curr.children.containsKey(o)) {
                    return false;
                }
                curr = curr.children.get(o);
            }
            return true;
        }

        class Node {
            public Map<Character, Node> children;
            public boolean isWord;

            public Node() {
                this.children = new HashMap<>();
                this.isWord = false;
            }
        }
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    //leetcode submit region end(Prohibit modification and deletion)


}


//A trie (pronounced as "try") or prefix tree is a tree data structure used to
//efficiently store and retrieve keys in a dataset of strings. There are various 
//applications of this data structure, such as autocomplete and spellchecker. 
//
// Implement the Trie class: 
//
// 
// Trie() Initializes the trie object. 
// void insert(String word) Inserts the string word into the trie. 
// boolean search(String word) Returns true if the string word is in the trie (
//i.e., was inserted before), and false otherwise. 
// boolean startsWith(String prefix) Returns true if there is a previously 
//inserted string word that has the prefix prefix, and false otherwise. 
// 
//
// 
// Example 1: 
//
// 
//Input
//["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//Output
//[null, null, true, false, true, null, true]
//
//Explanation
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // return True
//trie.search("app");     // return False
//trie.startsWith("app"); // return True
//trie.insert("app");
//trie.search("app");     // return True
// 
//
// 
// Constraints: 
//
// 
// 1 <= word.length, prefix.length <= 2000 
// word and prefix consist only of lowercase English letters. 
// At most 3 * 10⁴ calls in total will be made to insert, search, and 
//startsWith. 
// 
//
// Related TopicsHash Table | String | Design | Trie 
//
// 👍 11958, 👎 147bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
