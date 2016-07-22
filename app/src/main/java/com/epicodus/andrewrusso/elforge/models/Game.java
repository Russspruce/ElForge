package com.epicodus.andrewrusso.elforge.models;

/**
 * Created by andrewrusso on 7/21/16.
 */
public class Game {
    private String name;
//    private String deck;
    private String id;
    private String image;

    public Game() {}




    public Game(String name, String id, String image) {
        this.name = name;
//        this.deck = deck;
        this.id = id;
        this.image = image;
    }

    public String getName() {
        return name;
    }

//    public String getDeck() {
//        return deck;
//    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }



}
