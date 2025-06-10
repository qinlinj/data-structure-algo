package org.qinlinj.algoframework._900_other_common_algo_tricks._910_mathematical_techniques_I._914_game_randomization_algorithms;

/**
 * RESERVOIR SAMPLING ALGORITHM FOR GAME DEVELOPMENT
 * <p>
 * This class implements Reservoir Sampling, a sophisticated random selection algorithm
 * that can select k random elements from a stream of unknown or very large size,
 * using only O(k) space complexity. Perfect for games with massive worlds or
 * memory-constrained environments.
 * <p>
 * Key Concepts:
 * 1. Streaming Selection: Select k items from stream without knowing total size
 * - Process elements one by one
 * - Maintain reservoir of size k
 * - Each element has equal probability k/n of being selected
 * <p>
 * 2. Core Algorithm:
 * - Fill reservoir with first k elements
 * - For each subsequent element i (i > k):
 * * Generate random number r in [0, i]
 * * If r < k, replace reservoir[r] with current element
 * <p>
 * 3. Mathematical Proof of Uniformity:
 * - Probability element i is selected = k/i
 * - Probability it survives to end = ∏(1 - 1/j) for j > i
 * - Final probability = k/n for all elements
 * <p>
 * 4. Applications in Game Development:
 * - Large world exploration: Random POI selection
 * - Procedural generation: Selecting from massive item pools
 * - Memory-limited systems: Mobile/embedded games
 * - Streaming content: Online multiplayer events
 * <p>
 * 5. Advantages over Fisher-Yates:
 * - O(k) space instead of O(n)
 * - Works with unknown/infinite streams
 * - Suitable for very large datasets
 * - Real-time streaming capability
 * <p>
 * 6. LeetCode Applications:
 * - 382: Linked List Random Node
 * - 398: Random Pick Index
 * - General random sampling problems
 * <p>
 * Time Complexity: O(n) to process n elements
 * Space Complexity: O(k) for reservoir storage
 */

public class _914_c_ReservoirSamplingGames {
    private java.util.Random random;

    public _914_c_ReservoirSamplingGames() {
        this.random = new java.util.Random();
    }

    public _914_c_ReservoirSamplingGames(long seed) {
        this.random = new java.util.Random(seed);
    }

    /**
     * Generic reservoir sampling for k elements from stream
     */
    public <T> T[] reservoirSample(T[] stream, int k) {
        if (k <= 0 || stream.length == 0) {
            return (T[]) new Object[0];
        }

        k = Math.min(k, stream.length);
        T[] reservoir = (T[]) new Object[k];

        // Fill reservoir with first k elements
        for (int i = 0; i < k; i++) {
            reservoir[i] = stream[i];
        }

        // Process remaining elements
        for (int i = k; i < stream.length; i++) {
            // Generate random index in range [0, i]
            int randomIndex = random.nextInt(i + 1);

            // If random index < k, replace element in reservoir
            if (randomIndex < k) {
                reservoir[randomIndex] = stream[i];
            }
        }

        return reservoir;
    }

    /**
     * Reservoir sampling for integer ranges (like minesweeper)
     */
    public int[] sampleRange(int start, int end, int k) {
        int totalElements = end - start;
        if (k > totalElements) {
            throw new IllegalArgumentException("k cannot be larger than range size");
        }

        int[] reservoir = new int[k];

        // Fill reservoir with first k elements
        for (int i = 0; i < k; i++) {
            reservoir[i] = start + i;
        }

        // Process remaining elements
        for (int i = k; i < totalElements; i++) {
            int randomIndex = random.nextInt(i + 1);
            if (randomIndex < k) {
                reservoir[randomIndex] = start + i;
            }
        }

        return reservoir;
    }

