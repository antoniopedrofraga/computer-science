/**
 * Your music player contains N different songs and she wants to listen to L (not necessarily different) songs during your trip.  You create a playlist so that:
 *
 * Every song is played at least once
 * A song can only be played again only if K other songs have been played
 * Return the number of possible playlists.  As the answer can be very large, return it modulo 10^9 + 7.
 *
 * Approach:
 *
 * Let dp[i][j] be the number of playlists of length i that have exactly j unique songs. We can write a recurrence for this dp.
 *
 * If we played a song for the first time, we have dp[i - 1][j - 1] * (N - j) ways to choose it.
 *
 * If we are repeating a song, then we can calculate the new number of ways by using dp[i - 1][j] * Math.max(j - k, 0).
 */
public class NumberPlaylists {
    public int numMusicPlaylists(int n, int l, int k) {
        int mod = (int) 1e9 + 7;
        long [][] dp = new long[l + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= l; i++) {
            for (int j = 1; j <= n; j++) {
                int songsAvaliable = n - j + 1,
                        availableAgain = Math.max(0, j - k);
                dp[i][j] += dp[i - 1][j - 1] * songsAvaliable % mod;
                dp[i][j] += dp[i - 1][j] * availableAgain % mod;
                dp[i][j] %= mod;
            }
        }
        return (int) dp[l][n];
    }
}
