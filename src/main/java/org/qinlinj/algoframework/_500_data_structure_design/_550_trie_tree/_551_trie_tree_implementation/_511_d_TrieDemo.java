package org.qinlinj.algoframework._500_data_structure_design._550_trie_tree._551_trie_tree_implementation;

import java.util.*;

/**
 * TRIE DEMONSTRATION - PRACTICAL EXAMPLES
 * <p>
 * Key Points:
 * - This class demonstrates the practical usage of TrieMap and TrieSet
 * - Examples cover all key operations:
 * 1. Basic operations (put, get, remove)
 * 2. Prefix operations (prefix search, longest/shortest prefix)
 * 3. Pattern matching (wildcard searches)
 * - Time complexity advantages over HashMap for string operations:
 * - Prefix searches: O(k) where k is prefix length
 * - Wildcard matching: More efficient than regex for simple patterns
 * - Sorted output: Keys are returned in lexicographical order
 * - Common applications:
 * - Autocomplete systems
 * - Spell checkers
 * - IP routing (longest prefix matching)
 * - Dictionary implementations
 */
public class _511_d_TrieDemo {

    public static void main(String[] args) {
        demonstrateTrieMap();
        System.out.println("\n" + "=".repeat(50) + "\n");
        demonstrateTrieSet();
    }

    private static void demonstrateTrieMap() {
        System.out.println("TRIEMAP DEMONSTRATION");
        System.out.println("====================");

        // Initialize a TrieMap
        _511_b_TrieMap<Integer> trieMap = new _511_b_TrieMap<>();

        // Add some programming languages and their popularity scores
        System.out.println("Adding key-value pairs...");
        trieMap.put("java", 10);
        trieMap.put("cpp", 9);
        trieMap.put("python", 12);
        trieMap.put("go", 8);
        trieMap.put("javascript", 11);
        trieMap.put("typescript", 9);
        trieMap.put("rust", 7);
        System.out.println("Size after adding: " + trieMap.size());

        // Basic operations
        System.out.println("\nBASIC OPERATIONS:");
        System.out.println("Get 'python': " + trieMap.get("python"));
        System.out.println("Contains 'java': " + trieMap.containsKey("java"));
        System.out.println("Contains 'ruby': " + trieMap.containsKey("ruby"));

        // Update a value
        System.out.println("\nUpdating 'python' popularity to 14");
        trieMap.put("python", 14);
        System.out.println("Get 'python' after update: " + trieMap.get("python"));

        // Prefix operations
        System.out.println("\nPREFIX OPERATIONS:");
        System.out.println("Has prefix 'jav': " + trieMap.hasKeyWithPrefix("jav"));
        System.out.println("Has prefix 'php': " + trieMap.hasKeyWithPrefix("php"));

        System.out.println("\nKeys with prefix 'ja':");
        List<String> jaKeys = trieMap.keysWithPrefix("ja");
        for (String key : jaKeys) {
            System.out.println("- " + key + ": " + trieMap.get(key));
        }

        // Shortest/Longest prefix
        System.out.println("\nShortest prefix of 'javafx': " + trieMap.shortestPrefixOf("javafx"));
        System.out.println("Longest prefix of 'javascripter': " + trieMap.longestPrefixOf("javascripter"));

        // Pattern matching
        System.out.println("\nPATTERN MATCHING:");
        System.out.println("Keys matching pattern 'j.va':");
        List<String> patternKeys = trieMap.keysWithPattern("j.va");
        for (String key : patternKeys) {
            System.out.println("- " + key + ": " + trieMap.get(key));
        }

        System.out.println("\nHas key matching '..thon': " + trieMap.hasKeyWithPattern("..thon"));

        // Removal
        System.out.println("\nREMOVAL OPERATIONS:");
        System.out.println("Remove 'java'");
        trieMap.remove("java");
        System.out.println("Contains 'java' after removal: " + trieMap.containsKey("java"));
        System.out.println("Size after removal: " + trieMap.size());

        // Show that the Trie structure is maintained properly after removals
        System.out.println("\nKeys with prefix 'j' after removing 'java':");
        List<String> jKeysAfterRemoval = trieMap.keysWithPrefix("j");
        for (String key : jKeysAfterRemoval) {
            System.out.println("- " + key + ": " + trieMap.get(key));
        }
    }

    private static void demonstrateTrieSet() {
        System.out.println("TRIESET DEMONSTRATION");
        System.out.println("====================");

        // Initialize a TrieSet
        _511_c_TrieSet trieSet = new _511_c_TrieSet();

        // Add some programming languages
        System.out.println("Adding elements...");
        trieSet.add("java");
        trieSet.add("cpp");
        trieSet.add("python");
        trieSet.add("go");
        trieSet.add("javascript");
        trieSet.add("typescript");
        trieSet.add("rust");
        System.out.println("Size after adding: " + trieSet.size());

        // Basic operations
        System.out.println("\nBASIC OPERATIONS:");
        System.out.println("Contains 'python': " + trieSet.contains("python"));
        System.out.println("Contains 'ruby': " + trieSet.contains("ruby"));

        // Prefix operations
        System.out.println("\nPREFIX OPERATIONS:");
        System.out.println("Has prefix 'typ': " + trieSet.hasKeyWithPrefix("typ"));
        System.out.println("Has prefix 'php': " + trieSet.hasKeyWithPrefix("php"));

        System.out.println("\nElements with prefix 'ja':");
        List<String> jaElements = trieSet.keysWithPrefix("ja");
        for (String element : jaElements) {
            System.out.println("- " + element);
        }

        // Shortest/Longest prefix
        System.out.println("\nShortest prefix of 'javafx': " + trieSet.shortestPrefixOf("javafx"));
        System.out.println("Longest prefix of 'javascripter': " + trieSet.longestPrefixOf("javascripter"));

        // Pattern matching
        System.out.println("\nPATTERN MATCHING:");
        System.out.println("Elements matching pattern 'j.va':");
        List<String> patternElements = trieSet.keysWithPattern("j.va");
        for (String element : patternElements) {
            System.out.println("- " + element);
        }

        System.out.println("\nHas element matching '..thon': " + trieSet.hasKeyWithPattern("..thon"));

        // Removal
        System.out.println("\nREMOVAL OPERATIONS:");
        System.out.println("Remove 'java'");
        trieSet.remove("java");
        System.out.println("Contains 'java' after removal: " + trieSet.contains("java"));
        System.out.println("Size after removal: " + trieSet.size());

        // Special cases demonstration
        System.out.println("\nSPECIAL CASES:");
        // 1. Empty string as a key
        trieSet.add("");
        System.out.println("Added empty string. Contains '': " + trieSet.contains(""));

        // 2. Longest prefix with complete match
        System.out.println("Longest prefix of 'python': " + trieSet.longestPrefixOf("python"));

        // 3. Complex pattern matching
        trieSet.add("p1p");
        trieSet.add("p2p");
        trieSet.add("pop");
        System.out.println("\nElements matching pattern 'p.p':");
        List<String> complexPattern = trieSet.keysWithPattern("p.p");
        for (String element : complexPattern) {
            System.out.println("- " + element);
        }
    }
}