import java.util.*;

/**
 * Given a list of non-overlapping axis-aligned rectangles rects, write a function pick which randomly and uniformily picks an integer point in the space covered by the rectangles.
 *
 * Note:
 *
 * An integer point is a point that has integer coordinates.
 * A point on the perimeter of a rectangle is included in the space covered by the rectangles.
 * ith rectangle = rects[i] = [x1,y1,x2,y2], where [x1, y1] are the integer coordinates of the bottom-left corner, and [x2, y2] are the integer coordinates of the top-right corner.
 * length and width of each rectangle does not exceed 2000.
 * 1 <= rects.length <= 100
 * pick return a point as an array of integer coordinates [p_x, p_y]
 * pick is called at most 10000 times.
 * Example 1:
 *
 * Input:
 * ["Solution","pick","pick","pick"]
 * [[[[1,1,5,5]]],[],[],[]]
 * Output:
 * [null,[4,1],[4,1],[3,3]]
 * Example 2:
 *
 * Input:
 * ["Solution","pick","pick","pick","pick","pick"]
 * [[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]]
 * Output:
 * [null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]]
 * Explanation of Input Syntax:
 *
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array of rectangles rects. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 */

public class NonOverlapingRectangles {
    public NonOverlapingRectangles() {}

    TreeMap<Integer, Integer> tree = new TreeMap<>();
    Random randomR;
    int [][] rects;
    int sum;
    public NonOverlapingRectangles(int[][] rects) {
        sum = 0;
        for (int i = 0; i < rects.length; i++) {
            int [] rect = rects[i];
            int lj = rect[0], li = rect[1],
                    hj = rect[2], hi = rect[3];
            int height = hi - li + 1;
            int width = hj - lj + 1;
            int area = height * width;
            tree.put(sum, i);
            sum += area;
        }
        this.rects = rects;
        this.randomR = new Random();
    }
    private int[] next() {
        int nextSum = randomR.nextInt(sum);
        Map.Entry<Integer, Integer> entry = tree.floorEntry(nextSum);
        int r = entry.getValue(), lowerSum = entry.getKey();
        int [] rect = rects[r];
        int lj = rect[0], hj = rect[2], li = rect[1];
        int width = hj - lj + 1;
        int total = nextSum - lowerSum;
        int j = lj + total % width, i = li + total / width;
        return new int[]{j, i};
    }
    public int[] pick() {
        return next();
    }

    public void test() {
        NonOverlapingRectangles test = new NonOverlapingRectangles(new int[][]{
                {1,1,5,5}
        });
        System.out.println(Arrays.toString(test.pick()));
        System.out.println(Arrays.toString(test.pick()));
        System.out.println(Arrays.toString(test.pick()));
        System.out.println(Arrays.toString(test.pick()));
        System.out.println(Arrays.toString(test.pick()));
        System.out.println(Arrays.toString(test.pick()));
        System.out.println(Arrays.toString(test.pick()));
        System.out.println(Arrays.toString(test.pick()));
        System.out.println(Arrays.toString(test.pick()));
        System.out.println();

        NonOverlapingRectangles test2 = new NonOverlapingRectangles(new int[][]{
                {-2,-2,-1,-1}, {1,0,3,0}
        });
        System.out.println(Arrays.toString(test2.pick()));
        System.out.println(Arrays.toString(test2.pick()));
        System.out.println(Arrays.toString(test2.pick()));
        System.out.println(Arrays.toString(test2.pick()));
        System.out.println(Arrays.toString(test2.pick()));

        NonOverlapingRectangles test3 = new NonOverlapingRectangles(new int[][]{
                {82918473, -57180867, 82918476, -57180863}, {83793579, 18088559, 83793580, 18088560}, {66574245, 26243152, 66574246, 26243153}, {72983930, 11921716, 72983934, 11921720}
        });
        for (int i  = 0; i < 100; i++) {
            System.out.println(Arrays.toString(test3.pick()));
        }
    }
}
