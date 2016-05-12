package com.Tommy;

import java.util.*;
import java.awt.*;
import java.util.LinkedList;

import javax.swing.JPanel;

/** This class responsible for displaying the graphics, so the snake, grid, kibble, instruction text and high score
 * 
 * @author Clara
 *
 */
public class DrawSnakeGamePanel extends JPanel {

	private static int gameStage = SnakeGame.BEFORE_GAME;  //use this to figure out what to paint

	private Snake snake;
	private Kibble kibble;
	private Score score;
	private Maze maze;

	DrawSnakeGamePanel(GameComponentManager components) {
		this.snake = components.getSnake();
		this.kibble = components.getKibble();
		this.score = components.getScore();
		this.maze = components.getMaze();
	}

	//displays score while in game
	private void displayScore(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		String highscore = score.getStringHighScore();
		String textScore = score.getStringScore();
		g.drawString("Score: " + textScore, SnakeGame.xPixelMaxDimension, SnakeGame.yPixelMaxDimension);
		g.drawString("High Score: " + highscore, SnakeGame.xPixelMaxDimension - 50, SnakeGame.yPixelMaxDimension + 50);
	}

	public Dimension getPreferredSize() {
		return new Dimension(SnakeGame.xPixelMaxDimension, SnakeGame.yPixelMaxDimension);
	}

	//Writes out instructions for the game
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

        /* Where are we at in the game? 4 phases.. 
         * 1. Before game starts
         * 2. During game
         * 3. Game lost aka game over
         * 4. or, game won
         */

		gameStage = SnakeGame.getGameStage();
		g.setColor(Color.BLUE);

		switch (gameStage) {
			case SnakeGame.BEFORE_GAME: {
				displayInstructions(g);
				break;
			}
			case SnakeGame.DURING_GAME: {
				displayGame(g);
				displayScore(g); //TN - added score to show in GUI during game
				break;
			}
			case SnakeGame.GAME_OVER: {
				displayGameOver(g);
				break;
			}
			case SnakeGame.GAME_WON: {
				displayGameWon(g);
				break;
			}
		}
	}

	//display when game is won
	private void displayGameWon(Graphics g) {
		// TODO Replace this with something really special!
		g.clearRect(100, 100, 350, 350);
		g.drawString("YOU WON SNAKE!!!", 150, 150);


	}

	//display when game is over with option to quit or play again
	//TN - moved around and enlarged wording
	private void displayGameOver(Graphics g) {
		//updated font and spacing of "Game Over" graphics
		g.setFont(new Font("TimesRoman", Font.BOLD, 18));
		g.clearRect(50, 50, 350, 350);
		g.drawString("GAME OVER!", 100, 100);

		String textScore = score.getStringScore();
		String textHighScore = score.getStringHighScore();
		String newHighScore = score.newHighScore();

		g.drawString("SCORE = " + textScore, 100, 200);

		g.drawString("HIGH SCORE = " + textHighScore, 100, 250);
		g.drawString(newHighScore, 150, 400);

		g.drawString("Press ANY key to play again", 100, 300);
		g.drawString("Press Q to quit the game", 100, 350);

	}

	//TN - displays the grid, snake, kibbles, and maze blocks of the game
	private void displayGame(Graphics g) {
		displayGameGrid(g);
		displaySnake(g);
		displayKibble(g);
		displayMaze(g);
	}

	//draws the game board with squares
	private void displayGameGrid(Graphics g) {

		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY = SnakeGame.yPixelMaxDimension;
		int squareSize = SnakeGame.squareSize;

		//TN - fills background color to lightGray
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, maxX, maxY);



		//Draw grid - horizontal lines
		for (int y = 0; y <= maxY; y += squareSize) {
			g.drawLine(0, y, maxX, y);
		}
		//Draw grid - vertical lines
		for (int x = 0; x <= maxX; x += squareSize) {
			g.drawLine(x, 0, x, maxY);
		}
	}

	//TN - draws maze walls in GUI
	private void displayMaze(Graphics g) {
		g.setColor(Color.BLACK);
		int x = maze.getMazeX() * SnakeGame.squareSize;
		int y = maze.getMazeY() * SnakeGame.squareSize;
		g.fill3DRect(x + 1, y + 1, SnakeGame.squareSize - 2, SnakeGame.squareSize - 2, true);

	}

	private void displayKibble(Graphics g) {

		//TN - Draw the kibble in green
		g.setColor(Color.RED);

		int x = kibble.getKibbleX() * SnakeGame.squareSize;
		int y = kibble.getKibbleY() * SnakeGame.squareSize;

		//TN - Creates an oval of the kibble
		g.fillOval(x + 1, y + 1, SnakeGame.squareSize - 2, SnakeGame.squareSize - 2);
	}

	//Draws the snake to play the game
	private void displaySnake(Graphics g) {

		LinkedList<Point> coordinates = snake.segmentsToDraw();

		//Draw head in yellow
		g.setColor(Color.blue);
		Point head = coordinates.pop();
		g.fill3DRect((int) head.getX(), (int) head.getY(), SnakeGame.squareSize, SnakeGame.squareSize, true);

		//Draw rest of snake in black
		g.setColor(Color.ORANGE);
		for (Point p : coordinates) {
			g.fill3DRect((int) p.getX(), (int) p.getY(), SnakeGame.squareSize, SnakeGame.squareSize, true);
		}
	}

	//Displays the instructions before the game starts
	private void displayInstructions(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.BOLD, 18));
		g.drawString("Press ANY key to begin!", 100, 150);
		g.drawString("Press Q to quit the game", 100, 250);
		g.drawString("**Eat red dots", 100, 350);
		g.drawString("**Do not run into black squares", 100, 450);

	}
}

