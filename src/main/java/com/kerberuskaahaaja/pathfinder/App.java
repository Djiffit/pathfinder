package com.kerberuskaahaaja.pathfinder;

import com.kerberuskaahaaja.pathfinder.algorithms.Astar;
import com.kerberuskaahaaja.pathfinder.datastructures.PriorityQueue;
import com.kerberuskaahaaja.pathfinder.map.Map;
import com.kerberuskaahaaja.pathfinder.tiles.NormalTile;
import com.sun.glass.ui.SystemClipboard;


public class App
{
    public static void main( String[] args ) {
        Map map = new Map(10, 10);
        Astar astar = new Astar(map);
        System.out.println(astar.solveMap(9,9,0,0).getLowestCost());

        System.out.println("lol");
//        PriorityQueue jono = new PriorityQueue();
//        NormalTile tile = new NormalTile(1, 2);
//        tile.setPriority(1);
//        NormalTile tile2 = new NormalTile(12, 2);
//        tile2.setPriority(15);
//        NormalTile tile3 = new NormalTile(21, 2);
//        tile3.setPriority(31);
//        jono.enqueue(tile);
//        jono.enqueue(tile2);
//        jono.enqueue(tile);
//        jono.enqueue(tile2);
//        jono.enqueue(tile);
//        jono.enqueue(tile);
//        jono.enqueue(tile3);
//
//        jono.enqueue(tile3);
//        jono.enqueue(tile3);
//        jono.enqueue(tile2);
//        System.out.println(jono.poll());
//        System.out.println(jono.poll());
//        System.out.println(jono.poll());
//        System.out.println(jono.poll());
//        System.out.println(jono.poll());
//        System.out.println(jono.poll());
//        System.out.println(jono.poll());
//        System.out.println(jono.poll());
    }
}
