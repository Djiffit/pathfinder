package com.kerberuskaahaaja.pathfinder.map;

import com.kerberuskaahaaja.pathfinder.tiles.*;

import java.util.Random;

/**
 *
 */
public class Map {
    private Tile[][] tiles;
    private int width;
    private int height;

    /**
     *
     * @param width
     * @param height
     */
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[height][width];
        initializeMap();
    }

    /**
     *
     */
    private void initializeMap() {
        Random random = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height ; j++) {
                int luku = random.nextInt(100);
                if (luku < 80) {
                    tiles[j][i] = new NormalTile(i, j);
                } else {
                    tiles[j][i] = new WallTile(i, j);
                }
            }
        }
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public Tile getCoordinates(int x, int y) {
        return tiles[y][x];
    }

    /**
     *
     * @param tile
     * @return
     */
    public Tile[] getNeighbors(Tile tile) {
        Tile[] neighbors = new Tile[3];
        int x = tile.getX();
        int y = tile.getY();
        neighbors = specialCaseNeighbours(x, y);
        neighbors = checkForWalls(neighbors);
        return neighbors;
    }

    /**
     *
     * @param neighbors
     * @return
     */
    private Tile[] checkForWalls(Tile[] neighbors) {
        for (int i = 0; i < 4; i++) {
            if (neighbors[i] != null && neighbors[i].isWall()) {
                neighbors[i] = null;
            }
        }
        return neighbors;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    private Tile[] specialCaseNeighbours(int x, int y) {
        Tile[] neighbors = new Tile[4];
        try { neighbors[0] = getCoordinates(x+1, y);
        } catch(Exception e) {
        }
        try { neighbors[1] = getCoordinates(x, y+1);
        } catch(Exception e) {
        }
        try { neighbors[2] = getCoordinates(x-1, y);
        } catch(Exception e) {
        }
        try { neighbors[3] = getCoordinates(x, y-1);
        } catch(Exception e) {
        }
        return neighbors;
    }

    /**
     *
     */
    public void draw() {
        System.out.println("Map:");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(tiles[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}