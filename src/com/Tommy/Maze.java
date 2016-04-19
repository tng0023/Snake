package com.Tommy;

import java.util.Random;

/* The maze blocks created in this class will end the game if snake hits it. */


public class Maze {

    /** Identifies a random square to display a maze block
     * Any square is ok, so long as it doesn't have any snake segments in it.
     * There is only one maze block that knows where it is on the screen.
     */

    private int MazeX; //This is the square number (not pixel)
    private int MazeY;  //This is the square number (not pixel)

    public Maze(Snake s){
        //maze block needs to know where the snake is, so it does not create a maze block in the snake
        //Pick a random location for maze block, check if it is in the snake
        //If in snake, try again

        moveMaze(s);
    }

    protected final void moveMaze(Snake s){

        Random rng = new Random();
        boolean maze = true;
        while (maze == true) {
            //Generate random maze block location
            MazeX = rng.nextInt(SnakeGame.xSquares);
            MazeY = rng.nextInt(SnakeGame.ySquares);
            maze = s.isSnakeSegment(MazeX, MazeY);
        }

    }

    public int getMazeX() {
        return MazeX;
    }

    public int getMazeY() {
        return MazeY;
    }


}
