package org.qinlinj.nonlinear.tree.btree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// @formatter:off
/**
 * Test class for B-Tree implementation
 *
 * This class provides comprehensive testing for the BTree class, including:
 * - Basic operations (insert, search, remove)
 * - Edge cases (empty tree, single element)
 * - Sequential and random insertions
 * - Large dataset testing
 * - Tree structure verification
 * - Performance measurement
 */
public class BTreeTest {

    /**
     * Main method to run all test cases
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        testEmptyTree();
        testBasicInsertionAndSearch();
        testSequentialInsertion();
        testRandomInsertion();
        testRemoval();
        testComplexRemoval();
        testInOrderTraversal();
        testLargeDataset();

        System.out.println("\nAll tests completed successfully!");
    }

    /**
     * Tests operations on an empty B-tree
     *
     * Verifies that:
     * - A newly created tree is empty
     * - Search operations return correct results
     * - The height is as expected
     */
    private static void testEmptyTree() {
        System.out.println("Testing empty tree...");

        // Create a B-tree with minimum degree 3
        BTree<Integer> tree = new BTree<>(3);

        // Verify search returns false for any key
        assert !tree.search(10) : "Search should return false for empty tree";

        // Verify height is 1 (a single node)
        assert tree.height() == 1 : "Empty tree should have height 1";

        System.out.println("Empty tree test passed!");
    }

    /**
     * Tests basic insertion and search operations
     *
     * Verifies that:
     * - Elements can be inserted correctly
     * - Search operations find inserted elements
     * - Search returns false for non-existent elements
     */
    private static void testBasicInsertionAndSearch() {
        System.out.println("\nTesting basic insertion and search...");

        // Create a B-tree with minimum degree 3
        BTree<Integer> tree = new BTree<>(3);

        // Insert some values
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(15);
        tree.insert(30);

        // Verify search finds all inserted elements
        assert tree.search(10) : "Tree should contain 10";
        assert tree.search(20) : "Tree should contain 20";
        assert tree.search(5) : "Tree should contain 5";
        assert tree.search(15) : "Tree should contain 15";
        assert tree.search(30) : "Tree should contain 30";

        // Verify search doesn't find non-existent elements
        assert !tree.search(25) : "Tree should not contain 25";
        assert !tree.search(1) : "Tree should not contain 1";

        // Print tree structure
        System.out.println("Tree structure after basic insertions:");
        System.out.println(tree);

        System.out.println("Basic insertion and search test passed!");
    }

    /**
     * Tests sequential insertion of elements
     *
     * This specifically tests how the tree handles ordered data,
     * which can be challenging for some tree structures.
     */
    private static void testSequentialInsertion() {
        System.out.println("\nTesting sequential insertion...");

        // Create a B-tree with minimum degree 2 (minimum possible degree)
        BTree<Integer> tree = new BTree<>(2);

        // Insert elements in ascending order
        for (int i = 1; i <= 20; i++) {
            tree.insert(i);

            // Verify each insertion
            assert tree.search(i) : "Tree should contain " + i + " after insertion";
        }

        // Print final tree structure
        System.out.println("Tree structure after sequential insertions:");
        System.out.println(tree);

        // Verify the tree's height isn't degenerated (should be logarithmic)
        int height = tree.height();
        System.out.println("Tree height after 20 sequential insertions: " + height);
        assert height <= 5 : "Tree height should be logarithmic, not linear";

        System.out.println("Sequential insertion test passed!");
    }

    /**
     * Tests random insertion of elements
     *
     * This verifies the tree's behavior with non-sequential data
     * and checks that all elements are properly inserted and searchable.
     */
    private static void testRandomInsertion() {
        System.out.println("\nTesting random insertion...");

        // Create a B-tree with minimum degree 3
        BTree<Integer> tree = new BTree<>(3);

        // Create a list of numbers and shuffle it
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, new Random(42)); // Fixed seed for reproducibility

        // Insert the shuffled numbers
        for (int num : numbers) {
            tree.insert(num);
        }

        // Verify all numbers can be found
        for (int i = 1; i <= 30; i++) {
            assert tree.search(i) : "Tree should contain " + i + " after all insertions";
        }

        // Print tree structure
        System.out.println("Tree structure after random insertions:");
        System.out.println(tree);

