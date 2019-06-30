import java.util.*;

/**
 * Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)
 *
 * Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).
 *
 * When you get an instruction "A", your car does the following: position += speed, speed *= 2.
 *
 * When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)
 *
 * For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.
 *
 * Now for some target position, say the length of the shortest sequence of instructions to get there.
 *
 * Example 1:
 * Input:
 * target = 3
 * Output: 2
 * Explanation:
 * The shortest instruction sequence is "AA".
 * Your position goes from 0->1->3.
 * Example 2:
 * Input:
 * target = 6
 * Output: 5
 * Explanation:
 * The shortest instruction sequence is "AAARA".
 * Your position goes from 0->1->3->7->7->6.
 *
 *
 * Note:
 *
 * 1 <= target <= 10000.
 *
 * Approach: Use bfs in order to obtain the shortest path.
 * Time complexity: O(2^n)
 * Memory complexity: O(n)
 */
public class RaceCar {
class State {
    int speed;
    int pos;
    int level;
    public State(int speed, int pos, int level) {
        this.speed = speed;
        this.pos = pos;
        this.level = level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(speed, pos);
    }

    @Override
    public boolean equals(Object obj) {
        State s2 = (State) obj;
        return this.speed == s2.speed &&
                this.pos == s2.pos;
    }
}
public int racecar(int target) {
    Set<State> seen = new HashSet<>();
    Queue<State> bfs = new LinkedList<>();
    bfs.add(new State(1, 0, 0));
    seen.add(new State(1, 0, 0));
    while (!bfs.isEmpty()) {
        State state = bfs.remove();
        if (state.pos == target) return state.level;
        int level = state.level + 1;
        State state1 = new State(state.speed << 1, state.pos + state.speed, level),
                state2 = new State(state.speed > 0 ? -1 : 1, state.pos, level);
        if (state1.pos > 0 && state1.pos < target * 2 && !seen.contains(state1)) {
            bfs.add(state1);
            seen.add(state1);
        }
        if (state2.pos > 0 && !seen.contains(state2)) {
            bfs.add(state2);
            seen.add(state2);
        }
    }
    return -1;
}

    public void test() {
        System.out.println(racecar(3));
        System.out.println(racecar(6));
        System.out.println(racecar(4));
    }
}
