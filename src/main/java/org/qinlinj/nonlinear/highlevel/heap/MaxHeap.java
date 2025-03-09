package org.qinlinj.nonlinear.highlevel.heap;

import org.qinlinj.linear.arraylist.ArrayList;

// @formatter:off
/**
 * MaxHeap Implementation
 * ==============================
 *
 * CONCEPT AND PRINCIPLES:
 * A max heap is a complete binary tree where each parent node is greater than or equal to its children.
 * This property is known as the "heap property." The largest element is always at the root (index 0).
 * Unlike binary search trees, heaps are not ordered left-to-right, only parent-child relationships matter.
 *
 * VISUALIZATION OF A MAX HEAP:
 *             100
 *            /   \
 *          19     36
 *         / \    /  \
 *        17  3  25   1
 *       / \
 *      2   7
 *
 * ARRAY REPRESENTATION:
 * [100, 19, 36, 17, 3, 25, 1, 2, 7]
 *   0   1   2   3  4   5  6  7  8
 *
 * For any element at index i:
 * - Left child is at index: 2i + 1
 * - Right child is at index: 2i + 2
 * - Parent is at index: (i-1)/2 (integer division)
 *
 * ADVANTAGES:
 * 1. Efficient access to the maximum element (O(1))
 * 2. Efficient insertion and deletion operations (O(log n))
 * 3. In-place construction possible (O(n))
 * 4. Can be used to implement priority queues
 * 5. Efficient for selection algorithms (e.g., finding kth largest element)
 * 6. Natural implementation of heapsort (O(n log n))
 * 7. Memory-efficient as it can be implemented using a simple array
 *
 * APPLICATIONS:
 * - Priority queues (scheduling, event-driven simulation)
 * - Heapsort algorithm
 * - Graph algorithms (e.g., Dijkstra's, Prim's)
 * - Selection algorithms (finding kth largest/smallest element)
 * - Memory management in some systems
 * - Task scheduling in operating systems
 *
 * @param <E> The type of elements stored in the heap, must be comparable
 */
public class MaxHeap<E extends Comparable<E>> {
    // The underlying data structure to store heap elements
    private ArrayList<E> data;

