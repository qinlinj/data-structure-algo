package org.qinlinj.nonlinear.highlevel.map;

/**
 * Test class for demonstrating the functionality of the HashMap implementation.
 * This class performs various operations on a HashMap and displays the results.
 */
public class HashMapTest {

    public static void main(String[] args) {
        // Create a new HashMap with initial capacity of 5 and default load factor
        HashMap<String, Integer> fruitMap = new HashMap<>(5);

        System.out.println("=== Testing HashMap Implementation ===");

        // Test 1: Adding key-value pairs
        System.out.println("\nTest 1: Adding key-value pairs");
        fruitMap.add("apple", 10);
        fruitMap.add("banana", 5);
        fruitMap.add("cherry", 20);
        fruitMap.add("date", 15);
        fruitMap.add("elderberry", 8);

        System.out.println("Size after adding 5 fruits: " + fruitMap.size());
        System.out.println("Value for 'apple': " + fruitMap.get("apple"));
        System.out.println("Value for 'banana': " + fruitMap.get("banana"));

        // Test 2: Adding a key that already exists (should update value)
        System.out.println("\nTest 2: Updating existing key");
        System.out.println("Original value for 'apple': " + fruitMap.get("apple"));
        fruitMap.add("apple", 25);
        System.out.println("Updated value for 'apple': " + fruitMap.get("apple"));
        System.out.println("Size after update: " + fruitMap.size() + " (should still be 5)");

        // Test 3: Testing the containsKey method
        System.out.println("\nTest 3: Testing containsKey");
        System.out.println("Contains 'cherry'? " + fruitMap.containsKey("cherry"));
        System.out.println("Contains 'fig'? " + fruitMap.containsKey("fig"));

        // Test 4: Testing the set method
        System.out.println("\nTest 4: Testing set method");
        try {
            System.out.println("Original value for 'date': " + fruitMap.get("date"));
            fruitMap.set("date", 30);
            System.out.println("Updated value for 'date': " + fruitMap.get("date"));

            // This should throw NoSuchElementException
            fruitMap.set("fig", 12);
            System.out.println("This line should not be reached");
        } catch (Exception e) {
            System.out.println("Exception caught when setting non-existent key: " + e.getMessage());
        }

        // Test 5: Testing remove method
        System.out.println("\nTest 5: Testing remove");
        System.out.println("Size before removal: " + fruitMap.size());
        Integer removedValue = fruitMap.remove("banana");
        System.out.println("Removed value for 'banana': " + removedValue);
        System.out.println("Size after removal: " + fruitMap.size());
        System.out.println("Contains 'banana' after removal? " + fruitMap.containsKey("banana"));

        // Try to remove a non-existent key
        Integer nonExistentValue = fruitMap.remove("fig");
        System.out.println("Result of removing non-existent key: " + nonExistentValue);

        // Test 6: Adding more elements to trigger resize
        System.out.println("\nTest 6: Adding more elements to trigger resize");
        fruitMap.add("fig", 12);
        fruitMap.add("grape", 30);
        fruitMap.add("honeydew", 22);
        fruitMap.add("kiwi", 18);
        fruitMap.add("lemon", 7);

        System.out.println("Size after adding more fruits: " + fruitMap.size());
        System.out.println("Value for 'fig': " + fruitMap.get("fig"));
        System.out.println("Value for 'grape': " + fruitMap.get("grape"));

        // Test 7: Testing with different key types
        System.out.println("\nTest 7: Testing with Integer keys");
        HashMap<Integer, String> numberMap = new HashMap<>();
        numberMap.add(1, "One");
        numberMap.add(2, "Two");
        numberMap.add(3, "Three");

        System.out.println("Size of numberMap: " + numberMap.size());
        System.out.println("Value for key 2: " + numberMap.get(2));
        numberMap.remove(2);
        System.out.println("Contains key 2 after removal? " + numberMap.containsKey(2));

        // Test 8: Testing edge cases
        System.out.println("\nTest 8: Testing edge cases");
        HashMap<String, String> emptyMap = new HashMap<>();
        System.out.println("Is empty map empty? " + emptyMap.isEmpty());
        System.out.println("Size of empty map: " + emptyMap.size());

        // Test 9: Print hashmap
        System.out.println("\nTest 9: Print this hashmap");
        System.out.println(fruitMap);

        System.out.println("\n=== HashMap Test Complete ===");
    }
}
