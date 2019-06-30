import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * A dummy description of ReachNumber
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
public class ReachNumber {
    class Wrapper {
        int n;
        int number;
        public Wrapper(int number, int n) {
            this.number = number;
            this.n = n;
        }
    }
    int reachNumber(int k) {
        k = Math.abs(k);
        Queue<Wrapper> q = new LinkedList<>();
        Set<Integer> memo = new HashSet<>();

        q.add(new Wrapper(0, 1));
        while (!q.isEmpty()) {
            Wrapper top = q.remove();
            int n = top.n;
            int number = top.number;
            //memo.add(number);
            if (number == k) return n - 1;
            if (!memo.contains(number - n)) {
                q.add(new Wrapper(number - n , n + 1));
            }
            if (!memo.contains(number + n)) {
                q.add(new Wrapper(number + n , n + 1));
            }
        }
        return -1;
    }

    public void test() {
        System.out.println(reachNumber(2));
    }
}
