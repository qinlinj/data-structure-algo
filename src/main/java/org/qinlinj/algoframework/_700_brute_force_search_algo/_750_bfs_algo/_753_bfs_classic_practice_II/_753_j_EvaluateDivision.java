package org.qinlinj.algoframework._700_brute_force_search_algo._750_bfs_algo._753_bfs_classic_practice_II; /**
 * Evaluate Division (LeetCode 399)
 * ------------------------------
 * <p>
 * Summary:
 * This problem involves a set of equations and their corresponding values (e.g., a/b = 2.0).
 * We need to evaluate queries (c/d = ?) based on the given equations. If a query can't be
 * determined based on the given equations, we return -1.0.
 * <p>
 * Key Concepts:
 * 1. Graph representation for variable relationships
 * 2. Directed weighted graph where:
 * - Variables are nodes
 * - Edges represent division relationships with weights
 * - a/b = 2.0 means there's an edge from a to b with weight 2.0
 * and an edge from b to a with weight 1/2.0
 * 3. BFS/DFS to find paths between variables (numerator to denominator)
 * 4. Accumulating division results along paths
 * <p>
 * Approach:
 * - Build a graph where each variable is a node
 * - Add weighted edges for each equation (a/b = v adds edges a→b with weight v and b→a with weight 1/v)
 * - For each query x/y, use BFS to find a path from x to y
 * - Multiply the weights along the path to get the result
 * <p>
 * Time Complexity:
 * - O(E + Q*V) where:
 * - E is the number of equations
 * - Q is the number of queries
 * - V is the number of variables
 * <p>
 * Space Complexity: O(V) for the graph and queue
 */

import java.util.*;

public class _753_j_EvaluateDivision {

    /**
     * Example usage
     */
    public static void main(String[] args) {
        _753_j_EvaluateDivision solution = new _753_j_EvaluateDivision();

        // Example 1
        List<List<String>> equations1 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c")
        );
        double[] values1 = {2.0, 3.0};
        List<List<String>> queries1 = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "e"),
                Arrays.asList("a", "a"),
                Arrays.asList("x", "x")
        );

        double[] results1 = solution.calcEquation(equations1, values1, queries1);
        System.out.println("Example 1 (BFS): " + Arrays.toString(results1));

        // Example 2
        List<List<String>> equations2 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c"),
                Arrays.asList("bc", "cd")
        );
        double[] values2 = {1.5, 2.5, 5.0};
        List<List<String>> queries2 = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("c", "b"),
                Arrays.asList("bc", "cd"),
                Arrays.asList("cd", "bc")
        );

        double[] results2 = solution.calcEquationDFS(equations2, values2, queries2);
        System.out.println("Example 2 (DFS): " + Arrays.toString(results2));

        // Example 3
        List<List<String>> equations3 = Arrays.asList(
                Arrays.asList("a", "b")
        );
        double[] values3 = {0.5};
        List<List<String>> queries3 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "c"),
                Arrays.asList("x", "y")
        );

        double[] results3 = solution.calcEquation(equations3, values3, queries3);
        System.out.println("Example 3 (BFS): " + Arrays.toString(results3));
    }

    /**
     * Evaluate division queries based on given equations
     * @param equations List of variable pairs in each equation (e.g., a/b)
     * @param values Values corresponding to the equations (e.g., a/b = 2.0)
     * @param queries Queries to evaluate (e.g., c/d = ?)
     * @return Array of results for each query
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Build graph from equations and values
        Map<String, List<Edge>> graph = buildGraph(equations, values);

        // Evaluate each query
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String numerator = query.get(0);
            String denominator = query.get(1);

            // Special cases
            if (!graph.containsKey(numerator) || !graph.containsKey(denominator)) {
                results[i] = -1.0;
            } else if (numerator.equals(denominator)) {
                results[i] = 1.0;
            } else {
                // Use BFS to find path from numerator to denominator
                results[i] = bfs(graph, numerator, denominator);
            }
        }

        return results;
    }

    /**
     * Build a graph from equations and values
     * @param equations List of variable pairs
     * @param values Corresponding values
     * @return Adjacency list representation of the graph
     */
    private Map<String, List<Edge>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, List<Edge>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String a = equation.get(0);
            String b = equation.get(1);
            double value = values[i];

            // Add nodes if they don't exist
            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());

            // Add edges in both directions
            graph.get(a).add(new Edge(b, value));       // a/b = value
            graph.get(b).add(new Edge(a, 1.0 / value)); // b/a = 1/value
        }

        return graph;
    }

    /**
     * BFS to find the value of numerator/denominator
     * @param graph Adjacency list representation of the graph
     * @param start Numerator variable
     * @param end Denominator variable
     * @return Result of division or -1.0 if no path exists
     */
    private double bfs(Map<String, List<Edge>> graph, String start, String end) {
        Queue<String> queue = new LinkedList<>();
        Queue<Double> valueQueue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // Start from the numerator
        queue.offer(start);
        valueQueue.offer(1.0);
        visited.add(start);

        while (!queue.isEmpty()) {
            String currentNode = queue.poll();
            double currentValue = valueQueue.poll();

            // Check if we've reached the denominator
            if (currentNode.equals(end)) {
                return currentValue;
            }

            // Explore all neighbors
            for (Edge neighbor : graph.get(currentNode)) {
                if (!visited.contains(neighbor.node)) {
                    // New accumulated value = current value * edge weight
                    double newValue = currentValue * neighbor.weight;
                    queue.offer(neighbor.node);
                    valueQueue.offer(newValue);
                    visited.add(neighbor.node);
                }
            }
        }

        // No path found
        return -1.0;
    }

    /**
     * Alternative implementation using DFS
     */
    public double[] calcEquationDFS(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Build graph from equations and values
        Map<String, List<Edge>> graph = buildGraph(equations, values);

        // Evaluate each query
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String numerator = query.get(0);
            String denominator = query.get(1);

            // Special cases
            if (!graph.containsKey(numerator) || !graph.containsKey(denominator)) {
                results[i] = -1.0;
            } else if (numerator.equals(denominator)) {
                results[i] = 1.0;
            } else {
                // Use DFS to find path from numerator to denominator
                Set<String> visited = new HashSet<>();
                results[i] = dfs(graph, numerator, denominator, 1.0, visited);
            }
        }

        return results;
    }

    /**
     * DFS to find the value of numerator/denominator
     * @param graph Adjacency list representation of the graph
     * @param current Current node
     * @param target Target node (denominator)
     * @param accumulated Current accumulated value
     * @param visited Set of visited nodes
     * @return Result of division or -1.0 if no path exists
     */
    private double dfs(Map<String, List<Edge>> graph, String current, String target,
                       double accumulated, Set<String> visited) {
        // Mark current node as visited
        visited.add(current);

        // Base case: reached the target
        if (current.equals(target)) {
            return accumulated;
        }

        // Explore all neighbors
        for (Edge neighbor : graph.get(current)) {
            if (!visited.contains(neighbor.node)) {
                double result = dfs(graph, neighbor.node, target,
                        accumulated * neighbor.weight, visited);
                if (result != -1.0) {
                    return result;
                }
            }
        }

        // No path found
        return -1.0;
    }

    /**
     * Class to represent edges in the graph with destination node and weight
     */
    static class Edge {
        String node;
        double weight;

        Edge(String node, double weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}