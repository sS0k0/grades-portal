package model;

public class Student extends Account{

    public Student(String name, String password) {
        super(name, password);
        this.role = "student";
    }
}
