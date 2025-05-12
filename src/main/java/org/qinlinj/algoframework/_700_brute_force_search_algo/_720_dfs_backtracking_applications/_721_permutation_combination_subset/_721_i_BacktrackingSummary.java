package org.qinlinj.algoframework._700_brute_force_search_algo._720_dfs_backtracking_applications._721_permutation_combination_subset;

/**
 * BACKTRACKING SOLUTIONS SUMMARY
 * <p>
 * This class provides a comprehensive summary of backtracking patterns for
 * solving subset, combination, and permutation problems with their variations.
 * <p>
 * TYPES OF PROBLEMS:
 * <p>
 * 1. ELEMENT UNIQUENESS AND REUSE PATTERNS:
 * - Elements without duplicates, no reuse (basic form)
 * - Elements with duplicates, no reuse
 * - Elements without duplicates, with reuse
 * <p>
 * 2. PROBLEM PATTERNS:
 * - Subset problems: Generate all possible subsets
 * - Combination problems: Generate subsets of specific size k
 * - Permutation problems: Generate all possible arrangements
 * <p>
 * BACKTRACKING TEMPLATES:
 * <p>
 * 1. SUBSET/COMBINATION TEMPLATES:
 * - Without duplicates, no reuse:
 * - Use start parameter: backtrack(nums, i + 1)
 * - With duplicates, no reuse:
 * - Sort first
 * - Skip duplicates: if (i > start && nums[i] == nums[i-1]) continue;
 * - Without duplicates, with reuse:
 * - Reuse current element: backtrack(nums, i)
 * <p>
 * 2. PERMUTATION TEMPLATES:
 * - Without duplicates, no reuse:
 * - Use boolean[] used array to avoid reusing elements
 * - With duplicates, no reuse:
 * - Sort first
 * - Use used array and skip logic: if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
 * - Without duplicates, with reuse:
 * - No used array, allow any element at each position
 */
public class _721_i_BacktrackingSummary {

