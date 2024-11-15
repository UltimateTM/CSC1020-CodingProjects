package week11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


// Do not make a set inside a map
public class GLSet <E> implements Set<E> {

    private ArrayList<E> data;

    public GLSet() {
        data = new ArrayList<>();
    }

    public GLSet(Collection<E> data) {
        this(); // call on above constructor
        for (E e: data) {
            if(!this.data.contains(e)) {
                this.data.add(e); // ensures no duplicates are added to arrayList
            }
        }
    }
    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.data.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return this.data.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.data.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.data.toArray(a);
    }

    @Override
    public boolean add(E e) {
        // some collections return false if an item already exists
        // ensures no duplicates are added

        if (this.data.contains(e)) {
            return false;
        }
        return this.data.add(e);
    }

    @Override
    public boolean remove(Object o) {
        if (this.data.contains(o)) {
            return this.data.remove(o);
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.data.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return this.data.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.data.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.data.removeAll(c);
    }

    @Override
    public void clear() {
        this.data.clear();
    }
}
