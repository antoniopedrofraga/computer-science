public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    AVLTree(T rootValue) {
        super(rootValue);
    }

    @Override
    public boolean insert(T value) {
        int add;
        if (root == null) {
            root = new Node<T>(null, value);
            size++;
            return true;
        }
        add = insertInAVL(root,  value) ? 1 : 0;
        size += add;
        return add == 1;
    }

    @Override
    public void delete(T value) {
        if (!exists(value) || (value.equals(root.value) && size == 1)) {
            return;
        }
        Node<T> node = find(root, value);
        Node<T> parent = findParent(root, value);
        if (node.right == null && node.left == null) {
            if (parent.checkRightChild(value)) {
                parent.right = null;
            } else if (parent.checkLeftChild(value)) {
                parent.left = null;
            }
        } else if (node.right != null && node.left != null) {
            Node<T> successor = min(node.right), successorParent = successor.parent;
            if (successorParent.checkLeftChild(successor.value)) {
                successorParent.left = null;
            } else {
                successorParent.right = null;
            }
            node.value = successor.value;
            node = successorParent;
        } else {
            Node<T> newSubRoot = node.left == null ? node.right : node.left;
            if (parent.checkRightChild(value)) {
                parent.right = newSubRoot;
            } else if (parent.checkLeftChild(value)) {
                parent.left = newSubRoot;
            }
        }
        size--;
        while (node != null) {
            balanceIfNeeded(node);
            node = node.parent;
        }
    }

    private boolean insertInAVL(Node<T> node, T value) {
        boolean result = false;
        if (value.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new Node<T>(node, value);
                result = true;
            } else {
                result = insertInAVL(node.left, value);
            }
        } else if (value.compareTo(node.value) > 0) {
            if (node.right == null) {
                node.right = new Node<T>(node, value);
                result = true;
            } else {
                result = insertInAVL(node.right, value);
            }
        }
        if (result) {
            balanceIfNeeded(node);
        }
        return result;
    }

    private void balanceIfNeeded(Node<T> node) {
        if (isUnbalanced(node)) {
            boolean isLeftHeavy = isLeftHeavy(node);
            boolean isDoubleRotation = isLeftHeavy ?
                    node.left.right != null : node.right.left != null;
            if (!isDoubleRotation) {
                node = isLeftHeavy ? rotateRight(node) : rotateLeft(node);
            } else {
                Node<T> firstNode = isLeftHeavy ? rotateLeft(node.left) : rotateRight(node.right);
                updateHeight(firstNode);
                node = isLeftHeavy ? rotateRight(node) : rotateLeft(node);
            }
        }
        updateHeight(node);
    }

    private boolean isLeftHeavy(Node<T> node) {
        int left = node.left != null ? node.left.height : -1;
        int right = node.right != null ? node.right.height : -1;
        return left > right;
    }

    private boolean isUnbalanced(Node<T> node) {
        int left = node.left != null ? node.left.height : -1;
        int right = node.right != null ? node.right.height : -1;
        return Math.abs(left - right) >= 2;
    }
}
