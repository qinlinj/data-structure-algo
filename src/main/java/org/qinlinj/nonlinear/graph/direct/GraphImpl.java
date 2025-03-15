package org.qinlinj.nonlinear.graph.direct;

import java.util.*;

public class GraphImpl {
    private int V;
    private int E;
    private TreeSet<Integer>[] adj;

    private boolean isDirected;

    private int[] indegrees;
    private int[] outdegrees;
}
