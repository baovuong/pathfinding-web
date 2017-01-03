package com.vuongideas.pathfinding.algorithm;

import java.awt.Point;

import com.vuongideas.pathfinding.graph.Graph;
import com.vuongideas.pathfinding.graph.Vertex;


public class ManhattanDistanceHeuristic implements Heuristic<Point> {
    public double perform(Graph<Point> graph, Vertex<Point> current) {
        double dx = Math.abs(current.getValue().x - graph.getGoal().getValue().x);
        double dy = Math.abs(current.getValue().y - graph.getGoal().getValue().y);
        return dx + dy;
    }
}