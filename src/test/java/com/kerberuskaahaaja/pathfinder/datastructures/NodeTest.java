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
        assertEquals(Integer.MIN_VALUE, node.getLeft().priority());
        assertEquals(Integer.MIN_VALUE, node.getRight().priority());
    }

    @Test
    public void priorityReturnsPriority() throws Exception {
        assertEquals(1, node.priority());
    }
}