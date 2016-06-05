package com.kerberuskaahaaja.pathfinder.tiles;

public class NormalTile extends Tile {

    public NormalTile(int x, int y) {
        super(x, y);
    }

    public String toString() {
        Tile tile = super.getCameFrom();
        int x = super.getX();
        int y = super.getY();
        if (tile != null) {
            if (super.isPartOfPath()) {
              return "o";
            } else if (tile.getX() < x ) {
                return "<";
            } else if (tile.getX() > x) {
                return ">";
            } else if (tile.getY() > y) {
                return "v";
            } else {
                return "^";
            }
        } else {
            return "x";
        }
    }
}
