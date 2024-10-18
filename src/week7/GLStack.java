package week7;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

public class GLStack<E> implements PureStack<E> {
    private final List<E> data = new LinkedList<>();


    @Override
    public boolean empty() {
        return data.isEmpty();
    }

    @Override
    public E peek() throws EmptyStackException{
        if(data.isEmpty()) {
            throw new EmptyStackException();
        }
        return data.getFirst();
    }

    @Override
    public E pop() throws EmptyStackException{
        if(data.isEmpty()) {
            throw new EmptyStackException();
        }
        return data.removeFirst();
    }

    @Override
    public E push(E item) {
        data.addFirst(item);
        return item;
    }
}
