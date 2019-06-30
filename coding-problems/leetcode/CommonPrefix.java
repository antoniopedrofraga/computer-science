public class CommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String smallestString;
        if (strs.length == 0) {
            return "";
        }

        smallestString = strs[0];
        for (int i = 1; i < strs.length; ++i) {
            if (smallestString.length() > strs[i].length()) {
                smallestString = strs[i];
            }
        }

        for (String str : strs) {
            for (int i = 0; i < smallestString.length(); ++i) {
                if (str.charAt(i) != smallestString.charAt(i)) {
                    smallestString = smallestString.substring(0, i + 1);
                    break;
                }
            }
            if (smallestString.isEmpty()) {
                return "";
            }
        }

        return smallestString;
    }
}
