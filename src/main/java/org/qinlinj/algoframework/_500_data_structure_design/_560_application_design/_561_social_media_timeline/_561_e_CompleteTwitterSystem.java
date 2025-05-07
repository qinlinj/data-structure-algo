package org.qinlinj.algoframework._500_data_structure_design._560_application_design._561_social_media_timeline; /**
 * Complete Twitter System Implementation
 * <p>
 * This class provides a complete solution for the LeetCode problem 355: Design Twitter.
 * It combines all the components previously discussed:
 * 1. Twitter, Tweet, and User class definitions
 * 2. API methods for posting tweets, following/unfollowing users
 * 3. The algorithm for merging tweet feeds in chronological order
 * <p>
 * Key aspects of this implementation:
 * - Object-oriented design with appropriate class relationships
 * - Efficient data structures: HashMap for user lookup, HashSet for followed users
 * - Priority queue algorithm for merging multiple sorted tweet lists
 * - Proper handling of edge cases (non-existent users, self-following)
 * <p>
 * This implementation demonstrates how data structures and algorithms can be
 * applied to solve real-world problems like social media feed generation.
 */

import java.util.*;

public class _561_e_CompleteTwitterSystem {

    public static void main(String[] args) {
        // Complete demonstration of Twitter functionality
        Twitter twitter = new Twitter();

        // User activities
        twitter.postTweet(1, 5);
        System.out.println("User 1's feed after posting tweet 5: " + twitter.getNewsFeed(1));

        twitter.follow(1, 2);
        System.out.println("User 1 follows User 2");

        twitter.postTweet(2, 6);
        System.out.println("User 1's feed after User 2 posts tweet 6: " + twitter.getNewsFeed(1));

        twitter.unfollow(1, 2);
        System.out.println("User 1 unfollows User 2");
        System.out.println("User 1's feed after unfollowing: " + twitter.getNewsFeed(1));

        // Additional test cases
        twitter.follow(1, 2);
        twitter.postTweet(2, 7);
        twitter.postTweet(1, 8);
        twitter.postTweet(2, 9);
        twitter.postTweet(1, 10);
        System.out.println("User 1's feed with multiple tweets: " + twitter.getNewsFeed(1));
        // Expected: [10, 9, 8, 7, 6, 5] - newest first
    }

    static class Twitter {
        // Global timestamp for ordering tweets
        private static int timestamp = 0;

        // Map to store userId -> User object mapping
        private Map<Integer, User> userMap;

        /**
         * Initialize Twitter system
         */
        public Twitter() {
            userMap = new HashMap<>();
        }

        /**
         * API Method 1: Post a tweet
         * Time Complexity: O(1)
         */
        public void postTweet(int userId, int tweetId) {
            // Create user if not exists
            if (!userMap.containsKey(userId)) {
                userMap.put(userId, new User(userId));
            }

            // Delegate tweet posting to the User class
            User user = userMap.get(userId);
            user.post(tweetId);
        }

        /**
         * API Method 2: Follow another user
         * Time Complexity: O(1)
         */
        public void follow(int followerId, int followeeId) {
            // Create follower if not exists
            if (!userMap.containsKey(followerId)) {
                userMap.put(followerId, new User(followerId));
            }

            // Create followee if not exists
            if (!userMap.containsKey(followeeId)) {
                userMap.put(followeeId, new User(followeeId));
            }

            // Delegate follow action to User class
            userMap.get(followerId).follow(followeeId);
        }

        /**
         * API Method 3: Unfollow another user
         * Time Complexity: O(1)
         */
        public void unfollow(int followerId, int followeeId) {
            // Do nothing if follower doesn't exist
            if (userMap.containsKey(followerId)) {
                User follower = userMap.get(followerId);
                follower.unfollow(followeeId);
            }
        }

        /**
         * API Method 4: Get news feed
         * Time Complexity: O(N log K) where N is the number of tweets we need (10)
         * and K is the number of users being followed
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

        /**
         * Tweet class - Represents a single tweet in a linked list
         */
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

        /**
         * User class - Manages user data and actions
         */
        private static class User {
            public Set<Integer> followed; // Set of followed user IDs
            public Tweet head;          // Head of this user's tweet list (newest)
            private int id;              // User ID

            public User(int userId) {
                this.id = userId;
                this.followed = new HashSet<>();
                this.head = null;
                // Users automatically follow themselves
                follow(userId);
            }

            // Add a user to followed set
            public void follow(int userId) {
                followed.add(userId);
            }

            // Remove a user from followed set (can't unfollow self)
            public void unfollow(int userId) {
                if (userId != this.id) {
                    followed.remove(userId);
                }
            }

            // Create a new tweet and add to the head of the list
            public void post(int tweetId) {
                Tweet tweet = new Tweet(tweetId, timestamp);
                timestamp++;

                // Insert at the beginning (newest first)
                tweet.next = head;
                head = tweet;
            }
        }
    }
}