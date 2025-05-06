package org.qinlinj.algoframework._500_data_structure_design._540_segment_tree._541_basic_segment_tree; /**
 * Segment Tree Comparison and Applications
 * <p>
 * This class demonstrates and compares the node-based and array-based segment tree implementations.
 * Both implementations provide the same functionality but differ in their internal structure.
 * <p>
 * Key comparisons:
 * 1. Memory Usage:
 * - Node-based: Uses more memory due to pointers and node objects
 * - Array-based: More memory efficient (4*n array size vs potentially more for nodes)
 * <p>
 * 2. Performance:
 * - Node-based: Potentially slower due to pointer chasing and memory indirection
 * - Array-based: Better cache locality due to contiguous memory allocation
 * <p>
 * 3. Implementation Complexity:
 * - Node-based: More intuitive to understand as it directly models the tree structure
 * - Array-based: More abstract indexing logic, similar to binary heap implementation
 * <p>
 * Current Limitations and Potential Enhancements:
 * <p>
 * 1. Range Updates:
 * - Current implementations only support single-element updates
 * - Can be extended to support range updates using lazy propagation techniques
 * - This would maintain O(log n) time complexity for range updates
 * <p>
 * 2. Dynamic Construction:
 * - Current implementations require pre-construction with all elements
 * - Could be modified to support dynamic/sparse construction for very large ranges
 * - Useful when dealing with large ranges (e.g., 10^9) with sparse actual data
 * <p>
 * Common Applications:
 * - Range sum/min/max queries with frequent updates
 * - Computational geometry problems
 * - Database systems for aggregation queries
 * - Text editors for range operations
 */

import java.util.function.*;

public class _541_c_SegmentTreeComparison {

