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
            if (toggle.equals(map.getStart())) {
                unlockedStartPoint = true;
            } else if (toggle.equals(map.getGoal())) {
                unlockedEndPoint = true;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Tile toggle = maprender.getPosition(e);
        if (toggle != null) {
            if (!unlockedStartPoint && !unlockedEndPoint) {
                if (!tile.equals(toggle)) {
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
                unlockedEndPoint = false;
            } else if (unlockedEndPoint) {
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
