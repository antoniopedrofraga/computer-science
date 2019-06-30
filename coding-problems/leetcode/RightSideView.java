import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * Example:
 *
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */
public class RightSideView {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> result = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> level = new LinkedList<>();
        q.add(root);
        level.add(0);
        int lastLevel = 0, lastValue = root.val;
        while (!q.isEmpty()) {
            TreeNode node = q.remove();
            int currLevel = level.remove();
            if (node.left != null) {
                q.add(node.left);
                level.add(currLevel + 1);
            }
            if (node.right != null) {
                q.add(node.right);
                level.add(currLevel + 1);
            }
            if (currLevel != lastLevel) {
                result.add(lastValue);
            }
            lastValue = node.val;
            lastLevel = currLevel;
        }
        result.add(lastValue);
        return result;
    }
}
