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
