package com.epicodus.andrewrusso.elforge.models;

/**
 * Created by andrewrusso on 7/21/16.
 */
public class Game {
    private String name;
    private String id;
    private String imageUrl;

    public Game() {}




    public Game(String name, String id, String imageUrl) {
        this.name = name;
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }


    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }



}
