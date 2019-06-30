import java.util.*;

/**
 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
 *
 * 0 represents the obstacle can't be reached.
 * 1 represents the ground can be walked through.
 * The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
 *
 *
 * You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).
 *
 * You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.
 *
 * You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
 *
 * Example 1:
 *
 * Input:
 * [
 *  [1,2,3],
 *  [0,0,4],
 *  [7,6,5]
 * ]
 * Output: 6
 *
 *
 * Example 2:
 *
 * Input:
 * [
 *  [1,2,3],
 *  [0,0,0],
 *  [7,6,5]
 * ]
 * Output: -1
 *
 *
 * Example 3:
 *
 * Input:
 * [
 *  [2,3,4],
 *  [0,0,5],
 *  [8,7,6]
 * ]
 * Output: 6
 * Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
 *
 *
 * Hint: size of the given matrix will not exceed 50x50.
 */
public class CutOfTrees {
    private static int[][] moves = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    class Tree implements Comparable<Tree> {
        int y;
        int x;
        int height;
        public Tree(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
        @Override
        public int compareTo(Tree t) {
            return this.height - t.height;
        }
    }
    class Level {
        int steps;
        int y;
        int x;
        public Level(int x, int y, int steps) {
            this.steps = steps;
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object l) {
            Level l2 = (Level) l;
            return this.x == l2.x && this.y == l2.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }
    }
    public int cutOffTree(List<List<Integer>> forest) {
        int steps = 0, height = forest.size(), width = forest.get(0).size();
        List<Tree> trees = new ArrayList<>();
        Queue<Level> bfs = new LinkedList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (forest.get(y).get(x) > 0) {
                    trees.add(new Tree(x, y, forest.get(y).get(x)));
                }
            }
        }
        Collections.sort(trees);
        bfs.add(new Level(0, 0, 0));
        for (int i = 0; i < trees.size(); i++) {
            Set<Level> visited = new HashSet<>();
            int x = trees.get(i).x, y = trees.get(i).y;
            while (!bfs.isEmpty()) {
                Level top = bfs.remove();
                if (visited.contains(top)) {
                    continue;
                }
                visited.add(top);
                if (x == top.x && y == top.y) {
                    bfs.clear();
                    steps += top.steps;
                    top.steps = 0;
                    bfs.add(top);
                    break;
                }
                for (int[] move : moves) {
                    int newX = top.x + move[0], newY = top.y + move[1];
                    if (newX >= 0 && newX < width && newY >= 0 && newY < height && forest.get(newY).get(newX) > 0) {
                        Level level = new Level(newX, newY, top.steps + 1);
                        bfs.add(level);
                    }
                }
            }
            if (bfs.isEmpty()) {
                return -1;
            }
        }
        return steps;
    }
    public void test() {
        System.out.println(cutOffTree(Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(0,0,4),
                Arrays.asList(7,6,5)
        )));
        System.out.println(cutOffTree(Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(0,0,0),
                Arrays.asList(7,6,5)
        )));
        System.out.println(cutOffTree(Arrays.asList(
                Arrays.asList(2,3,4),
                Arrays.asList(0,0,5),
                Arrays.asList(8,7,6)
        )));
        System.out.println(cutOffTree(Arrays.asList(
                Arrays.asList(2395,206,0,5388),
                Arrays.asList(4985,2866,0,1789),
                Arrays.asList(0,0,7052,0),
                Arrays.asList(0,3029,3327,685),
                Arrays.asList(0,0,7846,0),
                Arrays.asList(0,0,1542,0),
                Arrays.asList(7577,0,2902,623),
                Arrays.asList(2610,9817,0,8198)
        )));
    }
}
