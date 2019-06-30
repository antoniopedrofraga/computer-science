import javafx.util.Pair;

import java.util.*;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *    3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * return its level order traversal as:
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * Time Complexity: O(n)
 * Memory Complexity: O(n)
 */
public class LevelOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> levels = new LinkedList<>();
        nodes.add(root);
        levels.add(0);
        while (!nodes.isEmpty()) {
            int level = levels.remove();
            TreeNode node = nodes.remove();

            if (level == result.size()) {
                result.add(new ArrayList<>());
            }

            result.get(level).add(node.val);

            if (node.left != null) {
                nodes.add(node.left);
                levels.add(level + 1);
            }
            if (node.right != null) {
                nodes.add(node.right);
                levels.add(level + 1);
            }
        }
        return result;
    }


}
