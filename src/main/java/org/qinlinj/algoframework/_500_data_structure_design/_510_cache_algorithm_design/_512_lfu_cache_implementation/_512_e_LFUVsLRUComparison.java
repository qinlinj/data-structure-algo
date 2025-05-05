package org.qinlinj.algoframework._500_data_structure_design._510_cache_algorithm_design._512_lfu_cache_implementation;

/**
 * Comparison of LFU (Least Frequently Used) and LRU (Least Recently Used) Cache Algorithms
 * <p>
 * This class compares and contrasts the two most popular cache replacement policies:
 * LRU and LFU. Both algorithms aim to optimize cache performance by determining
 * which items to evict when the cache is full, but they use different strategies.
 * <p>
 * LRU (Least Recently Used):
 * - Evicts the item that has not been accessed for the longest time
 * - Based on temporal locality - items used recently are likely to be used again soon
 * - Simpler to implement using a HashMap + Doubly Linked List (or LinkedHashMap)
 * - Works well with access patterns that exhibit temporal locality
 * <p>
 * LFU (Least Frequently Used):
 * - Evicts the item that has been accessed the least number of times
 * - Based on frequency of access - frequently used items are more valuable
 * - More complex implementation using three maps (or similar structures)
 * - Works well when access frequency is a good predictor of future access
 * <p>
 * Performance characteristics and suitable use cases for each algorithm are explored.
 */
public class _512_e_LFUVsLRUComparison {

    public static void main(String[] args) {
        System.out.println("LFU vs LRU Cache Algorithm Comparison");
        System.out.println("====================================");

        // Compare the two algorithms on various workloads
        compareWithSequentialAccess();
        compareWithRepeatedAccess();
        compareWithMixedAccess();
        compareImplementationComplexity();
        comparePerformanceCharacteristics();
    }

    /**
     * Compare LRU and LFU with sequential access pattern
     * e.g., accessing keys 1, 2, 3, 4, 5, ... in sequence
     */
    private static void compareWithSequentialAccess() {
        System.out.println("\n1. Sequential Access Pattern:");
        System.out.println("----------------------------");
        System.out.println("Access sequence: 1, 2, 3, 4, 5, 1 (cache capacity: 3)");

        System.out.println("\nLRU behavior:");
        System.out.println("- After accessing 1,2,3: Cache contains [1, 2, 3]");
        System.out.println("- After accessing 4: Cache contains [2, 3, 4], evicted 1 (oldest)");
        System.out.println("- After accessing 5: Cache contains [3, 4, 5], evicted 2 (oldest)");
        System.out.println("- After accessing 1: Cache contains [4, 5, 1], evicted 3 (oldest)");

        System.out.println("\nLFU behavior:");
        System.out.println("- After accessing 1,2,3: Cache contains [1, 2, 3], all with frequency 1");
        System.out.println("- After accessing 4: Cache contains [2, 3, 4], evicted 1 (tied freq=1, oldest)");
        System.out.println("- After accessing 5: Cache contains [3, 4, 5], evicted 2 (tied freq=1, oldest)");
        System.out.println("- After accessing 1: Cache contains [3, 4, 5], DID NOT add 1 back (all cached items already have freq=1)");

        System.out.println("\nConclusion: LRU performs slightly better for sequential access, as it can adapt to new items in the sequence.");
    }

    /**
     * Compare LRU and LFU with repeated access pattern
     * e.g., accessing some keys much more frequently than others
     */
    private static void compareWithRepeatedAccess() {
        System.out.println("\n2. Repeated Access Pattern:");
        System.out.println("--------------------------");
        System.out.println("Access sequence: 1, 2, 1, 2, 1, 3, 4 (cache capacity: 3)");

        System.out.println("\nLRU behavior:");
        System.out.println("- After accessing 1,2: Cache contains [1, 2]");
        System.out.println("- After accessing 1,2,1: Cache contains [2, 1], 1 moved to most recent");
        System.out.println("- After accessing 2,1: Cache contains [2, 1], both items shift positions");
        System.out.println("- After accessing 3: Cache contains [2, 1, 3]");
        System.out.println("- After accessing 4: Cache contains [1, 3, 4], evicted 2 (oldest)");
        System.out.println("  Note: even though 1 and 2 were used more frequently, 2 was evicted because it was least recently used");

        System.out.println("\nLFU behavior:");
        System.out.println("- After accessing 1,2: Cache contains [1, 2], both freq=1");
        System.out.println("- After accessing 1,2,1: Cache contains [1, 2], 1 has freq=2, 2 has freq=1");
        System.out.println("- After accessing 2,1: Cache contains [1, 2], 1 has freq=3, 2 has freq=2");
        System.out.println("- After accessing 3: Cache contains [1, 2, 3], 3 has freq=1");
        System.out.println("- After accessing 4: Cache contains [1, 2, 4], evicted 3 (lowest freq=1)");
        System.out.println("  Note: LFU correctly retained the most frequently used items (1 and 2)");

        System.out.println("\nConclusion: LFU performs better for repeated access patterns, as it remembers the most frequently used items.");
    }

