package com.kerberuskaahaaja.pathfinder.tiles;

public interface Tile {
    int getCost();
    void setPriority(int priority);
    int getX();
    int getY();
    Tile getCameFrom();
    void setCameFrom(Tile cameFrom);
    int getLowestCost();
    void setLowestCost(int lowestCost);
    boolean isWall();
    int getPriority();
}
