package org.qinlinj.practical.leaderboard;

import java.util.*;

public class Leaderboard {
    private Map<Integer, Integer> map;

    public Leaderboard() {
        this.map = new HashMap<>();
    }

    public static void main(String[] args) {

    }

    public void addScore(int playerId, int score) {
        if (map.containsKey(playerId)) {
            map.put(playerId, map.get(playerId) + score);
        } else {
            map.put(playerId, score);
        }
    }

    public int top(int k) {
        Integer[] scores = map.values().toArray(new Integer[map.values().size()]);
    }

    public void reset(int playerId) {
        if (map.containsKey(playerId)) {
            map.remove(playerId);
        }
    }
}