    /**
     * Compare LRU and LFU with mixed access pattern
     * e.g., a combination of sequential and repeated access
     */
    private static void compareWithMixedAccess() {
        System.out.println("\n3. Mixed Access Pattern:");
        System.out.println("------------------------");
        System.out.println("Access sequence: 1, 2, 3, 1, 2, 4, 5 (cache capacity: 3)");

        System.out.println("\nLRU behavior:");
        System.out.println("- Final cache state: [2, 4, 5]");
        System.out.println("- Misses: When accessing 4, 5");
        System.out.println("- Advantages: Adapts quickly to newly introduced items");
        System.out.println("- Disadvantages: Might evict frequently used items that haven't been accessed very recently");

        System.out.println("\nLFU behavior:");
        System.out.println("- Final cache state: [1, 2, 5]");
        System.out.println("- Misses: When accessing 4, 5");
        System.out.println("- Advantages: Retains frequently used items (1 and 2)");
        System.out.println("- Disadvantages: Slow to adapt if access patterns change (frequency bias)");

        System.out.println("\nConclusion: Each algorithm has strengths and weaknesses depending on the specific access pattern.");
    }

    /**
     * Compare implementation complexity of LRU and LFU
     */
    private static void compareImplementationComplexity() {
        System.out.println("\n4. Implementation Complexity:");
        System.out.println("----------------------------");

        System.out.println("\nLRU Implementation:");
        System.out.println("- Data structures: HashMap + DoublyLinkedList (or LinkedHashMap)");
        System.out.println("- Time complexity: O(1) for all operations");
        System.out.println("- Space complexity: O(capacity)");
        System.out.println("- Complexity rating: Moderate");
        System.out.println("- Code example: Use LinkedHashMap with access-order=true");

        System.out.println("\nLFU Implementation:");
        System.out.println("- Data structures: Three maps (keyToVal, keyToFreq, freqToKeys)");
        System.out.println("- Time complexity: O(1) for all operations (with careful implementation)");
        System.out.println("- Space complexity: O(capacity)");
        System.out.println("- Complexity rating: High");
        System.out.println("- Code challenge: Maintaining consistent state across three maps");

        System.out.println("\nConclusion: LRU is simpler to implement and less error-prone compared to LFU.");
    }

    /**
     * Compare general performance characteristics of LRU and LFU
     */
    private static void comparePerformanceCharacteristics() {
        System.out.println("\n5. Performance Characteristics:");
        System.out.println("------------------------------");

        System.out.println("\nLRU Characteristics:");
        System.out.println("- Strengths:");
        System.out.println("  • Good for workloads with temporal locality");
        System.out.println("  • Adapts quickly to changing access patterns");
        System.out.println("  • Good for general-purpose caching");
        System.out.println("  • Suitable for streaming or sequential data");
        System.out.println("- Weaknesses:");
        System.out.println("  • One-time accessed items can push out frequently used items");
        System.out.println("  • No distinction between high and low frequency items");
        System.out.println("  • Less optimal for stable, frequency-based workloads");

        System.out.println("\nLFU Characteristics:");
        System.out.println("- Strengths:");
        System.out.println("  • Excellent for stable access patterns where frequency matters");
        System.out.println("  • Resists cache pollution from one-time accesses");
        System.out.println("  • Optimal for recurring, predictable workloads");
        System.out.println("  • Great for read-heavy workloads with stable hot spots");
        System.out.println("- Weaknesses:");
        System.out.println("  • Slow to adapt to changing access patterns");
        System.out.println("  • 'Frequency bias' - items can stay in cache too long due to historical frequency");
        System.out.println("  • More complex implementation");
        System.out.println("  • May underperform for bursty or time-sensitive workloads");

        System.out.println("\nConclusion:");
        System.out.println("- LRU is generally preferred for general-purpose caching due to simplicity and adaptability");
        System.out.println("- LFU may be better for specific workloads with stable frequency patterns");
        System.out.println("- Hybrid approaches (e.g., LRU-K, LRFU) attempt to combine the strengths of both");
        System.out.println("- The best algorithm depends on the specific application workload and access patterns");
    }

    /**
     * This class would include simple implementations of both algorithms
     * to demonstrate their behavior, but they are omitted here for brevity.
     * See the _511_d_LRUCacheImplementation and _512_d_LFUImplementation classes
     * for complete implementations.
     */
}