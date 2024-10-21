package week8;

import java.util.Arrays;

public class BinarySearch {
    // list needs to be sorted in order for a binary search to take place
    // binary search is log 2^n

    public static void main(String[] args) {
        String[] words = {"this", "needs", "to", "be", "sorted", "before", "we",
                "can", "search", "through", "it"};

        Arrays.sort(words);
        Arrays.stream(words).forEach(s -> System.out.print(s + " "));
        binarySearch2(words, "to", 0, words.length - 1);
    }

    private static int binarySearch(String[] arr, String target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int middle = left + right / 2; // *IMPORTANT* determines middle of array no matter size of the array
            if (arr[middle].compareTo(target) == 0) { // equal
                return middle;
            } else if (arr[middle].compareTo(target) < 0) { // go right
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    private static int binarySearch2(String[] arr, String target, int left, int right) {
        // when writing a recursive method, method takes as parameters variables that WILL CHANGE
        int middle = left + right / 2;


        // base case
        if (left > right) { // not there
            return -1;
        }
        if (arr[middle].compareTo(target) == 0) { // equal
            return middle;
            // recursive cases
        } else if (arr[middle].compareTo(target) < 0) { // go right
            // left = middle + 1;
            binarySearch2(arr, target, middle + 1, right);
        } else {
            // right = middle - 1;
            binarySearch2(arr, target, left, middle - 1);
        }

    }
}
