package com.vuongideas.pathfinding.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vuongideas.pathfinding.algorithm.BreadthFirstSearchAlgorithm;
import com.vuongideas.pathfinding.algorithm.DepthFirstSearchAlgorithm;
import com.vuongideas.pathfinding.algorithm.GreedySearchAlgorithm;
import com.vuongideas.pathfinding.algorithm.Heuristic;
import com.vuongideas.pathfinding.algorithm.SearchAlgorithm;
import com.vuongideas.pathfinding.graph.Graph;
import com.vuongideas.pathfinding.graph.Vertex;
import com.vuongideas.pathfinding.model.PathPoint;
import com.vuongideas.pathfinding.model.PathProblem;
import com.vuongideas.pathfinding.model.PathSolution;
import com.vuongideas.pathfinding.model.World;

@RestController
@RequestMapping("/graph")
public class GraphRestController {
    
    private static final Map<String, SearchAlgorithm<Point>> algorithms;
    
    static {
        algorithms = new HashMap<String, SearchAlgorithm<Point>>();
        algorithms.put("breadthfirst", new BreadthFirstSearchAlgorithm<Point>());
        algorithms.put("depthfirst", new DepthFirstSearchAlgorithm<Point>());
        algorithms.put("greedy", new GreedySearchAlgorithm<Point>(new Heuristic<Point>() {

            @Override
            public double perform(Graph<Point> graph, Vertex<Point> current) {
                Point start = current.getValue();
                Point goal = graph.getGoal().getValue();
                return start.distance(goal);
            }
            
        }));
    }
    
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String test(@PathVariable String name) {
        String result = "Hello " + name;
        return result;
    }
    
    @RequestMapping(value = "/solve/{algorithm}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public PathSolution solvePath(@PathVariable String algorithm, @RequestBody PathProblem problem) {
        PathSolution solution = new PathSolution();
        
        if (algorithm != null && algorithms.containsKey(algorithm)) {
            // construct the world
            World world = new World();
            world.setBeginning(problem.getStart());
            world.setDestination(problem.getGoal());
            world.setObstacles(problem.getObstacles());
            world.setMaxX(problem.getWidth());
            world.setMaxY(problem.getHeight());
            
            List<Vertex<Point>> path = world.constructGraph().solve(algorithms.get(algorithm));
            for (Vertex<Point> vertex : path) {
                solution.getPath().add(vertex.getValue());
            }
        }
        return solution;
    }
}
