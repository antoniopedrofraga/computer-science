import java.util.TreeSet;

/**
 * A dummy description of MyCalendar
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
public class MyCalendar {

    class Event implements Comparable<Event> {
        int time;
        boolean start;
        public Event(int time, boolean start) {
            this.time = time;
            this.start = start;
        }
        @Override
        public int compareTo(Event e2) {
            if (this.time == e2.time) {
                if (!start) return -1;
                else return 1;
            }
            return this.time - e2.time;
        }
        @Override
        public boolean equals(Object obj) {
            Event e2 = (Event) obj;
            return this.time == e2.time && this.start == e2.start;
        }
    }
    TreeSet<Event> bst;
    public MyCalendar() {
        bst = new TreeSet<>();
    }
    public boolean book(int start, int end) {
        Event startEvent = new Event(start, true),
                endEvent = new Event(end, false);
        if (bst.contains(startEvent)) {
            return false;
        }
        Event lowerStart = bst.lower(startEvent),
                lowerEnd = bst.lower(endEvent);
        if ((lowerStart != null && lowerStart.start) || lowerStart != lowerEnd) {
            return false;
        }
        bst.add(startEvent);
        bst.add(endEvent);
        return true;
    }

}
