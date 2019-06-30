import java.util.*;

/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 *
 * Example:
 *
 * Input: 3
 * Output:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 *
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *
 * Definition for a binary tree node.
 *
 *
 *
 */

public class UniqueBSTII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return Collections.emptyList();
        Map<Integer, List<TreeNode>> memo = new HashMap<>();
        return recursiveBuild(memo, 1, n + 1, n + 1);
    }

    public List<TreeNode> recursiveBuild(Map<Integer, List<TreeNode>> memo, int j, int k, int limit) {
        int index = j * limit + k;
        if (memo.containsKey(index)) return memo.get(index);
        List<TreeNode> result = new ArrayList<>();
        for (int i = j; i < k; i++) {
            List<TreeNode> leftNodes = recursiveBuild(memo, j, i, limit);
            List<TreeNode> rightNodes = recursiveBuild(memo, i + 1, k, limit);
            for (TreeNode left : leftNodes) {
                for (TreeNode right : rightNodes) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        memo.put(index, result);
        return result;
    }


}
