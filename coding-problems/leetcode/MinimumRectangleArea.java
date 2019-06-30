import java.util.*;

public class MinimumRectangleArea {
    class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object o) {
            Point p = (Point) o;
            return this.x == p.x && this.y == p.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public int minAreaRect(int[][] points) {
        Set<Point> memo = new HashSet<>();
        Arrays.stream(points).forEach(p -> memo.add(new Point(p[0], p[1])));
        int area = Integer.MAX_VALUE;
        for (Point p1 : memo) {
            for (Point p2 : memo) {
                if (p2.equals(p1)) continue;
                int x1 = p1.x, y1 = p1.y;
                int x2 = p2.x, y2 = p2.y;
                if (x1 == x2 || y1 == y2) continue;
                Point p3 = new Point(x2, y1);
                Point p4 = new Point(x1, y2);
                if (memo.contains(p3) && memo.contains(p4)) {
                    int localArea = Math.abs(x1 - x2) * Math.abs(y1 - y2);
                    area = Math.min(area, localArea);
                }
            }
        }
        return area;
    }

    public void test() {
        System.out.println(minAreaRect(new int[][]{{1,1},{1,3},{3,1},{3,3},{2,2}}));
    }
}
