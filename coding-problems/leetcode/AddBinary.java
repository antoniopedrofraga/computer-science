public class AddBinary {
    public String addBinary(String a, String b) {
        int minLength = Math.min(a.length(), b.length()), carry = 0, i = 0;
        String maxString = a.length() > b.length() ? a : b;
        StringBuilder result = new StringBuilder();
        for (; i < minLength; i++) {
            int j = a.length() - 1 - i,
                    k = b.length() - 1 - i;
            carry += Character.getNumericValue(b.charAt(k)) + Character.getNumericValue(a.charAt(j));
            int digit = carry % 2;
            carry /= 2;
            result.append(Character.forDigit(digit, 10));
        }
        for (; i < maxString.length(); i++) {
            int j = maxString.length() - 1 - i;
            carry += Character.getNumericValue(maxString.charAt(j));
            int digit = carry % 2;
            carry /= 2;
            result.append(Character.forDigit(digit, 10));
        }
        while (carry > 0) {
            int digit = carry % 2;
            carry /= 2;
            result.append(Character.forDigit(digit, 10));
        }
        return result.reverse().toString();
    }
}
