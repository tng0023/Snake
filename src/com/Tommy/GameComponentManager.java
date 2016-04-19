package com.Tommy;

/**
 * Created by Clara. Manages game components such as the Snake, Kibble... and their interactions.
 */
public class GameComponentManager {

    private Kibble kibble;
    private Snake snake;
    private Score score;
    private Maze maze;


    /** Called every clock tick. Tell components to interact/update,
     * manage interactions, update score etc.
     * If there were more components - e.g walls, mazes,
     * different types of kibble/prizes, different scoring systems...
     * they could be managed here too
     */
    public void update() {
        snake.moveSnake();
        if (snake.didEatKibble(kibble)) {
            //tell kibble to update
            kibble.moveKibble(snake);
            //TN - tell maze block to update
            maze.moveMaze(snake);
            Score.increaseScore();
        }else if(snake.hitMazeWall(maze)){
            SnakeGame.setGameStage(SnakeGame.GAME_OVER);
		}
    }

    public void newGame() {
        snake.reset();
    }


    public void addKibble(Kibble kibble) {
        this.kibble = kibble;
    }

    public void addMaze(Maze maze){
        this.maze = maze;
    }

    public void addSnake(Snake snake) {
        this.snake = snake;
    }

    public void addScore(Score score) {
        this.score = score;
    }

    public Score getScore() {
        return score;
    }

    public Kibble getKibble() {
        return kibble;
    }

    public Snake getSnake() {
        return snake;
    }

    public Maze getMaze() {
        return maze;
    }
}
