package org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._542_dynamic_segment_tree; /**
 * Comparison Between Regular and Dynamic Segment Trees
 * <p>
 * This class demonstrates the differences between regular segment trees and dynamic
 * segment trees, focusing on memory usage, initialization, and performance characteristics.
 * <p>
 * Key comparisons:
 * <p>
 * 1. Memory Usage:
 * - Regular Segment Tree: Allocates memory for all possible nodes upfront
 * - Dynamic Segment Tree: Creates nodes only when needed (on-demand)
 * <p>
 * 2. Initialization:
 * - Regular Segment Tree: Requires an array of values to initialize
 * - Dynamic Segment Tree: Only needs range boundaries and a default value
 * <p>
 * 3. Best Use Cases:
 * - Regular Segment Tree: Dense data where most positions have values
 * - Dynamic Segment Tree: Sparse data or very large ranges
 * <p>
 * 4. Memory Efficiency:
 * - For a range of size n:
 * * Regular Segment Tree: O(n) memory regardless of data sparsity
 * * Dynamic Segment Tree: O(k log n) memory where k is the number of updates
 * <p>
 * 5. Performance:
 * - Both have O(log n) time complexity for queries and updates
 * - Regular may be slightly faster due to contiguous memory access patterns
 * - Dynamic has better memory usage when dealing with sparse data
 * <p>
 * This implementation provides practical examples and measurements to illustrate
 * these differences with both small and large ranges.
 */

import org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._541_basic_segment_tree._541_b_ArraySegmentTree;

public class _542_c_DynamicSegmentTreeComparison {

    /**
     * Main method to demonstrate and compare regular and dynamic segment trees
     */
    public static void main(String[] args) {
        System.out.println("=== Comparing Regular and Dynamic Segment Trees ===\n");

        // Small range comparison
        smallRangeComparison();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Large range comparison
        largeRangeComparison();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Memory usage estimation
        compareMemoryUsage();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Performance comparison
        comparePerformance();
    }

    /**
     * Demonstrates the difference between regular and dynamic segment trees for small ranges
     */
    private static void smallRangeComparison() {
        System.out.println("Small Range Comparison (10 elements):");

        // Sample array of 10 elements
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};

        System.out.println("Initial array: [3, 1, 4, 1, 5, 9, 2, 6, 5, 3]");

        // Initialize regular segment tree
        _541_b_ArraySegmentTree regularTree = new _541_b_ArraySegmentTree(arr, Integer::sum);

        // Initialize dynamic segment tree
        _542_a_DynamicSegmentTree dynamicTree = new _542_a_DynamicSegmentTree(0, arr.length - 1, Integer::sum, 0);
        // Populate with values (required for dynamic tree)
        for (int i = 0; i < arr.length; i++) {
            dynamicTree.update(i, arr[i]);
        }

        // Compare query results
        int left = 2, right = 7;
        System.out.println("\nQuery sum of range [" + left + ", " + right + "]:");
        System.out.println("Regular Segment Tree: " + regularTree.query(left, right));
        System.out.println("Dynamic Segment Tree: " + dynamicTree.query(left, right));

        // Compare update operations
        int updateIndex = 4, updateValue = 10;
        System.out.println("\nUpdate index " + updateIndex + " to value " + updateValue + ":");

        regularTree.update(updateIndex, updateValue);
        dynamicTree.update(updateIndex, updateValue);

        System.out.println("Regular Segment Tree (after update): " + regularTree.query(left, right));
        System.out.println("Dynamic Segment Tree (after update): " + dynamicTree.query(left, right));

