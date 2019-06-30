import java.util.*;

/**
 * A dummy description of DailyTemperatures
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        int [] daily = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int j = stack.peek();
                daily[j] = i - j;
                stack.pop();
            }
            stack.push(i);
        }
        return daily;
    }

    public int[] dailyTemperaturesBST(int[] T) {
        int [] daily = new int[T.length];
        TreeMap<Integer, List<Integer>> bst = new TreeMap<>();
        for (int i = 0; i < T.length; i++) {
            Map.Entry<Integer, List<Integer>> lowerEntry = bst.lowerEntry(T[i]);
            while ((lowerEntry = bst.lowerEntry(T[i])) != null) {
                int temp = lowerEntry.getKey();
                List<Integer> js = lowerEntry.getValue();
                for (int j : js) {
                    daily[j] = i - j;
                }
                bst.remove(temp);
            }
            List<Integer> list = bst.containsKey(T[i]) ? bst.get(T[i]) : new LinkedList<>();
            list.add(i);
            bst.put(T[i], list);
        }
        return daily;
    }

}
