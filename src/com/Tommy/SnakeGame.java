package com.Tommy;

import java.awt.*;
import java.util.*;

import java.util.Timer;

import javax.swing.*;


public class SnakeGame {

	public final static int xPixelMaxDimension = 501;  //Pixels in window. 501 to have 50-pixel squares plus 1 to draw a border on last square
	public final static int yPixelMaxDimension = 501;

	public static int xSquares ;
	public static int ySquares ;

	public final static int squareSize = 25;
	public static int bottomPanelHeight = 120; //TN - Sets a border on the bottom of screen
	public static int topPanelHeight = 120; //TN - Sets a border on the right side of screen for the score to appear

	protected static Snake snake ;

	private static GameComponentManager componentManager;

	protected static Score score;

	static final int BEFORE_GAME = 1;
	static final int DURING_GAME = 2;
	static final int GAME_OVER = 3;
	static final int GAME_WON = 4;   //The numerical values of these variables are not important. The important thing is to use the constants
	//instead of the values so you are clear what you are setting. Easy to forget what number is Game over vs. game won
	//Using constant names instead makes it easier to keep it straight. Refer to these variables 
	//using statements such as SnakeGame.GAME_OVER 

	private static int gameStage = BEFORE_GAME;  //use this to figure out what should be happening. 
	//Other classes like Snake and DrawSnakeGamePanel will query this, and change its value

	protected static long clockInterval = 200; //controls game speed
	//Every time the clock ticks, the snake moves
	//This is the time between clock ticks, in milliseconds
	//1000 milliseconds = 1 second.

	static JFrame snakeFrame;
	static DrawSnakeGamePanel snakePanel;
	//Framework for this class adapted from the Java Swing Tutorial, FrameDemo and Custom Painting Demo. You should find them useful too.
	//http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/FrameDemoProject/src/components/FrameDemo.java
	//http://docs.oracle.com/javase/tutorial/uiswing/painting/step2.html


	public static void main(String[] args) {
		//create and set up options window to run snake game
		gameOptions options = new gameOptions();
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				//initializeGame();
//				//createAndShowGUI();
//
//			}
		}



	public static void createAndShowGUI() {
		snakeFrame = new JFrame();
		snakeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		//added bottompanel height to fix bug of snake going past bottom wall
		snakeFrame.setSize(xPixelMaxDimension + topPanelHeight, yPixelMaxDimension + bottomPanelHeight);
		snakeFrame.setUndecorated(false); //hide title bar
		snakeFrame.setTitle("Snake"); //added to show name of game at title bar of gui
		snakeFrame.setVisible(true);
		snakeFrame.setResizable(true);
		snakeFrame.setLocationRelativeTo(null);


		snakePanel = new DrawSnakeGamePanel(componentManager);

		snakePanel.setFocusable(true);
		snakePanel.requestFocusInWindow(); //required to give this component the focus so it can generate KeyEvents

		snakeFrame.add(snakePanel);

		//Add listeners to listen for key presses
		snakePanel.addKeyListener(new GameControls());
		snakePanel.addKeyListener(new SnakeControls(snake));

		setGameStage(BEFORE_GAME);

		snakeFrame.setVisible(true);
	}

	public static void initializeGame() {

		//set up score, snake, first kibble, and maze block
		xSquares = xPixelMaxDimension / squareSize;
		ySquares = yPixelMaxDimension / squareSize;

		componentManager = new GameComponentManager();
		snake = new Snake(xSquares, ySquares, squareSize);
		Kibble kibble = new Kibble(snake);
		Maze maze = new Maze(snake);

		componentManager.addSnake(snake);
		componentManager.addKibble(kibble);
		componentManager.addMaze(maze);

		score = new Score();

		componentManager.addScore(score);

		gameStage = BEFORE_GAME;
	}


	protected static void newGame() {
		Timer timer = new Timer();
		GameClock clockTick = new GameClock(componentManager, snakePanel);
		componentManager.newGame();
		timer.scheduleAtFixedRate(clockTick, 0, clockInterval);
	}


	public static int getGameStage() {
		return gameStage;
	}

	public static void setGameStage(int gameStage) {
		SnakeGame.gameStage = gameStage;
	}

	}

