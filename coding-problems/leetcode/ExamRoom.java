import java.util.Iterator;
import java.util.TreeSet;

/**
 * In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.
 *
 * When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)
 *
 * Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.
 *
 *
 *
 * Example 1:
 *
 * Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
 * Output: [null,0,9,4,2,null,5]
 * Explanation:
 * ExamRoom(10) -> null
 * seat() -> 0, no one is in the room, then the student sits at seat number 0.
 * seat() -> 9, the student sits at the last seat number 9.
 * seat() -> 4, the student sits at the last seat number 4.
 * seat() -> 2, the student sits at the last seat number 2.
 * leave(4) -> null
 * seat() -> 5, the student sits at the last seat number 5.
 * ​​​​​​​
 *
 * Note:
 *
 * 1 <= N <= 10^9
 * ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
 * Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.
 *
 * Time complexity: O(n log n)
 * Memory complexity: O(n)
 */
class ExamRoom {
    public ExamRoom() {}
    int n;
    TreeSet<Integer> bst = new TreeSet();
    public ExamRoom(int N) {
        this.n = N;
        this.bst = new TreeSet<>();
    }

    public int seat() {
        int bestDist = 0, bestPlace = 0, lastPlace = 0;

        if (bst.size() > 0) {
            int first = bst.first();
            bestDist = first - lastPlace;
        }

        for (int place : bst) {
            int dist = (place - lastPlace) / 2;
            if (dist > bestDist) {
                bestDist = dist;
                bestPlace = lastPlace + bestDist;
            }
            lastPlace = place;
        }

        if (lastPlace != n - 1 && bst.size() != 0) {
            int dist = ((n - 1) - lastPlace);
            if (dist > bestDist) {
                bestDist = dist;
                bestPlace = lastPlace + bestDist;
            }
        }
        bst.add(bestPlace);
        return bestPlace;
    }

    public void leave(int p) {
        bst.remove(p);
    }

    public void test() {
        ExamRoom examRoom = new ExamRoom(10);
        for (int i = 0; i < 4; i++) {
            System.out.println(examRoom.seat());
        }
        examRoom.leave(4);
        System.out.println(examRoom.seat());
    }
}
