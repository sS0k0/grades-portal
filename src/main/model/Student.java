package model;

public class Student extends Account{

    public Student(String name, String password) {
        super(name, password);
        this.role = "student";
    }

    @Override
    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
        if (!course.getStudents().contains(this)) {
            course.addStudent(this);
        }
    }
}
