package com.kerberuskaahaaja.pathfinder.datastructures;
import com.kerberuskaahaaja.pathfinder.tiles.NormalTile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NodeTest {
    private Node node;
    @Before
    public void setUp() throws Exception {
        node = new Node(new NormalTile(5,7), 1);
    }

    @Test
    public void newNodeHasDummyNodesAsLeftAndRight() throws Exception {
        assertEquals(-900001, node.getLeft().priority());
        assertEquals(-900001, node.getRight().priority());
    }

    @Test
    public void priorityReturnsPriority() throws Exception {
        assertEquals(1, node.priority());
    }
}