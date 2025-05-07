package org.qinlinj.algoframework._500_data_structure_design._560_application_design._561_social_media_timeline;

import java.util.*;

/**
 * LeetCode Problem 355: Design Twitter
 * <p>
 * This problem combines object-oriented design with the algorithm for merging multiple sorted lists.
 * The task is to implement a simplified version of Twitter with the following features:
 * <p>
 * 1. Users can post tweets
 * 2. Users can follow/unfollow other users
 * 3. Users can view a feed containing the most recent tweets from followed users
 * <p>
 * The main algorithmic challenge is in the getNewsFeed method, which needs to:
 * - Retrieve tweets from all followed users (including self)
 * - Merge these tweets in time order (newest first)
 * - Return only the 10 most recent tweets
 * <p>
 * This demonstrates a practical application of the "merge k sorted lists" algorithm
 * combined with basic object-oriented design principles.
 */
public class _561_a_TwitterIntroduction {
    public static void main(String[] args) {
        // Example usage - this matches the example in the problem description
        Twitter twitter = new Twitter();

        twitter.postTweet(1, 5);
        // User 1 posts a tweet with id 5

        System.out.println(twitter.getNewsFeed(1));
        // Returns [5] - user sees their own tweet

        twitter.follow(1, 2);
        // User 1 follows user 2

        twitter.postTweet(2, 6);
        // User 2 posts a tweet with id 6

        System.out.println(twitter.getNewsFeed(1));
        // Returns [6, 5] - most recent tweets from all followed users (including self)

        twitter.unfollow(1, 2);
        // User 1 unfollows user 2

        System.out.println(twitter.getNewsFeed(1));
        // Returns [5] - only user 1's own tweet remains
    }

    // The Twitter class implementation will be shown in the next classes
    static class Twitter {
        public void postTweet(int userId, int tweetId) {
        }

        public List<Integer> getNewsFeed(int userId) {
            return new ArrayList<>();
        }

        public void follow(int followerId, int followeeId) {
        }

        public void unfollow(int followerId, int followeeId) {
        }
    }
}