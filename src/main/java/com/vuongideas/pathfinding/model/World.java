package com.vuongideas.pathfinding.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.vuongideas.pathfinding.graph.AdjacencyListFiniteGraph;
import com.vuongideas.pathfinding.graph.ConcurrentAdjacencyListFiniteGraph;
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
    
    private class VertexWorkerThread implements Runnable {
        private Graph<Point> graph;
        private int x;
        private List<Point> obstacles;
        private Point beginning;
        private Point destination;
        private Map<Point, Vertex<Point>> vertices;
        
        public VertexWorkerThread(Graph<Point> graph, int x, List<Point> obstacles, Point beginning, Point destination, Map<Point, Vertex<Point>> vertices) {
            this.graph = graph;
            this.x = x;
            this.obstacles = obstacles;
            this.beginning = beginning;
            this.destination = destination;
            this.vertices = vertices;
        }
        
        @Override
        public void run() {
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
    }
    
    private class EdgeWorkerThread implements Runnable {
        private Map<Point, Vertex<Point>> vertices;
        private Vertex<Point> vertex;
        private Graph<Point> graph;
        
        public EdgeWorkerThread(Vertex<Point> vertex, Graph<Point> graph, Map<Point, Vertex<Point>> vertices) {
            this.vertex = vertex;
            this.graph = graph;
            this.vertices = vertices;
        }
        
        @Override
        public void run() {
            // TODO Auto-generated method stub
            // look for neighbors
            Point[] points = new Point[4];
            points[0] = new Point(vertex.getValue().x - 1, vertex.getValue().y);
            points[1] = new Point(vertex.getValue().x + 1, vertex.getValue().y);
            points[2] = new Point(vertex.getValue().x, vertex.getValue().y - 1);
            points[3] = new Point(vertex.getValue().x, vertex.getValue().y + 1);

            // add the existing neighbors
            for (Point p : points) {
                if (vertices.containsKey(p)) {
                    graph.addEdge(vertex, vertices.get(p));
                }
            }            
        }
        
    }
    
    public Graph<Point> constructGraph() {
        FiniteGraph<Point> graph = new ConcurrentAdjacencyListFiniteGraph<Point>((maxX) * (maxY) - obstacles.size());

        // creating vertices
        Map<Point, Vertex<Point>> vertices = new ConcurrentHashMap<Point, Vertex<Point>>();

        // NOTE complexity: O(n^2)
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<Object>> todo = new ArrayList<Callable<Object>>(maxX);
        for (int x = 1; x <= maxX; x++) {
            todo.add(Executors.callable(new VertexWorkerThread(graph, x, obstacles, beginning, destination, vertices)));
//            for (int y = 1; y <= maxY; y++) {
//                Point p = new Point(x, y);
//                if (!obstacles.contains(p)) {
//                    Vertex<Point> v = graph.addVertex(p);
//                    if (p.equals(beginning)) {
//                        graph.setStart(v);
//                    } else if (p.equals(destination)) {
//                        graph.setGoal(v);
//                    }
//                    vertices.put(p, v);
//                }
//            }
        }
        try {
            executor.invokeAll(todo);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // creating edges
        todo = new ArrayList<Callable<Object>>(vertices.size());
        
        // NOTE complexity: O(n)
        for (Vertex<Point> v : vertices.values()) {

//            // look for neighbors
//            Point[] points = new Point[4];
//            points[0] = new Point(v.getValue().x - 1, v.getValue().y);
//            points[1] = new Point(v.getValue().x + 1, v.getValue().y);
//            points[2] = new Point(v.getValue().x, v.getValue().y - 1);
//            points[3] = new Point(v.getValue().x, v.getValue().y + 1);
//
//            // add the existing neighbors
//            for (Point p : points) {
//                if (vertices.containsKey(p)) {
//                    graph.addEdge(v, vertices.get(p));
//                }
//            }
            
            todo.add(Executors.callable(new EdgeWorkerThread(v, graph, vertices)));
        }
        
        try {
            executor.invokeAll(todo);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
