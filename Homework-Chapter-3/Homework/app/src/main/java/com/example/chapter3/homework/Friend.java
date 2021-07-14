package com.example.chapter3.homework;

import java.security.PublicKey;

public class Friend {
    private String name;
    private int imageId;

    public Friend(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }
}
