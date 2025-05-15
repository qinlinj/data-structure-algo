package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._753_bfs_classic_practice_II; /**
 * Water Jug Problem (LeetCode 365)
 * ------------------------------
 * <p>
 * Summary:
 * This problem involves two water jugs with capacities x and y. Starting with both jugs empty,
 * we need to determine if it's possible to measure exactly z units of water using these jugs.
 * We can perform the following operations:
 * 1. Fill any jug completely
 * 2. Empty any jug completely
 * 3. Pour water from one jug to another until either the source jug is empty or the target jug is full
 * <p>
 * Key Concepts:
 * 1. State space exploration using BFS
 * 2. Representing states as [jug1, jug2] pairs
 * 3. Tracking visited states to avoid cycles
 * 4. Generating next states based on allowed operations
 * <p>
 * Approach:
 * - Use BFS to explore all possible states of the jugs
 * - Each state is represented by the amount of water in each jug [jug1, jug2]
 * - From each state, generate all possible next states by applying the allowed operations
 * - Check if we can reach a state where either jug contains exactly z units of water
 * <p>
 * Time Complexity: O(x*y) as there are x*y possible states
 * Space Complexity: O(x*y) for the queue and visited set
 * <p>
 * Note: This problem can also be solved mathematically using Bézout's identity (aka the extended
 * Euclidean algorithm), but the BFS approach is more intuitive and directly maps to the problem.
 */

import java.util.*;

public class _753_f_WaterJugProblem {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _753_f_WaterJugProblem solution = new _753_f_WaterJugProblem();

        // Example 1: x = 3, y = 5, z = 4
        System.out.println("Example 1 (BFS): " + solution.canMeasureWater(3, 5, 4));  // Expected: true
        System.out.println("Example 1 (Math): " + solution.canMeasureWaterMath(3, 5, 4));  // Expected: true

        // Example 2: x = 2, y = 6, z = 5
        System.out.println("Example 2 (BFS): " + solution.canMeasureWater(2, 6, 5));  // Expected: false
        System.out.println("Example 2 (Math): " + solution.canMeasureWaterMath(2, 6, 5));  // Expected: false

        // Example 3: x = 1, y = 2, z = 3
        System.out.println("Example 3 (BFS): " + solution.canMeasureWater(1, 2, 3));  // Expected: true
        System.out.println("Example 3 (Math): " + solution.canMeasureWaterMath(1, 2, 3));  // Expected: true

        // Additional examples
        System.out.println("Additional (BFS): " + solution.canMeasureWater(4, 6, 8));  // Expected: true
        System.out.println("Additional (Math): " + solution.canMeasureWaterMath(4, 6, 8));  // Expected: true

        System.out.println("Additional (BFS): " + solution.canMeasureWater(7, 11, 16));  // Expected: true
        System.out.println("Additional (Math): " + solution.canMeasureWaterMath(7, 11, 16));  // Expected: true
    }

    /**
     * Determine if it's possible to measure exactly z units of water
     * @param jug1Capacity Capacity of the first jug
     * @param jug2Capacity Capacity of the second jug
     * @param targetCapacity Target amount of water to measure
     * @return True if possible, false otherwise
     */
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        // If target is greater than the sum of capacities, it's impossible
        if (targetCapacity > jug1Capacity + jug2Capacity) {
            return false;
        }

        // If target is 0 or equal to either jug capacity or their sum, it's trivially possible
        if (targetCapacity == 0 || targetCapacity == jug1Capacity ||
                targetCapacity == jug2Capacity || targetCapacity == jug1Capacity + jug2Capacity) {
            return true;
        }

        // Queue for BFS
        Queue<int[]> queue = new LinkedList<>();

        // Use a set to track visited states to avoid cycles
        Set<Long> visited = new HashSet<>();

        // Start BFS from the initial state [0, 0] (both jugs empty)
        queue.offer(new int[]{0, 0});
        visited.add(hash(0, 0, jug2Capacity));

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int jug1 = state[0];
            int jug2 = state[1];

            // Check if we've reached the target
            if (jug1 == targetCapacity || jug2 == targetCapacity || jug1 + jug2 == targetCapacity) {
                return true;
            }

            // Generate all possible next states
            List<int[]> nextStates = new ArrayList<>();

            // Fill jug 1
            nextStates.add(new int[]{jug1Capacity, jug2});

            // Fill jug 2
            nextStates.add(new int[]{jug1, jug2Capacity});

            // Empty jug 1
            nextStates.add(new int[]{0, jug2});

            // Empty jug 2
            nextStates.add(new int[]{jug1, 0});

            // Pour from jug 1 to jug 2 until jug 1 is empty or jug 2 is full
            int pourAmount = Math.min(jug1, jug2Capacity - jug2);
            nextStates.add(new int[]{jug1 - pourAmount, jug2 + pourAmount});

            // Pour from jug 2 to jug 1 until jug 2 is empty or jug 1 is full
            pourAmount = Math.min(jug2, jug1Capacity - jug1);
            nextStates.add(new int[]{jug1 + pourAmount, jug2 - pourAmount});

            // Add unvisited states to the queue
            for (int[] nextState : nextStates) {
                long hashValue = hash(nextState[0], nextState[1], jug2Capacity);
                if (!visited.contains(hashValue)) {
                    queue.offer(nextState);
                    visited.add(hashValue);
                }
            }
        }

        // If we've explored all states and haven't found the target, it's impossible
        return false;
    }

    /**
     * Hash function to convert a state to a unique long value
     * @param jug1 Amount of water in jug 1
     * @param jug2 Amount of water in jug 2
     * @param jug2Capacity Capacity of jug 2 (used for hashing)
     * @return Unique hash value for the state
     */
    private long hash(int jug1, int jug2, int jug2Capacity) {
        return (long) jug1 * (jug2Capacity + 1) + jug2;
    }

    /**
     * Mathematical solution using Bézout's identity
     * z is achievable if and only if z is a multiple of gcd(x,y) and z <= x+y
     */
    public boolean canMeasureWaterMath(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        // Edge cases
        if (targetCapacity > jug1Capacity + jug2Capacity) {
            return false;
        }

        if (targetCapacity == 0 || targetCapacity == jug1Capacity ||
                targetCapacity == jug2Capacity || targetCapacity == jug1Capacity + jug2Capacity) {
            return true;
        }

        // Apply Bézout's identity: z is achievable if z is a multiple of gcd(x,y)
        return targetCapacity % gcd(jug1Capacity, jug2Capacity) == 0;
    }

    /**
     * Calculate the greatest common divisor using Euclidean algorithm
     */
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}