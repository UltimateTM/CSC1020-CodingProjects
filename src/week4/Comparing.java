package week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Comparing {
    public static void main(String[] args) {
//        List list = new ArrayList(); // Object list, stores ANYTHING
//        list.add(new Scanner(System.in));
//        list.add("Hello");
//        list.add(new Random());
//        list.add(null);
//
//        // list.get(0).nextLine();
//        // Will not work because it is a generic object
//        ((Scanner) (list.get(0))).nextLine();

        // Generics
        // Many different types under one type
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            list.add((int) (Math.random() * 10) + 1);
        }
        System.out.println(list);

        // if you see a "<", a generic is involved
        list.sort(Comparator.naturalOrder());
        System.out.println(list);
        list.sort(Comparator.reverseOrder());
        System.out.println(list);
        list.sort((a, b) -> a % 2 == 0 && b % 2 == 1 ? 1 : a - b);
        // if a is even and b is odd, return 1. If not, return a - b
        System.out.println(list);

        String[] array = {"uh", "cat", "dog", "duck", "car", "giraffe", "supra", "chair", "incandescence"};
        List<String> words = Arrays.asList(array);
        words.sort((a, b) -> a.charAt(a.length() - 1) - b.charAt(b.length() - 1));
        System.out.println(words);
    }
}
