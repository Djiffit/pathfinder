package com.kerberuskaahaaja.pathfinder.ui;

import com.kerberuskaahaaja.pathfinder.map.Map;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MapRender extends JPanel {


    private Map map;
    private int squareSize = 4;

    public void setMap(Map map) {
        this.map = map;
        repaint();
        super.repaint();
    }

    public MapRender(Map map) {
        this.map = map;
        setPreferredSize(new Dimension(squareSize * map.getWidth(), squareSize * map.getHeight()));
    }

    public int getSquareSize() {
        return squareSize;
    }

    public void enlargen() {
        this.squareSize++;
        setSize(new Dimension(squareSize*map.getWidth(), squareSize*map.getHeight()));
    }

    public void shrink() {
        if (squareSize > 1) {
            squareSize--;
        }
        resize(squareSize*map.getWidth(), squareSize*map.getHeight());
    }

    protected Tile getPosition(MouseEvent e) {
        int x = ((e.getX()) / squareSize);
        int y = (e.getY()) / squareSize;
        System.out.println(e.getX()+" "+x);
        System.out.println(e.getY()+" "+y);
        return map.getCoordinates(x, y);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(new Color(30, 30, 30));
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
