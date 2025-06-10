package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._914_game_randomization_algorithms;

/**
 * FISHER-YATES SHUFFLE ALGORITHM FOR GAME DEVELOPMENT
 * <p>
 * This class implements the Fisher-Yates shuffle algorithm specifically for game development
 * scenarios, demonstrating how to solve the "select k different random elements" problem
 * by transforming it into "shuffle array and take first k elements".
 * <p>
 * Key Concepts:
 * 1. Problem Transformation: Instead of selecting k random elements (with duplicate handling),
 * place k elements at start of array, then shuffle entire array randomly
 * <p>
 * 2. Fisher-Yates Shuffle Algorithm:
 * - For each position i from 0 to n-1:
 * * Generate random index r in range [i, n-1]
 * * Swap elements at positions i and r
 * - Guarantees each permutation has equal probability (1/n!)
 * <p>
 * 3. Mathematical Correctness:
 * - Algorithm produces all n! possible permutations
 * - Each element has equal probability of ending up at any position
 * - Proof: Position 0 has n choices, position 1 has (n-1) choices, etc.
 * <p>
 * 4. Applications in Game Development:
 * - Minesweeper: Random mine placement
 * - Card games: Deck shuffling
 * - RPGs: Random loot distribution
 * - Puzzle games: Level generation
 * <p>
 * 5. Advantages:
 * - Simple implementation
 * - Guaranteed uniform distribution
 * - O(n) time complexity
 * - Well-established algorithm with proven properties
 * <p>
 * 6. Trade-offs:
 * - Requires O(n) space for full array
 * - Must process all elements even if k << n
 * - Not suitable for very large search spaces
 * <p>
 * Time Complexity: O(n) for shuffling n elements
 * Space Complexity: O(n) for storing all elements
 */
public class _914_b_FisherYatesShuffleGames {

    private java.util.Random random;

    public _914_b_FisherYatesShuffleGames() {
        this.random = new java.util.Random();
    }

    public _914_b_FisherYatesShuffleGames(long seed) {
        this.random = new java.util.Random(seed);
    }

    public static void main(String[] args) {
        _914_b_FisherYatesShuffleGames shuffleDemo = new _914_b_FisherYatesShuffleGames(42);

        // Basic demonstration
        System.out.println("FISHER-YATES SHUFFLE ALGORITHM FOR GAMES");
        System.out.println("=======================================");

        // Step-by-step demonstration
        int[] smallArray = {1, 2, 3, 4, 5};
        shuffleDemo.demonstrateShuffleProcess(smallArray);

        // LeetCode 384 example
        System.out.println("\n=== LeetCode 384: Shuffle an Array ===");
        int[] nums = {1, 2, 3};
        ShuffleArray solution = new ShuffleArray(nums);

        System.out.println("Original: " + java.util.Arrays.toString(nums));
        System.out.println("Shuffle 1: " + java.util.Arrays.toString(solution.shuffle()));
        System.out.println("Shuffle 2: " + java.util.Arrays.toString(solution.shuffle()));
        System.out.println("Reset: " + java.util.Arrays.toString(solution.reset()));
        System.out.println("Shuffle 3: " + java.util.Arrays.toString(solution.shuffle()));

        // Game examples
        shuffleDemo.gameExamples();

        // Mathematical correctness
        shuffleDemo.demonstrateMathematicalCorrectness();

        // Algorithm verification
        shuffleDemo.verifyShuffleCorrectness(5, 10000);

        // Performance comparison
        shuffleDemo.performanceComparison(1000, 100, 1000);

        System.out.println("\n=== Key Advantages ===");
        System.out.println("1. Guaranteed uniform distribution");
        System.out.println("2. O(n) time complexity - linear and efficient");
        System.out.println("3. Simple implementation with proven correctness");
        System.out.println("4. Generates all n! permutations with equal probability");
        System.out.println("5. No issues with duplicate handling");

        System.out.println("\n=== When to Use Fisher-Yates ===");
        System.out.println("• When you can afford O(n) space complexity");
        System.out.println("• For complete array shuffling");
        System.out.println("• When simplicity and correctness are priorities");
        System.out.println("• Game development with manageable data sizes");
        System.out.println("• Educational purposes and algorithm learning");

        System.out.println("\n=== Limitations ===");
        System.out.println("• Requires storing all elements in memory");
        System.out.println("• Not suitable for very large datasets");
        System.out.println("• Overkill when k << n (few selections from large set)");
        System.out.println("• Cannot handle streaming data");
    }

