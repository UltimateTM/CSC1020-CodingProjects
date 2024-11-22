package week12;

public class MergeSort {

    public <T extends Comparable<T>> void sort(T[] table) {
        // if the array is larger than size one, split it
        // recursive

        if (table.length > 1) {
            // split it
            int half = table.length / 2;
            T[] left = (T[]) new Comparable[half];
            T[] right = (T[]) new Comparable[table.length - half];
            System.arraycopy(table, 0, left, 0, half);
            System.arraycopy(table, half, right, 0, table.length - half);
            sort(left);
            sort(right);
            merge(table, left, right);
        }
        // base case - size 1
        // implied base case

    }

    private static <T extends Comparable<T>> void merge(T[] output, T[] left, T[] right) {
        int l = 0;
        int r = 0;
        int o = 0;

        // while there are still values in both sub-arrays
        while(l < left.length && r < right.length) {
            if (left[l].compareTo(right[r]) < 0) {
                output[o++] = left[l++];
            } else {
                output[o++] = right[r++];
            }
        }

        while (l < left.length) {
            output[o++] = left[l++];
        }

        while (r < right.length) {
            output[o++] = right[r++];
        }
    }
}
