package org.qinlinj.algoframework._900_other_common_algo_tricks._920_mathematical_techniques_II._923_finding_missing_and_duplicate_elements;

import java.util.*;

public class _923_b_ElementIndexMapping {
    /**
     * Demonstrates the element-index correspondence concept
     */
    public static void demonstrateMapping() {
        System.out.println("=== Element-Index Mapping Concept ===");

        // Perfect case: no duplicates or missing elements
        int[] perfect = {0, 1, 2, 3};  // Using 0..N-1 for simplicity
        System.out.println("Perfect array (0..N-1): " + Arrays.toString(perfect));
        System.out.println("Element -> Index mapping:");
        for (int i = 0; i < perfect.length; i++) {
            System.out.println("  Element " + perfect[i] + " maps to index " + perfect[i]);
        }

        System.out.println("\nProblematic case with duplicate and missing:");
        int[] problematic = {0, 1, 1, 3};  // 1 is duplicate, 2 is missing
        System.out.println("Problematic array: " + Arrays.toString(problematic));
        System.out.println("Element -> Index mapping:");

        boolean[] visited = new boolean[problematic.length];
        for (int i = 0; i < problematic.length; i++) {
            int element = problematic[i];
            System.out.print("  Element " + element + " tries to map to index " + element);

            if (visited[element]) {
                System.out.println(" -> CONFLICT! Index already visited (DUPLICATE)");
            } else {
                visited[element] = true;
                System.out.println(" -> OK, first visit");
            }
        }

        System.out.println("\nIndex analysis:");
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                System.out.println("  Index " + i + " was never visited -> MISSING element " + i);
            }
        }
    }

    /**
     * Shows how negative marking works to detect visited indices
     */
    public static void demonstrateNegativeMarking() {
        System.out.println("\n=== Negative Marking Technique ===");

        int[] nums = {0, 1, 1, 3};  // Simplified: 0..N-1
        int[] original = nums.clone();

        System.out.println("Original array: " + Arrays.toString(original));
        System.out.println("Processing each element to mark visited indices:");

        int duplicate = -1;

        for (int i = 0; i < nums.length; i++) {
            int element = Math.abs(nums[i]);  // Get original value (might be negative)
            System.out.print("Step " + (i + 1) + ": Processing element " + element);

            if (nums[element] < 0) {
                // Already negative = already visited = duplicate found
                duplicate = element;
                System.out.println(" -> Index " + element + " already marked negative -> DUPLICATE!");
            } else {
                // First time visiting this index, mark it negative
                nums[element] *= -1;
                System.out.println(" -> Marking index " + element + " as visited (making negative)");
            }

            System.out.println("     Array state: " + Arrays.toString(nums));
        }

        System.out.println("\nFinding missing element:");
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                System.out.println("Index " + i + " is still positive -> Element " + i + " is MISSING");
            }
        }

        System.out.println("Duplicate: " + duplicate);
    }

    /**
     * Converts the concept from [0..N-1] to [1..N] as required by the actual problem
     */
    public static void explainConversion() {
        System.out.println("\n=== Converting from [0..N-1] to [1..N] ===");

        System.out.println("Problem requirements:");
        System.out.println("- Elements should be in range [1..N]");
        System.out.println("- Array indices are [0..N-1]");
        System.out.println("- Mapping: element 'x' -> index 'x-1'");

        int[] nums = {1, 2, 2, 4};  // Actual problem format
        System.out.println("\nExample: " + Arrays.toString(nums));

        System.out.println("Element-to-Index mapping:");
        for (int i = 0; i < nums.length; i++) {
            int element = nums[i];
            int targetIndex = element - 1;  // Convert to 0-based index
            System.out.println("  Element " + element + " -> Index " + targetIndex);
        }

        System.out.println("\nWhy elements must start from 1 (not 0):");
        System.out.println("- If element 0 exists, its negative is still 0");
        System.out.println("- Cannot distinguish between 'visited' and 'unvisited' for 0");
        System.out.println("- Elements must be non-zero for negative marking to work");
    }

}
