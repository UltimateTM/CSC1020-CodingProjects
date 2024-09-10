package week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PathStuff {
    public static void main(String[] args) {
        // absolute path (i.e C:\ProgramFiles\Java\HW.java
        // relative path (HW.java ^^)


        // Path
        Path p = Paths.get("data", "file.txt"); // use this
        // do not need to do data/file.txt, "Paths" automatically does it

        // File
        File f = new File("data/file.txt"); // hard-coded path, do NOT do
        f = p.toFile(); // what you should do
        System.out.println(f.length());
        // "As long as you have one of them, you can get to both of them"
        Path p2 = f.toPath();
        System.out.println(p2);

        // Stream (think like a water valve)
        // Need a stream to access files of any type
        // Input stream reads info into memory
        // Output goes from memory to file itself
        // text input

        /*
        Scanner in = new Scanner(System.in); // user input, example of a stream
        Scanner read = new Scanner(System.in);

        try {
            read = new Scanner(f); // dangerous code
            System.out.println(read.next());
            read.close(); // need to close stream after end
        } catch(FileNotFoundException e) {
            System.err.println("cannot find file"); // error message (its just red)
        } finally { // runs no matter what, but runs at the end of a try catch block
            read.close(); // IMPORTANT
        }
        */


        // Reading Stream
        Scanner in = new Scanner(System.in); // user input, example of a stream

        try (Scanner read = new Scanner(f)) {
            // dangerous code
            //System.out.println(read.next());
        } catch(FileNotFoundException e) {
            System.err.println("cannot find file"); // error message (its just red)
        } finally { // runs no matter what, but runs at the end of a try catch block
           // read.close();
            // ALWAYS RUNS
        }


        // Output Stream
        Scanner output = new Scanner(System.in); // user input, example of a stream

        try (Scanner read = new Scanner(f); PrintWriter pw = new PrintWriter(f)) { // TRY WITH RESOURCES
            // dangerous code
            //System.out.println(read.next());

        } catch(FileNotFoundException e) {
            System.err.println("cannot find file"); // error message (its just red)
        } finally { // runs no matter what, but runs at the end of a try catch block
            // read.close();
            // ALWAYS RUNS
        }

        try (PrintWriter pw = new PrintWriter(f)) {
            pw.println("Printing more stuff");
            pw.flush();
            System.out.println(f.length());
        } catch (FileNotFoundException e) {
            System.out.println("You destroyed it");
        }



    }
}
