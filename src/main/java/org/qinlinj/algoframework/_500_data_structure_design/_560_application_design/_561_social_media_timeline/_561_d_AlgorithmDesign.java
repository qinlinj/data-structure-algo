package org.qinlinj.algoframework._500_data_structure_design._560_application_design._561_social_media_timeline; /**
 * Twitter Algorithm Design - Merging Feeds
 * <p>
 * This class implements the core algorithm for the getNewsFeed method, which:
 * 1. Retrieves the most recent tweets from all followed users
 * 2. Merges these tweets in chronological order (newest first)
 * 3. Returns at most 10 tweets
 * <p>
 * The key algorithm is "Merge K Sorted Lists" implemented using a priority queue:
 * - Each user has their own chronologically sorted tweet list (newest first)
 * - We need to merge these lists maintaining the sort order
 * - A priority queue allows efficient selection of the newest tweet across all lists
 * <p>
 * Implementation details:
 * - Initialize the priority queue with the head of each user's tweet list
 * - Sort by timestamp in descending order (newest first)
 * - Repeatedly extract the newest tweet and add its next tweet to the queue
 * - Stop after collecting 10 tweets or when the queue is empty
 * <p>
 * Time complexity: O(N log K) where:
 * - N is the number of tweets we need (10 in this case)
 * - K is the number of users being followed
 */

import java.util.*;

public class _561_d_AlgorithmDesign {

    public static void main(String[] args) {
        // Demonstrate the complete Twitter system with algorithm

        // Create a new Twitter instance
        Twitter twitter = new Twitter();

        // User 1 posts a tweet
        twitter.postTweet(1, 5);
        System.out.println("User 1's feed: " + twitter.getNewsFeed(1));  // [5]

        // User 1 follows user 2
        twitter.follow(1, 2);

        // User 2 posts a tweet
        twitter.postTweet(2, 6);
        System.out.println("User 1's feed after user 2 posts: " + twitter.getNewsFeed(1));  // [6, 5]

        // User 1 unfollows user 2
        twitter.unfollow(1, 2);
        System.out.println("User 1's feed after unfollowing user 2: " + twitter.getNewsFeed(1));  // [5]
    }

    static class Twitter {
        private static int timestamp = 0;
        private Map<Integer, User> userMap;

        public Twitter() {
            userMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            if (!userMap.containsKey(userId)) {
                userMap.put(userId, new User(userId));
            }
            userMap.get(userId).post(tweetId);
        }

        public void follow(int followerId, int followeeId) {
            if (!userMap.containsKey(followerId)) {
                userMap.put(followerId, new User(followerId));
            }
            if (!userMap.containsKey(followeeId)) {
                userMap.put(followeeId, new User(followeeId));
            }
            userMap.get(followerId).follow(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (userMap.containsKey(followerId)) {
                User follower = userMap.get(followerId);
                follower.unfollow(followeeId);
            }
        }

        /**
         * Core algorithm implementation: Get news feed for a user
         * Merges multiple sorted tweet lists into a single chronological feed
         */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> result = new ArrayList<>();

            // Return empty list if user doesn't exist
            if (!userMap.containsKey(userId)) {
                return result;
            }

            // Get set of all followed users
            Set<Integer> followedUsers = userMap.get(userId).followed;

            // Create priority queue sorted by tweet timestamp (newest first)
            PriorityQueue<Tweet> pq = new PriorityQueue<>(
                    followedUsers.size(),
                    (a, b) -> (b.time - a.time)  // Sort by time in descending order
            );

            // Initialize the queue with the newest tweet from each followed user
            for (int followedId : followedUsers) {
                Tweet userHead = userMap.get(followedId).head;
                if (userHead != null) {
                    pq.add(userHead);
                }
            }

            // Extract tweets in order, adding the next tweet from the same user
            while (!pq.isEmpty() && result.size() < 10) {
                // Get the newest tweet
                Tweet tweet = pq.poll();
                result.add(tweet.id);

                // If this user has more tweets, add the next one to the queue
                if (tweet.next != null) {
                    pq.add(tweet.next);
                }
            }

            return result;
        }

        // Tweet class definition
        private static class Tweet {
            private int id;      // Tweet content ID
            private int time;    // Timestamp when tweet was posted
            private Tweet next;  // Reference to the next (older) tweet

            public Tweet(int id, int time) {
                this.id = id;
                this.time = time;
                this.next = null;
            }
        }

        // User class definition
        private static class User {
            public Set<Integer> followed;
            public Tweet head;
            private int id;

            public User(int userId) {
                this.id = userId;
                this.followed = new HashSet<>();
                this.head = null;
                follow(userId);
            }

            public void follow(int userId) {
                followed.add(userId);
            }

            public void unfollow(int userId) {
                if (userId != this.id) {
                    followed.remove(userId);
                }
            }

            public void post(int tweetId) {
                Tweet tweet = new Tweet(tweetId, timestamp);
                timestamp++;
                tweet.next = head;
                head = tweet;
            }
        }
    }
}