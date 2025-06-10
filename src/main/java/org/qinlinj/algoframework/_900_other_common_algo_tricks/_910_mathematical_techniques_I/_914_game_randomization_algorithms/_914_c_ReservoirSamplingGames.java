package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._914_game_randomization_algorithms;

/**
 * RESERVOIR SAMPLING ALGORITHM FOR GAME DEVELOPMENT
 * <p>
 * This class implements Reservoir Sampling, a sophisticated random selection algorithm
 * that can select k random elements from a stream of unknown or very large size,
 * using only O(k) space complexity. Perfect for games with massive worlds or
 * memory-constrained environments.
 * <p>
 * Key Concepts:
 * 1. Streaming Selection: Select k items from stream without knowing total size
 * - Process elements one by one
 * - Maintain reservoir of size k
 * - Each element has equal probability k/n of being selected
 * <p>
 * 2. Core Algorithm:
 * - Fill reservoir with first k elements
 * - For each subsequent element i (i > k):
 * * Generate random number r in [0, i]
 * * If r < k, replace reservoir[r] with current element
 * <p>
 * 3. Mathematical Proof of Uniformity:
 * - Probability element i is selected = k/i
 * - Probability it survives to end = ∏(1 - 1/j) for j > i
 * - Final probability = k/n for all elements
 * <p>
 * 4. Applications in Game Development:
 * - Large world exploration: Random POI selection
 * - Procedural generation: Selecting from massive item pools
 * - Memory-limited systems: Mobile/embedded games
 * - Streaming content: Online multiplayer events
 * <p>
 * 5. Advantages over Fisher-Yates:
 * - O(k) space instead of O(n)
 * - Works with unknown/infinite streams
 * - Suitable for very large datasets
 * - Real-time streaming capability
 * <p>
 * 6. LeetCode Applications:
 * - 382: Linked List Random Node
 * - 398: Random Pick Index
 * - General random sampling problems
 * <p>
 * Time Complexity: O(n) to process n elements
 * Space Complexity: O(k) for reservoir storage
 */

public class _914_c_ReservoirSamplingGames {
    private java.util.Random random;

    public _914_c_ReservoirSamplingGames() {
        this.random = new java.util.Random();
    }

    public _914_c_ReservoirSamplingGames(long seed) {
        this.random = new java.util.Random(seed);
    }

    /**
     * LeetCode 382: Linked List Random Node
     * Returns a random node's value from the linked list
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static class LinkedListRandomNode {
        private ListNode head;
        private java.util.Random random;

        public LinkedListRandomNode(ListNode head) {
            this.head = head;
            this.random = new java.util.Random();
        }

        /**
         * Returns a random node's value
         */
        public int getRandom() {
            int result = 0;
            int count = 0;
            ListNode current = head;

            while (current != null) {
                count++;
                // With probability 1/count, select current element
                if (random.nextInt(count) == 0) {
                    result = current.val;
                }
                current = current.next;
            }

            return result;
        }
    }
}
