import com.sun.deploy.util.OrderedHashSet;
import com.sun.jmx.remote.internal.ArrayQueue;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;

public class MergeIntervals {
    public class Interval {
        public int start;
        public int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> finalIntervals = new ArrayList<>();
        Interval currentInterval = new Interval();
        int currentEnd = 0;

        // O(n) with linear sorting (we're sorting integers)
        Collections.sort(intervals, Comparator.comparing(i -> i.start));

        if (!intervals.isEmpty()) {
            currentEnd = intervals.get(0).end;
            currentInterval.start = intervals.get(0).start;
        }
        for (int i = 0; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (interval.start <= currentEnd) {
               currentEnd = Math.max(interval.end, currentEnd);
            } else {
               currentInterval.end = currentEnd;
               finalIntervals.add(currentInterval);
               currentInterval = new Interval();
               currentInterval.start = interval.start;
               currentEnd = interval.end;
            }
            if (i == intervals.size() - 1) {
                currentInterval.end = currentEnd;
                finalIntervals.add(currentInterval);
            }
        }
        return finalIntervals;
    }

    /*
        Time -> O(n)
        Space -> O(n)
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();

        for(Interval i: intervals) {https://www.youtube.com/watch?list=PLjbi89IuxHOGmdJKWmgw_PneRNyiAK_x4&v=m7OLRC9sL5A
            if(newInterval == null || i.end < newInterval.start) {
                res.add(i);
            } else if(newInterval.end < i.start) {
                res.add(newInterval);
                res.add(i);
                newInterval = null;
            } else {
                newInterval.start = Math.min(i.start, newInterval.start);
                newInterval.end = Math.max(i.end, newInterval.end);
            }
        }

        if(newInterval != null) {
            res.add(newInterval);
        }

        return res;
    }
}
