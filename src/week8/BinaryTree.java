package week8;

/**
 * Simple binary tree implementation
 * @param <E>
 */
public class BinaryTree<E> {
    private static class Node<E> {
        private E data;
        private Node<E> left;
        private Node<E> right;

        public Node (E data) {
            this.data = data;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node<E> root;

    public BinaryTree() {
        this(null);
    }

    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node(data);

        if(leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }

        if(rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) { //must check if root is null as well
            return new BinaryTree<>(root.left);
        } else {
            return null;
        }
    }

    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) { //must check if root is null as well
            return new BinaryTree<>(root.right);
        } else {
            return null;
        }
    }

    public E getData() {
        if (root != null) {
            return root.data;
        } else {
            return null;
        }

    }


}
