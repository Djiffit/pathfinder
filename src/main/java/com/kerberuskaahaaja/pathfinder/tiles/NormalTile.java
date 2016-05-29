package com.kerberuskaahaaja.pathfinder.tiles;

public class NormalTile implements Tile {

    private int cost;
    private int x;
    private int y;
    private int priority;
    private Tile cameFrom;
    private int lowestCost;

    public NormalTile(int x, int y) {
        this.cost = 1;
        this.x = x;
        this.y = y;
        this.lowestCost = Integer.MAX_VALUE;
        this.cameFrom = null;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isWall() {return false;}

    public int getPriority() {
        return this.priority;
    }

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

    @Override
    public int getCost() {
        return this.cost;
    }

    public String toString() {
        if (cameFrom != null) {
            if (cameFrom.getX() < x ) {
                return "<";
            } else if (cameFrom.getX() > x) {
                return ">";
            } else if (cameFrom.getY() > y) {
                return "v";
            } else {
                return "^";
            }
        } else {
            return "x";
        }
    }
}
