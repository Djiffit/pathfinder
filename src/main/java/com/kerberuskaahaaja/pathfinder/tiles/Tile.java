package com.kerberuskaahaaja.pathfinder.tiles;

public abstract class Tile {

    private int cost;
    private int x;
    private int y;
    private Tile cameFrom;
    private int lowestCost;
    private boolean partOfPath;
    private String toString;

    public Tile(int x, int y) {
        this.cost = 1;
        this.x = x;
        this.y = y;
        this.lowestCost = Integer.MAX_VALUE;
        this.cameFrom = null;
        this.partOfPath = false;
    }

    public boolean isPartOfPath() {
        return partOfPath;
    }

    public void setPartOfPath(boolean partOfPath) {
        this.partOfPath = partOfPath;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isWall() {return false;}

    public Tile getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(Tile cameFrom) {
        this.cameFrom = cameFrom;
    }

    public int getLowestCost() {
        return lowestCost;
    }

    public void setLowestCost(int lowestCost) {
        this.lowestCost = lowestCost;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCost() {
        return this.cost;
    }

    public void reset() {
        this.lowestCost = Integer.MAX_VALUE;
        this.cameFrom = null;
        this.partOfPath = false;
    }
}
