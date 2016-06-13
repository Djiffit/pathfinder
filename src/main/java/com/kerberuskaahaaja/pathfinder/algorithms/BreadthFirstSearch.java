package com.kerberuskaahaaja.pathfinder.algorithms;

import com.kerberuskaahaaja.pathfinder.datastructures.PriorityQueue;
import com.kerberuskaahaaja.pathfinder.datastructures.Queue;
import com.kerberuskaahaaja.pathfinder.map.Map;
import com.kerberuskaahaaja.pathfinder.tiles.NormalTile;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;

public class BreadthFirstSearch {
    private Queue consideredTiles;
    private Map map;
    private long time;
    private int length = 0;

    /**
     * Leveyssuuntainen haku
     * @param map
     */
    public BreadthFirstSearch(Map map) {
        this.time = 0;
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
        map.getCoordinates(startX, startY).setStart(true);
        map.getCoordinates(goalX, goalY).setGoal(true);
        time = System.currentTimeMillis();
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
        time = Math.abs(time - System.currentTimeMillis());
        retracePath(map.getCoordinates(goalX, goalY), map.getCoordinates(startX, startY));
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
        length = 0;
        while (tile != start) {
            length++;
            tile.setPartOfPath(true);
            tile = tile.getCameFrom();
        }
//        map.draw();
    }

    public String toString() {
        System.out.println(Math.abs(time));
        return time+" ms, length of path "+ length;
    }

    /**
     * Palauttaa kartan alkuasemaan
     */

    public void resetMap() {
        map.resetTiles();
    }
}
