
package week7;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;



/**
 * Tests for double linked list
 */
public class DLLTest {

    private DoubleLinkedList<String> testList = new DoubleLinkedList<>();
    private String tail;

    /**
     * tests offer method
     */
    @Test
    @DisplayName("Testing offer() method")
    public void offerTest() {
        String[] comparisonArray = {"Test2", "Test1", "String"};
        String tail1 = comparisonArray[2];
        testList.offer("Test2");
        testList.offer("Test1");
        testList.offer("String");
        tail = testList.getLast();
        Assertions.assertEquals(tail1, tail);

        testList = new DoubleLinkedList<>();
        Assertions.assertThrows(NullPointerException.class, ()
                -> testList.offer(null));
    }

    /**
     * test peek method
     */
    @Test
    @DisplayName("Testing offer() method")
    public void peekTest() {
        testList = new DoubleLinkedList<>();
        Assertions.assertThrows(NullPointerException.class, ()
                -> testList.offer(null));

        testList.offer("Test2");
        testList.offer("Test1");
        testList.offer("String");
        String temp = testList.peek();
        Assertions.assertEquals("Test2", temp);
    }

    /**
     * tests pollTest method
     */
    @Test
    @DisplayName("Testing offer() method")
    public void pollTest() {
        testList = new DoubleLinkedList<>();
        Assertions.assertThrows(NullPointerException.class, ()
                -> testList.offer(null));

        testList.offer("Test2");
        testList.offer("Test1");
        testList.offer("String");
        String temp = testList.poll();
        Assertions.assertEquals("Test2", temp);
        Assertions.assertEquals("Test1", testList.peek());
    }

}
