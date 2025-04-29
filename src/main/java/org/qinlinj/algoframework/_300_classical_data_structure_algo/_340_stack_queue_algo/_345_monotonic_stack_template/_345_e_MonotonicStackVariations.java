package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._345_monotonic_stack_template;

/**
 * Monotonic Stack - Variations and Extensions
 * <p>
 * This class explores different variations of the monotonic stack pattern:
 * 1. Next Greater Element (default template covered earlier)
 * 2. Previous Greater Element
 * 3. Next Smaller Element
 * 4. Previous Smaller Element
 * <p>
 * For each variation, the core monotonic stack logic remains the same, but:
 * - The direction of iteration may change (left-to-right vs right-to-left)
 * - The comparison operator may change (greater vs smaller)
 * - The position to look for elements may change (next vs previous)
 * <p>
 * These variants enable solving a wide range of problems involving finding
 * elements with specific relative properties.
 * <p>
 * Time Complexity: O(n) for all variations
 * Space Complexity: O(n) for the stack in all variations
 */

import java.util.*;

public class _345_e_MonotonicStackVariations {

    /**
     * Main method to test all the variations
     */
    public static void main(String[] args) {
        _345_e_MonotonicStackVariations solution = new _345_e_MonotonicStackVariations();

        // Example array
        int[] nums = {4, 2, 3, 5, 1};

        // Print all variations
        System.out.println("EXAMPLE ARRAY: " + Arrays.toString(nums));
        System.out.println("Next Greater Elements: " +
                Arrays.toString(solution.nextGreaterElement(nums)));
        System.out.println("Previous Greater Elements: " +
                Arrays.toString(solution.previousGreaterElement(nums)));
        System.out.println("Next Smaller Elements: " +
                Arrays.toString(solution.nextSmallerElement(nums)));
        System.out.println("Previous Smaller Elements: " +
                Arrays.toString(solution.previousSmallerElement(nums)));

        System.out.println("\n----- DETAILED DEMONSTRATION -----");
        solution.demonstrateVariations(nums);

        System.out.println("\n----- VARIATION PATTERNS -----");
        solution.explainVariationPatterns();

        System.out.println("\n----- APPLICATION TABLE -----");
        solution.showApplicationTable();
    }

    /**
     * 1. Next Greater Element (Already covered in previous classes)
     * For each element, find the first greater element to its right
     */
    public int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process right to left
        for (int i = n - 1; i >= 0; i--) {
            // Remove smaller or equal elements
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }

