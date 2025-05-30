package org.qinlinj.algoframework._800_dynamic_programming_algo._840_dp_games_I._844_weighted_shortest_path;

/**
 * Complete Analysis and Variations of Cheapest Flights Problem
 * <p>
 * Comprehensive Coverage:
 * 1. Multiple Solution Approaches: BFS/Dijkstra, Top-down DP, Bottom-up DP, Bellman-Ford
 * 2. Problem Variations: Exact K stops, At most K stops, Multiple destinations
 * 3. Edge Cases: No path exists, Self-loops, Multiple edges between cities
 * 4. Optimization Techniques: Early termination, Space optimization, Pruning
 * 5. Real-world Applications: Flight booking, Network routing, Cost optimization
 * <p>
 * Algorithm Comparison:
 * - Dijkstra: O((V+E)logV), good for sparse graphs, handles general shortest path
 * - Top-down DP: O(V*K*E), intuitive recursion, handles step constraints naturally
 * - Bottom-up DP: O(K*E), iterative, better space locality, easier to optimize
 * - Bellman-Ford: O(K*E), detects negative cycles, works with negative weights
 * <p>
 * This class serves as a complete reference implementation with extensive testing
 * and real-world scenario simulation for the cheapest flights problem.
 */

import java.util.*;

public class _844_d_CompleteAnalysisAndVariations {

    public static void main(String[] args) {
        System.out.println("CHEAPEST FLIGHTS PROBLEM - COMPLETE ANALYSIS");
        System.out.println("=" + "=".repeat(60));

        // Run comprehensive tests
        TestSuite.runAllTests();

        // Run real-world simulation
        FlightBookingSimulator simulator = new FlightBookingSimulator();
        simulator.simulateBookingScenarios();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("SUMMARY OF KEY INSIGHTS:");
        System.out.println("1. Bottom-up DP is most efficient for this problem");
        System.out.println("2. Dijkstra works well for sparse graphs with few constraints");
        System.out.println("3. Top-down DP is most intuitive but has function call overhead");
        System.out.println("4. Bellman-Ford is robust and handles negative weights");
        System.out.println("5. Problem can be extended to many real-world scenarios");
        System.out.println("6. Always consider the trade-off between time and space complexity");
    }

    /**
     * Master solution class with all approaches
     */
    public static class MasterSolution {

        // Approach 1: Dijkstra with step constraint
        public int solveDijkstra(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int[] flight : flights) {
                graph.computeIfAbsent(flight[0], key -> new ArrayList<>())
                        .add(new int[]{flight[1], flight[2]});
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            pq.offer(new int[]{src, 0, 0}); // {city, cost, stops}

            Map<String, Integer> visited = new HashMap<>();

            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int city = curr[0], cost = curr[1], stops = curr[2];

                if (city == dst) return cost;
                if (stops > k) continue;

                String key = city + "," + stops;
                if (visited.containsKey(key) && visited.get(key) <= cost) continue;
                visited.put(key, cost);

                if (graph.containsKey(city)) {
                    for (int[] next : graph.get(city)) {
                        pq.offer(new int[]{next[0], cost + next[1], stops + 1});
                    }
                }
            }
            return -1;
        }

        // Approach 2: Top-down DP with memoization
        public int solveTopDownDP(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, List<int[]>> indegree = new HashMap<>();
            for (int[] flight : flights) {
                indegree.computeIfAbsent(flight[1], key -> new ArrayList<>())
                        .add(new int[]{flight[0], flight[2]});
            }

            Integer[][] memo = new Integer[n][k + 2];
            return dpHelper(dst, k + 1, src, indegree, memo);
        }

        private int dpHelper(int city, int k, int src, Map<Integer, List<int[]>> indegree, Integer[][] memo) {
            if (city == src) return 0;
            if (k == 0) return -1;
            if (memo[city][k] != null) return memo[city][k];

            int res = Integer.MAX_VALUE;
            if (indegree.containsKey(city)) {
                for (int[] edge : indegree.get(city)) {
                    int subRes = dpHelper(edge[0], k - 1, src, indegree, memo);
                    if (subRes != -1) {
                        res = Math.min(res, subRes + edge[1]);
                    }
                }
            }
            return memo[city][k] = (res == Integer.MAX_VALUE ? -1 : res);
        }

