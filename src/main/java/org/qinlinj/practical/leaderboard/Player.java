package org.qinlinj.practical.leaderboard;

/**
 * Player Class
 * <p>
 * This class represents a player in the leaderboard system.
 * It encapsulates player information including:
 * - A unique player identifier
 * - The player's cumulative score
 * <p>
 * This class serves as the data model for player entities and is used by
 * the Leaderboard implementations to track and sort players.
 */
public class Player {
    // The unique identifier for the player
    private int id;

    // The cumulative score of the player
    private int scores;

    /**
     * Constructor to create a new Player with specified ID and initial score
     *
     * @param id     The unique identifier for the player
     * @param scores The initial score value for the player
     */
    public Player(int id, int scores) {
        this.id = id;
        this.scores = scores;
    }

    /**
     * Retrieves the player's unique identifier
     *
     * @return The player's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the player's current score
     *
     * @return The player's cumulative score
     */
    public int getScores() {
        return scores;
    }

    /**
     * Updates the player's score to a new value
     * <p>
     * Note: This method replaces the current score rather than adding to it.
     * For incrementing scores, the caller must get the current score,
     * add the increment value, and then set the new total.
     *
     * @param scores The new score value to set
     */
    public void setScores(int scores) {
        this.scores = scores;
    }
}