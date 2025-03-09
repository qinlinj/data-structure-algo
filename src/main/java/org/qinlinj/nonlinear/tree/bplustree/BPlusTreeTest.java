package org.qinlinj.nonlinear.tree.bplustree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Test class for B+ Tree implementation
 * <p>
 * This class provides comprehensive testing for the BPlusTree class, including:
 * - Basic operations (insert, search, delete)
 * - Range queries
 * - Sequential and random data patterns
 * - Edge cases (empty tree, single element)
 * - Large dataset testing
 * - Tree structure verification
 */
public class BPlusTreeTest {

    /**
     * Main method to run all test cases
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        testEmptyTree();
        testBasicOperations();
        testSequentialInsertion();
        testRandomInsertion();
        testDuplicateKeys();
        testRangeQueries();
        testDeletion();
        testLargeDataset();

        System.out.println("\nAll tests completed successfully!");
    }

    /**
     * Tests operations on an empty B+ tree
     * <p>
     * Verifies that:
     * - A newly created tree is empty
     * - Search operations return null
     * - The height is as expected
     */
    private static void testEmptyTree() {
        System.out.println("Testing empty tree...");

        // Create a B+ tree with order 5
        BPlusTree<Integer, String> tree = new BPlusTree<>(5);

        // Verify tree properties
        assert tree.isEmpty() : "Newly created tree should be empty";
        assert tree.search(10) == null : "Search on empty tree should return null";
        assert tree.getHeight() == 1 : "Empty tree should have height 1 (just the root)";
        assert tree.size() == 0 : "Empty tree should have size 0";

        // Verify range query returns empty list
        assert tree.rangeQuery(1, 100).isEmpty() : "Range query on empty tree should return empty list";

        System.out.println("Empty tree test passed!");
    }

    /**
     * Tests basic insertion, search, and range query operations
     * <p>
     * Verifies that:
     * - Elements can be inserted correctly
     * - Search operations find inserted elements
     * - Search returns null for non-existent elements
     * - Range queries return correct results
     */
    private static void testBasicOperations() {
        System.out.println("\nTesting basic operations...");

        // Create a B+ tree with order 5
        BPlusTree<Integer, String> tree = new BPlusTree<>(5);

        // Insert some key-value pairs
        tree.insert(10, "Ten");
        tree.insert(20, "Twenty");
        tree.insert(5, "Five");
        tree.insert(15, "Fifteen");
        tree.insert(30, "Thirty");

        // Verify search results
        assert tree.search(10).equals("Ten") : "Search should find key 10";
        assert tree.search(20).equals("Twenty") : "Search should find key 20";
        assert tree.search(5).equals("Five") : "Search should find key 5";
        assert tree.search(15).equals("Fifteen") : "Search should find key 15";
        assert tree.search(30).equals("Thirty") : "Search should find key 30";

        // Verify search for non-existent key
        assert tree.search(25) == null : "Search should return null for non-existent key";

        // Verify tree properties
        assert !tree.isEmpty() : "Tree should not be empty after insertions";
        assert tree.size() == 5 : "Tree should have 5 elements";

        // Verify all keys are present and in order
        List<Integer> allKeys = tree.getAllKeys();
        assert allKeys.size() == 5 : "getAllKeys should return 5 elements";
        assert allKeys.get(0) == 5 : "First key should be 5";
        assert allKeys.get(4) == 30 : "Last key should be 30";

        // Print tree structure
        System.out.println("Tree structure after basic insertions:");
        System.out.println(tree);

        System.out.println("Basic operations test passed!");
    }

    /**
     * Tests sequential insertion of elements
     * <p>
     * This specifically tests how the tree handles ordered data,
     * which can be challenging for some tree structures.
     */
    private static void testSequentialInsertion() {
        System.out.println("\nTesting sequential insertion...");

        // Create a B+ tree with order 4
        BPlusTree<Integer, String> tree = new BPlusTree<>(4);

        // Insert elements in ascending order
        for (int i = 1; i <= 20; i++) {
            tree.insert(i, "Value" + i);

            // Verify each insertion
            assert tree.search(i).equals("Value" + i) : "Tree should contain key " + i + " after insertion";
        }

        // Verify tree height (should be logarithmic, not linear)
        int height = tree.getHeight();
        System.out.println("Tree height after 20 sequential insertions: " + height);
        assert height <= 5 : "Tree height should be logarithmic, not linear";

        // Print tree structure
        System.out.println("Tree structure after sequential insertions:");
        System.out.println(tree);

        // Verify all keys are present and in order
        List<Integer> allKeys = tree.getAllKeys();
        assert allKeys.size() == 20 : "Tree should contain 20 keys";
        for (int i = 0; i < 20; i++) {
            assert allKeys.get(i) == i + 1 : "Key at index " + i + " should be " + (i + 1);
        }

        System.out.println("Sequential insertion test passed!");
    }

