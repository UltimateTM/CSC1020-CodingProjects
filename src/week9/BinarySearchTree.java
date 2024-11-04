package week9;

import week8.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<E extends Comparable<E>>
        extends BinaryTree<E>
        implements SearchTree<E> {

    private boolean addReturn;
    private E deleteReturn;

    @Override
    public boolean add(E item) {
        root = add(item, root);
        return addReturn;
    }

    private Node<E> add(E item, Node<E> localRoot) {
        // if localroot is null, this is where it goes - base case
        if (localRoot == null) {
            addReturn = true;
            return new Node<>(item);
        }
        // otherwise see where it is - recursive case
        int compResult = item.compareTo(localRoot.data);

        if(compResult == 0) {
            addReturn = false;
        } else if (compResult < 0) {
            // go left
            localRoot.left = add(item, localRoot.left);
        } else {
            // else go right
            localRoot.right = add(item, localRoot.right);
        }
        // else go right
        return localRoot;

    }
    // If a binary tree is balanced, it a o(log (n) ) operation
    // if a binary tree is unbalanced, it is a o(n) operation


    @Override
    public boolean contains(E item) {
        // if not null, return true, else false
        return find(item) != null ? true : false;

        // equivalent to find(item) != null
    }

    private boolean contains(E target, Node<E> localRoot) {
        if (localRoot == null) {
            return false;
        }

        int compResult = target.compareTo(localRoot.data);

        if(compResult == 0) {
            return true;
        } else if (compResult < 0) {
            // go left
            return contains(target, localRoot.left);
        } else {
            // else go right
            return contains(target, localRoot.right);
        }
    }

    @Override
    public E find(E target) {
        return find(target, root);
    }

    private E find(E target, Node<E> localRoot) {
        if (localRoot == null) {
            return null;
        }

        int compResult = target.compareTo(localRoot.data);

        if(compResult == 0) {
            return localRoot.data;
        } else if (compResult < 0) {
            // go left
            return find(target, localRoot.left);
        } else {
            // else go right
            return find(target, localRoot.right);
        }

        // if exists, is it a target
    }

    @Override
    public E delete(E target) {
        return null;
    }



    private Node<E> delete(E target, Node<E> localRoot) {
        if (localRoot == null) {
            return null;
        }

        int compResult = target.compareTo(localRoot.data);

        if (compResult < 0) {
            // go left
            localRoot.left = delete(target, localRoot.left);
            return localRoot;
        } else if (compResult > 0){
            // else go right
            localRoot.right = delete(target, localRoot.right);
            return localRoot;
        } else {
            // found thing to delete
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {
                return localRoot.right;
            } else if(localRoot.right == null) {
                return localRoot.left;
            } else {
                // there are two children
                // if the left child has no right child
                // left child is the biggest in the subtree
                if(localRoot.left.right == null) {
                    localRoot.data = localRoot.left.data;
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    // find biggest value in the left subtree and search from here
                    localRoot.data = findLargestChild(localRoot.left);
                }

            }
        }
        return localRoot;
    }

    private E findLargestChild(Node<E> Node) {
        return null;
    }

    @Override
    public boolean remove(E target) {
        return false;
    }

    public boolean remove(E target, Node<E> localRoot) {
        return false;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public List<E> toList() {

        List<E> result = new ArrayList<>();
        inOrderTraversal((data, depth) -> result.add(data));
        return result;

    }
}
