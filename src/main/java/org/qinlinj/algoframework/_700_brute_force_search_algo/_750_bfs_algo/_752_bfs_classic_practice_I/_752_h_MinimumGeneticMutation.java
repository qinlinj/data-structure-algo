package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._752_bfs_classic_practice_I; /**
 * Minimum Genetic Mutation (LeetCode 433)
 * --------------------------------------
 * <p>
 * Summary:
 * This problem asks us to find the minimum number of mutations required to transform
 * one genetic string to another. Each mutation changes one character, and all intermediate
 * genetic strings must be valid (present in the given bank).
 * <p>
 * Key Concepts:
 * 1. BFS to find the shortest path from start to end
 * 2. Each valid mutation represents an edge in our graph
 * 3. Nodes are valid gene strings in the bank plus the starting string
 * 4. Each character can only be 'A', 'C', 'G', or 'T'
 * <p>
 * Approach:
 * - Use BFS starting from the start gene
 * - For each gene, generate all possible one-character mutations
 * - Check if each mutation is valid (in the bank)
 * - Track the minimum number of mutations needed
 * <p>
 * Time Complexity: O(L * N * 4) where:
 * - L is the length of the gene string (8 in this problem)
 * - N is the number of genes in the bank
 * - 4 represents the four possible characters (A, C, G, T)
 * <p>
 * Space Complexity: O(N) for the queue and visited set
 */

import java.util.*;

public class _752_h_MinimumGeneticMutation {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _752_h_MinimumGeneticMutation solution = new _752_h_MinimumGeneticMutation();

        // Example 1: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
        String start1 = "AACCGGTT";
        String end1 = "AACCGGTA";
        String[] bank1 = {"AACCGGTA"};

        int result1 = solution.minMutation(start1, end1, bank1);
        System.out.println("Example 1: " + result1); // Should output 1

        // Example 2: start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
        String start2 = "AACCGGTT";
        String end2 = "AAACGGTA";
        String[] bank2 = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};

        int result2 = solution.minMutation(start2, end2, bank2);
        System.out.println("Example 2: " + result2); // Should output 2

        // Example 3: start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
        String start3 = "AAAAACCC";
        String end3 = "AACCCCCC";
        String[] bank3 = {"AAAACCCC", "AAACCCCC", "AACCCCCC"};

        int result3 = solution.minMutation(start3, end3, bank3);
        System.out.println("Example 3: " + result3); // Should output 3

        // Example 4: Impossible mutation
        String start4 = "AACCGGTT";
        String end4 = "AACCGGGG";
        String[] bank4 = {"AACCGGTA"};

        int result4 = solution.minMutation(start4, end4, bank4);
        System.out.println("Example 4 (Impossible): " + result4); // Should output -1
    }

    /**
     * Find the minimum number of mutations required to transform start to end
     * @param start Starting gene string
     * @param end Target gene string
     * @param bank Array of valid gene strings
     * @return Minimum number of mutations or -1 if impossible
     */
    public int minMutation(String start, String end, String[] bank) {
        // Convert bank to a set for O(1) lookups
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        // If the target gene is not in the bank, it's impossible
        if (!bankSet.contains(end)) {
            return -1;
        }

        // Valid gene characters
        char[] validChars = {'A', 'C', 'G', 'T'};

        // BFS implementation
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        int mutations = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                String currentGene = queue.poll();

                // If we've reached the target
                if (currentGene.equals(end)) {
                    return mutations;
                }

                // Try mutating each position
                char[] geneArray = currentGene.toCharArray();

                for (int j = 0; j < geneArray.length; j++) {
                    char originalChar = geneArray[j];

                    // Try all possible mutations at this position
                    for (char newChar : validChars) {
                        if (newChar == originalChar) {
                            continue; // Skip if same character
                        }

                        // Apply mutation
                        geneArray[j] = newChar;
                        String mutatedGene = new String(geneArray);

                        // Check if valid and not visited
                        if (bankSet.contains(mutatedGene) && !visited.contains(mutatedGene)) {
                            queue.offer(mutatedGene);
                            visited.add(mutatedGene);
                        }
                    }

                    // Restore original character for next iteration
                    geneArray[j] = originalChar;
                }
            }

            // Increment mutation count after processing each level
            mutations++;
        }

        // If we exit the loop, no valid path was found
        return -1;
    }

    /**
     * Alternative implementation generating all possible mutations
     */
    public int minMutation2(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        if (!bankSet.contains(end)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        int mutations = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                String currentGene = queue.poll();

                if (currentGene.equals(end)) {
                    return mutations;
                }

                // Generate all possible mutations and check if valid
                for (String nextGene : getAllPossibleMutations(currentGene)) {
                    if (bankSet.contains(nextGene) && !visited.contains(nextGene)) {
                        queue.offer(nextGene);
                        visited.add(nextGene);
                    }
                }
            }

            mutations++;
        }

        return -1;
    }

    /**
     * Helper method to generate all possible one-character mutations
     * @param gene Current gene string
     * @return List of all possible one-character mutations
     */
    private List<String> getAllPossibleMutations(String gene) {
        List<String> mutations = new ArrayList<>();
        char[] geneArray = gene.toCharArray();
        char[] validChars = {'A', 'C', 'G', 'T'};

        for (int i = 0; i < geneArray.length; i++) {
            char originalChar = geneArray[i];

            for (char newChar : validChars) {
                if (newChar != originalChar) {
                    geneArray[i] = newChar;
                    mutations.add(new String(geneArray));
                }
            }

            // Restore original character
            geneArray[i] = originalChar;
        }

        return mutations;
    }
}