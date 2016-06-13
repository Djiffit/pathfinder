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
    private Tile start;
    private Tile goal;

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

    public Tile getStart() {
        return start;
    }

    public void setStart(Tile start) {
        this.start = start;
    }

    public Tile getGoal() {
        return goal;
    }

    public void setGoal(Tile goal) {
        this.goal = goal;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Luo kartan
     */
    public void initializeMap() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height ; j++) {
                tiles[j][i] = new NormalTile(i, j);
            }
        }
        this.start = tiles[0][0];
        this.goal = tiles[height-1][width-1];
        if (start.isWall()) {
            start.toggleWall();
        }
        if (goal.isWall()) {
            goal.toggleWall();
        }
    }

    public void randomize() {
        Random random = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height ; j++) {
                int luku = random.nextInt(100);
                if (luku < 83) {
                    tiles[j][i] = new NormalTile(i, j);
                } else {
                    tiles[j][i] = new WallTile(i, j);
                }
            }
        }
        if (start.isWall()) {
            start.toggleWall();
        }
        if (goal.isWall()) {
            goal.toggleWall();
        }
    }

    /**
     * Palauttaa koordinaateissa olevan ruudun
     * @param x
     * @param y
     * @return
     */
    public Tile getCoordinates(int x, int y) {
        return tiles[y][x];
    }

    /**
     * Palauttaa kaikki jonkin koordinaatin naapuriruudut
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

    public void resetTiles() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height ; j++) {
                if (tiles[j][i].isWall()) {
                    tiles[j][i] = new WallTile(i, j);
                } else {
                    tiles[j][i] = new NormalTile(i, j);
                }
            }
        }
    }

    /**
     * Varmistaa että ei palauteta seiniä
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
     * Estää outofbounds errorit ja lisää koordinaatit listaan
     * @param x
     * @param y
     * @return
     */
    private Tile[] specialCaseNeighbours(int x, int y) {
        Tile[] neighbors = new Tile[4];
        if (outOfBounds(x+1, y)) {
            neighbors[0] = getCoordinates(x+1, y);
        }
        if (outOfBounds(x, y+1)) {
            neighbors[1] = getCoordinates(x, y+1);
        }
        if (outOfBounds(x-1, y)) {
            neighbors[2] = getCoordinates(x-1, y);
        }
        if (outOfBounds(x, y-1)) {
            neighbors[3] = getCoordinates(x, y-1);
        }
        return neighbors;
    }

    private boolean outOfBounds(int i, int y) {
        return (i < width && i >= 0 && y >= 0 && y < height);
    }

    /**
     * Piirtää kartan
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
