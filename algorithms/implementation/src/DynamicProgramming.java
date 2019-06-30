import static java.lang.Integer.min;

public class DynamicProgramming<T> {
    public int editDistance(String a, String b) {
        if (b == null || b.length()  == 0) {
            return a != null ? a.length() : 0;
        } else if (a == null || a.length() == 0) {
            return b.length();
        }

        Integer [][] memo = new Integer[a.length()][b.length()];
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(0)) {
                memo[i][0] = 1;
            }
        }
        for (int i = 0; i < b.length(); i++) {
            if (b.charAt(i) != a.charAt(0)) {
                memo[0][i] = 1;
            }
        }

        for (int i = 1; i < a.length(); ++i) {
            for (int j = i; j < b.length(); ++j) {
                int cost = a.charAt(i) == b.charAt(j) ? 0 : 1;
                memo[i][j] = min(memo[i - 1][j - 1], min(memo[i - 1][j], memo[i][j - 1])) + cost;
            }
        }

        return memo[a.length() - 1][b.length() - 1];
    }

    public int longestCommonSubsequence(T [] x, T [] y) {
        Integer [][] length = new Integer[x.length][y.length];
        for (int i = 0; i < x.length; ++i) {
            length[i][0] = 0;
        }
        for (int j = 0; j < y.length; ++j) {
            length[0][j] = 0;
        }
        for (int i = 1; i < x.length; ++i) {
            for (int j = 0; j < y.length; ++j) {
                if (x[i].equals(y[j])) {
                    length[i][j] = length[i - 1][j - 1] + 1;
                } else {
                    length[i][j] = Math.max(length[i - 1][j], length[i][j - 1]);
                }
            }
        }
        return length[x.length - 1][y.length - 1];
    }
}
