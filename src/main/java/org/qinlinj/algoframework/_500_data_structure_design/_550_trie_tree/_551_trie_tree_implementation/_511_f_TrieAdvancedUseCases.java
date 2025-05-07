package org.qinlinj.algoframework._500_data_structure_design._550_trie_tree._551_trie_tree_implementation;

import java.util.*;

/**
 * TRIE ADVANCED USE CASES
 * <p>
 * Key Points:
 * - This class demonstrates practical advanced applications of Trie data structure
 * - Implementations include:
 * 1. Autocomplete system with frequency-based suggestions
 * 2. Spell checker with edit distance for word correction
 * 3. Word filtering system using a Trie
 * 4. Boggle word finder using Trie for dictionary lookup
 * - Each implementation shows how Trie's prefix operations provide significant
 * advantages over alternative data structures
 */
public class _511_f_TrieAdvancedUseCases {

    public static void main(String[] args) {
        // Demonstrate autocomplete with frequency
        System.out.println("AUTOCOMPLETE SYSTEM DEMONSTRATION");
        System.out.println("=================================");
        AutocompleteSystem autocomplete = new AutocompleteSystem();
        autocomplete.addWord("apple", 10);
        autocomplete.addWord("application", 15);
        autocomplete.addWord("approach", 5);
        autocomplete.addWord("banana", 7);
        autocomplete.addWord("ball", 8);
        autocomplete.addWord("basketball", 12);

        System.out.println("\nSuggestions for 'app':");
        List<String> appResults = autocomplete.getSuggestions("app", 3);
        for (String result : appResults) {
            System.out.println("- " + result);
        }

        System.out.println("\nSuggestions for 'ba':");
        List<String> baResults = autocomplete.getSuggestions("ba", 3);
        for (String result : baResults) {
            System.out.println("- " + result);
        }

        // Demonstrate spell checker
        System.out.println("\n\nSPELL CHECKER DEMONSTRATION");
        System.out.println("===========================");
        SpellChecker spellChecker = new SpellChecker();
        spellChecker.addToDictionary("dictionary");
        spellChecker.addToDictionary("hello");
        spellChecker.addToDictionary("world");
        spellChecker.addToDictionary("program");
        spellChecker.addToDictionary("computer");
        spellChecker.addToDictionary("keyboard");
        spellChecker.addToDictionary("mouse");

        String[] wordsToCheck = {"hello", "worlb", "dictionry", "cmoputer", "keyborad"};
        for (String word : wordsToCheck) {
            System.out.println("Checking '" + word + "': " +
                    (spellChecker.isCorrect(word) ? "Correct" : "Incorrect"));
            if (!spellChecker.isCorrect(word)) {
                List<String> suggestions = spellChecker.getSuggestions(word, 3);
                System.out.println("  Suggestions: " + suggestions);
            }
        }

        // Demonstrate word filter
        System.out.println("\n\nWORD FILTER DEMONSTRATION");
        System.out.println("=========================");
        WordFilter filter = new WordFilter();
        filter.addBlockedWord("spam");
        filter.addBlockedWord("scam");
        filter.addBlockedWord("inappropriate");

        String[] textsToFilter = {
                "This is a normal message.",
                "This message contains spam content.",
                "Watch out for scams online.",
                "This is inappropriate behavior."
        };

        for (String text : textsToFilter) {
            System.out.println("Original: " + text);
            System.out.println("Filtered: " + filter.filterText(text));
            System.out.println();
        }

        // Demonstrate Boggle solver
        System.out.println("\nBOGGLE SOLVER DEMONSTRATION");
        System.out.println("==========================");
        BoggleSolver boggle = new BoggleSolver();
        boggle.addToDictionary("cat");
        boggle.addToDictionary("ate");
        boggle.addToDictionary("eat");
        boggle.addToDictionary("tea");
        boggle.addToDictionary("toe");
        boggle.addToDictionary("note");

        char[][] board = {
                {'c', 'a', 't'},
                {'r', 'e', 'o'},
                {'n', 'o', 't'}
        };

        List<String> foundWords = boggle.findWords(board);
        System.out.println("Board:");
        for (char[] row : board) {
            System.out.println(new String(row));
        }

        System.out.println("\nFound words:");
        for (String word : foundWords) {
            System.out.println("- " + word);
        }
    }

