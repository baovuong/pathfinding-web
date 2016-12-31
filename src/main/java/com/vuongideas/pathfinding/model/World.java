package com.vuongideas.pathfinding.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.vuongideas.pathfinding.graph.AdjacencyListFiniteGraph;
import com.vuongideas.pathfinding.graph.FiniteGraph;
import com.vuongideas.pathfinding.graph.Graph;
import com.vuongideas.pathfinding.graph.Vertex;

public class World {
    private int maxX;
    private int maxY;
    private Point beginning;
    private Point destination;
    private List<Point> obstacles;

    public World() {
        this(100, 100);
    }

    public World(int x, int y) {
        this(x, y, null, null);
    }

    public World(int x, int y, Point beginning, Point destination) {
        obstacles = new ArrayList<Point>();
        maxX = x;
        maxY = y;
        this.beginning = beginning;
        this.destination = destination;
    }

    public Graph<Point> constructGraph() {
        FiniteGraph<Point> graph = new AdjacencyListFiniteGraph<Point>((maxX) * (maxY) - obstacles.size());

        // creating vertices
        Map<Point, Vertex<Point>> vertices = new HashMap<Point, Vertex<Point>>();

        // NOTE complexity: O(n^2)
        for (int x = 1; x <= maxX; x++) {
            for (int y = 1; y <= maxY; y++) {
                Point p = new Point(x, y);
                if (!obstacles.contains(p)) {
                    Vertex<Point> v = graph.addVertex(p);
                    if (p.equals(beginning)) {
                        graph.setStart(v);
                    } else if (p.equals(destination)) {
                        graph.setGoal(v);
                    }
                    vertices.put(p, v);
                }
            }
        }

        // creating edges

        // NOTE complexity: O(n)
        for (Vertex<Point> v : vertices.values()) {

            // look for neighbors
            Point[] points = new Point[4];
            points[0] = new Point(v.getValue().x - 1, v.getValue().y);
            points[1] = new Point(v.getValue().x + 1, v.getValue().y);
            points[2] = new Point(v.getValue().x, v.getValue().y - 1);
            points[3] = new Point(v.getValue().x, v.getValue().y + 1);

            // add the existing neighbors
            for (Point p : points) {
                if (vertices.containsKey(p)) {
                    graph.addEdge(v, vertices.get(p));
                }
            }

        }
        return graph;
    }

    // getters and setters

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public Point getBeginning() {
        return beginning;
    }

    public void setBeginning(Point beginning) {
        this.beginning = beginning;
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }

    public List<Point> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Point> obstacles) {
        this.obstacles = obstacles;
    }

}
