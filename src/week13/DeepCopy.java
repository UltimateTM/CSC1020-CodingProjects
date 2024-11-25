package week13;

import java.util.ArrayList;
import java.util.List;

public class DeepCopy {
    public static void main(String[] args) {
        Student s1 = new Student("Miguel", 3.43, 19, null);
        Student s2 = new Student("Ferris", 1.5, 19, s1);

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);

        addStudent(students);
        System.out.println(students.size());
        Student s3 = s1; // assigning memory location, not variables
        s3 = new Student(s1.getName(), s1.getGpa(), s1.getAge(), s1.getBestFriend()); // new memory location
        // all instance variables in the Student object creation have their own unique
        // memory location, EXCEPT s1.getBestFriend();
        // Because it is referring to a student object
        System.out.println(s1);
        System.out.println(s3);
        System.out.println(s2.getBestFriend());
        System.out.println(s3.getBestFriend());
        s3.getBestFriend().setBestFriend(s3);
        System.out.println(s3.getBestFriend().getName());
        System.out.println(s3.getBestFriend().getName());
        // to solve this issue, we use the java clone method

    }

    private static void addStudent(List<Student> list) {
        list.add(new Student("Sean", 4.2, 55,
                new Student("Bill", 4.1, 85, null)));
    }
}