    /**
     * This is a summary class with backtracking code templates.
     * Please refer to specific implementation classes for complete solutions.
     */
    public static void main(String[] args) {
        System.out.println("BACKTRACKING SOLUTIONS SUMMARY");
        System.out.println("===============================");

        // Template 1: Subsets (Elements without duplicates, no reuse)
        System.out.println("\n1. SUBSETS (Elements without duplicates, no reuse):");
        System.out.println("void backtrack(int[] nums, int start) {");
        System.out.println("    // Add current subset to result");
        System.out.println("    res.add(new LinkedList<>(track));");
        System.out.println("    ");
        System.out.println("    for (int i = start; i < nums.length; i++) {");
        System.out.println("        // Make choice");
        System.out.println("        track.addLast(nums[i]);");
        System.out.println("        // Explore with i+1 to avoid reuse");
        System.out.println("        backtrack(nums, i + 1);");
        System.out.println("        // Undo choice");
        System.out.println("        track.removeLast();");
        System.out.println("    }");
        System.out.println("}");

        // Template 2: Combinations (Elements without duplicates, no reuse)
        System.out.println("\n2. COMBINATIONS (Elements without duplicates, no reuse):");
        System.out.println("void backtrack(int start, int n, int k) {");
        System.out.println("    // Base case: reached size k");
        System.out.println("    if (track.size() == k) {");
        System.out.println("        res.add(new LinkedList<>(track));");
        System.out.println("        return;");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    for (int i = start; i <= n; i++) {");
        System.out.println("        track.addLast(i);");
        System.out.println("        backtrack(i + 1, n, k);");
        System.out.println("        track.removeLast();");
        System.out.println("    }");
        System.out.println("}");

        // Template 3: Permutations (Elements without duplicates, no reuse)
        System.out.println("\n3. PERMUTATIONS (Elements without duplicates, no reuse):");
        System.out.println("void backtrack(int[] nums) {");
        System.out.println("    // Base case: reached full length");
        System.out.println("    if (track.size() == nums.length) {");
        System.out.println("        res.add(new LinkedList<>(track));");
        System.out.println("        return;");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    for (int i = 0; i < nums.length; i++) {");
        System.out.println("        // Skip used elements");
        System.out.println("        if (used[i]) continue;");
        System.out.println("        ");
        System.out.println("        used[i] = true;");
        System.out.println("        track.addLast(nums[i]);");
        System.out.println("        backtrack(nums);");
        System.out.println("        track.removeLast();");
        System.out.println("        used[i] = false;");
        System.out.println("    }");
        System.out.println("}");

        // Template 4: Subsets with Duplicates (Elements with duplicates, no reuse)
        System.out.println("\n4. SUBSETS WITH DUPLICATES (Elements with duplicates, no reuse):");
        System.out.println("// First sort the array");
        System.out.println("Arrays.sort(nums);");
        System.out.println("void backtrack(int[] nums, int start) {");
        System.out.println("    res.add(new LinkedList<>(track));");
        System.out.println("    ");
        System.out.println("    for (int i = start; i < nums.length; i++) {");
        System.out.println("        // Skip duplicates at same level");
        System.out.println("        if (i > start && nums[i] == nums[i-1]) continue;");
        System.out.println("        ");
        System.out.println("        track.addLast(nums[i]);");
        System.out.println("        backtrack(nums, i + 1);");
        System.out.println("        track.removeLast();");
        System.out.println("    }");
        System.out.println("}");

        // Template 5: Permutations with Duplicates (Elements with duplicates, no reuse)
        System.out.println("\n5. PERMUTATIONS WITH DUPLICATES (Elements with duplicates, no reuse):");
        System.out.println("// First sort the array");
        System.out.println("Arrays.sort(nums);");
        System.out.println("void backtrack(int[] nums) {");
        System.out.println("    if (track.size() == nums.length) {");
        System.out.println("        res.add(new LinkedList<>(track));");
        System.out.println("        return;");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    for (int i = 0; i < nums.length; i++) {");
        System.out.println("        if (used[i]) continue;");
        System.out.println("        ");
        System.out.println("        // Key pruning logic for duplicates");
        System.out.println("        if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;");
        System.out.println("        ");
        System.out.println("        used[i] = true;");
        System.out.println("        track.addLast(nums[i]);");
        System.out.println("        backtrack(nums);");
        System.out.println("        track.removeLast();");
        System.out.println("        used[i] = false;");
        System.out.println("    }");
        System.out.println("}");

        // Template 6: Combination Sum with Reuse (Elements without duplicates, with reuse)
        System.out.println("\n6. COMBINATION SUM WITH REUSE (Elements without duplicates, with reuse):");
        System.out.println("void backtrack(int[] nums, int start, int target) {");
        System.out.println("    if (trackSum == target) {");
        System.out.println("        res.add(new LinkedList<>(track));");
        System.out.println("        return;");
        System.out.println("    }");
        System.out.println("    if (trackSum > target) return;");
        System.out.println("    ");
        System.out.println("    for (int i = start; i < nums.length; i++) {");
        System.out.println("        trackSum += nums[i];");
        System.out.println("        track.add(nums[i]);");
        System.out.println("        // Use i instead of i+1 to allow reuse");
        System.out.println("        backtrack(nums, i, target);");
        System.out.println("        track.removeLast();");
        System.out.println("        trackSum -= nums[i];");
        System.out.println("    }");
        System.out.println("}");

        // Template 7: Permutations with Reuse (Elements without duplicates, with reuse)
        System.out.println("\n7. PERMUTATIONS WITH REUSE (Elements without duplicates, with reuse):");
        System.out.println("void backtrack(int[] nums) {");
        System.out.println("    if (track.size() == nums.length) {");
        System.out.println("        res.add(new LinkedList<>(track));");
        System.out.println("        return;");
        System.out.println("    }");
        System.out.println("    ");
        System.out.println("    for (int i = 0; i < nums.length; i++) {");
        System.out.println("        // No used[] check needed - elements can be reused");
        System.out.println("        track.add(nums[i]);");
        System.out.println("        backtrack(nums);");
        System.out.println("        track.removeLast();");
        System.out.println("    }");
        System.out.println("}");

        System.out.println("\nFOR DETAILED IMPLEMENTATIONS, REFER TO THE SPECIFIC CLASSES.");
    }
}