    /**
     * Generic Fisher-Yates shuffle for any array type
     */
    public <T> void shuffle(T[] array) {
        for (int i = 0; i < array.length; i++) {
            int randomIndex = i + random.nextInt(array.length - i);
            // Swap elements
            T temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
    }

    /**
     * Fisher-Yates shuffle for integer arrays
     */
    public void shuffle(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randomIndex = i + random.nextInt(array.length - i);
            // Swap elements
            int temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
    }

    /**
     * Minesweeper mine placement using Fisher-Yates
     */
    public int[] placeMinesweperMines(int boardWidth, int boardHeight, int mineCount) {
        if (mineCount > boardWidth * boardHeight) {
            throw new IllegalArgumentException("Too many mines for board size");
        }

        int totalCells = boardWidth * boardHeight;
        int[] positions = new int[totalCells];

        // Initialize positions array with all possible indices
        for (int i = 0; i < totalCells; i++) {
            positions[i] = i;
        }

        // Shuffle the array using Fisher-Yates
        shuffle(positions);

        // Take first mineCount positions as mine locations
        int[] minePositions = new int[mineCount];
        System.arraycopy(positions, 0, minePositions, 0, mineCount);

        return minePositions;
    }

    /**
     * Demonstrates the step-by-step Fisher-Yates process
     */
    public void demonstrateShuffleProcess(int[] array) {
        System.out.println("=== Fisher-Yates Shuffle Step-by-Step ===");
        System.out.println("Original array: " + java.util.Arrays.toString(array));

        int[] working = array.clone();

        for (int i = 0; i < working.length; i++) {
            // Generate random index in range [i, n-1]
            int randomIndex = i + random.nextInt(working.length - i);

            System.out.printf("Step %d: Swap position %d with position %d (%d <-> %d)\n",
                    i + 1, i, randomIndex, working[i], working[randomIndex]);

            // Perform swap
            int temp = working[i];
            working[i] = working[randomIndex];
            working[randomIndex] = temp;

            System.out.println("         Result: " + java.util.Arrays.toString(working));
        }

        System.out.println("Final shuffled: " + java.util.Arrays.toString(working));
    }

    /**
     * Verifies algorithm correctness by analyzing distribution
     */
    public void verifyShuffleCorrectness(int arraySize, int trials) {
        System.out.println("=== Shuffle Correctness Verification ===");
        System.out.printf("Testing array of size %d with %d trials\n", arraySize, trials);

        // Track how often each element ends up at each position
        int[][] positionCounts = new int[arraySize][arraySize];

        for (int trial = 0; trial < trials; trial++) {
            // Create array [0, 1, 2, ..., arraySize-1]
            int[] array = new int[arraySize];
            for (int i = 0; i < arraySize; i++) {
                array[i] = i;
            }

            // Shuffle and record positions
            shuffle(array);

            for (int pos = 0; pos < arraySize; pos++) {
                int element = array[pos];
                positionCounts[element][pos]++;
            }
        }

        // Analyze results
        double expectedFrequency = (double) trials / arraySize;
        System.out.printf("Expected frequency per position: %.1f\n", expectedFrequency);

        System.out.println("\nElement -> Position frequency matrix:");
        System.out.print("Elem\\Pos ");
        for (int j = 0; j < arraySize; j++) {
            System.out.printf("%6d", j);
        }
        System.out.println();

        for (int i = 0; i < arraySize; i++) {
            System.out.printf("%8d ", i);
            for (int j = 0; j < arraySize; j++) {
                System.out.printf("%6d", positionCounts[i][j]);
            }
            System.out.println();
        }

        // Calculate deviation from expected
        double maxDeviation = 0;
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                double deviation = Math.abs(positionCounts[i][j] - expectedFrequency);
                maxDeviation = Math.max(maxDeviation, deviation);
            }
        }

        System.out.printf("Maximum deviation from expected: %.1f\n", maxDeviation);
        System.out.printf("Relative deviation: %.2f%%\n", 100.0 * maxDeviation / expectedFrequency);

