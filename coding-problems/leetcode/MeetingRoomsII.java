import java.util.*;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 *
 * Example 1:
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 *
 * Example 2:
 * Input: [[7,10],[2,4]]
 * Output: 1
 *
 * Approach: Technically we can sort this kind of things in linear time.
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */
public class MeetingRoomsII {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    class KeyTime implements Comparable {
        boolean start;
        int time;
        public KeyTime(int time, boolean start) {
            this.time = time;
            this.start = start;
        }
        @Override
        public int compareTo(Object object) {
            KeyTime kt1 = this, kt2 = (KeyTime) object;
            if (kt1.time != kt2.time) return kt1.time - kt2.time;
            if (kt1.start) return 1;
            return -1;
        }
    }
    public int minMeetingRooms(Interval[] intervals) {
        int minRooms = 0, currRooms = 0;
        List<KeyTime> keyTimes = new ArrayList<>(intervals.length * 2);
        Arrays.stream(intervals).forEach(i -> {
            keyTimes.add(new KeyTime(i.start, true));
            keyTimes.add(new KeyTime(i.end, false));
        });
        Collections.sort(keyTimes);
        for (KeyTime keyTime : keyTimes) {
            currRooms += keyTime.start ? 1 : -1;
            minRooms = Math.max(minRooms, currRooms);
        }
        return minRooms;
    }

}
