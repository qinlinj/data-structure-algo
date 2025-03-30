package org.qinlinj.practical.leaderboard;

import java.util.*;

/**
 * Optimized Leaderboard Implementation using TreeSet
 * <p>
 * This class implements a game leaderboard that tracks player scores with improved
 * performance for retrieving top scores compared to the basic implementation.
 * <p>
 * Key optimizations:
 * 1. Uses a TreeSet to maintain players in sorted order automatically (by score)
 * 2. Provides O(log n) insertion and removal operations
 * 3. Enables O(k) access to the top k scores without sorting each time
 * <p>
 * The implementation uses both:
 * - HashMap for O(1) player lookups by ID
 * - TreeSet for maintaining the sorted order of players by score
 */
public class Leaderboard1 {
    // Map to quickly look up players by their ID
    private Map<Integer, Player> map;

    // TreeSet to maintain players in sorted order by score (highest first)
    private TreeSet<Player> players;

    /**
     * Constructor initializes an empty leaderboard with a custom-sorted TreeSet
     */
    public Leaderboard1() {
        this.map = new HashMap<>();

        // Initialize TreeSet with a custom comparator
        players = new TreeSet<>(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                // Primary sort by score (descending order)
                // Secondary sort by player ID (ascending) to handle equal scores
                return o1.getScores() == o2.getScores() ?
                        o1.getId() - o2.getId() :
                        o2.getScores() - o1.getScores();
            }
        });
    }

    /**
     * Demo of optimized leaderboard functionality
     */
    public static void main(String[] args) {
        // Create a new leaderboard
        Leaderboard1 leaderboard = new Leaderboard1();

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
        leaderboard.reset(2);  // Player 2's score is now removed
        leaderboard.reset(4);  // Player 4's score is now removed

        // Get the sum of the top 1 score (Player 3: 39 points)
        System.out.println(leaderboard.top(1));  // Output: 39
    }

    /**
     * Adds a score to a player's total score
     * <p>
     * Time Complexity: O(log n) where n is the number of players
     * - O(1) for HashMap operations
     * - O(log n) for TreeSet removal and insertion
     * <p>
     * Implementation details:
     * 1. If the player exists, we must remove them from TreeSet
     * 2. Update their score
     * 3. Re-insert them into TreeSet to maintain sorted order
     *
     * @param playerId The ID of the player
     * @param score    The score to add
     */
    public void addScore(int playerId, int score) {
        Player player = null;

        // Check if the player already exists in the leaderboard
        if (map.containsKey(playerId)) {
            // Get the existing player
            player = map.get(playerId);

            // Remove from TreeSet first (must do this before changing score)
            // because TreeSet sorting depends on the score
            players.remove(player);

            // Update the player's score
            player.setScores(player.getScores() + score);
        } else {
            // Create a new player with the given score
            player = new Player(playerId, score);

            // Add to the HashMap for quick lookups
            map.put(playerId, player);
        }

        // Add (or re-add) the player to the TreeSet
        // This will place them in the correct position based on their score
        players.add(player);
    }

    /**
     * Returns the sum of the top K scores in the leaderboard
     * <p>
     * Time Complexity: O(k) where k is the number of top scores requested
     * This is much more efficient than O(n log n) in the basic implementation
     *
     * @param k The number of top scores to sum
     * @return The sum of the top K scores
     */
    public int top(int k) {
        // Get an iterator for the TreeSet (already sorted by score)
        Iterator<Player> it = players.iterator();
        int sum = 0;

        // Iterate through the first k elements and sum their scores
        while (it.hasNext() && k > 0) {
            sum += it.next().getScores();
            k--;
        }

        return sum;
    }

    /**
     * Resets a player's score by removing them from the leaderboard
     * <p>
     * Time Complexity: O(log n) where n is the number of players
     * - O(1) for HashMap lookups and removal
     * - O(log n) for TreeSet removal
     *
     * @param playerId The ID of the player to reset
     */
    public void reset(int playerId) { // O(log n)
        // Check if the player exists in the leaderboard
        if (map.containsKey(playerId)) {
            // Remove from TreeSet first
            players.remove(map.get(playerId));

            // Then remove from HashMap
            map.remove(playerId);
        }
    }
}