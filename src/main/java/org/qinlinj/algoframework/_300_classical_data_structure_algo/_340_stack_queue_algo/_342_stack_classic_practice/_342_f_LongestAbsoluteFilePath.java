package org.qinlinj.algoframework._300_classical_data_structure_algo._340_stack_queue_algo._342_stack_classic_practice;

/**
 * LeetCode 388: Longest Absolute File Path
 * <p>
 * Problem Description:
 * Suppose we have a file system that stores both files and directories. An absolute path
 * to a file is a string that specifies the location of a file starting from the root directory.
 * <p>
 * Given a string input representing the file system in a text format, return the length of the
 * longest absolute path to a file in the abstract file system. If there is no file in the system,
 * return 0.
 * <p>
 * The input format is:
 * - Each line is either a directory or file name with indentation indicating its depth
 * - A single '\t' character represents one level of indentation
 * - The name of a directory or file contains only letters, digits, and spaces
 * - File names have a '.extension' format
 * <p>
 * Solution Approach:
 * We use a stack to keep track of the current path's directories:
 * <p>
 * 1. Split the input by newline character to get each file/directory
 * 2. For each item, count the level of indentation (number of '\t')
 * 3. Use the stack to only keep track of the current path's directories:
 * - Remove items from the stack until we have the correct parent directory
 * - Add the current directory to the stack
 * 4. When we encounter a file (contains '.'), calculate the path length:
 * - Sum the length of all elements in the stack
 * - Add the number of '/' separators
 * - Update the maximum length
 * <p>
 * Time Complexity: O(n) where n is the length of the input string
 * Space Complexity: O(m) where m is the number of directories in the longest path
 */

import java.util.*;

public class _342_f_LongestAbsoluteFilePath {
    // Example usage
    public static void main(String[] args) {
        _342_f_LongestAbsoluteFilePath solution = new _342_f_LongestAbsoluteFilePath();

        // Example 1: "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
        System.out.println(solution.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        // Output: 20 ("dir/subdir2/file.ext")

        // Example 2: "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
        System.out.println(solution.lengthLongestPath(
                "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
        // Output: 32 ("dir/subdir2/subsubdir2/file2.ext")

        // Example 3: "a"
        System.out.println(solution.lengthLongestPath("a"));
        // Output: 0 (no file in the system)

        // Example 4: "file1.txt\nfile2.txt\nlongfile.txt"
        System.out.println(solution.lengthLongestPath("file1.txt\nfile2.txt\nlongfile.txt"));
        // Output: 12 ("longfile.txt")
    }

    public int lengthLongestPath(String input) {
        // Split the input by newline
        String[] lines = input.split("\n");

        // Stack to store directories in the current path
        Deque<String> stack = new LinkedList<>();

        int maxLength = 0;

        for (String line : lines) {
            // Calculate the level of indentation (number of tabs)
            int level = line.lastIndexOf("\t") + 1;

            // Remove the tabs from the name
            String name = line.substring(level);

            // Pop from stack until we have the correct parent directory
            while (level < stack.size()) {
                stack.removeLast();
            }

            // Add the current directory/file to the stack
            stack.addLast(name);

            // If it's a file (contains a dot), calculate the path length
            if (name.contains(".")) {
                // Calculate the total length of the path
                int pathLength = 0;
                for (String part : stack) {
                    pathLength += part.length();
                }

                // Add the number of '/' separators
                pathLength += stack.size() - 1;

                // Update the maximum length
                maxLength = Math.max(maxLength, pathLength);
            }
        }

        return maxLength;
    }
}