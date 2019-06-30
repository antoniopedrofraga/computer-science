import java.util.*;

/**
 * Given an array A of strings, find any smallest string that contains each string in A as a substring.
 *
 * We may assume that no string in A is substring of another string in A.
 *
 *
 * Example 1:
 *
 * Input: ["alex","loves","leetcode"]
 * Output: "alexlovesleetcode"
 * Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
 * Example 2:
 *
 * Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
 * Output: "gctaagttcatgcatc"
 *
 *
 * If we apply a bottom up solution we can get a time complexity: O(n * 2^n)
 * vs the time complexity with a top down solution : O(n!)
 */

public class ShortestSuperstring {
    class Pair {
        int weight;
        List<Integer> list;
        public Pair(int weight, List<Integer> list) {
            this.weight = weight;
            this.list = list;
        }
    }
    private int getWeight(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if (b.startsWith(a.substring(i))) {
                return a.length() - i;
            }
        }
        return 0;
    }

    public String shortestSuperstring(String[] A) {
        int mask = 0;
        int [][] weights= new int[A.length][A.length];
        StringBuilder builder = new StringBuilder();
        List<Integer> [][] dp = new ArrayList[1 << A.length][A.length];
        int [][] dpW = new int[1 << A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                weights[i][j] = getWeight(A[i], A[j]);
                weights[j][i] = getWeight(A[j], A[i]);
            }
        }
        List<Integer> bestList = new ArrayList<>();
        int minWeight = -1;
        for (int i = 0; i < A.length; i++) {
            Pair local = tsp(A, weights, dp, dpW, mask | (1 << i), i);
            int localWeight = local.weight;
            if (localWeight > minWeight) {
                minWeight = localWeight;
                bestList = local.list;
            }
        }
        int first = bestList.get(bestList.size() - 1);
        builder.append(A[first]);
        for (int j = bestList.size() - 2; j >= 0; j--) {
            int previous = bestList.get(j + 1);
            int next = bestList.get(j);
            int weight = weights[previous][next];
            String toAppend = A[next].substring(weight);
            builder.append(toAppend);
        }
        return builder.toString();
    }
    Pair tsp(String[] A, int [][] weights, List<Integer> [][] dp, int [][] dpW, int mask, int pos) {
        if (mask == (1 << A.length) - 1) {
            List<Integer> leaf = new ArrayList<>();
            leaf.add(pos);
            return new Pair(0, leaf);
        }
        if (dp[mask][pos] != null) {
            return new Pair(dpW[mask][pos], dp[mask][pos]);
        }
        List<Integer> bestList = new ArrayList<>();
        int minWeight = -1;
        for (int i = 0; i < A.length; i++) {
            if ((mask & (1 << i)) == 0) {
                Pair local = tsp(A, weights, dp, dpW,mask | (1 << i), i);
                int localWeight = weights[pos][i] + local.weight;
                if (localWeight > minWeight) {
                    minWeight = localWeight;
                    bestList = new ArrayList<>(local.list);
                }
            }
        }
        bestList.add(pos);
        dp[mask][pos] = bestList;
        dpW[mask][pos] = minWeight;
        return new Pair(minWeight, bestList);
    }
    public void test() {
        System.out.println(shortestSuperstring(new String[]{"alex","loves","leetcode"}));
        System.out.println(shortestSuperstring(new String[]{"catg","ctaagt","gcta","ttca","atgcatc"}));
    }
}