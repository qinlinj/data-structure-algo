package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._342_stack_classic_practice;

/**
 * LeetCode 71: Simplify Path
 * <p>
 * Problem Description:
 * Given a string path, which is an absolute path (starting with '/') to a file or directory
 * in a Unix-style file system, convert it to the simplified canonical path.
 * <p>
 * In a Unix-style file system:
 * - A '.' refers to the current directory
 * - A '..' refers to the directory up a level
 * - Multiple consecutive slashes ('/') are treated as a single slash
 * <p>
 * The canonical path should:
 * - Begin with a single slash '/'
 * - Have only a single slash between two directory names
 * - Not end with a trailing '/'
 * - Not include '.' or '..' directories
 * <p>
 * Solution Approach:
 * - Use a stack to track the directory structure
 * - Split the path by '/' and process each part:
 * - Ignore empty parts and '.' (current directory)
 * - For '..' (parent directory), pop from the stack if not empty
 * - For regular directory names, push onto the stack
 * - Build the canonical path from the stack contents
 * <p>
 * Time Complexity: O(n) where n is the length of the path
 * Space Complexity: O(n) for storing the stack
 */

import java.util.*;

public class _342_a_SimplifyPath {
    // Example usage
    public static void main(String[] args) {
        _342_a_SimplifyPath solution = new _342_a_SimplifyPath();

        System.out.println(solution.simplifyPath("/home/")); // Output: "/home"
        System.out.println(solution.simplifyPath("/../")); // Output: "/"
        System.out.println(solution.simplifyPath("/home//foo/")); // Output: "/home/foo"
        System.out.println(solution.simplifyPath("/a/./b/../../c/")); // Output: "/c"
    }

    public String simplifyPath(String path) {
        // Split the path by '/'
        String[] parts = path.split("/");
        Stack<String> stk = new Stack<>();

        // Process each part of the path
        for (String part : parts) {
            // Skip empty parts and current directory marker
            if (part.isEmpty() || part.equals(".")) {
                continue;
            }

            // Handle parent directory
            if (part.equals("..")) {
                if (!stk.isEmpty()) stk.pop();
                continue;
            }

            // Add valid directory name to stack
            stk.push(part);
        }

        // Build the canonical path from stack
        String res = "";
        while (!stk.isEmpty()) {
            res = "/" + stk.pop() + res;
        }

        // Handle edge case of empty path (root directory)
        return res.isEmpty() ? "/" : res;
    }
}
