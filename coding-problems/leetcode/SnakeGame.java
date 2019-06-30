import java.util.*;

/**
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
 *
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 *
 * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
 *
 * Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
 *
 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 *
 * Example:
 *
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 *
 * Snake snake = new Snake(width, height, food);
 *
 * Initially the snake appears at position (0,0) and the food at (1,2).
 *
 * |S| | |
 * | | |F|
 *
 * snake.move("R"); -> Returns 0
 *
 * | |S| |
 * | | |F|
 *
 * snake.move("D"); -> Returns 0
 *
 * | | | |
 * | |S|F|
 *
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
 *
 * | |F| |
 * | |S|S|
 *
 * snake.move("U"); -> Returns 1
 *
 * | |F|S|
 * | | |S|
 *
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 *
 * | |S|S|
 * | | |S|
 *
 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */
public class SnakeGame {
    static int [] noFood = new int[]{-1, -1};
    int width, height, score, foodPointer;
    int [][] food;
    Set<Integer> occupied = new HashSet<>();
    Deque<Integer> queue = new LinkedList<>();

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.score = 0;
        this.food = food;
        this.foodPointer = 0;
        queue.add(0);
        occupied.add(0);
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int [] foodCoords = foodPointer < food.length ? food[foodPointer] : noFood;
        int lastPos = queue.getLast(), newPos = 0;
        int posX = lastPos % width, posY = lastPos / width;
        if (direction.equals("U")) {
            posY--;
        } else if (direction.equals("D")) {
            posY++;
        } else if (direction.equals("L")) {
            posX--;
        } else if (direction.equals("R")) {
            posX++;
        }
        if (posY < 0 || posY >= height || posX < 0 || posX >= width) {
            return -1;
        }
        newPos = posY * width + posX;
        if (foodCoords[0] != posY || foodCoords[1] != posX) {
            int index = queue.remove();
            occupied.remove(index);
        } else {
            score++;
            foodPointer++;
        }
        if (occupied.contains(newPos)) {
            return -1;
        }
        queue.add(newPos);
        occupied.add(newPos);
        return score;
    }
}
