package org.qinlinj.nonlinear.highlevel.skiplist;

/**
 * Test class for the SkipList implementation.
 * This class demonstrates the operations of a SkipList and visualizes its structure after each operation.
 */
public class SkipListTest {

    /**
     * Prints a visual representation of the SkipList structure.
     * <p>
     * Example visualization of a SkipList with 3 levels and elements 3, 6, 9:
     * <p>
     * Level 2:  head ------------------------> 9 -------> null
     * Level 1:  head -----------> 6 --------> 9 -------> null
     * Level 0:  head --> 3 -----> 6 ---------> 9 -------> null
     *
     * @param list The SkipList to visualize
     */
    @SuppressWarnings("unchecked")
    public static void printSkipList(SkipList<Integer> list) {
        System.out.println("\n=== SkipList Structure ===");

        // Accessing private fields using reflection for visualization purposes
        try {
            java.lang.reflect.Field levelCountField = SkipList.class.getDeclaredField("levelCount");
            levelCountField.setAccessible(true);
            int levelCount = (int) levelCountField.get(list);

            java.lang.reflect.Field dummyHeadField = SkipList.class.getDeclaredField("dummyHead");
            dummyHeadField.setAccessible(true);
            Object dummyHead = dummyHeadField.get(list);

            java.lang.reflect.Field nextNodesField = dummyHead.getClass().getDeclaredField("nextNodes");
            nextNodesField.setAccessible(true);
            Object[] headNextNodes = (Object[]) nextNodesField.get(dummyHead);

            // Print the skip list level by level, starting from the highest level
            for (int i = levelCount - 1; i >= 0; i--) {
                System.out.print("Level " + i + ":  head ");

                // Get the first node at this level
                Object currentNode = headNextNodes[i];

                // Track previous node's value for spacing
                Integer prevValue = null;

                // Traverse the level
                while (currentNode != null) {
                    // Get the data from current node
                    java.lang.reflect.Field dataField = currentNode.getClass().getDeclaredField("data");
                    dataField.setAccessible(true);
                    Integer data = (Integer) dataField.get(currentNode);

                    // Calculate spacing based on the gap between values
                    String spacing = "";
                    if (prevValue == null) {
                        // First node spacing from head
                        spacing = "-".repeat(4);
                    } else {
                        // Spacing proportional to the difference between values
                        int gap = data - prevValue;
                        spacing = "-".repeat(4 * gap);
                    }

                    System.out.print(spacing + "> " + data + " ");

                    // Update prevValue
                    prevValue = data;

                    // Move to next node at this level
                    nextNodesField = currentNode.getClass().getDeclaredField("nextNodes");
                    nextNodesField.setAccessible(true);
                    Object[] nextNodes = (Object[]) nextNodesField.get(currentNode);
                    currentNode = nextNodes[i];
                }

                // End of level indicator
                System.out.println("-------> null");
            }

            System.out.println("=========================\n");

        } catch (Exception e) {
            System.out.println("Unable to visualize SkipList structure: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Main method to demonstrate SkipList operations.
     * Creates a SkipList, performs various operations, and visualizes the structure after each operation.
     */
    public static void main(String[] args) {
        System.out.println("==== SkipList Test ====");

        // Create a new SkipList
        SkipList<Integer> skipList = new SkipList<>();
        System.out.println("Created empty SkipList");

        // Insert elements
        System.out.println("\n--- Inserting elements ---");

        // Insert first element
        skipList.add(4);
        System.out.println("Inserted: 4");
        printSkipList(skipList);

        /* Expected structure (levels may vary due to randomness):
         *
         * Level 1:  head ----------> 4 -------> null
         * Level 0:  head ----------> 4 -------> null
         */

        // Insert more elements
        skipList.add(2);
        System.out.println("Inserted: 2");
        printSkipList(skipList);

        /* Expected structure:
         *
         * Level 1:  head ----------> 4 -------> null
         * Level 0:  head --> 2 ----> 4 -------> null
         */

        skipList.add(7);
        System.out.println("Inserted: 7");
        printSkipList(skipList);

        /* Expected structure:
         *
         * Level 2:  head --------------------------> 7 -------> null  (if 7 gets level 3)
         * Level 1:  head ----------> 4 ------------> 7 -------> null
         * Level 0:  head --> 2 ----> 4 ------------> 7 -------> null
         */

        skipList.add(1);
        System.out.println("Inserted: 1");

        skipList.add(9);
        System.out.println("Inserted: 9");

        skipList.add(3);
        System.out.println("Inserted: 3");

        skipList.add(6);
        System.out.println("Inserted: 6");

        // Print the final structure after inserting all elements
        System.out.println("\n--- Final structure after all insertions ---");
        printSkipList(skipList);

        /* Expected final structure (levels will vary due to randomness):
         *
         * Level 3:  head ---------------------------------------------> 9 -------> null
         * Level 2:  head ------------------------------> 7 -----------> 9 -------> null
         * Level 1:  head -----------------> 4 ---------> 7 -----------> 9 -------> null
         * Level 0:  head --> 1 -> 2 -> 3 -> 4 ----> 6 -> 7 -----------> 9 -------> null
         */

        // Search for elements
        System.out.println("\n--- Searching for elements ---");

        System.out.println("Contains 3: " + skipList.contains(3));
        System.out.println("Contains 5: " + skipList.contains(5));

        /* Search operations don't change the structure, but we can explain how the search works:
         *
         * When searching for element 3:
         * 1. Start at the highest level from the head
         * 2. Move forward at each level until finding a node with value > 3 or the end of the level
         * 3. Move down one level and repeat until reaching level 0
         * 4. At level 0, check if the target element is found
         *
         * This process gives O(log n) search time complexity.
         */

        // Remove elements
        System.out.println("\n--- Removing elements ---");

        skipList.remove(4);
        System.out.println("Removed: 4");
        printSkipList(skipList);

        /* Expected structure after removing 4:
         *
         * Level 3:  head ------------------------------------------> 9 -------> null
         * Level 2:  head -----------------------------> 7 ---------> 9 -------> null
         * Level 1:  head -----------------------------> 7 ---------> 9 -------> null
         * Level 0:  head --> 1 -> 2 -> 3 --------> 6 -> 7 ---------> 9 -------> null
         */

        skipList.remove(1);
        System.out.println("Removed: 1");
        printSkipList(skipList);

        /* Expected structure after removing 1:
         *
         * Level 3:  head ---------------------------------------> 9 -------> null
         * Level 2:  head --------------------------> 7 ---------> 9 -------> null
         * Level 1:  head --------------------------> 7 ---------> 9 -------> null
         * Level 0:  head -----> 2 -> 3 -------> 6 -> 7 ---------> 9 -------> null
         */

        // Test removing a non-existent element
        skipList.remove(5);
        System.out.println("Attempted to remove non-existent element: 5");
        printSkipList(skipList);

        // The structure should remain unchanged after attempting to remove a non-existent element

        System.out.println("\n==== SkipList Test Completed ====");
    }
}