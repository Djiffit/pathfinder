package com.kerberuskaahaaja.pathfinder.datastructures;

import com.kerberuskaahaaja.pathfinder.tiles.NormalTile;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueueTest {
    private Queue queue;
    @Before
    public void setUp() throws Exception {
        queue = new Queue();
    }

    @Test
    public void pollReturnsAnElementFromQueue() throws Exception {
        Tile tile = new NormalTile(1,2);
        queue.enqueue(tile);
        assertEquals(2, queue.poll().getY());
    }
    @Test
    public void sizeReturnsAmountOfElements() throws Exception {
        Tile tile = new NormalTile(1,2);
        queue.enqueue(tile);
        queue.enqueue(tile);
        queue.enqueue(tile);
        queue.enqueue(tile);
        queue.enqueue(tile);
        assertEquals(5, queue.size());
    }

    @Test
    public void sizeIsZeroForEmptyQueue() throws Exception {
        assertEquals(0, queue.size());
    }

    @Test
    public void pollReturnsFirstElementAddedToQueue() throws Exception {
        Tile tile = new NormalTile(1,2);
        queue.enqueue(tile);
        queue.enqueue(new NormalTile(3,8));
        queue.enqueue(new NormalTile(3,8));
        queue.enqueue(new NormalTile(3,8));
        assertEquals(2, queue.poll().getY());
    }



}