    /**
     * Tests random insertion of elements
     * <p>
     * This verifies the tree's behavior with non-sequential data
     * and checks that all elements are properly inserted and searchable.
     */
    private static void testRandomInsertion() {
        System.out.println("\nTesting random insertion...");

        // Create a B+ tree with order 5
        BPlusTree<Integer, String> tree = new BPlusTree<>(5);

        // Create a list of numbers and shuffle it
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, new Random(42)); // Fixed seed for reproducibility

        // Insert the shuffled numbers
        for (int num : numbers) {
            tree.insert(num, "Random" + num);
        }

        // Verify all numbers can be found
        for (int i = 1; i <= 30; i++) {
            String value = tree.search(i);
            assert value != null && value.equals("Random" + i) :
                    "Tree should contain key " + i + " with value Random" + i;
        }

        // Verify tree height (should be logarithmic)
        int height = tree.getHeight();
        System.out.println("Tree height after 30 random insertions: " + height);
        assert height <= 5 : "Tree height should be logarithmic";

        // Verify keys are in sorted order
        List<Integer> allKeys = tree.getAllKeys();
        assert allKeys.size() == 30 : "Tree should contain 30 keys";
        for (int i = 0; i < 29; i++) {
            assert allKeys.get(i) < allKeys.get(i + 1) :
                    "Keys should be in ascending order, but " + allKeys.get(i) +
                            " is not less than " + allKeys.get(i + 1);
        }

