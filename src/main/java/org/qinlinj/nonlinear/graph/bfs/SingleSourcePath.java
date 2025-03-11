package org.qinlinj.nonlinear.graph.bfs;

import org.qinlinj.nonlinear.graph.AdjSet;
import org.qinlinj.nonlinear.graph.Graph;

import java.util.*;

/**
 * SingleSourcePath - An implementation to find a path from a source vertex to any other vertex in a graph using BFS
 * <p>
 * Concept and Principles:
 * - This algorithm uses Breadth-First Search to find a path between two vertices in an unweighted graph
 * - Unlike SingleSourceShortestPath, this class only focuses on finding a valid path, not necessarily the shortest
 * - However, since BFS is used, the found path will be one of the shortest possible paths in an unweighted graph
 * <p>
 * Advantages:
 * - Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * - Memory Efficiency: Only stores the path information without distance tracking
 * - Simple Implementation: Uses a queue-based BFS traversal for intuitive path finding
 * - Complete: Guaranteed to find a path if one exists
 * <p>
 * Visual Example:
 * Consider a graph with 7 vertices (0-6) connected as follows:
 * 0 -- 1 -- 3 -- 5
 * |    |    |
 * 2 -- 4 -- 6
 * <p>
 * Running BFS from source=0:
 * - First visit vertex 0
 * - Then visit vertices 1,2 (neighbors of 0)
 * - Then visit vertices 3,4 (neighbors of 1,2)
 * - Finally visit vertices 5,6 (neighbors of 3,4)
 * <p>
 * For a path from 0 to 6, we would discover: 0 -> 1 -> 3 -> 6 or 0 -> 2 -> 4 -> 6
 * With the prevs array tracking the discovery: prevs[6] = 3, prevs[3] = 1, prevs[1] = 0
 */
public class SingleSourcePath {
    private Graph g;
    private boolean[] visited;
    private int[] prevs;

    private int source;

    public SingleSourcePath(Graph g, int source) {
        this.g = g;
        this.source = source;
        this.visited = new boolean[g.getV()];
        this.prevs = new int[g.getV()];
        Arrays.fill(this.prevs, -1);

        bfs(source);
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("data/graph-bfs.txt");
        SingleSourcePath graphBFS = new SingleSourcePath(g, 0);
        System.out.println(graphBFS.path(6));
    }

    private void bfs(int source) {
    }

    public boolean isConnected(int target) {
        validateVertex(target);
        return visited[target];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= g.getV()) {
            throw new IllegalArgumentException("illegal vertex，out of scope");
        }
    }

    public List<Integer> path(int target) {
        List<Integer> res = new ArrayList<>();

        if (!isConnected(target)) {
            return res;
        }

        while (target != source) {
            res.add(target);
            target = prevs[target];
        }
        res.add(source);

        Collections.reverse(res);

        return res;
    }
}

