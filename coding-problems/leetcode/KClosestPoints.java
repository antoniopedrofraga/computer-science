import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 * Approach: Create a priority queue in order to find the k closest k points. It has to be a max heap, in case our max is greater than the point we’re analysing, we remove the top element and insert the new one.
 *
 * Time complexity: O(n log k)
 * Memory complexity: O(n)
 */
public class KClosestPoints {
    private void swap(int [][] points, int i, int j) {
        int [] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
    public int[][] kClosest(int[][] points, int K) {
        int l = 0, r = points.length - 1;
        while (l < r) {
            int pivot = choosePivot(points, l, r);
            if (pivot == K) break;
            if (pivot < K) l = pivot + 1;
            else r = pivot - 1;
        }
        return Arrays.copyOf(points, K);
    }
    private int choosePivot(int [][] points, int l, int r) {
        int pivot = (int) (Math.random() * (r - l + 1)) + l;
        swap(points, r, pivot);
        int i = l;
        for (int j = l; j < r; j++) {
            if (compare(points[j], points[r]) < 0) {
                swap(points, i, j);
                i++;
            }
        }
        swap(points, i, r);
        return i;
    }
    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }

    public int[][] kClosestSort(int[][] points, int K) {
        Arrays.sort(points, (p1, p2) -> {
            int dist1 = p1[0] * p1[0] + p1[1] * p1[1],
                    dist2 = p2[0] * p2[0] + p2[1] * p2[1];
            return dist1 - dist2;
        });
        return Arrays.copyOf(points, K);
    }
    public int[][] kClosestPq(int[][] points, int K) {
        int [][] result = new int[K][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> {
            int dist1 = p1[0] * p1[0] + p1[1] * p1[1],
                    dist2 = p2[0] * p2[0] + p2[1] * p2[1];
           return dist2 - dist1;
        });
        for (int  [] p1 : points) {
            if (pq.size() < K) {
                pq.add(p1);
            } else {
                int [] p2 = pq.peek();
                int dist1 = p1[0] * p1[0] + p1[1] * p1[1],
                        dist2 = p2[0] * p2[0] + p2[1] * p2[1];
                if (dist1 < dist2) {
                    pq.remove();
                    pq.add(p1);
                }
            }
        }
        int i = 0;
        for (int [] point : pq) {
            result[i++] = point;
        }
        return result;
    }

    public void test() {
        int [][] result = kClosest(new int[][]{
                {1,3},{-2,2}
        }, 1);
        for (int [] point : result) {
            System.out.print(Arrays.toString(point) + " ");
        }
        System.out.println();
        result = kClosest(new int[][]{
                {3,3},{5,-1},{-2,4}
        }, 2);
        for (int [] point : result) {
            System.out.print(Arrays.toString(point) + " ");
        }
        System.out.println();
        result = kClosest(new int[][]{
                {0,1},{1,0}
        }, 2);
        for (int [] point : result) {
            System.out.print(Arrays.toString(point) + " ");
        }
        System.out.println();
        result = kClosest(new int[][]{
                {2,2},{2,2},{2,2},{2,2},{2,2},{2,2},{1,1}
        }, 2);
        for (int [] point : result) {
            System.out.print(Arrays.toString(point) + " ");
        }
        System.out.println();
        result = kClosest(new int[][]{
                {1,3},{-2,2},{2,-2}
        }, 2);
        for (int [] point : result) {
            System.out.print(Arrays.toString(point) + " ");
        }
        System.out.println();
    }
}
