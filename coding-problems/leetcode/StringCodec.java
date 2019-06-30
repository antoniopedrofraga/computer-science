import java.util.*;

/**
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
 *
 * Machine 1 (sender) has the function:
 *
 * string encode(vector<string> strs) {
 *   // ... your code
 *   return encoded_string;
 * }
 * Machine 2 (receiver) has the function:
 * vector<string> decode(string s) {
 *   //... your code
 *   return strs;
 * }
 * So Machine 1 does:
 *
 * string encoded_string = encode(strs);
 * and Machine 2 does:
 *
 * vector<string> strs2 = decode(encoded_string);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 *
 * Implement the encode and decode methods.
 *
 *
 *
 * Note:
 *
 * The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 * Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
 */
public class StringCodec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strs.size(); i++) {
            builder.append(strs.get(i));

            builder.append("-#*");
        }
        return builder.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (i + 2 < s.length() && s.charAt(i) == '-' && s.charAt(i + 1) == '#' && s.charAt(i + 2) == '*') {
                result.add(builder.toString());
                builder.setLength(0);
                i += 3;
            } else {
                builder.append(s.charAt(i));
                i++;
            }
        }
        return result;
    }

    public void test() {
        String encoded = encode(Arrays.asList("test1", "test2"));
        List<String> decoded = decode(encoded);
        System.out.println(decoded.toString());
        String encoded1 = encode(Collections.emptyList());
        List<String> decoded1 = decode(encoded1);
        System.out.println(decoded1.toString());
        String encoded2= encode(Arrays.asList("", ""));
        List<String> decoded2 = decode(encoded2);
        System.out.println(decoded2.toString());
    }
}
