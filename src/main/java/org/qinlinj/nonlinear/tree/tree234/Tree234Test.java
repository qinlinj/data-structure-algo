package org.qinlinj.nonlinear.tree.tree234;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Comprehensive test class for the 2-3-4 Tree implementation
 * This class tests all major operations:
 * - Insertion (sequential, random, and duplicate values)
 * - Search
 * - Deletion (leaf nodes, internal nodes)
 * - Tree traversal
 * - Edge cases (empty tree, single node tree)
 * - Performance with large datasets
 */
public class Tree234Test {

    /**
     * Main method to run all the tests
     */
    public static void main(String[] args) {
        testEmptyTree();
        testBasicInsertion();
        testSequentialInsertion();
        testRandomInsertion();
        testContains();
        testDuplicateInsertion();
        testBasicRemoval();
        testComplexRemoval();
        testInOrderTraversal();
        testLevelOrderTraversal();
        testHeight();
        testLargeDataset();

        System.out.println("\nAll tests completed successfully!");
    }

    /**
     * Test empty tree operations
     */
    private static void testEmptyTree() {
        System.out.println("Testing empty tree...");

        Tree234<Integer> tree = new Tree234<>();

        assert tree.isEmpty() : "New tree should be empty";
        assert tree.size() == 0 : "New tree should have size 0";
        assert !tree.contains(10) : "Empty tree should not contain any elements";
        assert tree.height() == 0 : "Empty tree should have height 0";
        assert tree.inOrderTraversal().isEmpty() : "In-order traversal of empty tree should be empty";
        assert !tree.remove(5) : "Removing from empty tree should return false";

        System.out.println("Empty tree tests passed!\n");
    }

    /**
     * Test basic insertion operations
     */
    private static void testBasicInsertion() {
        System.out.println("Testing basic insertion...");

        Tree234<Integer> tree = new Tree234<>();

        // Insert into empty tree
        tree.insert(10);
        assert tree.size() == 1 : "Tree size should be 1 after insertion";
        assert !tree.isEmpty() : "Tree should not be empty after insertion";
        assert tree.contains(10) : "Tree should contain inserted element";

        // Insert more elements to create different node types
        tree.insert(5);    // Should still be a single node with [5, 10]
        tree.insert(15);   // Should still be a single node with [5, 10, 15]
        tree.insert(20);   // Should cause a split and create a multi-level tree

        assert tree.size() == 4 : "Tree size should be 4 after insertions";
        assert tree.contains(5) : "Tree should contain element 5";
        assert tree.contains(15) : "Tree should contain element 15";
        assert tree.contains(20) : "Tree should contain element 20";

        // Print the tree to visualize its structure
        System.out.println("Tree after basic insertions: ");
        System.out.println(tree);

        System.out.println("Basic insertion tests passed!\n");
    }

    /**
     * Test inserting elements in sequential order
     */
    private static void testSequentialInsertion() {
        System.out.println("Testing sequential insertion...");

        Tree234<Integer> tree = new Tree234<>();

        // Insert elements in ascending order - this should test the splitting mechanism thoroughly
        for (int i = 1; i <= 20; i++) {
            tree.insert(i);

            // Uncomment to visualize tree growth
            // System.out.println("After adding " + i + ":");
            // System.out.println(tree);
        }

        assert tree.size() == 20 : "Tree size should be 20 after insertions";

        // Check that all elements are present
        for (int i = 1; i <= 20; i++) {
            assert tree.contains(i) : "Tree should contain element " + i;
        }

        assert !tree.contains(0) : "Tree should not contain element 0";
        assert !tree.contains(21) : "Tree should not contain element 21";

        System.out.println("Sequential insertion tests passed!\n");
    }

    /**
     * Test inserting elements in random order
     */
    private static void testRandomInsertion() {
        System.out.println("Testing random insertion...");

        Tree234<Integer> tree = new Tree234<>();
        Random random = new Random(42); // Fixed seed for reproducibility

        List<Integer> values = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            values.add(i);
        }
        Collections.shuffle(values, random);

        // Insert shuffled elements
        for (int value : values) {
            tree.insert(value);
        }

        assert tree.size() == 100 : "Tree size should be 100 after insertions";

        // Check that all elements are present
        for (int i = 1; i <= 100; i++) {
            assert tree.contains(i) : "Tree should contain element " + i;
        }

