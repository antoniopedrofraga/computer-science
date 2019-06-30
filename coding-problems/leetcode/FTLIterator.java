import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 * Input: [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,4,6].
 */
public class FTLIterator {
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    class IterationLevel {
        List<NestedInteger> list;
        int index;
        public IterationLevel(List<NestedInteger> list) {
            this.index = 0;
            this.list = list;
        }
    }
    public class NestedIterator implements Iterator<Integer> {
        Stack<IterationLevel> stack;
        boolean finished;
        public NestedIterator(List<NestedInteger> nestedList) {
            finished = false;
            stack = new Stack<>();
            stack.push(new IterationLevel(nestedList));
            nextInteger();
        }
        void nextInteger() {
            while (stack.size() > 0
                    && stack.peek().index >= stack.peek().list.size()) {
                stack.pop();
            }
            if (stack.size() == 0) {
                finished = true;
                return;
            }
            int i = stack.peek().index;
            List<NestedInteger> list = stack.peek().list;
            if (!list.get(i).isInteger()) {
                stack.peek().index++;
                stack.push(new IterationLevel(list.get(i).getList()));
                nextInteger();
            }
        }

        @Override
        public Integer next() {
            if (finished) return null;
            int i = stack.peek().index;
            stack.peek().index++;
            Integer result = stack.peek().list.get(i).getInteger();
            nextInteger();
            return result;
        }

        @Override
        public boolean hasNext() {
            return !finished;
        }
    }

}
