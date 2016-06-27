package com.kerberuskaahaaja.pathfinder.ui;

import com.kerberuskaahaaja.pathfinder.map.Map;
import com.kerberuskaahaaja.pathfinder.tiles.NormalTile;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Käyttöliittymän vaatimien hiiritoimintojen havainnointi
 */
public class MouseFunctionality extends MouseAdapter {



    private Map map;
    private MapRender maprender;
    private boolean unlockedStartPoint;
    private boolean unlockedEndPoint;
    private Tile tile;


    /**
     * Alustaa lähdön ja maalin lukkiutumisen sekä luo ruudun, johon voidaan vertailla kun katsotaan mikä ruutu oli viimeksi käsittelyssä
     * @param maprender Käyttöliittymä, jonka kautta saadaan ruutu, jota ollaan klikattu
     * @param map Kartta, jotta voidaan muokata siinä olevia ruutuja
     */

    public MouseFunctionality(MapRender maprender, Map map) {
        this.map = map;
        this.maprender = maprender;
        unlockedEndPoint = false;
        unlockedStartPoint = false;
        tile = new NormalTile(-1,-5);
    }

    /**
     *
     * @return Palauttaa kartan
     */

    public Map getMap() {
        return map;
    }

    /**
     * Asettaa kartan
     * @param map kartta
     */

    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Määrittelee mitä tapahtuu kun klikataan hiirtä eli muutetaan seinän status
     * @param e tiedot klikkauksesta
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Tile toggle = maprender.getPosition(e);
        toggle.toggleWall();
        maprender.repaint();
    }

    /**
     * Toiminnallisuus hiiren painamiselle, mutta ei vapauttamiselle eli siirretään maalipisteitä
     * @param e tietoja toiminnasta
     */

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

    /**
     * Toiminnallisuus hiiren siirtelylle, kun painiketta pidetään pohjassa, eli siirretään maalia tai muutetaan ruutu jonka päällä ollaan seinäksi tai pois seinästä
     * @param e tietoa
     */

    @Override
    public void mouseDragged(MouseEvent e) {
        Tile toggle = maprender.getPosition(e);
        if (toggle != null) {
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

    /**
     * Vapautetaan hiiiri, ja vapautetaan lukitus maalista ja lähdöstä jos ne oli lukittuina
     * @param e tieto
     */

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
