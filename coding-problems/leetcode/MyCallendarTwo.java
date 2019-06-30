import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.
 *
 * Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * Example 1:
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * Explanation:
 * The first two events can be booked.  The third event can be double booked.
 * The fourth event (5, 15) can't be booked, because it would result in a triple booking.
 * The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
 * The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
 * the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 *
 *
 * Note:
 *
 * The number of calls to MyCalendar.book per test case will be at most 1000.
 * In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 *
 * With n being the number of bookings.
 * Memory complexity: O(n)
 * Time complexity: O(n 2)
 */
public class MyCallendarTwo {
    class Node {
        int time;
        boolean start;
        public Node (int time, boolean start) {
            this.time = time;
            this.start = start;
        }
        @Override
        public boolean equals(Object obj) {
            Node n2 = (Node) obj;
            return this.time == n2.time &&
                    this.start == n2.start;
        }
    }
    List<Node> times = new ArrayList<>();
    public void insertSorted(Node node) {
        times.add(node);
        for (int i = times.size() - 1; i > 0; i--) {
            if (times.get(i - 1).time > node.time ||
                    (times.get(i - 1).time == node.time && times.get(i - 1).start)) {
                times.set(i, times.get(i - 1));
                times.set(i - 1, node);
            } else {
                break;
            }
        }
    }
    public boolean book(int start, int end) {
        Node startNode = new Node(start, true);
        Node endNode = new Node(end, false);
        insertSorted(startNode);
        insertSorted(endNode);
        int count = 0;
        for (int i = 0; i < times.size(); i++) {
            if (times.get(i).start)
                count++;
            else
                count--;
            if (count > 2) {
                times.remove(startNode);
                times.remove(endNode);
                return false;
            }
        }
        return true;
    }
    public void test() {
        List<Integer> starts = Arrays.asList(10, 50, 10, 5, 5, 25);
        List<Integer> ends = Arrays.asList(20, 60, 40, 15, 10, 55);
        List<Boolean> expected = Arrays.asList(true, true, true, false, true, true);
        for (int i = 0; i < starts.size(); i++) {
            System.out.println("(" + starts.get(i) + ", " + ends.get(i) + ") should be " + expected.get(i) + " and is " + book(starts.get(i), ends.get(i)));
        }
    }
}