    /**
     * Demonstrates the reservoir sampling process step by step
     */
    public void demonstrateReservoirProcess(int[] stream, int k) {
        System.out.println("=== Reservoir Sampling Step-by-Step ===");
        System.out.printf("Stream: %s, k=%d\n", java.util.Arrays.toString(stream), k);

        if (k > stream.length) {
            System.out.println("k is larger than stream size!");
            return;
        }

        int[] reservoir = new int[k];

        // Fill reservoir with first k elements
        System.out.println("\nInitialization phase:");
        for (int i = 0; i < k; i++) {
            reservoir[i] = stream[i];
            System.out.printf("Step %d: Add element %d to reservoir[%d]\n", i + 1, stream[i], i);
            System.out.printf("         Reservoir: %s\n", java.util.Arrays.toString(reservoir));
        }

        // Process remaining elements
        System.out.println("\nSampling phase:");
        for (int i = k; i < stream.length; i++) {
            int randomIndex = random.nextInt(i + 1);
            System.out.printf("Step %d: Element %d, random index = %d/%d",
                    i + 1, stream[i], randomIndex, i + 1);

            if (randomIndex < k) {
                int replaced = reservoir[randomIndex];
                reservoir[randomIndex] = stream[i];
                System.out.printf(" -> Replace reservoir[%d]: %d -> %d",
                        randomIndex, replaced, stream[i]);
            } else {
                System.out.print(" -> Keep current reservoir");
            }
            System.out.println();
            System.out.printf("         Reservoir: %s\n", java.util.Arrays.toString(reservoir));
        }

        System.out.printf("\nFinal reservoir: %s\n", java.util.Arrays.toString(reservoir));
    }

    /**
     * Mathematical proof demonstration
     */
    public void demonstrateMathematicalProof(int n, int k) {
        System.out.println("=== Mathematical Proof of Uniformity ===");
        System.out.printf("Total elements: %d, Sample size: %d\n", n, k);

        System.out.println("\nProbability calculation for each element:");
        System.out.println("Final probability = Selection probability × Survival probability");

        for (int i = 0; i < Math.min(n, 8); i++) { // Show first 8 elements
            if (i < k) {
                // Element is initially selected
                System.out.printf("Element %d: Initially selected (prob=1.0)\n", i);
                double survivalProb = 1.0;
                for (int j = k; j < n; j++) {
                    double notReplacedProb = 1.0 - (1.0 / (j + 1));
                    survivalProb *= notReplacedProb;
                }
                System.out.printf("           Survival probability: %.6f\n", survivalProb);
                System.out.printf("           Final probability: %.6f (expected: %.6f)\n",
                        survivalProb, (double) k / n);
            } else {
                // Element enters reservoir later
                double selectionProb = (double) k / (i + 1);
                double survivalProb = 1.0;
                for (int j = i + 1; j < n; j++) {
                    double notReplacedProb = 1.0 - (1.0 / (j + 1));
                    survivalProb *= notReplacedProb;
                }
                double finalProb = selectionProb * survivalProb;
                System.out.printf("Element %d: Selection prob=%.6f, Survival prob=%.6f\n",
                        i, selectionProb, survivalProb);
                System.out.printf("           Final probability: %.6f (expected: %.6f)\n",
                        finalProb, (double) k / n);
            }
        }

        if (n > 8) {
            System.out.println("... (remaining elements follow same pattern)");
        }

        System.out.printf("\nConclusion: All elements have probability %.6f = %d/%d\n",
                (double) k / n, k, n);
    }

