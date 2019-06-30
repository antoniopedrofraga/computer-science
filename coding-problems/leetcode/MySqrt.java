public class MySqrt {
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        long start = 1, end = x, mid = start + (end - start) / 2;;
        while (start < mid) {
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid < x) {
                start = mid;
            } else {
                end = mid;
            }
            mid = start + (end - start) / 2;
        }
        return (int) start;
    }
}
