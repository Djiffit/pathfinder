package com.kerberuskaahaaja.pathfinder.ui;

import com.kerberuskaahaaja.pathfinder.map.Map;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Luokka joka huolehtii kartan piirtämisestä
 */

public class MapRender extends JPanel {


    private Map map;
    private int squareSize = 2;

    /**
     * Aseta kartta
     * @param map kartta
     */

    public void setMap(Map map) {
        this.map = map;
        repaint();
        super.repaint();
    }

    /**
     * Konstruktori, luo oikeankokoisen alueen kartan kokojen pohjalta
     * @param map
     */

    public MapRender(Map map) {
        this.map = map;
        setPreferredSize(new Dimension(squareSize * map.getWidth(), squareSize * map.getHeight()));
    }

    /**
     * palauttaa ruutujen koon
     * @return ruutujen koko
     */

    public int getSquareSize() {
        return squareSize;
    }

    /**
     * Kasvattaa ruutujen kokoa yhdellä
     */

    public void enlargen() {
        this.squareSize++;
        setSize(new Dimension(squareSize*map.getWidth(), squareSize*map.getHeight()));
    }

    /**
     * Kutistaa ruutujen kokoa yhdellä
     */

    public void shrink() {
        if (squareSize > 1) {
            squareSize--;
        }
    }

    /**
     * Palauttaa ruudun joka on tietyn hiirenpainalluksen koordinaateissa
     * @param e hiirenpainallus
     * @return ruutu joka on paikassa
     */

    protected Tile getPosition(MouseEvent e) {
        int x = ((e.getX()) / squareSize);
        int y = (e.getY()) / squareSize;
        return map.getCoordinates(x, y);
    }

    /**
     * Värikoodaa ja piirtää alueen ruudut
     * @param graphics grafiikkakomponentti joka väritetään
     */

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(new Color(33, 33, 33));
        graphics.fillRect(0, 0, map.getWidth()*squareSize, map.getHeight()*squareSize);
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                Tile tile = map.getCoordinates(i, j);
                graphics.setColor(Color.BLACK);
                graphics.drawRect(tile.getX()*squareSize, tile.getY() * squareSize , squareSize, squareSize);
                if (tile.getLowestCost() < Integer.MAX_VALUE) {
                    graphics.setColor(Color.white);
                }
                if (tile.isPartOfPath()) {
                    graphics.setColor(Color.BLUE);
                }
                if (tile.isWall()) {
                    graphics.setColor(Color.RED);
                }
                if (tile.isGoal()){
                    graphics.setColor(Color.GREEN);
                }
                if (tile.isStart()) {
                    graphics.setColor(Color.green);
                }
                if (graphics.getColor() != Color.BLACK) {
                    graphics.fillRect(tile.getX() * squareSize + 1, tile.getY() * squareSize + 1, squareSize - 1, squareSize - 1);
                }
            }
        }
    }
}
