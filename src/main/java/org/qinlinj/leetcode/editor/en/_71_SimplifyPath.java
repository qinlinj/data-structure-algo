package org.qinlinj.leetcode.editor.en;

import java.util.*;

// [71] Simplify Path
public class _71_SimplifyPath {
    public static void main(String[] args) {
        Solution solution = new _71_SimplifyPath().new Solution();
        String s = "/home/user/Documents/../Pictures";
        System.out.println(solution.simplifyPath(s));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String simplifyPath(String path) {
            String[] pathSplitList = path.split("/");
//            System.out.println(Arrays.toString(pathSplitList));
            Stack<String> stack = new Stack<>();

            for (String s : pathSplitList) {
                if (s.equals("..")) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                    continue;
                } else if (s.equals(".") || s.isEmpty()) {
                    continue;
                } else {
                    stack.add(s);
                }
            }
            StringBuilder sb = new StringBuilder();
            for (String s : stack) {
                sb.append("/");
                sb.append(s);
            }
            return sb.isEmpty() ? "/" : sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}


//You are given an absolute path for a Unix-style file system, which always
//begins with a slash '/'. Your task is to transform this absolute path into its 
//simplified canonical path. 
//
// The rules of a Unix-style file system are as follows: 
//
// 
// A single period '.' represents the current directory. 
// A double period '..' represents the previous/parent directory. 
// Multiple consecutive slashes such as '//' and '///' are treated as a single 
//slash '/'. 
// Any sequence of periods that does not match the rules above should be 
//treated as a valid directory or file name. For example, '...' and '....' are valid 
//directory or file names. 
// 
//
// The simplified canonical path should follow these rules: 
//
// 
// The path must start with a single slash '/'. 
// Directories within the path must be separated by exactly one slash '/'. 
// The path must not end with a slash '/', unless it is the root directory. 
// The path must not have any single or double periods ('.' and '..') used to 
//denote current or parent directories. 
// 
//
// Return the simplified canonical path. 
//
// 
// Example 1: 
//
// 
// Input: path = "/home/" 
// 
//
// Output: "/home" 
//
// Explanation: 
//
// The trailing slash should be removed. 
//
// Example 2: 
//
// 
// Input: path = "/home//foo/" 
// 
//
// Output: "/home/foo" 
//
// Explanation: 
//
// Multiple consecutive slashes are replaced by a single one. 
//
// Example 3: 
//
// 
// Input: path = "/home/user/Documents/../Pictures" 
// 
//
// Output: "/home/user/Pictures" 
//
// Explanation: 
//
// A double period ".." refers to the directory up a level (the parent 
//directory). 
//
// Example 4: 
//
// 
// Input: path = "/../" 
// 
//
// Output: "/" 
//
// Explanation: 
//
// Going one level up from the root directory is not possible. 
//
// Example 5: 
//
// 
// Input: path = "/.../a/../b/c/../d/./" 
// 
//
// Output: "/.../b/d" 
//
// Explanation: 
//
// "..." is a valid name for a directory in this problem. 
//
// 
// Constraints: 
//
// 
// 1 <= path.length <= 3000 
// path consists of English letters, digits, period '.', slash '/' or '_'. 
// path is a valid absolute Unix path. 
// 
//
// Related TopicsString | Stack 
//
// 👍 5940, 👎 1346bug 反馈 | 使用指南 | 更多配套插件 
//
//
//
//
