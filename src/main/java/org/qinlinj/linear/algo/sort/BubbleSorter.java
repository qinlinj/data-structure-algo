package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

/**
 * BubbleSorter class implements the bubble sort algorithm
 * Bubble sort repeatedly compares adjacent elements and swaps them if they are in the wrong order
 */
public class BubbleSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42};
        new BubbleSorter().sort(data);
        System.out.println(Arrays.toString(data));
    }

    /**
     * Sort an array using the bubble sort algorithm
     * <p>
     * Example: Initial array [12, 23, 36, 9, 24, 42]
     *
     * @param data the array to be sorted
     */
    public void sort(int[] data) {
        // Return early if array is null or has 0 or 1 element (already sorted)
        if (data == null || data.length <= 1) {
            return;
        }

        // Flag to check if any swaps were made in a pass
        boolean sortFlag = false;

        // Outer loop: controls the number of passes through the array
        // We need at most n passes for an array of size n
        for (int i = 0; i < data.length; i++) {
            // Reset sortFlag at the beginning of each pass
            sortFlag = false;

            // Inner loop: compare adjacent elements and swap if needed
            // In each pass, the largest remaining element "bubbles up" to its correct position
            // After i passes, the i largest elements are already in place, so we only need to check up to (length-i-1)
            for (int j = 0; j < data.length - i - 1; j++) {
                // If current element is greater than the next element, swap them
                if (data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                    sortFlag = true;  // Set flag to indicate a swap was made
                }

                // Example state during first pass (i=0):
                // j=0: Compare 12 and 23 -> No swap -> [12, 23, 36, 9, 24, 42]
                // j=1: Compare 23 and 36 -> No swap -> [12, 23, 36, 9, 24, 42]
                // j=2: Compare 36 and 9 -> Swap -> [12, 23, 9, 36, 24, 42]
                // j=3: Compare 36 and 24 -> Swap -> [12, 23, 9, 24, 36, 42]
                // j=4: Compare 36 and 42 -> No swap -> [12, 23, 9, 24, 36, 42]

                // After first pass: [12, 23, 9, 24, 36, 42] (largest element 42 is in correct position)

                // Example state during second pass (i=1):
                // j=0: Compare 12 and 23 -> No swap -> [12, 23, 9, 24, 36, 42]
                // j=1: Compare 23 and 9 -> Swap -> [12, 9, 23, 24, 36, 42]
                // j=2: Compare 23 and 24 -> No swap -> [12, 9, 23, 24, 36, 42]
                // j=3: Compare 24 and 36 -> No swap -> [12, 9, 23, 24, 36, 42]

                // After second pass: [12, 9, 23, 24, 36, 42] (second-largest element 36 is in correct position)

                // Example continues for remaining passes...
            }

            // If no swaps were made in this pass, the array is already sorted
            // This optimization helps to exit early when the array becomes sorted
            if (!sortFlag) break;

            // Example: After the third pass, array might be [9, 12, 23, 24, 36, 42]
            // Since no swaps were needed, sortFlag remains false, and we exit the loop
        }

        // Final sorted array: [9, 12, 23, 24, 36, 42]
    }
}