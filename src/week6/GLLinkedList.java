package week6;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class GLLinkedList<E> implements List<E> {
    private static class Node<E> { // inner class
        private E data;
        private Node<E> nextNode;

        private Node(E element) {
            this(element, null); // calls on lower Node constructor
            // when creating multiple constructors,
            // call upon the constructor with the most parameters
        } // Constructor will be used ONLY in GLLinkedList class

        private Node(E element, Node<E> nextNode) {
            this.data = element;
            this.nextNode = nextNode;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // do not use <E>
    // Iterator uses a "different" iterator, not static
    // Iterator interface needs generic
    private class GLIterator implements Iterator<E> {
        private Node<E> next;
        private E lastReturned;

        private GLIterator() {
            next = head;
            lastReturned = null;
        }

        @Override
        public boolean hasNext() {
            // if iterator is not pointing to null, return true
            return next != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next.data;
            next = next.nextNode;
            return lastReturned;
        }

        // can only remove if it sees something
        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }

            // calls upon the instance of "this" LinkedList class
            GLLinkedList.this.remove(lastReturned);
            lastReturned = null;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    private Node<E> head;
    private int size;

    public GLLinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) { // o(n)
        // when iterating through a linked list, make a copy
        Node<E> current = head;

        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.nextNode; // because inner class is inside GLLinkedList, it is within scope
        }

        return false;

    }

    // Reason why iterator was not working in class, need to implement iterator method
    @Override
    public Iterator<E> iterator() {
        return new GLIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        // is empty?
        if (head == null) {
            // if yes, make the head
            head = new Node<>(e);
        } else {
            // if no, find the end of the list
            Node<E> current = head;
            while (current.nextNode != null) {
                current = current.nextNode;
            }
            current.nextNode = new Node<>(e);
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (size == 0) {
            return false;
        }

        boolean removed = false;
        // Check the head
        if (head.data.equals(o)) {
            head = head.nextNode;
            removed = true;
            // remove head
        } else {
            // check the next node
            Node<E> current = head;
            while (current.nextNode != null && !removed) { // will stop once removed has occurred at least once
                if (current.nextNode.data.equals(o)) {
                    current.nextNode = current.nextNode.nextNode;
                    removed = true;
                }
                current = current.nextNode; // go to next node
            }
        }

        if (removed) {
            --size;
        }
        // Check the rest
        return removed;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head = null; // removes reference
        size = 0;
    }

    @Override
    public E get(int index) { //o(n)
        Node<E> current = getNode(index);

        return current.data;
    }

    private void validateIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException { //o(n)
        Node<E> current = getNode(index);
        // swap
        E old = current.data;
        current.data = element;
        return old;
    }

    private Node<E> getNode(int index) {
        validateIndex(index);
        Node<E> current = head;
        for (int i = 0; i < index; ++i) {
            current = current.nextNode;
        }
        return current;
    }


    @Override
    public void add(int index, E element) {
        validateIndex(index);

        if (index == 0) {
            head = new Node<>(element, head);
            // = new Node<>(element, head) occurs first
        } else {
            Node<E> prev = getNode(index - 1);
            prev.nextNode = new Node<>(element, prev.nextNode);
        }

        // need to get to node previous of index
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }
}
