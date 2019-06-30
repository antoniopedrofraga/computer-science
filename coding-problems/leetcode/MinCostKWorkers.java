import java.util.*;

/**
 * There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
 *
 * Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:
 *
 * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Return the least amount of money needed to form a paid group satisfying the above conditions.
 *
 *
 *
 * Example 1:
 *
 * Input: quality = [10,20,5], wage = [70,50,30], K = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
 * Example 2:
 *
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
 *
 *
 * Note:
 *
 * 1 <= K <= N <= 10000, where N = quality.length = wage.length
 * 1 <= quality[i] <= 10000
 * 1 <= wage[i] <= 10000
 * Answers within 10^-5 of the correct answer will be considered correct.
 *
 * Memory complexity: O(n)
 * Time complexity: O(n log k)
 */
public class MinCostKWorkers {
class Worker implements Comparable {
    int quality;
    int wage;
    double ratio;

    public Worker(int quality, int wage) {
        this.quality = quality;
        this.wage = wage;
        this.ratio = wage / (double) quality;
    }
    @Override
    public int compareTo(Object obj) {
        Worker w2 = (Worker) obj;
        if (ratio < w2.ratio) {
            return -1;
        } else {
            return 1;
        }
    }
}
public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
    List<Worker> list = new ArrayList<>();
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    double sum = Double.MAX_VALUE, qualities = 0;
    for (int i = 0; i < quality.length; i++) {
        list.add(new Worker(quality[i], wage[i]));
    }
    Collections.sort(list);
    for (Worker worker : list) {
        pq.add(worker.quality);
        qualities +=  worker.quality;
        if (pq.size() > K) qualities -= pq.remove();
        if (pq.size() == K) sum = Math.min(sum, qualities * worker.ratio);
    }
    return sum;
}
    public void test() {
        System.out.println(
                mincostToHireWorkers(
                        new int[]{10,20,5},
                        new int[]{70,50,30},
                        2
                )
        );
        System.out.println(
                mincostToHireWorkers(
                        new int[]{3,1,10,10,1},
                        new int[]{4,8,2,2,7},
                        3
                )
        );
    }
}



