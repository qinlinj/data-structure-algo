package org.qinlinj.linear.algo.sort;

import java.util.Arrays;

/**
 * Radix Sort Algorithm Principle:
 * <p>
 * Radix sort is a non-comparative integer sorting algorithm that sorts data by processing
 * individual digits, starting from the least significant digit (LSD) to the most significant
 * digit (MSD). It uses counting sort as a subroutine to sort elements based on their digits.
 * <p>
 * The algorithm works as follows:
 * 1. Find the maximum number to determine the number of digits needed
 * 2. For each digit position (ones, tens, hundreds, etc.):
 * a. Group numbers by the current digit
 * b. Sort numbers within each group using counting sort
 * c. Collect numbers in order for the next iteration
 * 3. After processing all digits, the array is sorted
 * <p>
 * This implementation provides two variants:
 * - Basic radix sort for non-negative integers
 * - Extended radix sort for handling negative numbers
 * <p>
 * Time Complexity: O(d * (n + k)) where:
 * - n is the number of elements
 * - k is the range of each digit (10 for decimal)
 * - d is the number of digits in the maximum number
 * <p>
 * Space Complexity: O(n + k)
 * <p>
 * Advantages:
 * - Linear time complexity when the number of digits is constant
 * - Stable sorting algorithm
 * - Works well for fixed-length integer keys
 * <p>
 * Limitations:
 * - Only works for integers or data that can be mapped to integers
 * - Requires additional space
 * - Less efficient for numbers with many digits
 */
public class RadixSorter extends Sorter {
    public static void main(String[] args) {
        // Phone numbers example to showcase radix sort's efficiency
        // Format: 10-digit numbers without country code
        long[] phoneNumbers = new long[]{
                1382675591L,  // 138-267-5591
                1382675540L,  // 138-267-5540
                1382675519L,  // 138-267-5519
                1382675568L,  // 138-267-5568
                1382675527L   // 138-267-5527
        };

        RadixSorter sorter = new RadixSorter();
        sorter.sortPhoneNumbers(phoneNumbers);
        System.out.println(Arrays.toString(phoneNumbers));

        // Regular integer example
        int[] data = new int[]{2, 3, 6, 1, 34, 11, 53, 6, 22, 12};
        sorter.sort(data);
        System.out.println(Arrays.toString(data));
    }

    /**
     * Sorts an array using the radix sort algorithm
     * <p>
     * Example: Initial array [2, 3, 6, 1, 34, 11, 53, 6, 22, 12]
     *
     * @param data the array to be sorted
     */
    public void sort(int[] data) {
        // Return early if array is null or empty
        if (data == null || data.length == 0) {
            return;
        }

        // Find the maximum value to determine the number of digits
        int max = data[0];
        for (int i = 1; i < data.length; i++) {
            max = Math.max(max, data[i]);
        }
        // Example: max = 53

        // Calculate how many digits the maximum number has
        int times = 0;
        while (max >= 1) {
            max = max / 10;
            times++;
        }
        // Example: times = 2 (53 has 2 digits)

        // Process each digit position, starting from the least significant digit (ones)
        for (int i = 0; i < times; i++) {
            // Create a count array for each digit (0-9)
            int[] countArray = new int[10];

            // Count occurrences of each digit at the current position
            for (int m = 0; m < data.length; m++) {
                // Calculate the digit at position i for the current number
                int pos = (int) (data[m] / (Math.pow(10, i)) % 10);
                countArray[pos]++;
            }

            // Example for first iteration (i=0, ones digit):
            // data = [2, 3, 6, 1, 34, 11, 53, 6, 22, 12]
            // countArray = [0, 1, 3, 1, 1, 0, 2, 0, 0, 0]
            // meaning:
            // 0 numbers with '0' in ones place
            // 1 number with '1' in ones place (1)
            // 3 numbers with '2' in ones place (2, 22, 12)
            // 1 number with '3' in ones place (3)
            // 1 number with '4' in ones place (34)
            // 0 numbers with '5' in ones place
            // 2 numbers with '6' in ones place (6, 6)
            // etc.

            // Calculate cumulative counts to determine positions in output array
            for (int j = 1; j < countArray.length; j++) {
                countArray[j] += countArray[j - 1];
            }

            // Example after cumulative calculation:
            // countArray = [0, 1, 4, 5, 6, 6, 8, 8, 8, 8]
            // meaning:
            // Numbers with ones digit ≤ 0 end at position 0
            // Numbers with ones digit ≤ 1 end at position 1
            // Numbers with ones digit ≤ 2 end at position 4
            // etc.

            // Create a temporary array for the sorted result
            int[] tmp = new int[data.length];

            // Build the sorted array based on the current digit
            // Process from right to left to maintain stability
            for (int k = data.length - 1; k >= 0; k--) {
                // Get the digit at position i
                int digit = (int) (data[k] / (Math.pow(10, i)) % 10);
                // Place the number in its correct position and decrement the count
                tmp[--countArray[digit]] = data[k];
            }

            // Example after sorting by ones digit:
            // tmp = [1, 11, 2, 12, 22, 3, 53, 34, 6, 6]

            // Copy the sorted array back to the original array
            System.arraycopy(tmp, 0, data, 0, data.length);

            // Example after first iteration (i=0):
            // data = [1, 11, 2, 12, 22, 3, 53, 34, 6, 6]

            // Example for second iteration (i=1, tens digit):
            // After counting digits at tens place:
            // countArray might be [6, 1, 1, 1, 0, 1, 0, 0, 0, 0]
            // After cumulative calculation:
            // countArray becomes [6, 7, 8, 9, 9, 10, 10, 10, 10, 10]
            // After sorting by tens digit:
            // data = [1, 2, 3, 6, 6, 11, 12, 22, 34, 53]
            // This is the final sorted array since max has 2 digits
        }

        // Final sorted array: [1, 2, 3, 6, 6, 11, 12, 22, 34, 53]
    }

