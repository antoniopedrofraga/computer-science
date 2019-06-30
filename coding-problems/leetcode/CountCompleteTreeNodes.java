import javafx.util.Pair;
import java.util.Stack;

/**
 * Given a complete binary tree, count the number of nodes.
 *
 * Note:
 *
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Example:
 *
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * Output: 6
 */
public class CountCompleteTreeNodes {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int nodes = 1, lastHeight = Integer.MAX_VALUE;
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 1));
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> top = stack.pop();
            TreeNode node = top.getKey();
            int height = top.getValue();
            if (node.right != null && node.left != null) {
                stack.push(new Pair<>(node.left, height + 1));
                stack.push(new Pair<>(node.right, height + 1));
            } else if (node.left != null) {
                int totalHeight = height + 1;
                nodes++;
                return (int) Math.pow(2, totalHeight) - nodes;
            } else {
                if (height > lastHeight) {
                    return (int) Math.pow(2, height) - nodes;
                }
                nodes += 2;
                lastHeight = height;
            }
        }
        return (int) Math.pow(2, lastHeight) - 1;
    }
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        System.out.println(countNodes(root));

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        System.out.println(countNodes(root2));
    }
}
