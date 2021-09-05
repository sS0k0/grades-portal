package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

public abstract class Account implements Writable {

    protected String name;
    protected String password;
    protected String role;
    protected List<Course> courses;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

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