        System.out.println("\nSmall ranges: Both implementations work similarly and produce identical results");
    }

    /**
     * Demonstrates the advantage of dynamic segment trees for large ranges
     */
    private static void largeRangeComparison() {
        System.out.println("Large Range Comparison (10^9 elements):");

        System.out.println("Range size: 1,000,000,000 (10^9)");

        System.out.println("\nRegular Segment Tree:");
        System.out.println("- Would require approximately 4 * 10^9 * 4 bytes = 16GB of memory!");
        System.out.println("- Impossible to initialize with an array of this size");
        System.out.println("- Would cause OutOfMemoryError on most systems");

        System.out.println("\nDynamic Segment Tree:");
        // Create a dynamic tree with a very large range
        _542_a_DynamicSegmentTree largeTree = new _542_a_DynamicSegmentTree(0, 1_000_000_000 - 1, Integer::sum, 0);
        System.out.println("- Successfully initialized with minimal memory footprint");
        System.out.println("- Initial memory usage: Only a single root node (~24 bytes)");

        // Perform sparse updates
        largeTree.update(123456789, 42);
        largeTree.update(987654321, 13);

        System.out.println("\nAfter updating just 2 elements in this massive range:");
        System.out.println("Sum of entire range [0, 999,999,999]: " + largeTree.query(0, 1_000_000_000 - 1));
        System.out.println("Sum of specific elements [123456789, 987654321]: " + largeTree.query(123456789, 987654321));

        System.out.println("\nLarge ranges: Dynamic Segment Tree clearly outperforms in memory efficiency");
        System.out.println("Estimated nodes created: O(log N) per update = ~60 nodes for 2 updates");
    }

    /**
     * Compares the theoretical memory usage of both implementations
     */
    private static void compareMemoryUsage() {
        System.out.println("Memory Usage Comparison:");

        System.out.println("\nTheoretical memory usage analysis:");

        // Memory for node-based segment tree
        System.out.println("Regular Segment Tree (size n):");
        System.out.println("- Uses approximately 4n nodes regardless of data distribution");
        System.out.println("- Each node requires ~24 bytes (16 bytes for object header, 8 bytes for data)");
        System.out.println("- Total memory: ~96n bytes");

        // Memory for dynamic segment tree
        System.out.println("\nDynamic Segment Tree (size n with k updates):");
        System.out.println("- Creates approximately O(k log n) nodes, where k is the number of updates");
        System.out.println("- Each node requires the same ~24 bytes");
        System.out.println("- Total memory: ~24k log n bytes");

        // Concrete examples
        System.out.println("\nFor n = 1,000,000 (10^6):");
        System.out.println("- Regular Segment Tree: ~96MB regardless of updates");

        System.out.println("\nFor same range but with only 1,000 updates:");
        System.out.println("- Dynamic Segment Tree: ~24 * 1,000 * log(10^6) ≈ 0.5MB");
        System.out.println("- Memory saving: ~99.5%");

        System.out.println("\nFor extremely large ranges like n = 10^9:");
        System.out.println("- Regular Segment Tree: ~96GB (impractical for most systems)");
        System.out.println("- Dynamic Segment Tree with 10,000 updates: ~7MB");
    }

    /**
     * Compares the performance characteristics of both implementations
     */
    private static void comparePerformance() {
        System.out.println("Performance Comparison:");

        // Prepare data for timing tests
        int size = 100000; // 10^5
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i % 100; // Some arbitrary values
        }

        // Time regular segment tree construction
        long startTime = System.nanoTime();
        _541_b_ArraySegmentTree regularTree = new _541_b_ArraySegmentTree(arr, Integer::sum);
        long regularConstructTime = System.nanoTime() - startTime;

        // Time dynamic segment tree construction with all elements
        startTime = System.nanoTime();
        _542_a_DynamicSegmentTree fullDynamicTree = new _542_a_DynamicSegmentTree(0, size - 1, Integer::sum, 0);
        for (int i = 0; i < size; i++) {
            fullDynamicTree.update(i, arr[i]);
        }
        long dynamicFullConstructTime = System.nanoTime() - startTime;

        // Time dynamic segment tree construction with sparse updates (1% of elements)
        startTime = System.nanoTime();
        _542_a_DynamicSegmentTree sparseDynamicTree = new _542_a_DynamicSegmentTree(0, size - 1, Integer::sum, 0);
        for (int i = 0; i < size; i += 100) { // Update only 1% of elements
            sparseDynamicTree.update(i, arr[i]);
        }
        long dynamicSparseConstructTime = System.nanoTime() - startTime;

        System.out.println("\nConstruction time for " + size + " elements:");
        System.out.println("- Regular Segment Tree: " + regularConstructTime / 1_000_000.0 + " ms");
        System.out.println("- Dynamic Segment Tree (all elements): " + dynamicFullConstructTime / 1_000_000.0 + " ms");
        System.out.println("- Dynamic Segment Tree (1% of elements): " + dynamicSparseConstructTime / 1_000_000.0 + " ms");

        // Time queries
        int queries = 10000;
        int queryRange = 1000;

        startTime = System.nanoTime();
        for (int i = 0; i < queries; i++) {
            int left = i % (size - queryRange);
            int right = left + queryRange - 1;
            regularTree.query(left, right);
        }
        long regularQueryTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < queries; i++) {
            int left = i % (size - queryRange);
            int right = left + queryRange - 1;
            fullDynamicTree.query(left, right);
        }
        long dynamicFullQueryTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < queries; i++) {
            int left = i % (size - queryRange);
            int right = left + queryRange - 1;
            sparseDynamicTree.query(left, right);
        }
        long dynamicSparseQueryTime = System.nanoTime() - startTime;

        System.out.println("\nQuery time for " + queries + " range queries:");
        System.out.println("- Regular Segment Tree: " + regularQueryTime / 1_000_000.0 + " ms");
        System.out.println("- Dynamic Segment Tree (all elements): " + dynamicFullQueryTime / 1_000_000.0 + " ms");
        System.out.println("- Dynamic Segment Tree (sparse): " + dynamicSparseQueryTime / 1_000_000.0 + " ms");

        System.out.println("\nConclusions:");
        System.out.println("1. Regular Segment Tree typically has faster construction for dense data");
        System.out.println("2. Dynamic Segment Tree excels with sparse data or massive ranges");
        System.out.println("3. Query performance is comparable between implementations");
        System.out.println("4. The memory-performance tradeoff strongly favors Dynamic Segment Trees for sparse data");
    }
}