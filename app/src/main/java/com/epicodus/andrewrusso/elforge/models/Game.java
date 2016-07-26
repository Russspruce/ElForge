package com.epicodus.andrewrusso.elforge.models;


import org.parceler.Parcel;

/**
 * Created by andrewrusso on 7/21/16.
 */
@Parcel
public class Game {
    String name;
    String id;
    String imageUrl;
    String pushId;
    String index;

    public Game() {}


    public Game(String name, String id, String imageUrl) {
        this.name = name;
        this.id = id;
        this.imageUrl = imageUrl;
        this.index = "not_specified";
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

    public String getIndex() {
        return index;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }


    public void setIndex(String index) {
        this.index = index;
    }
}
