package week7;

import java.util.Stack;

public class StackDriver {
    public static void main(String[] args) {
        final Integer[] nums = {1, 6, 4, 2, 8, 9, 3};
        Stack<Integer> stack = new Stack<>();
        GLStack<Integer> glStack = new GLStack<>();

        for (int i: nums) {
            stack.push(i);
            glStack.push(i);
        }
        System.out.println(stack.contains(2));
    }
}
