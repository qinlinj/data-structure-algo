package org.qinlinj.linear.algo.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Shell Sort Implementation
 * <p>
 * Algorithm Principle:
 * Shell sort is a generalization of insertion sort that allows the exchange of items that are far apart.
 * The algorithm sorts elements at specific intervals (gaps), gradually reducing these intervals until
 * the gap becomes 1, at which point it becomes a standard insertion sort, but with mostly pre-sorted data.
 * <p>
 * The algorithm works as follows:
 * 1. Start with a large gap value
 * 2. Perform insertion sort on elements at gap distance
 * 3. Reduce the gap and repeat until gap = 1
 * 4. When gap = 1, perform standard insertion sort
 * <p>
 * Time Complexity:
 * - Best case: O(n log n)
 * - Average case: Depends on gap sequence, typically O(n^(4/3)) to O(n^(3/2))
 * - Worst case: O(n^2) for worst gap sequences, O(n^(3/2)) for Knuth sequence
 * <p>
 * Space Complexity:
 * - O(1) - sorts in-place with only a few extra variables regardless of input size
 * <p>
 * Advantages:
 * - Much faster than insertion sort for large arrays
 * - In-place sorting algorithm (doesn't require extra memory)
 * - Adaptive: performs better with partially sorted arrays
 * - Doesn't require as many comparisons as other O(n^2) algorithms
 * <p>
 * Limitations:
 * - Not stable (may change the relative order of equal elements)
 * - Performance heavily depends on the gap sequence chosen
 * - More complex to implement than some other simple sorts
 */
public class ShellSorter extends Sorter {
    /**
     * Main method to demonstrate Shell sort
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Using a simple example array for demonstration
        int[] data = new int[]{8, 3, 5, 1, 9, 6, 0, 7, 4, 2};
        System.out.println("Original array: " + Arrays.toString(data));
        new ShellSorter().sort(data);
        System.out.println("Sorted array: " + Arrays.toString(data));
    }

    /**
     * Implementation of Shell sort algorithm using move-based approach with detailed execution trace
     * <p>
     * We'll trace through the sorting of this example array: [8, 3, 5, 1, 9, 6, 0, 7, 4, 2]
     *
     * @param data array to be sorted
     */
    public void sort(int[] data) {
        int h = 1;                  // Initial gap value is 1
        int n = data.length;        // n = 10 for our example

        // Calculate the largest gap value using Knuth sequence (3h + 1)
        while (h < n / 3) {         // 1 < 10/3 ? Yes, so enter loop
            h = h * 3 + 1;          // h = 1*3 + 1 = 4
        }                           // 4 < 10/3 ? No, so exit loop. Final h = 4

        // At this point, our gap sequence will be: 4, 1

        // Perform h-sort for decreasing values of h
        while (h >= 1) {            // First iteration: h = 4

            // For h = 4, we perform insertion sort on 4 sublists:
            // [8, x, x, x, 9, x, x, x, 4, x]
            // [x, 3, x, x, x, 6, x, x, x, 2]
            // [x, x, 5, x, x, x, 0, x, x, x]
            // [x, x, x, 1, x, x, x, 7, x, x]

            for (int i = h; i < n; i++) {  // For h=4, i starts at 4 and goes to 9

                // First pass (i=4):
                // tmp = data[4] = 9
                // Check if 9 < 8: No, so no movement needed
                // Array unchanged: [8, 3, 5, 1, 9, 6, 0, 7, 4, 2]

                // Second pass (i=5):
                // tmp = data[5] = 6
                // Check if 6 < 3: No, so no movement needed
                // Array unchanged: [8, 3, 5, 1, 9, 6, 0, 7, 4, 2]

                // Third pass (i=6):
                // tmp = data[6] = 0
                // Check if 0 < 5: Yes, so shift 5 right
                // Array becomes: [8, 3, 5, 1, 9, 6, 5, 7, 4, 2]
                // j = 2, place 0 at position 2
                // Array becomes: [8, 3, 0, 1, 9, 6, 5, 7, 4, 2]

                // Fourth pass (i=7):
                // tmp = data[7] = 7
                // Check if 7 < 1: No, so no movement needed
                // Array unchanged: [8, 3, 0, 1, 9, 6, 5, 7, 4, 2]

                // Fifth pass (i=8):
                // tmp = data[8] = 4
                // Check if 4 < 9: Yes, so shift 9 right
                // Array becomes: [8, 3, 0, 1, 9, 6, 5, 7, 9, 2]
                // j = 4, place 4 at position 4
                // Array becomes: [8, 3, 0, 1, 4, 6, 5, 7, 9, 2]

                // Sixth pass (i=9):
                // tmp = data[9] = 2
                // Check if 2 < 6: Yes, so shift 6 right
                // Array becomes: [8, 3, 0, 1, 4, 6, 5, 7, 9, 6]
                // Check if 2 < 3: Yes, so shift 3 right
                // Array becomes: [8, 3, 0, 1, 4, 3, 5, 7, 9, 6]
                // j = 1, place 2 at position 1
                // Array becomes: [8, 2, 0, 1, 4, 3, 5, 7, 9, 6]

                // After h=4 pass, array is: [8, 2, 0, 1, 4, 3, 5, 7, 9, 6]
                int tmp = data[i];
                int j;

                for (j = i; j >= h && data[j - h] > tmp; j -= h) {
                    data[j] = data[j - h];
                }

                data[j] = tmp;
            }

            // Decrease the gap: h = 4/3 = 1
            h = h / 3;

            // Second iteration of outer loop: h = 1 (standard insertion sort)
            // Detailed execution not shown for brevity, but will perform standard
            // insertion sort on the partially sorted array: [8, 2, 0, 1, 4, 3, 5, 7, 9, 6]

            // Final sorted array: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        }
    }

    /**
     * Alternative implementation of Shell sort using swap-based approach
     * <p>
     * Tracing with the same example array: [8, 3, 5, 1, 9, 6, 0, 7, 4, 2]
     *
     * @param data array to be sorted
     */
    public void sort_swap(int[] data) {
        if (data == null || data.length <= 1) return;

        int n = data.length;  // n = 10

        // Calculate gap sequence and store in reverse order
        ArrayList<Integer> list = new ArrayList<>();
        int k = 1;
        int h;

        // Gap sequence calculation:
        // k=1: h = (3^1-1)/2 = 1, add to list
        // k=2: h = (3^2-1)/2 = 4, add to list
        // k=3: h = (3^3-1)/2 = 13, > 10/3, so break
        // list = [1, 4], which we'll use in reverse: [4, 1]
        do {
            h = ((int) Math.pow(3, k) - 1) / 2;

            if (h > n / 3 && n >= 3) break;
            list.add(h);
            k++;
        } while (h <= n / 3);

        // Sort using each gap in decreasing order
        for (k = list.size() - 1; k >= 0; k--) {
            h = list.get(k);  // First h = 4

            // First gap (h=4) detailed execution:
            // i=4: Compare data[4]=9 with data[0]=8
            //      9 > 8, no swap
            // i=5: Compare data[5]=6 with data[1]=3
            //      6 > 3, no swap
            // i=6: Compare data[6]=0 with data[2]=5
            //      0 < 5, swap: [8, 3, 0, 1, 9, 6, 5, 7, 4, 2]
            // i=7: Compare data[7]=7 with data[3]=1
            //      7 > 1, no swap
            // i=8: Compare data[8]=4 with data[4]=9
            //      4 < 9, swap: [8, 3, 0, 1, 4, 6, 5, 7, 9, 2]
            // i=9: Compare data[9]=2 with data[5]=6
            //      2 < 6, swap: [8, 3, 0, 1, 4, 2, 5, 7, 9, 6]
            //      Compare data[5]=2 with data[1]=3
            //      2 < 3, swap: [8, 2, 0, 1, 4, 3, 5, 7, 9, 6]

            // After h=4 pass, array is: [8, 2, 0, 1, 4, 3, 5, 7, 9, 6]

            // Second gap (h=1) will perform standard insertion sort (not detailed)
            // Final sorted array: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j = j - h) {
                    if (data[j] < data[j - h]) {
                        swap(data, j, j - h);
                    } else {
                        break;
                    }
                }
            }
        }
    }
}