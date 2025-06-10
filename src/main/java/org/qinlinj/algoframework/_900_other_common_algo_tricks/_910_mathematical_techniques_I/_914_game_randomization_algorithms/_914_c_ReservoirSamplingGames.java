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
}
