import java.util.Arrays;

public class OptimalBST<T extends Comparable<T>>{

    private static int UNDEFINED = -1;

    Node<T> [] nodes;

    /* Optimal cost and optimal sub root from i to j */
    Double [][] costs;
    Integer [][] roots;

    class Node<T extends Comparable<T>> implements Comparable<Node> {
        T key;
        int appearances;
        Node(T key, int appearances) {
            if (this.appearances <= 0) {
                throw new IllegalArgumentException();
            }
            this.key = key;
            this.appearances = appearances;
        }
        @Override
        public int compareTo(Node o) {
            return key.compareTo((T) o.key);
        }
    }
    OptimalBST(T [] keys, int [] appearances) {
        if (keys.length != appearances.length) {
            throw new IllegalArgumentException();
        }
        nodes = new Node[keys.length];
        for (int i = 0; i < nodes.length; ++i) {
            nodes[i] = new Node(keys[i], appearances[i]);
        }
        Arrays.sort(nodes);
        costs = new Double[nodes.length][nodes.length];
        roots = new Integer[nodes.length][nodes.length];
        for (int i = 0; i < nodes.length; ++i) {
            for (int j = 0; j < nodes.length; ++j) {
                costs[i][j] = Double.MAX_VALUE;
                roots[i][j] = UNDEFINED;
            }
        }
        buildTree(0, nodes.length - 1, 1);
    }

    private double buildTree(int i, int j, int level) {
        if (roots[i][j] != UNDEFINED) {
            return costs[i][j];
        } else if (i == j) {
            return nodes[i].appearances * Math.log(level);
        }
        for (int r = i; r <= j; r++) {
            double candidateCost = buildTree(i, r - 1, level + 1) +
                    buildTree(r + 1, j, level + 1) + nodes[r].appearances * Math.log(level);
            if (candidateCost < costs[i][j]) {
                costs[i][j] = candidateCost;
                roots[i][j] = r;
            }
        }
        return costs[i][j];
    }
}
