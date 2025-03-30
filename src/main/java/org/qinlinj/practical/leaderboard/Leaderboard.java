package org.qinlinj.practical.leaderboard;

import java.util.*;

/**
 * Leaderboard Implementation
 * <p>
 * This class implements a simple game leaderboard that tracks player scores.
 * It supports adding scores to players, retrieving the sum of top K scores,
 * and resetting a player's score.
 * <p>
 * The implementation uses a HashMap to store player IDs and their corresponding scores,
 * which provides O(1) time complexity for score updates and resets.
 */
public class Leaderboard {
    // Map to store player IDs (keys) and their corresponding scores (values)
    private Map<Integer, Integer> map;

    /**
     * Constructor initializes an empty leaderboard
     */
    public Leaderboard() {
        this.map = new HashMap<>();
    }

    /**
     * Demo of leaderboard functionality
     */
    public static void main(String[] args) {
        // Create a new leaderboard
        Leaderboard leaderboard = new Leaderboard();

        // Add initial scores for players 1, 2, 3, and 4
        leaderboard.addScore(1, 20);  // Player 1: 20 points
        leaderboard.addScore(2, 30);  // Player 2: 30 points
        leaderboard.addScore(3, 16);  // Player 3: 16 points
        leaderboard.addScore(4, 44);  // Player 4: 44 points

        // Get the sum of the top 2 scores (44 + 30 = 74)
        System.out.println(leaderboard.top(2));  // Output: 74

        // Add more scores to players 2 and 3
        leaderboard.addScore(2, 34);  // Player 2: 30 + 34 = 64 points
        leaderboard.addScore(3, 23);  // Player 3: 16 + 23 = 39 points

        // Get the sum of the top 1 score (Player 2: 64 points)
        System.out.println(leaderboard.top(1));  // Output: 64

        // Reset scores for players 2 and 4
        leaderboard.reset(2);  // Player 2's score is now 0 (removed)
        leaderboard.reset(4);  // Player 4's score is now 0 (removed)

        // Get the sum of the top 1 score (Player 3: 39 points)
        System.out.println(leaderboard.top(1));  // Output: 39
    }

    /**
     * Adds a score to a player's total score
     * If the player doesn't exist in the leaderboard, they are added with the given score
     * If the player already exists, the score is added to their current total
     * <p>
     * Time Complexity: O(1) for HashMap operations
     *
     * @param playerId The ID of the player
     * @param score    The score to add
     */
    public void addScore(int playerId, int score) {
        // Check if the player already exists in the leaderboard
        if (map.containsKey(playerId)) {
            // Add the new score to the player's existing score
            map.put(playerId, map.get(playerId) + score);
        } else {
            // Add the player with their initial score
            map.put(playerId, score);
        }
    }

    /**
     * Returns the sum of the top K scores in the leaderboard
     * <p>
     * Time Complexity: O(n log n) where n is the number of players
     * - O(n) to convert values to array
     * - O(n log n) for sorting
     * - O(k) to sum the top k scores
     * <p>
     * Space Complexity: O(n) for the scores array
     *
     * @param k The number of top scores to sum
     * @return The sum of the top K scores
     */
    public int top(int k) {
        // Convert the scores to an array
        Integer[] scores = map.values().toArray(new Integer[map.values().size()]);

        // Sort the scores in descending order (highest scores first)
        Arrays.sort(scores, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;  // Descending order
            }
        });

        // Sum the top k scores
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += scores[i];
        }

        return sum;
    }

    /**
     * Resets a player's score by removing them from the leaderboard
     * <p>
     * Time Complexity: O(1) for HashMap removal
     *
     * @param playerId The ID of the player to reset
     */
    public void reset(int playerId) {
        // Check if the player exists in the leaderboard
        if (map.containsKey(playerId)) {
            // Remove the player from the leaderboard
            map.remove(playerId);
        }
    }
}
