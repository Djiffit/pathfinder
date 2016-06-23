package com.kerberuskaahaaja.pathfinder.ui;

import com.kerberuskaahaaja.pathfinder.algorithms.Astar;
import com.kerberuskaahaaja.pathfinder.algorithms.BreadthFirstSearch;
import com.kerberuskaahaaja.pathfinder.algorithms.Dijkstra;
import com.kerberuskaahaaja.pathfinder.map.Map;
import com.kerberuskaahaaja.pathfinder.tiles.Tile;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseListener;

public class UserInterface implements Runnable {
    private JFrame frame;
    private Map map;
    private MouseFunctionality mouse;

    @Override
    public void run() {
        map = new Map(50, 150);

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setTitle("Topkeke");
        JPanel setup = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel result = new JPanel();
        JLabel time = new JLabel("0 ms");
        result.add(time);

        MapRender mapRender = new MapRender(map);
        mouse = new MouseFunctionality(mapRender, map);
        mapRender.addMouseListener(mouse);
        mapRender.addMouseMotionListener(mouse);

        createAlgorithmButtons(setup, result, time, mapRender);

        createSquareSizeButtons(setup, mapRender);

        createMapSizeButtons(setup, mapRender);

        createMapEditButtons(setup, mapRender);

        frame.add(setup, BorderLayout.PAGE_START);
        frame.add(time, BorderLayout.LINE_START);
        frame.add(mapRender, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createMapEditButtons(JPanel setup, MapRender mapRender) {
        JButton random = new JButton("Random walls");
        random.addActionListener(ae -> {
            map.randomize();
            mapRender.repaint();
        });
        setup.add(random);

        JButton empty = new JButton("Destroy Walls");
        empty.addActionListener(ae -> {
            map.initializeMap();
            mapRender.repaint();
        });
        setup.add(empty);
    }

    private void createSquareSizeButtons(JPanel setup, MapRender mapRender) {
        JButton enlargen = new JButton("+");
        enlargen.addActionListener(ae -> {
            mapRender.enlargen();
            mapRender.repaint();
            mapRender.repaint();
        });

        setup.add(enlargen);
        JButton shrink = new JButton("-");
        shrink.addActionListener(ae -> {
            mapRender.shrink();
            mapRender.repaint();
        });
        setup.add(shrink);
    }

    private void createMapSizeButtons(JPanel setup, MapRender mapRender) {
        JButton increasewidth = new JButton("width+");
        increasewidth.addActionListener(ae -> {
            addStartAndGoal(true, 50);
            mapRender.setMap(map);
            mapRender.repaint();
            mapRender.repaint();
        });
        setup.add(increasewidth);

        JButton increaseheight = new JButton("height+");
        increaseheight.addActionListener(ae -> {
            addStartAndGoal(false, 50);
            mapRender.setMap(map);
            mapRender.repaint();
            mapRender.repaint();
        });
        setup.add(increaseheight);

        JButton decreasewidth = new JButton("width-");
        decreasewidth.addActionListener(ae -> {
            addStartAndGoal(true, -50);
            mapRender.setMap(map);
            mapRender.repaint();
            mapRender.repaint();
        });
        setup.add(decreasewidth);

        JButton decreaseheight = new JButton("height-");
        decreaseheight.addActionListener(ae -> {
            addStartAndGoal(false, -50);
            mapRender.setMap(map);
            mapRender.repaint();
            mapRender.repaint();
        });
        setup.add(decreaseheight);
    }

    private void createAlgorithmButtons(JPanel setup, JPanel result, JLabel time, MapRender mapRender) {
        JButton djk = new JButton("Run Dijkstra");
        djk.addActionListener(ae -> {
            Dijkstra dijkstra = new Dijkstra(map);
            dijkstra.resetMap();
            dijkstra.solveMap(map.getStart().getX(), map.getStart().getY(), map.getGoal().getX(), map.getGoal().getY());
            time.setText(dijkstra.toString());
            result.repaint();
            mapRender.repaint();
        });
        setup.add(djk);

        JButton astarB = new JButton("Run A*");
        astarB.addActionListener(ae -> {
            Astar astar = new Astar(map);
            astar.resetMap();
            astar.solveMap(map.getStart().getX(), map.getStart().getY(), map.getGoal().getX(), map.getGoal().getY());
            time.setText(astar.toString());
            result.repaint();
            mapRender.repaint();
        });

        setup.add(astarB);

        JButton breadthB = new JButton("Run BreadthSearch");
        breadthB.addActionListener(ae -> {
            BreadthFirstSearch bred = new BreadthFirstSearch(map);
            bred.resetMap();
            bred.solveMap(map.getStart().getX(), map.getStart().getY(), map.getGoal().getX(), map.getGoal().getY());
            time.setText(bred.toString());
            result.repaint();
            mapRender.repaint();
        });
        setup.add(breadthB);
    }

    private void addStartAndGoal(boolean width, int change) {
        Tile start = map.getStart();
        Tile goal = map.getGoal();
        if (width) {
            map = new Map(map.getWidth()+change, map.getHeight());
        } else {
            map = new Map(map.getWidth(), map.getHeight() + change);
        }
        map.setStart(start);
        map.setGoal(goal);
        mouse.setMap(map);
    }

}
