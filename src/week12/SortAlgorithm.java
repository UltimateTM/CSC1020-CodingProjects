package week12;

public interface SortAlgorithm {
    <T extends Comparable<T>> void sort(T[] table);
}
