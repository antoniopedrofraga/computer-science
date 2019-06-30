import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 *
 * Example:
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 *
 * Follow up:
 *
 * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 *
 * Approach: Initialize min heap, that should hold elements greater than the median. Initialize a max heap, that should hold elements less than the median.
 *
 * Time complexity: O(n log n)
 * Memory complexity: O(n)
 */
public class MedianFinder {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public MedianFinder() {}

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() + 1 < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    public double findMedian() {
        if ((maxHeap.size() + minHeap.size()) % 2 == 1) {
            return minHeap.peek();
        } else {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
    }
    public void test() {
        test1();
        test2();
    }
    public void test1() {
        System.out.println();
        System.out.println("Test 1");
        MedianFinder finder = new MedianFinder();
        List<String> cmds = Arrays.asList("MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian");
        List<Integer> nums = Arrays.asList(null, -1, null, -2, null, -3, null, -4, null, -5, null);
        List<Double> expected = Arrays.asList(null,null,-1.0,null,-1.5,null,-2.0,null,-2.5,null,-3.0);
        for (int i = 0; i < cmds.size(); i++) {
            if (cmds.get(i).equals("MedianFinder")) {
                finder = new MedianFinder();
            } else if (cmds.get(i).equals("addNum")) {
                finder.addNum(nums.get(i));
            } else if (cmds.get(i).equals("findMedian")) {
                if (finder.findMedian() != expected.get(i)) {
                    System.out.println("Find median at index i = " + i + " returned " + finder.findMedian() + " but should have returned " + expected.get(i));
                }
            }
        }
    }
    
    public void test2() {
        System.out.println();
        System.out.println("Test 2");
        MedianFinder finder = new MedianFinder();
        List<String> cmds = Arrays.asList("MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian");
        List<Integer> nums = Arrays.asList(null,12,null,10,null,13,null,11,null,5,null,15,null,1,null,11,null,6,null,17,null,14,null,8,null,17,null,6,null,4,null,16,null,8,null,10,null,2,null,12,null,0,null);
        List<Double> expected = Arrays.asList(null,null,12.0,null,11.0,null,12.0,null,11.5,null,11.0,null,11.5,null,11.0,null,11.0,null,11.0,null,11.0,null,11.0,null,11.0,null,11.0,null,11.0,null,11.0,null,11.0,null,11.0,null,10.5,null,10.0,null,10.5,null,10.0);
        for (int i = 0; i < cmds.size(); i++) {
            if (cmds.get(i).equals("MedianFinder")) {
                finder = new MedianFinder();
            } else if (cmds.get(i).equals("addNum")) {
                finder.addNum(nums.get(i));
            } else if (cmds.get(i).equals("findMedian")) {
                if (finder.findMedian() != expected.get(i)) {
                    System.out.println("Find median at index i = " + i + " returned " + finder.findMedian() + " but should have returned " + expected.get(i));
                }
            }
        }
    }
}
