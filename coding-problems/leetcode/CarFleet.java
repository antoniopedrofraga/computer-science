import java.util.Arrays;

/**
 * N cars are going to the same destination along a one lane road.  The destination is target miles away.
 *
 * Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.
 *
 * A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.
 *
 * The distance between these two cars is ignored - they are assumed to have the same position.
 *
 * A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that a single car is also a car fleet.
 *
 * If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
 *
 *
 * How many car fleets will arrive at the destination?
 *
 *
 *
 * Example 1:
 *
 * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * Output: 3
 * Explanation:
 * The cars starting at 10 and 8 become a fleet, meeting each other at 12.
 * The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
 * The cars starting at 5 and 3 become a fleet, meeting each other at 6.
 * Note that no other cars meet these fleets before the destination, so the answer is 3.
 *
 * Note:
 *
 * 0 <= N <= 10 ^ 4
 * 0 < target <= 10 ^ 6
 * 0 < speed[i] <= 10 ^ 6
 * 0 <= position[i] < target
 * All initial positions are different.
 *
 *
 * Time complexity: O(n log n)
 * Memory complexity: O(n) but we could make it O(1), by sorting the positions array in-place and keep track of the changes in the speed array as well.
 */

public class CarFleet {
    static final int POS = 0, VEL = 1;
    public int carFleet(int target, int[] position, int[] speed) {
        if (position.length == 0) return 0;
        int [][] stats = new int[position.length][2];
        for (int i = 0; i < position.length; i++) {
            stats[i] = new int[]{position[i], speed[i]};
        }
        Arrays.sort(stats, (s1,s2) -> s2[0] - s1[0]);
        int fleets = 1, pos = stats[0][POS], vel = stats[0][VEL];
        for (int i = 1; i < stats.length; i++) {
            double d1 = target - pos,
                    d2 = target - stats[i][POS];
            double t1 = d1 / vel,
                    t2 = d2 / stats[i][VEL];
            if (t1 < t2) {
                fleets++;
                vel = stats[i][VEL];
                pos = stats[i][POS];
            }
        }
        return fleets;
    }

    public void test() {
        System.out.println(carFleet(12, new int[]{10,8,0,5,3}, new int[]{2,4,1,1,3}));
    }
}
