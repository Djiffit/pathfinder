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
        priority.enqueue(new NormalTile(1, 24), 50);
        priority.enqueue(new NormalTile(13, 2), 10);
        priority.enqueue(new NormalTile(1, 24), 50);
        priority.enqueue(desiredTile, 100);
        priority.enqueue(new NormalTile(1, 24), 50);
        priority.enqueue(new NormalTile(13, 2), 10);
        priority.enqueue(new NormalTile(1, 24), 50);
        assertEquals(desiredTile, priority.poll());
    }

    @Test
    public void pollingWorksWithNegativePriorities() throws Exception {
        Tile desiredTile = new NormalTile(15, -24);
        priority.enqueue(new NormalTile(1, 24), -50);
        priority.enqueue(new NormalTile(13, 2), -10);
        priority.enqueue(new NormalTile(1, 24), -50);
        priority.enqueue(new NormalTile(13, 2), -10);
        priority.enqueue(new NormalTile(1, 24),- 50);
        priority.poll();
        priority.enqueue(desiredTile, -1);
        priority.enqueue(new NormalTile(1, 24), -50);
        priority.enqueue(new NormalTile(13, 2), -10);
        priority.enqueue(new NormalTile(1, 24), -50);
        assertEquals(desiredTile, priority.poll());
    }

    @Test
    public void pollingWorksWithManyEntries() throws Exception {
        Tile desiredTile = new NormalTile(15, -24);
        priority.enqueue(new NormalTile(1, 24), -1);
        priority.enqueue(new NormalTile(1, 24), -1);
        priority.enqueue(new NormalTile(1, 24), -1);
        priority.enqueue(new NormalTile(1, 24), -1);
        priority.poll();
        priority.poll();
        priority.poll();
        priority.enqueue(new NormalTile(13, 2), -2);
        priority.enqueue(new NormalTile(13, 2), -2);
        priority.enqueue(new NormalTile(13, 2), -2);
        priority.enqueue(new NormalTile(13, 2), -2);
        priority.enqueue(new NormalTile(13, 2), -2);
        priority.enqueue(new NormalTile(13, 2), -2);
        priority.enqueue(new NormalTile(13, 2), -2);
        assertEquals(1, priority.poll().getX());
        priority.enqueue(new NormalTile(1, 24), -3);
        priority.enqueue(new NormalTile(1, 24), -3);
        priority.enqueue(new NormalTile(1, 24), -3);
        assertEquals(13, priority.poll().getX());
        priority.enqueue(new NormalTile(1, 24), -3);
        priority.enqueue(new NormalTile(1, 24), -3);
        priority.enqueue(new NormalTile(1, 24), -3);
        assertEquals(13, priority.poll().getX());
        priority.enqueue(new NormalTile(1, 24), -3);
        priority.enqueue(new NormalTile(1, 24), -3);
        priority.enqueue(new NormalTile(1, 24), -3);
        priority.poll();
        assertEquals(13, priority.poll().getX());
        priority.poll();
        priority.poll();
        priority.poll();
        priority.enqueue(new NormalTile(134, 2), -4);
        priority.enqueue(new NormalTile(134, 2), -4);
        priority.enqueue(new NormalTile(134, 2), -4);
        priority.enqueue(new NormalTile(134, 2), -4);
        priority.poll();
        priority.poll();
        priority.enqueue(new NormalTile(134, 2), -4);
        priority.enqueue(new NormalTile(134, 2), -4);
        priority.enqueue(new NormalTile(134, 2), -4);
        priority.poll();
        priority.poll();
        priority.poll();
        assertEquals(1, priority.poll().getX());
        priority.poll();
        priority.poll();
        priority.poll();
        priority.poll();
        priority.enqueue(new NormalTile(1, 24), -5);
        priority.enqueue(new NormalTile(1, 24), -5);
        priority.enqueue(new NormalTile(1, 24), -5);
        priority.enqueue(new NormalTile(1, 24), -5);
        priority.poll();
        priority.enqueue(new NormalTile(1, 24), -6);
        priority.poll();
        priority.enqueue(new NormalTile(1, 24), -6);
        priority.enqueue(new NormalTile(1, 24), -6);
        assertEquals(134, priority.poll().getX());
        priority.poll();
        priority.enqueue(desiredTile, -1);
        priority.enqueue(new NormalTile(1, 24), -6);
        priority.enqueue(new NormalTile(13, 2), -7);
        priority.enqueue(new NormalTile(1, 24), -8);
        assertEquals(desiredTile, priority.poll());
    }

    @Test
    public void enqueueAddsElementToTheQueue() throws Exception {
        Tile desiredTile = new NormalTile(15, 24);
        priority.enqueue(desiredTile, 43);
        assertEquals(1, priority.size());
    }

    @Test
    public void enqueueAddsElementToTheQueueAndItIsReturned() throws Exception {
        Tile desiredTile = new NormalTile(15, 24);
        priority.enqueue(desiredTile, 43);
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

    @Test
    public void startSizeIsZero() throws Exception {
        assertEquals(0, priority.size());
    }

    @Test
    public void pollingEmptyResultsInError() throws Exception {
        assertEquals(0, priority.poll());
    }

}