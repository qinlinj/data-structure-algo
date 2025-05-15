package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._753_bfs_classic_practice_II; /**
 * Accounts Merge (LeetCode 721)
 * ---------------------------
 * <p>
 * Summary:
 * This problem involves merging email accounts that belong to the same person.
 * Each account is a list where the first element is a person's name and the rest are emails.
 * If two accounts share any email, they belong to the same person and should be merged.
 * After merging, each account should have all unique emails sorted in ASCII order.
 * <p>
 * Key Concepts:
 * 1. Graph connectivity problem where emails are nodes
 * 2. BFS to find connected components (all emails belonging to the same person)
 * 3. Using a HashMap to map emails to their respective accounts
 * 4. Handling transitive relationships (A shares with B, B shares with C, so A, B, C are the same person)
 * <p>
 * Approach:
 * - Build a mapping from each email to the accounts containing it
 * - For each unique email, perform BFS to find all connected emails
 * - Group emails by connectivity, forming merged accounts
 * - Sort emails in each merged account
 * <p>
 * Time Complexity: O(NE log(NE)) where N is the number of accounts and E is the max number of emails per account
 * - The log term comes from sorting emails
 * Space Complexity: O(NE) for storing the graph and visited set
 */

import java.util.*;

public class _753_g_AccountsMerge {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _753_g_AccountsMerge solution = new _753_g_AccountsMerge();

        // Example 1
        List<List<String>> accounts1 = new ArrayList<>();
        accounts1.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts1.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts1.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts1.add(Arrays.asList("Mary", "mary@mail.com"));

        List<List<String>> result1 = solution.accountsMerge(accounts1);
        System.out.println("Example 1 (BFS):");
        for (List<String> account : result1) {
            System.out.println(account);
        }

        // Example 2
        List<List<String>> accounts2 = new ArrayList<>();
        accounts2.add(Arrays.asList("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe1@m.co"));
        accounts2.add(Arrays.asList("Kevin", "Kevin3@m.co", "Kevin5@m.co", "Kevin0@m.co"));
        accounts2.add(Arrays.asList("Ethan", "Ethan5@m.co", "Ethan4@m.co", "Ethan0@m.co"));
        accounts2.add(Arrays.asList("Hanzo", "Hanzo3@m.co", "Hanzo1@m.co", "Hanzo0@m.co"));
        accounts2.add(Arrays.asList("Fern", "Fern5@m.co", "Fern1@m.co", "Fern0@m.co"));

        List<List<String>> result2 = solution.accountsMerge(accounts2);
        System.out.println("\nExample 2 (BFS):");
        for (List<String> account : result2) {
            System.out.println(account);
        }

        // Using Union-Find implementation
        List<List<String>> result1UF = solution.accountsMergeUnionFind(accounts1);
        System.out.println("\nExample 1 (Union-Find):");
        for (List<String> account : result1UF) {
            System.out.println(account);
        }
    }

    /**
     * Merge accounts that belong to the same person
     * @param accounts List of accounts where each account is [name, email1, email2, ...]
     * @return List of merged accounts with emails sorted
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // Map each email to all accounts that contain it
        Map<String, List<Integer>> emailToAccounts = new HashMap<>();

        // Build the mapping
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                emailToAccounts.computeIfAbsent(email, k -> new ArrayList<>()).add(i);
            }
        }

        // Perform BFS to find merged accounts
        List<List<String>> result = new ArrayList<>();
        Set<String> visitedEmails = new HashSet<>();

        for (String email : emailToAccounts.keySet()) {
            if (visitedEmails.contains(email)) {
                continue;
            }

            // BFS to find all connected emails
            Set<String> connectedEmails = new HashSet<>();
            Queue<String> queue = new LinkedList<>();

            queue.offer(email);
            visitedEmails.add(email);

            while (!queue.isEmpty()) {
                String currentEmail = queue.poll();
                connectedEmails.add(currentEmail);

                // Get all accounts containing this email
                List<Integer> accountIndices = emailToAccounts.get(currentEmail);

                // Find all other emails in these accounts
                for (int accountIndex : accountIndices) {
                    List<String> account = accounts.get(accountIndex);
                    for (int j = 1; j < account.size(); j++) {
                        String nextEmail = account.get(j);
                        if (!visitedEmails.contains(nextEmail)) {
                            queue.offer(nextEmail);
                            visitedEmails.add(nextEmail);
                        }
                    }
                }
            }

            // Create merged account
            List<String> mergedAccount = new ArrayList<>(connectedEmails);
            Collections.sort(mergedAccount);  // Sort emails

            // Get the account owner's name (from any account containing these emails)
            String accountOwner = accounts.get(emailToAccounts.get(email).get(0)).get(0);
            mergedAccount.add(0, accountOwner);  // Add name at the beginning

            result.add(mergedAccount);
        }

        return result;
    }

    /**
     * Alternative implementation using Union-Find
     */
    public List<List<String>> accountsMergeUnionFind(List<List<String>> accounts) {
        // Map each email to its owner
        Map<String, String> emailToName = new HashMap<>();

        // Map each email to its parent (for union-find)
        Map<String, String> emailParent = new HashMap<>();

        // Map each email to itself initially (for union-find)
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.put(email, name);
                emailParent.put(email, email);  // Each email is its own parent initially
            }
        }

        // Union emails in the same account
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            for (int i = 2; i < account.size(); i++) {
                union(emailParent, firstEmail, account.get(i));
            }
        }

        // Group emails by their root parent
        Map<String, List<String>> components = new HashMap<>();
        for (String email : emailParent.keySet()) {
            String root = find(emailParent, email);
            components.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
        }

        // Build the final result
        List<List<String>> result = new ArrayList<>();
        for (List<String> emails : components.values()) {
            Collections.sort(emails);
            emails.add(0, emailToName.get(emails.get(0)));  // Add name at the beginning
            result.add(emails);
        }

        return result;
    }

    /**
     * Find operation for Union-Find
     */
    private String find(Map<String, String> parent, String x) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent, parent.get(x)));
        }
        return parent.get(x);
    }

    /**
     * Union operation for Union-Find
     */
    private void union(Map<String, String> parent, String x, String y) {
        parent.put(find(parent, x), find(parent, y));
    }
}