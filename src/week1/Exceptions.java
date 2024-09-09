package week1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exceptions {
    public static void main(String[] args) {
        int age = -1;
        Scanner in = new Scanner(System.in);

        while (age < 4) {
            try {
                System.out.println("Enter your age: ");
                age = in.nextInt(); //nextInt() does not clear newline
                // nextLine clears scanner buffer, preventing an infinite loop

                if (age > 3) {
                    System.out.println("You are" + age + " years old.");
                } else {
                    System.out.println("Don't lie to me :P");
                }
            } catch (InputMismatchException e) {
                System.out.println("Hey dummy enter a number");
                in.nextLine();
            }
        }
        in.close(); // closes scanner
        System.out.println("done");
    }
}
