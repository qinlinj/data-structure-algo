package org.qinlinj.practical.bitmap;

import java.util.*;

/**
 * Bloom Filter Implementation
 * <p>
 * Advantages of Bloom Filter:
 * 1. Space Efficiency: Uses significantly less memory than conventional data structures (e.g., HashSet)
 * for storing large sets of data. For instance, storing 10 million elements with 1% false positive
 * rate requires only about 12MB of memory.
 * 2. Constant-time Operations: Offers O(1) time complexity for both insertions and lookups,
 * regardless of the number of elements stored.
 * 3. No False Negatives: If the filter reports an element is not in the set, it definitely is not.
 * This guarantees certain negative results.
 * 4. Privacy: Doesn't store the actual elements, only their hash fingerprints, which can be
 * advantageous for privacy-sensitive applications.
 * 5. Compact Representation: Can represent arbitrary elements (strings, objects, etc.) in a fixed-size space.
 * <p>
 * Concept and Principle:
 * A Bloom Filter is a probabilistic data structure that efficiently tests whether an element
 * is a member of a set. It may report false positives (incorrectly indicating an element is in the set),
 * but it will never report false negatives (saying an element is not in the set when it actually is).
 * <p>
 * The core idea involves:
 * 1. A bit array of m bits, initially all set to 0
 * 2. k different hash functions that map each element to k positions in the bit array
 * 3. To add an element, compute k hash values and set the corresponding bits to 1
 * 4. To query if an element exists, check if all k corresponding bits are set to 1
 * <p>
 * Visual Example:
 * Consider a Bloom Filter with m=16 bits and k=3 hash functions. Initially, all bits are 0:
 * [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * <p>
 * To add "apple":
 * - Hash1("apple") = 2 → Set bit 2 to 1
 * - Hash2("apple") = 7 → Set bit 7 to 1
 * - Hash3("apple") = 11 → Set bit 11 to 1
 * Result: [0,0,1,0,0,0,0,1,0,0,0,1,0,0,0,0]
 * <p>
 * To add "orange":
 * - Hash1("orange") = 1 → Set bit 1 to 1
 * - Hash2("orange") = 7 → Bit 7 already 1
 * - Hash3("orange") = 14 → Set bit 14 to 1
 * Result: [0,1,1,0,0,0,0,1,0,0,0,1,0,0,1,0]
 * <p>
 * To check if "apple" exists, we check bits 2, 7, and 11, all are 1 → Probably yes
 * To check if "banana" exists, if any of its hash bits are 0 → Definitely no
 */
public class BloomFilter {
    private BitSet bitset; // The bit array for storing hashed positions
    private int bitSize;   // Size of the bit array (m)
    private int numHashes; // Number of hash functions (k)
    private int expectedElements; // Number of expected elements to be inserted
    private double falsePositiveRate; // Desired false positive rate

    /**
     * Constructor that calculates optimal bit size and number of hash functions
     * based on expected number of elements and desired false positive rate.
     * <p>
     * Mathematical formulas used:
     * m = -n*ln(p)/(ln(2)^2) where:
     * m = bit size
     * n = expected number of elements
     * p = desired false positive probability
     * <p>
     * k = m/n*ln(2) where:
     * k = optimal number of hash functions
     * <p>
     * Time Complexity: O(1)
     * Space Complexity: O(m) where m is the calculated bit size
     *
     * @param expectedElements  The number of expected elements to be inserted
     * @param falsePositiveRate The desired false positive probability (between 0 and 1)
     */
    public BloomFilter(int expectedElements, double falsePositiveRate) {
        this.expectedElements = expectedElements;
        this.falsePositiveRate = falsePositiveRate;

        // Calculate optimal bit size (m)
        this.bitSize = calculateBitSize(expectedElements, falsePositiveRate);

        // Calculate optimal number of hash functions (k)
        this.numHashes = calculateNumHashes(bitSize, expectedElements);

        // Initialize the bit array
        this.bitset = new BitSet(bitSize);
    }

    /**
     * Main method to demonstrate the Bloom Filter usage.
     */
    public static void main(String[] args) {
        // Create a Bloom Filter with expected 1000 elements and 1% false positive rate
        BloomFilter filter = new BloomFilter(1000, 0.01);
        System.out.println("Bloom Filter created: " + filter);

        // Add some elements
        String[] elementsToAdd = {"apple", "banana", "cherry", "date", "elderberry"};
        for (String element : elementsToAdd) {
            filter.add(element);
            System.out.println("Added: " + element);
        }

        // Check for elements
        System.out.println("\nChecking for elements:");
        String[] elementsToCheck = {"apple", "banana", "grape", "kiwi", "elderberry"};
        for (String element : elementsToCheck) {
            boolean might = filter.mightContain(element);
            System.out.println("Element '" + element + "' might be in the set: " + might);
        }

        // Demonstrate false positives by adding many random elements
        System.out.println("\nDemonstrating false positives:");
        Random random = new Random(123); // Fixed seed for reproducibility

        // Add 800 more random elements
        for (int i = 0; i < 800; i++) {
            String randomElement = "element-" + random.nextInt(10000);
            filter.add(randomElement);
        }

        System.out.println("Added 800 random elements, total 805 elements.");
        System.out.println("Expected false positive rate: " + filter.getFalsePositiveRate(805));

        // Check for elements we know are not in the set
        int falsePositives = 0;
        int trials = 10000;

        for (int i = 0; i < trials; i++) {
            String nonExistingElement = "non-existing-" + random.nextInt(1000000);
            if (filter.mightContain(nonExistingElement)) {
                falsePositives++;
            }
        }

        System.out.println("Actual false positive rate: " + (falsePositives / (double) trials));
    }

