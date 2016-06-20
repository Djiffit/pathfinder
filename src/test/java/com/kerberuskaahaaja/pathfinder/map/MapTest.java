package com.kerberuskaahaaja.pathfinder.map;

import com.kerberuskaahaaja.pathfinder.tiles.NormalTile;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class MapTest {
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

    @Test
    public void mapIsCreatedAtRightSize() throws Exception {
        assertEquals(10, map.getWidth());
        assertEquals(10, map.getHeight());
    }

    @Test
    public void mapInitializationCreatesTilesToMap() throws Exception {
        map.initializeMap();
        assertEquals(1, map.getCoordinates(1,1).getX());
        assertEquals(1, map.getCoordinates(1,1).getY());
    }

    @Test
    public void getNeighbors() throws Exception {
        assertEquals(4, map.getNeighbors(map.getCoordinates(1,1)).length);
    }

    @Test
    public void resetTilesResetsTiles() throws Exception {
        NormalTile tile = new NormalTile(55,55);
        map.getCoordinates(1,1).setCameFrom(tile);
        map.resetTiles();
        assertNotSame(tile, map.getCoordinates(1,1).getCameFrom());
    }

    @Test
    public void resetTilesResetsTileData() throws Exception {
        map.getCoordinates(1,1).setLowestCost(1);
        map.resetTiles();
        assertNotSame(1, map.getCoordinates(1,1).getLowestCost());
    }

}