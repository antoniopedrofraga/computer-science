import java.util.PriorityQueue;

/**
 * On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.
 *
 * Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
 *
 * Return the smallest possible value of D.
 *
 * Example:
 *
 * Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
 * Output: 0.500000
 * Note:
 *
 * stations.length will be an integer in range [10, 2000].
 * stations[i] will be an integer in range [0, 10^8].
 * K will be an integer in range [1, 10^6].
 * Answers within 10^-6 of the true value will be accepted as correct.
 *
 * First Approach: sort stations, create, max priority queue of distances and divide the max distance in 2 k times.
 *
 * Second Approach: binary search through the distances. We can approximate the result by assuming that “right” is the largest possible distance, and “left” is 0.
 * We can count how many elements we would have in case our guess is the maximum distance.
 * If our count is above k, then we know that we have to increase our guess (left = mid), we set right = mid otherwise.
 *
 * Time complexity: n log d (where d is the maximum distance)
 * Memory complexity: O(1)
 */
public class MinMaxDistGasStation {
    // Binary search solution (Approximated)
    public double minmaxGasDist(int[] stations, int k) {
        double left = 0.0, right = stations[stations.length - 1] - stations[0], mid;
        int count;
        while (left + 1e-6 < right) {
            count = 0;
            mid = (right + left) / 2;
            for (int i = 1; i < stations.length; i++) {
                count += Math.floor((stations[i] - stations[i - 1]) / mid);
            }
            if (count > k) left = mid;
            else right = mid;
        }
        return left;
    }
    // The correct solution
    public double accMinmaxGasDist(int[] stations, int k) {
        PriorityQueue<double[]> pq = new PriorityQueue<>((d1, d2) -> (d1[0] / d1[1] > d2[0] / d2[1]) ? -1 : 1);
        for (int i = 1; i < stations.length; i++) {
            pq.add(new double[]{stations[i] - stations[i - 1], 1.0});
        }
        while (k-- > 0) {
            double [] max = pq.poll();
            max[1]++;
            pq.add(max);
        }
        double [] bestMax = pq.peek();
        return bestMax[0] / bestMax[1];
    }
    public void test() {
        System.out.println(minmaxGasDist(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9));
        System.out.println(minmaxGasDist(new int[]{10,19,25,27,56,63,70,87,96,97}, 3));
    }
}