        System.out.println("Random insertion tests passed!\n");
    }

    /**
     * Test 'contains' method thoroughly
     */
    private static void testContains() {
        System.out.println("Testing contains method...");

        Tree234<Integer> tree = new Tree234<>();

        // Insert some elements
        int[] elements = {10, 5, 15, 3, 7, 12, 17, 1, 4, 6, 8, 11, 13, 16, 19};
        for (int element : elements) {
            tree.insert(element);
        }

        // Check all inserted elements
        for (int element : elements) {
            assert tree.contains(element) : "Tree should contain element " + element;
        }

        // Check elements not in the tree
        int[] nonElements = {0, 2, 9, 14, 18, 20};
        for (int element : nonElements) {
            assert !tree.contains(element) : "Tree should not contain element " + element;
        }

        System.out.println("Contains tests passed!\n");
    }

    /**
     * Test inserting duplicate elements
     */
    private static void testDuplicateInsertion() {
        System.out.println("Testing duplicate insertion...");

        Tree234<Integer> tree = new Tree234<>();

        // Insert unique elements
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        assert tree.size() == 3 : "Tree size should be 3 after unique insertions";

        // Insert duplicates - behavior depends on implementation
        // Some implementations ignore duplicates, others might store them
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        // Note: This test expects the implementation to allow duplicates
        // If your implementation ignores duplicates, adjust this assertion
        assert tree.size() == 6 : "Tree should handle duplicate insertions (size should be 6)";

        System.out.println("Duplicate insertion tests passed!\n");
    }

    /**
     * Test basic element removal
     */
    private static void testBasicRemoval() {
        System.out.println("Testing basic removal...");

        Tree234<Integer> tree = new Tree234<>();

        // Insert elements
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(12);
        tree.insert(17);

        // Show initial tree
        System.out.println("Tree before removal: ");
        System.out.println(tree);

        // Remove leaf elements
        assert tree.remove(3) : "Remove should return true for existing element";
        assert !tree.contains(3) : "Tree should not contain removed element";
        assert tree.size() == 6 : "Tree size should decrease after removal";

        // Show tree after first removal
        System.out.println("Tree after removing 3: ");
        System.out.println(tree);

        assert tree.remove(7) : "Remove should return true for existing element";
        assert !tree.contains(7) : "Tree should not contain removed element";
        assert tree.size() == 5 : "Tree size should decrease after removal";

        // Show tree after second removal
        System.out.println("Tree after removing 7: ");
        System.out.println(tree);

        // Try removing non-existent element
        assert !tree.remove(100) : "Remove should return false for non-existent element";
        assert tree.size() == 5 : "Tree size should not change after failed removal";

        System.out.println("Basic removal tests passed!\n");
    }

    /**
     * Test more complex removal scenarios
     */
    private static void testComplexRemoval() {
        System.out.println("Testing complex removal...");

        Tree234<Integer> tree = new Tree234<>();

        // Insert a sequence of elements
        int[] elements = {50, 25, 75, 12, 37, 62, 87, 6, 18, 31, 43, 56, 68, 81, 93};
        for (int element : elements) {
            tree.insert(element);
        }

        // Initial size check
        assert tree.size() == 15 : "Tree should have 15 elements before removal";

        // Show initial tree
        System.out.println("Tree before complex removals: ");
        System.out.println(tree);

        // Test removing internal nodes (which requires more complex handling)
        assert tree.remove(50) : "Should be able to remove an internal node";
        assert !tree.contains(50) : "Tree should not contain removed element";
        assert tree.size() == 14 : "Tree size should decrease after removal";

        System.out.println("Tree after removing 50: ");
        System.out.println(tree);

        assert tree.remove(25) : "Should be able to remove an internal node";
        assert !tree.contains(25) : "Tree should not contain removed element";
        assert tree.size() == 13 : "Tree size should decrease after removal";

        System.out.println("Tree after removing 25: ");
        System.out.println(tree);

        // Test removing the minimum element
        assert tree.remove(6) : "Should be able to remove the minimum element";
        assert !tree.contains(6) : "Tree should not contain removed element";

        // Test removing the maximum element
        assert tree.remove(93) : "Should be able to remove the maximum element";
        assert !tree.contains(93) : "Tree should not contain removed element";

        // Check that the tree is still valid by verifying remaining elements
        for (int element : elements) {
            if (element != 50 && element != 25 && element != 6 && element != 93) {
                assert tree.contains(element) : "Tree should still contain element " + element;
            }
        }

        System.out.println("Tree after all complex removals: ");
        System.out.println(tree);

        System.out.println("Complex removal tests passed!\n");
    }

    /**
     * Test in-order traversal
     */
    private static void testInOrderTraversal() {
        System.out.println("Testing in-order traversal...");

        Tree234<Integer> tree = new Tree234<>();

        // Insert elements not in order
        int[] elements = {50, 25, 75, 12, 37, 62, 87, 6, 18, 31, 43, 56, 68, 81, 93};
        for (int element : elements) {
            tree.insert(element);
        }

        // Get in-order traversal
        List<Integer> traversal = tree.inOrderTraversal();

        // Create expected sorted list
        List<Integer> expected = new ArrayList<>();
        for (int element : elements) {
            expected.add(element);
        }
        Collections.sort(expected);

        // Compare
        assert traversal.size() == expected.size() : "Traversal should have same size as input";
        for (int i = 0; i < expected.size(); i++) {
            assert traversal.get(i).equals(expected.get(i)) :
                    "Traversal element at index " + i + " should be " + expected.get(i) + ", got " + traversal.get(i);
        }

        System.out.println("In-order traversal tests passed!\n");
    }

    /**
     * Test level-order traversal
     */
    private static void testLevelOrderTraversal() {
        System.out.println("Testing level-order traversal...");

        Tree234<Integer> tree = new Tree234<>();

        // Insert a known sequence that produces a predictable tree
        tree.insert(50);
        tree.insert(25);
        tree.insert(75);
        tree.insert(12);
        tree.insert(37);
        tree.insert(62);
        tree.insert(87);

        // Get level-order traversal
        List<List<Integer>> levelTraversal = tree.levelOrderTraversal();

        // Print for inspection
        System.out.println("Level-order traversal by levels: " + levelTraversal);

        // Flatten for easier checking if needed
        List<Integer> flatTraversal = new ArrayList<>();
        for (List<Integer> level : levelTraversal) {
            flatTraversal.addAll(level);
        }

        System.out.println("Flattened level-order traversal: " + flatTraversal);

        // The root should be in the first level
        assert levelTraversal.get(0).contains(50) : "Root should be in the first level";

        // Check size at minimum
        int totalElements = 0;
        for (List<Integer> level : levelTraversal) {
            totalElements += level.size();
        }
        assert totalElements == 7 : "Traversal should include all elements";

        System.out.println("Level-order traversal tests passed!\n");
    }

    /**
     * Test tree height calculation
     */
    private static void testHeight() {
        System.out.println("Testing tree height...");

        // Empty tree
        Tree234<Integer> emptyTree = new Tree234<>();
        assert emptyTree.height() == 0 : "Empty tree should have height 0";

        // Single node tree
        Tree234<Integer> singleNodeTree = new Tree234<>();
        singleNodeTree.insert(10);
        assert singleNodeTree.height() == 1 : "Single node tree should have height 1";

        // Create a tree with multiple levels by inserting enough elements
        Tree234<Integer> multiLevelTree = new Tree234<>();
        for (int i = 1; i <= 20; i++) {
            multiLevelTree.insert(i);
        }

        // Height should be logarithmic with the number of elements
        int height = multiLevelTree.height();
        System.out.println("Tree with 20 elements has height: " + height);
        assert height > 1 : "Tree with 20 elements should have height > 1";
        assert height < 20 : "Tree with 20 elements should have height < 20 (should be logarithmic)";

        System.out.println("Height tests passed!\n");
    }

    /**
     * Test with larger dataset to check performance
     */
    private static void testLargeDataset() {
        System.out.println("Testing with large dataset...");

        Tree234<Integer> tree = new Tree234<>();
        Random random = new Random(42); // Fixed seed for reproducibility

        // Number of elements to insert
        int numElements = 10000;

        // Create a shuffled list of values
        List<Integer> values = new ArrayList<>();
        for (int i = 1; i <= numElements; i++) {
            values.add(i);
        }
        Collections.shuffle(values, random);

        // Measure insertion time
        long startTime = System.nanoTime();

        for (int value : values) {
            tree.insert(value);
        }

        long endTime = System.nanoTime();
        double insertionTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds

        System.out.println("Inserted " + numElements + " elements in " + insertionTime + " ms");

        // Verify tree size
        assert tree.size() == numElements : "Tree size should match number of inserted elements";

        // Measure search time (contains)
        startTime = System.nanoTime();

        for (int i = 1; i <= numElements; i++) {
            assert tree.contains(i) : "Tree should contain all inserted elements";
        }

        endTime = System.nanoTime();
        double searchTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds

        System.out.println("Performed " + numElements + " searches in " + searchTime + " ms");

        // Measure traversal time
        startTime = System.nanoTime();

        List<Integer> traversal = tree.inOrderTraversal();

        endTime = System.nanoTime();
        double traversalTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds

        System.out.println("In-order traversal of " + traversal.size() + " elements in " + traversalTime + " ms");
        assert traversal.size() == numElements : "Traversal should include all elements";

        // Measure removal time (remove half of the elements)
        startTime = System.nanoTime();

        int removeCount = numElements / 2;
        for (int i = 1; i <= removeCount; i++) {
            assert tree.remove(i) : "Should be able to remove all elements";
        }

        endTime = System.nanoTime();
        double removalTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds

        System.out.println("Removed " + removeCount + " elements in " + removalTime + " ms");
        assert tree.size() == numElements - removeCount : "Tree size should reflect removals";

        System.out.println("Large dataset tests passed!\n");
    }
}