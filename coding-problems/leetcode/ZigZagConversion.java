public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;

        StringBuilder [] result = new StringBuilder[numRows];
        boolean down = false;

        for (int i = 0; i < numRows; i++) {
            result[i] = new StringBuilder("");
        }
        for (int i = 0, index = 0; i < s.length(); i++) {
            if (i % (numRows - 1) == 0) down = !down;
            result[index].append(s.charAt(i));
            index += down ? 1 : -1;
        }

        return String.join("", result);
    }
}
