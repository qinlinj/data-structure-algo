package org.qinlinj.algoframework._500_data_structure_design._560_application_design._561_social_media_timeline;

import java.util.*;

/**
 * Object-Oriented Design for Twitter System
 * <p>
 * This class demonstrates the object-oriented design approach for the Twitter system.
 * The design consists of three primary classes:
 * <p>
 * 1. Twitter - The main class that provides the API methods and manages user data
 * 2. Tweet - Represents a single tweet with its ID, timestamp and a link to next tweet
 * 3. User - Represents a user with their ID, followed users, and posted tweets
 * <p>
 * Key design decisions:
 * - Tweet objects form a linked list per user, with newest tweets at the head
 * - Users maintain a set of followed user IDs for efficient lookup
 * - Users automatically follow themselves to see their own tweets
 * - A global timestamp ensures tweets can be ordered chronologically
 * <p>
 * This design facilitates efficient implementation of the core algorithm to merge
 * tweets from multiple users in chronological order.
 */
public class _561_b_ObjectOrientedDesign {
    public static void main(String[] args) {
        // This class demonstrates the object-oriented design of the Twitter system
        System.out.println("Twitter Object-Oriented Design Structure:");
        System.out.println("- Twitter class: Main controller with API methods");
        System.out.println("- Tweet class: Stores tweet data and forms linked lists");
        System.out.println("- User class: Manages user data, follows, and tweets");
    }

    static class Twitter {
        // Global timestamp to track tweet chronology
        private static int timestamp = 0;

        // Map to store userId -> User object mapping
        private Map<Integer, User> userMap;

        // Twitter class constructor
        public Twitter() {
            userMap = new HashMap<>();
        }

        // Tweet class definition
        private static class Tweet {
            private int id;      // Tweet content ID
            private int time;    // Timestamp when tweet was posted
            private Tweet next;  // Reference to the next (older) tweet

            // Constructor requires tweet ID and current timestamp
            public Tweet(int id, int time) {
                this.id = id;
                this.time = time;
                this.next = null;
            }
        }

        // User class definition
        private static class User {
            private int id;              // User ID
            private Set<Integer> followed; // Set of followed user IDs
            private Tweet head;          // Head of this user's tweet list (newest)

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

        // API methods will be implemented in the next class
    }
}