package model;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String name;
    private int id;
    private int section;
    private int maxSize;
    private List<Student> students;
    private Professor professor;
    private List<Assignment> assignments;

    public Course(String name, int id, int section, int maxSize, Professor professor,  List<Assignment> assignments) {
        this.name = name;
        this.id = id;
        this.section = section;
        this.maxSize = maxSize;
        this.professor = professor;
        this.assignments = assignments;
        this.students = new ArrayList<>();
        professor.addCourse(this);
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public int getId() {
        return this.id;
    }

    // MODIFIES : this
    // EFFECTS : adds Student to the list of students
    public void addStudent(Student student) {
        if (!students.contains(student)) {
            this.students.add(student);
        }
        if (!student.getCourses().contains(this)) {
            student.addCourse(this);
        }
    }


}
