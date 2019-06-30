import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Please use this Google doc to code during your interview. To free your hands for coding, we recommend that you use a headset or a phone with speaker option.
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *    1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * But the following [1,2,2,null,3,null,3] is not:
 *    1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * Approach: BFS with a queue, get elements in the same level into an Array List. Check if they come in the correct order.
 *
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */
public class IsSymmetricTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode a = queue.remove(),
                    b = queue.remove();

            if (a == null && b == null) continue;
            if (a == null || b == null) return false;
            if (a.val != b.val) return false;

            queue.add(a.left);
            queue.add(b.right);
            queue.add(b.left);
            queue.add(a.right);
        }
        return true;
    }
}
