package com.vuongideas.pathfinding.algorithm;

import java.util.List;

import com.vuongideas.pathfinding.graph.Graph;
import com.vuongideas.pathfinding.graph.Vertex;

public interface SearchAlgorithm<T> {
	public List<Vertex<T>> search(Graph<T> graph);
}
