package com.vuongideas.pathfinding.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class PathProblem {
    
    
    private List<Point> obstacles;
    private Point start;
    private Point goal;
    private int height;
    private int width;
    
    public PathProblem() {
        this(0, 0, new Point(), new Point());
    }
    
    public PathProblem(int height, int width, Point start, Point goal) {
        this(height, width, start, goal, new ArrayList<Point>());
    }
    public PathProblem(int height, int width, Point start, Point goal, List<Point> obstacles) {
        this.height = height;
        this.width = width;
        this.start = start;
        this.goal = goal;
        this.obstacles = obstacles;
    }

    public List<Point> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Point> obstacles) {
        this.obstacles = obstacles;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getGoal() {
        return goal;
    }

    public void setGoal(Point goal) {
        this.goal = goal;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
