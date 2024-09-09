package week1;

import java.util.ArrayList;
import java.util.Scanner;

public class Exceptions2 {
    public static void main(String[] args) {
        try {
            toUpper("44");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints out a string in uppercase
     * @param s the string to print
     * @return true if I printed
     * @throws YouGaveMeANumberException if a number is encountered
     */
    public static boolean toUpper(String s) throws YouGaveMeANumberException {
        for (int i = 0; i < s.length(); ++i) {
            if (Character.isDigit(s.charAt(i))) {
                throw new YouGaveMeANumberException("You Gave me a Number"); // created during lesson
            }
            System.out.print(Character.toUpperCase(s.charAt(i)));
        }
        return true;
    }
}
