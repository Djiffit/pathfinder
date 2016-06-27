package com.kerberuskaahaaja.pathfinder.tiles;

public class WallTile extends Tile {

    /**
     * Seinäruutu
     * @param x x
     * @param y y
     */
    public WallTile(int x, int y) {
        super(x, y);
        super.toggleWall();
    }
}