        System.out.println("Random insertion test passed!");
    }

    /**
     * Tests handling of duplicate keys
     * <p>
     * Verifies that:
     * - Inserting a duplicate key updates the value
     * - The tree size doesn't increase when updating existing keys
     */
    private static void testDuplicateKeys() {
        System.out.println("\nTesting duplicate keys...");

        // Create a B+ tree with order 4
        BPlusTree<Integer, String> tree = new BPlusTree<>(4);

        // Insert some key-value pairs
        tree.insert(10, "Ten");
        tree.insert(20, "Twenty");
        tree.insert(30, "Thirty");

        // Verify initial size
        assert tree.size() == 3 : "Tree should have 3 elements initially";

        // Update existing keys
        tree.insert(10, "Updated Ten");
        tree.insert(20, "Updated Twenty");

        // Verify size hasn't changed
        assert tree.size() == 3 : "Tree size should remain 3 after updating existing keys";

        // Verify updated values
        assert tree.search(10).equals("Updated Ten") : "Key 10 should have updated value";
        assert tree.search(20).equals("Updated Twenty") : "Key 20 should have updated value";
        assert tree.search(30).equals("Thirty") : "Key 30 should retain original value";

        System.out.println("Duplicate keys test passed!");
    }

    /**
     * Tests range query functionality
     * <p>
     * Verifies that:
     * - Range queries return all keys within the specified range
     * - Range queries handle boundary cases correctly
     * - Empty ranges return empty results
     */
    private static void testRangeQueries() {
        System.out.println("\nTesting range queries...");

        // Create a B+ tree with order 5
        BPlusTree<Integer, String> tree = new BPlusTree<>(5);

        // Insert elements 10, 20, 30, ... 100
        for (int i = 1; i <= 10; i++) {
            tree.insert(i * 10, "Value" + (i * 10));
        }

        // Test range query for subset of keys
        List<BPlusTree.KeyValuePair<Integer, String>> result1 = tree.rangeQuery(25, 75);
        assert result1.size() == 5 : "Range query [25, 75] should return 5 elements";
        assert result1.get(0).getKey() == 30 : "First key in range [25, 75] should be 30";
        assert result1.get(4).getKey() == 70 : "Last key in range [25, 75] should be 70";

        // Test range query with exact boundary matches
        List<BPlusTree.KeyValuePair<Integer, String>> result2 = tree.rangeQuery(20, 50);
        assert result2.size() == 4 : "Range query [20, 50] should return 4 elements";
        assert result2.get(0).getKey() == 20 : "First key in range [20, 50] should be 20";
        assert result2.get(3).getKey() == 50 : "Last key in range [20, 50] should be 50";

        // Test range query for all keys
        List<BPlusTree.KeyValuePair<Integer, String>> result3 = tree.rangeQuery(0, 200);
        assert result3.size() == 10 : "Range query [0, 200] should return all 10 elements";

        // Test range query for non-existent range
        List<BPlusTree.KeyValuePair<Integer, String>> result4 = tree.rangeQuery(101, 200);
        assert result4.isEmpty() : "Range query [101, 200] should return empty list";

        // Test range query for single key
        List<BPlusTree.KeyValuePair<Integer, String>> result5 = tree.rangeQuery(50, 50);
        assert result5.size() == 1 : "Range query [50, 50] should return 1 element";
        assert result5.get(0).getKey() == 50 : "The only key in range [50, 50] should be 50";

        System.out.println("Range queries test passed!");
    }

    /**
     * Tests deletion operations
     * <p>
     * Verifies that:
     * - Elements can be deleted correctly
     * - Search operations don't find deleted elements
     * - The tree structure remains valid after deletions
     * - Handles underflow conditions appropriately
     */
    private static void testDeletion() {
        System.out.println("\nTesting deletion operations...");

        // Create a B+ tree with order 4
        BPlusTree<Integer, String> tree = new BPlusTree<>(4);

        // Insert elements 10, 20, 30, ... 100
        for (int i = 1; i <= 10; i++) {
            tree.insert(i * 10, "Value" + (i * 10));
        }

        System.out.println("Tree structure before deletions:");
        System.out.println(tree);

        // Delete some keys
        assert tree.delete(20) : "Deletion of key 20 should return true";
        assert tree.delete(50) : "Deletion of key 50 should return true";
        assert tree.delete(80) : "Deletion of key 80 should return true";

        // Verify keys were deleted
        assert tree.search(20) == null : "Key 20 should no longer exist";
        assert tree.search(50) == null : "Key 50 should no longer exist";
        assert tree.search(80) == null : "Key 80 should no longer exist";

        // Verify size decreased
        assert tree.size() == 7 : "Tree size should be 7 after deletions";

        // Verify remaining keys are still present
        assert tree.search(10).equals("Value10") : "Key 10 should still exist";
        assert tree.search(30).equals("Value30") : "Key 30 should still exist";
        assert tree.search(40).equals("Value40") : "Key 40 should still exist";
        assert tree.search(60).equals("Value60") : "Key 60 should still exist";
        assert tree.search(70).equals("Value70") : "Key 70 should still exist";
        assert tree.search(90).equals("Value90") : "Key 90 should still exist";
        assert tree.search(100).equals("Value100") : "Key 100 should still exist";

        // Verify keys are in sorted order
        List<Integer> allKeys = tree.getAllKeys();
        for (int i = 0; i < allKeys.size() - 1; i++) {
            assert allKeys.get(i) < allKeys.get(i + 1) : "Keys should remain in ascending order";
        }

        System.out.println("Tree structure after deletions:");
        System.out.println(tree);

        // Delete non-existent key
        assert !tree.delete(25) : "Deletion of non-existent key should return false";

        // Delete all remaining keys
        for (int key : new int[]{10, 30, 40, 60, 70, 90, 100}) {
            assert tree.delete(key) : "Deletion of key " + key + " should return true";
        }

        // Verify tree is now empty
        assert tree.isEmpty() : "Tree should be empty after deleting all keys";
        assert tree.size() == 0 : "Tree size should be 0 after deleting all keys";

        System.out.println("Deletion operations test passed!");
    }

    /**
     * Tests the B+ tree with a large dataset to evaluate performance
     * <p>
     * This test:
     * - Inserts a large number of elements
     * - Verifies structure and searchability
     * - Performs range queries
     * - Deletes random elements
     */
    private static void testLargeDataset() {
        System.out.println("\nTesting with large dataset...");

        // Create a B+ tree with order 16 (higher order is better for large datasets)
        BPlusTree<Integer, String> tree = new BPlusTree<>(16);

        // Number of elements to test
        int numElements = 1000;

        // Insert elements
        long startTime = System.nanoTime();
        for (int i = 1; i <= numElements; i++) {
            tree.insert(i, "Value" + i);
        }
        long endTime = System.nanoTime();
        double insertionTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds

        System.out.println("Inserted " + numElements + " elements in " + insertionTime + " ms");

        // Verify tree height (should be logarithmic)
        int height = tree.getHeight();
        System.out.println("Tree height with " + numElements + " elements: " + height);
        assert height <= 5 : "Tree height should be logarithmic with the number of elements";

        // Test random searches
        startTime = System.nanoTime();
        Random random = new Random(42);
        for (int i = 0; i < 100; i++) {
            int keyToFind = random.nextInt(numElements) + 1;
            assert tree.search(keyToFind).equals("Value" + keyToFind) :
                    "Tree should contain key " + keyToFind;
        }
        endTime = System.nanoTime();
        double searchTime = (endTime - startTime) / 1_000_000.0;

        System.out.println("Performed 100 random searches in " + searchTime + " ms");

        // Test range query
        startTime = System.nanoTime();
        List<BPlusTree.KeyValuePair<Integer, String>> rangeResult =
                tree.rangeQuery(400, 600);
        endTime = System.nanoTime();
        double rangeQueryTime = (endTime - startTime) / 1_000_000.0;

        assert rangeResult.size() == 201 : "Range query [400, 600] should return 201 elements";
        System.out.println("Range query for 201 elements completed in " + rangeQueryTime + " ms");

        // Test random deletions
        startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            int keyToDelete = random.nextInt(numElements) + 1;
            tree.delete(keyToDelete);
        }
        endTime = System.nanoTime();
        double deletionTime = (endTime - startTime) / 1_000_000.0;

        System.out.println("Deleted 100 random elements in " + deletionTime + " ms");

        // Verify tree is still valid
        assert tree.isValid() : "Tree should remain valid after random deletions";

        System.out.println("Large dataset test passed!");
    }
}