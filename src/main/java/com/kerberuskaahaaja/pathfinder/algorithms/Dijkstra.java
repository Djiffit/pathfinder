package com.kerberuskaahaaja.pathfinder.algorithms;

import com.kerberuskaahaaja.pathfinder.datastructures.PriorityQueue;
import com.kerberuskaahaaja.pathfinder.map.Map;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;


public class Dijkstra {
    private PriorityQueue consideredTiles;
    private Map map;

    /**
     * Dijkstra
     * @param map
     */
    public Dijkstra(Map map) {
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
        Tile tile = initializeWithFirstNode(startX, startY);
        while (consideredTiles.size() > 0) {
            tile = consideredTiles.poll();
            if (tile.getX() == goalX && tile.getY() == goalY) {
                System.out.println("hmhmhm");
                break;
            }
            evaluateNeighbors(tile);
        }
//        retracePath(map.getCoordinates(goalX, goalY), map.getCoordinates(startX, startY));
        return map.getCoordinates(goalX, goalY);
    }

    /**
     * Käy läpi löydetyn polun
     * @param tile
     * @param start
     */

    private void retracePath(Tile tile, Tile start) {
        while (tile != start) {
            tile.setPartOfPath(true);
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

    public void resetMap() {
        map.resetTiles();
    }
}
