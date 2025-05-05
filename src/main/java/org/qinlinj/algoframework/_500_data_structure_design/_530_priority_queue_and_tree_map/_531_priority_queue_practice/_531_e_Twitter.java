package org.qinlinj.algoframework._500_data_structure_design._530_priority_queue_and_tree_map._531_priority_queue_practice; /**
 * _531_e_Twitter
 * <p>
 * LeetCode #355: Design Twitter
 * <p>
 * This class implements a simplified Twitter design with core functionalities:
 * - Users can post tweets
 * - Users can follow/unfollow other users
 * - Users can see a news feed (recent tweets from followed users and themselves)
 * <p>
 * The key insight is treating each user's tweet history as a sorted linked list,
 * and using a priority queue to merge these lists when generating a news feed.
 * <p>
 * Time Complexity:
 * - postTweet: O(1)
 * - getNewsFeed: O(K log F) where K is max tweets returned and F is number of followees
 * - follow/unfollow: O(1)
 * <p>
 * Space Complexity: O(U + T) where U is number of users and T is number of tweets
 */

import java.util.*;

public class _531_e_Twitter {
    // Map to store user data by ID
    private final Map<Integer, User> userMap;
    // Global timestamp for ordering tweets
    private int timestamp;

    // Inner classes for User and Tweet

    // Constructor
    public _531_e_Twitter() {
        timestamp = 0;
        userMap = new HashMap<>();
    }

    public static void main(String[] args) {
        _531_e_Twitter twitter = new _531_e_Twitter();

        // Test the Twitter implementation
        twitter.postTweet(1, 5);  // User 1 posts tweet with id 5

        List<Integer> feed1 = twitter.getNewsFeed(1);
        System.out.println("User 1's feed: " + feed1);  // Expected: [5]

        twitter.follow(1, 2);     // User 1 follows user 2
        twitter.postTweet(2, 6);  // User 2 posts tweet with id 6

        List<Integer> feed2 = twitter.getNewsFeed(1);
        System.out.println("User 1's feed after following user 2: " + feed2);  // Expected: [6, 5]

        twitter.unfollow(1, 2);   // User 1 unfollows user 2

        List<Integer> feed3 = twitter.getNewsFeed(1);
        System.out.println("User 1's feed after unfollowing user 2: " + feed3);  // Expected: [5]
    }

    // Post a tweet - O(1)
    public void postTweet(int userId, int tweetId) {
        // Create user if they don't exist
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }

        // Post the tweet
        userMap.get(userId).postTweet(tweetId, timestamp++);
    }

    // Get a user's news feed - O(K log F)
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feed = new ArrayList<>();

        // Check if user exists
        if (!userMap.containsKey(userId)) {
            return feed;
        }

        User user = userMap.get(userId);

        // Max heap: sort tweets by timestamp (newest first)
        PriorityQueue<Tweet> pq = new PriorityQueue<>(
                (a, b) -> b.timestamp - a.timestamp
        );

        // Add the first tweet from each followed user
        for (int followeeId : user.following) {
            if (userMap.containsKey(followeeId) &&
                    userMap.get(followeeId).tweetHead != null) {
                pq.offer(userMap.get(followeeId).tweetHead);
            }
        }

        // Get the most recent 10 tweets
        int count = 0;
        while (!pq.isEmpty() && count < 10) {
            Tweet tweet = pq.poll();
            feed.add(tweet.id);
            count++;

            // Add the next tweet from the same user
            if (tweet.next != null) {
                pq.offer(tweet.next);
            }
        }

        return feed;
    }

    // Follow a user - O(1)
    public void follow(int followerId, int followeeId) {
        // Create users if they don't exist
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }
        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new User(followeeId));
        }

        // Follow
        userMap.get(followerId).follow(followeeId);
    }

    // Unfollow a user - O(1)
    public void unfollow(int followerId, int followeeId) {
        // Check if follower exists
        if (userMap.containsKey(followerId)) {
            userMap.get(followerId).unfollow(followeeId);
        }
    }

    // Tweet class - represents a single tweet
    private static class Tweet {
        private final int id;          // Tweet ID
        private final int timestamp;   // When the tweet was posted
        private final Tweet next;      // Link to next (older) tweet, forming a linked list

        public Tweet(int id, int timestamp, Tweet next) {
            this.id = id;
            this.timestamp = timestamp;
            this.next = next;
        }
    }

    // User class - represents a Twitter user
    private static class User {
        private final int id;                      // User ID
        private final Set<Integer> following;      // Set of users this user follows
        private Tweet tweetHead;                   // Head of linked list of tweets

        public User(int id) {
            this.id = id;
            this.tweetHead = null;
            this.following = new HashSet<>();
            // Users follow themselves to see their own tweets
            this.following.add(id);
        }

        // Post a new tweet
        public void postTweet(int tweetId, int timestamp) {
            Tweet newTweet = new Tweet(tweetId, timestamp, tweetHead);
            tweetHead = newTweet;
        }

        // Follow another user
        public void follow(int userId) {
            following.add(userId);
        }

        // Unfollow another user
        public void unfollow(int userId) {
            // Users cannot unfollow themselves
            if (userId != this.id) {
                following.remove(userId);
            }
        }
    }
}