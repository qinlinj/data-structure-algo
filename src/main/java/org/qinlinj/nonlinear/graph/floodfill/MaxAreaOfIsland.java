package org.qinlinj.nonlinear.graph.floodfill;

import java.util.*;

public class MaxAreaOfIsland {
    private int rows;
    private int cols;

    private int[][] grid;

    private Set<Integer>[] graph;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
}
