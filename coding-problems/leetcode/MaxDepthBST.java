import java.util.Stack;

/**
 * A dummy description of MaxDepthBST
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
public class MaxDepthBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int maxDepth(TreeNode root) {
        int maxLevel = 0;
        Stack<TreeNode> nodes = new Stack<>();
        Stack<Integer> levels = new Stack<>();
        nodes.push(root);
        levels.push(1);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            int level = levels.pop();
            if (node == null) continue;
            maxLevel = Math.max(maxLevel, level);
            nodes.push(node.left);
            nodes.push(node.right);
            levels.push(level + 1);
            levels.push(level + 1);
        }
        return maxLevel;
    }

}
