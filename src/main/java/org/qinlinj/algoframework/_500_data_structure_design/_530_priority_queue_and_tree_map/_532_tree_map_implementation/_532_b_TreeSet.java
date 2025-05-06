package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._532_tree_map_implementation; /**
 * TreeSet Implementation
 * <p>
 * This class implements a TreeSet data structure, which is a Set implementation
 * that uses a TreeMap internally. All elements in a TreeSet are maintained in
 * sorted order.
 * <p>
 * Key features:
 * 1. Ordered Set: Maintains elements in sorted order
 * 2. No Duplicates: Each element can appear only once in the set
 * 3. BST Operations: Inherits all BST navigation operations from TreeMap
 * 4. Order Statistics: Supports rank() and select() methods from TreeMap
 * <p>
 * Implementation Details:
 * - The TreeSet is essentially a thin wrapper around TreeMap
 * - Elements of the set are stored as keys in the internal TreeMap
 * - A placeholder object is used as the value for all keys in the map
 * - All major operations are delegated to the underlying TreeMap
 * <p>
 * Relationship to Other Collections:
 * - TreeSet relates to TreeMap just as HashSet relates to HashMap
 * - It's a specialized version of Set that maintains ordering
 */

public class _532_b_TreeSet<K extends Comparable<K>> {

    // A placeholder object used as the value for all keys in the map
    private static final Object PRESENT = new Object();
    // TreeSet internally uses TreeMap, with set elements as keys
    private _532_a_TreeMap<K, Object> map;

    /**
     * Creates a new empty TreeSet
     */
    public _532_b_TreeSet() {
        map = new _532_a_TreeMap<>();
    }

    /**
     * Adds an element to the set
     */
    public void add(K key) {
        map.put(key, PRESENT);
    }

    /**
     * Removes an element from the set
     */
    public void remove(K key) {
        map.remove(key);
    }

    /**
     * Checks if the set contains the given element
     */
    public boolean contains(K key) {
        return map.containsKey(key);
    }

    /**
     * Returns the number of elements in the set
     */
    public int size() {
        return map.size();
    }

    /**
     * Checks if the set is empty
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Returns all elements in the set in sorted order
     */
    public Iterable<K> keys() {
        return map.keys();
    }

    /**
     * Returns all elements in the range [min, max] in sorted order
     */
    public Iterable<K> keys(K min, K max) {
        return map.keys(min, max);
    }

    /**
     * Returns the smallest element in the set
     */
    public K minKey() {
        return map.minKey();
    }

    /**
     * Returns the largest element in the set
     */
    public K maxKey() {
        return map.maxKey();
    }

    /**
     * Returns the largest element less than or equal to the given element
     */
    public K floorKey(K key) {
        return map.floorKey(key);
    }

    /**
     * Returns the smallest element greater than or equal to the given element
     */
    public K ceilingKey(K key) {
        return map.ceilingKey(key);
    }

    /**
     * Returns the element at the given index in the sorted order (0-based)
     */
    public K select(int i) {
        return map.select(i);
    }

    /**
     * Returns the number of elements less than the given element
     */
    public int rank(K key) {
        return map.rank(key);
    }

    /**
     * Removes the smallest element from the set
     */
    public void removeMin() {
        map.removeMin();
    }

    /**
     * Removes the largest element from the set
     */
    public void removeMax() {
        map.removeMax();
    }
}