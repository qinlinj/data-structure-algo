package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

/**
 * Counting Sort Algorithm Principle:
 * <p>
 * Counting sort is a non-comparison based sorting algorithm with O(n+k) time complexity,
 * where n is the number of elements and k is the range of input values (max - min + 1).
 * <p>
 * The algorithm works as follows:
 * 1. Find the minimum and maximum values in the input array to determine the range.
 * 2. Create a count array of size (max - min + 1) to store the frequency of each element.
 * 3. Count the occurrences of each element in the input array.
 * 4. Modify the count array to store the cumulative sum, which gives the correct position
 * of each element in the sorted output.
 * 5. Build the sorted output array by placing each element at its correct position based
 * on the count array.
 * 6. Copy the sorted output back to the original array.
 * <p>
 * Counting sort is efficient when the range of input values (k) is not significantly larger
 * than the number of elements (n). It is particularly useful for sorting integers or data
 * that can be mapped to integers within a limited range.
 * <p>
 * Advantages:
 * - Linear time complexity O(n+k) when k is O(n)
 * - Stable sorting algorithm (preserves relative order of equal elements)
 * <p>
 * Limitations:
 * - Requires additional O(n+k) space
 * - Not efficient when the range of values is large compared to the number of elements
 * - Works best with non-negative integers, but can be adapted for other data types
 */
public class CountingSorter extends Sorter {
    public static void main(String[] args) {
        int[] data = new int[]{-22, 2, 3, 6, 1, 34, 11, 53, -1, 6, 22, 12};
        CountingSorter sorter = new CountingSorter();
        sorter.sort(data);
        System.out.println(Arrays.toString(data));
    }

    /**
     * Sort an array using the counting sort algorithm
     * <p>
     * Example: Initial array [-22, 2, 3, 6, 1, 34, 11, 53, -1, 6, 22, 12]
     *
     * @param data the array to be sorted
     */
    public void sort(int[] data) {
        // Return early if array is null or empty
        if (data == null || data.length == 0) {
            return;
        }

        // Find the minimum and maximum values in the array
        // This helps handle negative numbers and optimize the count array size
        int max = data[0];
        int min = data[0];
        for (int i = 1; i < data.length; i++) {
            max = Math.max(max, data[i]);
            min = Math.min(min, data[i]);
        }
        // Example: min = -22, max = 53
        // Range = max - min + 1 = 53 - (-22) + 1 = 76

        // Create a count array to store the frequency of each element
        // Size is (max - min + 1) to accommodate all possible values between min and max
        int[] countArray = new int[max - min + 1];

        // Count the occurrences of each element in the input array
        for (int datum : data) {
            countArray[datum - min]++;
        }
        // Example: After this loop, countArray contains:
        // indices: 0    1  2  ... 21   22   23   24   25  ... 28   ... 56   ... 75
        // values:  1    0  0  ...  1    0    1    1    0  ...  2   ...  1   ...  1
        // meaning:
        // countArray[0] = 1: There is 1 occurrence of value -22 (0 + min)
        // countArray[21] = 1: There is 1 occurrence of value -1 (21 + min)
        // countArray[23] = 1: There is 1 occurrence of value 1 (23 + min)
        // countArray[24] = 1: There is 1 occurrence of value 2 (24 + min)
        // countArray[28] = 2: There are 2 occurrences of value 6 (28 + min)
        // countArray[56] = 1: There is 1 occurrence of value 34 (56 + min)
        // countArray[75] = 1: There is 1 occurrence of value 53 (75 + min)

        // Calculate the cumulative sum in the count array
        // This will give the correct position of each element in the sorted array
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }
        // Example: After this loop, countArray becomes:
        // indices: 0    1    2  ... 21   22   23   24   25  ... 28   ... 56   ... 75
        // values:  1    1    1  ...  2    2    3    4    4  ...  6   ... 11   ... 12
        // This means:
        // countArray[0] = 1: Elements <= -22 should be at positions up to 0
        // countArray[21] = 2: Elements <= -1 should be at positions up to 1
        // countArray[23] = 3: Elements <= 1 should be at positions up to 2
        // countArray[24] = 4: Elements <= 2 should be at positions up to 3
        // countArray[28] = 6: Elements <= 6 should be at positions up to 5
        // countArray[56] = 11: Elements <= 34 should be at positions up to 10
        // countArray[75] = 12: Elements <= 53 should be at positions up to 11

        // Create a temporary array to store the sorted result
        int tmp[] = new int[data.length];

        // Build the sorted array by placing each element at its correct position
        // We process the original array from right to left to maintain stability
        // (i.e., elements with the same value maintain their relative order)

        // Alternative implementation (commented out):
//        for (int i = data.length - 1; i >= 0; i--) { // O(n)
//            int j = data[i];
//            int k = countArray[j - min] - 1;
//            tmp[k] = data[i];
//            countArray[j - min]--;
//        }

        // Iterate through the original array from right to left
        for (int j = data.length - 1; j >= 0; j--) {
            // Get the correct position for the current element
            // Decrement the count after using it (--countArray[...])
            tmp[--countArray[data[j] - min]] = data[j];

            // Example step-by-step processing:
            // j=11: data[11]=12, mapped index=34, countArray[34]=8, tmp[7]=12, countArray[34] becomes 7
            // j=10: data[10]=22, mapped index=44, countArray[44]=9, tmp[8]=22, countArray[44] becomes 8
            // j=9: data[9]=6, mapped index=28, countArray[28]=6, tmp[5]=6, countArray[28] becomes 5
            // j=8: data[8]=-1, mapped index=21, countArray[21]=2, tmp[1]=-1, countArray[21] becomes 1
            // j=7: data[7]=53, mapped index=75, countArray[75]=12, tmp[11]=53, countArray[75] becomes 11
            // j=6: data[6]=11, mapped index=33, countArray[33]=7, tmp[6]=11, countArray[33] becomes 6
            // j=5: data[5]=34, mapped index=56, countArray[56]=11, tmp[10]=34, countArray[56] becomes 10
            // j=4: data[4]=1, mapped index=23, countArray[23]=3, tmp[2]=1, countArray[23] becomes 2
            // j=3: data[3]=6, mapped index=28, countArray[28]=5, tmp[4]=6, countArray[28] becomes 4
            // j=2: data[2]=3, mapped index=25, countArray[25]=4, tmp[3]=3, countArray[25] becomes 3
            // j=1: data[1]=2, mapped index=24, countArray[24]=4, tmp[3]=2, countArray[24] becomes 3
            // j=0: data[0]=-22, mapped index=0, countArray[0]=1, tmp[0]=-22, countArray[0] becomes 0

            // Final tmp array: [-22, -1, 1, 2, 3, 6, 6, 11, 12, 22, 34, 53]
        }

        // Copy the sorted elements from the temporary array back to the original array
        System.arraycopy(tmp, 0, data, 0, data.length);

        // Final sorted array: [-22, -1, 1, 2, 3, 6, 6, 11, 12, 22, 34, 53]
    }
}