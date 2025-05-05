package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._531_priority_queue_practice; /**
 * _531_d_SuperUglyNumber
 * <p>
 * LeetCode #313: Super Ugly Number
 * <p>
 * This solution finds the nth super ugly number, which is a positive number whose prime factors
 * are all in the given prime numbers array. This is an extension of the "Ugly Number II" problem
 * and can be viewed as merging multiple sorted lists.
 * <p>
 * Approach:
 * 1. Conceptualize each prime factor as generating a sequence of ugly numbers
 * 2. Use a min-heap to merge these sequences efficiently
 * 3. Maintain pointers into an array of already-computed ugly numbers
 * 4. Use the priority queue to always select the smallest next ugly number
 * <p>
 * Time Complexity: O(n * log(m)) where n is the desired index and m is the number of primes
 * Space Complexity: O(n + m) for the ugly array and priority queue
 */

import java.util.*;

public class _531_d_SuperUglyNumber {

    public static void main(String[] args) {
        _531_d_SuperUglyNumber solution = new _531_d_SuperUglyNumber();

        // Test case 1
        int n1 = 12;
        int[] primes1 = {2, 7, 13, 19};
        System.out.println(n1 + "th super ugly number with primes " +
                arrayToString(primes1) + ": " +
                solution.nthSuperUglyNumber(n1, primes1));
        // Expected output: 32

        // Test case 2
        int n2 = 1;
        int[] primes2 = {2, 3, 5};
        System.out.println(n2 + "st super ugly number with primes " +
                arrayToString(primes2) + ": " +
                solution.nthSuperUglyNumber(n2, primes2));
        // Expected output: 1

        // Additional test case
        int n3 = 10;
        int[] primes3 = {2, 3, 5};
        System.out.println(n3 + "th super ugly number with primes " +
                arrayToString(primes3) + ": " +
                solution.nthSuperUglyNumber(n3, primes3));
        // Expected output: 12
    }

    // Helper method to convert an array to string
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        // Array to store ugly numbers in order
        int[] ugly = new int[n + 1];
        ugly[1] = 1; // First ugly number is 1

        // Priority queue stores [product, prime, pointer] triples
        // - product: current product value (the potential next ugly number)
        // - prime: the prime factor used
        // - pointer: index into ugly array for the next multiplication
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Initialize the heap with the first product from each prime
        for (int prime : primes) {
            minHeap.offer(new int[]{prime, prime, 2});
        }

        // Generate ugly numbers
        for (int i = 2; i <= n; i++) {
            // Get the smallest product from the heap
            int[] current = minHeap.poll();
            int product = current[0];
            int prime = current[1];
            int pointer = current[2];

            // Avoid duplicate ugly numbers
            if (product != ugly[i - 1]) {
                ugly[i] = product;
            } else {
                // Duplicate found, try the next smallest
                i--;
            }

            // Calculate the next product and offer to the heap
            int nextProduct = ugly[pointer] * prime;
            minHeap.offer(new int[]{nextProduct, prime, pointer + 1});
        }

        return ugly[n];
    }
}