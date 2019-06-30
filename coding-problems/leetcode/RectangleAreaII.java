import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * We are given a list of (axis-aligned) rectangles.  Each rectangle[i] = [x1, y1, x2, y2] , where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the coordinates of the top-right corner of the ith rectangle.
 *
 * Find the total area covered by all rectangles in the plane.  Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Approach: Discretize the whole space, sort x and y wise, sum the areas.
 *
 * Time complexity: O(n^3) worst case (we could easily turn this into O(n^2))
 * Memory complexity: O(n)
 */
public class RectangleAreaII {
    class Point implements Comparable<Point> {
        int x;
        int y;
        boolean start;
        boolean bot;

        public Point(int x, int y, boolean start, boolean bot) {
            this.x = x;
            this.y = y;
            this.start = start;
            this.bot = bot;
        }

        @Override
        public int compareTo(Point p2) {
            if (this.x == p2.x) {
                if (this.start) return 1;
                else return -1;
            }
            return this.x - p2.x;
        }

        @Override
        public boolean equals(Object obj) {
            Point p2 = (Point) obj;
            return this.y == p2.y && this.bot == p2.bot;
        }
    }

    public int rectangleArea(int[][] rectangles) {
        int sum = 0, modulo = 1000000007;
        List<Point> points = new ArrayList<>(),
                window = new ArrayList<>();
        for (int [] coordinates : rectangles) {
            int x1 = coordinates[0], y1 = coordinates[1],
                    x2 = coordinates[2], y2 = coordinates[3];
            points.add(new Point(x1, y1, true, true));
            points.add(new Point(x1, y2, true, false));
            points.add(new Point(x2, y1, false, true));
            points.add(new Point(x2, y2, false, false));
        }
        Collections.sort(points);
        window.add(points.get(0));
        int i = 1, lastx = points.get(0).x;
        for (; i < points.size(); i++) {
            Point point = points.get(i);
            if (point.x != lastx)
                break;
            window.add(point);
        }
        while (i < points.size()) {
            int newx = points.get(i).x;
            long width = (newx - lastx) % modulo;
            Integer y0 = null, depth = 0;
            window.sort((p1, p2) -> {
                if (p1.y == p2.y) {
                    if (p1.bot) return -1;
                    else return 1;
                }
                return p1.y - p2.y;
            });
            for (Point point : window) {
                if (y0 == null)
                    y0 = point.y;
                if (point.bot)
                    depth++;
                else
                    depth--;
                if (depth == 0) {
                    long height = (point.y - y0) % modulo;
                    long area = (width * height) % modulo;
                    sum += area;
                    sum %= modulo;
                    y0 = null;
                }
            }
            for (; i < points.size(); i++) {
                Point point = points.get(i);
                if (point.x != newx)
                    break;
                if (!point.start) {
                    window.remove(point);
                } else {
                    window.add(point);
                }
            }
            lastx = newx;
        }
        return sum;
    }

    public void test() {
        System.out.println(rectangleArea(new int[][]{
                {0,0,2,2},
                {1,0,2,3},
                {1,0,3,1}
        }));
        System.out.println(rectangleArea(new int[][]{
                {0,0,1000000000,1000000000}
        }));
        System.out.println(rectangleArea(new int[][]{
                {224386961,128668997,546647847,318900555},
                {852286866,238086790,992627088,949888275},
                {160239672,137108804,398130330,944807066},
                {431047948,462092719,870611028,856851714},
                {736895365,511285772,906155231,721626624},
                {289309389,607009433,558359552,883664714},
                {780746435,397872372,931219192,863727103},
                {573523994,124874359,889018012,471879750},
                {619886375,149607927,727026507,446976526},
                {51739879,716225241,115331335,785850603},
                {171077223,267051983,548436248,349498903},
                {314437215,169054168,950814572,481179241},
                {64126215,646689712,595562376,829164135},
                {926011655,481539702,982179297,832455610},
                {40370235,231510218,770233582,851797196},
                {292546319,45032676,413358795,783606009},
                {424366277,369838051,453541063,777456024},
                {211837048,142665527,217366958,952362711},
                {228416869,402115549,672143142,644930626},
                {755018294,194555696,846854520,939022548},
                {192890972,586071668,992336688,759060552},
                {127869582,392855032,338983665,954245205},
                {665603955,208757599,767586006,276627875},
                {260384651,10960359,736299693,761411808},
                {46440611,559601039,911666265,904518674},
                {54013763,90331595,332153447,106222561},
                {73093292,378586103,423488105,826750366},
                {327100855,516514806,676134763,653520887},
                {930781786,407609872,960671631,510621750},
                {35479655,449171431,931212840,617916927}
        }));
    }
}
