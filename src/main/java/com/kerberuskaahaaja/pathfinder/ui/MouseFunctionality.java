package com.kerberuskaahaaja.pathfinder.ui;

import com.kerberuskaahaaja.pathfinder.map.Map;
import com.kerberuskaahaaja.pathfinder.tiles.NormalTile;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseFunctionality extends MouseAdapter {



    private Map map;
    private MapRender maprender;
    private boolean unlockedStartPoint;
    private boolean unlockedEndPoint;
    private Tile tile;

    public MouseFunctionality(MapRender maprender, Map map) {
        this.map = map;
        this.maprender = maprender;
        unlockedEndPoint = false;
        unlockedStartPoint = false;
        tile = new NormalTile(-1,-5);
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Tile toggle = maprender.getPosition(e);
        toggle.toggleWall();
        maprender.repaint();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        Tile toggle = maprender.getPosition(e);
        if (toggle != null) {
            if (toggle.getX() == (map.getStart().getX()) && toggle.getY() == map.getStart().getY()) {
                unlockedStartPoint = true;
            } else if (toggle.getX() == (map.getGoal().getX()) && toggle.getY() == map.getGoal().getY())  {
                unlockedEndPoint = true;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Tile toggle = maprender.getPosition(e);
        if (toggle != null) {
            System.out.println(unlockedEndPoint + " " + unlockedStartPoint);
            if (!unlockedStartPoint && !unlockedEndPoint) {
                if (tile != toggle) {
                    tile = toggle;
                    tile.toggleWall();
                }
            } else if (unlockedStartPoint) {
                map.setStart(toggle);
            } else {
                map.setGoal(toggle);
            }
        }
        maprender.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Tile toggle = maprender.getPosition(e);
        if (toggle != null) {
            if (unlockedStartPoint) {
                map.setStart(toggle);
                unlockedStartPoint = false;
            } else if (unlockedEndPoint) {
                map.setGoal(toggle);
                unlockedEndPoint = false;
            }
        }
        maprender.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
