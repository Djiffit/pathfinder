package com.kerberuskaahaaja.pathfinder.datastructures;

import com.kerberuskaahaaja.pathfinder.tiles.Tile;

public class Queue {

    private int tail;
    private int head;
    private Tile[] queue;
    private int size;

    /**
     * Perus jono, alustetaan 10 kokoiseksi
     */
    public Queue() {
        size = 10;
        queue = new Tile[10];
        tail = 0;
        head = 0;
    }

    /**
     * Lisää alkio jonoon ja kasvattaa jonoa jos se on liian pieni
     * @param tile lisättävä
     */

    public void enqueue(Tile tile) {
        queue[tail] = tile;
        tail++;
        if (tail == size-2) {
            Tile[] newqueue = new Tile[size*2];
            size = size*2;
            copyArray(newqueue);
            tail = size() -1;
            head = 0;
        }
    }

    /**
     * Kasvata taulukkoa
     * @param newnodes uusi taulukko
     */
    private void copyArray(Tile[] newnodes) {
        int j = 0;
        for (int i = head; i <= tail ; i++, j++) {
            newnodes[j] = queue[i];
        }
        this.queue = newnodes;
    }

    /**
     * Poistaa ensimmäisen alkion
     * @return alkio
     */

    public Tile poll() {
        Tile tile = queue[head];
        queue[head] = null;
        head++;
        return tile;
    }

    /**
     * Palauttaa jonon koon
     * @return koko
     */
    public int size() {
        return tail - head;
    }

}
