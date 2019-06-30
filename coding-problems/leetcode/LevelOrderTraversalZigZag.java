import java.util.*;

/**
 * A dummy description of LevelOrderTraversalZigZag
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
public class LevelOrderTraversalZigZag {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        Stack<TreeNode> currLevel = new Stack<>();
        Stack<TreeNode> newLevel = new Stack<>();
        currLevel.push(root);
        int level = 0;
        while (!currLevel.isEmpty()) {
            while (!currLevel.isEmpty()) {
                TreeNode node = currLevel.pop();
                if (level == result.size()) {
                    result.add(new ArrayList<>());
                }
                result.get(level).add(node.val);
                List<TreeNode> list = new LinkedList<>(Arrays.asList(node.left, node.right));
                if (level % 2 != 0)
                    Collections.reverse(list);
                for (TreeNode child : list) {
                    if (child != null)
                        newLevel.push(child);
                }
            }
            level++;
            Stack<TreeNode> temp = currLevel;
            currLevel = newLevel;
            newLevel = temp;
        }
        return result;
    }
}
