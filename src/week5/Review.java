package week5;

import java.util.Arrays;
import java.util.List;

public class Review {
    public static void main(String[] args) {
        // functional programming
        String[] words = {"number", "dog", "hippopotamus", "bird", "table", "camera", "word", "cat", "glass"};
        List<String> list = Arrays.asList(words);

        // make a space in between each letter
        StringStuff stuff = s -> {
            String result = "";
            for (int i = 0; i < s.length(); ++i) {
                result += s.charAt(i) + " ";
            }
            return result.trim();
        };

        for(String a : list) {
            System.out.println(stuff.apply(a));
            System.out.println(stringyThingy(a, t -> t.toUpperCase()));
        }
        
        // list to stream //map "something goes in something comes out"
        List<String> list2 = list.stream().map(s -> s.repeat(2)).toList();
    }

    private static String stringyThingy(String s, StringStuff stuff) {
        return stuff.apply(s);
    }

    private interface StringStuff {
        String apply(String s);
    }
}
