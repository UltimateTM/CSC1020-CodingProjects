package week12;

public class ShellSort {

    public <T extends Comparable<T>> void sort(T[] table) {
        final double shellConstant = 2.2;
        int gap = table.length / 2;

        while (gap > 0) {
            for (int nextPos = gap; nextPos < table.length; ++nextPos) {
                int nextInsert = nextPos; // index
                T nextVal = table[nextInsert]; // value
                while (nextInsert > gap - 1 && nextVal.compareTo(table[nextInsert - gap]) < 0) {
                    table[nextInsert] = table[nextInsert - gap];
                    nextInsert -= gap;
                }
                table[nextInsert] = nextVal;
            }
            if (gap == 2) {
                gap = 1;
            } else {
                gap = (int) (gap / shellConstant);
            }
        }
    }
}
