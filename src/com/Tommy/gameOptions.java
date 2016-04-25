package com.Tommy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tommy on 4/17/2016.
 */
public class gameOptions extends JFrame {

    private JPanel root;
    private JLabel Options;
    private JComboBox speedChoice;
    private JButton submitButton;
    private JCheckBox warpWallsCheckBox;
    private JComboBox screenSize;

    protected gameOptions() {
        super("OPTIONS");
        setContentPane(root);
        setPreferredSize(new Dimension(700, 500));

        configureSpeed();
        screenSize();

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SnakeGame.warpWalls = warpWallsCheckBox.isSelected();  //Tell SnakeGame about selected options
                //TODO add code to send other options too

                SnakeGame.initializeGame();
                SnakeGame.createAndShowGUI();
                SnakeGame.setGameStage(SnakeGame.BEFORE_GAME);
                SnakeGame.getGameStage();
                SnakeGame.setGameStage(SnakeGame.DURING_GAME);


                SnakeGame.newGame();


                setVisible(false);



                return;
            }
        });

        warpWallsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameOptions.this.warpWallsCheckBox.isSelected()){

                }


                }

        });

        speedChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                }
        });
}


    private void configureSpeed() {
        for (int x = 1; x <= 3; x++) {
            if (x == 1) {
                speedChoice.addItem(x + " Slow");
            } else if (x == 2) {
                speedChoice.addItem(x + " fast");
            } else if (x == 3) {
                speedChoice.addItem(x + " very fast");
            }
        }
    }

    private void screenSize() {
        for (int x = 1; x <= 3; x++) {
            if (x == 1) {
                screenSize.addItem(x + " Small");
            } else if (x == 2) {
                screenSize.addItem(x + " Medium");
            } else if (x == 3) {
                screenSize.addItem(x + " Large");
            }
        }
    }
}
