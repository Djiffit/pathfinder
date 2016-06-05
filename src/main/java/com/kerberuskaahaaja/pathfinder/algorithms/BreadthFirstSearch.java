package com.kerberuskaahaaja.pathfinder.algorithms;

import com.kerberuskaahaaja.pathfinder.datastructures.PriorityQueue;
import com.kerberuskaahaaja.pathfinder.datastructures.Queue;
import com.kerberuskaahaaja.pathfinder.map.Map;
import com.kerberuskaahaaja.pathfinder.tiles.NormalTile;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;

public class BreadthFirstSearch {
    private Queue consideredTiles;
    private Map map;

    /**
     * Leveyssuuntainen haku
     * @param map
     */
    public BreadthFirstSearch(Map map) {
        this.map = map;
        this.consideredTiles = new Queue();
    }


    /**
     * Ratkaisee algoritmille annetun kartan
     * @param startX
     * @param startY
     * @param goalX
     * @param goalY
     * @return
     */
    public Tile solveMap(int startX, int startY, int goalX, int goalY) {
//        map.draw();
        Tile tile = map.getCoordinates(startX, startY);
        tile.setLowestCost(0);
        consideredTiles.enqueue(tile);
        while (consideredTiles.size() > 0) {
            tile = consideredTiles.poll();
            if (tile.getX() == goalX && tile.getY() == goalY) {
                break;
            }
            evaluateNeighbors(tile);
        }
//        retracePath(map.getCoordinates(goalX, goalY), map.getCoordinates(startX, startY));
        return map.getCoordinates(goalX, goalY);
    }

    /**
     * Käy läpi ruudun naapurit ja lisää ne jonoon
     * @param tile
     */
    private void evaluateNeighbors(Tile tile) {
        for (Tile t : map.getNeighbors(tile)) {
            if (t != null) {
                if (t.getCameFrom() == null) {
                    t.setCameFrom(tile);
                    t.setLowestCost(tile.getLowestCost()+1);
                    consideredTiles.enqueue(t);
                }
            }
        }
    }

    /**
     * Piirtää löydetyn polun
     * @param tile
     * @param start
     */

    private void retracePath(Tile tile, Tile start) {
//        map.draw();
        while (tile != start) {
            tile.setPartOfPath(true);
            tile = tile.getCameFrom();
        }
//        map.draw();
    }

    /**
     * Palauttaa kartan alkuasemaan
     */

    public void resetMap() {
        map.resetTiles();
    }
}
