package org.qinlinj.algoframework._500_data_structure_design._520_advanced_array_and_hash._522_hash_table_practice; /**
 * _522_h_GroupAnagrams
 * <p>
 * LeetCode #49: Group Anagrams
 * <p>
 * Problem:
 * Given an array of strings, group anagrams together. An anagram is a word formed
 * by rearranging the letters of another word, using all original letters exactly once.
 * You can return the answer in any order.
 * <p>
 * Approach:
 * The key insight is to develop a "signature" for each string that will be identical
 * for all anagrams. We can use a HashMap where:
 * - Keys are the unique signatures that identify anagram groups
 * - Values are lists of strings that belong to the same anagram group
 * <p>
 * For the signature, we can use:
 * 1. A character count array encoded as a string, which is more efficient than sorting
 * 2. This encoding gives us a unique signature for each anagram group
 * <p>
 * Time Complexity: O(n * k) where n is the number of strings and k is the maximum length
 * Space Complexity: O(n * k) for storing all strings in the HashMap
 */

import java.util.*;

public class _522_h_GroupAnagrams {

    public static void main(String[] args) {
        _522_h_GroupAnagrams solution = new _522_h_GroupAnagrams();

        // Test case 1
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result1 = solution.groupAnagrams(strs1);

        System.out.println("Example 1:");
        for (List<String> group : result1) {
            System.out.println(group);
        }
        // Expected output: [["bat"],["nat","tan"],["ate","eat","tea"]] (in any order)

        // Test case 2
        String[] strs2 = {""};
        List<List<String>> result2 = solution.groupAnagrams(strs2);

        System.out.println("\nExample 2:");
        for (List<String> group : result2) {
            System.out.println(group);
        }
        // Expected output: [[""]]

        // Test case 3
        String[] strs3 = {"a"};
        List<List<String>> result3 = solution.groupAnagrams(strs3);

        System.out.println("\nExample 3:");
        for (List<String> group : result3) {
            System.out.println(group);
        }
        // Expected output: [["a"]]
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        // Map from anagram signature to list of strings
        HashMap<String, List<String>> groups = new HashMap<>();

        for (String s : strs) {
            // Get the signature for this string
            String signature = getSignature(s);

            // Add to the appropriate group
            if (!groups.containsKey(signature)) {
                groups.put(signature, new ArrayList<>());
            }
            groups.get(signature).add(s);
        }

        // Convert map values to result list
        return new ArrayList<>(groups.values());
    }

    /**
     * Creates a unique signature for a string based on character counts
     * All anagrams will have the same signature
     */
    private String getSignature(String s) {
        // Count occurrences of each character
        int[] counts = new int[26]; // For lowercase English letters

        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }

        // Build the signature string
        StringBuilder signature = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            // Only include characters that appear
            if (counts[i] > 0) {
                signature.append((char) ('a' + i));
                signature.append(counts[i]);
            }
        }

        return signature.toString();
    }

    /**
     * Alternative approach using character count array
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        // Map from encoded character count to list of strings
        HashMap<String, List<String>> groups = new HashMap<>();

        for (String s : strs) {
            // Encode the string based on character frequencies
            String code = encode(s);

            // Add to the appropriate group
            groups.putIfAbsent(code, new ArrayList<>());
            groups.get(code).add(s);
        }

        // Return the grouped anagrams
        return new ArrayList<>(groups.values());
    }

    /**
     * Encodes a string based on character frequencies
     */
    private String encode(String s) {
        char[] count = new char[26]; // Using char array to create a compact signature

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Convert count array to string
        return new String(count);
    }

    /**
     * Simple solution using sorting (less efficient but easier to understand)
     */
    public List<List<String>> groupAnagramsWithSorting(String[] strs) {
        HashMap<String, List<String>> groups = new HashMap<>();

        for (String s : strs) {
            // Sort the string characters to create a signature
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);

            // Add to the appropriate group
            groups.putIfAbsent(sortedStr, new ArrayList<>());
            groups.get(sortedStr).add(s);
        }

        return new ArrayList<>(groups.values());
    }
}