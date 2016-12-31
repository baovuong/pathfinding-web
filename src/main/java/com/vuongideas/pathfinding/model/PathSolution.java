package com.vuongideas.pathfinding.model;

import java.awt.Point;
import java.util.List;

public class PathSolution {
    private List<Point> path;
    
    public PathSolution() {
        
    }

    public List<Point> getPath() {
        return path;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }
}
