package com.kerberuskaahaaja.pathfinder.datastructures;

import com.kerberuskaahaaja.pathfinder.tiles.NormalTile;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;
import javafx.scene.layout.Priority;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class PriorityQueueTest {
    private PriorityQueue priority;
    @Before
    public void setUp() throws Exception {
        priority = new PriorityQueue();
    }

    @Test
    public void pollGivesTheHighestPriorityElement() throws Exception {
        Tile desiredTile = new NormalTile(15, 24);
        priority.enqueue(new NormalTile(1, 24), 50);
        priority.enqueue(new NormalTile(13, 2), 10);
        priority.enqueue(desiredTile, 100);
        assertEquals(desiredTile, priority.poll());
    }

    @Test
    public void enqueueAddsElementToTheQueue() throws Exception {
        Tile desiredTile = new NormalTile(15, 24);
        priority.enqueue(desiredTile, 43);
        assertEquals(1, priority.size());
        assertEquals(desiredTile, priority.poll());
    }

    @Test
    public void sizeReturnsNumberOfElements() throws Exception {
        priority.enqueue(new NormalTile(1, 24), 530);
        priority.enqueue(new NormalTile(13, 2), 130);
        priority.enqueue(new NormalTile(1, 24), 502);
        priority.enqueue(new NormalTile(13, 2), 130);
        priority.enqueue(new NormalTile(1, 24), 530);
        priority.enqueue(new NormalTile(13, 2), 106);
        priority.enqueue(new NormalTile(1, 24), 540);
        priority.enqueue(new NormalTile(13, 2), 105);
        assertEquals(8, priority.size());
    }

}