    /**
     * LeetCode 382: Linked List Random Node
     * Returns a random node's value from the linked list
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static class LinkedListRandomNode {
        private ListNode head;
        private java.util.Random random;

        public LinkedListRandomNode(ListNode head) {
            this.head = head;
            this.random = new java.util.Random();
        }

        /**
         * Returns a random node's value
         */
        public int getRandom() {
            int result = 0;
            int count = 0;
            ListNode current = head;

            while (current != null) {
                count++;
                // With probability 1/count, select current element
                if (random.nextInt(count) == 0) {
                    result = current.val;
                }
                current = current.next;
            }

            return result;
        }
    }

    /**
     * Advanced game: Massive world exploration system
     */
    public static class MassiveWorldExplorer {
        private _914_c_ReservoirSamplingGames sampler;

        public MassiveWorldExplorer() {
            this.sampler = new _914_c_ReservoirSamplingGames();
        }

        /**
         * Simulates exploring a massive world and finding random POIs
         */
        public PointOfInterest[] exploreWorld(int worldSize, int maxPOIs) {
            System.out.printf("Exploring world of size %dx%d, collecting up to %d POIs\n",
                    worldSize, worldSize, maxPOIs);

            java.util.List<PointOfInterest> allPOIs = new java.util.ArrayList<>();

            // Simulate streaming discovery of POIs
            for (int id = 0; id < worldSize * worldSize / 100; id++) { // 1% of world has POI
                POIType type = POIType.values()[sampler.random.nextInt(POIType.values().length)];
                String name = generatePOIName(type, id);
                int x = sampler.random.nextInt(worldSize);
                int y = sampler.random.nextInt(worldSize);

                allPOIs.add(new PointOfInterest(id, type, name, x, y));
            }

            // Use reservoir sampling to select random POIs
            PointOfInterest[] allPOIArray = allPOIs.toArray(new PointOfInterest[0]);
            PointOfInterest[] selectedPOIs = sampler.reservoirSample(allPOIArray, maxPOIs);

            return selectedPOIs;
        }

        private String generatePOIName(POIType type, int id) {
            String[] prefixes = {"Ancient", "Mysterious", "Hidden", "Forgotten", "Sacred"};
            String[] suffixes = {"Temple", "Ruins", "Cave", "Grove", "Tower"};

            switch (type) {
                case TREASURE:
                    return "Treasure Chest #" + id;
                case MONSTER:
                    return "Monster Lair #" + id;
                case NPC:
                    return "Villager #" + id;
                case LANDMARK:
                    return prefixes[id % prefixes.length] + " " + suffixes[id % suffixes.length];
                case RESOURCE:
                    return "Resource Node #" + id;
                default:
                    return "Unknown POI #" + id;
            }
        }

        public enum POIType {TREASURE, MONSTER, NPC, LANDMARK, RESOURCE}

        public static class PointOfInterest {
            public final int id;
            public final POIType type;
            public final String name;
            public final int x, y;

            public PointOfInterest(int id, POIType type, String name, int x, int y) {
                this.id = id;
                this.type = type;
                this.name = name;
                this.x = x;
                this.y = y;
            }

            @Override
            public String toString() {
                return String.format("%s:%s at (%d,%d)", type, name, x, y);
            }
        }
    }

    /**
     * Memory-efficient minesweeper for very large boards
     */
    public static class MegaMinesweeper {
        private final int width, height, mineCount;
        private int[] minePositions;
        private _914_c_ReservoirSamplingGames sampler;

        public MegaMinesweeper(int width, int height, int mineCount) {
            this.width = width;
            this.height = height;
            this.mineCount = mineCount;
            this.sampler = new _914_c_ReservoirSamplingGames();
            generateMines();
        }

        private void generateMines() {
            System.out.printf("Generating %d mines for %dx%d board (%d total cells)\n",
                    mineCount, width, height, width * height);

            // Use reservoir sampling to avoid creating massive array
            minePositions = sampler.sampleRange(0, width * height, mineCount);
            java.util.Arrays.sort(minePositions); // Sort for easier debugging
        }

        public boolean isMine(int x, int y) {
            int position = x * height + y;
            return java.util.Arrays.binarySearch(minePositions, position) >= 0;
        }

        public int[] getMinePositions() {
            return minePositions.clone();
        }

        public void printMineStatistics() {
            System.out.printf("Mine positions (first 10): %s\n",
                    java.util.Arrays.toString(
                            java.util.Arrays.copyOf(minePositions, Math.min(10, minePositions.length))));

            // Convert some positions to 2D coordinates
            System.out.println("Sample mine coordinates:");
            for (int i = 0; i < Math.min(5, minePositions.length); i++) {
                int pos = minePositions[i];
                int x = pos / height;
                int y = pos % height;
                System.out.printf("  Position %d -> (%d,%d)\n", pos, x, y);
            }
        }
    }
}