    // --------------------------------------
    // Support for negative numbers

    /**
     * Extension of RadixSorter that can handle negative numbers
     * It works by shifting all numbers to make them non-negative,
     * applying radix sort, and then shifting back
     */
    public class NegativeRadixSorter {
        /**
         * Sorts an array including negative numbers using the radix sort algorithm
         * <p>
         * Example: Initial array [2, -5, 6, -1, 34, 11, -53, 6, 22, 12]
         *
         * @param data the array to be sorted
         */
        public void sort(int[] data) {
            if (data == null || data.length <= 1) return;

            // Find the minimum and maximum values in the array
            int min = data[0];
            int max = data[0];
            for (int i = 1; i < data.length; i++) {
                min = Math.min(min, data[i]);
                max = Math.max(max, data[i]);
            }
            // Example: min = -53, max = 34

            // Calculate the shift needed to make all numbers non-negative
            int shift = min < 0 ? -min : 0;

            // Example: shift = 53

            // Apply shift if there are negative numbers
            if (shift > 0) {
                for (int i = 0; i < data.length; i++) {
                    data[i] += shift;
                }
                max += shift; // Adjust maximum value based on shift
            }

            // Example after shifting:
            // data = [55, 48, 59, 52, 87, 64, 0, 59, 75, 65]
            // max = 87

            // Apply radix sort on the shifted array
            // Process each digit place (1s, 10s, 100s, etc.)
            for (int exp = 1; max / exp > 0; exp *= 10) {
                countSort(data, exp);

                // Example iterations:
                // exp=1 (ones place): data might become [0, 52, 55, 64, 65, 75, 87, 48, 59, 59]
                // exp=10 (tens place): data might become [0, 48, 52, 55, 59, 59, 64, 65, 75, 87]
                // exp=100 (hundreds place): No elements have hundreds digit, so no change
            }

            // Shift numbers back to their original range if needed
            if (shift > 0) {
                for (int i = 0; i < data.length; i++) {
                    data[i] -= shift;
                }
            }

            // Example after shifting back:
            // data = [-53, -5, -1, 2, 6, 6, 11, 12, 22, 34]
            // This is the final sorted array
        }

