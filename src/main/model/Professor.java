package model;

import org.json.JSONObject;

public class Professor extends Account{

    public Professor(String name, String password) {
        super(name, password);
        this.role = "professor";
    }

    @Override
    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

}
