package week8andweek9;

public class Fibonacci {
    public static void main(String[] args) {
        int total = 1;
        int previous = 1;
        for (int i = 1; i <= 5; i++) {
            int newPrev = total;
            total += previous;
            previous = newPrev;
        }
        System.out.println(total);

        System.out.println(fibonacci(5, 1, 1));
    }

    // 3 variables change (total, previous, and newPrev)
    private static int fibonacci(int times, int prev, int total) {
        if (times == 0) {
            return total;
        }
        return fibonacci(--times, total, prev + total);
    } // 1 1
}
