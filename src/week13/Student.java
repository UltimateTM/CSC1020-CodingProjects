package week13;

public class Student implements Cloneable{
    private String name;
    private double gpa;
    private int age;
    private Student bestFriend;

    public Student(String name, double gpa, int age, Student bff) {
        this.name = name;
        this.gpa = gpa;
        this.age = age;
        this.bestFriend = bff;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public int getAge() {
        return age;
    }

    public Student getBestFriend() {
        return bestFriend;
    }

    public void setBestFriend(Student bestFriend) {
        this.bestFriend = bestFriend;
    }

    @Override
    public Object clone() {
        // write clone code
        return new Student(this.name, this.gpa, this.age,
                new Student(bestFriend.getName(), bestFriend.getGpa(), bestFriend.getAge(),
                        bestFriend.getBestFriend()));
    }
}
