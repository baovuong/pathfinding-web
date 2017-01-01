package com.vuongideas.pathfinding.graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentAdjacencyListFiniteGraph<T> extends AdjacencyListFiniteGraph<T> {

    public ConcurrentAdjacencyListFiniteGraph(int size) {
        super(size);
        vertices = new CopyOnWriteArrayList<Vertex<T>>();
        edges = new ArrayList<List<Integer>>(size);
        for (int i = 0; i < size; i++) {
            edges.add(new LinkedList<Integer>());
        }
    }

}
