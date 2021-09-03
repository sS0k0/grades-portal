package model;

import org.json.JSONObject;

public class Professor extends Account{

    public Professor(String name, String password) {
        super(name, password);
        this.role = "professor";
    }

}
