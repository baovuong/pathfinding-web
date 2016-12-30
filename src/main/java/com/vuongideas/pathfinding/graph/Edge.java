package com.vuongideas.pathfinding.graph;

public class Edge<T> {
	private double weight;
	private Vertex<T> node1;
	private Vertex<T> node2;
	
	public Edge() {
		this(null, null);
	}
	
	public Edge(Vertex<T> node1, Vertex<T> node2) {
		this(node1, node2, 1);
	}
	
	public Edge(Vertex<T> node1, Vertex<T> node2, double weight) {
		this.node1 = node1;
		this.node2 = node2;
		this.weight = weight;
	}
	
	public Vertex<T> getOtherNode(Vertex<T> node) {
		return node == node1 ?  node2 : (node == node2 ? node1 : null);
	}
	
	public boolean contains(Vertex<T> node) {
		return getOtherNode(node) != null;
	}
	
	// getters and setters
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Vertex<T> getNode1() {
		return node1;
	}

	public void setNode1(Vertex<T> node1) {
		this.node1 = node1;
	}

	public Vertex<T> getNode2() {
		return node2;
	}

	public void setNode2(Vertex<T> node2) {
		this.node2 = node2;
	}
	
}
