package week4;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

// make class use generic
// "E" is used as classic convention, placeholder
public class GLArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 10;
    // think of an arraylist as a wrapper class around an array
    // Backing data structure is the array

    // All data structures must have a way to store data (this is the backing data structure)
    private E[] data;
    // tracks how many items are in the array
    private int size;
    // dynamically size array
    private int capacity;

    public GLArrayList() {
        // "no type E"
        //data = new E[INITIAL_CAPACITY];
        data = (E[]) (new Object[INITIAL_CAPACITY]);
        size = 0;
        capacity = INITIAL_CAPACITY;

    }

    // RULES
    // 1. NO GAPS
    // 2. Shift elements to make room or fill gaps
    // 3. Always add at the end of the list, unless an index is given


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        // Will come back
        for(int i = 0; i < size; ++i) {
            if (data[i].equals(o)) {
                return true;
            }
        }
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
    public boolean add(E e) {
        if (size == capacity) {
            reallocate();
        }
        data[size] = e;
        ++size; // unary operator
        return true;
    }

    @Override
    public boolean remove(Object o) {
        // Find out if object is in the list
        // Loop through the array to find the value
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) {
                // found object

                // getting rid of the value at i
                // do not have to worry going out of bounds
                for (int j = i + 1; j < size; ++j) {
                    data[j-1] = data[j];
                }
                return true;
            }
        }
        // if not, return false
        // if it is in the list, remove it, return true
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
        size = 0;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        validateIndex(index);
        return data[index];
    }

    private void validateIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is invalid for list of size " + size);
        }
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        validateIndex(index);
        E tempVariable = data[index];
        data[index] = element;
        return tempVariable;
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        validateIndex(index);
        if (size == capacity) {
            reallocate();
        }
        // make room
        for (int i = size - 1; i >= index; --i) {
            data[i + 1] = data[i];
        }
        //insert
        data[index] = element;
        ++size;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException{
        validateIndex(index);
        //shift stuff over
        E tempVariable = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        // return the value to be removed
        --size;
        return tempVariable;
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

    private void reallocate() {
        capacity *= 2;
        E[] newData = (E[]) (new Object[capacity]);

//        for (int i = 0; i < data.length; i++) {
//            newData[i] = data[i];
//        }
        System.arraycopy(data, 0, newData, 0, data.length);

        data = newData;
    }


}
