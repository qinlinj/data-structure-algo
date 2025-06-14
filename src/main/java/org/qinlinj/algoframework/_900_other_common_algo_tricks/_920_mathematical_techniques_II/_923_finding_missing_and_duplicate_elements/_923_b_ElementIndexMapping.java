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
}
