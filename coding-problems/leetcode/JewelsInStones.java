/**
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
 *
 * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".
 *
 * Example 1:
 *
 * Input: J = "aA", S = "aAAbbbb"
 * Output: 3
 * Example 2:
 *
 * Input: J = "z", S = "ZZ"
 * Output: 0
 * Note:
 *
 * S and J will consist of letters and have length at most 50.
 * The characters in J are distinct.
 *
 * Approach: Create char array indicating which chars are jewels for O(1) queries.
 * Time complexity: O(n)
 * Memory complexity: O(1)
 */
public class JewelsInStones {
    public int numJewelsInStones(String J, String S) {
        boolean [] isJewel = new boolean[128];
        int sum = 0;
        for (int i = 0; i < J.length(); i++) {
            isJewel[J.charAt(i)] = true;
        }
        for (int i = 0; i < S.length(); i++) {
            if (isJewel[S.charAt(i)]) sum++;
        }
        return sum;
    }
}
