package org.qinlinj.algoframework._300_classical_data_structure_algo._330_array_algo_II._333_rabin_karp_algorithm;

import java.util.*;

/**
 * LeetCode 187: Repeated DNA Sequences
 * <p>
 * Problem:
 * Given a string s representing a DNA sequence that contains only 'A', 'C', 'G', and 'T',
 * find all the 10-letter-long sequences (substrings) that occur more than once in the DNA molecule.
 * Return all the repeated sequences as a List of Strings.
 * <p>
 * This implementation demonstrates the Rabin-Karp string matching algorithm with a sliding window.
 * Instead of computing a hash value for each substring from scratch (which would be O(L) time),
 * we use a rolling hash technique to efficiently update the hash value in O(1) time
 * as the window slides through the input string.
 * <p>
 * Time Complexity: O(N) where N is the length of the input string
 * Space Complexity: O(N) for storing the seen hashes and result sequences
 */

public class RepeatedDnaSequences {

    public static void main(String[] args) {
        RepeatedDnaSequences solution = new RepeatedDnaSequences();

        // Test cases
        String s1 = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        System.out.println("Input: " + s1);
        System.out.println("Brute Force: " + solution.findRepeatedDnaSequencesBruteForce(s1));
        System.out.println("Rabin-Karp: " + solution.findRepeatedDnaSequences(s1));
        System.out.println("Bitwise: " + solution.findRepeatedDnaSequencesBitwise(s1));

        String s2 = "AAAAAAAAAAAAA";
        System.out.println("\nInput: " + s2);
        System.out.println("Brute Force: " + solution.findRepeatedDnaSequencesBruteForce(s2));
        System.out.println("Rabin-Karp: " + solution.findRepeatedDnaSequences(s2));
        System.out.println("Bitwise: " + solution.findRepeatedDnaSequencesBitwise(s2));

        // Demonstrate rolling hash calculation
        System.out.println("\n---- Rolling Hash Demonstration ----");
        solution.demonstrateRollingHash("ACGTACGTAC");
    }

    /**
     * Find all repeated DNA sequences of length 10 using the brute force approach.
     * This implementation uses substring extraction which is O(L) per window,
     * resulting in O(N*L) time complexity.
     */
    public List<String> findRepeatedDnaSequencesBruteForce(String s) {
        int n = s.length();
        int L = 10; // Length of the DNA sequence we're looking for

        // Records sequences we've seen before
        HashSet<String> seen = new HashSet<>();
        // Records sequences that appear multiple times (our answer)
        HashSet<String> result = new HashSet<>();

        // Slide window through the string
        for (int i = 0; i + L <= n; i++) {
            String sequence = s.substring(i, i + L);
            if (seen.contains(sequence)) {
                // Found a repeated sequence
                result.add(sequence);
            } else {
                seen.add(sequence);
            }
        }

        return new ArrayList<>(result);
    }

    /**
     * Find all repeated DNA sequences of length 10 using the Rabin-Karp algorithm.
     * This implementation uses a rolling hash technique to achieve O(N) time complexity.
     */
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        int L = 10; // Length of the DNA sequence we're looking for

        // Base/radix for our number system (4 for A,C,G,T)
        int R = 4;

