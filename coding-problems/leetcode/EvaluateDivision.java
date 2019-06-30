import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 *
 * Approach: This is a graph, run dfs and compute factors on the fly.
 */
public class EvaluateDivision {
    class Node {
        String a;
        boolean visited;
        Map<String, Double> equiv = new HashMap<>();
        public Node(String a) {
            this.a = a;
            this.visited = false;
        }
        public void addEquiv(String b, double factor) {
            equiv.put(b, factor);
        }
        @Override
        public boolean equals(Object o) {
            return this.a.equals(((Node)o).a);
        }
    }
    public double backtrack(Map<String, Node> dict, Node a, Node b) {
        Map<String, Double> childs = a.equiv;
        for (Map.Entry<String, Double> entry : childs.entrySet()) {
            double factor = entry.getValue();
            Node c = dict.get(entry.getKey());
            if (c.visited) { continue; }
            if (c.equals(b)) { return factor; }
            c.visited = true;
            double answer = backtrack(dict, c, b);
            c.visited = false;
            if (answer != -1.0) {
                return factor * answer;
            }
        }
        return -1.0;
    }
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Node> dict = new HashMap<>();
        double [] results = new double[queries.length];
        for (int i = 0; i < equations.length; i++) {
            String a = equations[i][0], b = equations[i][1];
            double k = values[i];
            Node node = dict.containsKey(a) ? dict.get(a) : new Node(a);
            node.addEquiv(b, k);
            dict.putIfAbsent(a, node);
            node = dict.containsKey(b) ? dict.get(b) : new Node(b);
            node.addEquiv(a, 1 / k);
            dict.putIfAbsent(b, node);
        }
        for (int i = 0; i < queries.length; i++) {
            results[i] = -1.0;
            Node a = dict.get(queries[i][0]), b = dict.get(queries[i][1]);
            if (a == null || b == null) {
                continue;
            }
            results[i] = backtrack(dict, a, b);
        }
        return results;
    }
    public void test() {
        System.out.println(Arrays.toString(
        calcEquation(new String[][]{{"a", "b"}, {"b", "c"}}, new double[]{2.0, 3.0}, new String[][]{{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}})
        ));
        System.out.println(Arrays.toString(
                calcEquation(new String[][]{{"x1","x2"},{"x2","x3"},{"x3","x4"},{"x4","x5"}}, new double[]{3.0,4.0,5.0,6.0}, new String[][]{{"x1","x5"},{"x5","x2"},{"x2","x4"},{"x2","x2"},{"x2","x9"},{"x9","x9"}})
        ));
    }
}
