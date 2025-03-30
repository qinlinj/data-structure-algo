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
    private int id;
    private int scores;

    public Player(int id, int scores) {
        this.id = id;
        this.scores = scores;
    }

    public int getId() {
        return id;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }
}
