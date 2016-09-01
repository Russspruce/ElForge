package com.epicodus.andrewrusso.elforge.models;

/**
 * Created by andrewrusso on 9/1/16.
 */
public class Convo {

    private String message;
    private String name;


    private String date;

    private Convo() {
    }

    public Convo(String name, String message, String date) {
        this.name = name;
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