    /**
     * Autocomplete System with Frequency-Based Suggestions
     * Uses a Trie to store words and their frequencies,
     * then returns suggestions based on frequency ranking.
     */
    static class AutocompleteSystem {
        private TrieNode root = new TrieNode();

        // Add a word with its frequency to the system
        public void addWord(String word, int frequency) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                current.children.putIfAbsent(c, new TrieNode());
                current = current.children.get(c);
            }
            current.isEndOfWord = true;
            current.frequency = frequency;
        }

        // Get top N suggestions for a prefix, sorted by frequency
        public List<String> getSuggestions(String prefix, int n) {
            List<Pair<String, Integer>> candidates = new ArrayList<>();
            TrieNode current = root;

            // Navigate to the node corresponding to the prefix
            for (char c : prefix.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    return new ArrayList<>(); // No matches
                }
                current = current.children.get(c);
            }

            // Collect all words with the given prefix
            collectWords(current, new StringBuilder(prefix), candidates);

            // Sort by frequency (descending)
            candidates.sort((a, b) -> b.second.compareTo(a.second));

            // Return top N results
            List<String> result = new ArrayList<>();
            for (int i = 0; i < Math.min(n, candidates.size()); i++) {
                result.add(candidates.get(i).first);
            }
            return result;
        }

        private void collectWords(TrieNode node, StringBuilder prefix, List<Pair<String, Integer>> results) {
            if (node.isEndOfWord) {
                results.add(new Pair<>(prefix.toString(), node.frequency));
            }

            for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
                prefix.append(entry.getKey());
                collectWords(entry.getValue(), prefix, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

        private static class TrieNode {
            Map<Character, TrieNode> children = new HashMap<>();
            boolean isEndOfWord = false;
            int frequency = 0;
        }

        private static class Pair<F, S> {
            F first;
            S second;

            Pair(F first, S second) {
                this.first = first;
                this.second = second;
            }
        }
    }

    /**
     * Spell Checker with Edit Distance
     * Uses a Trie to store dictionary words and finds suggestions
     * for misspelled words based on edit distance.
     */
    static class SpellChecker {
        private _511_c_TrieSet dictionary = new _511_c_TrieSet();

        // Add a word to the dictionary
        public void addToDictionary(String word) {
            dictionary.add(word.toLowerCase());
        }

        // Check if a word is correctly spelled
        public boolean isCorrect(String word) {
            return dictionary.contains(word.toLowerCase());
        }

        // Get suggestions for a misspelled word
        public List<String> getSuggestions(String word, int maxSuggestions) {
            word = word.toLowerCase();
            List<String> allWords = new ArrayList<>();

            // Get all words from dictionary (for a real implementation, 
            // you would use a more efficient approach)
            List<String> dictionaryWords = dictionary.keysWithPrefix("");

            // Find words with edit distance <= 2
            List<Pair<String, Integer>> candidates = new ArrayList<>();
            for (String dictWord : dictionaryWords) {
                int distance = calculateEditDistance(word, dictWord);
                if (distance <= 2) {
                    candidates.add(new Pair<>(dictWord, distance));
                }
            }

            // Sort by edit distance
            candidates.sort((a, b) -> a.second.compareTo(b.second));

            // Return top suggestions
            List<String> result = new ArrayList<>();
            for (int i = 0; i < Math.min(maxSuggestions, candidates.size()); i++) {
                result.add(candidates.get(i).first);
            }
            return result;
        }

        // Calculate Levenshtein distance between two strings
        private int calculateEditDistance(String s1, String s2) {
            int m = s1.length();
            int n = s2.length();
            int[][] dp = new int[m + 1][n + 1];

            for (int i = 0; i <= m; i++) {
                dp[i][0] = i;
            }

            for (int j = 0; j <= n; j++) {
                dp[0][j] = j;
            }

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    }
                }
            }

            return dp[m][n];
        }

        private static class Pair<F, S> {
            F first;
            S second;

            Pair(F first, S second) {
                this.first = first;
                this.second = second;
            }
        }
    }

    /**
     * Word Filter System
     * Uses a Trie to efficiently identify and filter out blocked words
     * from text content.
     */
    static class WordFilter {
        private TrieNode root = new TrieNode();

        // Add a word to the blocked list
        public void addBlockedWord(String word) {
            TrieNode current = root;
            for (char c : word.toLowerCase().toCharArray()) {
                current.children.putIfAbsent(c, new TrieNode());
                current = current.children.get(c);
            }
            current.isEndOfWord = true;
        }

        // Filter text by replacing blocked words with asterisks
        public String filterText(String text) {
            String[] words = text.split("\\s+");
            StringBuilder result = new StringBuilder();

            for (String word : words) {
                // Check if the word is blocked
                if (isBlocked(word.toLowerCase())) {
                    // Replace with asterisks
                    result.append("*".repeat(word.length()));
                } else {
                    result.append(word);
                }
                result.append(" ");
            }

            return result.toString().trim();
        }

        // Check if a word is in the blocked list
        private boolean isBlocked(String word) {
            // Check if the word itself is blocked
            if (containsExactWord(word)) {
                return true;
            }

            // Check if the word contains any blocked words
            return containsBlockedWordAsSubstring(word);
        }

        // Check if a word exactly matches a blocked word
        private boolean containsExactWord(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    return false;
                }
                current = current.children.get(c);
            }
            return current.isEndOfWord;
        }

        // Check if a word contains any blocked word as a substring
        private boolean containsBlockedWordAsSubstring(String word) {
            for (int i = 0; i < word.length(); i++) {
                TrieNode current = root;
                for (int j = i; j < word.length(); j++) {
                    char c = word.charAt(j);
                    if (!current.children.containsKey(c)) {
                        break;
                    }
                    current = current.children.get(c);
                    if (current.isEndOfWord) {
                        return true;
                    }
                }
            }
            return false;
        }

        private static class TrieNode {
            Map<Character, TrieNode> children = new HashMap<>();
            boolean isEndOfWord = false;
        }
    }

    /**
     * Boggle Word Finder
     * Uses a Trie to efficiently find valid words in a Boggle board.
     */
    static class BoggleSolver {
        private _511_c_TrieSet dictionary = new _511_c_TrieSet();

        // Add a word to the dictionary
        public void addToDictionary(String word) {
            dictionary.add(word.toLowerCase());
        }

        // Find all valid words in the Boggle board
        public List<String> findWords(char[][] board) {
            List<String> result = new ArrayList<>();
            int m = board.length;
            int n = board[0].length;
            boolean[][] visited = new boolean[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dfs(board, visited, i, j, new StringBuilder(), result);
                }
            }

            return result;
        }

        // DFS to find words in the board
        private void dfs(char[][] board, boolean[][] visited, int i, int j,
                         StringBuilder path, List<String> result) {
            // Check boundaries
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) {
                return;
            }

            // Add current character to path
            path.append(board[i][j]);
            String currentWord = path.toString();

            // Early pruning: if no word in the dictionary starts with the current prefix, stop
            if (!dictionary.hasKeyWithPrefix(currentWord)) {
                path.deleteCharAt(path.length() - 1);
                return;
            }

            // Mark current cell as visited
            visited[i][j] = true;

            // If current path is a valid word, add to result
            if (dictionary.contains(currentWord) && !result.contains(currentWord)) {
                result.add(currentWord);
            }

            // Explore all 8 adjacent cells
            int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

            for (int k = 0; k < 8; k++) {
                dfs(board, visited, i + dx[k], j + dy[k], path, result);
            }

            // Backtrack
            visited[i][j] = false;
            path.deleteCharAt(path.length() - 1);
        }
    }
}