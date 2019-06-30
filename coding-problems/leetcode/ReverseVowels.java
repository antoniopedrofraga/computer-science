import java.util.Arrays;
import java.util.List;

/**
 * A dummy description of ReverseVowels
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
public class ReverseVowels {
    private static final List<Character> VOWELS = Arrays.asList('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u');
    public String reverseVowels(String s) {
        char [] array = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (!VOWELS.contains(s.charAt(i))) {
                i++;
                continue;
            }
            if (!VOWELS.contains(s.charAt(j))) {
                j--;
                continue;
            }
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
        return new String(array);
    }

    public void test() {
        System.out.println(reverseVowels("hello"));
        System.out.println(reverseVowels("leetcode"));
    }
}
