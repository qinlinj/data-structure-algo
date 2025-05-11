package org.qinlinj.algoframework._700_brute_force_search_algo._710_dfs_backtracking_framework._711_backtracking_framework;

import java.util.*;

public class _711_a_BacktrackingIntroduction {
    /**
     * Generic backtracking algorithm framework
     *
     * @param path    The current path (choices made so far)
     * @param choices The available choices at this point
     */
    public void backtrack(List<Object> path, List<Object> choices) {
        // Base case: If end condition is met
        if (endConditionMet(path)) {
            // Add the current path to the result
            saveResult(path);
            return;
        }

        // Try each available choice
        for (Object choice : choices) {
            // 1. Make a choice
            path.add(choice);
            // Remove the choice from available options to avoid repetition
            choices.remove(choice);

            // 2. Explore further with the current choice
            backtrack(path, choices);

            // 3. Undo the choice (backtrack)
            path.remove(path.size() - 1);
            // Add the choice back to available options
            choices.add(choice);
        }
    }
}
