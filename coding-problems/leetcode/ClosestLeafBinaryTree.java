/**
 * Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.
 *
 * Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.
 *
 * In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.
 *
 * Example 1:
 *
 * Input:
 * root = [1, 3, 2], k = 1
 * Diagram of binary tree:
 *           1
 *          / \
 *         3   2
 *
 * Output: 2 (or 3)
 *
 * Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
 * Example 2:
 *
 * Input:
 * root = [1], k = 1
 * Output: 1
 *
 * Explanation: The nearest leaf node is the root node itself.
 * Example 3:
 *
 * Input:
 * root = [1,2,3,4,null,null,null,5,null,6], k = 2
 * Diagram of binary tree:
 *              1
 *             / \
 *            2   3
 *           /
 *          4
 *         /
 *        5
 *       /
 *      6
 *
 * Output: 3
 * Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
 * Note:
 * root represents a binary tree with at least 1 node and at most 1000 nodes.
 * Every node has a unique node.val in range [1, 1000].
 * There exists some node in the given binary tree for which node.val == k.
 *
 *
 * Approach: Use backtracking in order to know which updates to make.
 * Memory complexity: O(h)
 * Time complexity: O(n)
 *
 * [ kDist, bestAnswer, leafDist, closestLeaf ]
 */
public class ClosestLeafBinaryTree {
    final static int UNDEFINED = Integer.MAX_VALUE, K_DIST = 0, BEST_ANSWER = 1, LEAF_DIST = 2, CLOSEST_LEAF = 3;

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int findClosestLeaf(TreeNode root, int k) {
        int [] result = findClosestLeafRecursive(root, k);
        return result[CLOSEST_LEAF];
    }

    int [] findClosestLeafRecursive(TreeNode root, int k) {
        if (root == null) {
            return new int[]{UNDEFINED, UNDEFINED, UNDEFINED, UNDEFINED};
        }
        int [] left = findClosestLeafRecursive(root.left, k);
        int [] right = findClosestLeafRecursive(root.right, k);
        int leafDist, currentClosestLeaf = UNDEFINED;
        if (left[LEAF_DIST] == UNDEFINED && right[LEAF_DIST] == UNDEFINED) {
            leafDist = 0;
            currentClosestLeaf = root.val;
        } else if (left[LEAF_DIST] < right[LEAF_DIST]) {
            leafDist = left[LEAF_DIST];
            currentClosestLeaf = left[CLOSEST_LEAF];
        } else {
            leafDist = right[LEAF_DIST];
            currentClosestLeaf = right[CLOSEST_LEAF];
        }
        int kDist = getKDist(root, k, left, right);
        int best = UNDEFINED, closestLeaf = currentClosestLeaf;
        if (left[BEST_ANSWER] != UNDEFINED) {
            best = left[1];
            closestLeaf = left[CLOSEST_LEAF];
        } else if (right[BEST_ANSWER] != UNDEFINED) {
            best = right[1];
            closestLeaf = right[CLOSEST_LEAF];
        }
        if (kDist != UNDEFINED && kDist + leafDist < best) {
            best = kDist + leafDist;
            closestLeaf = currentClosestLeaf;
        }

        left[K_DIST] = kDist != UNDEFINED ? kDist + 1 : kDist;
        left[BEST_ANSWER] = best;
        left[LEAF_DIST] = leafDist + 1;
        left[CLOSEST_LEAF] = closestLeaf;
        return left;
    }

    int getKDist(TreeNode root, int k, int [] left, int [] right) {
        int kDist = UNDEFINED;
        if (left[K_DIST] != UNDEFINED) {
            kDist = Math.min(kDist, left[K_DIST]);
        }
        if (right[K_DIST] != UNDEFINED) {
            kDist = Math.min(kDist, right[K_DIST]);
        }
        if (root.val == k) {
            kDist = 0;
        }
        return kDist;
    }

    public void test() {
        TreeNode root = new TreeNode(1), left = null;
        root.right = new TreeNode(3);
        left = root.left = new TreeNode(2);
        left = left.left = new TreeNode(4);
        left = left.left = new TreeNode(5);
        left.left = new TreeNode(6);
        System.out.println(findClosestLeaf(root, 2));
    }
}
