package com.kerberuskaahaaja.pathfinder.map;

import com.kerberuskaahaaja.pathfinder.tiles.*;

import java.util.Random;

/**
 * Kartta
 */
public class Map {
    private Tile[][] tiles;
    private int width;
    private int height;
    private Tile start;
    private Tile goal;

    /**
     * Kartan konstruktori, luo halutunkokoisen kartan
     * @param width levey s
     * @param height korkeus
     */
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[height][width];
        initializeMap();
    }

    /**
     * Palauta alkuruutu
     * @return ruutu
     */

    public Tile getStart() {
        return start;
    }

    /**
     * Aseta alkuruutu
     * @param start ruutu
     */
    public void setStart(Tile start) {
        this.start.setStart(false);
        this.start = start;
        this.start.setStart(true);
    }

    /**
     * Palauta maali
     * @return maali
     */
    public Tile getGoal() {
        return goal;
    }

    /**
     * Aseta maali
     * @param goal maali
      */
    public void setGoal(Tile goal) {
        this.goal.setGoal(false);
        this.goal = goal;
        this.goal.setGoal(true);
    }

    /**
     * Palauta leveys
     * @return Leveys
     */
    public int getWidth() {
        return width;
    }

    /**
     * Palauta korkeus
     * @return korkeus
     */
    public int getHeight() {
        return height;
    }

    /**
     * Luo kartan jossa ei ole seiniä
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

    /**
     * Luo karttaan 17% koko kartasta seiniä
     */
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
     * @param x x koord
     * @param y y koord
     * @return ruutu
     */
    public Tile getCoordinates(int x, int y) {
        return tiles[y][x];
    }

    /**
     * Palauttaa kaikki jonkin koordinaatin naapuriruudut
     * @param tile ruutu
     * @return lista naapureista
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
     * Resetoi kaikki ruudut jotka kuuluvat karttaan
     */
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
     * @param neighbors ruudut jotka tarkistetaan
     * @return Lista korjatuista ruuduista
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
     * @param x x
     * @param y y
     * @return taulukko ruuduista
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

    /**
     * Tarkistaa ollaanko ulkona
     * @param i x koord
     * @param y y koord
     * @return ollaanko ulkona
     */
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
