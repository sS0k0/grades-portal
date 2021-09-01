package model;

import java.util.List;

public class Account {

    protected String name;
    protected String password;
    protected List<Course> courses;

    // Creates a new Account instance
    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
