import java.util.Arrays;
import java.util.List;

/**
 * Given an encoded string, return it's decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 * Examples:
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 *
 * Approach parse the string as we go, enter a recursion level whenever we find an integer, in order to solve the same problem again.
 *
 * Time complexity: O(n)
 * Memory complexity: O(n k)
 */
public class DecodeString {
    class Wrapper {
        int index;
        String result;
        public Wrapper(String result, int index) {
            this.index = index;
            this.result = result;
        }
    }

    public String decodeString(String s) {
        return newdfs(s, 0).result;
    }

    public Wrapper dfs(String s, int index) {
        StringBuilder builder = new StringBuilder();
        String toRepeat = "";
        int repetition = 1, i = index;
        boolean isRepeating = false;
        for (; i < s.length() && Character.isDigit(s.charAt(i)); i++) {
            builder.append(s.charAt(i));
        }
        if (builder.length() > 0) {
            repetition = Integer.parseInt(builder.toString());
            // consume parenthesis
            i++;
            isRepeating = true;
        }

        builder.setLength(0);
        while (i < s.length() && Character.isLetter(s.charAt(i))) {
            builder.append(s.charAt(i));
            i++;
        }
        if (i < s.length() ) {
            if (s.charAt(i) != ']') {
                Wrapper wrapper = dfs(s, i);
                builder.append(wrapper.result);
                i = wrapper.index;
            }
            if (isRepeating)
                i++;
        }

        toRepeat = builder.toString();
        builder.setLength(0);
        for (int j = 0; j < repetition; j++) {
            builder.append(toRepeat);
        }
        if (i < s.length() && s.charAt(i) != ']') {
            Wrapper wrapper = dfs(s, i);
            builder.append(wrapper.result);
            i = wrapper.index;
        }
        return new Wrapper(builder.toString(), i);
    }


    public Wrapper newdfs(String s, int index) {
        int i = index;
        StringBuilder flatten = new StringBuilder();
        while (i < s.length()) {
            StringBuilder builder = new StringBuilder();
            String toRepeat = "";
            int repetition = 1;
            boolean isRepeating = false;
            for (; i < s.length() && Character.isDigit(s.charAt(i)); i++) {
                builder.append(s.charAt(i));
            }
            if (builder.length() > 0) {
                repetition = Integer.parseInt(builder.toString());
                // consume parenthesis
                isRepeating = true;
                i++;
            }

            builder.setLength(0);
            while (i < s.length() && Character.isLetter(s.charAt(i))) {
                builder.append(s.charAt(i));
                i++;
            }
            if (i < s.length()) {
                if (Character.isDigit(s.charAt(i))) {
                    Wrapper wrapper = newdfs(s, i);
                    builder.append(wrapper.result);
                    i = wrapper.index;
                }
            }
            toRepeat = builder.toString();
            builder.setLength(0);
            for (int j = 0; j < repetition; j++) {
                builder.append(toRepeat);
            }
            flatten.append(builder);
            if (isRepeating) i++;
            if (i < s.length() && s.charAt(i) == ']') break;
        }
        return new Wrapper(flatten.toString(), i);
    }

    public void test() {
        List<String> tests = Arrays.asList("3[a]2[bc]", "3[a2[c]]", "2[abc]3[cd]ef", "3[z]2[2[y]pq4[2[jk]e1[f]]]ef");
        List<String> expected = Arrays.asList("aaabcbc", "accaccacc", "abcabccdcdcdef", "zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef");
        for (int i = 0; i < tests.size(); i++) {
            String result = decodeString(tests.get(i));
            if (!result.equals(expected.get(i))) {
                System.out.println("Test " + i + " is wrong (" + result + "), it should be (" + expected.get(i) + ")");
                break;
            }
        }
    }
}
