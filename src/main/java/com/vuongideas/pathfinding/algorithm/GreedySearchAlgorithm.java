package com.vuongideas.pathfinding.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.vuongideas.pathfinding.graph.Graph;
import com.vuongideas.pathfinding.graph.Vertex;

public class GreedySearchAlgorithm<T> implements SearchAlgorithm<T> {
	private Heuristic<T> heuristic;
	
	public GreedySearchAlgorithm(Heuristic<T> heuristic) {
		this.heuristic = heuristic;
	}
	
	@Override
	public List<Vertex<T>> search(final Graph<T> graph) {
		List<Vertex<T>> path = new ArrayList<Vertex<T>>();
		Queue<Vertex<T>> fringe = new PriorityQueue<Vertex<T>>(11, new Comparator<Vertex<T>>() {

			@Override
			public int compare(Vertex<T> o1, Vertex<T> o2) {
				return (int) (heuristic.perform(graph, o1) - heuristic.perform(graph, o2));
			}
			
		});
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
