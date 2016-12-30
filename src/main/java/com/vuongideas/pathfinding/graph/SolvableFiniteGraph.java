package com.vuongideas.pathfinding.graph;

import java.util.List;

import com.vuongideas.pathfinding.algorithm.SearchAlgorithm;

public abstract class SolvableFiniteGraph<T> extends FiniteGraph<T> {

	public SolvableFiniteGraph(int size) {
		super(size);
	}
	
	public List<Vertex<T>> solve(SearchAlgorithm<T> algorithm) {
		return algorithm.search(this);
	}

}
