package com.kerberuskaahaaja.pathfinder.algorithms;

import com.kerberuskaahaaja.pathfinder.map.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AstarTest {
    private Astar astar;
    @Before
    public void setUp() throws Exception {
        Map map = new Map(10, 10);
        astar = new Astar(map);
    }

    @Test
    public void solveMapDiagonal() throws Exception {
        assertEquals(18, astar.solveMap(9,9,0,0).getLowestCost());
    }

    @Test
    public void solveMapWorksIfStartingSquareIsGoal() throws Exception {
        assertEquals(0, astar.solveMap(0,0,0,0).getLowestCost());
    }

    @Test
    public void solveMapHorizontal() throws Exception {
        assertEquals(9, astar.solveMap(0,9,0,0).getLowestCost());
    }

    @Test
    public void solveMapVertical() throws Exception {
        assertEquals(9, astar.solveMap(9,0,0,0).getLowestCost());
    }
}