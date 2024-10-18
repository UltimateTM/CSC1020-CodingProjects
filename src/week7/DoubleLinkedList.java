/*
 * Course: CSC-1020
 * Double Linked List
 */
package week7;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Double LinkedList implementation
 *
 * @param <E> the type of element stored in the list
 */
public class DoubleLinkedList<E> implements List<E> {
    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        private Node(E element) {
            this(element, null, null);
        }

        private Node(E element, Node<E> next) {
            this(element, null, next);
        }

        private Node(E element, Node<E> prev, Node<E> next) {
            this.data = element;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private class SJIterator implements Iterator<E> {
        private Node<E> next;
        private E lastReturned;

        private SJIterator() {
            next = head;
            lastReturned = null;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (next == null) {
                throw new NoSuchElementException();
            }
            lastReturned = next.data;
            next = next.next;
            return lastReturned;
        }

        @Override
        public void remove() throws IllegalStateException {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            DoubleLinkedList.this.remove(lastReturned);
            lastReturned = null;
        }
    }

    private class SJListIterator implements ListIterator<E> {
        private Node<E> next;
        private Node<E> lastReturned;
        private int index;

        private SJListIterator() {
            next = head;
            lastReturned = null;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (next == null) {
                throw new NoSuchElementException();
            }
            ++index;
            lastReturned = next;
            next = next.next;
            return lastReturned.data;
        }

        @Override
        public boolean hasPrevious() {
            return next.prev != null;
        }

        @Override
        public E previous() throws NoSuchElementException {
            if (next.prev == null) {
                throw new NoSuchElementException();
            }
            --index;
            lastReturned = next.prev;
            next = next.prev;
            return lastReturned.data;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() throws IllegalStateException {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            DoubleLinkedList.this.remove(lastReturned.data);
            lastReturned = null;
            --index;
        }

        @Override
        public void set(E e) throws IllegalStateException {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            lastReturned.data = e;
        }

        @Override
        public void add(E e) {
            DoubleLinkedList.this.add(index, e);
            ++index;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * No-param constructor
     */
    public DoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Constructs a list containing the elements of the specified collection, in the order they are
     * returned by the collection's iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public DoubleLinkedList(Collection<? extends E> c) throws NullPointerException {
        this();
        Objects.requireNonNull(c);
        addAll(c);
    }

    @Override
    public E getFirst() {
        return head.data;
    }

    @Override
    public E getLast() {
        return tail.data;
    }

    @Override
    public E removeFirst() {
        return this.remove(0);
    }

    @Override
    public E removeLast() {
        return this.remove(size - 1);
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
    public boolean contains(Object o) {
        Node<E> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new SJIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        Node<E> current = head;
        for (int i = 0; i < size; ++i) {
            result[i] = current.data;
            current = current.next;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) throws NullPointerException {
        Object[] result = Objects.requireNonNull(a);
        if (a.length < size) {
            result = new Object[size];
        }
        Node<E> current = head;
        for (int i = 0; i < size; ++i) {
            result[i] = current.data;
        }
        if (result.length > size) {
            result[size] = null;
        }
        return (T[]) result;
    }

    @Override
    public boolean add(E e) {
        if(head == null) {
            head = new Node<>(e);
            tail = head;
        } else {
            tail.next = new Node<>(e, tail, null);
            tail = tail.next;
        }
        ++size;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(size == 0) {
            return false;
        }
        boolean removed = false;
        if(head.data.equals(o)) {
            head = head.next;
            removed = true;
        } else {
            Node<E> current = head;
            while(current.next != null && !removed) {
                if(current.next.data.equals(o)) {
                    if(current.next == tail) {
                        tail = current;
                        tail.next = null;
                    } else {
                        current.next = current.next.next;
                        current.next.prev = current; // very confusing
                    }
                    removed = true;
                }
                current = current.next;
            }
        }
        if(removed) {
            --size;
        }
        return removed;
    }

    @Override
    public boolean containsAll(Collection<?> c) throws NullPointerException {
        Objects.requireNonNull(c);
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) throws NullPointerException {
        Objects.requireNonNull(c);
        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c)
            throws NullPointerException, IndexOutOfBoundsException {
        Objects.requireNonNull(c);
        if (index != size) {
            validateIndex(index);
        } else {
            addAll(c);
            return true;
        }
        int i = index;
        for (E e : c) {
            add(i++, e);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean changed = false;
        for (Object o : c) {
            if (remove(o)) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) throws NullPointerException {
        Objects.requireNonNull(c);
        boolean changed = false;
        for (E e : this) {
            if (!c.contains(e)) {
                remove(e);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        Node<E> current = getNode(index);
        return current.data;
    }

    private Node<E> getNode(int index) throws IndexOutOfBoundsException {
        validateIndex(index);
        Node<E> current = head;
        for (int i = 0; i < index; ++i) {
            current = current.next;
        }
        return current;
    }

    private void validateIndex(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        Node<E> current = getNode(index);
        E old = current.data;
        current.data = element;
        return old;
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        validateIndex(index);
        if(index == 0) {
            head = new Node<>(element, null, head);
        } else {
            Node<E> prev = getNode(index - 1);
            prev.next = new Node<>(element, prev, prev.next);
            if(prev == tail) {
                tail = prev.next;
            } else {
                prev.next.next.prev = prev.next;
            }
        }
        ++size;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        validateIndex(index);
        E result;
        if(index == 0) {
            result = head.data;
            head = head.next;
            head.prev = null;
        } else {
            Node<E> prev = getNode(index - 1);
            result = prev.next.data;
            prev.next = prev.next.next;
            if(tail == prev.next) {
                tail = prev;
            } else {
                prev.next.prev = prev;
            }
        }
        --size;
        return result;
    }

    @Override
    public int indexOf(Object o) {
        Node<E> current = head;
        for (int i = 0; i < size; ++i) {
            if (head.data.equals(o)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> current = tail;
        for (int i = size - 1; i >= 0; --i) {
            if (current.data.equals(o)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new SJListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) throws IndexOutOfBoundsException {
        validateIndex(index);
        ListIterator<E> result = new SJListIterator();
        for(int i = 0; i < index; ++i) {
            result.next();
        }
        return result;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }


    public boolean offer(E e) {

    }

    public E peek() {

    }

    public E poll() {

    }

}
