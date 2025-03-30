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
    private Map<Integer, Player> map;
    private TreeSet<Player> players;

    public Leaderboard1() {
        this.map = new HashMap<>();
        players = new TreeSet<>(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.getScores() == o2.getScores() ?
                        o1.getId() - o2.getId() :
                        o2.getScores() - o1.getScores();
            }
        });
    }

    public static void main(String[] args) {
        Leaderboard1 leaderboard = new Leaderboard1();
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

    public void addScore(int playerId, int score) {
        Player player = null;
        if (map.containsKey(playerId)) {
            player = map.get(playerId);
            players.remove(player);
            player.setScores(player.getScores() + score);
        } else {
            player = new Player(playerId, score);
            map.put(playerId, player);
        }
        players.add(player);
    }

    public int top(int k) {
        Iterator<Player> it = players.iterator();
        int sum = 0;
        // O(k)
        while (it.hasNext() && k > 0) {
            sum += it.next().getScores();
            k--;
        }
        return sum;
    }

    public void reset(int playerId) { // O(logn)
        if (map.containsKey(playerId)) {
            players.remove(map.get(playerId));
            map.remove(playerId);
        }
    }
}
