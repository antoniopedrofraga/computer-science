import java.util.ArrayList;
import java.util.List;

/**
 * You have an array of logs.  Each log is a space delimited string of words.
 *
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 *
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
 *
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 *
 * Return the final order of the logs.
 *
 *
 *
 * Example 1:
 *
 * Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 * Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 *
 *
 * Note:
 *
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */

public class ReorderLogFiles {
    boolean isLetterLog(String log) {
        boolean firstSpace = false;
        for (int i = 0; i < log.length(); i++) {
            if (log.charAt(i) == ' ') {
                firstSpace = true;
                continue;
            }
            if (firstSpace) {
                return !Character.isDigit(log.charAt(i));
            }
        }
        return true;
    }
    String getLogBody(String log) {
        for (int i = 0; i < log.length(); i++) {
            if (log.charAt(i) == ' ') {
                return log.substring(i + 1);
            }
        }
        return "";
    }
    public String[] reorderLogFiles(String[] logs) {
        String[] result = new String[logs.length];
        List<String> letterLogs = new ArrayList<>(),
                numberLogs = new ArrayList<>();
        for (String log : logs) {
            if (isLetterLog(log)) {
                letterLogs.add(log);
            } else {
                numberLogs.add(log);
            }
        }
        letterLogs.sort((s1, s2) -> {
            String body1 = getLogBody(s1),
                    body2 = getLogBody(s2);
            if (body1.compareTo(body2) == 0) {
                return s1.compareTo(s2);
            }
            return body1.compareTo(body2);
        });
        int i = 0;
        for (String log : letterLogs) {
            result[i++] = log;
        }
        for (String log : numberLogs) {
            result[i++] = log;
        }
        return result;
    }
}
