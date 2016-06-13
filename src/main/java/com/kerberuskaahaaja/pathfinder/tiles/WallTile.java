package com.kerberuskaahaaja.pathfinder.tiles;

public class WallTile extends Tile {

    public WallTile(int x, int y) {
        super(x, y);
        super.toggleWall();
    }
    public String toString() {
        return "W";
    }
}
