package org.qinlinj.practical.leaderboard;

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
