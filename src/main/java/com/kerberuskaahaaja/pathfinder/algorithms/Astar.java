package com.kerberuskaahaaja.pathfinder.algorithms;

import com.kerberuskaahaaja.pathfinder.datastructures.PriorityQueue;
import com.kerberuskaahaaja.pathfinder.map.Map;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;
import com.kerberuskaahaaja.pathfinder.ui.MapRender;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class Astar {
    private PriorityQueue consideredTiles;
    private long time;
    private Map map;
    private int length = 0;

    /**
     * Astar algoritmi
     * @param map
     */
    public Astar(Map map) {
        this.time = 0;
        this.map = map;
        this.consideredTiles = new PriorityQueue();
    }

    /**
     * Ratkaisee annetun kartan näillä lähtö ja maalikoordinaateilla
     * @param startX
     * @param startY
     * @param goalX
     * @param goalY
     * @return
     */
    public Tile solveMap(int startX, int startY, int goalX, int goalY) {
        map.getCoordinates(startX, startY).setStart(true);
        map.getCoordinates(goalX, goalY).setGoal(true);
        time = System.currentTimeMillis();
        initializationWithFirstTile(startX, startY);
        Tile tile = map.getCoordinates(startX, startY);
//        map.draw();
        while (consideredTiles.size() > 0) {
            tile = consideredTiles.poll();
            if (tile.getX() == goalX && tile.getY() == goalY) {
                break;
            }
            evaluateNeighbors(goalX, goalY, tile);
        }
        time = Math.abs(time - System.currentTimeMillis());
        retracePath(map.getCoordinates(goalX, goalY), map.getCoordinates(startX, startY));
        return tile;
}

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return map;
    }
    /**
     * Tarkistaa onko ruudun naapureihin kulkeva reitti paras, joka on tiedossa ja lisää jonoon
     * @param goalX
     * @param goalY
     * @param tile
     */
    private void evaluateNeighbors(int goalX, int goalY, Tile tile) {
        for (Tile t: map.getNeighbors(tile)) {
            if (t != null) {
                int costOfPath = tile.getLowestCost() + t.getCost();
                if (t.getLowestCost() > costOfPath) {
                    t.setLowestCost(costOfPath);
                    t.setCameFrom(tile);
                    consideredTiles.enqueue(t, (-costOfPath - calculateHeuristics(t.getX(), t.getY(), goalX, goalY)));
                }
            }
        }
    }

    private void retracePath(Tile tile, Tile start) {
        length = 0;
        while (tile != start) {
            length++;
            tile.setPartOfPath(true);
            tile = tile.getCameFrom();
        }
//        map.draw();
    }

    /**
     * Lisää aloitusruudun jonoon
     * @param startX
     * @param startY
     */
    private void initializationWithFirstTile(int startX, int startY) {
        Tile tile = map.getCoordinates(startX, startY);
        consideredTiles.enqueue(tile, 0);
        tile.setLowestCost(0);
    }

    /**
     * Laskee heuristiikan
     * @param x
     * @param y
     * @param goalX
     * @param goalY
     * @return
     */
    private int calculateHeuristics(int x, int y, int goalX, int goalY) {
        return Math.abs(goalX-x) + Math.abs(goalY-y);
    }

    public String toString() {
        System.out.println(Math.abs(time) + " " + length);
        return time+" ms, length of path "+ length;
    }

    public void resetMap() {
        map.resetTiles();
    }
}
