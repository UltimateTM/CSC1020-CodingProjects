package week6;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LinkedListTest {
    List<Integer> list;
    final Integer[] nums = {6, 8, 9, 2, 1, 0, 3, 9, 0};
    @BeforeEach
    void setup() {
        list = new GLLinkedList<>();
        // setups an object for testing
    }

    @Test
    @DisplayName("Testing LinkedList size")
    void size() {
        final int numTests = 10_000;

        // is it empty?

        Assertions.assertEquals(0, list.size());
        // expect 0, running method
        //list.add(2);
        Assertions.assertTrue(list.add(2));

        Assertions.assertEquals(1, list.size());

        list = new GLLinkedList<>();
        for (int i = 0; i < numTests; i++) {
            Assertions.assertEquals(i, list.size());
            list.add(i);
        }
    }

    @Test
    @DisplayName("Testing contains")
    void contains() {

        // boundary  conditions
        // 1. list is empty - false
        // 2. list is not empty, item is not there - false
        // 3. list is not empty, item is there - true
        // 4. list is not empty, multiples copies of item - true
        // 5. List has one item - not our item - false
        // 6. list has one item - is our item - true
        // 7. list has many items, ours is the last one
        Assertions.assertFalse(list.contains(1));
        list.add(0);
        Assertions.assertFalse(list.contains(1));
        Assertions.assertTrue(list.contains(0));
        list.add(1);
        list.add(2);
        Assertions.assertFalse(list.contains(3));
        Assertions.assertTrue(list.contains(1));
        Assertions.assertTrue(list.contains(2));

        list = new GLLinkedList<>();
        //list = Arrays.asList(nums);
        list.addAll(Arrays.asList(nums));
    }
}
