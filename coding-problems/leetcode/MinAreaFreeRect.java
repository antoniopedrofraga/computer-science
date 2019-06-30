import java.util.*;

/**
 * Given a set of points in the xy-plane, determine the minimum area of any rectangle formed from these points, with sides not necessarily parallel to the x and y axes.
 *
 * If there isn't any rectangle, return 0.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [[1,2],[2,1],[1,0],[0,1]]
 * Output: 2.00000
 * Explanation: The minimum area rectangle occurs at [1,2],[2,1],[1,0],[0,1], with an area of 2.
 * Example 2:
 *
 *
 *
 * Input: [[0,1],[2,1],[1,1],[1,0],[2,0]]
 * Output: 1.00000
 * Explanation: The minimum area rectangle occurs at [1,0],[1,1],[2,1],[2,0], with an area of 1.
 * Example 3:
 *
 *
 *
 * Input: [[0,3],[1,2],[3,1],[1,3],[2,1]]
 * Output: 0
 * Explanation: There is no possible rectangle to form from these points.
 * Example 4:
 *
 *
 *
 * Input: [[3,1],[1,1],[0,1],[2,1],[3,3],[3,2],[0,2],[2,3]]
 * Output: 2.00000
 * Explanation: The minimum area rectangle occurs at [2,1],[2,3],[3,3],[3,1], with an area of 2.
 *
 *
 * Note:
 *
 * 1 <= points.length <= 50
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * All points are distinct.
 * Answers within 10^-5 of the actual value will be accepted as correct.
 *
 *
 * Approach: Use Pythagoras theorem in order to know whether the 3rd point is valid or not. Calculate the 4th point and check whether we have it or not.
 * Time complexity: O(n^3)
 * Memory complexity: O(n)
 */
public class MinAreaFreeRect {
class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    @Override
    public boolean equals(Object obj) {
        Point p2 = (Point) obj;
        return this.x == p2.x && this.y == p2.y;
    }
}

public boolean canFormTriangle(Point p1, Point p2, Point p3) {
    double d1, d2, d3;
    double x1 = p1.x, y1 = p1.y,
            x2 = p2.x, y2 = p2.y,
            x3 = p3.x, y3 = p3.y;
    d1 = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    d2 = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
    d3 = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
    double d4 = Math.pow(d1, 2) + Math.pow(d2, 2), d5 = Math.pow(d3, 2);
    return Math.abs(d4 - d5) < 1e-5;
}

public double getArea(Point p1, Point p2, Point p3) {
    double d1, d2;
    double x1 = p1.x, y1 = p1.y,
            x2 = p2.x, y2 = p2.y,
            x3 = p3.x, y3 = p3.y;
    d1 = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    d2 = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
    return d1 * d2;
}

public Point get4thPoint(Point p1, Point p2, Point p3) {
    double x1 = p1.x, y1 = p1.y,
            x2 = p2.x, y2 = p2.y,
            x3 = p3.x, y3 = p3.y,
            x4, y4;
    double xInterval = x2 - x1,
            yInterval = y2 - y1;
    x4 = x3 - xInterval;
    y4 = y3 - yInterval;
    return new Point((int) x4, (int)y4);
}

public double minAreaFreeRect(int[][] points) {
    double min = Double.MAX_VALUE;
    Set<Point> set = new HashSet<>();
    List<Point> list = new ArrayList<>();
    for (int [] point : points) {
        Point p = new Point(point[0], point[1]);
        if (!set.contains(p)) {
            set.add(p);
            list.add(p);
        }
    }
    Point p1, p2, p3, p4;
    for (int i = 0; i < list.size(); i++) {
        p1 = list.get(i);
        for (int j = i + 1; j < list.size(); j++) {
            p2 = list.get(j);
            for (int k = j + 1; k < list.size(); k++) {
                p3 = list.get(k);
                if (canFormTriangle(p1, p2, p3)) {
                    p4 = get4thPoint(p1, p2, p3);
                } else if (canFormTriangle(p2, p1, p3)) {
                    p4 = get4thPoint(p2, p1, p3);
                } else {
                    continue;
                }
                if (set.contains(p4)) {
                    double localArea = canFormTriangle(p1, p2, p3) ? getArea(p1, p2, p3) : getArea(p2, p1, p3);
                    if (localArea != 0) {
                        min = Math.min(localArea, min);
                    }
                }
            }
        }
    }
    return min == Double.MAX_VALUE ? 0 : min;
}

    public void test() {
        System.out.println(minAreaFreeRect(new int [][]{
                {1,2},{2,1},{1,0},{0,1}
        }));
        System.out.println(minAreaFreeRect(new int [][]{
                {0,1},{2,1},{1,1},{1,0},{2,0}
        }));
        System.out.println(minAreaFreeRect(new int [][]{
                {0,3},{1,2},{3,1},{1,3},{2,1}
        }));
        System.out.println(minAreaFreeRect(new int [][]{
                {3,1},{1,1},{0,1},{2,1},{3,3},{3,2},{0,2},{2,3}
        }));
    }
}
