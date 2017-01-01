package com.vuongideas.pathfinding.graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentAdjacencyListFiniteGraph<T> extends AdjacencyListFiniteGraph<T> {

    public ConcurrentAdjacencyListFiniteGraph(int size) {
        super(size);
        vertices = new CopyOnWriteArrayList<Vertex<T>>();
        edges = new CopyOnWriteArrayList<List<Integer>>();
        for (int i = 0; i < size; i++) {
            edges.add(new CopyOnWriteArrayList<Integer>());
        }
    }

}