    /**
     * Adds an element to the Bloom Filter.
     * <p>
     * Visual Example:
     * Consider adding "cat" to our previous example:
     * - Hash1("cat") = 4 → Set bit 4 to 1
     * - Hash2("cat") = 9 → Set bit 9 to 1
     * - Hash3("cat") = 15 → Set bit 15 to 1
     * <p>
     * Before: [0,1,1,0,0,0,0,1,0,0,0,1,0,0,1,0]
     * After:  [0,1,1,0,1,0,0,1,0,1,0,1,0,0,1,1]
     * <p>
     * Time Complexity: O(k) where k is the number of hash functions
     *
     * @param value The element to add to the Bloom Filter
     */
    public void add(String value) {
        // For each hash function
        for (int i = 0; i < numHashes; i++) {
            // Calculate the hash value and set the corresponding bit
            int hash = getHash(value, i);
            bitset.set(hash);
        }
    }

    /**
     * Checks if an element might be in the Bloom Filter.
     * <p>
     * Visual Example:
     * To check if "apple" exists in our example:
     * - Hash1("apple") = 2 → Check bit 2: it's 1
     * - Hash2("apple") = 7 → Check bit 7: it's 1
     * - Hash3("apple") = 11 → Check bit 11: it's 1
     * All bits are 1, so "apple" is probably in the set.
     * <p>
     * To check if "grape" exists:
     * - Hash1("grape") = 3 → Check bit 3: it's 0
     * Since one hash bit is 0, "grape" is definitely not in the set.
     * <p>
     * Time Complexity: O(k) where k is the number of hash functions
     *
     * @param value The element to check
     * @return true if the element might be in the set, false if it's definitely not
     */
    public boolean mightContain(String value) {
        // For each hash function
        for (int i = 0; i < numHashes; i++) {
            // Calculate the hash value and check the corresponding bit
            int hash = getHash(value, i);
            // If any bit is not set, the element is definitely not in the set
            if (!bitset.get(hash)) {
                return false;
            }
        }
        // All bits are set, the element is probably in the set
        return true;
    }

    /**
     * Generates a hash value for a given string and hash function index.
     * Uses a simple but effective hashing scheme with different seeds for each hash function.
     * <p>
     * Visual Example:
     * For the string "apple" and hash function 0:
     * 1. Start with seed = 0 * 31 + 17 = 17
     * 2. For each character in "apple", update hash:
     * - 'a': hash = (17 * 31) + 97 = 624
     * - 'p': hash = (624 * 31) + 112 = 19,456
     * - 'p': hash = (19,456 * 31) + 112 = 603,248
     * - 'l': hash = (603,248 * 31) + 108 = 18,700,796
     * - 'e': hash = (18,700,796 * 31) + 101 = 579,724,777
     * 3. Take hash modulo bitSize to get the bit position:
     * 579,724,777 % 16 = 9
     * <p>
     * Time Complexity: O(L) where L is the length of the input string
     *
     * @param value        The string to hash
     * @param hashFunction The index of the hash function to use
     * @return The hash value (a position in the bit array)
     */
    private int getHash(String value, int hashFunction) {
        int hash = hashFunction * 31 + 17; // Different starting point for each hash function

        for (int i = 0; i < value.length(); i++) {
            hash = (hash * 31) + value.charAt(i);
        }

        // Make sure the hash is positive and within range
        return Math.abs(hash % bitSize);
    }

    /**
     * Calculates the optimal number of hash functions.
     * <p>
     * Formula: k = m/n*ln(2)
     * <p>
     * Time Complexity: O(1)
     *
     * @param m Bit size
     * @param n Expected number of elements
     * @return The optimal number of hash functions
     */
    private int calculateNumHashes(int m, int n) {
        return Math.max(1, (int) Math.round((m / n) * Math.log(2)));
    }

    /**
     * Calculates the optimal bit size for the Bloom Filter.
     * <p>
     * Formula: m = -n*ln(p)/(ln(2)^2)
     * <p>
     * Time Complexity: O(1)
     *
     * @param n Expected number of elements
     * @param p Desired false positive probability
     * @return The optimal bit size
     */
    private int calculateBitSize(int n, double p) {
        return (int) Math.ceil(-(n * Math.log(p)) / (Math.log(2) * Math.log(2)));
    }

    /**
     * Returns the current estimated false positive rate based on the number
     * of elements added and the filter configuration.
     * <p>
     * Formula: p = (1 - e^(-k*n/m))^k
     * <p>
     * Time Complexity: O(1)
     *
     * @param elementsAdded Number of elements added to the filter
     * @return The estimated false positive rate
     */
    public double getFalsePositiveRate(int elementsAdded) {
        return Math.pow(1 - Math.exp(-numHashes * (double) elementsAdded / bitSize), numHashes);
    }

    /**
     * Returns a human-readable representation of the Bloom Filter parameters.
     * <p>
     * Time Complexity: O(1)
     *
     * @return A string describing the Bloom Filter
     */
    @Override
    public String toString() {
        return "BloomFilter{" +
                "bitSize=" + bitSize +
                ", numHashes=" + numHashes +
                ", expectedElements=" + expectedElements +
                ", targetFalsePositiveRate=" + falsePositiveRate +
                ", bitSetSize=" + bitset.size() +
                ", bitsSet=" + bitset.cardinality() +
                '}';
    }
}
