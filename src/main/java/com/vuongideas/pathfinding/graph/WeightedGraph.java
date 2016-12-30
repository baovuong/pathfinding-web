package com.vuongideas.pathfinding.graph;

public interface WeightedGraph<T> extends Graph<T> {
	public double getWeight(Vertex<T> v1, Vertex<T> v2);
}
