
/*
    "12345"
    "12354"
    "12435"
*/

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        int [] factorial = new int[n + 1];
        StringBuilder pool = new StringBuilder("");
        StringBuilder stringBuilder = new StringBuilder("");
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        for (int i = 1; i <= n; i++) {
            pool.append(Character.forDigit(i, 10));
        }
        k--;
        while (pool.length() > 0) {
            int denominator = factorial[pool.length() - 1];
            int index =  k / denominator;
            stringBuilder.append(pool.charAt(index));
            pool.deleteCharAt(index);
            k -= index * denominator;
        }
        return stringBuilder.toString();
    }
}
