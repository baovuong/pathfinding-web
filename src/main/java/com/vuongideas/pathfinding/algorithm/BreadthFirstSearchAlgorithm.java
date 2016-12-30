package com.vuongideas.pathfinding.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import com.vuongideas.pathfinding.graph.Graph;
import com.vuongideas.pathfinding.graph.Vertex;

public class BreadthFirstSearchAlgorithm<T> implements SearchAlgorithm<T> {

	@Override
	public List<Vertex<T>> search(Graph<T> graph) {
		List<Vertex<T>> path = new ArrayList<Vertex<T>>();
		Queue<Vertex<T>> fringe = new LinkedList<Vertex<T>>();
		Vertex<T> start = graph.getStart();
		Vertex<T> goal = graph.getGoal();
		Vertex<T> current;
		List<Vertex<T>> discovered = new ArrayList<Vertex<T>>();
		Map<Vertex<T>, Vertex<T>> parent = new HashMap<Vertex<T>, Vertex<T>>();
		fringe.add(start);
		
		while (!fringe.isEmpty()) {
			current = fringe.remove();
			if (current == goal) {
				do {
					path.add(0, current);
					current = parent.containsKey(current) ? parent.get(current) : null;
				} while (current != null);
				
				break;
			}
			if (!discovered.contains(current)) {
				discovered.add(current);
				for (Vertex<T> neighbor : graph.neighbors(current)) {
					if (!(fringe.contains(neighbor) || discovered.contains(neighbor))) {
						fringe.add(neighbor);
						parent.put(neighbor, current);
					}
					
				}
			}
		}
		return path;
	}

}
