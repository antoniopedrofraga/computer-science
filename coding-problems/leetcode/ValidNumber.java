import java.util.HashMap;
import java.util.Map;

public class ValidNumber {
    static final Map<Character, Integer> validLimits = new HashMap<Character, Integer>() {{
        put('0', Integer.MAX_VALUE);
        put('1', Integer.MAX_VALUE);
        put('2', Integer.MAX_VALUE);
        put('3', Integer.MAX_VALUE);
        put('4', Integer.MAX_VALUE);
        put('5', Integer.MAX_VALUE);
        put('6', Integer.MAX_VALUE);
        put('7', Integer.MAX_VALUE);
        put('8', Integer.MAX_VALUE);
        put('9', Integer.MAX_VALUE);
        put('+', 2);
        put('-', 2);
        put('e', 1);
        put('.', 1);
    }};
    Map<Character, Integer> currentLimits = new HashMap<Character, Integer>() {{
        put('0', 0);
        put('1', 0);
        put('2', 0);
        put('3', 0);
        put('4', 0);
        put('5', 0);
        put('6', 0);
        put('7', 0);
        put('8', 0);
        put('9', 0);
        put('+', 0);
        put('-', 0);
        put('e', 0);
        put('.', 0);
    }};
    boolean isSign(Character c) {
        return c == '+' || c == '-';
    }
    public boolean isNumber(String s) {
        StringBuilder trimed = new StringBuilder();
        boolean e = false, zero = false;
        s = s.trim();
        for (int i = 0; i < s.length(); i++) {
            if (!currentLimits.containsKey(s.charAt(i))) {
                return false;
            } else {
                int apps = currentLimits.get(s.charAt(i));
                apps++;
                if (apps > validLimits.get(s.charAt(i))) {
                    return false;
                }
                currentLimits.put(s.charAt(i), apps);
                trimed.append(s.charAt(i));
            }
        }
        if (trimed.length() == 0) {
            return false;
        } else if (trimed.length() == 1 && !Character.isDigit(trimed.charAt(0))) {
            return false;
        } else if (trimed.toString().equals("-.")) {
            return false;
        }
        for (int i = 0; i < trimed.length(); i++) {
            if ((trimed.charAt(i) == '+' || trimed.charAt(i) == '-')
                 && ((i > 0 && trimed.charAt(i - 1) != 'e') || i == trimed.length() - 1)) {
                return false;
            }
            if (trimed.charAt(i) == '.') {
                if (i == 0) {
                    zero = true;
                }
                if (e)
                    return false;
                else if (i > 0 && !Character.isDigit(trimed.charAt(i - 1)) && !isSign(trimed.charAt(i - 1)))
                    return false;
                else if (i < trimed.length() - 1 && !Character.isDigit(trimed.charAt(i + 1)) && trimed.charAt(i + 1) != 'e') {
                    return false;
                }
            }
            if (trimed.charAt(i) == 'e') {
                if (zero && trimed.charAt(i - 1) == '.')
                    return false;
                e = true;
                if (i == 0 || i == trimed.length() - 1 || (!Character.isDigit(trimed.charAt(i - 1)) && trimed.charAt(i - 1) != '.'))
                    return false;
            }
        }
        return true;
    }
}
