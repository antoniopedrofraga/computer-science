import java.util.Arrays;

/**
 * Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.
 *
 * Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.
 *
 * So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.
 *
 * Note:
 *
 * Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
 * Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
 * As long as a house is in the heaters' warm radius range, it can be warmed.
 * All the heaters follow your radius standard and the warm radius will the same.
 *
 *
 * Example 1:
 *
 * Input: [1,2,3],[2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 *
 *
 * Example 2:
 *
 * Input: [1,2,3,4],[1,4]
 * Output: 1
 * Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 */

public class Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int left = 0, right = Math.max(houses[houses.length - 1], heaters[heaters.length - 1]);
        while (left < right) {
            int mid = (right - left) / 2 + left;
            int i = 0, j = 0;
            while (i < houses.length && j < heaters.length) {
                int min = heaters[j] - mid, max = heaters[j] + mid;
                while (i < houses.length
                        && houses[i] >= min && max >= houses[i]) {
                    i++;
                }
                if (i != houses.length) j++;
            }
            if (i == houses.length) {
                right = mid;
            } else if (j == heaters.length) {
                left = mid + 1;
            }
        }
        return left;
    }

    public void test() {
        System.out.println(findRadius(new int[]{1,5}, new int[]{2}));
        System.out.println(findRadius(new int[]{1,2,3}, new int[]{2}));
        System.out.println(findRadius(new int[]{1,2,3,4}, new int[]{1,4}));
        System.out.println(findRadius(new int[]{1}, new int[]{1,2,3,4}));
    }
}
