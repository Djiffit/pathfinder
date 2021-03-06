package com.kerberuskaahaaja.pathfinder.algorithms;

import com.kerberuskaahaaja.pathfinder.datastructures.PriorityQueue;
import com.kerberuskaahaaja.pathfinder.map.Map;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;


public class Dijkstra {
    private PriorityQueue consideredTiles;
    private Map map;
    private int length = 0;
    private long time;

    /**
     * Dijkstra
     * @param map
     */
    public Dijkstra(Map map) {
        this.time = 0;
        this.map = map;
        this.consideredTiles = new PriorityQueue();
    }

    /**
     * Ratkaisee annetun kartan koordinaateilla
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
        Tile tile = initializeWithFirstNode(startX, startY);
        while (consideredTiles.size() > 0) {
            tile = consideredTiles.poll();
            if (tile.getX() == goalX && tile.getY() == goalY) {
                break;
            }
            evaluateNeighbors(tile);
        }
        time = Math.abs(time - System.currentTimeMillis());
        retracePath(map.getCoordinates(goalX, goalY), map.getCoordinates(startX, startY));
        return map.getCoordinates(goalX, goalY);
    }

    /**
     * Käy läpi löydetyn polun
     * @param tile
     * @param start
     */

    private void retracePath(Tile tile, Tile start) {
        length = 0;
        while (tile != start) {
            tile.setPartOfPath(true);
            length++;
            tile = tile.getCameFrom();
        }
    }

    /**
     * Lisää ruudukot jonoon, jos saatu etäisyys on pienempi kuin aikaisempi
     * @param tile
     */

    private void evaluateNeighbors(Tile tile) {
        for (Tile t : map.getNeighbors(tile)) {
            if (t != null) {
                int costOfPath = tile.getLowestCost() + t.getCost();
                if (costOfPath < t.getLowestCost()) {
                    t.setLowestCost(costOfPath);
                    t.setCameFrom(tile);
                    consideredTiles.enqueue(t, -costOfPath);
                }
            }
        }
    }

    /**
     * Lisää ensimmäisen ruudun
     * @param startX
     * @param startY
     * @return
     */

    private Tile initializeWithFirstNode(int startX, int startY) {
        Tile firstTile = map.getCoordinates(startX, startY);
        firstTile.setLowestCost(0);
        consideredTiles.enqueue(firstTile, 0);
        return firstTile;
    }

    public String toString() {

        System.out.println(Math.abs(time) + " " + length);

        return time+" ms, length of path "+ length+ " height: " + map.getHeight() + " width: " +map.getWidth();
    }

    /**
     * Resetoi kartan kaikki ruudut
     */
    public void resetMap() {
        map.resetTiles();
    }
}
