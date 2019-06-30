import java.util.Objects;

/**
 * A conveyor belt has packages that must be shipped from one port to another within D days.
 *
 * The i-th package on the conveyor belt has a weight of weights[i].  Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within D days.
 *
 *
 *
 * Example 1:
 *
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * Output: 15
 * Explanation:
 * A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 *
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 * Example 2:
 *
 * Input: weights = [3,2,2,4,1,4], D = 3
 * Output: 6
 * Explanation:
 * A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 * Example 3:
 *
 * Input: weights = [1,2,3,1,1], D = 4
 * Output: 3
 * Explanation:
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 *
 *
 * Note:
 *
 * 1 <= D <= weights.length <= 50000
 * 1 <= weights[i] <= 500
 */
public class CapacityShipPackage {
    public int shipWithinDaysBinarySearch(int[] weights, int D) {
        int left = 0, right = 0;
        for (int w : weights) {
            left = Math.max(w, left);
            right += w;
        }
        while (left < right) {
            int mid = left + (right - left) / 2, need = 1, curr = 0;
            for (int w : weights) {
                if (curr + w > mid) {
                    need++;
                    curr = 0;
                }
                curr += w;
            }
            if (need > D) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    class Pair {
        int i;
        int D;
        public Pair(int i, int D) {
            this.i = i;
            this.D = D;
        }
        @Override
        public int hashCode() {
            return Objects.hash(i, D);
        }
    }

    public int shipWithinDays(int[] weights, int D) {
        int [][] dp = new int[weights.length][D];
        return divideAndConquer(weights, dp, 0, D - 1);
    }

    int divideAndConquer(int[] weights, int [][] dp, int i, int D) {
        if (dp[i][D] != 0) return dp[i][D];
        if (D == 0) {
            int sum = 0;
            for (int j = i; j < weights.length; j++) {
                sum += weights[j];
            }
            dp[i][D] = sum;
            return sum;
        }
        int sum = weights[i], best = Integer.MAX_VALUE;
        for (int j =  i + 1; j < weights.length - D + 1; j++) {
            if (sum >= best) break;
            int current = Math.max(sum, divideAndConquer(weights, dp, j, D - 1));
            best = Math.min(current, best);
            sum += weights[j];
        }
        dp[i][D] = best;
        return best;
    }


    public void test() {
        System.out.println(shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));
        System.out.println(shipWithinDays(new int[]{3,2,2,4,1,4}, 3));
        System.out.println(shipWithinDays(new int[]{1,2,3,1,1}, 4));
    }
}
