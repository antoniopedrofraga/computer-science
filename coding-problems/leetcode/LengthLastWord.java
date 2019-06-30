public class LengthLastWord {
    public int lengthOfLastWord(String s) {
        int counter = 0, i = s.length() - 1;
        for (; i >= 0 && s.charAt(i) == ' '; i--) {}
        for (; i >= 0 && Character.isAlphabetic(s.charAt(i)); i--) { counter++; }
        return counter;
    }
}