        if (maxDeviation < expectedFrequency * 0.1) {
            System.out.println("✓ Algorithm appears to be working correctly!");
        } else {
            System.out.println("⚠ Large deviations detected - check implementation");
        }
    }

    /**
     * Performance comparison with naive random selection
     */
    public void performanceComparison(int arraySize, int selectCount, int trials) {
        System.out.println("=== Performance Comparison ===");
        System.out.printf("Array size: %d, Select: %d, Trials: %d\n",
                arraySize, selectCount, trials);

        // Method 1: Fisher-Yates shuffle
        long startTime = System.nanoTime();
        for (int trial = 0; trial < trials; trial++) {
            int[] positions = new int[arraySize];
            for (int i = 0; i < arraySize; i++) {
                positions[i] = i;
            }
            shuffle(positions);
            // Take first selectCount elements
        }
        long fisherYatesTime = System.nanoTime() - startTime;

        // Method 2: Naive random with duplicate checking
        startTime = System.nanoTime();
        for (int trial = 0; trial < trials; trial++) {
            java.util.Set<Integer> selected = new java.util.HashSet<>();
            while (selected.size() < selectCount) {
                int randomIndex = random.nextInt(arraySize);
                selected.add(randomIndex);
            }
        }
        long naiveTime = System.nanoTime() - startTime;

        System.out.printf("Fisher-Yates: %.2f ms\n", fisherYatesTime / 1_000_000.0);
        System.out.printf("Naive method: %.2f ms\n", naiveTime / 1_000_000.0);

        if (naiveTime > fisherYatesTime) {
            System.out.printf("Fisher-Yates is %.2fx faster\n", (double) naiveTime / fisherYatesTime);
        } else {
            System.out.printf("Naive method is %.2fx faster\n", (double) fisherYatesTime / naiveTime);
        }
    }

    /**
     * Game development examples
     */
    public void gameExamples() {
        System.out.println("=== Game Development Examples ===");

        // Minesweeper example
        System.out.println("1. Minesweeper Mine Placement:");
        int[] minePositions = placeMinesweperMines(8, 8, 10);
        System.out.printf("Placed 10 mines in 8x8 board at indices: %s\n",
                java.util.Arrays.toString(minePositions));

        // Convert to 2D coordinates for display
        System.out.println("Mine positions (x,y):");
        for (int index : minePositions) {
            int x = index / 8;
            int y = index % 8;
            System.out.printf("  Index %d -> (%d,%d)\n", index, x, y);
        }

        // Card shuffling example
        System.out.println("\n2. Card Deck Shuffling:");
        CardDeck deck = new CardDeck();
        System.out.println("Original first 5 cards:");
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + deck.dealCard(i));
        }

        deck.shuffle();
        System.out.println("After shuffling first 5 cards:");
        for (int i = 0; i < 5; i++) {
            System.out.println("  " + deck.dealCard(i));
        }

        // Loot distribution example
        System.out.println("\n3. RPG Loot Distribution:");
        LootDistributor lootDist = new LootDistributor();
        LootDistributor.LootItem[] playerLoot = lootDist.distributeLoot(4);

        System.out.println("Loot distributed to 4 players:");
        for (int i = 0; i < playerLoot.length; i++) {
            System.out.printf("  Player %d: %s\n", i + 1, playerLoot[i]);
        }
    }

    /**
     * Mathematical correctness demonstration
     */
    public void demonstrateMathematicalCorrectness() {
        System.out.println("=== Mathematical Correctness ===");

        int n = 4; // Small array for clear demonstration
        System.out.printf("For array of size %d:\n", n);
        System.out.printf("Total possible permutations: %d! = %d\n", n, factorial(n));

        System.out.println("\nFisher-Yates algorithm analysis:");
        System.out.println("• Position 0: Can be filled by any of n elements");
        System.out.println("• Position 1: Can be filled by any of (n-1) remaining elements");
        System.out.println("• Position 2: Can be filled by any of (n-2) remaining elements");
        System.out.println("• Position 3: Can be filled by the last remaining element");
        System.out.printf("• Total arrangements: %d × %d × %d × %d = %d\n", n, n - 1, n - 2, 1, factorial(n));

        System.out.println("\nThis proves the algorithm can generate all possible permutations!");

        // Demonstrate with actual small example
        System.out.println("\nSmall example with array [0, 1, 2]:");
        java.util.Map<String, Integer> permutationCounts = new java.util.HashMap<>();
        int trials = 60000;

        for (int trial = 0; trial < trials; trial++) {
            int[] array = {0, 1, 2};
            shuffle(array);
            String permutation = java.util.Arrays.toString(array);
            permutationCounts.put(permutation, permutationCounts.getOrDefault(permutation, 0) + 1);
        }

        System.out.printf("Results from %d trials:\n", trials);
        double expectedCount = (double) trials / factorial(3);
        System.out.printf("Expected count per permutation: %.1f\n", expectedCount);

        for (java.util.Map.Entry<String, Integer> entry : permutationCounts.entrySet()) {
            double percentage = 100.0 * entry.getValue() / trials;
            System.out.printf("%s: %d times (%.1f%%)\n",
                    entry.getKey(), entry.getValue(), percentage);
        }
    }

    private int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * LeetCode 384: Shuffle an Array
     * Implements the classic Fisher-Yates shuffle algorithm
     */
    public static class ShuffleArray {
        private int[] original;
        private java.util.Random random;

        public ShuffleArray(int[] nums) {
            this.original = nums.clone();
            this.random = new java.util.Random();
        }

        /**
         * Resets the array to its original configuration and returns it.
         */
        public int[] reset() {
            return original.clone();
        }

        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle() {
            int[] shuffled = original.clone();

            // Fisher-Yates shuffle algorithm
            for (int i = 0; i < shuffled.length; i++) {
                // Generate random index in range [i, n-1]
                int randomIndex = i + random.nextInt(shuffled.length - i);
                // Swap elements at positions i and randomIndex
                swap(shuffled, i, randomIndex);
            }

            return shuffled;
        }

        private void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    /**
     * Card deck shuffling for card games
     */
    public static class CardDeck {
        private Card[] deck;
        private _914_b_FisherYatesShuffleGames shuffler;

        public CardDeck() {
            this.shuffler = new _914_b_FisherYatesShuffleGames();
            initializeDeck();
        }

        private void initializeDeck() {
            deck = new Card[52];
            int index = 0;

            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    deck[index++] = new Card(suit, rank);
                }
            }
        }

        public void shuffle() {
            shuffler.shuffle(deck);
        }

        public Card[] getDeck() {
            return deck.clone();
        }

        public Card dealCard(int index) {
            if (index >= 0 && index < deck.length) {
                return deck[index];
            }
            return null;
        }

        public enum Suit {HEARTS, DIAMONDS, CLUBS, SPADES}

        public enum Rank {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}

        public static class Card {
            public final Suit suit;
            public final Rank rank;

            public Card(Suit suit, Rank rank) {
                this.suit = suit;
                this.rank = rank;
            }

            @Override
            public String toString() {
                return rank + " of " + suit;
            }
        }
    }

    /**
     * Random loot distribution for RPG games
     */
    public static class LootDistributor {
        private LootItem[] availableLoot;
        private _914_b_FisherYatesShuffleGames shuffler;

        public LootDistributor() {
            this.shuffler = new _914_b_FisherYatesShuffleGames();
            initializeLoot();
        }

        private void initializeLoot() {
            availableLoot = new LootItem[]{
                    new LootItem("Iron Sword", LootRarity.COMMON, 10),
                    new LootItem("Health Potion", LootRarity.COMMON, 5),
                    new LootItem("Magic Ring", LootRarity.UNCOMMON, 25),
                    new LootItem("Steel Armor", LootRarity.UNCOMMON, 40),
                    new LootItem("Dragon Scale", LootRarity.RARE, 100),
                    new LootItem("Enchanted Bow", LootRarity.RARE, 80),
                    new LootItem("Phoenix Feather", LootRarity.EPIC, 200),
                    new LootItem("Crown of Kings", LootRarity.LEGENDARY, 1000)
            };
        }

        public LootItem[] distributeLoot(int playerCount) {
            if (playerCount > availableLoot.length) {
                throw new IllegalArgumentException("Not enough loot for all players");
            }

            // Shuffle loot and give first items to players
            LootItem[] lootCopy = availableLoot.clone();
            shuffler.shuffle(lootCopy);

            LootItem[] distributedLoot = new LootItem[playerCount];
            System.arraycopy(lootCopy, 0, distributedLoot, 0, playerCount);

            return distributedLoot;
        }

        public enum LootRarity {COMMON, UNCOMMON, RARE, EPIC, LEGENDARY}

        public static class LootItem {
            public final String name;
            public final LootRarity rarity;
            public final int value;

            public LootItem(String name, LootRarity rarity, int value) {
                this.name = name;
                this.rarity = rarity;
                this.value = value;
            }

            @Override
            public String toString() {
                return String.format("%s (%s, %d gold)", name, rarity, value);
            }
        }
    }
}