package com.kerberuskaahaaja.pathfinder.tiles;

import com.kerberuskaahaaja.pathfinder.map.Map;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


public class TileTest {
    private Map map;
    @Before
    public void setUp() throws Exception {
        map = new Map(10, 10);
    }

    @Test
    public void getCoordinatesReturnsTileInDesiredCoordinates() throws Exception {
        Tile tile = map.getCoordinates(1,1);
        assertEquals(1, tile.getX());
        assertEquals(1, tile.getY());
    }

}