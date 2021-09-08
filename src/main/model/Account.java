package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Writable {

    protected String name;
    protected String password;
    protected String role;
    protected List<Course> courses;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
        this.courses = new ArrayList<>();
    }

    // EFFECTS : returns name attached to Account
    public String getName() {
        return this.name;
    }

    // EFFECTS : returns password attached to Account
    public String getPassword() {
        return this.password;
    }

    // EFFECTS : returns role attached to Account
    public String getRole() {
        return this.role;
    }

    // EFFECTS : returns a list of courses
    public List<Course> getCourses() {
        return this.courses;
    }

    // MODIFIES : this
    // EFFECTS : adds a course to the list of courses if not already contained
    public abstract void addCourse(Course course);

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("username", name);
        json.put("password", password);
        json.put("role", role);
        json.put("courses", courses);
        return json;
    }
}
