package com.github.elunmuks.BTS;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {

    public JButton buttonLoad = new JButton("load graph");
    public JButton buttonPlay = new JButton("run algorithm");
    public JButton buttonNext = new JButton("next step");
    public JButton buttonPrev = new JButton("prev step");

    public GraphViewer graphViewer = new GraphViewer();

    public View() throws HeadlessException {

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.BLACK);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(buttonLoad);
        buttons.add(buttonPlay);
        buttons.add(buttonNext);
        buttons.add(buttonPrev);

        JPanel main = new JPanel();
        main.setBackground(Color.BLACK);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(buttons);
        main.add(graphViewer);

        buttonLoad.addActionListener( graphViewer::actionLoad );
        buttonPlay.addActionListener( graphViewer::actionPlay );
        buttonNext.addActionListener( graphViewer::actionNext );
        buttonPrev.addActionListener( graphViewer::actionPrev );

        JFrame frame = new JFrame("Kruskal");
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setResizable(false);
        frame.setVisible(true);

    }

}
