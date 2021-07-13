package com.example.chapter2_1;

public class Item {
    private String text;
    private int imgId;

    public Item(String text, int imgId){
        this.text = text;
        this.imgId = imgId;
    }
    public String getText(){
        return text;
    }
    public int getImgId(){
        return imgId;
    }
}
