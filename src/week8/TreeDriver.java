package week8;

public class TreeDriver {
    public static void main(String[] args) {
        BinaryTree<Integer> lowerLeft = new BinaryTree<>(1);
        BinaryTree<Integer> lowerRight = new BinaryTree<>(8);
        BinaryTree<Integer> left = new BinaryTree<>(7, lowerLeft, lowerRight);
        //7 (root), 1, 8

        BinaryTree<Integer> right = new BinaryTree<>(2);
        BinaryTree<Integer> root = new BinaryTree<>(5, left, right);
        // 5 (root), 7, 1, 8 (right), 2 (right)

        System.out.println(root);
    }
}
