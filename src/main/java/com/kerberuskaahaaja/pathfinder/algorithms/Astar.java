package com.kerberuskaahaaja.pathfinder.algorithms;

import com.kerberuskaahaaja.pathfinder.datastructures.PriorityQueue;
import com.kerberuskaahaaja.pathfinder.map.Map;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;

/**
 *
 */
public class Astar {
    private PriorityQueue consideredTiles;
    private Map map;

    /**
     * Astar algoritmi
     * @param map
     */
    public Astar(Map map) {
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
        initializationWithFirstTile(startX, startY);
        Tile tile = map.getCoordinates(startX, startY);
        map.draw();
        while (consideredTiles.size() > 0) {
            tile = consideredTiles.poll();
            if (tile.getX() == goalX && tile.getY() == goalY) {
                break;
            }
            evaluateNeighbors(goalX, goalY, tile);
        }
        map.draw();
        return tile;
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
                    consideredTiles.enqueue(t, (costOfPath -calculateHeuristics(t.getX(), t.getY(), goalX, goalY)));
                }
            }
        }
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
        return Math.abs(x-goalX) + Math.abs(y-goalY);
    }
}
