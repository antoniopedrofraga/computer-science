import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * Example:
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 *
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 *
 * Approach: serialize following leetcode approach.
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */


public class Codec {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        if (root == null) return "";
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node == null) {
                builder.append(",");
                continue;
            }
            builder.append(node.val + ",");
            queue.add(node.left);
            queue.add(node.right);
        }
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        String [] array = data.split(",");
        int index = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(array[0]));
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();

            if (index + 1 < array.length && !array[++index].equals("")) {
                int value = Integer.parseInt(array[index]);
                node.left = new TreeNode(value);
                queue.add(node.left);
            }
            if (index + 1 < array.length && !array[++index].equals("")) {
                int value = Integer.parseInt(array[index]);
                node.right = new TreeNode(value);
                queue.add(node.right);
            }
        }
        return root;
    }




    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode left2 = new TreeNode(4);
        TreeNode right2 = new TreeNode(5);
        root.left = left;
        root.right = right;
        right.left = left2;
        right.right = right2;
        System.out.println(serialize(root));
        TreeNode rootTest = deserialize(serialize(root));
        System.out.println(serialize(rootTest));
    }
    public void test1() {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode left2 = new TreeNode(4);
        TreeNode right2 = new TreeNode(5);
        root.left = left;
        root.right = right;
        right.left = left2;
        right.right = right2;
        System.out.println(serialize(root));
        TreeNode rootTest = deserialize(serialize(root));
        System.out.println(serialize(rootTest));
    }
}

