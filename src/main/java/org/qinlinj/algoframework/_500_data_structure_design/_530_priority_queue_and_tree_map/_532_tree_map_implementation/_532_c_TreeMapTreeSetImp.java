package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._532_tree_map_implementation;

/**
 * Example Usage of TreeMap and TreeSet
 * <p>
 * This class demonstrates how to use the TreeMap and TreeSet implementations.
 * It shows common operations and special features like rank and select that
 * differentiate these implementations from standard Java collections.
 * <p>
 * Example highlights:
 * 1. Basic operations (put, get, containsKey, remove)
 * 2. Navigation operations (min, max, floor, ceiling)
 * 3. Order statistics (rank and select)
 * 4. Range queries
 * <p>
 * Learning outcomes:
 * - Understand the difference between TreeMap and TreeSet
 * - Learn how to use BST navigation methods
 * - See practical applications of order statistics in BSTs
 */

public class _532_c_TreeMapTreeSetImp {

    public static void main(String[] args) {
        // Demonstrate TreeMap usage
        treeMapExample();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Demonstrate TreeSet usage
        treeSetExample();
    }

    private static void treeMapExample() {
        System.out.println("TreeMap Example:");

        // Create a new TreeMap instance
        _532_a_TreeMap<Integer, String> map = new _532_a_TreeMap<>();

        // Add key-value pairs
        map.put(5, "Five");
        map.put(3, "Three");
        map.put(7, "Seven");
        map.put(2, "Two");
        map.put(4, "Four");
        map.put(6, "Six");
        map.put(8, "Eight");

        // Basic operations
        System.out.println("Map size: " + map.size());
        System.out.println("Value for key 4: " + map.get(4));
        System.out.println("Contains key 9? " + map.containsKey(9));

        // Navigation operations
        System.out.println("Minimum key: " + map.minKey());
        System.out.println("Maximum key: " + map.maxKey());
        System.out.println("Floor key of 4.5: " + map.floorKey(4));  // 4
        System.out.println("Ceiling key of 4.5: " + map.ceilingKey(5));  // 5

        // Order statistics
        System.out.println("Rank of 6: " + map.rank(6));  // Number of keys less than 6
        System.out.println("Select index 3: " + map.select(3));  // 4th smallest key (0-indexed)

        // Iterate through all keys in sorted order
        System.out.print("All keys in order: ");
        for (Integer key : map.keys()) {
            System.out.print(key + " ");
        }
        System.out.println();

        // Range query
        System.out.print("Keys between 3 and 7: ");
        for (Integer key : map.keys(3, 7)) {
            System.out.print(key + " ");
        }
        System.out.println();

        // Remove operations
        System.out.println("Removing key 4, value: " + map.remove(4));
        System.out.println("Map size after removal: " + map.size());

        System.out.println("Removing minimum key...");
        map.removeMin();
        System.out.print("Keys after removeMin: ");
        for (Integer key : map.keys()) {
            System.out.print(key + " ");
        }
        System.out.println();
    }

    private static void treeSetExample() {
        System.out.println("TreeSet Example:");

        // Create a new TreeSet instance
        _532_b_TreeSet<Character> set = new _532_b_TreeSet<>();

        // Add elements
        set.add('E');
        set.add('A');
        set.add('G');
        set.add('B');
        set.add('D');
        set.add('F');
        set.add('C');

        // Basic operations
        System.out.println("Set size: " + set.size());
        System.out.println("Contains 'D'? " + set.contains('D'));
        System.out.println("Contains 'Z'? " + set.contains('Z'));

        // Navigation operations
        System.out.println("Minimum element: " + set.minKey());
        System.out.println("Maximum element: " + set.maxKey());
        System.out.println("Floor of 'D': " + set.floorKey('D'));
        System.out.println("Ceiling of 'D': " + set.ceilingKey('D'));

        // Order statistics
        System.out.println("Rank of 'E': " + set.rank('E'));  // Number of elements less than 'E'
        System.out.println("Select index 2: " + set.select(2));  // 3rd smallest element (0-indexed)

        // Iterate through all elements in sorted order
        System.out.print("All elements in order: ");
        for (Character c : set.keys()) {
            System.out.print(c + " ");
        }
        System.out.println();

        // Range query
        System.out.print("Elements between 'B' and 'F': ");
        for (Character c : set.keys('B', 'F')) {
            System.out.print(c + " ");
        }
        System.out.println();

        // Remove operations
        System.out.println("Removing element 'D'...");
        set.remove('D');
        System.out.print("Elements after removal: ");
        for (Character c : set.keys()) {
            System.out.print(c + " ");
        }
        System.out.println();

        System.out.println("Removing maximum element...");
        set.removeMax();
        System.out.print("Elements after removeMax: ");
        for (Character c : set.keys()) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}