public class Palindrome {
    public boolean isPalindrome(int x) {
        String number = Integer.toString(x);
        String reverse = new StringBuilder(number).reverse().toString();
        return number.equals(reverse);
    }
}
