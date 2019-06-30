/**
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 *
 * Approach: We can use two pointers to the end of both strings and update backspaces as we compare characters between strings instead, maintaining constant memory.
 *
 * Time complexity: With string a and string b -> O(|a| + |b|)
 * Memory complexity: O(1)
 */
public class KeyPresses {
    public static final Character BACKSPACE = '#';

    public boolean backspaceCompare(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1,
                aCarry = 0, bCarry = 0;
        while ((i >= 0 || j >= 0) || aCarry > 0 || bCarry > 0) {
            if (i >= 0 && a.charAt(i) == BACKSPACE) {
                aCarry++;
                i--;
            } else if (j >= 0 && b.charAt(j) == BACKSPACE) {
                bCarry++;
                j--;
            } else if (aCarry > 0) {
                aCarry--;
                i--;
            } else if (bCarry > 0) {
                bCarry--;
                j--;
            } else if (i < 0 || j < 0 || a.charAt(i) != b.charAt(j)) {
                return false;
            } else {
                i--;
                j--;
            }
        }
        return true;
    }

    public void test() {
        System.out.println(backspaceCompare("ab#c", "ad#c"));
        System.out.println(backspaceCompare("ab##", "c#d#"));
        System.out.println(backspaceCompare("a##c", "#a#c"));
        System.out.println(backspaceCompare("a#c", "b"));
    }
}
