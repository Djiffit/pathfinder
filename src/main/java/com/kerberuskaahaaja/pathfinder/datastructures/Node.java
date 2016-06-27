package com.kerberuskaahaaja.pathfinder.datastructures;

import com.kerberuskaahaaja.pathfinder.tiles.NormalTile;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;

/**
 * Solmuluokka, joita käytetään priorityqueuessa, jotta saadaan prioriteetit
 */
public class Node {

    private Node left;
    private Node right;
    private Tile tile;
    private int priority;

    /**
     * Palauta vasen
     * @return vasen
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Palauta oikea
     * @return oikea
     */
    public Node getRight() {
        return right;
    }

    /**
     * Solmu, joka alustetaan minimiprioriteettilapsilla
     * @param tile ruutu jota solmu edustaa
     * @param priority sen prioriteetti
     */

    public Node(Tile tile, int priority) {
        this.tile = tile;
        this.priority = priority;
        createEmptyNodes();
    }

    /**
     * Luo tyhjät solmut uuden solmun lapsiksi
     */

    private void createEmptyNodes() {
        if (priority() >= Integer.MIN_VALUE+55) {
            Tile faketile = new NormalTile(999,999);
            this.left = new Node(faketile, Integer.MIN_VALUE);
            this.right = new Node(faketile, Integer.MIN_VALUE);
        }
    }

    /**
     * Palauta prioriteetti
     * @return
     */
    public int priority() {
        return priority;
    }

    /**
     * Aseta prioriteetti
     * @param prio aseta
     */
    public void setPriority(int prio) {
        this.priority = prio;
    }

    /**
     * Aseta vasen lapsi
     * @param left lapsi
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Palauta ruutu jota solmu edusta a
     * @return ruutu
     */
    public Tile getTile() {
        return this.tile;
    }

    /**
     * Aseta oikea lapsi
     * @param right lapsi
     */
    public void setRight(Node right) {
        this.right = right;
    }
}
