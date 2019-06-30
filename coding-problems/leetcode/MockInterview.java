import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A dummy description of MockInterview
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
public class MockInterview {
    class MovingAverage {
        int size;
        Queue<Integer> q;
        int currSum;

        /** Initialize your data structure here. */
        public MovingAverage(int size) {
            this.q = new LinkedList<>();
            this.size = size;
            this.currSum = 0;
        }

        public double next(int val) {
            this.currSum += val;
            this.q.add(val);
            if (this.q.size() > this.size) {
                this.currSum -= this.q.remove();
            }
            return ((double) this.currSum / (double) this.q.size());
        }
    }

    public void addToList(List<String> ranges, long low, long high) {
        ranges.add(low == high ? low + "" : low + "->" + high);
    }
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ranges = new LinkedList<>();
        long previous = lower;
        if (nums.length == 0) {
            addToList(ranges, previous, upper);
            return ranges;
        }
        if (lower < nums[0]) {
            addToList(ranges, lower, nums[0] - 1);
            previous = nums[0];
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > previous + 1) {
                addToList(ranges, previous + 1, nums[i] - 1);
            }
            previous = nums[i];
        }
        if (upper > previous) {
            addToList(ranges, previous + 1, upper);
        }
        return ranges;
    }


    public void test() {
        /*MovingAverage ma = new MovingAverage(3);
        System.out.println(ma.next(3));
        System.out.println(ma.next(1));
        System.out.println(ma.next(10));
        System.out.println(ma.next(5));*/
        System.out.println(findMissingRanges(new int[]{0,1,3,50,75},0 , 99));
        System.out.println(findMissingRanges(new int[]{2147483647},0 , 2147483647));
        System.out.println(findMissingRanges(new int[]{},1 , 1));
        System.out.println(findMissingRanges(new int[]{-1},-1 , -1));
        System.out.println(findMissingRanges(new int[]{-2147483648, 2147483647},-2147483648 , 2147483647));
    }
}
