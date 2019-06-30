/**
 * You are given a license key represented as a string S which consists only alphanumeric character and dashes. The string is separated into N+1 groups by N dashes.
 *
 * Given a number K, we would want to reformat the strings such that each group contains exactly K characters, except for the first group which could be shorter than K, but still must contain at least one character. Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.
 *
 * Given a non-empty string S and a number K, format the string according to the rules described above.
 */
public class LicenseKeyFormatting {
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder filtered = new StringBuilder(),
                builder = new StringBuilder();
        S.chars().forEach( c -> {
            if ((char) c != '-') {
                filtered.append(Character.toUpperCase((char)c));
            }
        });
        int i = filtered.length() % K;
        builder.append(filtered.substring(0, i));
        for (int j = i; j < filtered.length();) {
            if (j != 0) builder.append("-");
            builder.append(filtered.substring(j, j + K));
            j += K;
        }
        return builder.toString();
    }
    public void test() {
        System.out.println(licenseKeyFormatting(
                "2-4A0r7-4k", 3));
        System.out.println(licenseKeyFormatting("5F3Z-2e-9-w", 4));
        System.out.println(licenseKeyFormatting("2-5g-3-J", 2));
    }
}
