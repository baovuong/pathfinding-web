package com.vuongideas.pathfinding.graph;

import java.util.ArrayList;
import java.util.List;

public class MyGraph<T> {

    private List<Edge<T>> edges;
    private List<Vertex<T>> nodes;

    public MyGraph() {
        edges = new ArrayList<Edge<T>>();
        nodes = new ArrayList<Vertex<T>>();
    }

    public List<Vertex<T>> getNeighbors(Vertex<T> node) {
        List<Vertex<T>> result = new ArrayList<Vertex<T>>();
        for (Edge<T> edge : edges) {
            Vertex<T> other = edge.getOtherNode(node);
            if (other != null) {
                result.add(other);
            }
        }
        return result;
    }

    // getters and setters
    public List<Edge<T>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<T>> edges) {
        this.edges = edges;
    }

    public List<Vertex<T>> getNodes() {
        return nodes;
    }

    public void setNodes(List<Vertex<T>> nodes) {
        this.nodes = nodes;
    }
}