            // Set result
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current element
            stack.push(nums[i]);
        }

        return result;
    }

    /**
     * 2. Previous Greater Element
     * For each element, find the first greater element to its left
     */
    public int[] previousGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process left to right
        for (int i = 0; i < n; i++) {
            // Remove smaller or equal elements
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }

            // Set result
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current element
            stack.push(nums[i]);
        }

        return result;
    }

    /**
     * 3. Next Smaller Element
     * For each element, find the first smaller element to its right
     */
    public int[] nextSmallerElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process right to left
        for (int i = n - 1; i >= 0; i--) {
            // Remove larger or equal elements
            while (!stack.isEmpty() && stack.peek() >= nums[i]) {
                stack.pop();
            }

            // Set result
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current element
            stack.push(nums[i]);
        }

        return result;
    }

    /**
     * 4. Previous Smaller Element
     * For each element, find the first smaller element to its left
     */
    public int[] previousSmallerElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Process left to right
        for (int i = 0; i < n; i++) {
            // Remove larger or equal elements
            while (!stack.isEmpty() && stack.peek() >= nums[i]) {
                stack.pop();
            }

            // Set result
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current element
            stack.push(nums[i]);
        }

        return result;
    }

    /**
     * Demonstrates all variations with a step-by-step explanation
     */
    public void demonstrateVariations(int[] nums) {
        System.out.println("Input array: " + Arrays.toString(nums));

        // Demonstrate all variations
        int[] nextGreater = nextGreaterElement(nums);
        int[] prevGreater = previousGreaterElement(nums);
        int[] nextSmaller = nextSmallerElement(nums);
        int[] prevSmaller = previousSmallerElement(nums);

        System.out.println("\nResults for all variations:");
        System.out.println("Next Greater: " + Arrays.toString(nextGreater));
        System.out.println("Previous Greater: " + Arrays.toString(prevGreater));
        System.out.println("Next Smaller: " + Arrays.toString(nextSmaller));
        System.out.println("Previous Smaller: " + Arrays.toString(prevSmaller));

        // Visual explanation for a specific element
        int index = 2; // Example: look at the element at index 2
        if (index < nums.length) {
            System.out.println("\nDetailed view for element at index " + index + " (value: " + nums[index] + "):");
            System.out.println("  Next Greater: " + (nextGreater[index] == -1 ? "None" : nextGreater[index]));
            System.out.println("  Previous Greater: " + (prevGreater[index] == -1 ? "None" : prevGreater[index]));
            System.out.println("  Next Smaller: " + (nextSmaller[index] == -1 ? "None" : nextSmaller[index]));
            System.out.println("  Previous Smaller: " + (prevSmaller[index] == -1 ? "None" : prevSmaller[index]));
        }
    }

    /**
     * Mapping of stack variation changes
     */
    public void explainVariationPatterns() {
        System.out.println("Monotonic Stack Variation Patterns:");
        System.out.println("----------------------------------");
        System.out.println("1. Next Greater Element:");
        System.out.println("   - Direction: Right to Left");
        System.out.println("   - Comparison: Remove elements <= current");
        System.out.println("   - Stack Order: Decreasing (top to bottom)\n");

        System.out.println("2. Previous Greater Element:");
        System.out.println("   - Direction: Left to Right");
        System.out.println("   - Comparison: Remove elements <= current");
        System.out.println("   - Stack Order: Decreasing (top to bottom)\n");

        System.out.println("3. Next Smaller Element:");
        System.out.println("   - Direction: Right to Left");
        System.out.println("   - Comparison: Remove elements >= current");
        System.out.println("   - Stack Order: Increasing (top to bottom)\n");

        System.out.println("4. Previous Smaller Element:");
        System.out.println("   - Direction: Left to Right");
        System.out.println("   - Comparison: Remove elements >= current");
        System.out.println("   - Stack Order: Increasing (top to bottom)\n");

        System.out.println("General Pattern:");
        System.out.println("- For 'Next' elements: Iterate Right to Left");
        System.out.println("- For 'Previous' elements: Iterate Left to Right");
        System.out.println("- For 'Greater' elements: Remove smaller or equal");
        System.out.println("- For 'Smaller' elements: Remove greater or equal");
    }

    /**
     * Table summarizing when to use each variation
     */
    public void showApplicationTable() {
        System.out.println("Common Applications of Monotonic Stack Variations:");
        System.out.println("-------------------------------------------------");
        System.out.println("| Variation             | Common Applications                             |");
        System.out.println("|-----------------------|------------------------------------------------|");
        System.out.println("| Next Greater Element  | - Stock price span                              |");
        System.out.println("|                       | - Building with sunlight from east              |");
        System.out.println("|                       | - Temperature forecast (LeetCode 739)           |");
        System.out.println("|-----------------------|------------------------------------------------|");
        System.out.println("| Previous Greater      | - Building with sunlight from west              |");
        System.out.println("|                       | - Largest Rectangle in Histogram                |");
        System.out.println("|                       | - Maximum width ramp                            |");
        System.out.println("|-----------------------|------------------------------------------------|");
        System.out.println("| Next Smaller Element  | - Room needed to place an element               |");
        System.out.println("|                       | - Minimum cost tree from leaf values            |");
        System.out.println("|                       | - Find first smaller element on right           |");
        System.out.println("|-----------------------|------------------------------------------------|");
        System.out.println("| Previous Smaller      | - Calculating water trap volumes                |");
        System.out.println("|                       | - Maximum area under histogram                  |");
        System.out.println("|                       | - Creating intervals for parsing                |");
    }
}
