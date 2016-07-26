package com.epicodus.andrewrusso.elforge.models;


import org.parceler.Parcel;

/**
 * Created by andrewrusso on 7/21/16.
 */
@Parcel
public class Game {
    private String name;
    private String id;
    private String imageUrl;
    private String pushId;

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


    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
