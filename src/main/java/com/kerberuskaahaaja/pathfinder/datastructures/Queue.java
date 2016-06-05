package com.kerberuskaahaaja.pathfinder.datastructures;

import com.kerberuskaahaaja.pathfinder.tiles.Tile;

public class Queue {

    private int tail;
    private int head;
    private Tile[] queue;

    public Queue() {
        queue = new Tile[10000000];
        tail = 0;
        head = 0;
    }

    /**
     * Lisää alkio jonoon
     * @param tile
     */

    public void enqueue(Tile tile) {
        queue[tail] = tile;
        tail++;
    }

    /**
     * Poistaa ensimmäisen alkion
     * @return
     */

    public Tile poll() {
        Tile tile = queue[head];
        queue[head] = null;
        head++;
        return tile;
    }

    public int size() {
        return tail - head;
    }

}
