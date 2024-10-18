package week7;

public interface PureStack<E> {
    boolean empty();

    E peek(); // add to stack

    E pop(); // remove from stack

    E push(E item); // add to stack, return item added
}
