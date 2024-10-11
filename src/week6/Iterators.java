package week6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Iterators {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> list2 = new LinkedList<>();

        Collection<String> collection = new ArrayList<>();

        String[] words = {"word", "poop", "economics", "iterator", "data", "football", "cat", "hola", "nurse"};
        list.addAll(Arrays.asList(words));
        list2.addAll(Arrays.asList(words));
        collection.addAll(Arrays.asList(words));


        // All three below do the same function
        for (int i = 0; i < list.size(); ++i) {
            System.out.println(list.get(i));
        }

        // Linked
        Iterator<String> it = list2.iterator(); // get iterator

        // using an iterator
        while (it.hasNext()) {
            System.out.println(it.next()); // essentially like a for loop
        }

        for (String s : collection) {
            System.out.println(s);
        }

        // end of 3 "loops"

        List<String> list3 = new GLLinkedList<>();
        list3.addAll(Arrays.asList(words));
        Iterator<String> it2 = list3.iterator();

        for(String s : list3) {
            System.out.println(s);
            // SHOULD SAY ITERATOR DOES NOT EXIST
            // everytime you make a collection you need to make an iterator
        }
    }

}
