package week8;

import java.util.function.BiConsumer;

/**
 * Simple binary tree implementation
 * @param <E>
 */
public class BinaryTree<E> {
    protected static class Node<E> { // only time to use protected is to allow subclasses to be used in other classes
        public E data;
        public Node<E> left;
        public Node<E> right;

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

    protected Node<E> root;

    public BinaryTree() {
        root = null;
    }

    /*public BinaryTree() {
        this((Node<E>) null);
    }*/

    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    public BinaryTree(E data) {
        this.root = new Node<>(data);
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


    // Pre: Visit root, L, R
    // In: L, Visit Root, R
    // Post: L, R, Visit Root

    //isLeaf
    public boolean isLeaf() {
        return root.left == null & root.right == null;
    }

    public String toString() {
        // traverse the tree using a stringbuilder
        StringBuilder sb = new StringBuilder();
        // e is node
        // d is depth
        preOrderTraversal((e, d) ->{
            for (int i = 0; i < d; i++) {
                sb.append("  ");
            }
           sb.append(e.toString()).append("\n");
        });
        return sb.toString();
    }

    // Traversals
    public void preOrderTraversal(BiConsumer<E, Integer> consumer) {
        preOrderTraversal(root, consumer, 1);
    }

    private void preOrderTraversal(Node<E> node, BiConsumer<E, Integer> consumer, int depth) {
        // base case
        if (node == null) {
            // shouldnt technically do anything
            //consumer.accept(null, depth); // basically does nothing
        } else {
            // visit root
            consumer.accept(node.data, depth);
            // go left
            preOrderTraversal(node.left, consumer, depth + 1);
            // go right
            preOrderTraversal(node.right, consumer, depth + 1);
        }
    }

    public void inOrderTraversal(BiConsumer<E, Integer> consumer) {
        // base case
        inOrderTraversal(root, consumer, 1);
        // go left
        // visit root
        // go right
    }

    private void inOrderTraversal(Node<E> node, BiConsumer<E, Integer> consumer, int depth) {
        // base case
        if (node == null) {
            // shouldnt technically do anything
            //consumer.accept(null, depth); // basically does nothing
        } else {
            // go left
            inOrderTraversal(node.left, consumer, depth + 1);
            // visit root
            consumer.accept(node.data, depth);
            // go right
            inOrderTraversal(node.right, consumer, depth + 1);
        }
        // go left
        // visit root
        // go right
    }

    public void postOrderTraversal(BiConsumer<E, Integer> consumer) {
        // base case
        postOrderTraversal(root, consumer, 1);
        // go left
        // go right
        // visit root
    }

    private void postOrderTraversal(Node<E> node, BiConsumer<E, Integer> consumer, int depth) {
        // base case
        if (node == null) {
            // shouldnt technically do anything
            //consumer.accept(null, depth); // basically does nothing
        } else {
            // visit root
            consumer.accept(node.data, depth);
            // go left
            preOrderTraversal(node.left, consumer, depth + 1);
            // go right
            preOrderTraversal(node.right, consumer, depth + 1);
        }
        // go left
        // go right
        // visit root
    }


}