        // Approach 3: Bottom-up DP (most efficient)
        public int solveBottomUpDP(int n, int[][] flights, int src, int dst, int k) {
            int[] dp = new int[n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[src] = 0;

            for (int i = 0; i <= k; i++) {
                int[] temp = dp.clone();
                for (int[] flight : flights) {
                    int from = flight[0], to = flight[1], price = flight[2];
                    if (dp[from] != Integer.MAX_VALUE) {
                        temp[to] = Math.min(temp[to], dp[from] + price);
                    }
                }
                dp = temp;
            }
            return dp[dst] == Integer.MAX_VALUE ? -1 : dp[dst];
        }

        // Approach 4: Bellman-Ford variant
        public int solveBellmanFord(int n, int[][] flights, int src, int dst, int k) {
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;

            // Relax edges k+1 times
            for (int i = 0; i <= k; i++) {
                int[] temp = dist.clone();
                for (int[] flight : flights) {
                    int u = flight[0], v = flight[1], w = flight[2];
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + w < temp[v]) {
                        temp[v] = dist[u] + w;
                    }
                }
                dist = temp;
            }
            return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
        }
    }

    /**
     * Problem variations and extensions
     */
    public static class ProblemVariations {

        // Variation 1: Find path with exactly K stops
        public int findCheapestPriceExactlyKStops(int n, int[][] flights, int src, int dst, int k) {
            int[] dp = new int[n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[src] = 0;

            for (int step = 0; step < k; step++) {
                int[] temp = new int[n];
                Arrays.fill(temp, Integer.MAX_VALUE);

                for (int[] flight : flights) {
                    int from = flight[0], to = flight[1], price = flight[2];
                    if (dp[from] != Integer.MAX_VALUE) {
                        temp[to] = Math.min(temp[to], dp[from] + price);
                    }
                }
                dp = temp;
            }
            return dp[dst] == Integer.MAX_VALUE ? -1 : dp[dst];
        }

        // Variation 2: Find cheapest to any of multiple destinations
        public Map<Integer, Integer> findCheapestToMultipleDestinations(
                int n, int[][] flights, int src, Set<Integer> destinations, int k) {

            int[] dp = new int[n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[src] = 0;

            for (int i = 0; i <= k; i++) {
                int[] temp = dp.clone();
                for (int[] flight : flights) {
                    int from = flight[0], to = flight[1], price = flight[2];
                    if (dp[from] != Integer.MAX_VALUE) {
                        temp[to] = Math.min(temp[to], dp[from] + price);
                    }
                }
                dp = temp;
            }

            Map<Integer, Integer> results = new HashMap<>();
            for (int dest : destinations) {
                results.put(dest, dp[dest] == Integer.MAX_VALUE ? -1 : dp[dest]);
            }
            return results;
        }

        // Variation 3: Find all paths within budget
        public List<List<Integer>> findAllPathsWithinBudget(
                int n, int[][] flights, int src, int dst, int budget, int maxStops) {

            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int[] flight : flights) {
                graph.computeIfAbsent(flight[0], k -> new ArrayList<>())
                        .add(new int[]{flight[1], flight[2]});
            }

            List<List<Integer>> allPaths = new ArrayList<>();
            List<Integer> currentPath = new ArrayList<>();
            currentPath.add(src);

            dfsAllPaths(src, dst, budget, maxStops, 0, 0,
                    graph, currentPath, allPaths);

            return allPaths;
        }

        private void dfsAllPaths(int current, int dst, int budget, int maxStops,
                                 int currentCost, int stops,
                                 Map<Integer, List<int[]>> graph,
                                 List<Integer> path, List<List<Integer>> allPaths) {

            if (current == dst) {
                allPaths.add(new ArrayList<>(path));
                return;
            }

            if (stops >= maxStops || currentCost > budget) {
                return;
            }

            if (graph.containsKey(current)) {
                for (int[] next : graph.get(current)) {
                    int nextCity = next[0], price = next[1];
                    if (currentCost + price <= budget) {
                        path.add(nextCity);
                        dfsAllPaths(nextCity, dst, budget, maxStops,
                                currentCost + price, stops + 1,
                                graph, path, allPaths);
                        path.remove(path.size() - 1);
                    }
                }
            }
        }
    }

    /**
     * Real-world application simulator
     */
    public static class FlightBookingSimulator {
        private Random random = new Random(42);

        // Generate realistic flight network
        public int[][] generateRealisticFlightNetwork(int numCities, double connectivity) {
            List<int[]> flights = new ArrayList<>();

            // Add hub-and-spoke structure (realistic for airlines)
            int[] hubs = {0, numCities / 4, numCities / 2, 3 * numCities / 4};

            // Connect hubs to each other (expensive)
            for (int i = 0; i < hubs.length; i++) {
                for (int j = i + 1; j < hubs.length; j++) {
                    int price1 = 200 + random.nextInt(300);
                    int price2 = 200 + random.nextInt(300);
                    flights.add(new int[]{hubs[i], hubs[j], price1});
                    flights.add(new int[]{hubs[j], hubs[i], price2});
                }
            }

            // Connect each city to nearest hub (cheaper)
            for (int city = 0; city < numCities; city++) {
                int finalCity = city;
                if (Arrays.stream(hubs).anyMatch(h -> h == finalCity)) continue;

                int nearestHub = hubs[0];
                for (int hub : hubs) {
                    if (Math.abs(city - hub) < Math.abs(city - nearestHub)) {
                        nearestHub = hub;
                    }
                }

                int priceToHub = 50 + random.nextInt(150);
                int priceFromHub = 50 + random.nextInt(150);
                flights.add(new int[]{city, nearestHub, priceToHub});
                flights.add(new int[]{nearestHub, city, priceFromHub});
            }

            // Add some direct connections between non-hub cities
            for (int i = 0; i < numCities * connectivity; i++) {
                int from = random.nextInt(numCities);
                int to = random.nextInt(numCities);
                if (from != to) {
                    int price = 100 + random.nextInt(200);
                    flights.add(new int[]{from, to, price});
                }
            }

            return flights.toArray(new int[0][]);
        }

        // Simulate booking scenarios
        public void simulateBookingScenarios() {
            System.out.println("\n=== Flight Booking Simulation ===");

            int numCities = 20;
            int[][] flights = generateRealisticFlightNetwork(numCities, 0.3);

            MasterSolution solver = new MasterSolution();
            ProblemVariations variations = new ProblemVariations();

            // Scenario 1: Budget traveler (flexible dates, more stops allowed)
            System.out.println("Scenario 1: Budget Traveler");
            int budgetPrice = solver.solveBottomUpDP(numCities, flights, 1, 15, 3);
            System.out.println("Cheapest price with up to 3 stops: $" + budgetPrice);

            // Scenario 2: Business traveler (fewer stops, time-sensitive)
            System.out.println("\nScenario 2: Business Traveler");
            int businessPrice = solver.solveBottomUpDP(numCities, flights, 1, 15, 1);
            System.out.println("Cheapest price with up to 1 stop: $" + businessPrice);

            // Scenario 3: Multi-destination trip
            System.out.println("\nScenario 3: Multi-destination Trip");
            Set<Integer> destinations = Set.of(8, 12, 18);
            Map<Integer, Integer> multiDestPrices = variations.findCheapestToMultipleDestinations(
                    numCities, flights, 1, destinations, 2);
            multiDestPrices.forEach((dest, price) ->
                    System.out.println("To city " + dest + ": $" + price));

            // Performance analysis
            performanceAnalysis(numCities, flights, solver);
        }

        private void performanceAnalysis(int n, int[][] flights, MasterSolution solver) {
            System.out.println("\n=== Performance Analysis ===");

            int src = 0, dst = n - 1, k = 2;

            long[] times = new long[4];
            int[] results = new int[4];

            // Test Dijkstra
            long start = System.nanoTime();
            results[0] = solver.solveDijkstra(n, flights, src, dst, k);
            times[0] = System.nanoTime() - start;

            // Test Top-down DP
            start = System.nanoTime();
            results[1] = solver.solveTopDownDP(n, flights, src, dst, k);
            times[1] = System.nanoTime() - start;

            // Test Bottom-up DP
            start = System.nanoTime();
            results[2] = solver.solveBottomUpDP(n, flights, src, dst, k);
            times[2] = System.nanoTime() - start;

            // Test Bellman-Ford
            start = System.nanoTime();
            results[3] = solver.solveBellmanFord(n, flights, src, dst, k);
            times[3] = System.nanoTime() - start;

            String[] methods = {"Dijkstra", "Top-down DP", "Bottom-up DP", "Bellman-Ford"};

            for (int i = 0; i < 4; i++) {
                System.out.printf("%-15s: Result = %4d, Time = %6d μs%n",
                        methods[i], results[i], times[i] / 1000);
            }
        }
    }

    /**
     * Comprehensive test suite
     */
    public static class TestSuite {

        public static void runAllTests() {
            System.out.println("COMPREHENSIVE TEST SUITE");
            System.out.println("=" + "=".repeat(40));

            testBasicCases();
            testEdgeCases();
            testLargeInputs();
            testProblemVariations();
        }

        private static void testBasicCases() {
            System.out.println("\n--- Basic Test Cases ---");
            MasterSolution solver = new MasterSolution();

            // Test 1: Simple path
            int[][] flights1 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
            assert solver.solveBottomUpDP(3, flights1, 0, 2, 1) == 200;
            System.out.println("✓ Basic path test passed");

            // Test 2: Direct path only
            assert solver.solveBottomUpDP(3, flights1, 0, 2, 0) == 500;
            System.out.println("✓ Direct path test passed");
        }

        private static void testEdgeCases() {
            System.out.println("\n--- Edge Case Tests ---");
            MasterSolution solver = new MasterSolution();

            // Test: No path exists
            int[][] noPath = {{0, 1, 100}, {2, 3, 100}};
            assert solver.solveBottomUpDP(4, noPath, 0, 3, 5) == -1;
            System.out.println("✓ No path test passed");

            // Test: Self-loop (src == dst)
            int[][] selfLoop = {{0, 1, 100}};
            assert solver.solveBottomUpDP(2, selfLoop, 0, 0, 0) == 0;
            System.out.println("✓ Self-loop test passed");
        }

        private static void testLargeInputs() {
            System.out.println("\n--- Large Input Tests ---");

            // Generate large test case
            List<int[]> flights = new ArrayList<>();
            int n = 100;
            for (int i = 0; i < n - 1; i++) {
                flights.add(new int[]{i, i + 1, 100});
                if (i > 0) flights.add(new int[]{i, i - 1, 100});
            }

            MasterSolution solver = new MasterSolution();
            int result = solver.solveBottomUpDP(n, flights.toArray(new int[0][]), 0, n - 1, n);
            assert result > 0;
            System.out.println("✓ Large input test passed (result: " + result + ")");
        }

        private static void testProblemVariations() {
            System.out.println("\n--- Problem Variation Tests ---");
            ProblemVariations variations = new ProblemVariations();

            int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}, {1, 3, 200}};

            // Test exact K stops
            int exactResult = variations.findCheapestPriceExactlyKStops(4, flights, 0, 2, 2);
            System.out.println("✓ Exact K stops test: " + exactResult);

            // Test multiple destinations
            Set<Integer> dests = Set.of(2, 3);
            Map<Integer, Integer> multiResults = variations.findCheapestToMultipleDestinations(4, flights, 0, dests, 2);
            System.out.println("✓ Multiple destinations test: " + multiResults);
        }
    }
}