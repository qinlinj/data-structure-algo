package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._344_queue_classic_practice;

/**
 * Queue Classic Problems - Problem 5: Time Needed to Buy Tickets (LeetCode 2073)
 * <p>
 * This problem exemplifies how understanding the queue's behavior can lead to
 * an efficient mathematical solution without explicitly simulating the queue.
 * <p>
 * Problem Description:
 * - n people are in a queue to buy tickets, with person 0 at the front
 * - Each person i wants to buy tickets[i] tickets
 * - It takes 1 second to buy 1 ticket
 * - After buying a ticket, if a person needs more tickets, they go to the end of the queue
 * - If a person has all their tickets, they leave the queue
 * - Return the time it takes for person k to finish buying all their tickets
 * <p>
 * Key insights:
 * - Each person's turn cycles through the queue
 * - People before position k will buy min(tickets[i], tickets[k]) tickets
 * - People after position k will buy min(tickets[i], tickets[k]-1) tickets
 * <p>
 * Time Complexity:
 * - O(n) where n is the number of people in the queue
 * <p>
 * Space Complexity:
 * - O(1), uses only constant extra space
 */

class _344_e_TimeRequiredToBuy {

    // Main method to demonstrate the implementation
    public static void main(String[] args) {
        _344_e_TimeRequiredToBuy solution = new _344_e_TimeRequiredToBuy();

        // Test cases
        int[] tickets1 = {2, 3, 2};
        int k1 = 2;
        System.out.println("Example 1: " + solution.timeRequiredToBuy(tickets1, k1));  // Output: 6

        int[] tickets2 = {5, 1, 1, 1};
        int k2 = 0;
        System.out.println("Example 2: " + solution.timeRequiredToBuy(tickets2, k2));  // Output: 8

        // Compare with simulation approach
        System.out.println("Simulation approach for Example 1: " +
                solution.timeRequiredToBuySimulation(tickets1.clone(), k1));
        System.out.println("Simulation approach for Example 2: " +
                solution.timeRequiredToBuySimulation(tickets2.clone(), k2));

        // Explanation of the original solution:
        // For Example 1: [2, 3, 2], k = 2
        // - Person 0 (before k): buys min(2, 2) = 2 tickets (time: 2)
        // - Person 1 (before k): buys min(3, 2) = 2 tickets (time: 2)
        // - Person 2 (k itself): buys min(2, 2) = 2 tickets (time: 2)
        // Total time: 2 + 2 + 2 = 6

        // For Example 2: [5, 1, 1, 1], k = 0
        // - Person 0 (k itself): buys min(5, 5) = 5 tickets (time: 5)
        // - Person 1 (after k): buys min(1, 5-1) = 1 ticket (time: 1)
        // - Person 2 (after k): buys min(1, 5-1) = 1 ticket (time: 1)
        // - Person 3 (after k): buys min(1, 5-1) = 1 ticket (time: 1)
        // Total time: 5 + 1 + 1 + 1 = 8
    }

    /**
     * Calculate the time needed for person k to buy all their tickets
     *
     * @param tickets Array of tickets each person wants to buy
     * @param k       Index of the person we're interested in
     * @return Time (in seconds) for person k to buy all their tickets
     */
    public int timeRequiredToBuy(int[] tickets, int k) {
        int totalTime = 0;

        // Iterate through each person in the queue
        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                // People before or at position k
                // They will buy at most tickets[k] tickets before person k is done
                totalTime += Math.min(tickets[i], tickets[k]);
            } else {
                // People after position k
                // They will buy at most (tickets[k]-1) tickets before person k is done
                // This is because after person k buys their last ticket, the process stops
                totalTime += Math.min(tickets[i], tickets[k] - 1);
            }
        }

        return totalTime;
    }

    /**
     * Alternative solution that simulates the queue process
     * (Less efficient but helps understand the problem)
     */
    public int timeRequiredToBuySimulation(int[] tickets, int k) {
        int time = 0;

        // Continue until the person at position k has bought all tickets
        while (tickets[k] > 0) {
            // Simulate one round through the queue
            for (int i = 0; i < tickets.length; i++) {
                // Skip people who have already bought all their tickets
                if (tickets[i] == 0) {
                    continue;
                }

                // Person buys one ticket
                tickets[i]--;
                time++;

                // If person k has bought all tickets, we're done
                if (i == k && tickets[i] == 0) {
                    return time;
                }
            }
        }

        return time; // Should never reach here
    }
}
