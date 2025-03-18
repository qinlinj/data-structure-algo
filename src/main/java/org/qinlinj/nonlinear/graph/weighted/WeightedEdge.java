package org.qinlinj.nonlinear.graph.weighted;

public class WeightedEdge implements Comparable<WeightedEdge> {
    private int v;
    private int w;
    private int weight;

    public WeightedEdge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(WeightedEdge o) {
        return weight - o.getWeight();
    }
}
