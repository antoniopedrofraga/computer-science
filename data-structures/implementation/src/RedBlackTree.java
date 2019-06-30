public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    int falseCount = 0;
    RedBlackTree(T rootValue) {
        super(rootValue);
    }
    
    @Override
    public boolean insert(T value) {
        if (super.insert(value)) {
            Node<T> node = find(this.root, value);
            while (node.parent != null && node.parent.parent != null && !node.parent.black) {
                Node<T> parent = node.parent;
                Node<T> grandparent = parent.parent;
                if (grandparent.checkLeftChild(parent.value)) {
                    Node<T> y = grandparent.right;
                    if (y != null && !y.black) {
                        parent.black = true;
                        y.black = true;
                        grandparent.black = false;
                        node = grandparent;
                    } else if (parent.checkRightChild(node.value)) {
                        node = parent;
                        rotateLeft(node);
                        node.parent.black = true;
                        node.parent.parent.black = false;
                        rotateRight(node.parent.parent);
                    } else {
                        parent.black = true;
                        grandparent.black = false;
                        rotateRight(grandparent);
                    }
                } else if (grandparent.checkRightChild(parent.value)) {
                    Node<T> y = grandparent.left;
                    if (y != null && !y.black) {
                        parent.black = true;
                        y.black = true;
                        grandparent.black = false;
                        node = grandparent;
                    } else if (parent.checkLeftChild(value)) {
                        node = parent;
                        rotateRight(node);
                        node.parent.black = true;
                        node.parent.parent.black = false;
                        rotateLeft(node.parent.parent);
                    } else {
                        parent.black = true;
                        grandparent.black = false;
                        rotateLeft(grandparent);
                    }
                }
            }
            root.black = true;
            size++;
            return true;
        }
        return false;
    }

    private void transplant(Node<T> u, Node<T> v) {
        Node<T> parent = u.parent;
        if (parent == null) {
            root = v;
        } else if (parent.checkLeftChild(u.value)) {
            parent.left = v;
        } else {
            parent.right = v;
        }
        v.parent = u.parent;
    }
    private void deleteFixup(Node<T> x) {
        while (x.value.compareTo(root.value) != 0 && x.black) {
            Node<T> parent = x.parent;
            if (parent.checkLeftChild(x.value)) {
                Node<T> w = parent.right;
                if (!w.black) {
                    w.black = true;
                    parent.black = false;
                    rotateLeft(parent);
                    w = x.parent.right;
                }
                if (w.left.black && w.right.black) {
                    w.black = false;
                    x = x.parent;
                } else if (w.right.black) {
                    w.left.black = true;
                    w.black = false;
                    rotateRight(w);
                    w = w.parent.right;
                    w.black = x.parent.black;
                    x.parent.black = true;
                    w.right.black = true;
                    rotateLeft(x.parent);
                    x = root;
                } else {
                    w = w.parent.right;
                    w.black = x.parent.black;
                    x.parent.black = true;
                    w.right.black = true;
                    rotateLeft(x.parent);
                    x = root;
                }
            } else if (parent.checkRightChild(x.value)) {
                Node<T> w = parent.left;
                if (!w.black) {
                    w.black = true;
                    parent.black = false;
                    rotateRight(parent);
                    w = x.parent.left;
                }
                if (w.right.black && w.left.black) {
                    w.black = false;
                    x = x.parent;
                } else if (w.left.black) {
                    w.right.black = true;
                    w.black = false;
                    rotateLeft(w);
                    w = w.parent.left;
                    w.black = x.parent.black;
                    x.parent.black = true;
                    w.left.black = true;
                    rotateRight(x.parent);
                    x = root;
                } else {
                    w = w.parent.left;
                    w.black = x.parent.black;
                    x.parent.black = true;
                    w.left.black = true;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.black = true;
    }
    @Override
    public void delete(T value) {
        if (!exists(value) || (value.equals(root.value) && size == 1)) {
            return;
        }
        Node<T> y = find(root, value), x = null, z = null;
        z = y;
        boolean originallyBlack = y.black;

        if (y.left == null) {
            x = y.right;
            transplant(y, y.right);
        } else if (y.right == null) {
            x = y.left;
            transplant(y, y.left);
        } else {
            y = min(y.right);
            originallyBlack = y.black;
            x = y.right;
            if (z.value.compareTo(y.parent.value) == 0) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                if (y.right != null) {
                    y.right.parent = y;
                }
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.black = z.black;
        }
        if (originallyBlack) {
            deleteFixup(x);
        }
        size--;
    }

    boolean verifyTree() {
        if (!root.black) {
            System.out.println("Root is not black!");
            return false;
        }
        return verifyNode(this.root) != -1;
    }
    int verifyNode(Node<T> node) {
        if (node == null) {
            return 1;
        }
        if (!node.black) {
            boolean blackLeft = node.left == null || node.left.black;
            boolean blackRight = node.right == null || node.right.black;
            if (!blackLeft || !blackRight) {
                System.out.println("Node " + node.value + " is red, but has red childs!");
                return -1;
            }
        }
        int countLeft = verifyNode(node.left);
        int countRight = verifyNode(node.right);
        if (countLeft == -1 || countRight == -1) {
            return -1;
        } else if (countLeft != countRight) {
            System.out.println("Node " + node.value + " has different left and right black nodes count.");
            return -1;
        } else {
            return countLeft + (node.black ? 1 : 0);
        }
    }
}