        System.out.println("Random insertion test passed!");
    }

    /**
     * Tests basic removal operations
     *
     * Verifies that:
     * - Elements can be removed correctly
     * - Search operations don't find removed elements
     * - The tree structure remains valid after removals
     */
    private static void testRemoval() {
        System.out.println("\nTesting basic removal...");

        // Create a B-tree with minimum degree 2
        BTree<Integer> tree = new BTree<>(2);

        // Insert elements
        for (int i = 10; i <= 100; i += 10) {
            tree.insert(i);
        }

        System.out.println("Tree structure before removals:");
        System.out.println(tree);

        // Remove some elements
        tree.remove(20);
        tree.remove(50);
        tree.remove(80);

        // Verify removed elements are no longer present
        assert !tree.search(20) : "Tree should not contain 20 after removal";
        assert !tree.search(50) : "Tree should not contain 50 after removal";
        assert !tree.search(80) : "Tree should not contain 80 after removal";

        // Verify remaining elements are still present
        assert tree.search(10) : "Tree should still contain 10";
        assert tree.search(30) : "Tree should still contain 30";
        assert tree.search(40) : "Tree should still contain 40";
        assert tree.search(60) : "Tree should still contain 60";
        assert tree.search(70) : "Tree should still contain 70";
        assert tree.search(90) : "Tree should still contain 90";
        assert tree.search(100) : "Tree should still contain 100";

        System.out.println("Tree structure after removals:");
        System.out.println(tree);

        System.out.println("Basic removal test passed!");
    }

    /**
     * Tests more complex removal scenarios
     *
     * This tests various edge cases in removal:
     * - Removing the root
     * - Removing elements that require rebalancing
     * - Removing elements that require merging
     */
    private static void testComplexRemoval() {
        System.out.println("\nTesting complex removal scenarios...");

        // Create a B-tree with minimum degree 2
        BTree<Integer> tree = new BTree<>(2);

        // Insert a specific pattern of elements that will test different removal cases
        int[] elements = {50, 25, 75, 12, 37, 62, 87, 6, 18, 31, 43, 56, 68, 81, 93};
        for (int element : elements) {
            tree.insert(element);
        }

        System.out.println("Tree structure before complex removals:");
        System.out.println(tree);

        // Test removing a leaf node key
        tree.remove(6);
        assert !tree.search(6) : "Tree should not contain 6 after removal";

        // Test removing an internal node key (forces replacement with predecessor or successor)
        tree.remove(25);
        assert !tree.search(25) : "Tree should not contain 25 after removal";

        // Test removing root key
        tree.remove(50);
        assert !tree.search(50) : "Tree should not contain 50 after removal";

        System.out.println("Tree structure after complex removals:");
        System.out.println(tree);

        // Verify the remaining structure with in-order traversal
        List<Integer> inOrder = tree.inOrderTraversal();
        List<Integer> expected = new ArrayList<>();
        for (int element : elements) {
            if (element != 6 && element != 25 && element != 50) {
                expected.add(element);
            }
        }
        Collections.sort(expected);

        assert inOrder.equals(expected) : "In-order traversal should match expected remaining elements";

        System.out.println("Complex removal test passed!");
    }

    /**
     * Tests in-order traversal functionality
     *
     * Verifies that in-order traversal produces elements in sorted order
     */
    private static void testInOrderTraversal() {
        System.out.println("\nTesting in-order traversal...");

        // Create a B-tree with minimum degree 3
        BTree<Integer> tree = new BTree<>(3);

        // Create list of numbers in random order
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, new Random(42));

        // Insert in random order
        for (int num : numbers) {
            tree.insert(num);
        }

        // Get in-order traversal
        List<Integer> inOrder = tree.inOrderTraversal();

        // Verify traversal is sorted
        for (int i = 0; i < inOrder.size() - 1; i++) {
            assert inOrder.get(i) < inOrder.get(i + 1) :
                    "In-order traversal should produce sorted list, but found " +
                            inOrder.get(i) + " before " + inOrder.get(i + 1);
        }

        // Verify all elements are present
        Collections.sort(numbers);
        assert inOrder.equals(numbers) : "In-order traversal should contain all inserted elements";

        System.out.println("In-order traversal test passed!");
    }

    /**
     * Tests the B-tree with a large dataset to evaluate performance
     *
     * This test:
     * - Inserts a large number of elements
     * - Measures the time taken for insertions
     * - Verifies all elements can be found
     * - Tests random removal operations
     */
    private static void testLargeDataset() {
        System.out.println("\nTesting with large dataset...");

        // Create a B-tree with minimum degree 10 (higher degree is better for large datasets)
        BTree<Integer> tree = new BTree<>(10);

        // Number of elements to test
        int numElements = 10000;

        // Create dataset
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= numElements; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, new Random(42));

        // Measure insertion time
        long startTime = System.nanoTime();

        for (int num : numbers) {
            tree.insert(num);
        }

        long endTime = System.nanoTime();
        double insertionTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds

        System.out.println("Inserted " + numElements + " elements in " + insertionTime + " ms");

        // Measure search time for random elements
        startTime = System.nanoTime();

        // Select 1000 random elements to search for
        Random random = new Random(42);
        for (int i = 0; i < 1000; i++) {
            int numToFind = random.nextInt(numElements) + 1;
            assert tree.search(numToFind) : "Tree should contain " + numToFind;
        }

        endTime = System.nanoTime();
        double searchTime = (endTime - startTime) / 1_000_000.0;

        System.out.println("Performed 1000 random searches in " + searchTime + " ms");

        // Test removing 1000 random elements
        startTime = System.nanoTime();

        for (int i = 0; i < 1000; i++) {
            int numToRemove = random.nextInt(numElements) + 1;
            tree.remove(numToRemove);
        }

        endTime = System.nanoTime();
        double removalTime = (endTime - startTime) / 1_000_000.0;

        System.out.println("Removed 1000 random elements in " + removalTime + " ms");

        // Check tree height after operations
        int treeHeight = tree.height();
        System.out.println("Tree height after operations: " + treeHeight);
        assert treeHeight <= 8 : "Tree height should be logarithmic in the number of elements";

        System.out.println("Large dataset test passed!");
    }
}