    /**
     * Main method to demonstrate and compare both segment tree implementations
     */
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};

        System.out.println("=== Comparing Segment Tree Implementations ===\n");

        // Demonstrate node-based segment tree
        demonstrateNodeBasedTree(arr);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Demonstrate array-based segment tree
        demonstrateArrayBasedTree(arr);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Compare memory usage and performance
        compareImplementations(arr);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Show different aggregation functions
        demonstrateAggregationFunctions(arr);
    }

    /**
     * Demonstrates the node-based segment tree implementation
     */
    private static void demonstrateNodeBasedTree(int[] arr) {
        System.out.println("Node-based Segment Tree Example:");

        // Create a sum segment tree
        _541_a_NodeSegmentTree sumTree = new _541_a_NodeSegmentTree(arr, (a, b) -> a + b);

        // Perform range queries
        System.out.println("Sum of range [1, 3]: " + sumTree.query(1, 3));
        System.out.println("Sum of range [0, 4]: " + sumTree.query(0, 4));

        // Update elements and query again
        System.out.println("\nUpdating index 2 from " + arr[2] + " to 10...");
        sumTree.update(2, 10);
        System.out.println("Sum of range [1, 3] after update: " + sumTree.query(1, 3));

        // Update another element
        System.out.println("Updating index 0 from " + arr[0] + " to 15...");
        sumTree.update(0, 15);
        System.out.println("Sum of range [0, 2] after update: " + sumTree.query(0, 2));
    }

    /**
     * Demonstrates the array-based segment tree implementation
     */
    private static void demonstrateArrayBasedTree(int[] arr) {
        System.out.println("Array-based Segment Tree Example:");

        // Create a sum segment tree
        _541_b_ArraySegmentTree sumTree = new _541_b_ArraySegmentTree(arr, (a, b) -> a + b);

        // Perform range queries
        System.out.println("Sum of range [1, 3]: " + sumTree.query(1, 3));
        System.out.println("Sum of range [0, 4]: " + sumTree.query(0, 4));

        // Update elements and query again
        System.out.println("\nUpdating index 2 from " + arr[2] + " to 10...");
        sumTree.update(2, 10);
        System.out.println("Sum of range [1, 3] after update: " + sumTree.query(1, 3));

        // Update another element
        System.out.println("Updating index 0 from " + arr[0] + " to 15...");
        sumTree.update(0, 15);
        System.out.println("Sum of range [0, 2] after update: " + sumTree.query(0, 2));
    }

    /**
     * Compares the performance and memory characteristics of both implementations
     */
    private static void compareImplementations(int[] arr) {
        System.out.println("Performance and Memory Usage Comparison:");

        // Memory estimation (theoretical)
        int n = arr.length;
        int nodeBased = n * (4 * 4 + 8 + 8); // 4 ints, 2 references per node
        int arrayBased = 4 * n * 4; // 4n array of ints

        System.out.println("Theoretical memory usage for array size " + n + ":");
        System.out.println("Node-based: ~" + nodeBased + " bytes (more with object overhead)");
        System.out.println("Array-based: ~" + arrayBased + " bytes");

        // Simple timing comparison
        System.out.println("\nSimple timing comparison (not scientific):");

        // Create larger array for timing
        int[] largeArr = new int[10000];
        for (int i = 0; i < largeArr.length; i++) {
            largeArr[i] = i;
        }

        // Time node-based construction
        long startTime = System.nanoTime();
        _541_a_NodeSegmentTree nodeTree = new _541_a_NodeSegmentTree(largeArr, (a, b) -> a + b);
        long nodeConstructTime = System.nanoTime() - startTime;

        // Time array-based construction
        startTime = System.nanoTime();
        _541_b_ArraySegmentTree arrayTree = new _541_b_ArraySegmentTree(largeArr, (a, b) -> a + b);
        long arrayConstructTime = System.nanoTime() - startTime;

        System.out.println("Construction time for 10,000 elements:");
        System.out.println("Node-based: " + nodeConstructTime / 1_000_000.0 + " ms");
        System.out.println("Array-based: " + arrayConstructTime / 1_000_000.0 + " ms");

        // Time queries
        int iterations = 10000;

        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            int l = i % 5000;
            int r = l + 1000;
            nodeTree.query(l, r);
        }
        long nodeQueryTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            int l = i % 5000;
            int r = l + 1000;
            arrayTree.query(l, r);
        }
        long arrayQueryTime = System.nanoTime() - startTime;

        System.out.println("\nQuery time for " + iterations + " queries:");
        System.out.println("Node-based: " + nodeQueryTime / 1_000_000.0 + " ms");
        System.out.println("Array-based: " + arrayQueryTime / 1_000_000.0 + " ms");

        // Time updates
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            int idx = i % 10000;
            nodeTree.update(idx, i);
        }
        long nodeUpdateTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            int idx = i % 10000;
            arrayTree.update(idx, i);
        }
        long arrayUpdateTime = System.nanoTime() - startTime;

        System.out.println("\nUpdate time for " + iterations + " updates:");
        System.out.println("Node-based: " + nodeUpdateTime / 1_000_000.0 + " ms");
        System.out.println("Array-based: " + arrayUpdateTime / 1_000_000.0 + " ms");
    }

    /**
     * Demonstrates various aggregation functions possible with segment trees
     */
    private static void demonstrateAggregationFunctions(int[] arr) {
        System.out.println("Segment Trees with Different Aggregation Functions:");

        // Sum segment tree (already demonstrated)
        _541_b_ArraySegmentTree sumTree = new _541_b_ArraySegmentTree(arr, (a, b) -> a + b);
        System.out.println("Sum of range [0, 4]: " + sumTree.query(0, 4));

        // Maximum segment tree
        _541_b_ArraySegmentTree maxTree = new _541_b_ArraySegmentTree(arr, Math::max);
        System.out.println("Maximum in range [0, 4]: " + maxTree.query(0, 4));

        // Minimum segment tree
        _541_b_ArraySegmentTree minTree = new _541_b_ArraySegmentTree(arr, Math::min);
        System.out.println("Minimum in range [0, 4]: " + minTree.query(0, 4));

        // Product segment tree
        _541_b_ArraySegmentTree productTree = new _541_b_ArraySegmentTree(arr, (a, b) -> a * b);
        System.out.println("Product of range [0, 4]: " + productTree.query(0, 4));

        // GCD segment tree (Greatest Common Divisor)
        BinaryOperator<Integer> gcdFunction = (a, b) -> {
            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }
            return a;
        };
        _541_b_ArraySegmentTree gcdTree = new _541_b_ArraySegmentTree(arr, gcdFunction);
        System.out.println("GCD of range [0, 4]: " + gcdTree.query(0, 4));

        System.out.println("\nAdvanced Application Examples:");
        System.out.println("1. Range Frequency Queries - Count occurrences of elements in a range");
        System.out.println("2. Range Updates with Lazy Propagation - Update entire ranges efficiently");
        System.out.println("3. Persistent Segment Trees - Store previous versions of the tree");
        System.out.println("4. 2D Segment Trees - Handle queries on 2D grids");
    }

    /**
     * A simple example of how a range update could be implemented
     * Note: This is NOT an efficient implementation, just a demonstration
     */
    public static void rangeUpdateExample() {
        int[] arr = {1, 3, 5, 7, 9};
        _541_b_ArraySegmentTree tree = new _541_b_ArraySegmentTree(arr, (a, b) -> a + b);

        // Inefficient range update (O(m log n) where m is range size)
        int l = 1, r = 3, val = 10;
        for (int i = l; i <= r; i++) {
            tree.update(i, val);
        }

        System.out.println("Sum after range update: " + tree.query(0, 4));

        // Note: An efficient range update would use lazy propagation
        // with O(log n) time complexity instead of updating each element
    }
}