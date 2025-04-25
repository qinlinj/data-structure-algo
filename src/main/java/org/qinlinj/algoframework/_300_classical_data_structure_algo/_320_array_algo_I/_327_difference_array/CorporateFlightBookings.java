package org.qinlinj.algoframework._300_classical_data_structure_algo._320_array_algo_I._327_difference_array;

/**
 * Corporate Flight Bookings (LeetCode 1109)
 * <p>
 * Key Points:
 * 1. This problem is a disguised application of the difference array technique
 * 2. Each booking represents a range update operation
 * 3. The problem requires 1-indexed arrays, so we need to adjust indices
 * 4. Time Complexity: O(n + m) where n is number of flights and m is number of bookings
 * 5. Space Complexity: O(n) for the difference array
 */
public class CorporateFlightBookings {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        CorporateFlightBookings solution = new CorporateFlightBookings();

        // Example 1: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
        int[][] bookings1 = {
                {1, 2, 10},   // 10 seats reserved for flights 1-2
                {2, 3, 20},   // 20 seats reserved for flights 2-3
                {2, 5, 25}    // 25 seats reserved for flights 2-5
        };
        int n1 = 5;

        int[] result1 = solution.corpFlightBookings(bookings1, n1);

        System.out.println("Example 1 Result:");
        for (int i = 0; i < result1.length; i++) {
            System.out.print(result1[i] + " ");
        }
        System.out.println();  // Expected: [10, 55, 45, 25, 25]

        // Example 2: bookings = [[1,2,10],[2,2,15]], n = 2
        int[][] bookings2 = {
                {1, 2, 10},   // 10 seats reserved for flights 1-2
                {2, 2, 15}    // 15 seats reserved for flight 2
        };
        int n2 = 2;

        int[] result2 = solution.corpFlightBookings(bookings2, n2);

        System.out.println("Example 2 Result:");
        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i] + " ");
        }
        System.out.println();  // Expected: [10, 25]
    }

    /**
     * LeetCode 1109: Corporate Flight Bookings
     * <p>
     * There are n flights, labeled from 1 to n.
     * You are given an array of flight bookings, where bookings[i] = [firsti, lasti, seatsi]
     * represents a booking for flights firsti through lasti with seatsi seats reserved for each flight.
     * Return an array answer of length n, where answer[i] is the total number of seats reserved for flight i.
     *
     * @param bookings Array of bookings where each booking is [first, last, seats]
     * @param n        Number of flights
     * @return Array of total seats reserved for each flight
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        // Initialize array with zeros
        int[] flights = new int[n];

        // Create difference array utility
        Difference diff = new Difference(flights);

        // Apply each booking as a range update
        for (int[] booking : bookings) {
            // Convert from 1-indexed to 0-indexed
            int firstFlight = booking[0] - 1;
            int lastFlight = booking[1] - 1;
            int seats = booking[2];

            // Add seats to the range [firstFlight, lastFlight]
            diff.increment(firstFlight, lastFlight, seats);
        }

        // Return the result after all bookings
        return diff.result();
    }

    /**
     * Utility class for difference array operations
     */
    static class Difference {
        // The difference array
        private int[] diff;

        /**
         * Initializes the difference array from the input array
         */
        public Difference(int[] nums) {
            if (nums.length == 0) {
                throw new IllegalArgumentException("Input array cannot be empty");
            }

            diff = new int[nums.length];
            diff[0] = nums[0];

            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        /**
         * Increments all elements in the range [i, j] by val
         */
        public void increment(int i, int j, int val) {
            diff[i] += val;

            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }

        /**
         * Reconstructs the original array after all operations
         */
        public int[] result() {
            int[] res = new int[diff.length];
            res[0] = diff[0];

            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }

            return res;
        }
    }

    /**
     * Implementation Notes:
     *
     * 1. The key insight is recognizing this as a difference array problem:
     *    - Each booking represents adding seats to a range of flights
     *    - We need to efficiently process multiple range updates
     *    - After all updates, we need the final count for each flight
     *
     * 2. The main challenge is handling the 1-indexed nature of the problem:
     *    - Flights are numbered from 1 to n in the problem description
     *    - Arrays in Java are 0-indexed
     *    - We convert by subtracting 1 from the flight numbers
     *
     * 3. The solution has O(n + m) time complexity:
     *    - O(n) for initializing and reconstructing the array
     *    - O(m) for processing m bookings
     *    - Each range update operation is O(1)
     */
}