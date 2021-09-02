package model;

import java.util.List;

public abstract class Account {

    protected String name;
    protected String password;
    protected List<Course> courses;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
