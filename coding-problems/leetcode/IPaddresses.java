import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * Example:
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 *
 * Approach 1: Use backtracking to search all possible combinations of strings.
 * Approach 2: Use dynamic programming somehow.
 */
public class IPaddresses {
    class IP {
        int bytes;
        String ip;
        public IP(String ip, int bytes) {
            this.bytes = bytes;
            this.ip = ip;
        }
    }

    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4) return Collections.emptyList();
        List<IP> a = new ArrayList<>(), b = new ArrayList<>(), c = new ArrayList<>();

        a.add(new IP("", 0));
        b.add(new IP("", 0));
        c.add(new IP("", 0));

        for (int i = 0; i < s.length(); i++) {
            List<IP> curr = new ArrayList<>();
            int num3 = Character.getNumericValue(s.charAt(i));
            int num2 = i > 0 ? Character.getNumericValue(s.charAt(i - 1)) * 10 + num3 : -1;
            int num1 = i > 1 ? Character.getNumericValue(s.charAt(i - 2)) * 100 + num2 : -1;
            if (num1 >= 100 && num1 <= 255) {
                for (IP ip : a) {
                    if (ip.bytes + 1 <= 4 && 3 - ip.bytes < s.length() - i) {
                        String prefix = ip.ip.length() > 0 ? ip.ip + "." : "";
                        curr.add(new IP(prefix + s.substring(i - 2, i + 1), ip.bytes + 1));
                    }
                }
            }
            if (num2 >= 10 && num2 < 100) {
                for (IP ip : b) {
                    if (ip.bytes + 1 <= 4 && 3 - ip.bytes < s.length() - i) {
                        String prefix = ip.ip.length() > 0 ? ip.ip + "." : "";
                        curr.add(new IP(prefix + s.substring(i - 1, i + 1), ip.bytes + 1));
                    }
                }
            }
            if (num3 >= 0 && num3 < 10) {
                for (IP ip : c) {
                    if (ip.bytes + 1 <= 4 && 3 - ip.bytes < s.length() - i) {
                        String prefix = ip.ip.length() > 0 ? ip.ip + "." : "";
                        curr.add(new IP(prefix + s.substring(i, i + 1), ip.bytes + 1));
                    }
                }
            }
            a = b;
            b = c;
            c = curr;
        }
        return c.stream().map(ip1 -> ip1.ip).collect(Collectors.toList());
    }

}
