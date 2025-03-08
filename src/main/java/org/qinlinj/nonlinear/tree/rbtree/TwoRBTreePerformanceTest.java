package org.qinlinj.nonlinear.tree.rbtree;

import org.qinlinj.nonlinear.tree.avltree.AVLTree;
import org.qinlinj.nonlinear.tree.binarysearchtree.BinarySearchTree;

import java.util.ArrayList;
import java.util.Random;

/**
 * ANALYSIS OF TREE IMPLEMENTATIONS
 * <p>
 * 1. Binary Search Tree (BST)
 * Advantages:
 * - Simplest implementation with minimal memory overhead
 * - Very fast for already-sorted or nearly-sorted data
 * - Performs well when data is randomly distributed
 * <p>
 * Disadvantages:
 * - Can degenerate to a linked list (O(n) operations) with sorted input
 * - No guaranteed balance, worst-case O(n) for search/insert/delete
 * - Performance highly dependent on insertion order
 * <p>
 * Best use cases:
 * - When input is known to be random
 * - When simplicity is more important than guaranteed performance
 * - In memory-constrained environments
 * <p>
 * 2. AVL Tree
 * Advantages:
 * - Maintains perfect balance (height difference ≤ 1 between subtrees)
 * - Guaranteed O(log n) operations regardless of input order
 * - Fastest retrieval operations among balanced trees
 * <p>
 * Disadvantages:
 * - More rotations during insertion/deletion than Red-Black trees
 * - Higher overhead for maintaining balance factor in each node
 * - Can be slower for insertion-heavy workloads due to strict balancing
 * <p>
 * Best use cases:
 * - When retrieval/search operations are more frequent than insertions
 * - When guaranteed worst-case performance is critical
 * - Database indexing and other search-intensive applications
 * <p>
 * 3. Left-Leaning Red-Black Tree (LLRB)
 * Advantages:
 * - Simpler implementation than standard Red-Black Tree
 * - Clear 1:1 relationship with 2-3 trees
 * - Guaranteed O(log n) operations
 * - Less strict balancing than AVL (faster insertions/deletions)
 * <p>
 * Disadvantages:
 * - More rotations than standard Red-Black Tree
 * - Slightly less efficient than standard RB Tree for some operations
 * - Enforced left-leaning property adds some overhead
 * <p>
 * Best use cases:
 * - When implementation simplicity is valued
 * - Educational settings to understand Red-Black trees
 * - Systems with a balance of insertions and searches
 * <p>
 * 4. Red-Black Tree
 * Advantages:
 * - Excellent general-purpose balanced tree
 * - Fewer rotations than AVL during insertion/deletion
 * - Guaranteed O(log n) operations with low constant factors
 * - Used in most standard libraries (Java TreeMap, C++ std::map)
 * <p>
 * Disadvantages:
 * - More complex implementation than BST or LLRB
 * - Trees can be less balanced than AVL (up to 2x height difference)
 * - Slightly slower retrieval than AVL in some cases
 * <p>
 * Best use cases:
 * - General-purpose applications with mixed operations
 * - Systems with frequent insertions and deletions
 * - When predictable performance is needed across all operations
 * <p>
 * PERFORMANCE COMPARISON SUMMARY:
 * <p>
 * - BST: Fastest for random data, but unpredictable and potentially O(n) in worst case
 * - AVL: Most balanced and best for search-heavy workloads, but higher insertion/deletion cost
 * - LLRB: Good middle ground with simpler implementation, slightly less efficient than RB
 * - RB: Best overall performer for mixed workloads with a balance of operations
 * <p>
 * With random data as in this test:
 * 1. BST typically performs very well (often fastest) because random data tends to create balanced trees
 * 2. RB usually outperforms AVL due to less strict balancing requirements
 * 3. LLRB is typically slightly slower than standard RB due to enforced left-leaning property
 * 4. All balanced trees (AVL, LLRB, RB) will significantly outperform BST with non-random data
 */
public class TwoRBTreePerformanceTest {
    private static Random random = new Random();

    /**
     * Performance comparison of different tree implementations:
     * - Binary Search Tree (BST)
     * - AVL Tree
     * - Left-Leaning Red-Black Tree (LLRB)
     * - Red-Black Tree (RB)
     * <p>
     * This test measures insertion performance for each tree type with a large
     * number of random integers. The test helps understand the trade-offs between
     * different self-balancing tree implementations.
     */
    public static void main(String[] args) {
        // Number of elements to insert in each tree
        int num = 2000000;

        // Generate a list of random integers (same list used for all trees for fair comparison)
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(random.nextInt());
        }

        // Test 1: Binary Search Tree
        long startTime = System.nanoTime();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer i : list) bst.add(i);
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BST：" + time + " s");

        // Test 2: AVL Tree
        startTime = System.nanoTime();
        AVLTree<Integer> avl = new AVLTree<>();
        for (Integer i : list) avl.add(i);
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL：" + time + " s");

        // Test 3: Left-Leaning Red-Black Tree
        startTime = System.nanoTime();
        LLRBTree<Integer> llrbTree = new LLRBTree<>();
        for (Integer i : list) llrbTree.add(i);
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("LLRB：" + time + " s");

        // Test 4: Red-Black Tree
        startTime = System.nanoTime();
        RBTree<Integer> rbTree = new RBTree<>();
        for (Integer i : list) rbTree.add(i);
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RB：" + time + " s");
    }
}
