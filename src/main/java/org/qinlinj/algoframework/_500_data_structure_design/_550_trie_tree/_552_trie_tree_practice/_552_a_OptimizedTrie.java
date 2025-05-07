package org.qinlinj.algoframework._500_data_structure_design._550_trie_tree._552_trie_tree_practice;

/**
 * OPTIMIZED TRIE FOR ALGORITHM COMPETITIONS
 * <p>
 * Key Points:
 * - Standard Trie implementations can be optimized for specific use cases
 * - For LeetCode problems, we can make these optimizations:
 * 1. Reduce children array size from 256 to 26 when only lowercase letters are used
 * 2. Remove generic type parameters when they're not needed
 * 3. Adapt the implementation based on problem constraints
 * - These optimizations reduce both time and space complexity
 * - Smaller children array (26 vs 256) significantly reduces memory footprint
 * - Removing generics gives a slight performance boost for algorithm competitions
 * <p>
 * This class provides optimized versions of TrieNode, TrieMap, and TrieSet for
 * algorithm competitions where the input is limited to lowercase English letters.
 */
public class _552_a_OptimizedTrie {

    // Main method to demonstrate the optimized Trie
    public static void main(String[] args) {
        System.out.println("Optimized Trie for LeetCode Problems");
        System.out.println("===================================");

        TrieSet set = new TrieSet();
        set.add("apple");
        set.add("application");
        set.add("banana");

        System.out.println("Contains 'apple': " + set.contains("apple"));
        System.out.println("Contains 'app': " + set.contains("app"));
        System.out.println("Has prefix 'app': " + set.hasKeyWithPrefix("app"));
        System.out.println("Shortest prefix of 'apple': " + set.shortestPrefixOf("apple"));
        System.out.println("Matches pattern 'a.p.e': " + set.hasKeyWithPattern("a.p.e"));
    }

    /**
     * Optimized TrieNode for competitive programming with 26 lowercase letters
     */
    static class TrieNode {
        // Value stored at this node (null if not end of key)
        Object val = null;

        // Arrays size reduced to 26 (a-z) instead of 256 (all ASCII)
        TrieNode[] children = new TrieNode[26];

        // Convert char to index (a->0, b->1, ..., z->25)
        public int getIndex(char c) {
            return c - 'a';
        }
    }

    /**
     * Optimized TrieMap for competitive programming
     */
    static class TrieMap {
        private int size = 0;
        private TrieNode root = null;

        // Basic operations: put, get, containsKey

        public void put(String key, Object val) {
            if (!containsKey(key)) {
                size++;
            }
            root = put(root, key, val, 0);
        }

        private TrieNode put(TrieNode node, String key, Object val, int i) {
            if (node == null) {
                node = new TrieNode();
            }
            if (i == key.length()) {
                node.val = val;
                return node;
            }
            int idx = node.getIndex(key.charAt(i));
            node.children[idx] = put(node.children[idx], key, val, i + 1);
            return node;
        }

        public Object get(String key) {
            TrieNode node = getNode(root, key);
            return node == null ? null : node.val;
        }

        public boolean containsKey(String key) {
            return get(key) != null;
        }

        // Prefix operations

        public boolean hasKeyWithPrefix(String prefix) {
            return getNode(root, prefix) != null;
        }

        public String shortestPrefixOf(String query) {
            TrieNode p = root;

            for (int i = 0; i < query.length(); i++) {
                if (p == null) {
                    return "";
                }
                if (p.val != null) {
                    return query.substring(0, i);
                }
                int idx = p.getIndex(query.charAt(i));
                p = p.children[idx];
            }

            if (p != null && p.val != null) {
                return query;
            }
            return "";
        }

        // Helper methods

        private TrieNode getNode(TrieNode node, String key) {
            TrieNode p = node;
            for (int i = 0; i < key.length(); i++) {
                if (p == null) {
                    return null;
                }
                int idx = p.getIndex(key.charAt(i));
                p = p.children[idx];
            }
            return p;
        }

        public int size() {
            return size;
        }
    }

    /**
     * Optimized TrieSet for competitive programming
     */
    static class TrieSet {
        private final TrieMap map = new TrieMap();

        public void add(String key) {
            map.put(key, Boolean.TRUE);
        }

        public boolean contains(String key) {
            return map.containsKey(key);
        }

        public boolean hasKeyWithPrefix(String prefix) {
            return map.hasKeyWithPrefix(prefix);
        }

        public String shortestPrefixOf(String query) {
            return map.shortestPrefixOf(query);
        }

        public boolean hasKeyWithPattern(String pattern) {
            return hasKeyWithPattern(map.root, pattern, 0);
        }

        private boolean hasKeyWithPattern(TrieNode node, String pattern, int i) {
            if (node == null) {
                return false;
            }
            if (i == pattern.length()) {
                return node.val != null;
            }

            char c = pattern.charAt(i);
            if (c == '.') {
                for (int j = 0; j < 26; j++) {
                    if (hasKeyWithPattern(node.children[j], pattern, i + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                return hasKeyWithPattern(node.children[node.getIndex(c)], pattern, i + 1);
            }
        }
    }
}