    /**
     * Constructs a max heap with specified initial capacity.
     *
     * Time Complexity: O(1)
     *
     * @param capacity Initial capacity for the heap
     */
    public MaxHeap(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    /**
     * Constructs an empty max heap with default capacity.
     *
     * Time Complexity: O(1)
     */
    public MaxHeap() {
        this.data = new ArrayList<>();
    }

    /**
     * Constructs a max heap from an array using heapify process.
     * This is more efficient than adding elements one by one.
     *
     * Time Complexity: O(n) - More efficient than adding elements one by one O(n log n)
     *
     * HEAPIFY EXAMPLE:
     * Consider array [3, 10, 5, 17, 12, 1, 9, 6, 14]
     *
     * Initial array:
     *             3
     *            / \
     *          10   5
     *         / \  / \
     *       17  12 1  9
     *      / \
     *     6  14
     *
     * Start from last non-leaf node (index (9-1)/2 = 4) and sift down each node:
     *
     * After sifting down 12 (no change needed as children are smaller):
     *             3
     *            / \
     *          10   5
     *         / \  / \
     *       17  12 1  9
     *      / \
     *     6  14
     *
     * After sifting down 17 (no change needed):
     *             3
     *            / \
     *          10   5
     *         / \  / \
     *       17  12 1  9
     *      / \
     *     6  14
     *
     * After sifting down 5 (no change needed):
     *             3
     *            / \
     *          10   5
     *         / \  / \
     *       17  12 1  9
     *      / \
     *     6  14
     *
     * After sifting down 10 (swap with 17):
     *             3
     *            / \
     *          17   5
     *         / \  / \
     *       10  12 1  9
     *      / \
     *     6  14
     *
     * After sifting down 3 (swap with 17, then with 14):
     *            17
     *           /  \
     *         14    5
     *        / \   / \
     *      10  12 1  9
     *     / \
     *    6   3
     *
     * Final heap: [17, 14, 5, 10, 12, 1, 9, 6, 3]
     *
     * @param arr Array of elements to build the heap from
     */
    public MaxHeap(E[] arr) {
        this.data = new ArrayList<>(arr);
        // Heapify process - start from the last non-leaf node and sift down each node
        for (int i = lastNonLeafIndex(); i >= 0; i--) { // O(n)
            siftDown(i); // O(logn)
        }
    }

    /**
     * Returns the number of elements in the heap.
     *
     * Time Complexity: O(1)
     *
     * @return The current size of the heap
     */
    public int size() {
        return data.getSize();
    }

    /**
     * Checks if the heap is empty.
     *
     * Time Complexity: O(1)
     *
     * @return true if the heap contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Calculates the index of the left child for a node at the given index.
     * For a node at index i, its left child is at 2i + 1.
     *
     * Time Complexity: O(1)
     *
     * EXAMPLE:
     * In the max heap [17, 14, 5, 10, 12, 1, 9, 6, 3],
     * The left child of node at index 1 (value 14) is at index 2*1+1 = 3 (value 10).
     *
     * @param index The index of the parent node
     * @return The index of the left child
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * Calculates the index of the right child for a node at the given index.
     * For a node at index i, its right child is at 2i + 2.
     *
     * Time Complexity: O(1)
     *
     * EXAMPLE:
     * In the max heap [17, 14, 5, 10, 12, 1, 9, 6, 3],
     * The right child of node at index 1 (value 14) is at index 2*1+2 = 4 (value 12).
     *
     * @param index The index of the parent node
     * @return The index of the right child
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * Calculates the index of the parent for a node at the given index.
     * For a node at index i, its parent is at (i-1)/2.
     *
     * Time Complexity: O(1)
     *
     * EXAMPLE:
     * In the max heap [17, 14, 5, 10, 12, 1, 9, 6, 3],
     * The parent of node at index 3 (value 10) is at index (3-1)/2 = 1 (value 14).
     *
     * @param index The index of the child node
     * @return The index of the parent
     * @throws IllegalArgumentException if the index is 0 (root has no parent)
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 does not have parent");
        }
        return (index - 1) / 2;
    }

    /**
     * Returns the index of the last non-leaf node in the heap.
     * This is the parent of the last element in the array.
     *
     * Time Complexity: O(1)
     *
     * EXAMPLE:
     * In a heap with 9 elements [17, 14, 5, 10, 12, 1, 9, 6, 3],
     * The last element is at index 8, so the last non-leaf node is at index parent(8) = (8-1)/2 = 3 (value 10).
     *
     * @return The index of the last non-leaf node
     */
    private int lastNonLeafIndex() {
        int lastLeafIndex = data.getSize() - 1;
        return parent(lastLeafIndex);
    }

    /**
     * Adds a new element to the heap.
     * The element is first added to the end, then sifted up to maintain the heap property.
     *
     * Time Complexity: O(log n)
     *
     * EXAMPLE:
     * Adding 20 to the max heap [17, 14, 5, 10, 12, 1, 9, 6, 3]
     *
     * Step 1: Add 20 to the end
     * [17, 14, 5, 10, 12, 1, 9, 6, 3, 20]
     *
     * Step 2: Sift up from index 9
     * - Compare 20 with its parent at index (9-1)/2 = 4 (value 12)
     * - Since 20 > 12, swap them
     * [17, 14, 5, 10, 20, 1, 9, 6, 3, 12]
     *
     * Step 3: Continue sifting up from index 4
     * - Compare 20 with its parent at index (4-1)/2 = 1 (value 14)
     * - Since 20 > 14, swap them
     * [17, 20, 5, 10, 14, 1, 9, 6, 3, 12]
     *
     * Step 4: Continue sifting up from index 1
     * - Compare 20 with its parent at index (1-1)/2 = 0 (value 17)
     * - Since 20 > 17, swap them
     * [20, 17, 5, 10, 14, 1, 9, 6, 3, 12]
     *
     * Final heap: [20, 17, 5, 10, 14, 1, 9, 6, 3, 12]
     *
     * @param e The element to add to the heap
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * Moves an element up the heap until it's in the correct position.
     * This ensures the max-heap property where parent > children.
     *
     * Time Complexity: O(log n)
     *
     * @param index The index of the element to sift up
     */
    private void siftUp(int index) {
        // Save the element to be sifted up
        E e = data.get(index);

        // Continue until reaching root or finding correct position
        while (index > 0) {
            // Get parent element
            E parentNode = data.get(parent(index));

            // If element is not greater than parent, we've found its position
            if (e.compareTo(parentNode) <= 0) break;

            // Move parent down to current position
            data.set(index, parentNode);

            // Move up to parent position
            index = parent(index);
        }
        // Place element at its final position
        data.set(index, e);
    }

    /**
     * Returns the maximum element in the heap (the root) without removing it.
     *
     * Time Complexity: O(1)
     *
     * @return The maximum element in the heap
     * @throws RuntimeException if the heap is empty
     */
    public E findMax() {
        if (data.getSize() == 0)
            throw new RuntimeException("Heap is Empty");
        return data.getFirst();
    }

    /**
     * Removes and returns the maximum element from the heap.
     * The last element is moved to the root, then sifted down to maintain heap property.
     *
     * Time Complexity: O(log n)
     *
     * EXAMPLE:
     * Removing max from heap [20, 17, 5, 10, 14, 1, 9, 6, 3, 12]
     *
     * Step 1: Save the max element (20) to return later
     *
     * Step 2: Replace root with the last element (12)
     * [12, 17, 5, 10, 14, 1, 9, 6, 3]
     *
     * Step 3: Sift down from root (index 0)
     * - Compare 12 with its children: left=17 (index 1), right=5 (index 2)
     * - Largest child is 17, and 12 < 17, so swap them
     * [17, 12, 5, 10, 14, 1, 9, 6, 3]
     *
     * Step 4: Continue sifting down from index 1
     * - Compare 12 with its children: left=10 (index 3), right=14 (index 4)
     * - Largest child is 14, and 12 < 14, so swap them
     * [17, 14, 5, 10, 12, 1, 9, 6, 3]
     *
     * Step 5: No more swaps needed as 12 > its children
     *
     * Final heap: [17, 14, 5, 10, 12, 1, 9, 6, 3]
     * Return: 20
     *
     * @return The maximum element that was removed
     * @throws RuntimeException if the heap is empty
     */
    public E removeMax() {
        // Get the maximum element (root)
        E max = findMax();
        // Get the last element
        E last = data.getLast();
        // Replace root with last element
        data.set(0, last);

        // Remove the last element (now duplicated at root)
        data.removeLast();

        // If heap isn't empty, restore heap property by sifting down the new root
        if (!data.isEmpty()) siftDown(0);

        return max;
    }

    /**
     * Moves an element down the heap until it reaches its correct position.
     * Used after removing the max element or during heapify.
     *
     * Time Complexity: O(log n)
     *
     * EXAMPLE:
     * Consider sifting down element 3 at index 0 in this broken heap:
     * [3, 17, 5, 10, 14, 1, 9, 6, 12]
     *
     * Step 1: Compare 3 with its children: left=17 (index 1), right=5 (index 2)
     * - Largest child is 17, and 3 < 17, so swap them
     * [17, 3, 5, 10, 14, 1, 9, 6, 12]
     *
     * Step 2: Continue from index 1
     * - Compare 3 with its children: left=10 (index 3), right=14 (index 4)
     * - Largest child is 14, and 3 < 14, so swap them
     * [17, 14, 5, 10, 3, 1, 9, 6, 12]
     *
     * Step 3: Continue from index 4
     * - Compare 3 with its children: left=6 (index 9), right=12 (index 10)
     * - Largest child is 12, and 3 < 12, so swap them
     * [17, 14, 5, 10, 12, 1, 9, 6, 3]
     *
     * Final heap: [17, 14, 5, 10, 12, 1, 9, 6, 3]
     *
     * @param index The index of the element to sift down
     */
    private void siftDown(int index) {
        // Save the element to be sifted down
        E e = data.get(index);
        // Continue until reaching a leaf node
        while (leftChild(index) < data.getSize()) {
            // Initially assume left child is larger
            int maxNodeIndex = leftChild(index);
            // Check if right child exists and is larger than left child
            if (rightChild(index) < data.getSize()) {
                if (data.get(rightChild(index)).compareTo(data.get(leftChild(index))) > 0) {
                    // If right child is larger, update maxNodeIndex
                    maxNodeIndex = rightChild(index);
                }
            }

            // If element is not smaller than largest child, we've found its position
            if (e.compareTo(data.get(maxNodeIndex)) >= 0) break;

            // Move larger child up
            data.set(index, data.get(maxNodeIndex));

            // Move down to larger child's position
            index = maxNodeIndex;
        }
        // Place element at its final position
        data.set(index, e);
    }
}