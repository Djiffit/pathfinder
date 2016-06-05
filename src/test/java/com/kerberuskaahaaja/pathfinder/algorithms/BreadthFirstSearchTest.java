package com.kerberuskaahaaja.pathfinder.algorithms;

import com.kerberuskaahaaja.pathfinder.map.Map;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BreadthFirstSearchTest {
    private BreadthFirstSearch breadth;
    @Before
    public void setUp() throws Exception {
        Map map = new Map(10, 10);
        breadth = new BreadthFirstSearch(map);
    }

    @Test
    public void solveMapDiagonal() throws Exception {
        assertEquals(18, breadth.solveMap(9,9,0,0).getLowestCost());
    }

    @Test
    public void solveMapWorksIfStartingSquareIsGoal() throws Exception {
        assertEquals(0, breadth.solveMap(0,0,0,0).getLowestCost());
    }

    @Test
    public void solveMapHorizontal() throws Exception {
        assertEquals(9, breadth.solveMap(0,9,0,0).getLowestCost());
    }

    @Test
    public void solveMapVertical() throws Exception {
        assertEquals(9, breadth.solveMap(9,0,0,0).getLowestCost());
    }
}