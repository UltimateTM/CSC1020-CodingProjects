package week7;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Spliterator;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CircularQueue<E> implements Queue<E> {
    private static final int CAPACITY = 10;
    private int front;
    private int back;
    private int size; // similar to other data structures
    private E[] data;

    public CircularQueue() {
        this.front = -1; // front must be set to -1 initially, an invalid index
        this.back = 0;
        this.size = 0;
        this.data = (E[]) new Object[CAPACITY];
    }
    @Override
    public boolean add(E e) {
        return false;
    }

    // Adds into array if space is available
    @Override
    public boolean offer(E e) {
        // Checking to see if array is full
        if (size == data.length) {
            return false;
        }

        // are we empty
        if (front == -1) {
            front = 0;
        }

        data[back] = e; // set index at back to value passed in
        back++; // next time value is passed into back, it is passed into correct index
        back %= data.length; // forces back to be between 0 and length
        return true;
    }

    @Override
    public E remove() {
        return null;
    }

    // Retrieves and remove head of queue
    @Override
    public E poll() {
        if (size == data.length) {
            return null;
        }
        E result = data[front];
        front++;
        front %= data.length;
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    // Retrieve head but does not remove
    @Override
    public E peek() {
        if (front == back) {
            return null;
        }
        return data[front];

    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
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
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return Queue.super.toArray(generator);
    }

    @Override
    public boolean remove(Object o) {
        return false;
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
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return Queue.super.removeIf(filter);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Spliterator<E> spliterator() {
        return Queue.super.spliterator();
    }

    @Override
    public Stream<E> stream() {
        return Queue.super.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return Queue.super.parallelStream();
    }
}
