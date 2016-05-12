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
    private JButton submitButton;
    private JCheckBox warpWallsCheckBox;
    private JRadioButton smallRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton largeRadioButton;
    private JRadioButton slowRadioButton;
    private JRadioButton mediumRadioButton1;
    private JRadioButton fastRadioButton;

    private ButtonGroup screenSize = new ButtonGroup();
    private ButtonGroup speed = new ButtonGroup();

    protected gameOptions() {
        super("OPTIONS");
        setContentPane(root);
        setPreferredSize(new Dimension(700, 500));
        setUp();

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //set up button to play the game and initiates options and game screen
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeGame.warpWalls = warpWallsCheckBox.isSelected();
                gameSettings();
                SnakeGame.initializeGame();
                SnakeGame.createAndShowGUI();
                SnakeGame.newGame();
                setVisible(false);
                return;
            }
        });
        //checkbox for turning warp walls on and off
        warpWallsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean on = true;
                if (gameOptions.this.warpWallsCheckBox.isSelected()) {
                    on = true;
                    System.out.println("Walls on");
                }
            }

        });
    }

    //buttons for increasing speed and size of screen
    private void setUp() {
        speed.add(slowRadioButton);
        speed.add(mediumRadioButton1);
        speed.add(fastRadioButton);
        screenSize.add(smallRadioButton);
        screenSize.add(mediumRadioButton);
        screenSize.add(largeRadioButton);
    }

    //creates action from selected speed and screen options buttons
    public void gameSettings(){
        int gameSpeed = Integer.parseInt(speed.getSelection().getActionCommand());
        SnakeGame.setClockInterval(gameSpeed);

        int screen = Integer.parseInt(screenSize.getSelection().getActionCommand());

        SnakeGame.setxPixelMaxDimension(screen);
        SnakeGame.setyPixelMaxDimension(screen);

    }
}
