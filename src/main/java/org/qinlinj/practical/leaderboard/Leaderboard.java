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
    private Map<Integer, Integer> map;

    public Leaderboard() {
        this.map = new HashMap<>();
    }

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addScore(1, 20);
        leaderboard.addScore(2, 30);
        leaderboard.addScore(3, 16);
        leaderboard.addScore(4, 44);

        System.out.println(leaderboard.top(2));

        leaderboard.addScore(2, 34);
        leaderboard.addScore(3, 23);

        System.out.println(leaderboard.top(1));

        leaderboard.reset(2);
        leaderboard.reset(4);

        System.out.println(leaderboard.top(1));
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
        if (map.containsKey(playerId)) {
            map.put(playerId, map.get(playerId) + score);
        } else {
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
        Integer[] scores = map.values().toArray(new Integer[map.values().size()]);
        Arrays.sort(scores, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
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
        if (map.containsKey(playerId)) {
            map.remove(playerId);
        }
    }
}
