
@SuppressWarnings({"WeakerAccess", "unused"})
public class BinarySearchTree<T extends Comparable<T>> {
    protected class Node<T extends Comparable<T>> {
        T value;
        int height;
        boolean black;
        Node<T> parent, left, right;
        Node(Node<T> parent, T value) {
            this.parent = parent;
            this.value = value;
            this.height = 0;
            this.black = false;
        }
        boolean checkLeftChild(T value) {
            if (left == null) {
                return false;
            }
            return value.compareTo(left.value) == 0;
        }

        boolean checkRightChild(T value) {
            if (right == null) {
                return false;
            }
            return value.compareTo(right.value) == 0;
        }

        StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb, boolean leaf) {
            if(right != null) {
                right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, false);
            } else if (!leaf){
                Node<T> node = new Node(this, "NULL");
                node.black = true;
                node.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, true);
            }
            sb.append(prefix).append(isTail ? "└── " : "┌── ").append(value.toString() + " (" + (black ? "BLACK" : "RED") + ")").append("\n");
            if(left != null) {
                left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, false);
            } else if (!leaf){
                Node<T> node = new Node(this, "NULL");
                node.black = true;
                node.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, true);
            }
            return sb;
        }

        @Override
        public String toString() {
            return this.toString(new StringBuilder(), true, new StringBuilder(), false).toString();
        }
    }

    Node<T> root;
    int size;

    BinarySearchTree(T rootValue) {
        root = new Node<T>(null, rootValue);
        size = 1;
    }

    public boolean insert(T value) {
        int add;
        if (root == null) {
            root = new Node<T>(null, value);
            size++;
            return true;
        }
        add = insert(root,  value) ? 1 : 0;
        size += add;
        return add == 1;
    }

    private boolean insert(Node<T> node, T value) {
        boolean result = false;
        if (value.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new Node<T>(node, value);
                result = true;
            } else {
                result = insert(node.left, value);
            }
        } else if (value.compareTo(node.value) > 0) {
            if (node.right == null) {
                node.right = new Node<T>(node, value);
                result = true;
            } else {
                result = insert(node.right, value);
            }
        }
        if (result) {
            int left = node.left != null ? node.left.height : -1;
            int right = node.right != null ? node.right.height : -1;
            node.height = Math.max(left, right) + 1;
        }
        return result;
    }

    public T min() {
        if (root == null) {
            throw new NullPointerException();
        }
        return min(root).value;
    }

    protected Node<T> min(Node<T> node) {
        if (node.left != null) {
            return min(node.left);
        } else {
            return node;
        }
    }

    public T max() {
        if (root == null) {
            throw new NullPointerException();
        }
        return max(root).value;
    }

    protected Node<T> max(Node<T> node) {
        if (node.right != null) {
            return max(node.right);
        } else {
            return node;
        }
    }

    public T predecessor(T value) {
        if (value.equals(root.value)) {
            if (root.left != null) {
                return max(root.left).value;
            } else {
                return null;
            }
        } else {
            Node<T> node = find(root, value);
            if (node.left != null) {
                return max(node.left).value;
            } else {
                Node<T> parent = findParent(root, value);
                while (parent != null && value.equals(parent.left.value)) {
                    value = parent.value;
                    parent = findParent(root, value);
                }
                return parent == null ? null : parent.value;
            }
        }
    }

    public T successor(T value) {
        if (value.equals(root.value)) {
            if (root.right != null) {
                return min(root.right).value;
            } else {
                return null;
            }
        } else {
            Node<T> node = find(root, value);
            if (node.right != null) {
                return min(node.right).value;
            } else {
                Node<T> parent = findParent(root, value);
                while (parent != null && value.equals(parent.right.value)) {
                    value = parent.value;
                    parent = findParent(root, value);
                }
                return parent == null ? null : parent.value;
            }
        }
    }

    protected Node<T> findParent(Node<T> node, T value) {
        if (value.compareTo(node.value) < 0) {
            if (node.left == null) {
                return null;
            } else if (value.compareTo(node.left.value) == 0) {
                return node;
            } else {
                return findParent(node.left, value);
            }
        } else if (value.compareTo(node.value) > 0) {
            if (node.right == null) {
                return null;
            } else if (value.compareTo(node.right.value) == 0) {
                return node;
            } else {
                return findParent(node.right, value);
            }
        } else {
            /* It must be the value from the root parent */
            return null;
        }
    }

    public boolean exists(T value) {
        return find(root, value) != null;
    }

    protected Node<T> find(Node<T> node, T value) {
        if (node == null) {
            return null;
        } else if (value.compareTo(node.value) < 0) {
            return find(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            return find(node.right, value);
        } else {
            return node;
        }
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
        if (v != null)
            v.parent = u.parent;
    }
    public void delete(T value) {
        if (!exists(value) || (value.equals(root.value) && size == 1)) {
            return;
        }
        Node<T> z = find(root, value);
        if (z.left == null) {
            transplant(z, z.right);
        } else if (z.right == null) {
            transplant(z, z.left);
        } else {
            Node<T> y = min(z.right);
            if (z.value.compareTo(y.parent.value) != 0) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
        }
        size--;
    }

    protected Node<T> rotateRight(Node<T> node) {
        Node<T> pivot = node.left;
        node.left = pivot != null ? pivot.right : null;
        if (pivot.right != null) {
            pivot.right.parent = node;
        }
        pivot.parent = node.parent;
        Node<T> parent = node.parent;
        if (parent == null) {
            root = pivot;
        } else if (parent.checkLeftChild(node.value)) {
            parent.left = pivot;
        } else {
            parent.right = pivot;
        }
        pivot.right = node;
        node.parent = pivot;

        updateHeight(node);
        updateHeight(pivot);

        return pivot;
    }

    protected Node<T> rotateLeft(Node<T> node) {
        Node<T> pivot = node.right;
        node.right = pivot != null ? pivot.left : null;
        if (pivot.left != null) {
            pivot.left.parent = node;
        }
        pivot.parent = node.parent;
        Node<T> parent = node.parent;
        if (parent == null) {
            root = pivot;
        } else if (parent.checkLeftChild(node.value)) {
            parent.left = pivot;
        } else {
            parent.right = pivot;
        }
        pivot.left = node;
        node.parent = pivot;

        updateHeight(node);
        updateHeight(pivot);

        return pivot;
    }

    protected void updateHeight(Node<T> node) {
        if (node == null) return;
        int left = node.left != null ? node.left.height : -1;
        int right = node.right != null ? node.right.height : -1;
        node.height = Math.max(left, right) + 1;
    }

    public String inorderTraversal() {
        return inorderTraversal(root).toString();
    }

    public StringBuilder inorderTraversal(Node<T> node) {
        StringBuilder stringBuilder = new StringBuilder();
        if (node.left != null) {
            stringBuilder.append(inorderTraversal(node.left));
        }
        stringBuilder.append(node.value);
        stringBuilder.append(' ');
        if (node.right != null) {
            stringBuilder.append(inorderTraversal(node.right));
        }
        return stringBuilder;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
