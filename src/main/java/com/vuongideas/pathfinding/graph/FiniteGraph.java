package com.vuongideas.pathfinding.graph;

public abstract class FiniteGraph<T> implements Graph<T> {
	public FiniteGraph(int size) {
	}
	
	public abstract void setVertex(int i, Vertex<T> v);
}
