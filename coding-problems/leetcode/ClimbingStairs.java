public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n < 2) return 1;
        int [] ways = new int[n + 1];
        ways[0] = 1;
        for (int i = 0; i < ways.length - 1; i++) {
            ways[i + 1] += ways[i];
            if (i + 1 < n) {
                ways[i + 2] += ways[i];
            }
        }
        return ways[n];
    }
}
