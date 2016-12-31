package com.vuongideas.pathfinding.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class PathSolution {
    private List<Point> path;
    private int visited;
    
    public PathSolution() {
        path = new ArrayList<Point>();
    }

    public List<Point> getPath() {
        return path;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }
}
