package com.kerberuskaahaaja.pathfinder.tiles;

public abstract class Tile {

    private int cost;
    private int x;
    private int y;
    private Tile cameFrom;
    private int lowestCost;
    private boolean partOfPath;
    private String toString;
    private boolean start;
    private boolean wall;
    private boolean goal;

    /**
     * Ruutu
     * @param x x
     * @param y y
     */

    public Tile(int x, int y) {
        this.wall = false;
        this.cost = 1;
        this.x = x;
        this.y = y;
        this.lowestCost = Integer.MAX_VALUE;
        this.cameFrom = null;
        this.partOfPath = false;
        this.start = false;
        this.goal = false;
    }


    /**
     * Muuttaa seinästatuksen
     */
    public void toggleWall() {
        this.wall = !this.wall;
    }

    /**
     * Onko kyseessä maali
     * @return onko niin
     */
    public boolean isGoal() {
        return goal;
    }

    /**
     * Asettaa ruudun maaliksi tai pois
     * @param goal onko maali vai ei
     */
    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    /**
     * Onko tämä lähtöpiste
     * @return onko lähtö
     */
    public boolean isStart() {
        return start;
    }

    /**
     * Asettaa alkuruudun
     * @param start onko
     */
    public void setStart(boolean start) {
        this.start = start;
    }

    /**
     * Kertoo onko osa reittiä jolla ratkaistaan polku
     * @return onko
     */
    public boolean isPartOfPath() {
        return partOfPath;
    }

    /**
     * asettaa osaksi reittiä tai pois
     * @param partOfPath onko osa reittiä
     */
    public void setPartOfPath(boolean partOfPath) {
        this.partOfPath = partOfPath;
    }

    /**
     * Palauttaa onko kyseessä seinä
     * @return onko tämä seinä
     */
    public boolean isWall() {return this.wall;}

    /**
     * Mistä ruudustä tähän on tultu
     * @return ruutu
     */
    public Tile getCameFrom() {
        return cameFrom;
    }

    /**
     * Aseta mistä tultiin
     * @param cameFrom ruutu
     */
    public void setCameFrom(Tile cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     * Alin hinta jolla ruutuun on päästy
     * @return hinta
     */
    public int getLowestCost() {
        return lowestCost;
    }

    /**
     * Aseta alin hinta
     * @param lowestCost hinta
     */
    public void setLowestCost(int lowestCost) {
        this.lowestCost = lowestCost;
    }

    /**
     * palauta ruudun x
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * palauta ruudun y
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * palauta ruudun arvo
     * @return arvo
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Resetoi ruudun data
     */
    public void reset() {
        this.lowestCost = Integer.MAX_VALUE;
        this.cameFrom = null;
        this.partOfPath = false;
    }
}
