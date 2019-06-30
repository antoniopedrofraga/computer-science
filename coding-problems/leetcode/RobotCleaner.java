import java.util.*;

/**
 * interface Robot {
 *   // returns true if next cell is open and robot moves into the cell.
 *   // returns false if next cell is obstacle and robot stays on the current cell.
 *   boolean move();
 *
 *   // Robot will stay on the same cell after calling turnLeft/turnRight.
 *   // Each turn will be 90 degrees.
 *   void turnLeft();
 *   void turnRight();
 *
 *   // Clean the current cell.
 *   void clean();
 * }
 * Example:
 *
 * Input:
 * room = [
 *   [1,1,1,1,1,0,1,1],
 *   [1,1,1,1,1,0,1,1],
 *   [1,0,1,1,1,1,1,1],
 *   [0,0,0,1,0,0,0,0],
 *   [1,1,1,1,1,1,1,1]
 * ],
 * row = 1,
 * col = 3
 *
 * Explanation:
 * All grids in the room are marked by either 0 or 1.
 * 0 means the cell is blocked, while 1 means the cell is accessible.
 * The robot initially starts at the position of row=1, col=3.
 * From the top left corner, its position is one row below and three columns right.
 *
 *
 * Approach: Run backtracking and avoid visited cells.
 */
public class RobotCleaner {
    interface Robot {
        // Returns true if the cell in front is open and robot moves into the cells
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        public boolean move();

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        public void turnLeft();
        public void turnRight();

        // Clean the current cell.
        public void clean();
    }
    private final List<Pos> moves = Arrays.asList(new Pos(0, 1), new Pos(1, 0), new Pos(0, -1), new Pos(-1, 0));
    class Pos {
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        @Override
        public boolean equals(Object obj) {
            Pos pos = (Pos) obj;
            return this.x == pos.x && this.y == pos.y;
        }
    }
    public void cleanRoom(Robot robot) {
        Set<Pos> seen = new HashSet<>();
        backtrack(robot, seen, new Pos(0, 0));
    }
    public void turnAround(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
    public void backtrack(Robot robot, Set<Pos> seen, Pos current) {
        robot.clean();
        seen.add(current);
        int x = current.x, y = current.y;
        for (int i = 0; i < moves.size(); i++) {
            int newX = x + moves.get(i).x, newY = y + moves.get(i).y;
            Pos newPos = new Pos(newX, newY);
            if (!seen.contains(newPos) && robot.move()) {
                for (int j = i; j < 4; j++) {
                    robot.turnRight();
                }
                backtrack(robot, seen, newPos);
                for (int j = 0; j < i; j++) {
                    robot.turnRight();
                }
                turnAround(robot);
            }
            robot.turnRight();
        }
    }
}
