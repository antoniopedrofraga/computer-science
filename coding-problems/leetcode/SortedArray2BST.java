/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 *  Time complexity: O(n)
 *  Memory Complexity: O(log n)
 */
public class SortedArray2BST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        return buildTree(nums, 0, nums.length);
    }

    public TreeNode buildTree(int [] nums, int i, int j) {
        int mid = (j - i) / 2 + i;
        TreeNode root = new TreeNode(nums[mid]);
        if (j - i == 1) return root;
        if (j - i > 2) {
            root.right = buildTree(nums, mid + 1, j);
        }
        root.left = buildTree(nums, i, mid);
        return root;
    }
}
