package org.qinlinj.algoframework._500_data_structure_design._520_advanced_array_and_hash._521_constant_time_array_operations;

import java.util.*;

/**
 * _513_a_RandomizedSet
 * <p>
 * Data Structure Design for O(1) Operations with Random Access
 * <p>
 * Key concepts:
 * 1. This class implements a data structure that supports three O(1) operations:
 * - insert(val): Insert an element if it doesn't exist
 * - remove(val): Remove an element if it exists
 * - getRandom(): Return a random element with equal probability
 * <p>
 * 2. Implementation approach:
 * - An ArrayList to store elements (for O(1) random access)
 * - A HashMap to map values to their indices in the ArrayList (for O(1) lookup)
 * - For removal, swap the element to be removed with the last element in the ArrayList
 * and then remove the last element (now O(1) instead of O(n))
 * <p>
 * 3. The key insight is combining the strengths of different data structures:
 * - Arrays offer O(1) random access but O(n) removal
 * - HashMaps offer O(1) insertion/lookup/removal but can't do random selection
 * - By combining them, we achieve O(1) for all required operations
 */
public class _521_a_RandomizedSet {
    // Store the actual elements
    private List<Integer> nums;
    // Map each element to its index in the nums list
    private Map<Integer, Integer> valToIndex;
    private Random rand;

    public _521_a_RandomizedSet() {
        nums = new ArrayList<>();
        valToIndex = new HashMap<>();
        rand = new Random();
    }

    public static void main(String[] args) {
        _521_a_RandomizedSet randomizedSet = new _521_a_RandomizedSet();
        System.out.println(randomizedSet.insert(1)); // true
        System.out.println(randomizedSet.remove(2)); // false
        System.out.println(randomizedSet.insert(2)); // true
        System.out.println(randomizedSet.getRandom()); // 1 or 2 randomly
        System.out.println(randomizedSet.remove(1)); // true
        System.out.println(randomizedSet.insert(2)); // false
        System.out.println(randomizedSet.getRandom()); // always 2
    }

    /**
     * Inserts an element into the set if not present
     *
     * @param val value to insert
     * @return true if the element was not present, false otherwise
     */
    public boolean insert(int val) {
        // If val already exists, return false
        if (valToIndex.containsKey(val)) {
            return false;
        }

        // Add val to the end of the array and record its index
        valToIndex.put(val, nums.size());
        nums.add(val);
        return true;
    }

    /**
     * Removes an element from the set if present
     *
     * @param val value to remove
     * @return true if the element was present, false otherwise
     */
    public boolean remove(int val) {
        // If val doesn't exist, return false
        if (!valToIndex.containsKey(val)) {
            return false;
        }

        // Get index of val to be removed
        int index = valToIndex.get(val);
        // Get the last element
        int lastElement = nums.get(nums.size() - 1);

        // Update the index of the last element to the position of removed element
        valToIndex.put(lastElement, index);

        // Swap the element to be removed with the last element
        nums.set(index, lastElement);

        // Remove the last element (which is now the element we wanted to remove)
        nums.remove(nums.size() - 1);

        // Remove val from the mapping
        valToIndex.remove(val);

        return true;
    }

    /**
     * Returns a random element from the set with equal probability
     *
     * @return a random element
     */
    public int getRandom() {
        // Pick a random index and return the corresponding element
        return nums.get(rand.nextInt(nums.size()));
    }
}