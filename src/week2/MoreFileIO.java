package week2;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MoreFileIO {
    public static void main(String[] args) {
        // Char = 16 bits
        // int = 32 bits
        // double = 64 bits

        // Checked exception
        // Create data for primitive values
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File("binary.bin")))) {
            //DataInputStream dis = new DataInputStream(new FileInputStream(Paths.get("binary.bin")));
            //FileOutputStream fos = new FileOutputStream(Paths.get("data", "binary.bin").toFile());
            dos.writeInt(32); // 4 bytes
            dos.writeDouble(2.76281); // 8 bytes
            dos.writeChar('c'); // 2 bytes

            //System.out.println(dis.readInt());
            //System.out.println(dis.readDouble());
            //System.out.println(dis.readChar());

        } catch (FileNotFoundException e) { // needs to be first
            System.err.println("Did not find file");
        } catch (IOException e) {
            System.err.println("IO Exception");
        }

        Path binary = Paths.get("binary.bin");
        System.out.println(binary.toFile().length());

        // Object files
        File objectFile = new File("objectFile.bin");
        Student s = new Student("Adam", 85, 0.01);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(objectFile));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(objectFile))) {
            oos.writeObject(s);
            Object o = ois.readObject();
            System.out.println((Student) o.getClass);
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find file");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("IO Exception");
        } catch (ClassNotFoundException e) {
            System.err.println("Wrong class");
        }
        System.out.println(objectFile.length());


    }
}
