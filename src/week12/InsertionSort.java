package week12;

public class InsertionSort implements SortAlgorithm{
    @Override
    public <T extends Comparable<T>> void sort(T[] table) {
        // o(n)
        for (int next = 1; next < table.length; next++) {
            T current = table[next];
            int location = next;
            // o(n)
            while (location > 0 && current.compareTo(table[location - 1]) < 0) {
                table[location] = table[location - 1];
                --location;
            }
            table[location] = current;
        }
        // also o(n^2)
    }
}
