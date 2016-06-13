package com.kerberuskaahaaja.pathfinder.ui;

import com.kerberuskaahaaja.pathfinder.algorithms.Astar;
import com.kerberuskaahaaja.pathfinder.algorithms.BreadthFirstSearch;
import com.kerberuskaahaaja.pathfinder.algorithms.Dijkstra;
import com.kerberuskaahaaja.pathfinder.map.Map;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseListener;

public class UserInterface implements Runnable {
    private JFrame frame;

    @Override
    public void run() {
        Map map = new Map(1000, 500);

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setTitle("Topkeke");
        JPanel setup = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel result = new JPanel();
        JLabel time = new JLabel("0 ms");
        result.add(time);

        MapRender mapRender = new MapRender(map);
        MouseFunctionality mouse = new MouseFunctionality(mapRender, map);
        mapRender.addMouseListener(mouse);
        mapRender.addMouseMotionListener(mouse);

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
        mapRender.setMinimumSize(new Dimension(1920, 1000));
        time.setPreferredSize(new Dimension(1920, 40));
        setup.setPreferredSize(new Dimension(1920, 40));
        setup.setMaximumSize(new Dimension(300000, 40));
        time.setMaximumSize(new Dimension(300000, 40));
        mapRender.setAutoscrolls(true);
        frame.add(setup, BorderLayout.PAGE_START);
        frame.add(time, BorderLayout.LINE_START);
        frame.add(mapRender, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

}
