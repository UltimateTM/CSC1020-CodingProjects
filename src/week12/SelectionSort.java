package week12;

public class SelectionSort implements SortAlgorithm{

    @Override
    public <T extends Comparable<T>> void sort(T[] table) {

        // outer loop is o(n)
        for (int i = 0; i < table.length - 1; i++) {
            int min = i;

            // inner loop is o(n)/2
            for (int j = i + 1; j < table.length; j++) {
                if(table[j].compareTo(table[min]) < 0) {
                    min = j;
                }
            }
            T temp = table[i];
            table[i] = table[min];
            table[min] = temp;
        }
        // essentially o(n^2)
    }


}
