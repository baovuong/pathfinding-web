package com.vuongideas.pathfinding.algorithm;

import com.vuongideas.pathfinding.graph.Graph;
import com.vuongideas.pathfinding.graph.Vertex;

public interface Heuristic<T> {
    public double perform(Graph<T> graph, Vertex<T> current);
}
