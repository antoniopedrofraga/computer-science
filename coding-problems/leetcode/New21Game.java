import java.util.Arrays;

/**
 * Alice plays the following game, loosely based on the card game "21".
 *
 * Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.
 *
 * Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?
 *
 * Example 1:
 *
 * Input: N = 10, K = 1, W = 10
 * Output: 1.00000
 * Explanation:  Alice gets a single card, then stops.
 * Example 2:
 *
 * Input: N = 6, K = 1, W = 10
 * Output: 0.60000
 * Explanation:  Alice gets a single card, then stops.
 * In 6 out of W = 10 possibilities, she is at or below N = 6 points.
 * Example 3:
 *
 * Input: N = 21, K = 17, W = 10
 * Output: 0.73278
 * Note:
 *
 * 0 <= K <= N <= 10000
 * 1 <= W <= 10000
 * Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 * The judging time limit has been reduced for this question.
 *
 * N -> Target points
 * K -> Stops after k points
 * W -> can receive from [1, w]
 */
public class New21Game {
    public double oldNew21Game(int N, int K, int W) {
        int max = K + W;
        if (max < N) return 1.0;
        int [] dp = new int[max];
        int safe = 0, total = 0;
        dp[0] = 1;
        for (int i = 0; i < K; i++) {
            for (int j = 1; j <= W; j++) {
                dp[i + j] += dp[i];
            }
        }
        for (int i = K; i < max; i++) {
            if (i <= N) {
                safe += dp[i];
            }
            total += dp[i];
        }
        return (double) safe / total;
    }
    public double new21Game(int N, int K, int W) {
        int max = K + W;
        if (max < N) return 1.0;
        int [] dp = new int[max];
        int safe = 0, total = 0;
        dp[0] = 1;
        for (int i = 1; i < max; i++) {
            for (int j = 1; j <= W; j++) {
                if (i - j < 0) break;
                if (i - j >= K) { j = i - K; continue; }
                dp[i] += dp[i - j];
            }
            if (i >= K) {
                if (i <= N) {
                    safe += dp[i];
                }
                total += dp[i];
            }
        }
        return (double) safe / total;
    }
    public void test() {
        //System.out.println(new21Game(10, 1, 10));
        //System.out.println(new21Game(6, 1, 10));
        //oldNew21Game(21, 17, 10);
        //new21Game(21, 17, 10);
        System.out.println(new21Game(21, 17, 10));
    }
}
