package com.vuongideas.pathfinding.graph;

import java.util.List;

import com.vuongideas.pathfinding.algorithm.SearchAlgorithm;

public interface Graph<T> {
	public Vertex<T> getStart();
	public Vertex<T> getGoal();
	public void setStart(Vertex<T> start);
	public void setGoal(Vertex<T> goal);
	public boolean adjacent(Vertex<T> v1, Vertex<T> v2);
	public List<Vertex<T>> neighbors(Vertex<T> v);
	public void addVertex(Vertex<T> v);
	public Vertex<T> addVertex(T value);
	public void removeVertex(Vertex<T> v);
	public void addEdge(Vertex<T> v1, Vertex<T> v2);
	public void removeEdge(Vertex<T> v1, Vertex<T> v2);
	public List<Vertex<T>> solve(SearchAlgorithm<T> algorithm);
}
