package org.qinlinj.practical.multlinemerging;

import java.util.*;

/**
 * K Sorted Array Merger
 * <p>
 * Concept and Principles:
 * This class implements an algorithm to merge K sorted arrays into a single sorted array.
 * Unlike merging just two arrays, which can be done with a linear approach using two pointers,
 * merging K arrays efficiently requires a min-heap (priority queue) to continuously find the
 * smallest element among the K arrays.
 * <p>
 * Advantages:
 * 1. Time Complexity: O(N log K) where N is the total number of elements across all arrays
 * and K is the number of arrays
 * 2. More efficient than repeatedly merging two arrays (which would be O(N log N))
 * 3. Only needs to keep K elements in memory at once (in the heap)
 * 4. Stable merge - preserves the order of equal elements
 * <p>
 * Applications:
 * 1. External sorting (when data doesn't fit in memory)
 * 2. Merging results from distributed computing tasks
 * 3. Database operations with multiple sorted sources
 * 4. Implementing efficient K-way merge sort
 * <p>
 * Visual Example:
 * For inputs:
 * a = [1, 2, 6, 9, 10]
 * b = [2, 3, 7, 11]
 * c = [4, 8, 9, 13]
 * d = [5]
 * <p>
 * Initial min-heap: [1(a,0), 2(b,0), 4(c,0), 5(d,0)]
 * where each entry is [value, array_id, index_in_array]
 * <p>
 * Step 1: Poll 1(a,0) -> result = [1]
 * Add 2(a,1) -> heap = [2(a,1), 2(b,0), 4(c,0), 5(d,0)]
 * <p>
 * Step 2: Poll 2(a,1) -> result = [1, 2]
 * Add 6(a,2) -> heap = [2(b,0), 4(c,0), 5(d,0), 6(a,2)]
 * <p>
 * Step 3: Poll 2(b,0) -> result = [1, 2, 2]
 * Add 3(b,1) -> heap = [3(b,1), 4(c,0), 5(d,0), 6(a,2)]
 * <p>
 * ... and so on until the heap is empty.
 * <p>
 * Final result: [1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 11, 13]
 */
public class KSortedArrayMerger {
    public static void main(String[] args) {
        int[] a = {1, 2, 6, 9, 10};
        int[] b = {2, 3, 7, 11};
        int[] c = {4, 8, 9, 13};
        int[] d = {5};

        List<int[]> data = new ArrayList<>();
        data.add(a);
        data.add(b);
        data.add(c);
        data.add(d);

        int[] res = new KSortedArrayMerger().mergeKSortedArray(data, 4);
        System.out.println(Arrays.toString(res));
    }


    /**
     * Merges K sorted arrays into a single sorted array.
     * <p>
     * Algorithm:
     * 1. Calculate the total length of the result array
     * 2. Initialize a min-heap (priority queue) to track the smallest element
     * 3. Initially add the first element from each array to the heap
     * 4. Repeatedly extract the smallest element from the heap and add the next
     * element from the same array (if available)
     * 5. Continue until the heap is empty
     * <p>
     * Time Complexity: O(N log K) where:
     * - N is the total number of elements across all arrays
     * - K is the number of arrays
     * - Each heap operation takes O(log K) time
     * - We perform N heap operations (one for each element)
     * <p>
     * Space Complexity: O(K) for the heap + O(N) for the result array
     *
     * @param data List containing the K sorted arrays to merge
     * @param k    The number of arrays to merge
     * @return A new array containing all elements from the input arrays in sorted order
     */
    public int[] mergeKSortedArray(List<int[]> data, int k) {
        int len = 0;
        for (int i = 0; i < k; i++) {
            len += data.get(i).length;
        }

        int[] res = new int[len];

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });

        for (int i = 0; i < k; i++) {
            if (data.get(i).length == 0) continue;
            minHeap.add(new int[]{data.get(i)[0], i, 0});
        }

        int index = 0;
        while (!minHeap.isEmpty()) {
            int[] record = minHeap.poll();
            int value = record[0];
            int whichArray = record[1];
            int valueIndex = record[2];

            res[index++] = value;

            valueIndex++;

            if (valueIndex == data.get(whichArray).length) continue;

            minHeap.add(new int[]{data.get(whichArray)[valueIndex], whichArray, valueIndex});
        }
        return res;
    }
}