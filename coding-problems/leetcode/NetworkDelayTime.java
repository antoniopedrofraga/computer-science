import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 *
 * Note:
 *
 * N will be in the range [1, 100].
 * K will be in the range [1, N].
 * The length of times will be in the range [1, 6000].
 * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 *
 * Approach: We want to find the shortest path in the graph, therefore we need to run dijkstra.
 *
 * Time complexity: If we can use a fibonacci heap, we could run this algorithm in O(n log n + k)
 * Memory complexity: O(n^2), in the worst case we have an edge in every direction.
 */
public class NetworkDelayTime {
    class Node implements Comparable<Node> {
        int id;
        int weight = Integer.MAX_VALUE;
        List<Integer> childs = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();
        public Node(int id) {
            this.id = id;
        }
        @Override
        public int compareTo(Node n2) {
            return this.weight - n2.weight;
        }
        @Override
        public boolean equals(Object obj) {
            Node n2 = (Node) obj;
            return this.id == n2.id;
        }
    }
    public int networkDelayTime(int[][] times, int N, int K) {
        Node [] nodes = new Node[N + 1];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }
        for (int [] time : times) {
            int u = time[0], v = time[1], w = time[2];
            nodes[u].childs.add(v);
            nodes[u].weights.add(w);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        nodes[K].weight = 0;
        pq.add(nodes[K]);
        while (!pq.isEmpty()) {
            Node top = pq.remove();
            int currentWeight = top.weight;
            for (int i = 0; i < top.childs.size(); i++) {
                int j = top.childs.get(i);
                int weight = top.weights.get(i);
                Node child = nodes[j];
                if (child.weight > currentWeight + weight) {
                    pq.remove(child);
                    child.weight = currentWeight + weight;
                    pq.add(child);
                }
            }
        }
        int max = 0;
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i].weight == Integer.MAX_VALUE) {
                return -1;
            } else if (nodes[i].weight > max) {
                max = nodes[i].weight;
            }
        }
        return max;
    }

    public void test() {
        System.out.println(
                networkDelayTime(
                        new int[][] {{2,1,1},{2,3,1},{3,4,1}},4, 2
                )
        );
    }
}
