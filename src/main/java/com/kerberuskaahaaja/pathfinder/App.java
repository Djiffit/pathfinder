package com.kerberuskaahaaja.pathfinder;

import com.kerberuskaahaaja.pathfinder.algorithms.Astar;
import com.kerberuskaahaaja.pathfinder.algorithms.BreadthFirstSearch;
import com.kerberuskaahaaja.pathfinder.algorithms.Dijkstra;
import com.kerberuskaahaaja.pathfinder.datastructures.PriorityQueue;
import com.kerberuskaahaaja.pathfinder.map.Map;
import com.kerberuskaahaaja.pathfinder.tiles.NormalTile;
import com.sun.glass.ui.SystemClipboard;


public class App
{
    public static void main( String[] args ) {
        Map map = new Map(2000, 500);

        Dijkstra dijkstra = new Dijkstra(map);
        dijkstra.resetMap();
        long aikaAlussaDijkstra = System.currentTimeMillis();
        System.out.println(dijkstra.solveMap(0,0,1999,499).getLowestCost());
        long aikaLopussaDijkstra = System.currentTimeMillis();
        System.out.println("Dijkstra: " + (aikaLopussaDijkstra - aikaAlussaDijkstra));

        Astar astar = new Astar(map);
        astar.resetMap();
        long aikaAlussaAstar = System.currentTimeMillis();
        System.out.println(astar.solveMap(0,0,1999,499).getLowestCost());
        long aikaLopussaAstar = System.currentTimeMillis();
        System.out.println("Astar: " + (aikaLopussaAstar-aikaAlussaAstar) );

        BreadthFirstSearch breadth = new BreadthFirstSearch(map);
        breadth.resetMap();
        long aikaAlussaBreadth = System.currentTimeMillis();
        System.out.println(breadth.solveMap(0,0,1999,499).getLowestCost());
        long aikaLopussaBreadth = System.currentTimeMillis();
        System.out.println("Breadth: " + (aikaLopussaBreadth-aikaAlussaBreadth) );

        BreadthFirstSearch breadth2 = new BreadthFirstSearch(map);
        breadth.resetMap();
        long aikaAlussaBreadth2 = System.currentTimeMillis();
        System.out.println(breadth2.solveMap(0,0,1999,499).getLowestCost());
        long aikaLopussaBreadth2 = System.currentTimeMillis();
        System.out.println("Breadth: " + (aikaLopussaBreadth-aikaAlussaBreadth) );

        Dijkstra dijkstra2 = new Dijkstra(map);
        dijkstra.resetMap();
        long aikaAlussaDijkstra2 = System.currentTimeMillis();
        System.out.println(dijkstra2.solveMap(0,0,1999,499).getLowestCost());
        long aikaLopussaDijkstra2 = System.currentTimeMillis();
        System.out.println("Dijkstra: " + (aikaLopussaDijkstra - aikaAlussaDijkstra));

        Astar astar2 = new Astar(map);
        astar.resetMap();
        long aikaAlussaAstar2 = System.currentTimeMillis();
        System.out.println(astar2.solveMap(0,0,1999,499).getLowestCost());
        long aikaLopussaAstar2 = System.currentTimeMillis();
        System.out.println("Astar: " + (aikaLopussaAstar-aikaAlussaAstar) );


//        PriorityQueue jono = new PriorityQueue();
//        NormalTile tile = new NormalTile(1, 2);
//        NormalTile tile2 = new NormalTile(12, 2);
//        NormalTile tile3 = new NormalTile(21, 2);
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
