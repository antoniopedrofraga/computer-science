import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 * Approach: Have a stack to simulate dfs.
 * Time complexity on each next/hasNext call: O(1)
 * Memory Complexity: O(h)
 */

class BSTIterator {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    class NodeWrapper {
        TreeNode node;
        boolean wentLeft;
        public NodeWrapper(TreeNode node, boolean wentLeft) {
            this.node = node;
            this.wentLeft = wentLeft;
        }
    }

    Stack<NodeWrapper> stack;

    public BSTIterator(TreeNode root) {
        TreeNode node = root;
        stack = new Stack<>();
        if (root != null) {
            stack.push(new NodeWrapper(root, false));
            subtreeMinimum(stack.peek());
        }
    }
    private void subtreeMinimum(NodeWrapper root) {
        TreeNode left = root.node.left;
        while (left != null) {
            root.wentLeft = true;
            root = new NodeWrapper(left, false);
            stack.push(root);
            left = left.left;
        }
    }
    public int next() {
        NodeWrapper nodeWrapper = stack.peek();
        int value = nodeWrapper.node.val;
        TreeNode node = nodeWrapper.node;
        if (node.left == null || nodeWrapper.wentLeft) {
            stack.pop();
            if (node.right != null) {
                stack.push(new NodeWrapper(node.right, false));
                subtreeMinimum(stack.peek());
            }
        } else {
            nodeWrapper.wentLeft = true;
            stack.push(new NodeWrapper(node.left, false));
        }
        return value;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

