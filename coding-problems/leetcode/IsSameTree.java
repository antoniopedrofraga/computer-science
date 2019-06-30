import java.util.Stack;

/**
 * Given two binary trees, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 * Example 1:
 * Input:     1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *
 * Output: true
 *
 * Example 2:
 * Input:     1         1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 *
 * Output: false
 *
 * Example 3:
 * Input:     1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 *
 * Output: false
 *
 * Time complexity: O(n)
 * Memory complexity: O(n)
 *
 */
public class IsSameTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<TreeNode> pStack = new Stack<>(),
                qStack = new Stack<>();
        pStack.push(p);
        qStack.push(q);
        while (!pStack.isEmpty()) {
            if (pStack.size() != qStack.size()) {
                return false;
            }
            p = pStack.pop();
            q = qStack.pop();
            if (p == null && q == null) {
                continue;
            } else if (p == null || q == null) {
                return false;
            } else if (p.val != q.val) {
                return false;
            }
            pStack.push(p.left);
            pStack.push(p.right);
            qStack.push(q.left);
            qStack.push(q.right);
        }
        return pStack.size() == qStack.size();
    }

}
