package com.vuongideas.pathfinding.algorithm;

import java.util.List;

import com.vuongideas.pathfinding.graph.Graph;
import com.vuongideas.pathfinding.graph.Vertex;

public class BidirectionalBreadthFirstSearchAlgorithm<T> implements SearchAlgorithm<T> {
	private Heuristic<T> heuristic;
	
	public BidirectionalBreadthFirstSearchAlgorithm(Heuristic<T> heurstic) {
		this.heuristic = heuristic;
	}
	
	@Override
	public List<Vertex<T>> search(Graph<T> graph) {
		// TODO Auto-generated method stub
		return null;
	}

}
