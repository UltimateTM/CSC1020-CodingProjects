package week4;

import java.util.ArrayList;
import java.util.List;

public class BigO {
    // BigO aka Time Complexity

    public static void main(String[] args) {
        // make a random list of 100 integers in range 1-100
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 100; ++i) {
            numbers.add(((int) (Math.random() * 100)) + 1);
        }

        // numbers creation is constant (1)
        // i is constant (1)
        // i < 100 is n
        // ++i is n
        // numbers.add is constant (1)
        // math.random is consant (1)
        // 2n + 4
        // drop coefficients and constants
        // entire for loop can be counted as n

        // Get total of the list
        System.out.println(add(numbers)); // 0(n)

        // find the max of the list
        System.out.println(max(numbers)); // 0(n)

        // add to the list
        numbers.add(98);

        //
        powers(numbers);

        // Fake quadratic
        fakeQuadratic(numbers);


    }

    private static int add(List<Integer> list) {
        /*int total = 0;

        for (Integer i : list) {
            total += i;
        }
        return total; */
        return list.stream().mapToInt(a -> a).sum();
    }

    private static int max(List<Integer> list) {
        int max = list.get(0); // constant

        for (int i = 1; i < list.size(); i++) {
            if (max < list.get(i)) {
                max = list.get(i);
            }
        }
        // n is entirely reliant on size of the list
        return max;
    }

    private static void powers(List<Integer> list) {
        for (Integer n : list) {
            for (int i = 0; i < 10; ++i) {
                System.out.println(Math.pow(n, 1));
            }
        }
    }

    private static void fakeQuadratic(List<Integer> list) {
        for (int y = 0; y < list.size(); ++y) {
//            for (int x = 0; x < list.size(); ++x) {
//                System.out.print(list.get(x) * list.get(y) + " ");
//            } // n

            add(list); // no more for loop, need to check method for time complexity
            // add method is dependent on size of the list, so n

            System.out.println();
        } // n

        // complexity is n^2
    }


}
