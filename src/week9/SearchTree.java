package week9;

import java.util.List;

// E Must be Comparable to Another E
public interface SearchTree<E extends Comparable<E>> {
    boolean add(E item);
    boolean contains(E item);
    E find(E target);
    E delete(E target);
    boolean remove(E target);
    void clear();
    List<E> toList();



}
