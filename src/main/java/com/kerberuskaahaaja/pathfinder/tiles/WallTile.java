package com.kerberuskaahaaja.pathfinder.tiles;

public class WallTile extends Tile {

    public WallTile(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isWall() {return true;}

    public String toString() {
        return "W";
    }
}
