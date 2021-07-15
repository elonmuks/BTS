package com.github.elunmuks.BTS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class GraphViewer extends JPanel {

    public final double radius = 300;
    public final int minrad = 30;

    public final Kruskal kruskal = new Kruskal();

    public final ArrayList<Double> weights = new ArrayList();
    public final ArrayList<Point> lines = new ArrayList();
    public final ArrayList<Point> vertices = new ArrayList();

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        for (Point e: lines)
            g.drawLine( vertices.get(e.x).x, vertices.get(e.x).y,
                        vertices.get(e.y).x, vertices.get(e.y).y);

        g.setColor(Color.GREEN);
        for (int i = 0; i < kruskal.currentStep; i++) {
            AlgorithmSteps step = kruskal.algorithmSteps.get(i);
            if (step.isTrueEdge) g.setColor(Color.GREEN);
            else g.setColor(Color.RED);
            g.drawLine( vertices.get(step.graphEdge.firstVertex).x, vertices.get(step.graphEdge.firstVertex).y,
                        vertices.get(step.graphEdge.secondVertex).x, vertices.get(step.graphEdge.secondVertex).y);
        }

        g.setColor(Color.BLUE);
        for (Point v: vertices)
            g.fillOval(v.x - minrad / 2, v.y - minrad / 2, minrad, minrad);

        g.setColor(Color.MAGENTA);
        for (int i = 0; i < vertices.size(); i++)
            g.drawString(i + "", vertices.get(i).x - minrad, vertices.get(i).y - minrad);

        g.setColor(Color.YELLOW);
        for (int i = 0; i < lines.size(); i++) {
            int v1 = (vertices.get(lines.get(i).x).x + vertices.get(lines.get(i).y).x) / 2;
            int v2 = (vertices.get(lines.get(i).x).y + vertices.get(lines.get(i).y).y) / 2;
            g.drawString(weights.get(i) + "", v1, v2);
        }
    }

    public void actionLoad(ActionEvent e) {
        try {
            kruskal.graph.graphFromFile();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Graph is empty");
        }
    }

    public void actionPlay(ActionEvent e) {
        int size = kruskal.graph.graphVertices.size();
        for (int i = 0; i < size; i++) {
            double angle = 2 * Math.PI * ((double)i) / ((double)size);
            vertices.add(new Point((int)(radius * Math.cos( angle ) + getWidth() / 2),
                                   (int)(radius * Math.sin( angle ) + getHeight() / 2)));
        }

        for (GraphEdges edges: kruskal.graph.graphEdges) {
            lines.add(new Point(edges.firstVertex, edges.secondVertex));
            weights.add(edges.weight);
        }

        kruskal.run();
        repaint();
    }

    public void actionNext(ActionEvent e) {
        kruskal.stepForward();
        repaint();
    }

    public void actionPrev(ActionEvent e) {
        kruskal.stepBack();
        repaint();
    }

}
