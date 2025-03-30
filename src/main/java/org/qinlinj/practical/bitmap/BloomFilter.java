package org.qinlinj.practical.bitmap;

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
}
