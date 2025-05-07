package org.qinlinj.algoframework._500_data_structure_design._560_application_design._561_social_media_timeline;

import java.util.*;

/**
 * Twitter API Implementation
 * <p>
 * This class implements the four main API methods required by the Twitter system:
 * 1. postTweet - Allows a user to post a new tweet
 * 2. follow - Allows a user to follow another user
 * 3. unfollow - Allows a user to unfollow another user
 * 4. getNewsFeed - Returns the 10 most recent tweets from followed users
 * <p>
 * Implementation details:
 * - Each method handles the case where users might not exist yet
 * - The system automatically creates new users when they are referenced
 * - The most complex method is getNewsFeed, which will be implemented separately
 * <p>
 * These methods demonstrate proper encapsulation where the Twitter class delegates
 * user-specific actions to the User class when appropriate.
 */
public class _561_c_APIImplementation {

    static class Twitter {
        private static int timestamp = 0;
        private Map<Integer, User> userMap;

        public Twitter() {
            userMap = new HashMap<>();
        }

        /* API Method 1: Post a tweet */
        public void postTweet(int userId, int tweetId) {
            // Create user if not exists
            if (!userMap.containsKey(userId)) {
                userMap.put(userId, new User(userId));
            }

            // Delegate tweet posting to the User class
            User user = userMap.get(userId);
            user.post(tweetId);
        }

        /* API Method 2: Follow a user */
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

        /* API Method 3: Unfollow a user */
        public void unfollow(int followerId, int followeeId) {
            // Do nothing if follower doesn't exist
            if (userMap.containsKey(followerId)) {
                User follower = userMap.get(followerId);
                follower.unfollow(followeeId);
            }
        }

        /* API Method 4: Get news feed */
        public List<Integer> getNewsFeed(int userId) {
            // This will be implemented in the next class
            return new ArrayList<>();
        }

        // Internal Tweet and User classes as defined before
        private static class Tweet {
            private int id;
            private int time;
            private Tweet next;

            public Tweet(int id, int time) {
                this.id = id;
                this.time = time;
                this.next = null;
            }
        }

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