        /**
         * Sorts an array of phone numbers using the radix sort algorithm
         * <p>
         * Example: Initial phone numbers array
         * [1382675591, 1382675540, 1382675519, 1382675568, 1382675527]
         *
         * @param phoneNumbers the array of phone numbers to be sorted
         */
        public void sortPhoneNumbers(long[] phoneNumbers) {
            // Return early if array is null or empty
            if (phoneNumbers == null || phoneNumbers.length == 0) {
                return;
            }

            // Find the maximum value to determine the number of digits
            long max = phoneNumbers[0];
            for (int i = 1; i < phoneNumbers.length; i++) {
                max = Math.max(max, phoneNumbers[i]);
            }
            // Example: max = 1382675591

            // Calculate how many digits the maximum number has
            int times = 0;
            while (max >= 1) {
                max = max / 10;
                times++;
            }
            // Example: times = 10 (all phone numbers have 10 digits)

            // Process each digit position, starting from the least significant digit (ones)
            for (int i = 0; i < times; i++) {
                // Create a count array for each digit (0-9)
                int[] countArray = new int[10];

                // Count occurrences of each digit at the current position
                for (int m = 0; m < phoneNumbers.length; m++) {
                    // Calculate the digit at position i for the current number
                    int pos = (int) ((phoneNumbers[m] / Math.pow(10, i)) % 10);
                    countArray[pos]++;
                }

                // Example for first iteration (i=0, ones digit):
                // phoneNumbers = [1382675591, 1382675540, 1382675519, 1382675568, 1382675527]
                // countArray = [0, 1, 0, 0, 0, 0, 0, 1, 1, 2]
                // meaning:
                // 0 numbers with '0' in ones place
                // 1 number with '1' in ones place (1382675591)
                // 0 numbers with '2' in ones place
                // ...
                // 1 number with '7' in ones place (1382675527)
                // 1 number with '8' in ones place (1382675568)
                // 2 numbers with '9' in ones place (1382675519, 1382675519)

                // Calculate cumulative counts to determine positions in output array
                for (int j = 1; j < countArray.length; j++) {
                    countArray[j] += countArray[j - 1];
                }

                // Example after cumulative calculation:
                // countArray = [0, 1, 1, 1, 1, 1, 1, 2, 3, 5]

                // Create a temporary array for the sorted result
                long[] tmp = new long[phoneNumbers.length];

                // Build the sorted array based on the current digit
                // Process from right to left to maintain stability
                for (int k = phoneNumbers.length - 1; k >= 0; k--) {
                    // Get the digit at position i
                    int digit = (int) ((phoneNumbers[k] / Math.pow(10, i)) % 10);
                    // Place the number in its correct position and decrement the count
                    tmp[--countArray[digit]] = phoneNumbers[k];
                }

                // Example after sorting by ones digit:
                // tmp = [1382675591, 1382675527, 1382675568, 1382675519, 1382675540]

                // Copy the sorted array back to the original array
                System.arraycopy(tmp, 0, phoneNumbers, 0, phoneNumbers.length);

                // Example iterations (focusing on last 4 digits):
                // Initial: [xxx5591, xxx5540, xxx5519, xxx5568, xxx5527]
                //
                // After sorting by 1st digit (ones):
                // [xxx5591, xxx5540, xxx5519, xxx5568, xxx5527]
                // => [xxx5591, xxx5527, xxx5568, xxx5519, xxx5540]
                //
                // After sorting by 2nd digit (tens):
                // [xxx5591, xxx5527, xxx5568, xxx5519, xxx5540]
                // => [xxx5519, xxx5527, xxx5540, xxx5568, xxx5591]
                //
                // After sorting by 3rd digit (hundreds):
                // [xxx5519, xxx5527, xxx5540, xxx5568, xxx5591]
                // => [xxx5519, xxx5527, xxx5540, xxx5568, xxx5591]
                //
                // After sorting by 4th digit (thousands):
                // [xxx5519, xxx5527, xxx5540, xxx5568, xxx5591]
                // => [xxx5519, xxx5527, xxx5540, xxx5568, xxx5591]
                //
                // Continue for remaining digits...
                // Final sorted phone numbers:
                // [1382675519, 1382675527, 1382675540, 1382675568, 1382675591]
                //
                // Notice how this demonstrates the efficiency of radix sort for closely related
                // phone numbers that differ only in their last few digits
            }

            // Final sorted array: [1382675519, 1382675527, 1382675540, 1382675568, 1382675591]
        }

        /**
         * Helper method to perform counting sort for a specific digit position
         *
         * @param data the array to be sorted
         * @param exp  the current digit position (1 for ones, 10 for tens, etc.)
         */
        private void countSort(int[] data, int exp) {
            // Create a count array for digits 0-9
            int[] count = new int[10];

            // Count occurrences of each digit at the current position
            for (int i = 0; i < data.length; i++) {
                int digit = (data[i] / exp) % 10;
                count[digit]++;
            }

            // Calculate cumulative counts
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            // Create an output array for the sorted result
            int[] output = new int[data.length];

            // Build the sorted array based on the current digit
            // Process from right to left to maintain stability
            for (int i = data.length - 1; i >= 0; i--) {
                int digit = (data[i] / exp) % 10;
                int k = count[digit] - 1;
                output[k] = data[i];
                count[digit]--;
            }

            // Copy the sorted array back to the original array
            for (int i = 0; i < data.length; i++) {
                data[i] = output[i];
            }

            // Example for exp=1 (ones place) with data = [55, 48, 59, 52, 87, 64, 0, 59, 75, 65]:
            // 1. Count digits: count = [1, 0, 1, 0, 1, 3, 0, 1, 1, 2]
            // 2. Cumulative counts: count = [1, 1, 2, 2, 3, 6, 6, 7, 8, 10]
            // 3. Build output array: output = [0, 52, 55, 64, 65, 75, 87, 48, 59, 59]
            // 4. Copy back to data: data = [0, 52, 55, 64, 65, 75, 87, 48, 59, 59]
        }
    }
}