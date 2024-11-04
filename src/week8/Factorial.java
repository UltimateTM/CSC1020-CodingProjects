package week8;

public class Factorial {
    public static void main(String[] args) {
        int total = 1;
        for (int i = 1; i <= 5; ++i) {
            total *= i;
        }
        System.out.println(total);
    }

    private static int factorial(int i) {
        // base case
        if (i < 2) {
            return 1;
        }

        return i + factorial(i - 1);
    }
}
