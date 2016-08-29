package com.epicodus.andrewrusso.elforge.models;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andrewrusso on 8/17/16.
 */
public class Friends {

    private String name;
    private String id;
    private String email;
    private List<String> groupIds = new ArrayList<>();


    public Friends() {}

    public Friends(String name, String id, String email, List<String> groupIds) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("id", id);
        result.put("email", email);
        result.put("groupIds", groupIds);

        return result;
    }
}