        // Convert DNA string to array of integers
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            switch (s.charAt(i)) {
                case 'A':
                    nums[i] = 0;
                    break;
                case 'C':
                    nums[i] = 1;
                    break;
                case 'G':
                    nums[i] = 2;
                    break;
                case 'T':
                    nums[i] = 3;
                    break;
            }
        }

        // Calculate R^(L-1) for removing the leftmost digit later
        int RL = (int) Math.pow(R, L - 1);

        // Sliding window hash value
        int windowHash = 0;

        // Records hash values we've seen before
        HashSet<Integer> seen = new HashSet<>();
        // Records sequences that appear multiple times (our answer)
        HashSet<String> result = new HashSet<>();

        // Sliding window
        int left = 0, right = 0;
        while (right < n) {
            // Add rightmost character to window and update hash
            // Formula: hash = R * hash + value (adding digit to lowest position)
            windowHash = R * windowHash + nums[right];
            right++;

            // When window size reaches L
            if (right - left == L) {
                // Check if this hash has been seen before
                if (seen.contains(windowHash)) {
                    // Found a repeated sequence
                    result.add(s.substring(left, right));
                } else {
                    seen.add(windowHash);
                }

                // Remove leftmost character from window and update hash
                // Formula: hash = hash - value * R^(L-1) (removing digit from highest position)
                windowHash = windowHash - nums[left] * RL;
                left++;
            }
        }

        return new ArrayList<>(result);
    }

    /**
     * Optimized version that uses bit manipulation instead of power calculation.
     * Since we only have 4 possible characters (A,C,G,T), we can use 2 bits to represent each.
     * This allows us to store a 10-letter sequence in 20 bits (fits in an integer).
     */
    public List<String> findRepeatedDnaSequencesBitwise(String s) {
        int n = s.length();
        int L = 10; // Length of the DNA sequence we're looking for

        // If the input string is too short
        if (n <= L) {
            return new ArrayList<>();
        }

        // Map characters to their 2-bit representation
        // A: 00 (0), C: 01 (1), G: 10 (2), T: 11 (3)
        int[] charMap = new int[26];
        charMap['A' - 'A'] = 0;
        charMap['C' - 'A'] = 1;
        charMap['G' - 'A'] = 2;
        charMap['T' - 'A'] = 3;

        // Bit mask for 20 bits (2 bits per character * 10 characters)
        int mask = (1 << (2 * L)) - 1;

        // Sliding window hash value
        int windowHash = 0;

        // Records hash values we've seen before
        HashSet<Integer> seen = new HashSet<>();
        // Records sequences that appear multiple times (our answer)
        HashSet<String> result = new HashSet<>();

        // Initialize hash for first L-1 characters
        for (int i = 0; i < L - 1; i++) {
            // Shift left 2 bits and add new character's 2-bit value
            windowHash = ((windowHash << 2) | charMap[s.charAt(i) - 'A']) & mask;
        }

        // Slide window through the remaining string
        for (int i = L - 1; i < n; i++) {
            // Add next character to hash
            windowHash = ((windowHash << 2) | charMap[s.charAt(i) - 'A']) & mask;

            // Check if this hash has been seen before
            if (seen.contains(windowHash)) {
                result.add(s.substring(i - L + 1, i + 1));
            } else {
                seen.add(windowHash);
            }
        }

        return new ArrayList<>(result);
    }

    /**
     * Demo function that visualizes the sliding hash calculation
     */
    public void demonstrateRollingHash(String s) {
        int n = s.length();
        int L = 10; // Sequence length
        int R = 4;  // Base (4 for A,C,G,T)

        System.out.println("Demonstrating Rabin-Karp rolling hash for DNA sequence: " + s);
        System.out.println("Sequence length: " + L + ", Base: " + R);

        // Convert to numeric representation
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            switch (s.charAt(i)) {
                case 'A':
                    nums[i] = 0;
                    break;
                case 'C':
                    nums[i] = 1;
                    break;
                case 'G':
                    nums[i] = 2;
                    break;
                case 'T':
                    nums[i] = 3;
                    break;
            }
            System.out.println("Character " + s.charAt(i) + " mapped to value " + nums[i]);
        }

        // Calculate R^(L-1)
        int RL = (int) Math.pow(R, L - 1);
        System.out.println("Value of R^(L-1) = " + R + "^" + (L - 1) + " = " + RL);

        // Sliding window hash calculation
        int windowHash = 0;
        System.out.println("\nInitial window hash: " + windowHash);

        // Build initial hash for first window
        for (int i = 0; i < L && i < n; i++) {
            windowHash = R * windowHash + nums[i];
            System.out.println("Add " + s.charAt(i) + ": hash = " + R + " * " +
                    (windowHash - nums[i]) / R + " + " + nums[i] + " = " + windowHash);
        }

        // Slide window
        for (int i = 0; i <= n - L - 1 && i + L < n; i++) {
            System.out.println("\nWindow [" + i + ".." + (i + L - 1) + "]: " +
                    s.substring(i, i + L) + ", hash: " + windowHash);

            // Remove leftmost digit
            int oldHash = windowHash;
            windowHash = windowHash - nums[i] * RL;
            System.out.println("Remove " + s.charAt(i) + ": hash = " + oldHash + " - " +
                    nums[i] + " * " + RL + " = " + windowHash);

            // Add rightmost digit
            oldHash = windowHash;
            windowHash = R * windowHash + nums[i + L];
            System.out.println("Add " + s.charAt(i + L) + ": hash = " + R + " * " +
                    oldHash + " + " + nums[i + L] + " = " + windowHash);

            System.out.println("New window [" + (i + 1) + ".." + (i + L) + "]: " +
                    s.substring(i + 1, i + L + 1) + ", hash: " + windowHash);
        }
    }
}