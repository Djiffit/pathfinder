package com.kerberuskaahaaja.pathfinder.datastructures;

import com.kerberuskaahaaja.pathfinder.tiles.NormalTile;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;

/**
 *
 */
public class Node {

    private Node left;
    private Node right;
    private Tile tile;
    private int priority;

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    /**
     *
     * @param tile
     * @param priority
     */

    public Node(Tile tile, int priority) {
        this.tile = tile;
        this.priority = priority;
        createEmptyNodes();
    }

    /**
     *
     */

    private void createEmptyNodes() {
        if (priority() >= -9000) {
            Tile faketile = new NormalTile(1,2);
            this.left = new Node(faketile, -900001);
            this.right = new Node(faketile,-900001);
        }
    }

    public int priority() {
        return priority;
    }

    public void setPriority(int prio) {
        this.priority = prio;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Tile getTile() {
        return this.tile;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
